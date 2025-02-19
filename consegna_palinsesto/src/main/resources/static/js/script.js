var xmlFileName;
var idPalinsesto;

function aggiornaOra() {
    var oraCorrente = new Date();
    var ore = oraCorrente.getHours();
    var minuti = oraCorrente.getMinutes();
    var secondi = oraCorrente.getSeconds();

    ore = (ore < 10 ? "0" : "") + ore;
    minuti = (minuti < 10 ? "0" : "") + minuti;
    secondi = (secondi < 10 ? "0" : "") + secondi;

    var ora = ore + ":" + minuti + ":" + secondi;
    document.getElementById("ora").textContent = ora;
}

function aggiornaData() {
    var dataCorrente = new Date();
    var giorno = dataCorrente.getDate();
    var mese = dataCorrente.getMonth() + 1;
    var anno = dataCorrente.getFullYear();

    mese = (mese < 10 ? "0" : "") + mese;
    giorno = (giorno < 10 ? "0" : "") + giorno;

    var data = giorno + "/" + mese + "/" + anno;
    document.getElementById("data").textContent = data;
}

setInterval(aggiornaOra, 1000);
setInterval(aggiornaData, 1000);

window.onload = function () {
    var idImpianto = 1;

    $.ajax({
        url: "http://localhost:8080/api/getPalinsesto?id_impianto=" + idImpianto,
        type: "GET",
        success: function (response) {
            xmlFileName = response.xmlFileName;
            console.log("Nome del file XML: ", xmlFileName);
            caricaPalinsesto(xmlFileName);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Errore durante il recupero del nome del file XML: ", textStatus, errorThrown);
        }
    });

    $.ajax({
        url: "http://localhost:8080/api/getIdPalinsesto?id_impianto=" + idImpianto,
        type: "GET",
        success: function (response) {
            idPalinsesto = response.id_palinsesto;
            console.log("ID Palinsesto: ", idPalinsesto);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Errore durante il recupero dell'ID del palinsesto: ", textStatus, errorThrown);
        }
    });
};

function caricaPalinsesto() {
    console.log("Caricamento del file XML: ", "palinsesti/" + xmlFileName);
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            console.log("Stato della risposta: ", xmlhttp.status);
            console.log("Risposta della richiesta: ", xmlhttp.responseText);
            if (xmlhttp.status == 200) {
                var xmlDoc = xmlhttp.responseXML;
                if (xmlDoc) {
                    var cartelloni = xmlDoc.getElementsByTagName("cartellone");
                    avviaPalinsesto(cartelloni, 0);
                } else {
                    console.error("Il documento XML è null. Verifica la risposta della richiesta.");
                }
            } else {
                console.error("Errore durante il recupero del file XML. Stato: ", xmlhttp.status);
            }
        }
    };
    xmlhttp.open("GET", "palinsesti/" + xmlFileName, true);
    xmlhttp.send();
}

function avviaPalinsesto(cartelloni, index) {
    var cartellone = cartelloni[index];
    var fileHTML = cartellone.getElementsByTagName("fileHTML")[0].childNodes[0].nodeValue;
    var pathHTML = "cartelloni/" + fileHTML;
    fileHTML = fileHTML.replace(".html", "")
    var durata = parseInt(cartellone.getElementsByTagName("durata")[0].childNodes[0].nodeValue);
    var boxDiv = document.getElementById("box");    //document rappresenta l'intero documento HTML o XML caricato nel browser.
    var htmlRequest = new XMLHttpRequest();
    htmlRequest.onreadystatechange = function () {
        if (htmlRequest.readyState == 4 && htmlRequest.status == 200) {
            boxDiv.innerHTML = htmlRequest.responseText;

            var nome_cartellone = fileHTML;
            $.ajax({
                url: "http://localhost:8000/jakartaEE_war/writeLog",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    id_impianto: idImpianto,
                    id_palinsesto: idPalinsesto,
                    nome_cartellone: nome_cartellone,
                    durata: durata,
                })
            });

            setTimeout(function () {
                var nextIndex = (index + 1) % cartelloni.length;
                avviaPalinsesto(cartelloni, nextIndex);
            }, durata * 1000);
        }
    };
    htmlRequest.open("GET", pathHTML, true);
    htmlRequest.send();
}

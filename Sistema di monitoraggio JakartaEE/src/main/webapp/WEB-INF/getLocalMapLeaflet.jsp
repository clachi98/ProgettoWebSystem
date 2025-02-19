<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Mappa degli Impianti con Leaflet</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <style>
        #map {
            width: 100%;
            height: 600px;
        }
        .green-marker {
            background-color: green;
            border: 2px solid white;
            border-radius: 50%;
            width: 10px;
            height: 10px;
        }
        .red-marker {
            background-color: red;
            border: 2px solid white;
            border-radius: 50%;
            width: 10px;
            height: 10px;
        }
        #buttonContainer {
            margin-top: 20px;
        }
        .button {
            padding: 10px 20px;
            margin-right: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Mappa degli Impianti con Leaflet</h1>
<div id="map"></div>
<div id="buttonContainer">
    <button id="updateButton" class="button">Aggiorna posizione impianti</button>
    <button id="reloadButton" class="button">Aggiorna stato impianti</button>
</div>
<script>
    function initMap() {
        var map = L.map('map').setView([38.116709182804854, 13.354767535479171], 14);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        <%
            HashMap<Integer, JSONObject> logMap = (HashMap<Integer, JSONObject>) request.getAttribute("logMap");
            for (Map.Entry<Integer, JSONObject> entry : logMap.entrySet()) {
                Integer id = entry.getKey();
                JSONObject impianto = entry.getValue();
                String latitudine = impianto.optString("latitudine");
                String longitudine = impianto.optString("longitudine");
                boolean stato = impianto.optBoolean("stato");

                if (!latitudine.isEmpty() && !longitudine.isEmpty()) {
                    String colorClass = stato ? "green-marker" : "red-marker";
        %>
        var marker = L.marker([<%= latitudine %>, <%= longitudine %>], {
            icon: L.divIcon({
                className: '<%= colorClass %>',
                iconSize: [10, 10]
            })
        }).addTo(map).bindPopup("Impianto <%= id %>");
        <%
                }
            }
        %>
    }

    document.addEventListener('DOMContentLoaded', initMap);

    document.getElementById('updateButton').addEventListener('click', function() {
        console.log("Update button clicked");
        fetch('/jakartaEE_war/updateLogMap', { method: 'POST' })
            .then(response => {
                console.log("Response received", response);
                if (response.ok) {
                    location.reload();
                } else {
                    alert('Errore durante l\'aggiornamento della mappa.');
                }
            })
            .catch(error => {
                console.error('Fetch error:', error);
                alert('Errore durante l\'aggiornamento della mappa.');
            });
    });


    document.getElementById('reloadButton').addEventListener('click', function() {
        location.reload();
    });
</script>
</body>
</html>

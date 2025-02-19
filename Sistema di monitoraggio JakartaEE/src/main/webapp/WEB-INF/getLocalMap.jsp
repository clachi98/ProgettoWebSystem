<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Mappa degli Impianti</title>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdYkNOeVsygxZ9_waxkvDY9bvcyb0MhBE"></script>
    <script>
        function initMap() {
            var mapOptions = {
                center: { lat: 38.116709182804854, lng: 13.354767535479171 },
                zoom: 6
            };
            var map = new google.maps.Map(document.getElementById("map"), mapOptions);

            <%
                HashMap<Integer, JSONObject> logMap = (HashMap<Integer, JSONObject>) request.getAttribute("logMap");
                for (Map.Entry<Integer, JSONObject> entry : logMap.entrySet()) {
                    Integer id = entry.getKey();
                    JSONObject impianto = entry.getValue();
                    String latitudine = impianto.optString("latitudine");
                    String longitudine = impianto.optString("longitudine");
                    boolean stato = impianto.optBoolean("stato");

                    if (!latitudine.isEmpty() && !longitudine.isEmpty()) {
                        String color = stato ? "green" : "red";
            %>
            var marker = new google.maps.Marker({
                position: { lat: <%= Double.parseDouble(latitudine) %>, lng: <%= Double.parseDouble(longitudine) %> },
                map: map,
                title: "Impianto <%= id %>",
                icon: {
                    path: google.maps.SymbolPath.CIRCLE,
                    fillColor: "<%= color %>",
                    fillOpacity: 0.8,
                    strokeColor: "white",
                    strokeWeight: 2,
                    scale: 5
                }
            });
            <%
                    }
                }
            %>
        }
    </script>
</head>
<body onload="initMap()">
<h1>Mappa degli Impianti</h1>
<div id="map" style="width: 100%; height: 600px;"></div>
</body>
</html>

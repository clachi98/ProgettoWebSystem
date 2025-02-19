package org.example.jakarta.Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

@WebServlet(name = "updateMap", value = "/updateMap")
public class updateMap extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try (Connection conn = DBConnectionDatasourceJDBC.getConnection()) {
            // Query per ottenere l'ultimo timestamp per ogni impianto
            String query = "SELECT i.latitudine, i.longitudine, i.ID, " +
                    "       (SELECT MAX(l.timestamp) " +
                    "        FROM log l " +
                    "        WHERE l.impianto = i.ID) AS last_timestamp " +
                    "FROM impianti i " +
                    "ORDER BY i.ID";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            out.print("["); // Inizio dell'array JSON

            boolean first = true;
            while (rs.next()) {
                double latitudine = rs.getDouble("latitudine");
                double longitudine = rs.getDouble("longitudine");
                Timestamp timestamp = rs.getTimestamp("last_timestamp");

                // Debug: stampare i valori del database
                System.out.println("Latitudine: " + latitudine);
                System.out.println("Longitudine: " + longitudine);
                System.out.println("Timestamp: " + timestamp);

                LocalDateTime logTime = timestamp != null ? timestamp.toLocalDateTime() : LocalDateTime.MIN;
                LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Europe/Rome"));

                // Debug: stampare i tempi
                System.out.println("LogTime: " + logTime);
                System.out.println("CurrentTime: " + currentTime);

                Duration duration = Duration.between(logTime, currentTime);
                boolean status = duration.toMinutes() < 2;

                // Debug: stampare la durata e lo stato
                System.out.println("Duration: " + duration.toMinutes() + " minutes");
                System.out.println("Status: " + status);

                // Costruzione manuale dell'oggetto JSON
                if (!first) {
                    out.print(",");
                }
                out.print("{\"latitudine\": " + latitudine + ",");
                out.print("\"longitudine\": " + longitudine + ",");
                out.print("\"stato\": " + status + "}");
                first = false;
            }

            out.print("]"); // Fine dell'array JSON
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

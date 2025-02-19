package org.example.jakarta.Service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.jakarta.Beans.myMap;
import org.example.jakarta.Controller.DBConnectionDatasourceJDBC;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class LogService {

    @Inject
    private TimerUtility timerUtility;

    @Inject
    private myMap mappa;

    String tableLog = "log";
    String tableCartellone = "cartelloni";


    public String writeLog(JSONObject json) {
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2GetIdCartellone = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionDatasourceJDBC.getConnection();
            String insertLog = "insert into " + tableLog + "(impianto, palinsesto, cartellone, durata, timestamp) values (?,?,?,?,?)";
            String getIdCartellone = "SELECT ID from " + tableCartellone + " WHERE nome = ?";

            statement2GetIdCartellone = connection.prepareStatement(getIdCartellone);
            statement2GetIdCartellone.setString(1, json.getString("nome_cartellone"));
            resultSet = statement2GetIdCartellone.executeQuery();
            int idCartellone = 0;
            if (resultSet.next()) {
                idCartellone = resultSet.getInt("ID");
            } else {
                throw new RuntimeException("Non esistono cartelloni associati a questo nome: " + json.getString("nome_cartellone") +" : impossibile scrivere il record");
            }

            statement = connection.prepareStatement(insertLog);
            statement.setInt(1, Integer.parseInt(json.get("id_impianto").toString()));
            statement.setInt(2, Integer.parseInt(json.get("id_palinsesto").toString()));
            statement.setInt(3, idCartellone);
            statement.setInt(4, Integer.parseInt(json.get("durata").toString()));
            statement.setString(5, getTimestamp());

            statement.executeUpdate();


            switch (json.getInt("id_impianto")) {
                case 1:
                    timerUtility.startTimer1();
                    if (!(boolean) mappa.getLogMap().get(1).get("stato")) {
                        mappa.getLogMap().get(1).put("stato", true);
                    }
                    break;
                case 2:
                    timerUtility.startTimer2();
                    if (!(boolean) mappa.getLogMap().get(2).get("stato")) {
                        mappa.getLogMap().get(2).put("stato", true);
                    }
                    break;
                case 3:
                    timerUtility.startTimer3();
                    if (!(boolean) mappa.getLogMap().get(3).get("stato")) {
                        mappa.getLogMap().get(3).put("stato", true);
                    }
                    break;
                case 4:
                    timerUtility.startTimer4();
                    if (!(boolean) mappa.getLogMap().get(4).get("stato")) {
                        mappa.getLogMap().get(4).put("stato", true);
                    }
                    break;
                case 5:
                    timerUtility.startTimer5();
                    if (!(boolean) mappa.getLogMap().get(5).get("stato")) {
                        mappa.getLogMap().get(5).put("stato", true);
                    }
                    break;
                default:
                    // Azione di default
                    break;
            }


            String message = "Record inserito!";
            return message;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public String getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
}

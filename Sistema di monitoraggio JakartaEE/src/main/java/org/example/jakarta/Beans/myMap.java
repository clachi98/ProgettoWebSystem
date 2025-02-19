package org.example.jakarta.Beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.jakarta.Controller.DBConnectionDatasourceJDBC;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@ApplicationScoped
public class myMap {

    String table;
    HashMap<Integer, JSONObject> logMap;


    @PostConstruct
    public void initialize() {
        table = "impianti";
        logMap = new HashMap<>();
        logMap.put(1, new JSONObject().put("stato", false));
        logMap.put(2, new JSONObject().put("stato", false));
        logMap.put(3, new JSONObject().put("stato", false));
        logMap.put(4, new JSONObject().put("stato", false));
        logMap.put(5, new JSONObject().put("stato", false));

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnectionDatasourceJDBC.getConnection();
            String query = "SELECT * FROM " + table;
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String latitudine = resultSet.getString("latitudine");
                String longitudine = resultSet.getString("longitudine");

                logMap.put(id, logMap.get(id).put("latitudine", latitudine));
                logMap.put(id, logMap.get(id).put("longitudine", longitudine));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshLogMap() {
        logMap.clear();
        initialize();
    }


    public HashMap<Integer, JSONObject> getLogMap() {
        return logMap;
    }

}

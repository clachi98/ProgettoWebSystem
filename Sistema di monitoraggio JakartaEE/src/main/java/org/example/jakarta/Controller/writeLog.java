package org.example.jakarta.Controller;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jakarta.Service.LogService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "writeLog", value = "/writeLog")
public class writeLog extends HttpServlet {

    @Inject
    private LogService logService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Aggiungi intestazioni CORS
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        // Leggi il corpo della richiesta POST come stringa JSON
        String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream())).lines()
                .collect(Collectors.joining("\n"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(requestBody);
            String responseMessage = logService.writeLog(jsonObject);
            out.println(responseMessage);
        } catch (JSONException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "JSON non valido");
        }
    }

    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Aggiungi intestazioni CORS anche per le richieste di tipo OPTIONS
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
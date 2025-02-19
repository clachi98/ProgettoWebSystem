package org.example.jakarta.Controller;

import org.example.jakarta.Beans.myMap;
import org.json.JSONObject;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/getLocalMap")
public class MappaServlet extends HttpServlet {

    @Inject
    private myMap myMapBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<Integer, JSONObject> logMap = myMapBean.getLogMap();
        req.setAttribute("logMap", logMap);
        String mapType = req.getParameter("mapType");
        if ("leaflet".equalsIgnoreCase(mapType)) {
            req.getRequestDispatcher("/WEB-INF/getLocalMapLeaflet.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/getLocalMap.jsp").forward(req, resp);
        }
    }
}


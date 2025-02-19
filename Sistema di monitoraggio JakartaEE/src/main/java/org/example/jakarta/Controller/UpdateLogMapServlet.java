package org.example.jakarta.Controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jakarta.Beans.myMap;

import java.io.IOException;

@WebServlet("/updateLogMap")
public class UpdateLogMapServlet extends HttpServlet {

    @Inject
    private myMap myMapBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        myMapBean.refreshLogMap();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

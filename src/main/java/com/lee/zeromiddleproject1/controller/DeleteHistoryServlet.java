package com.lee.zeromiddleproject1.controller;

import com.lee.zeromiddleproject1.service.HistoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteHistoryServlet", value = "/deleteHistoryServlet")
public class DeleteHistoryServlet extends HttpServlet {

    private HistoryService historyService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        historyService = new HistoryService();

        try {
            historyService.deleteHistory(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

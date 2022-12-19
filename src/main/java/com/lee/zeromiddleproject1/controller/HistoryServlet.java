package com.lee.zeromiddleproject1.controller;

import com.lee.zeromiddleproject1.dto.HistoryDto;
import com.lee.zeromiddleproject1.service.HistoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "historyServlet", value = "/history-servlet")
public class HistoryServlet extends HttpServlet {
    private HistoryService historyService = new HistoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<HistoryDto> histories;

        try {
            histories = historyService.selectAllHistories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/history.jsp");
        request.setAttribute("histories", histories);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

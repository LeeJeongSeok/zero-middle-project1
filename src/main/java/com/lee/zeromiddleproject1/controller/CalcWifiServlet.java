package com.lee.zeromiddleproject1.controller;

import com.lee.zeromiddleproject1.common.WifiComparator;
import com.lee.zeromiddleproject1.dto.HistoryDto;
import com.lee.zeromiddleproject1.dto.WifiDto;
import com.lee.zeromiddleproject1.service.WifiService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "CalcWifiServlet", value = "/getWifiInfo")
public class CalcWifiServlet extends HttpServlet {

    private WifiService wifiService = new WifiService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<WifiDto> result = new ArrayList<>();

        double lat = Double.parseDouble(request.getParameter("lat"));
        double lnt = Double.parseDouble(request.getParameter("lnt"));

        try {
            ArrayList<WifiDto> wifiList = wifiService.selectAll();

            for (WifiDto wifiDto : wifiList) {
                wifiDto.setDistance(distance(lat, lnt, Double.parseDouble(wifiDto.getLat()), Double.parseDouble(wifiDto.getLnt())));
            }

            // 거리 순으로 정렬
            Collections.sort(wifiList, new WifiComparator());

            // 가장 가까운 값 20개만 뽑음
            for (int i = 0; i < 20; i++) {
                System.out.println(wifiList.get(i).toString());
                result.add(wifiList.get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        HistoryDto historyDto = new HistoryDto();
        historyDto.setLat(String.valueOf(lat));
        historyDto.setLnt(String.valueOf(lnt));
        historyDto.setUse_flag("Y");

        wifiService.insertHistory(historyDto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        request.setAttribute("wifiList", result);
        dispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static double distance(double lat1, double lnt1, double lat2, double lnt2) {

        double theta = lnt1 - lnt2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return dist * 1.609344;
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}

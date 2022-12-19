package com.lee.zeromiddleproject1.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lee.zeromiddleproject1.dto.WifiDto;
import com.lee.zeromiddleproject1.service.WifiService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "wifiServlet", value = "/load-wifi")
public class WifiServlet extends HttpServlet {

    private WifiService wifiService = new WifiService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<WifiDto> wifiList = new ArrayList<>();

        int startPage = 1;
        int endPage = 1000;
        int totalCount;

        while (true) {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" + URLEncoder.encode("인증키 자리","UTF-8") ); // 인증키
            urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startPage),"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endPage),"UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");

            System.out.println("Response code: " + conn.getResponseCode() + "/" + "Start Page : " + startPage + "/" + "End Page : " + endPage);
            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }


            JsonElement jsonElement = JsonParser.parseString(sb.toString());
            JsonObject tbPublicWifiInfo = jsonElement.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
            JsonArray row = tbPublicWifiInfo.getAsJsonObject().get("row").getAsJsonArray();
            totalCount = tbPublicWifiInfo.getAsJsonObject().get("list_total_count").getAsInt();

            for (int i = 0; i < row.size(); i++) {
                JsonObject jsonObject = (JsonObject) row.get(i);
                WifiDto wifiDto = new WifiDto();
                wifiDto.setMgr_no(jsonObject.get("X_SWIFI_MGR_NO").getAsString());
                wifiDto.setWrdofc(jsonObject.get("X_SWIFI_WRDOFC").getAsString());
                wifiDto.setMain_nm(jsonObject.get("X_SWIFI_MAIN_NM").getAsString());
                wifiDto.setAdres1(jsonObject.get("X_SWIFI_ADRES1").getAsString());
                wifiDto.setAdres2(jsonObject.get("X_SWIFI_ADRES2").getAsString());
                wifiDto.setInstl_floor(jsonObject.get("X_SWIFI_INSTL_FLOOR").getAsString());
                wifiDto.setInstl_ty(jsonObject.get("X_SWIFI_INSTL_TY").getAsString());
                wifiDto.setInstl_mby(jsonObject.get("X_SWIFI_INSTL_MBY").getAsString());
                wifiDto.setSvc_se(jsonObject.get("X_SWIFI_SVC_SE").getAsString());
                wifiDto.setCmcwr(jsonObject.get("X_SWIFI_CMCWR").getAsString());
                wifiDto.setCnstc_year(jsonObject.get("X_SWIFI_CNSTC_YEAR").getAsString());
                wifiDto.setInout_door(jsonObject.get("X_SWIFI_INOUT_DOOR").getAsString());
                wifiDto.setRemars3(jsonObject.get("X_SWIFI_REMARS3").getAsString());
                wifiDto.setLat(jsonObject.get("LAT").getAsString());
                wifiDto.setLnt(jsonObject.get("LNT").getAsString());
                wifiDto.setWork_dttm(jsonObject.get("WORK_DTTM").getAsString());
                wifiList.add(wifiDto);
            }

            if (endPage > totalCount) {
                rd.close();
                conn.disconnect();
                break;
            }

            startPage += 1000;
            endPage += 1000;


            System.out.println();
        }

        try {
            wifiService.insertWifiList(wifiList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/load-wifi.jsp");
        request.setAttribute("totalCount", totalCount);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

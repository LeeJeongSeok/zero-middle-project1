package com.lee.zeromiddleproject1.dao;

import com.lee.zeromiddleproject1.conn.DBConnection;
import com.lee.zeromiddleproject1.dto.Data;
import com.lee.zeromiddleproject1.dto.HistoryDto;
import com.lee.zeromiddleproject1.dto.WifiDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WifiDao {

    static DBConnection connection = DBConnection.getInstance();

    public static ArrayList<WifiDto> selectAll() throws SQLException {

        ArrayList<WifiDto> wifiList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connection.getConnection();
            pstmt = conn.prepareStatement("select * from tb_wifi_master");
            rs = pstmt.executeQuery();//select실행

            while(rs.next()) {
                WifiDto wifiDto = new WifiDto();
                wifiDto.setMgr_no(rs.getString("mgr_no"));
                wifiDto.setWrdofc(rs.getString("wrdofc"));
                wifiDto.setMain_nm(rs.getString("main_nm"));
                wifiDto.setAdres1(rs.getString("adres1"));
                wifiDto.setAdres2(rs.getString("adres2"));
                wifiDto.setInstl_floor(rs.getString("instl_floor"));
                wifiDto.setInstl_ty(rs.getString("instl_ty"));
                wifiDto.setInstl_mby(rs.getString("instl_mby"));
                wifiDto.setSvc_se(rs.getString("svc_se"));
                wifiDto.setCmcwr(rs.getString("cnstc_year"));
                wifiDto.setInout_door(rs.getString("inout_door"));
                wifiDto.setRemars3(rs.getString("remars3"));
                wifiDto.setLat(rs.getString("lat"));
                wifiDto.setLnt(rs.getString("lnt"));
                wifiDto.setWork_dttm(rs.getString("work_dttm"));
                wifiList.add(wifiDto);
            }
        }finally {
            connection.close(rs, pstmt, conn);
        }

        return wifiList;
    }

    public static void insertHistory(HistoryDto historyDto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connection.getConnection();
            pstmt = conn.prepareStatement("insert into tb_search_history_list(lat, lnt, search_date, use_flag) values (?, ?, NOW(), ?)");

            pstmt.setString(1, historyDto.getLat());
            pstmt.setString(2, historyDto.getLnt());
            pstmt.setString(3, historyDto.getUse_flag());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close(rs, pstmt, conn);
        }
    }

    public static void insertWifiList(ArrayList<WifiDto> wifiList) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connection.getConnection();

            for (int i = 0; i < wifiList.size(); i++) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO tb_wifi_master(mgr_no, wrdofc, main_nm, adres1, adres2, instl_floor, instl_ty, instl_mby, svc_se, cmcwr, cnstc_year, inout_door, remars3, lat, lnt, work_dttm)");
                sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                sql.append("ON DUPLICATE KEY UPDATE ");
                sql.append("wrdofc = VALUES(wrdofc), ");
                sql.append("main_nm = VALUES(main_nm), ");
                sql.append("adres1 = VALUES(adres1), ");
                sql.append("adres2 = VALUES(adres2), ");
                sql.append("instl_floor = VALUES(instl_floor), ");
                sql.append("instl_ty = VALUES(instl_ty), ");
                sql.append("instl_mby = VALUES(instl_mby), ");
                sql.append("svc_se = VALUES(svc_se), ");
                sql.append("cmcwr = VALUES(cmcwr), ");
                sql.append("cnstc_year = VALUES(cnstc_year), ");
                sql.append("inout_door = VALUES(inout_door), ");
                sql.append("remars3 = VALUES(remars3), ");
                sql.append("lat = VALUES(lat), ");
                sql.append("lnt = VALUES(lnt), ");
                sql.append("work_dttm = VALUES(work_dttm)");


                pstmt = conn.prepareStatement(sql.toString());

                pstmt.setString(1, wifiList.get(i).getMgr_no());
                pstmt.setString(2, wifiList.get(i).getWrdofc());
                pstmt.setString(3, wifiList.get(i).getMain_nm());
                pstmt.setString(4, wifiList.get(i).getAdres1());
                pstmt.setString(5, wifiList.get(i).getAdres2());
                pstmt.setString(6, wifiList.get(i).getInstl_floor());
                pstmt.setString(7, wifiList.get(i).getInstl_ty());
                pstmt.setString(8, wifiList.get(i).getInstl_mby());
                pstmt.setString(9, wifiList.get(i).getSvc_se());
                pstmt.setString(10, wifiList.get(i).getCmcwr());
                pstmt.setString(11, wifiList.get(i).getCnstc_year());
                pstmt.setString(12, wifiList.get(i).getInout_door());
                pstmt.setString(13, wifiList.get(i).getRemars3());
                pstmt.setString(14, wifiList.get(i).getLat());
                pstmt.setString(15, wifiList.get(i).getLnt());
                pstmt.setString(16, wifiList.get(i).getWork_dttm());
                pstmt.executeUpdate();
            }


        }finally {
            connection.close(rs, pstmt, conn);
        }
    }
}

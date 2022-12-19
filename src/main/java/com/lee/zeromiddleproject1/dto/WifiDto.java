package com.lee.zeromiddleproject1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
@ToString
public class WifiDto {

    private double distance;
    private String mgr_no;
    private String wrdofc;
    private String main_nm;
    private String adres1;
    private String adres2;
    private String instl_floor;
    private String instl_ty;
    private String instl_mby;
    private String svc_se;
    private String cmcwr;
    private String cnstc_year;
    private String inout_door;
    private String remars3;
    private String lat;
    private String lnt;
    private String work_dttm;

}

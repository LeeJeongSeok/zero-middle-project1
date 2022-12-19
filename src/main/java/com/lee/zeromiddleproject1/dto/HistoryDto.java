package com.lee.zeromiddleproject1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class HistoryDto {

    private int id;
    private String lat;
    private String lnt;
    private String search_date;
    private String use_flag;
}

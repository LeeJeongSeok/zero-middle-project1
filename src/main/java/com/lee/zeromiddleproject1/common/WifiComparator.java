package com.lee.zeromiddleproject1.common;

import com.lee.zeromiddleproject1.dto.WifiDto;

import java.util.Comparator;

public class WifiComparator implements Comparator<WifiDto> {
    @Override
    public int compare(WifiDto o1, WifiDto o2) {
        if (o1.getDistance() > o2.getDistance()) {
            return 1;
        } else if (o1.getDistance() < o2.getDistance()) {
            return -1;
        } else {
            return 0;
        }
    }
}

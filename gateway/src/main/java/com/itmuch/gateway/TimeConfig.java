package com.itmuch.gateway;

import java.time.LocalTime;

/**
 * @program: gateway
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-05 12:04
 **/
public class TimeConfig {
    private LocalTime startTime;
    private LocalTime endTime;

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}


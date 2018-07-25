package com.ef;

import java.util.Date;

public class CommandLineArgs {
    private String duration;
    private Date startDate;
    private Long threshold;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }
}

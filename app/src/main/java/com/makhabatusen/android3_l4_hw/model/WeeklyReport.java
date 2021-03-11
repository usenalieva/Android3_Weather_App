package com.makhabatusen.android3_l4_hw.model;

public class WeeklyReport {
    private String day;
    private int weatherCondition;
    private String maxDegree;
    private String minDegree;

    public WeeklyReport(String day, int weatherCondition, String maxDegree, String minDegree) {
        this.day = day;
        this.weatherCondition = weatherCondition;
        this.maxDegree = maxDegree;
        this.minDegree = minDegree;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(int weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(String maxDegree) {
        this.maxDegree = maxDegree;
    }

    public String getMinDegree() {
        return minDegree;
    }

    public void setMinDegree(String minDegree) {
        this.minDegree = minDegree;
    }
}

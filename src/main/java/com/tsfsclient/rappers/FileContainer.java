package com.tsfsclient.rappers;

public class FileContainer {
    private String absolutePath;
    private String fileName;
    private String startDay, startMonth, startYear, endDay, endMonth, endYear;
    private String[] citiesArray, linesArray;

    public FileContainer(String absolutePath, String fileName, String startDay, String startMonth, String startYear, String endDay, String endMonth, String endYear, String[] citiesArray, String[] linesArray) {
        this.absolutePath = absolutePath;
        this.fileName = fileName;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.citiesArray = citiesArray;
        this.linesArray = linesArray;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String[] getCitiesArray() {
        return citiesArray;
    }

    public void setCitiesArray(String[] citiesArray) {
        this.citiesArray = citiesArray;
    }

    public String[] getLinesArray() {
        return linesArray;
    }

    public void setLinesArray(String[] linesArray) {
        this.linesArray = linesArray;
    }
}


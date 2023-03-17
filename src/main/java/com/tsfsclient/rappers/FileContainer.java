package com.tsfsclient.rappers;

import java.util.List;

public class FileContainer {
    private String absolutePath;
    private String fileName;
    private String startDay, startMonth, startYear, endDay, endMonth, endYear;
    private List<String> citiesList, linesList;

    public FileContainer(String absolutePath, String fileName, String startDay, String startMonth, String startYear, String endDay, String endMonth, String endYear, List<String> citiesList, List<String> linesList) {
        this.absolutePath = absolutePath;
        this.fileName = fileName;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.citiesList = citiesList;
        this.linesList = linesList;
    }
}

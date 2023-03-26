package com.tsfsclient;

import javafx.beans.property.SimpleStringProperty;

public class FileTableViewRow {
    private final SimpleStringProperty fileName;
    private final SimpleStringProperty cities;
    private final SimpleStringProperty lines;

    public FileTableViewRow(String fileName, String[] cities, String[] lines) {
        this.fileName = new SimpleStringProperty(fileName);
        this.cities = new SimpleStringProperty(String.join(", ", cities));
        this.lines = new SimpleStringProperty(String.join(", ", lines));
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public String getCities() {
        return cities.get();
    }

    public SimpleStringProperty citiesProperty() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities.set(cities);
    }

    public String getLines() {
        return lines.get();
    }

    public SimpleStringProperty linesProperty() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines.set(lines);
    }
}

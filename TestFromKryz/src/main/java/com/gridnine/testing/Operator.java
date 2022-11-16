package com.gridnine.testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Operator {
    private Filter filter;
    private List<Flight> resultList;
    List<Flight> flightsForFilter;
    private String filterName;

    public Operator(List<Flight> flightsForFilter) {
        this.flightsForFilter = flightsForFilter;
    }

    public List<Flight> getResultList() {
        return resultList;
    }

    public void setResultList(List<Flight> resultList) {
        this.resultList = resultList;
    }

    private List<Flight> getFlightsForFilter() {
        return flightsForFilter;
    }

    public void setFilter(Filter filter) {
        filterName =  filter.getClass().getSimpleName();
        this.filter = filter;
    }

    public List<Flight> filter(){
        this.setResultList(filter.filter(this.getFlightsForFilter()));
        return this.getResultList();
    }

    public void printResultListToConsole(){
        System.out.println();
        System.out.println("ResultList by filter: " + filterName);
        printCurrentFilterDescription();

        for (int i = 0; i < resultList.size(); i++) {
            System.out.println("flight " + i + ": " + resultList.get(i));
        }
        System.out.println();
    }

    private void printCurrentFilterDescription() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/EN.properties");
            Properties properties = new Properties();
            properties.load(fis);
            String propertyName = choosePropertyName(filterName);
            String description = properties.getProperty(propertyName);
            System.out.println("(Filter description: " + description + ")");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String choosePropertyName(String filterName) {
        switch (filterName) {
            case "FilterDepartureTimeIsBeforeNow": {
                return "timeIsBeforeNowFilterDescription";
            }
            case "FilterIncorrectSegmentsTime": {
                return "incorrectSegmentFilterDescription";
            }
            case "FilterEarthTimeGreaterThanTwoHours": {
                return "earthTimeFilterDescription";
            }
            default: return "noSuchFilter";
        }
    }
}

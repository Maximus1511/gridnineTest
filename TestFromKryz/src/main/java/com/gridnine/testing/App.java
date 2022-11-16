package com.gridnine.testing;

import java.util.List;

public class App {
    public static void main(String[] args) {
        //create all stuff
        List<Flight> initialFlights = FlightBuilder.createFlights();
        System.out.println("All generated flights:");
        showFlights(initialFlights);

        Operator operator = new Operator(initialFlights);
        Filter departureTimeFilter = new FilterDepartureTimeIsBeforeNow();
        Filter incorrectSegmentTimeFilter = new FilterIncorrectSegmentsTime();
        Filter earthTimeFilter = new FilterEarthTimeGreaterThanTwoHours();

        //check filter1
        operator.setFilter(departureTimeFilter);
        operator.filter();
        operator.printResultListToConsole();

        //check filter2
        operator.setFilter(incorrectSegmentTimeFilter);
        operator.filter();
        operator.printResultListToConsole();

        //check filter 3
        operator.setFilter(earthTimeFilter);
        operator.filter();
        operator.printResultListToConsole();
    }

    private static void showFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
        System.out.println();
    }
}

package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class FilterIncorrectSegmentsTime implements Filter {
    @Override
    public List<Flight> filter(List<Flight> flightsForFilter) {
        List<Flight> resultList = new ArrayList<>();

        for (Flight flight : flightsForFilter) {
            List<Segment> segments = flight.getSegments();
            boolean needFlight = false;
            for (Segment segment : segments) {
                if (segment.getArrivalDate().isAfter(segment.getDepartureDate())) {
                    needFlight = true;
                } else {
                    needFlight = false;
                    break;
                }
            }
            if (needFlight) {
                resultList.add(flight);
            }
        }
        return resultList;
    }
}

package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FilterEarthTimeGreaterThanTwoHours implements Filter {
    @Override
    public List<Flight> filter(List<Flight> flightsForFilter) {
        List<Flight> resultList = new ArrayList<>();

        for (int i = 0; i < flightsForFilter.size(); i++) {
            List<Segment> segments = flightsForFilter.get(i).getSegments();
            if (segments.size() <= 1) {
                continue;
            }
            long minutesOnEarth = 0;
            for (int j = 0; j < segments.size() - 1; j++) {
                LocalDateTime to = segments.get(j + 1).getDepartureDate();
                LocalDateTime from = segments.get(j).getArrivalDate();
                minutesOnEarth = minutesOnEarth + ChronoUnit.MINUTES.between(from, to);
            }
            if (minutesOnEarth > 120) {
                resultList.add(flightsForFilter.get(i));
            }
        }
        return resultList;
    }
}

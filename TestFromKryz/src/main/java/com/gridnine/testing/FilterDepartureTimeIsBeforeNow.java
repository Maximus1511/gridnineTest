package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterDepartureTimeIsBeforeNow implements Filter {
    @Override
    public List<Flight> filter(List<Flight> flightsForFilter) {
        List<Flight> resultList = new ArrayList<>();

        for (int i = 0; i < flightsForFilter.size() - 1; i++) {
            Segment segment = flightsForFilter.get(i).getSegments().get(0);
            if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                resultList.add(flightsForFilter.get(i));
            }
        }
        return resultList;
    }
}

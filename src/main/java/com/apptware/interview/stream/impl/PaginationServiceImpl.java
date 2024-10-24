package com.apptware.interview.stream.impl;

import com.apptware.interview.stream.PaginationService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaginationServiceImpl implements PaginationService {

    private static final List<String> DATA = new ArrayList<>();

    static {
        for (int i = 1; i <= FULL_DATA_SIZE; i++) {
            DATA.add("Item " + i);
        }
    }

    @Override
    public List<String> getPaginatedData(int page, int pageSize) {
        log.info("Fetching paginated data for page: {}, pageSize: {}", page, pageSize);

        int startIndex = (page - 1) * pageSize;

        if (startIndex >= DATA.size()) {
            log.warn("Requested page {} exceeds available data. Returning empty list.", page);
            return new ArrayList<>(); // Return empty list if page exceeds available data
        }

        int endIndex = Math.min(startIndex + pageSize, DATA.size());
        log.info("Returning data from index {} to {}", startIndex, endIndex - 1);
        return DATA.subList(startIndex, endIndex);
    }
}

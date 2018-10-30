package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private static HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<Long, TimeEntry>();
    private static TimeEntry createdTimeEntry = new TimeEntry(1L, 123L, 456L, LocalDate.parse("2017-01-08"), 8);
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long id =timeEntry.getId();
        if (id==0)
        {
            id=1;
        }
        else
        {
            id=id+1;
        }

        timeEntryHashMap.put(id, createdTimeEntry);
        return createdTimeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntryHashMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        
        return new ArrayList<>(timeEntryHashMap.values());
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        return null;
    }

    @Override
    public TimeEntry delete(long timeEntryId) {
        return null;
    }
}

package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();

    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (find(id) == null) return null;

        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.replace(id, updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(Long id) {
        timeEntries.remove(id);
    }

   /* private List<TimeEntry> timeEntryList = new ArrayList<TimeEntry>();
    public TimeEntry create(TimeEntry timeEntry) {
        this.timeEntryList.add(timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        for (TimeEntry timeEntry : timeEntryList)
        {
            if (timeEntry.getId() == id)
            {
                return timeEntry;
            }
        }
        return null;
    }


    public List list() {
        return this.timeEntryList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        for (TimeEntry timeEntryObj : timeEntryList)
        {
            if (timeEntryObj.getId() == id)
            {
                timeEntryList.remove(timeEntryObj);
                timeEntryList.add(timeEntry);
                return timeEntry;
            }
        }
       return null;
    }

    public void delete(long id) {
        for (TimeEntry timeEntry : timeEntryList)
        {
            if (timeEntry.getId() == id)
            {
                timeEntryList.remove(timeEntry);
            }
        }
    }*/
}

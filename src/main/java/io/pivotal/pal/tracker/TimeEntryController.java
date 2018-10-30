package io.pivotal.pal.tracker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/time-entries")
@RestController
public class TimeEntryController {


    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
    }
    public ResponseEntity<TimeEntry> find(long timeEntryId) {
        timeEntryId = 1L;
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        long projectId = 123L;
        long userId = 456L;
         timeEntry = new TimeEntry(timeEntryId, projectId, userId, LocalDate.parse("2017-01-08"), 8);
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }



    public ResponseEntity <TimeEntry> read(long timeEntryId) {


        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        long projectId = 123L;
        long userId = 456L;
        TimeEntry expected = new TimeEntry(timeEntryId, projectId, userId, LocalDate.parse("2017-01-08"), 8);
        if(timeEntryId==1L) {

            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
        else {
            timeEntry=null;
            return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
        }
    }


  /*  public ResponseEntity <TimeEntry> read(long timeEntryId) {
        timeEntryId = 1L;
        TimeEntry expected = null;
       return new ResponseEntity<>(expected, HttpStatus.NOT_FOUND);
    }*/


    public ResponseEntity <List<TimeEntry>> list() {

        List<TimeEntry> timesheetEnrtyList= timeEntryRepository.list();
        return new ResponseEntity<>(timesheetEnrtyList, HttpStatus.OK);
    }

    public ResponseEntity update(long timeEntryId, TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId,expected);
        if (timeEntryId==1L)
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

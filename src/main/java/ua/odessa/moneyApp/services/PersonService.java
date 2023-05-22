package ua.odessa.moneyApp.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.odessa.moneyApp.models.Recording;
import ua.odessa.moneyApp.repositories.PeopleRepository;
import ua.odessa.moneyApp.repositories.RecordingRepository;
import ua.odessa.moneyApp.security.PersonDetails;

import java.util.List;

@Service
public class PersonService {

    private final PeopleRepository peopleRepository;
    private final RecordingRepository recordingRepository;


    public PersonService(PeopleRepository peopleRepository, RecordingRepository recordingRepository) {
        this.peopleRepository = peopleRepository;
        this.recordingRepository = recordingRepository;
    }

    public PersonDetails getSessionPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails;
    }

    public int getBalance() {
        List<Recording> list = recordingRepository.findByPersonId(getSessionPerson().getPerson());
        int result = 0;

        for (Recording i : list) {
            if (i.getType().equals("income")) {
                result += i.getValue();
            } else if (i.getType().equals("outgo")) {
                result -= i.getValue();
            }
        }
        return result;
    }

    public int getIncome() {
        List<Recording> list = recordingRepository.findByPersonId(getSessionPerson().getPerson());
        int result = 0;
        for (Recording i : list) {
            if (i.getType().equals("income")) {
                result += i.getValue();
            }
        }
        return result;
    }

    public int getOutgo() {
        List<Recording> list = recordingRepository.findByPersonId(getSessionPerson().getPerson());
        int result = 0;
        for (Recording i : list) {
            if (i.getType().equals("outgo")) {
                result += i.getValue();
            }
        }
        return result;
    }
}

package ua.odessa.moneyApp.services;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.models.Recording;
import ua.odessa.moneyApp.repositories.RecordingRepository;
import ua.odessa.moneyApp.security.PersonDetails;

@Service
@Transactional(readOnly = true)
public class RecordingService {
	
	private final RecordingRepository recordingRepository;
	private SessionFactory sessionFactory;

	@Autowired
	public RecordingService(RecordingRepository recordRepository) {
		super();
		this.recordingRepository = recordRepository;
	}
	
	public List<Recording> findAll() {
		return recordingRepository.findAll();
	}

	public int[] findAllIncomeByMonthIn() {
		List<Recording> list = recordingRepository.findAll();
		int[] array = new int[12];

		for (Recording i : list) {
			if (i.getMonth().equals("january") && i.getType().equals("income")) {
				array[0] += i.getValue();
			}else if (i.getMonth().equals("february") && i.getType().equals("income")) {
				array[1] += i.getValue();
			}else if (i.getMonth().equals("march") && i.getType().equals("income")) {
				array[2] += i.getValue();
			}else if (i.getMonth().equals("april") && i.getType().equals("income")) {
				array[3] += i.getValue();
			}else if (i.getMonth().equals("may") && i.getType().equals("income")) {
				array[4] += i.getValue();
			}else if (i.getMonth().equals("june") && i.getType().equals("income")) {
				array[5] += i.getValue();
			}else if (i.getMonth().equals("july") && i.getType().equals("income")) {
				array[6] += i.getValue();
			}else if (i.getMonth().equals("august") && i.getType().equals("income")) {
				array[7] += i.getValue();
			}else if (i.getMonth().equals("september") && i.getType().equals("income")) {
				array[8] += i.getValue();
			}else if (i.getMonth().equals("october") && i.getType().equals("income")) {
				array[9] += i.getValue();
			}else if (i.getMonth().equals("november") && i.getType().equals("income")) {
				array[10] += i.getValue();
			}else if (i.getMonth().equals("december") && i.getType().equals("income")) {
				array[11] += i.getValue();
			}
		}
		return array;
	}

	public int[] findAllIncomeByMonthOut() {
		List<Recording> list = recordingRepository.findAll();
		int[] array = new int[12];

		for (Recording i : list) {
			if (i.getMonth().equals("january") && i.getType().equals("outgo")) {
				array[0] += i.getValue();
			}else if (i.getMonth().equals("february") && i.getType().equals("outgo")) {
				array[1] += i.getValue();
			}else if (i.getMonth().equals("march") && i.getType().equals("outgo")) {
				array[2] += i.getValue();
			}else if (i.getMonth().equals("april") && i.getType().equals("outgo")) {
				array[3] += i.getValue();
			}else if (i.getMonth().equals("may") && i.getType().equals("outgo")) {
				array[4] += i.getValue();
			}else if (i.getMonth().equals("june") && i.getType().equals("outgo")) {
				array[5] += i.getValue();
			}else if (i.getMonth().equals("july") && i.getType().equals("outgo")) {
				array[6] += i.getValue();
			}else if (i.getMonth().equals("august") && i.getType().equals("outgo")) {
				array[7] += i.getValue();
			}else if (i.getMonth().equals("september") && i.getType().equals("outgo")) {
				array[8] += i.getValue();
			}else if (i.getMonth().equals("october") && i.getType().equals("outgo")) {
				array[9] += i.getValue();
			}else if (i.getMonth().equals("november") && i.getType().equals("outgo")) {
				array[10] += i.getValue();
			}else if (i.getMonth().equals("december") && i.getType().equals("outgo")) {
				array[11] += i.getValue();
			}
		}
		return array;
	}


	
	public Recording findById(int id) {
		return recordingRepository.findById(id).orElse(null);
	}

	public List<Recording> findByPerson() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
		return recordingRepository.findByPersonId(personDetails.getPerson());
	}
	
	@Transactional
	public void save(Recording record, String type) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Person person = ((PersonDetails) authentication.getPrincipal()).getPerson();
		record.setPersonId(person);
		record.setType(type);

		if (record.getValue() < 0) {
			record.setType("outgo");
		} else {
			record.setType("income");
		}

		record.setValue(Math.abs(record.getValue()));

		recordingRepository.save(record);
	}
	
	@Transactional
	public void delete(int id) {
		recordingRepository.deleteById(id);
	}
	
//	public int getBalance() {
//		List<Recording> incomeRecords = recordRepository.findByType("income");
//		List<Recording> expenceRecords = recordRepository.findByType("expence");
//		int incomeSum = 0;
//		int expenceSum = 0;
//
//		for (Recording record : incomeRecords) {
//			incomeSum += record.getAmount();
//		}
//
//		for (Recording record : expenceRecords) {
//			expenceSum += record.getAmount();
//		}
//
//		return incomeSum-expenceSum;
//	}
}

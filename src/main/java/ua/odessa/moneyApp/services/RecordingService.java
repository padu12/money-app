package ua.odessa.moneyApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.odessa.moneyApp.models.Recording;
import ua.odessa.moneyApp.repositories.RecordingRepository;
import ua.odessa.moneyApp.security.PersonDetails;

@Service
@Transactional(readOnly = true)
public class RecordingService {
	
	private final RecordingRepository recordingRepository;

	@Autowired
	public RecordingService(RecordingRepository recordRepository) {
		super();
		this.recordingRepository = recordRepository;
	}
	
	public List<Recording> findAll() {
		return recordingRepository.findAll();
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
	public void save(Recording record) {
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

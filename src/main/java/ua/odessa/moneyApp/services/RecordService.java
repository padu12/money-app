package ua.odessa.moneyApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.odessa.moneyApp.models.Recording;
import ua.odessa.moneyApp.repositories.RecordRepository;

@Service
@Transactional(readOnly = true)
public class  RecordService {
	
	private final RecordRepository recordRepository;

	@Autowired
	public RecordService(RecordRepository recordRepository) {
		super();
		this.recordRepository = recordRepository;
	}
	
	public List<Recording> findAll() {
		return recordRepository.findAll();
	}
	
	public Recording findById(int id) {
		return recordRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public void save(Recording record) {
		recordRepository.save(record);
	}
	
	@Transactional
	public void delete(int id) {
		recordRepository.deleteById(id);
	}
	
	public int getBalance() {
		List<Recording> incomeRecords = recordRepository.findByType("income");
		List<Recording> expenceRecords = recordRepository.findByType("expence");
		int incomeSum = 0;
		int expenceSum = 0;
		
		for (Recording record : incomeRecords) {
			incomeSum += record.getAmount();
		}
		
		for (Recording record : expenceRecords) {
			expenceSum += record.getAmount();
		}
		
		return incomeSum-expenceSum;
	}
}

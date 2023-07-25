package com.trasaction.management.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trasaction.management.entity.Transaction;
import com.trasaction.management.model.CustomerDAO;
import com.trasaction.management.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public void save(Transaction transaction) {
		computeRewards(transaction);
		transactionRepository.save(transaction);
	}

	/**
	 * Computes rewards points for the transaction
	 */
	private void computeRewards(Transaction transaction) {

		double amount = transaction.getAmount();

		if (amount <= 100 && amount >= 50) {
			transaction.setRewards((int) (amount - 50) * 1);
		} else if (amount > 100) {
			transaction.setRewards((int) (amount - 100) * 2 + 50 * 1);
		}
	}

	public void saveAll(List<Transaction> transactionList) {

		for (Transaction transaction : transactionList) {
			computeRewards(transaction);
		}

		transactionRepository.saveAll(transactionList);
	}

	public List<CustomerDAO> getRewards(LocalDate startDate, LocalDate endDate) {
		List<Integer[]> theList = transactionRepository.getTransactions(startDate, endDate);

		return convertToModel(theList);
	}

	public List<CustomerDAO> getRewards(LocalDate startDate, LocalDate endDate, int customerId) {
		List<Integer[]> theList = transactionRepository.getTransactions(startDate, endDate, customerId);

		return convertToModel(theList);
	}

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	public List<Transaction> getAllTransactions(int customerId) {
		return transactionRepository.findByCustomerId(customerId);
	}

	private List<CustomerDAO> convertToModel(List<Integer[]> theList) {
		return theList.stream().map(p -> {
			CustomerDAO customerTransactionModel = new CustomerDAO();
			customerTransactionModel.setCustomerId((int) p[0]);
			customerTransactionModel.setRewards((int) p[1]);

			return customerTransactionModel;
		}).collect(Collectors.toList());

	}
}

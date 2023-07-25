package com.trasaction.management.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.trasaction.management.entity.Transaction;
import com.trasaction.management.model.CustomerDAO;
import com.trasaction.management.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	// Gets all rewards of all customers for given start and end date
	@GetMapping("/rewards")
	public List<CustomerDAO> getRewards(@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate) {
		
		LocalDate dateStart = LocalDate.parse(startDate);
		
		LocalDate dateEnd = LocalDate.parse(endDate);
		
		return transactionService.getRewards(dateStart, dateEnd);
	}

	// Gets rewards for the given customer id and given start and end date
	@GetMapping("/rewards/{customerId}")
	public List<CustomerDAO> getRewards(@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate, @PathVariable(name = "customerId") int customerId) {
		
		LocalDate dateStart = LocalDate.parse(startDate);
		
		LocalDate dateEnd = LocalDate.parse(endDate);
		
		return transactionService.getRewards(dateStart, dateEnd, customerId);
	}

	// Gets all transactions
	@GetMapping("/all")
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();
	}

	// Gets all transactions for the given customer id
	@GetMapping("/{customerId}")
	public List<Transaction> getAllTransactions(@PathVariable(name = "customerId") int customerId) {
		return transactionService.getAllTransactions(customerId);
	}
}

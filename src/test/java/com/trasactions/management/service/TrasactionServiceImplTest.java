package com.trasactions.management.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trasaction.management.entity.Transaction;
import com.trasaction.management.model.CustomerDAO;
import com.trasaction.management.repository.TransactionRepository;
import com.trasaction.management.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TrasactionServiceImplTest {
	
	@InjectMocks 
	public TransactionService trasactionService;
	
	@Mock
	public TransactionRepository transactionRepository;
	
	
	@Test
	public void testSave() {
		
		Transaction transaction = new Transaction();
		transaction.setCustomerId(1);
		transaction.setAmount(450);
		
		 String date = "2023-05-03";
		 
		transaction.setDate(LocalDate.parse(date));
		

		trasactionService.save(transaction);
		verify(transactionRepository).save(any(Transaction.class));
		
		
		transaction.setAmount(95);
		trasactionService.save(transaction);
		verify(transactionRepository, times(2)).save(any(Transaction.class));
	}
	
	@Test
	public void testSaveAll() {
		
		Transaction transaction1 = new Transaction();
		transaction1.setCustomerId(1);
		transaction1.setAmount(450);
		 String date = "2023-05-02";
		 
		transaction1.setDate(LocalDate.parse(date));
		
		Transaction transaction2 = new Transaction();
		transaction2.setCustomerId(2);
		transaction2.setAmount(88);
		 date = "2023-05-03";
		 
		transaction2.setDate(LocalDate.parse(date));
		

		List<Transaction> theList = List.of(transaction1, transaction2);
		trasactionService.saveAll(theList);
		verify(transactionRepository).saveAll(any(List.class));
	}
	
	@Test
	public void testgetAllTransactions() {
		
		Integer data[] = new Integer[] {200,300};
		List<Integer[]> theList = new ArrayList<>();
		theList.add(data);
		
		LocalDate startDate = LocalDate.parse("2022-09-01");
		LocalDate endDate =  LocalDate.parse("2023-03-01");
		when(transactionRepository.getTransactions(startDate, endDate)).thenReturn(theList);
		
		List<CustomerDAO> result = trasactionService.getRewards(startDate, endDate);
		
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void testgetAllTransactionsOfCustomer() {
		
		Integer data[] = new Integer[] {200,300};
		List<Integer[]> theList = new ArrayList<>();
		theList.add(data);
		
		LocalDate startDate = LocalDate.parse("2022-09-01");
		LocalDate endDate =  LocalDate.parse("2023-03-01");
		when(transactionRepository.getTransactions(startDate, endDate, 200)).thenReturn(theList);
		
		List<CustomerDAO> result = trasactionService.getRewards(startDate, endDate,200);
		
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void getAllTransactions() {
		
		Transaction transaction = new Transaction();
		transaction.setCustomerId(1);
		transaction.setAmount(450);
		String date = "2023-05-03";
		 
		transaction.setDate(LocalDate.parse(date));
		when(transactionRepository.findAll()).thenReturn(List.of(transaction));
		
		
		List<Transaction> result = trasactionService.getAllTransactions();
		assertTrue(result.size() > 0);

	}
	
	@Test
	public void getAllTransactionsByCustomerId() {
		
		Transaction transaction = new Transaction();
		transaction.setCustomerId(1);
		transaction.setAmount(450);
		String date = "2023-05-03";
		 
		transaction.setDate(LocalDate.parse(date));
		when(transactionRepository.findByCustomerId(1)).thenReturn(List.of(transaction));
		
		
		List<Transaction> result = trasactionService.getAllTransactions(1);
		assertTrue(result.size() > 0);

	}
}

package com.trasaction.management.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.trasaction.management.entity.Transaction;
import com.trasaction.management.service.TransactionService;

@Configuration
public class Config {

	@Autowired
	private TransactionService transactionService;

	@Bean
	public void insertData() {
		BufferedReader br = null;
		List<Transaction> transactionList = new ArrayList<>();
		try {
			File dataFile = ResourceUtils.getFile("classpath:transactions.txt");
			br = new BufferedReader(new FileReader(dataFile));
			String data = null;
			while ((data = br.readLine()) != null) {
				String[] entry = data.split(",");

				Transaction transaction = new Transaction();
				transaction.setId(Integer.valueOf(entry[0]));
				transaction.setCustomerId(Integer.valueOf(entry[1]));
				transaction.setAmount(Double.valueOf(entry[2]));

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(entry[3]);

				transaction.setDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

				transactionList.add(transaction);
			}

			transactionService.saveAll(transactionList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

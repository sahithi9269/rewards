package com.trasaction.management.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	@JsonIgnore
	private int id;

	private int customerId;

	private double amount;

	private LocalDate date;

	private int rewards;
}

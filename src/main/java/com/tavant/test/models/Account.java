package com.tavant.test.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity(name = "Account")
@Table(name="accounts")
@IdClass(AccountId.class)
public class Account {
	
	
	@Id
	@NotNull(message = "AccountId cannot be NULL")
	private String accId ; 
	
	private String accountType;
	private String lastName;
	private String firstName;
	private Float address;



	
	
	
}

package com.example.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountResponse {

	private Long id;
	private String accountNumber;
	private String accountHolderName;
	private Double accountBalance;
}

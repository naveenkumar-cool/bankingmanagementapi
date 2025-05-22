package com.example.service;

import java.util.List;

import com.example.dto.AccountDto;
import com.example.response.AccountResponse;

public interface AccountService {

	public AccountResponse createAccount(AccountDto dto);
	
	public AccountResponse getAccountDetailsById(Long id);
	
	public List<AccountResponse> getAllAccountDetails();
	
	public AccountResponse depositAmount(Long id, Double amount);
	
	public AccountResponse withdrawAmount(Long id, Double amount);
	
	public void closeAccount(Long id);
}

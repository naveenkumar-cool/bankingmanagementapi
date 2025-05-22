package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.AccountDto;
import com.example.response.AccountResponse;
import com.example.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	public void setService(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/create")
	public AccountResponse createAccount(@RequestBody AccountDto dto) {
		return accountService.createAccount(dto);
	}

	@GetMapping("/{id}")
	public AccountResponse getAccountById(@PathVariable Long id) {
		return accountService.getAccountDetailsById(id);
	}

	@GetMapping("/getallaccounts")
	public List<AccountResponse> getAllAccountDetails() {
		return accountService.getAllAccountDetails();
	}

	@PutMapping("/deposit/{id}/{amount}")
	public AccountResponse depositAccount(@PathVariable Long id, @PathVariable Double amount) {
		return accountService.depositAmount(id, amount);
	}

	@PutMapping("/withdraw/{id}/{amount}")
	public AccountResponse withdrawAmount(@PathVariable Long id, @PathVariable Double amount) {
		return accountService.withdrawAmount(id, amount);
	}
	@DeleteMapping("/delete/{id}")
	public void closeAccount(@PathVariable Long id)
	{
		accountService.closeAccount(id);
	}
	
}

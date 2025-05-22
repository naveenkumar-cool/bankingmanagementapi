package com.example.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dto.AccountDto;
import com.example.entity.Account;
import com.example.repo.AccountRepository;
import com.example.response.AccountResponse;
import com.example.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AccountResponse createAccount(AccountDto dto) {
		Account account = new Account();
		account.setAccountHolderName(dto.getAccountHolderName());
		account.setAccountBalance(dto.getAccountBalance());
		accountRepository.save(account);
		AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
		return accountResponse;
	}

	@Override
	public AccountResponse getAccountDetailsById(Long id) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account is not found with " + id));
		AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
		return accountResponse;
	}

	@Override
	public List<AccountResponse> getAllAccountDetails() {
		List<Account> accounts = accountRepository.findAll();
		List<AccountResponse> accountResponses = accounts.stream()
				.map(account -> mapper.map(account, AccountResponse.class)).collect(Collectors.toList());
		return accountResponses;
	}

	@Override
	public AccountResponse depositAmount(Long id, Double amount) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account is not present " + id));
		account.setAccountBalance(account.getAccountBalance() + amount);

		Account updatedAccount = accountRepository.save(account);

		return mapper.map(updatedAccount, AccountResponse.class);
	}

	@Override
	public AccountResponse withdrawAmount(Long id, Double amount) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account is not present " + id));

		if (account.getAccountBalance() < amount) {
			throw new RuntimeException("Insufficient balance for account " + id);
		}

		account.setAccountBalance(account.getAccountBalance() - amount);

		Account updatedAccount = accountRepository.save(account);

		return mapper.map(updatedAccount, AccountResponse.class);
	}

	@Override
	public void closeAccount(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account is not present" + id));
		accountRepository.deleteById(id);
	}

}

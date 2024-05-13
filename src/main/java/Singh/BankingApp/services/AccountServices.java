package Singh.BankingApp.services;

import java.util.List;

import Singh.BankingApp.dao.AccountDto;

public interface AccountServices {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}

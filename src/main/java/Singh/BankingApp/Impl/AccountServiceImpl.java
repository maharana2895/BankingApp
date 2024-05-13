package Singh.BankingApp.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Singh.BankingApp.Mapper.AccountMapper;
import Singh.BankingApp.dao.AccountDto;
import Singh.BankingApp.entities.Account;
import Singh.BankingApp.repository.AccountRepository;
import Singh.BankingApp.services.AccountServices;


@Service
public class AccountServiceImpl implements AccountServices{
	
	@Autowired
	private AccountRepository accountRepository;

	public AccountServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does not exists"));
		double updatedBalance = account.getBalance()+ amount;
		account.setBalance(updatedBalance);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does not exists"));
		if(account.getBalance() < amount) {
			throw new RuntimeException("insuffient balance");
		}
		double updatedBalance = account.getBalance() - amount;
		account.setBalance(updatedBalance);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);

	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> allAccounts = accountRepository.findAll();
		List<AccountDto> allAccountsDto = allAccounts.stream().map(acc -> AccountMapper.mapToAccountDto(acc)).collect(Collectors.toList());
		return allAccountsDto;
	}

	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does not exists"));
		accountRepository.deleteById(id);
		
	}

}

package Singh.BankingApp.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Singh.BankingApp.dao.AccountDto;
import Singh.BankingApp.entities.Account;
import Singh.BankingApp.services.AccountServices;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountServices accountService;

	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountController(AccountServices accountService) {
		super();
		this.accountService = accountService;
	}
	
	//Add account rest api
	@PostMapping("/create")
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
		
	}
	// Get Account rest api
	
	@GetMapping("/{id}")
	public  ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
		
	}
	
	// Deposit amount rest api
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @RequestBody Map<String,Double> request){
		AccountDto accountDto = accountService.deposit(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
		
	}
	
	// Withdraw amout rest api
	
	@PutMapping("{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request){
		AccountDto accountDto = accountService.withdraw(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
	}
	
	// Get All account rest api
	@GetMapping("/all")
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> allAccountsDto = accountService.getAllAccounts();
		return ResponseEntity.ok(allAccountsDto);
		}
	
	// Delete account rest api
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted successfully!");
		
	}
	
}

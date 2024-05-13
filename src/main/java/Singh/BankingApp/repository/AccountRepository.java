package Singh.BankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Singh.BankingApp.entities.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{

}

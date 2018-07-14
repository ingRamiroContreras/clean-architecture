package banking.accounts.domain.repository;

import banking.accounts.domain.entity.BankAccount;

public interface BankAccountDao {

	public BankAccount create(BankAccount bankAccount) throws Exception;
	
}

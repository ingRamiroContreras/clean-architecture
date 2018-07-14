package banking.accounts.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import banking.accounts.application.dto.BankAccountDto;
import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountDao;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerDao;

@Service()
@Transactional
public class BankAccountService {

	@Autowired
	BankAccountDao bankAccountDao;
	
	@Autowired
	CustomerDao customerDao;
	
	public ResponseEntity<Object> saveAccount(BankAccountDto bankAccountDto) throws Exception {
		BankAccount bankAccount = new BankAccount();		
		bankAccount.setNumber(bankAccountDto.getNumber());
		bankAccount.setBalance(bankAccountDto.getBalance());
		bankAccount.setIsLocked(false);
		//Buscamos al cliente
		Customer customer = customerDao.findById(Long.parseLong(bankAccountDto.getCustomer().getId()));
		if ( customer == null) {
			return ResponseEntity.notFound().build();			
		}
		bankAccount.setCustomer(customer);
		bankAccountDao.create(bankAccount);
		return ResponseEntity.ok().body(bankAccount);	
	}

}

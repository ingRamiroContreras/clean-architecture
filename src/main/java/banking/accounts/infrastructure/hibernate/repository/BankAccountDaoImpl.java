package banking.accounts.infrastructure.hibernate.repository;

import org.springframework.stereotype.Repository;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountDao;
import banking.common.infrastructure.hibernate.repository.AbstractDao;

@Repository("BankAccountDao")
public class BankAccountDaoImpl extends AbstractDao<Long, BankAccount> implements BankAccountDao {

	@Override
	public BankAccount create(BankAccount bankAccount) throws Exception {
		saveOrUpdate(bankAccount);
        return bankAccount;
	}
	
}

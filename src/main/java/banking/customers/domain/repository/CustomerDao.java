package banking.customers.domain.repository;

import banking.customers.domain.entity.Customer;

public interface CustomerDao {

	public Customer create(Customer Customer) throws Exception;

	public Customer findById(Long id) throws Exception;
	
}

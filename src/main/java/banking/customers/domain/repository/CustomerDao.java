package banking.customers.domain.repository;

import java.util.Map;

import banking.customers.domain.entity.Customer;


public interface CustomerDao {

	public Customer create(Customer Customer) throws Exception;

	public Customer findById(Long id) throws Exception;
	
	public Map<String, Object> findAll(java.util.Optional<String> pag, java.util.Optional<String> pagLength) throws Exception;
	
}

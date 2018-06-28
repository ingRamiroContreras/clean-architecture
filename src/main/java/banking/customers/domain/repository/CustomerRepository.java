package banking.customers.domain.repository;

import java.util.List;

import banking.customers.domain.entity.Customer;

public interface CustomerRepository {
	Customer findById(long id) throws Exception;
	Customer findByName(String firstName, String lastName) throws Exception;
	List<Customer> findAll(String pag, String pagLenth) throws Exception;	
	void save(Customer customer);
	void saveOrUpdate(Customer customer);
}


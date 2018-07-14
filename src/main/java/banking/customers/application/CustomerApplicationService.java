package banking.customers.application;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import banking.common.application.Notification;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerDao;

@Service()
@Transactional
public class CustomerApplicationService {

	@Autowired
	CustomerDao customerDao;
	
	public ResponseEntity<Object> saveCustomer(CustomerDto customerDto) throws Exception {
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setActive(true);
		customerDao.create(customer);
		return ResponseEntity.ok().body(customer);	
	}

	public ResponseEntity<Object> getCustomerId(long customerId) throws Exception {
		Customer customer = customerDao.findById(Long.valueOf(customerId));		
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);
	}
	
	public ResponseEntity<Object> getCustomerAll(Optional<String> pag, Optional<String> pagLength) throws Exception {
		Map<String, Object> customer = customerDao.findAll(pag, pagLength);		
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);
	}


}

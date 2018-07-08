package banking.customers.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.accounts.domain.entity.Customer;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.customers.domain.RequestCustomerDto;
import banking.customers.domain.repository.CustomerRepository;

@Service()
public class CustomerApplicationService {
	
	@Autowired
	private CustomerRepository customerRepository;

	
	@Transactional
	public void saveCustomer(RequestCustomerDto requestCustomerDto) throws Exception {
		Notification notification = this.validation(requestCustomerDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        Customer customer = this.customerRepository.findById(requestCustomerDto.getId());
		if ( customer == null ) {
			customer = new Customer();
			customer.setFirstName(requestCustomerDto.getFirstName());
			customer.setLastName(requestCustomerDto.getLastName());
			customer.setActive(requestCustomerDto.isActive());
			this.customerRepository.save(customer);
		} else {
			customer.setFirstName(requestCustomerDto.getFirstName());
			customer.setLastName(requestCustomerDto.getLastName());
			customer.setActive(requestCustomerDto.isActive());
			this.customerRepository.save(customer);
		}
	}
	
	private Notification validation(RequestCustomerDto requestCustomerDto) {
		Notification notification = new Notification();
		if (requestCustomerDto == null || requestCustomerDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
	
	
}

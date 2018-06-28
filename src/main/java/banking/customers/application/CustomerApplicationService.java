package banking.customers.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.application.Notification;
import banking.common.application.enumeration.RequestBodyType;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.RequestCustomerDto;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerRepository;
import banking.transactions.application.dto.RequestBankTransferDto;
import banking.transactions.domain.service.TransferDomainService;

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
        Customer customer = this.customerRepository.findById(requestCustomerDto.getCustomer().getId());
		if ( customer != null ) {
			this.customerRepository.save(requestCustomerDto.getCustomer());	
		} else {
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

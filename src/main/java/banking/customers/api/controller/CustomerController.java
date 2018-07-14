package banking.customers.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import banking.common.api.controller.ResponseHandler;
import banking.customers.application.CustomerApplicationService;
import banking.customers.application.dto.CustomerDto;

@RestController
@RequestMapping("api/customers/")
public class CustomerController {
	
	@Autowired
	CustomerApplicationService customerApplicationService;
	
	
	@Autowired
	ResponseHandler responseHandler;		
			
				
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST, path = "save", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> saveCustomer(@Valid @RequestBody CustomerDto customerDto) throws Exception {
		try {
		return customerApplicationService.saveCustomer(customerDto);  			
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
	}
	
	@CrossOrigin(origins = "*")			
	@RequestMapping(method = RequestMethod.GET, value = "/customer/{customerId}")
	public ResponseEntity<Object> getCustomerId(@PathVariable(value="customerId") long customerId){
	try {				
			return customerApplicationService.getCustomerId(customerId);				
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
	}
	
	
	@CrossOrigin(origins = "*")			
	@RequestMapping(method = RequestMethod.GET, value = "/customerAll/pag/{pag}/pagLength/{pagLength}")
	public ResponseEntity<Object> getCustomerAll(@PathVariable(value="pag") Optional<String> pag, @PathVariable(value="pagLength") Optional<String> pagLength){
	try {	
			return customerApplicationService.getCustomerAll(pag, pagLength);				
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
	}

	
}

package banking.customers.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import banking.common.api.controller.ResponseHandler;
import banking.customers.application.CustomerApplicationService;
import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.RequestCustomerDto;
import banking.transactions.application.TransactionApplicationService;
import banking.transactions.application.dto.RequestBankTransferDto;

@RestController
@RequestMapping("api/customers/")
public class CustomerController {

	@Autowired
	ResponseHandler responseHandler;
	
	@Autowired
	CustomerApplicationService customerApplicationService;

	@RequestMapping(method = RequestMethod.POST, path = "transfers", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> saveCustomer(@RequestBody RequestCustomerDto requestCustomerDto) throws Exception {
		try {
			customerApplicationService.saveCustomer(requestCustomerDto);
			return this.responseHandler.getOkCommandResponse("Save customer!");
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
    }
	
}

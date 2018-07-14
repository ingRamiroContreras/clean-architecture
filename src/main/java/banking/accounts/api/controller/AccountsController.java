package banking.accounts.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import banking.accounts.application.BankAccountService;
import banking.accounts.application.dto.BankAccountDto;
import banking.common.api.controller.ResponseHandler;

@RestController
@RequestMapping("api/accounts/")
public class AccountsController {
	
	@Autowired
	BankAccountService bankAccountService;
	
	
	@Autowired
	ResponseHandler responseHandler;		
			
				
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST, path = "save", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> saveCustomer(@Valid @RequestBody BankAccountDto bankAccountDto) throws Exception {
		try {
		return bankAccountService.saveAccount(bankAccountDto);  			
		} catch(IllegalArgumentException ex) {
			return this.responseHandler.getAppCustomErrorResponse(ex.getMessage());
		} catch(Exception ex) {
			return this.responseHandler.getAppExceptionResponse();
		}
	}	
	
}

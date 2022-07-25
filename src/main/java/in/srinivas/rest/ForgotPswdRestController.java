package in.srinivas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.srinivas.service.UserManagementServiceImpl;

@RestController
public class ForgotPswdRestController {

	@Autowired
	private UserManagementServiceImpl uService;

	@GetMapping(value = "/forgot/{emai}")
	public ResponseEntity<String> forgotPswd(@PathVariable("email") String emailId) {
		String forgotPwd = uService.forgotPwd(emailId);
		return new ResponseEntity<>(forgotPwd, HttpStatus.OK);
	}

}

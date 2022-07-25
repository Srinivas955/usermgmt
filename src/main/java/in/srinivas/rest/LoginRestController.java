package in.srinivas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.srinivas.binding.LoginForm;
import in.srinivas.service.UserManagementServiceImpl;

@RestController
public class LoginRestController {

	@Autowired
	private UserManagementServiceImpl uService;

	@PostMapping(value = "/login")
	public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
		String loginCheck = uService.loginCheck(loginForm);
		return new ResponseEntity<String>(loginCheck, HttpStatus.OK);
	}

}

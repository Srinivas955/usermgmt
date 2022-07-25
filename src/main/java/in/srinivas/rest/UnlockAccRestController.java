package in.srinivas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.srinivas.binding.UnlockAccForm;
import in.srinivas.service.UserManagementServiceImpl;

@RestController
public class UnlockAccRestController {

	@Autowired
	private UserManagementServiceImpl uService;

	@PostMapping(value = "/unlock")
	public ResponseEntity<String> unlockAcc(@RequestBody UnlockAccForm unlock) {
		String unlockAcc = uService.unlockAcc(unlock);
		return new ResponseEntity<>(unlockAcc, HttpStatus.OK);

	}

}

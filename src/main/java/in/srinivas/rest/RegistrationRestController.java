package in.srinivas.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.srinivas.binding.UserRegistrationForm;
import in.srinivas.service.UserManagementServiceImpl;

@RestController
public class RegistrationRestController {

	@Autowired
	private UserManagementServiceImpl uService;

	@GetMapping(value = "/emailcheck/{emailId}")
	public ResponseEntity<String> emailCheck(@PathVariable String emailId) {
		String emailCheck = uService.emailCheck(emailId);
		return new ResponseEntity<String>(emailCheck, HttpStatus.OK);
	}

	@GetMapping(value = "/countries")
	public ResponseEntity<Map<Integer, String>> getCountries() {
		Map<Integer, String> loadCountries = uService.loadCountries();
		return new ResponseEntity<>(loadCountries, HttpStatus.OK);
	}

	@GetMapping(value = "/states/{countryId}")
	public ResponseEntity<Map<Integer, String>> getStates(@PathVariable Integer countryId) {
		Map<Integer, String> loadStates = uService.loadStates(countryId);
		return new ResponseEntity<>(loadStates, HttpStatus.OK);
	}

	@GetMapping(value = "/cities/{stateId}")
	public ResponseEntity<Map<Integer, String>> getCities(@PathVariable Integer stateId) {
		Map<Integer, String> loadCities = uService.loadCities(stateId);
		return new ResponseEntity<>(loadCities, HttpStatus.OK);
	}

	@PostMapping(value = "/saveuser")
	public ResponseEntity<String> saveUser(@RequestBody UserRegistrationForm userform) {
		String saveUser = uService.saveUser(userform);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}

}

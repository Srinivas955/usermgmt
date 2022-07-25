package in.srinivas.service;

import java.util.Map;

import in.srinivas.binding.LoginForm;
import in.srinivas.binding.UnlockAccForm;
import in.srinivas.binding.UserRegistrationForm;

public interface UserManagementService {
	
	// login screen related methods
		public String loginCheck(LoginForm loginForm);

		// Registration screen related methods
		public String emailCheck(String emailId);

		public Map<Integer, String> loadCountries();

		public Map<Integer, String> loadStates(Integer countryId);

		public Map<Integer, String> loadCities(Integer stateId);
		
		public String saveUser(UserRegistrationForm userForm);

		// unlock account screen related methods
		public String unlockAcc(UnlockAccForm unlockAccForm);

		// forgot pwd screen related methods
		public String forgotPwd(String emailId);

}

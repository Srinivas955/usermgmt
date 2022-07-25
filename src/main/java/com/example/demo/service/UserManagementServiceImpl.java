package com.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.LoginForm;
import com.example.demo.binding.UnlockAccForm;
import com.example.demo.binding.UserRegistrationForm;
import com.example.demo.constants.AppConstants;
import com.example.demo.entity.CityMaster;
import com.example.demo.entity.CountryMaster;
import com.example.demo.entity.StateMaster;
import com.example.demo.entity.UserDtails;
import com.example.demo.props.AppProperties;
import com.example.demo.repository.CityMasterRepository;
import com.example.demo.repository.CountryMasterRepository;
import com.example.demo.repository.StateMasterRepository;
import com.example.demo.repository.UserDtailsRepo;
import com.example.demo.utils.EmailUtils;

@Service
public class UserManagementServiceImpl implements UserManagementService{
	
	@Autowired
	private UserDtailsRepo userRepo;
	
	@Autowired
	private CountryMasterRepository countryRepo;
	
	@Autowired
	private StateMasterRepository stateRepo;
	
	@Autowired
	private CityMasterRepository cityRepo;
	
	@Autowired
	private EmailUtils eUtils;
	
	@Autowired
	private AppProperties aProp;

	@Override
	public String loginCheck(LoginForm loginForm) {
		UserDtails userAcc = userRepo.findByEmailAndPswd(loginForm.getEmail(), loginForm.getPswd());
		Map<String, String> messages = aProp.getMessages();
		if(userAcc==null) {
			return messages.get(AppConstants.INVALID_CRED);
		}
		if(userAcc.getAccStatus().equals("Locked")) {
			return messages.get(AppConstants.ACC_LOCKED);
		}
		return messages.get(AppConstants.LOGIN_SUCCESS);
	}

	@Override
	public String emailCheck(String emailId) {
		UserDtails userAcc = userRepo.findByEmail(emailId);
		Map<String, String> messages = aProp.getMessages();
		if(userAcc==null) {
			return messages.get(AppConstants.NEW_EMAIL);
		}		
		return messages.get(AppConstants.DUPLICATE);
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMaster> countries = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		countries.forEach(country ->{
			countryMap.put(country.getCountryId(), country.getCountryName());
			});	
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {
		 List<StateMaster> states = stateRepo.findByCountryId(countryId);
		 Map<Integer, String> statesMap = new HashMap<>();
		 states.forEach(state -> {
			 statesMap.put(state.getStateId(), state.getStateName());
		 });
		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		List<CityMaster> cities = cityRepo.findByStateId(stateId);
		Map<Integer, String> citiesMap = new HashMap<>();
		cities.forEach(city ->{
			citiesMap.put(city.getCityId(), city.getCityName());
		});
		return citiesMap;
	}

	@Override
	public String saveUser(UserRegistrationForm userForm) {
		
		userForm.setPswd(generateRandomPassword(6));
		UserDtails entity = new UserDtails();
		entity.setAccStatus("Locked");
		BeanUtils.copyProperties(userForm, entity);		
		userRepo.save(entity);
		
		String subject = "Unlock Your Account";
		String emailBody = readUnlockAccEmailBody(userForm);
		eUtils.mailsender(userForm.getEmail(), subject, emailBody);
		Map<String, String> messages = aProp.getMessages();
		return messages.get(AppConstants.REG_SUCCESS);
	}

	@Override
	public String unlockAcc(UnlockAccForm unlockAccForm) {
		UserDtails userAcc = userRepo.findByEmailAndPswd(unlockAccForm.getEmail(), unlockAccForm.getTemporaryPswd());
		Map<String, String> messages = aProp.getMessages();
		if(userAcc==null) {
			return messages.get(AppConstants.INVALID_ACC);
		}
			userAcc.setPswd(unlockAccForm.getNewPswd());
			userAcc.setAccStatus("Unlocked");
		return messages.get(AppConstants.ACCUNLOCKED);
	}

	@Override
	public String forgotPwd(String email) {
		UserDtails userAcc = userRepo.findByEmail(email);
		Map<String, String> messages = aProp.getMessages();
		if(userAcc==null) {
			return messages.get(AppConstants.INVALID_ACC);
		}
	String subject = "Recover your passwor Srinivas-IT";
	String recoveryEmailBody = recoveryEmailBody(userAcc);
	eUtils.mailsender(email, subject, recoveryEmailBody);
		return messages.get(AppConstants.PSWD_SENT);
	}
	
	
	
	
	 private String generateRandomPassword(int len)
	    {
	        // ASCII range – alphanumeric (0-9, a-z, A-Z)
	        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	 
	        SecureRandom random = new SecureRandom();
	        StringBuilder sb = new StringBuilder();
	 
	        // each iteration of the loop randomly chooses a character from the given
	        // ASCII range and appends it to the `StringBuilder` instance
	 
	        for (int i = 0; i < len; i++)
	        {
	            int randomIndex = random.nextInt(chars.length());
	            sb.append(chars.charAt(randomIndex));
	        }
	 
	        return sb.toString();
	    }
	 
	 private String readUnlockAccEmailBody(UserRegistrationForm userform) {
		 
		 String body = "";
		 StringBuffer buffer = new StringBuffer();
		 Path filePath = Paths.get("UNLOCK-ACCOUNT.txt");
		 
		 try(Stream<String> stream = Files.lines(filePath)){
			 stream.forEach(line ->{
				 buffer.append(line);
			 });
			body = buffer.toString();
			body = body.replace("{FNAME}", userform.getFirstName());
			body = body.replace("{FNAME}", userform.getLastName());
			body = body.replace("{TEMP-PWD}", userform.getPswd());
			body = body.replace("{EMAIL}", userform.getEmail());
		}catch (Exception e) {
			e.printStackTrace();
		}
		 return body;
	 }
	 
private String recoveryEmailBody(UserDtails user) {
		 
		 String body = "";
		 StringBuffer buffer = new StringBuffer();
		 Path filePath = Paths.get("RECOVERY-EMAIL.txt");
		 
		 try(Stream<String> stream = Files.lines(filePath)){
			 stream.forEach(line ->{
				 buffer.append(line);
			 });
			body = buffer.toString();
			body = body.replace("{FNAME}", user.getFirstName());
			body = body.replace("{LNAME}", user.getLastName());
			body = body.replace("{PWD}", user.getPswd());
		}catch (Exception e) {
			e.printStackTrace();
		}
		 return body;
	 }

}

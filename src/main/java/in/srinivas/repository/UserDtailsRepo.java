package in.srinivas.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.srinivas.entity.UserDtails;

public interface UserDtailsRepo extends JpaRepository<UserDtails, Serializable>  {
	public UserDtails findByEmailAndPswd(String email, String pswd);
	public UserDtails findByEmail(String email);
	
	

}

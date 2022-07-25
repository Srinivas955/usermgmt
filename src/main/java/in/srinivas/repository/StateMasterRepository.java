package in.srinivas.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.srinivas.entity.StateMaster;

public interface StateMasterRepository extends JpaRepository<StateMaster, Serializable> {
	public List<StateMaster>  findByCountryId(Integer countryId);

}

package in.srinivas.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.srinivas.entity.CityMaster;

public interface CityMasterRepository extends JpaRepository<CityMaster, Serializable> {

	public List<CityMaster> findByStateId(Integer stateId);
}

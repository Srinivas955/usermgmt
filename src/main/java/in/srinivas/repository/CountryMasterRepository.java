package in.srinivas.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.srinivas.entity.CountryMaster;

public interface CountryMasterRepository extends JpaRepository<CountryMaster, Serializable> {

}

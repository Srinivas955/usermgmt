package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CityMaster;

public interface CityMasterRepository extends JpaRepository<CityMaster, Serializable> {

	public List<CityMaster> findByStateId(Integer stateId);
}

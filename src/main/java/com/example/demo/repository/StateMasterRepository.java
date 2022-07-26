package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StateMaster;

public interface StateMasterRepository extends JpaRepository<StateMaster, Serializable> {
	public List<StateMaster>  findByCountryId(Integer countryId);

}

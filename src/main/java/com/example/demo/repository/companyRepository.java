package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.stockModel.Company;

@Repository
@Transactional
public interface companyRepository extends JpaRepository<Company, String> {
	
	
	/*
	 * @Query(value
	 * ="select * from company c where date >= startdate AND date<= enddate",
	 * nativeQuery = true ) public List<Company> customApi(Company company);
	 */
	 
	@Query(value ="select c FROM Company c WHERE c.stockPrice = (select MAX(c.stockPrice) FROM Company c)")
	public Company maxStock();
	
	@Query(value ="select c FROM Company c WHERE c.stockPrice = (select MIN(c.stockPrice) FROM Company c)")
	public Company minStock();
	
	@Query(value ="select AVG(c.stockPrice) FROM Company c")
	public double avgStock();
	
	

}

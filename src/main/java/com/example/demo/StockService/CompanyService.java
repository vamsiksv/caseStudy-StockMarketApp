package com.example.demo.StockService;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.From;

import org.springframework.data.jpa.repository.Query;

//import com.example.demo.exceptionhandler.CompanyCodeExistsException;
import com.example.demo.exceptionhandler.CompanyCodeNotExistException;
import com.example.demo.stockModel.Company;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

public interface CompanyService {
	
	public List<Company> getAllDetails();

	public Company register(Company company) ;
	
	public Company getInfo(String companyCode);
	
	public boolean deleteCompany(String companyCode) throws CompanyCodeNotExistException;
	
	public boolean addStockPrice(String companyCode , Company company);
	
	public boolean updateStockPrice(String companyCode , Company company);
	
	public Company updateInfo(Company company);
	
	public Company maxStock();

	public Company minStock();
	
	public double avgStock();
	
	
	/* public List<Company> customApi(Company company); */

}

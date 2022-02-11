package com.example.demo.StockService;
import java.util.List;

//import com.example.demo.exceptionhandler.CompanyCodeExistsException;
import com.example.demo.exceptionhandler.CompanyCodeNotExistException;
import com.example.demo.stockModel.Company;

public interface CompanyService {
	
	public List<Company> getAllDetails();

	public Company register(Company company) ;
	
	public Company getInfo(String companyCode);
	
	public boolean deleteCompany(String companyCode) throws CompanyCodeNotExistException;
	
	public boolean addStockPrice(String companyCode , Company company);
	
	public boolean updateStockPrice(String companyCode , Company company);

}

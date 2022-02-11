package com.example.demo.StockService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.demo.exceptionhandler.CompanyCodeExistsException;
import com.example.demo.exceptionhandler.CompanyCodeNotExistException;
import com.example.demo.repository.companyRepository;
import com.example.demo.stockModel.Company;

@Service

public class CompanyServiceImpl implements CompanyService{

 	@Autowired
	private companyRepository companyrepository;
	@Override
	public List<Company> getAllDetails() {
		
		List<Company> stockList = companyrepository.findAll();
		if(stockList!=null && stockList.size()>0)
		{
			return stockList;
		}
		return null;
	}

	@Override
	public Company register(Company company) {
		
		Optional<Company> optionalCompany= companyrepository.findById(company.getCompanyCode());
		
		/*
		 * if(optionalCompany.isPresent()) { throw new CompanyCodeExistsException(null);
		 * }
		 */
		
		if(company!=null && companyrepository.findById(company.getCompanyCode()).isEmpty())
		{
			return companyrepository.saveAndFlush(company);
		
		}
		
		return null;
	}

	@Override
	public Company getInfo(String companyCode) {
		
		if(companyCode!=null)
		{
			Optional<Company> company = companyrepository.findById(companyCode);
			
			if(company.isPresent())
			{
				return company.get();
			}
		}
		return null;
		
	}

	@Override
	public boolean deleteCompany(String companyCode) throws CompanyCodeNotExistException{
		
		Optional<Company> optionalCompany = companyrepository.findById(companyCode);
		
		if(optionalCompany.isEmpty())
		{
			throw new CompanyCodeNotExistException();
		}
		
		if(companyrepository.existsById(companyCode))
		{
		 companyrepository.deleteById(companyCode);
		 return true;
		}
		return false;
	}

	@Override
	public boolean addStockPrice(String companyCode ,Company company) {
		
		//Stock stock1 = stockRepository.
		if(companyrepository.existsById(companyCode))
		{
			Company company1 = companyrepository.getById(companyCode);
			company1.setStockPrice(company.getStockPrice());
			companyrepository.saveAndFlush(company1);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateStockPrice(String companyCode,Company company) {
		
		
		if(companyrepository.existsById(companyCode))
		{
			Company company1 = companyrepository.getById(companyCode);
			company1.setStockPrice(company.getStockPrice());
			companyrepository.saveAndFlush(company1);
			return true;
		}
		
		return false;
	}

}

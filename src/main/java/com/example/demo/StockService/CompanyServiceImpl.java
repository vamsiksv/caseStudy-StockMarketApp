package com.example.demo.StockService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
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
		
		//Optional<Company> optionalCompany= companyrepository.findById(company.getCompanyCode());
		
		/*
		 * if(optionalCompany.isPresent()) { throw new CompanyCodeExistsException(null);
		 * }
		 */
		
		if(company!=null && companyrepository.findById(company.getCompanyCode()).isEmpty() && company.getTurnOver()>10)
		{
			return companyrepository.saveAndFlush(company);
		
		}
		
		return null;
	}
	
	@Override
	public Company updateInfo(Company company) {
		
		Optional<Company> optionalCompany= companyrepository.findById(company.getCompanyCode());
		
		if(optionalCompany.isPresent())
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
			company1.setDate(java.time.LocalDate.now());
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
			company1.setDate(java.time.LocalDate.now());
			companyrepository.saveAndFlush(company1);
			return true;
		}
		
		return false;
	}

	public static final List<String> SUPPORTED_FORMATS = Arrays.asList("dd-MM-yyyy", "yyyy-MM-dd");
    public static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = SUPPORTED_FORMATS
            .stream()
            .map(DateTimeFormatter::ofPattern)
            .collect(Collectors.toList());
    public LocalDate convert(String s) {

        for (DateTimeFormatter dateTimeFormatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDate.parse(s, dateTimeFormatter);
            } catch (DateTimeParseException ex) {
                // deliberate empty block so that all parsers run
            }
        }

        throw new DateTimeException(String.format("unable to parse (%s) supported formats are %s",
                s, String.join(", ", SUPPORTED_FORMATS)));
    }

	@Override
	public Company maxStock() {
		 
		Company c = companyrepository.maxStock();
		if(c!=null)
		{
			return c;
		}
		return null;
	}

	@Override
	public Company minStock() {
		Company c = companyrepository.minStock();
		if(c!=null)
		{
			return c;
		}
		return null;
	}

	@Override
	public double avgStock() {
		return companyrepository.avgStock();
	}

	
	/*
	 * @Override public List<Company> customApi(Company company) { // TODO
	 * Auto-generated method stub List<Company> stockList =
	 * companyrepository.customApi(company); if(stockList!=null &&
	 * stockList.size()>0) { return stockList; } return null;
	 * 
	 * }
	 */
	 

	

}

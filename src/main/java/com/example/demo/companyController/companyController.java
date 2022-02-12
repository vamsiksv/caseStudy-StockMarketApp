package com.example.demo.companyController;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StockService.CompanyService;
//import com.example.demo.exceptionhandler.CompanyCodeExistsException;
import com.example.demo.exceptionhandler.CompanyCodeNotExistException;
import com.example.demo.responseHandler.ResponseHandler;
import com.example.demo.stockModel.Company;

@RestController
@RequestMapping("api/v1.0/market")
public class companyController {
	
	@Autowired
	private CompanyService CompanyService;
	
	@GetMapping("/company/getall")
	public ResponseEntity<?> getAllDetails()
	{
		List<Company> stockList = CompanyService.getAllDetails();
		if(stockList!=null)
		{
			//default way of implementation
			//return new ResponseEntity<List<Company>>(stockList, HttpStatus.OK);
			//Response Handler implemented
			//return ResponseHandler.handleResponse("Retrieved all registered Company details", HttpStatus.OK, stockList);
			//Implemented Caching along with response handler
			CacheControl cacheControl = CacheControl.maxAge(2,TimeUnit.MINUTES);
			return ResponseEntity.ok().cacheControl(cacheControl).body(ResponseHandler.handleResponse("Retrieved all registered Company details", HttpStatus.OK, stockList));
			
		}
		return new ResponseEntity<String>("No Company list is found!",HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping(value = "/company/register", consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> register(@RequestBody Company company) 
	{
		if(CompanyService.register(company)!= null && company.getTurnOver()>10)
		{
			//default way of implementation
			//return new ResponseEntity<Company>(company,HttpStatus.CREATED);
			//Response Handler implemented
			return ResponseHandler.handleResponse("Successfully registered the Company", HttpStatus.CREATED, company);
		}
		
		return new ResponseEntity<String>("Unable to register Company",HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/company/delete/{companyCode}")
	public ResponseEntity<?> deleteCompany(@PathVariable("companyCode") String companyCode) throws CompanyCodeNotExistException
	{
		if(CompanyService.deleteCompany(companyCode))
		{
			return new ResponseEntity<String>("Company record is deleted",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Internal error while deleting the Company record",HttpStatus.CONFLICT);
		
	}
	
	@GetMapping("/company/info/{companyCode}")
	public ResponseEntity<?> getInfo(@PathVariable("companyCode") String companyCode)
	{
		Company company = CompanyService.getInfo(companyCode);
		if(company != null) {
			//default way of implementation
			//return new ResponseEntity<Company>(company,HttpStatus.OK);
			//Response Handler implemented
			return ResponseHandler.handleResponse("Successfully retrieved the Company details", HttpStatus.OK, company);
		}
		return new ResponseEntity<String>("Company code not found",HttpStatus.CONFLICT);
	}
	
	@PutMapping("/stock/put/{companyCode}")
	public ResponseEntity<?> updateStockPrice(@PathVariable("companyCode") String companyCode , @RequestBody Company company) 
	{
	   if(CompanyService.updateStockPrice(companyCode, company))
	   {
		   return new ResponseEntity<String>("Stock Price updated",HttpStatus.OK);
	   }
	   return new ResponseEntity<String>("Stock Price not updated",HttpStatus.CONFLICT);
	}
	
	@PostMapping(value = "/stock/add/{companyCode}", consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> addStockPrice(@PathVariable("companyCode") String companyCode , @RequestBody Company company)
	{
		if(CompanyService.updateStockPrice(companyCode, company))
		   {
			   return new ResponseEntity<String>("Added the Stock Price",HttpStatus.OK);
		   }
		   return new ResponseEntity<String>("Stock Price not added",HttpStatus.CONFLICT);
	}

}

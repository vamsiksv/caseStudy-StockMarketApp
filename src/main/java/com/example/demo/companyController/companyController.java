package com.example.demo.companyController;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StockService.CompanyService;
//import com.example.demo.exceptionhandler.CompanyCodeExistsException;
import com.example.demo.exceptionhandler.CompanyCodeNotExistException;
import com.example.demo.responseHandler.ResponseHandler;
import com.example.demo.stockModel.Company;

@RestController
@RequestMapping("api/v1.0/market")
@CrossOrigin("*")
public class companyController {
	
	@Autowired
	private CompanyService CompanyService;
	
	@GetMapping("/company/getall")
	public ResponseEntity<?> getAllDetails()
	{
		System.out.println("in  cntlr");
		List<Company> stockList = CompanyService.getAllDetails();
		if(stockList!=null)
		{
			//default way of implementation
			return new ResponseEntity<List<Company>>(stockList, HttpStatus.OK);
			//Response Handler implemented
			//return ResponseHandler.handleResponse("Retrieved all registered Company details", HttpStatus.OK, stockList);
			//Implemented Caching along with response handler
			//CacheControl cacheControl = CacheControl.maxAge(2,TimeUnit.MINUTES);
			//return ResponseEntity.ok().cacheControl(cacheControl).body(ResponseHandler.handleResponse("Retrieved all registered Company details", HttpStatus.OK, stockList));
			
		}
		return new ResponseEntity<String>("No Company list is found!",HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping(value = "/company/register", consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> register(@RequestBody Company company) 
	{
		if(CompanyService.register(company)!= null)
		{
			//default way of implementation
			return new ResponseEntity<Company>(company,HttpStatus.CREATED);
			//Response Handler implemented
			//return ResponseHandler.handleResponse("Successfully registered the Company", HttpStatus.CREATED, company);
		}
		
		return new ResponseEntity<String>("Unable to register Company",HttpStatus.CONFLICT);
	}
	
	@PutMapping(value = "/company/updateinfo", consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> updateInfo(@RequestBody Company company) 
	{
		if(CompanyService.updateInfo(company)!= null )
		{
			//default way of implementation
			return new ResponseEntity<Company>(company,HttpStatus.ACCEPTED);
			//Response Handler implemented
			//return ResponseHandler.handleResponse("Successfully registered the Company", HttpStatus.CREATED, company);
		}
		
		return new ResponseEntity<String>("Unable to Update Company details",HttpStatus.CONFLICT);
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
			return new ResponseEntity<Company>(company,HttpStatus.OK);
			//Response Handler implemented
			//return ResponseHandler.handleResponse("Successfully retrieved the Company details", HttpStatus.OK, company);
		}
		return new ResponseEntity<String>("Company code not found",HttpStatus.CONFLICT);
	}
	
	@PutMapping(value="/stock/put/{companyCode}",consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> updateStockPrice(@PathVariable("companyCode") String companyCode , @RequestBody Company company) 
	{
	   if(CompanyService.updateStockPrice(companyCode, company))
	   {
		   //return new ResponseEntity<String>("Stock Price updated",HttpStatus.OK);
		   return ResponseHandler.handleResponse("Stock Price updated", HttpStatus.OK,company);
	   }
	   return new ResponseEntity<String>("Stock Price not updated",HttpStatus.CONFLICT);
	}
	
	@PostMapping(value = "/stock/add/{companyCode}", consumes = "application/json; charset= utf-8")
	public ResponseEntity<?> addStockPrice(@PathVariable("companyCode") String companyCode , @RequestBody Company company)
	{
		if(CompanyService.updateStockPrice(companyCode, company))
		   {
			  // return new ResponseEntity<String>("Added the Stock Price",HttpStatus.OK);
			   return ResponseHandler.handleResponse("Added the Stock Price", HttpStatus.OK,company);
		   }
		   return new ResponseEntity<String>("Stock Price not added",HttpStatus.CONFLICT);
	}
	
	@GetMapping("/company/getmaxStock")
	public ResponseEntity<?> getMaxStock()
	{
		Company company = CompanyService.maxStock();
		if(company !=null)
		{
			return new ResponseEntity<Company>(company,HttpStatus.OK);
		}
		return new ResponseEntity<String>("Not Found",HttpStatus.NO_CONTENT);

	}
	
	@GetMapping("/company/getminStock")
	public ResponseEntity<?> getMinStock()
	{
		Company company = CompanyService.minStock();
		if(company !=null)
		{
			return new ResponseEntity<Company>(company,HttpStatus.OK);
		}
		return new ResponseEntity<String>("Not Found",HttpStatus.NO_CONTENT);

	}
	
	@GetMapping("/company/getavgStock")
	public ResponseEntity<?> getAvgStock()
	{
		Double avg = CompanyService.avgStock();
		
		return new ResponseEntity<Double>(avg,HttpStatus.OK);

	}
	
	/*
	 * @GetMapping(value = "/stock/getcustom" ,consumes =
	 * "application/json; charset= utf-8") public ResponseEntity<?>
	 * getList(@RequestBody Company company) { List<Company> companyList =
	 * CompanyService.customApi(company); if(companyList!=null &&
	 * companyList.size()>0) { new ResponseEntity<List<Company>>(companyList,
	 * HttpStatus.OK); } return new
	 * ResponseEntity<String>("No Company list is found in the given tine period!"
	 * ,HttpStatus.NO_CONTENT); }
	 * 
	 */
}

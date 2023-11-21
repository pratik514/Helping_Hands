package Application.Controller;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.cfg.Environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.boot.context.config.ConfigData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.Models.DBconfigDto;
import Application.Models.ProductsDto;
import Application.Models.UsersDto;
import Application.Repo.UserRepo;
import Application.Service.ServiceImp;



@RestController
@ResponseBody
@CrossOrigin(origins ="http://localhost:5173")
public class Controller {
	private static final Logger logger = LogManager.getLogger(Controller.class);
	
	@Autowired
	org.springframework.core.env.Environment env;

	@Autowired
	UserRepo repo;

	@Autowired
	ServiceImp serveImp;
	
	private static final String TEST = "/test";
	private static final String GET_NAMES = "/names";
	private static final String PRODUCTS = "/products";
	private static final String PURCHASE_ORDER = "/purchase";
	
	
	
	
	
	
	
	@RequestMapping(value =TEST, method = RequestMethod.GET)
	public String test() {
	
			String gritting="";
			try {
				logger.info("test method started ........");
				gritting = serveImp.hello();
				logger.info("test method ended ........");
				
			} catch (Exception e) {
				logger.error("error occured in  Controller::test() ........");
				e.printStackTrace();
			}
			return gritting;
			
				
	}

	@RequestMapping(value=GET_NAMES, method= RequestMethod.GET )
	public List<UsersDto> getAllUserNames() throws Exception {
		List<UsersDto> usernames = new ArrayList<UsersDto>();
		try {
			logger.info("getAllUserNames method started ........");
			usernames = serveImp.allUserNames();
			logger.info("getAllUserNames method ended ........");
			
		} catch (Exception e) {
			logger.error("error occured in  Controller::getAllUserNames() ........");
			e.printStackTrace();
		}
		return usernames;

	}
	@RequestMapping(value=PRODUCTS, method= RequestMethod.GET )
	public List<ProductsDto> getAllProducts() throws Exception {
		List<ProductsDto> productList = new ArrayList<ProductsDto>();
		try {
			logger.info("getAllProducts method started ........");
			productList = serveImp.allProducts();
			logger.info("getAllProducts method ended ........");
			
		} catch (Exception e) {
			logger.error("error occured in  Controller::getAllProducts() ........");
			e.printStackTrace();
		}
		return productList;

	}
	
	@RequestMapping(value=PURCHASE_ORDER, method= RequestMethod.POST)
	public void purchaseOrder(@RequestBody ProductsDto productDto ) throws Exception {
			try {
				logger.info("purchaseOrder method started ........");
				 serveImp.getPurchaseOrder(productDto);
				logger.info("purchaseOrder method ended ........");

			} catch (Exception e) {
				logger.error("error occured in  Controller::purchaseOrder() ........");
				e.printStackTrace();				
			}
			
		}
	}
	

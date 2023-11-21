package Application.Service;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.Controller.Controller;
import Application.Models.DBconfigDto;
import Application.Models.ProductsDto;
import Application.Models.UsersDto;

@Component
public class ServiceImp {

	private static final Logger logger = LogManager.getLogger(Controller.class);

	@Autowired
	org.springframework.core.env.Environment env;

	public String hello() {
		return "HELLO Guys ... I am back.!! :)";
	}

	public List<UsersDto> allUserNames() throws Exception {
		java.sql.Connection con = null;
		String query = "select * from Customers";
		DBconfigDto config = new DBconfigDto();
		config.setUrl(env.getRequiredProperty("spring.datasource.url"));
		config.setUsername(env.getRequiredProperty("spring.datasource.username"));
		config.setPassword(env.getRequiredProperty("spring.datasource.password"));

		List<UsersDto> nameList = new ArrayList<UsersDto>();

		try {
			logger.info("allUserNames method started ........");
			con = DriverManager.getConnection((config.getUrl()), (config.getUsername()), (config.getPassword()));
			logger.info("connection established");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				UsersDto users = new UsersDto();
				users.setName(rs.getString("name"));
				users.setLocation(rs.getString("location"));
				nameList.add(users);
				

			}
			logger.info("allUserNames method ended ........");
		} catch (Exception e) {
			logger.error("error occured in  ServiceImp :: allUserNames() ........");
			e.printStackTrace();
		}finally {
		con.close();
		}
		return nameList;
	}

	public List<ProductsDto> allProducts() throws Exception {
		java.sql.Connection con = null;
		String query = "select * from croma_products";
		DBconfigDto config = new DBconfigDto();
		config.setUrl(env.getRequiredProperty("spring.datasource.url"));
		config.setUsername(env.getRequiredProperty("spring.datasource.username"));
		config.setPassword(env.getRequiredProperty("spring.datasource.password"));

		List<ProductsDto> productList = new ArrayList<ProductsDto>();

		try {
			logger.info("allProducts method started ........");
			con = DriverManager.getConnection((config.getUrl()), (config.getUsername()), (config.getPassword()));
			logger.info("connection established");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				ProductsDto prod = new ProductsDto(); 
				prod.setProducts(rs.getString("Products"));
				prod.setInStock(rs.getBoolean("InStock"));
				prod.setOffer(rs.getInt("Offer"));
				prod.setPrice(rs.getInt("price"));
				prod.setQuantity(rs.getInt("quantity"));
				prod.setProductId(rs.getInt("productId"));
				productList.add(prod);
				

			}
			logger.info("allProducts method ended ........");
		} catch (Exception e) {
			logger.error("error occured in  ServiceImp :: allUserNames() ........");
			e.printStackTrace();
		}finally {
		con.close();
		}
		return productList;
	}

	
	public void getPurchaseOrder(ProductsDto productDto) throws Exception {
		
		DBconfigDto config = new DBconfigDto();
		config.setUrl(env.getRequiredProperty("spring.datasource.url"));
		config.setUsername(env.getRequiredProperty("spring.datasource.username"));
		config.setPassword(env.getRequiredProperty("spring.datasource.password"));
		
		java.sql.Connection con = null; 
		ResultSet result =  null;  
		String query ="select * from croma_products where \"productId\"=?";
		String UPDATE_QUERY ="UPDATE croma_products SET  quantity=? WHERE \"productId\"=?";
		try {
			logger.info("getPurchaseOrder method started ........");
			con = DriverManager.getConnection((config.getUrl()), (config.getUsername()), (config.getPassword()));
			logger.info("connection established");
			PreparedStatement pstmt = con.prepareStatement(query);
			int	idProd = productDto.getProductId();
			pstmt.setInt(1,idProd );
			result =pstmt.executeQuery();
			result.next();
			boolean condition = result.getBoolean("InStock");
			 int availableQuantity  = result.getInt("quantity");
			 int prodId = result.getInt("productId");

			if(condition && availableQuantity>0) {
			int	ReqQuantity = productDto.getReqQuantity();
			int	updatedQuantity = availableQuantity - ReqQuantity;
			PreparedStatement Pstmt = con.prepareStatement(UPDATE_QUERY);
			Pstmt.setInt(1, updatedQuantity);
			Pstmt.setInt(2, prodId);			
			 Pstmt.executeUpdate();
			 
			 logger.info("getPurchaseOrder method ended ........");
			}else {
				logger.warn("Product Not Avalable");
			}
			
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("error occured in  ServiceImp ::getPurchaseOrder () ........");	
		}finally {
			con.close();			
		}
		
	}

}

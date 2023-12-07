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
import Application.Models.InvoiceDto;
import Application.Models.ProductsDto;
import Application.Models.CustomerDto;

@Component
public class ServiceImp {

	private static final Logger logger = LogManager.getLogger(Controller.class);

	@Autowired
	org.springframework.core.env.Environment env;

	public String hello() {
		return "HELLO Guys ... I am back.!! :)";
	}

	public List<CustomerDto> allUserNames() throws Exception {
		java.sql.Connection con = null;
		String query = "select * from Customers";
		DBconfigDto config = new DBconfigDto();
		config.setUrl(env.getRequiredProperty("spring.datasource.url"));
		config.setUsername(env.getRequiredProperty("spring.datasource.username"));
		config.setPassword(env.getRequiredProperty("spring.datasource.password"));

		List<CustomerDto> nameList = new ArrayList<CustomerDto>();

		try {
			logger.info("allUserNames method started ........");
			con = DriverManager.getConnection((config.getUrl()), (config.getUsername()), (config.getPassword()));
			logger.info("connection established");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				CustomerDto users = new CustomerDto();
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

	public void invoiceGeneration(InvoiceDto invoiceDto) throws Exception {
		java.sql.Connection con = null;
		String query = "INSERT INTO public.\"Order_Details\"(\r\n"
				+ "	\"OrderId\", \"StoreLoc\", \"totalAmount\", \"email_Id\", \"product_Id\")\r\n"
				+ "	VALUES (? , ?, ?, ?,?)";
				
		DBconfigDto config = new DBconfigDto();
		config.setUrl(env.getRequiredProperty("spring.datasource.url"));
		config.setUsername(env.getRequiredProperty("spring.datasource.username"));
		config.setPassword(env.getRequiredProperty("spring.datasource.password"));
		ResultSet rs = null;
 

		try {
			logger.info("invoiceGeneration method started ........");
			con = DriverManager.getConnection((config.getUrl()), (config.getUsername()), (config.getPassword()));
			logger.info("connection established");
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, invoiceDto.getOrderId());
			pstmt.setString(2, invoiceDto.getStoreLoc());
			pstmt.setDouble(3, invoiceDto.getTotalAmount());
			pstmt.setString(4, invoiceDto.getEmail_id());
			pstmt.setString(5, invoiceDto.getProduct_Id());
			
			pstmt.executeUpdate();
			
			logger.info("invoiceGeneration method ended ........");
			
		} catch (Exception e) {
			logger.error("error occured in  ServiceImp :: allUserNames() ........");
			e.printStackTrace();
		}finally {
		con.close();
		}
	}

	public List<InvoiceDto> ordersDetails(String emailId) throws SQLException {
		java.sql.Connection con = null;
		String query = "SELECT * FROM public.\"Order_Details\" WHERE \"email_Id\" =?";
		DBconfigDto config = new DBconfigDto();
		config.setUrl(env.getRequiredProperty("spring.datasource.url"));
		config.setUsername(env.getRequiredProperty("spring.datasource.username"));
		config.setPassword(env.getRequiredProperty("spring.datasource.password"));

		List<InvoiceDto> detailsList = new ArrayList<InvoiceDto>();
		ResultSet rs = null;
		try {
			logger.info("allProducts method started ........");
			con = DriverManager.getConnection((config.getUrl()), (config.getUsername()), (config.getPassword()));
			logger.info("connection established");

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, emailId);
			rs = pstmt.executeQuery();
			while (rs.next()) {		
				InvoiceDto details = new InvoiceDto();
				details.setOrderId(rs.getInt("OrderId"));  
				details.setStoreLoc(rs.getString("StoreLoc"));  
				details.setTotalAmount(rs.getDouble("totalAmount"));
				details.setEmail_id(rs.getString("email_Id")); 
				details.setProduct_Id(rs.getString("product_Id"));
				detailsList.add(details);
			}
			logger.info("ordersDetails method ended ........");
		} catch (Exception e) {
			logger.error("error occured in  ServiceImp :: allUserNames() ........");
			e.printStackTrace();
		}finally {
		con.close();
		}
		return detailsList;
	}
}

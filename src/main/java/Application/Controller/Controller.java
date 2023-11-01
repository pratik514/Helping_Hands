package Application.Controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.boot.context.config.ConfigData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.Models.DBconfig;
import Application.Repo.UserRepo;



@RestController
@ResponseBody
@CrossOrigin(origins ="http://localhost:5173")
public class Controller {
	
	@Autowired
	org.springframework.core.env.Environment env;	 
	
	@Autowired
	UserRepo repo;
	
	
	@RequestMapping("/test")
	public String hello() {
	return "HELLO Guys ... I am back.!! :)";
}
	
	@RequestMapping("/names")
	public  ArrayList<String> getAllUserNames() throws Exception{
		java.sql.Connection	con = null;
		String query ="select * from studs";
		DBconfig config = new DBconfig();
		config.setUrl(env.getRequiredProperty("spring.datasource.url"));
		config.setUsername(env.getRequiredProperty("spring.datasource.username"));
		config.setPassword(env.getRequiredProperty("spring.datasource.password"));
		
		ArrayList<String> nameList = new ArrayList<String>();
		
		try {
			con = DriverManager.getConnection((config.getUrl()),(config.getUsername()),(config.getPassword()));
			System.out.println("connected to DB");
		} catch (SQLException e) {
			System.out.println("error while connecting to DB");
			e.printStackTrace();
		}

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			nameList.add(rs.getString("name"));
			nameList.add(rs.getString("location"));
			
		}
		con.close();
		return nameList;	
	}
	}

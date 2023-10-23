package Controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import repository.UserRepo;

@RestController
@ResponseBody
public class HelloWorldController {
	
	@Autowired
	UserRepo userRepo ;
	
	@RequestMapping("/test")
	public String hello() {
	return "HELLO Guys ... I am back.!! :)";
}
	@RequestMapping("/names")
	public  ArrayList<String> getAllUserNames() throws Exception{
		String query ="select * from students";
		String url ="jdbc:postgresql://localhost:5432/students";
		String username = "postgres";
		String passward ="Prati@514";
		ArrayList<String> nameList = new ArrayList<String>();
		java.sql.Connection	con	=  DriverManager.getConnection((url),(username),(passward));
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			nameList.add(rs.getString("Name"));
			nameList.add(rs.getString("location"));
			
		}
		con.close();
		return nameList;
			
		
	}
	}

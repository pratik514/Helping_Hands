package Application;

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



@RestController
@ResponseBody
public class HelloWorldController {
	
	
	@RequestMapping("/test")
	public String hello() {
	return "HELLO Guys ... I am back.!! :)";
}
	
	@RequestMapping("/names")
	public  ArrayList<String> getAllUserNames() throws Exception{
		java.sql.Connection	con = null;
		String query ="select * from studs";
		String url ="jdbc:postgresql://localhost:5432/pratik_jdbc";
		String username = "postgres";
		String passward ="Pratik@514";
		ArrayList<String> nameList = new ArrayList<String>();
		
		try {
			con = DriverManager.getConnection((url),(username),(passward));
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

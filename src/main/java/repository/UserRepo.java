package repository;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepo{
	
	
	
	public List<String> getUserNames() {
		List<String> userNameList = new ArrayList<>();
		
		return userNameList;
	}

}

package Application.Models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roll_no ;
	private String name ;
	private String emai_id ;
	private String location ;
	
	
	public int getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(int roll_no) {
		this.roll_no = roll_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmai_id() {
		return emai_id;
	}
	public void setEmai_id(String emai_id) {
		this.emai_id = emai_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public String toString() {
		return "Users [roll_no=" + roll_no + ", name=" + name + ", emai_id=" + emai_id + ", location=" + location + "]";
	}
	
	
		
	

}

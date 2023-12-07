package Application.Models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CustomerDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int phoneNo ;
	private String name ;
	private String emai_id ;
	private String location ;


	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
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

}

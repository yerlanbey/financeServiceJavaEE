package hw5.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_email")

public class CustomerInfo {
    private String customer_email;
    private String address;
    private String balance;
    private String name;
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    


    

}

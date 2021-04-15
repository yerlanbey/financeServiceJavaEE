

package hw5.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_email")
public class CustomerCredential {
    private String customer_email;
    private String password;
    
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    

}

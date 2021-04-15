package hw5.databean;
import org.genericdao.PrimaryKey;
@PrimaryKey("id")

public class CustomerFunds {
    private String id;
    private String customer_email;
    private String fund_name;
    private String number_of_shares;
    private String total_value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getFund_name() {
		return fund_name;
	}
	public void setFund_name(String fund_name) {
		this.fund_name = fund_name;
	}
	public String getNumber_of_shares() {
		return number_of_shares;
	}
	public void setNumber_of_shares(String number_of_shares) {
		this.number_of_shares = number_of_shares;
	}
	public String getTotal_value() {
		return total_value;
	}
	public void setTotal_value(String total_value) {
		this.total_value = total_value;
	}
    
    

    
}

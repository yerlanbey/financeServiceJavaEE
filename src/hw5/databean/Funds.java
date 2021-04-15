package hw5.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fund_name")
public class Funds {
    private String fund_name;
    private String current_price;
    
	public String getFund_name() {
		return fund_name;
	}
	public void setFund_name(String fund_name) {
		this.fund_name = fund_name;
	}
	public String getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(String current_price) {
		this.current_price = current_price;
	}
   
 
}

package hw5.formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("customer_email,balance")
public class DepositForm extends FormBean {
    private String customer_email;
    private String balance;
    private String action;
    
    public String getCustomer_email()  { return customer_email; }
    public String getBalance()  { return balance; }
    public String getAction()    { return action; }
	
    public void setCustomer_email(String s)  { customer_email = s.trim(); }
    @InputType("balance")
    public void setBalance(String s)  { balance = s.trim(); }
    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        for(int i = 0; i < balance.length(); i++) {
            if(Character.isLetter(balance.charAt(i))) {
            	this.addFormError("Balance should be in decimal number");
            	break;
            }
        }

        if (!action.equals("Deposit")) {
            this.addFormError("Invalid button");
        }
        
        if (balance.matches(".*[<>\"].*")) {
            this.addFieldError("customer_email", "May not contain angle brackets or quotes");
        }
    }
}

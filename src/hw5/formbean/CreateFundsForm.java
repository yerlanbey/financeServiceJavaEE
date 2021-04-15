package hw5.formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("fund_name,current_price")
public class CreateFundsForm extends FormBean {
    private String fund_name;
    private String current_price;
    private String action;
    
    public String getFund_name()  { return fund_name; }
    public String getCurrent_price()  { return current_price; }
    public String getAction()    { return action; }
	
    public void setFund_name(String s)  { fund_name = s.trim(); }
    @InputType("current_price")
    public void setCurrent_price(String s)  { current_price = s.trim(); }
    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Fund")) {
            this.addFormError("Invalid button");
        }
        for(int i = 0; i < current_price.length(); i++) {
            if(Character.isLetter(current_price.charAt(i))) {
            	this.addFormError("Price should be a decimal number");
            	break;
            }
        }
        if (fund_name.matches(".*[<>\"].*")) {
            this.addFieldError("userName", "May not contain angle brackets or quotes");
        }
    }
}

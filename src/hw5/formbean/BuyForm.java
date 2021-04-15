
package hw5.formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("fund_name,number_of_shares")
public class BuyForm extends FormBean {
    private String fund_name;
    private String number_of_shares;
    private String action;
    
    public String getFund_name()  { return fund_name; }
    public String getNumber_of_shares()  { return number_of_shares; }
    public String getAction()    { return action; }
	
    public void setFund_name(String s)  { fund_name = s.trim(); }
    @InputType("number_of_shares")
    public void setNumber_of_shares(String s)  { number_of_shares = s.trim(); }
    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Buy")) {
            this.addFormError("Invalid button");
        }
        for(int i = 0; i < number_of_shares.length(); i++) {
            if(Character.isLetter(number_of_shares.charAt(i))) {
            	this.addFormError("Amount should be a decimal number with maximum 2 digit decimal.");
            	break;
            }
        }
        if (fund_name.matches(".*[<>\"].*")) {
            this.addFieldError("fund_name", "May not contain angle brackets or quotes");
        }
    }
}

package hw5.formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("email,name,password,address")
public class CreateCustomerAccountForm extends FormBean {
    private String email;
    private String name;
    private String password;
    private String address;
    private String action;
    
    public String getEmail()  { return email; }
    public String getName() { return name; }
    public String getPassword()  { return password; }
    public String getAddress()    { return address; }
    public String getAction()    { return action; }
    
  
    public void setEmail(String s)  { email = s.trim(); }
    
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }
    
    public void setName(String s) { name = s.trim(); }

    public void setAddress(String s)    { address   = s.trim(); }
    
    @InputType("button")
    public void setAction(String s)    { action   = s;        }
    
    
    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Register")) {
            this.addFormError("Invalid button");
        }
        if (!email.contains("@")) {
        	this.addFormError("Incorrect email format");
        }
        
        if (password.length() < 3) {
            this.addFormError("Invalid Password Length. It should be at least 3");
        }
    }
}

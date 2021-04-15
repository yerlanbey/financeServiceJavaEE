package hw5.formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("employee_id,password")
public class EmployeeLoginForm extends FormBean {
    private String employee_id;
    private String password;
    private String action;
    
    public String getEmployee_id()  { return employee_id; }
    public String getPassword()  { return password; }
    public String getAction()    { return action; }
	
    public void setEmployee_id(String s)  { employee_id = s.trim(); }
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }
    @InputType("button")
    public void setAction(String s)    { action   = s;        }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("Login") && !action.equals("Register")) {
            this.addFormError("Invalid button");
        }
        if (employee_id == null || employee_id.length() == 0) {
        	this.addFieldError("employee_id", "Employee ID is required");
        }
        
        if (password == null || password.length() == 0) {
        	this.addFieldError("password", "Password is required");
        }
        if (employee_id.matches(".*[<>\"].*")) {
            this.addFieldError("userName", "May not contain angle brackets or quotes");
        }
    }
}

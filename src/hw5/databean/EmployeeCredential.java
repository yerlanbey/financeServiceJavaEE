package hw5.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("employee_id")
public class EmployeeCredential {
    private String employee_id;
    private String password;
    
    
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    
}

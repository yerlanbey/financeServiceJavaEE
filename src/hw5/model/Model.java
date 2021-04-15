package hw5.model;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private CustomerCredentialDAO customerCredDAO;
	private CustomerFundsDAO customerFundsDAO;
	private CustomerInfoDAO customerInfoDAO;
	private EmployeeCredentialDAO employeeCredDAO;
	private FundsDAO fundsDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			customerCredDAO  = new CustomerCredentialDAO(pool, "hw5_customer_credential");
			customerFundsDAO = new CustomerFundsDAO(pool, "hw5_customer_funds");
			customerInfoDAO = new CustomerInfoDAO(pool, "hw5_customer_info");
			employeeCredDAO = new EmployeeCredentialDAO(pool, "hw5_employee_credential");
			fundsDAO = new FundsDAO(pool, "hw5_funds");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}	
	
	public CustomerCredentialDAO getCustomerCredentialDAO()  { return customerCredDAO; }
	public CustomerFundsDAO getCustomerFundsDAO()  { return customerFundsDAO; }
	public CustomerInfoDAO getCustomerInfoDAO()  { return customerInfoDAO; }
	public EmployeeCredentialDAO getEmployeeCredentialDAO()  { return employeeCredDAO; }
	public FundsDAO getFundsDAO()  { return fundsDAO; }
}

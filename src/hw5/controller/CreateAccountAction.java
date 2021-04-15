package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import hw5.databean.CustomerCredential;
import hw5.databean.CustomerInfo;
import hw5.databean.EmployeeCredential;
import hw5.formbean.CreateCustomerAccountForm;
import hw5.formbean.EmployeeLoginForm;
import hw5.model.Model;
import hw5.model.CustomerCredentialDAO;
import hw5.model.CustomerInfoDAO;
import hw5.model.EmployeeCredentialDAO;

public class CreateAccountAction extends Action {
    private FormBeanFactory<CreateCustomerAccountForm> formBeanFactory = new FormBeanFactory<>(CreateCustomerAccountForm.class);

    private CustomerCredentialDAO custCredDAO;
    private CustomerInfoDAO custInfoDAO;
    private EmployeeCredentialDAO employeeDAO;

    public CreateAccountAction(Model model) {
    	custCredDAO = model.getCustomerCredentialDAO();
    	custInfoDAO = model.getCustomerInfoDAO();
    }

    public String getName() {
        return "register.do";
    }
    
    public String performGet(HttpServletRequest request) {
        // Otherwise, just display the login page.
        request.setAttribute("form", new CreateCustomerAccountForm());
        return "Register.jsp";
        
        
    }

    public String performPost(HttpServletRequest request) {
        try {
        	CreateCustomerAccountForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "Register.jsp";
            }

            // Look up the user
            CustomerCredential customer = custCredDAO.read(form.getEmail());
            System.out.print(form.getEmail());

            if (customer == null) {
                if (form.getAction().equals("Register")) {
                	CustomerCredential newCustomerCred = new CustomerCredential();
                	CustomerInfo newCustomerInfo = new CustomerInfo();
                	newCustomerCred.setCustomer_email(form.getEmail());
                	System.out.print(form.getEmail());
                	newCustomerCred.setPassword(form.getPassword());
                    
                	newCustomerInfo.setCustomer_email(form.getEmail());
                	newCustomerInfo.setAddress(form.getAddress());
                	newCustomerInfo.setName(form.getName());
                	newCustomerInfo.setBalance("0");
                    try {
                    	custCredDAO.create(newCustomerCred);
                    	custInfoDAO.create(newCustomerInfo);
                        
                        return ("register.do");
                    } catch (DuplicateKeyException e) {
                        form.addFieldError("email", "A user with this email already exists");
                        return "Register.jsp";
                    }
                }
            }else {
                form.addFieldError("email", "Email is exist");
                return "Register.jsp";
            }
           
            


            // Attach (this copy of) the user bean to the session
//            session.setAttribute("user", user);

            // If redirectTo is null, redirect to the "todolist" action
            return "register.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}

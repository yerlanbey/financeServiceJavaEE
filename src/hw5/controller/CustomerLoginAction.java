package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import hw5.databean.CustomerCredential;
import hw5.formbean.CustomerLoginForm;
import hw5.model.Model;
import hw5.model.CustomerCredentialDAO;

public class CustomerLoginAction extends Action {
    private FormBeanFactory<CustomerLoginForm> formBeanFactory = new FormBeanFactory<>(CustomerLoginForm.class);

    private CustomerCredentialDAO customerDAO;

    public CustomerLoginAction(Model model) {
    	customerDAO = model.getCustomerCredentialDAO();
    }

    public String getName() {
        return "signin.do";
        
       
    }

    public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
    	
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            return "welcome.do";
        }

        // Otherwise, just display the login page.
        request.setAttribute("form", new CustomerLoginForm());
        return "customer.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("customer") != null) {
            return "welcome.do";
        }

        try {
        	CustomerLoginForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "customer.jsp";
            }



            // Look up the user
            CustomerCredential customer = customerDAO.read(form.getCustomer_email());

            if (customer == null) {
                form.addFieldError("customer_email", "No such user");
                return "customer.jsp";
            }

            // Check the password
            if (!customer.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "customer.jsp";
            }
            
            // Attach (this copy of) the user bean to the session
            session.setAttribute("customer", customer);

            // If redirectTo is null, redirect to the "todolist" action
            return "welcome.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}

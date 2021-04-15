package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;


import hw5.databean.CustomerInfo;

import hw5.formbean.DepositForm;

import hw5.model.Model;

import hw5.model.CustomerInfoDAO;
import hw5.model.EmployeeCredentialDAO;

public class DepositAction extends Action {
    private FormBeanFactory<DepositForm> formBeanFactory = new FormBeanFactory<>(DepositForm.class);


    private CustomerInfoDAO custInfoDAO;


    public DepositAction(Model model) {

    	custInfoDAO = model.getCustomerInfoDAO();
    }

    public String getName() {
        return "deposit.do";
    }
    
    public String performGet(HttpServletRequest request) {
        // Otherwise, just display the login page.
        request.setAttribute("form", new DepositForm());
        return "deposit.jsp";
        
        
    }

    public String performPost(HttpServletRequest request) {
        try {
        	DepositForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "deposit.jsp";
            }

            // Look up the user
            CustomerInfo customer = custInfoDAO.read(form.getCustomer_email());
            System.out.print(form.getCustomer_email());

            if (customer != null) {
                if (form.getAction().equals("Deposit")) {
 
                	customer.setBalance(form.getBalance());
                	customer.setCustomer_email(form.getCustomer_email());
                	custInfoDAO.update(customer);
                    return ("deposit.do");
                  
                }
            }else {
                form.addFormError( "No such customer");
                return "deposit.jsp";
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

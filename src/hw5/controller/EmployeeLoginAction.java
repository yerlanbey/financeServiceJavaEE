package hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import hw5.databean.EmployeeCredential;
import hw5.formbean.EmployeeLoginForm;
import hw5.model.Model;
import hw5.model.EmployeeCredentialDAO;

public class EmployeeLoginAction extends Action {
    private FormBeanFactory<EmployeeLoginForm> formBeanFactory = new FormBeanFactory<>(EmployeeLoginForm.class);

    private EmployeeCredentialDAO employeeDAO;

    public EmployeeLoginAction(Model model) {
    	employeeDAO = model.getEmployeeCredentialDAO();
    }

    public String getName() {
        return "login.do";
        
       
    }

    public String performGet(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "register.do";
        }

        // Otherwise, just display the login page.
        request.setAttribute("form", new EmployeeLoginForm());
        return "index.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is already logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "register.do";
        }

        try {
        	EmployeeLoginForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "index.jsp";
            }



            // Look up the user
            EmployeeCredential user = employeeDAO.read(form.getEmployee_id());
            System.out.print(user);

            if (user == null) {
                form.addFieldError("employee_id", "Employee ID not found");
                return "index.jsp";
            }

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "index.jsp";
            }
            

            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);

            // If redirectTo is null, redirect to the "todolist" action
            return "register.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}

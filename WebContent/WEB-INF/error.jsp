<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title> -- Error Page --</title>
    </head>
    
	<body>
	
		<h2>-- Error Page --</h2>
<%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h3 style="color:red"> <%= error %> </h3>
<%
			}
		}
		
		if (session.getAttribute("user") == null) {
%>
			Click <a href="Register">here</a> to login.
<%
		} else {
%>
			Click <a href="ToDoList">here</a> to return to the To Do List.
<%
		}
%>	
	
	</body>
</html>
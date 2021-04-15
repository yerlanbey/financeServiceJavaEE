<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%-- Styles --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<%-- Scripts --%>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>Blog</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 40%;
}

.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.container {
  padding: 2px 16px;
}
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #3477eb;
  color: white;
}
</style>
</head>
<body onload="document.getElementById('userName').focus()">
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">

		<a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">SDU</br>Finance!</a>
		  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		
		<div class="btn-group me-2">
		            <a href="signout.do" class="btn btn-sm btn-outline-secondary">Logout</a>
		</div>

</header>
<%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h3 style="color:red"> <%= error %> </h3>
<%
			}
		}
%>	
<div class="container-fluid">
  <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse" style="">
      <div class="position-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="welcome.do">
              View Account
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="buy.do">
              Buy Fund
            </a>
          </li>
        </ul>
      </div>
    </nav>
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
		<h1>Welcome, Customer <b>${ customer_info.customer_email }</b></h1>
		<h4>Available balance is: <b>${ customer_info.balance }</b></h4>
		     	<c:forEach var="error" items="${form.formErrors}">
					<h3 style="color:red"> ${error} </h3>
				</c:forEach>
			<form action="buy.do" method="POST">
				    <table>
				        <c:forEach var="field" items="${form.visibleFields}">
		        		        <tr>
		        		            <td style="font-size: x-large">
		                            <label>${field.label}</label>
		                        </td>
		        		            <td>
		        		                <input class="form-control" id="${field.name}" type="${field.type}" name="${field.name}" value="${field.value}"/>
		        		            </td>
		                        <td style="color:red">
		                            ${field.error}
		                        </td>
		        		        </tr>
		                </c:forEach>
				        <tr>
				            <td colspan="2" align="left">
				                <button type="submit" name="action" class="btn btn-primary" value="Buy"  >Buy</button>
				            </td>
				        </tr>
					</table>
				</form>
				<table id="customers" style="margin-top: 50px;">
							  <tr>
							    <th>Fund name</th>
							    <th>Price of one share</th>
							  </tr>
							<c:forEach var="fund" items="${funds}">
								  <tr>
								    <td>${ fund.fund_name }</td>
								    <td>${ fund.current_price }</td>
	
								  </tr>
							</c:forEach>
				</table>
    </main>
  </div>
</div>
</body>
</html>
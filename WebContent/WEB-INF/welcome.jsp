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
	<h1>Welcome, Customer ${ customer_info.customer_email }</h1>

<div class="card">
  <img src="https://www.pphfoundation.ca/wp-content/uploads/2018/05/default-avatar.png" alt="Avatar" style="width:350px; height:200px;">
  <div class="container">
  	<h2>Customer Information</h2>
    <h4>Name:<b>${ customer_info.name }</b></h4> 
    <h4>Email:${ customer_info.customer_email }</b></h4> 
    <h4>Address:${ customer_info.address }</b></h4> 
    <h4>Balance:${ customer_info.balance }</b></h4> 
  </div>
	</div>
				<table id="customers" style="margin-top: 50px;">
							  <tr>
							    <th>Fund name</th>
							    <th>Number of shares</th>
							    <th>Total value</th>
							    <th>Action</th>
							  </tr>
							<c:forEach var="customer_fund" items="${customer_funds}">
								  <tr>
								    <td>${ customer_fund.fund_name }</td>
								    <td>${ customer_fund.number_of_shares }</td>
									<td>${ customer_fund.total_value }</td>
									<td>
						                <form class="delete-form" method="POST" action="refund.do">
						                    	<input type="hidden" name="id" value="${ customer_fund.id }" />
						                    <button type="submit" class="btn btn-success">Refund</button>
						                </form>
									</td>
								  </tr>
							</c:forEach>
				</table>
    </main>
  </div>
</div>
</body>
</html>
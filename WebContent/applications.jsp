<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.application"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Solr Panel Application Management</title>
	<link rel="stylesheet" href="Views/bootstrap.css">
</head>

<body>
	<div class="container"><div class="row"><div class="col-6">
		<h1>------ Solar Panel Application ------</h1>
		<form id="formApplication" name="formApplication">

			<!-- NAME -->
 			Name:
 			<input id="name" name="name" type="text"
 				class="form-control form-control-sm"
				placeholder="Enter Your Name Here">
 			<br> 

			 <!-- NIC -->
			 NIC:
		 	<input id="nic" name="nic" type="text"
 				class="form-control form-control-sm"
				placeholder="Enter Your NIC Here">
 			<br>
			 
			 <!-- ADDRESS -->
			 Address:
 			<input id="address" name="address" type="text"
 				class="form-control form-control-sm"
				placeholder="Enter Your Address Here">
 			<br> 

			 <!-- PHONE -->
			 Phone:
 			<input id="phone" name="phone" type="text"
 				class="form-control form-control-sm"
				placeholder="Enter Your Phone Number Here">
 			<br>

			 <!-- E-MAIL -->
			E-mail:
 			<input id="email" email="email" type="text"
 				class="form-control form-control-sm"
				placeholder="Enter Your E-mail Here">
 			<br> 

			 <!-- AREA -->
			 Area:
		 	<input id="area" name="area" type="text"
 				class="form-control form-control-sm"
				placeholder="Enter Your Area Here">
 			<br> 

			 <!-- SERVICE CENTER -->
			 Service Center:
 			<input id="service_center" name="service_center" type="text"
 				class="form-control form-control-sm"
				placeholder="Enter Your Service Center Here">
 			<br>
			 
			 <!-- SOLAR PANEL TYPE -->
			<div class="btn-group">
				<button 
					type="button" 
					class="btn btn-warning dropdown-toggle form-control form-control-sm" 
					data-toggle="dropdown" 
					aria-haspopup="true" 
					aria-expanded="false">
				Solar Panel
				</button>
				<div class="dropdown-menu">
				<a class="dropdown-item" href="#">Polycrystalline</a>
				<a class="dropdown-item" href="#">Monocrystalline</a>
				<a class="dropdown-item" href="#">Amorphous silicon (a-Si)</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="#">SCadmium telluride (CdTe)</a>
				</div>
			</div> 
 			<br>

			 <!-- BUTTON SUBMIT -->
 			<input id="btnSave" name="btnSave" type="button" value="Submit"
 				class="btn btn-success">
 			<!-- <input type="hidden" id="hidApplicationIDSave"
 				name="hidApplicationIDSave" value=""> -->
		</form>
		<br>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
	<br>
		<div id="divApplicationGrid">
 		<%
 			application applicationObj = new application();
 			out.print(applicationObj.readapplication());
 		%>
		</div>
		</div> 
	</div> 
</div>
</body>

	<script src="Components/jquery.min.js"></script>
	<script src="Components/inquiries.js"></script>
</html>
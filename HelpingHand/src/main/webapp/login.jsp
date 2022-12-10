<%
//change redirect page to postings later 
	if(session.getAttribute("fname")!=null){
		response.sendRedirect("profile.jsp");
	}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Helping Hand Login</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 	Bootstrap Link  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link href='https://fonts.googleapis.com/css?family=Shrikhand' rel='stylesheet'>
	
	<style>
	/*      Styling for Required Asterick - Class */
		.required:after {
			content: " *";
			color: red;
		}
	/*      Title Font */
		.titlefont {
            font-family: 'Shrikhand'; 
			font-size: 30px;
			color:#B62D2D;
        }  
	/*      Background Color */
		.overlay{
			background-color:#FEF8C4;
			position:fixed;
			width:100%;
			height:100%;
			top:0px;
			left:0px;
			z-index:1000;
		}
	</style>
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>"> 

	<div class="overlay">
		<!-- Title -->
		<div class="container">
			<div class="row">
				<h2 class="col-10 titlefont mt-4" >Helping Hand</h2>
				<hr style="width:100%", size="3", color=#B62D2D>  
				<h1 class="col-12">Login</h1>
			</div> <!-- .row -->
		</div> <!-- .container -->
		
		<!-- Login Form -->
		<div class="container">
			<div class="row">
				<div class="col-10">
					<form method="post" id="login-form" action="login" >
						<!-- USC Email -->
						<div class="form-group row">
							<label for="email" class="required col-10 col-form-label">USC Email:</label>
							<div class="col-10">
								<!-- Example Text -->
								<input type="text" name="email" class="form-control" placeholder="ttrojan@usc.edu" id="email" style="background-color:#FFFAE1">
							</div>
						</div> <!-- .form-group -->

						<!-- Password -->
						<div class="form-group row">
							<label for="password" class="required col-10 col-form-label">Password:</label>
							<div class="col-10">
								<!-- Example Text -->
								<input type="text" name="password" class="form-control" placeholder="********" id="password" style="background-color:#FFFAE1">
							</div>
						</div> <!-- .form-group -->

						<!-- Submit Button -->
						<div class="form-group row">
							<div class="col-10">
								<button type = "submit" name="login" value="true" id="loginButton" class="btn" style="background-color:#B62D2D; color:#FFFAE1" >Login</button>
								<a href="signup.jsp" class="btn" name="action" value="true" role="button" style="background-color:#B62D2D; color:#FFFAE1">Sign up</a>

							</br></br>
								<!-- need to add href to guest view of posting page -->
								<a href="posts" class="btn" role="button" style="background-color:#B62D2D; color:#FFFAE1">Continue as Guest</a>
							</div> <!-- .col -->
						</div> <!-- .form-group -->
					</form>
				</div> <!-- .col -->
			</div> <!-- .row -->
		</div> <!-- .container -->
	</div> <!-- .container-fluid -->
 	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- Possible Login Error -->
	 <c:if test="${not empty error}">
	    <script>
        alert("${error}");
	    </script>
	</c:if>
 	<link rel="stylesheet" href="alert/dist/sweetalert.css">
</body>
</html>

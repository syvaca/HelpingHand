<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Helping Hand Sign Up</title>
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
			width:100%;
			height:100%;
			top:0px;
			left:0px;
			z-index:1000;
		}
		/* Fix scrollbar */
		body {
		    overflow:auto;
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
				<h1 class="col-12">Sign Up</h1>
			</div> <!-- .row -->
		</div> <!-- .container -->

		<div class="container">
			<div class="row">
				<div class="col-10">
					<form method="post" id="signup-form" action="signup" action="confirmation.jsp">
						<!-- First Name -->
						<div class="form-group row">
							<label for="fname" class="required col-10 col-form-label">First Name:</label>
							<div class="col-10">
								<!-- Example Text -->
								<input type="text" name="fname" class="form-control" placeholder="Tommy" id="fname" style="background-color:#FFFAE1" required>
							</div>
						</div> <!-- .form-group -->
						
						<!-- Last Name -->
						<div class="form-group row">
							<label for="lname" class="required col-10 col-form-label">Last Name:</label>
							<div class="col-10">
								<!-- Example Text -->
								<input type="text" name="lname" class="form-control" placeholder="Trojan" id="lname" style="background-color:#FFFAE1" required>
							</div>
						</div> <!-- .form-group -->

						<!-- USC Email -->
						<div class="form-group row">
							<label for="email" class="required col-10 col-form-label">USC Email:</label>
							<div class="col-10">
								<!-- Example Text -->
								<input type="text" name="email" class="form-control" placeholder="ttrojan@usc.edu" id="email" pattern="[a-z0-9]+@usc.edu" style="background-color:#FFFAE1" required>
							</div>
						</div> <!-- .form-group -->
						
						<!-- USC Number -->
						<div class="form-group row">
							<label for="sid" class="required col-10 col-form-label">USC ID:</label>
							<div class="col-10">
								<!-- Example Text -->
								<input type="text" name="sid" class="form-control" placeholder="10101010" id="sid" style="background-color:#FFFAE1" required>
							</div>
						</div> <!-- .form-group -->

						<!-- Password -->
						<div class="form-group row">
							<label for="password" class="required col-10 col-form-label">Password:</label>
							<div class="col-10">
								<!-- Example Text -->
								<input type="text" name="password" class="form-control" placeholder="********" id="password" style="background-color:#FFFAE1" required >
							</div>
						</div> <!-- .form-group -->

						<!-- Phone Number -->
						<div class="form-group row">
							<label for="phone" class="required col-10 col-form-label">Phone Number:</label>
							<div class="col-10">
								<!-- Example Text -->
								<input type="text" name="phone" class="form-control" placeholder="000-000-0000" id="phone" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" style="background-color:#FFFAE1" required>
							</div>
						</div> <!-- .form-group -->

						<!-- Submit and Login Button -->
						<div class="form-group row">
							<div class="col-12">
								<button type="submit" name="action" value="true" class="btn" style="background-color:#B62D2D; color:#FFFAE1">Submit</button>
								<a href="login.jsp" class="btn" role="button" style="background-color:#B62D2D; color:#FFFAE1">Back to Login</a>
							</div> <!-- .col -->
						</div> <!-- .form-group -->

					</form>
				</div> <!-- .col -->
			</div> <!-- .row -->
		</div> <!-- .container -->
	</div> <!-- .container-fluid -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
			var status = document.getElementById("status").value;
			if(status=="success"){
				swal("Contrats", "Account Created Successfully","success");
			}else if(status =="failed"){
				swal("Sorry", "This USC email has been registered","error");
			}
	</script>
</body>
</html>
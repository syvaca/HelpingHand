<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Profile Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 	Bootstrap Link  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link href='https://fonts.googleapis.com/css?family=Shrikhand' rel='stylesheet'>
	
	<style>
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

	/*	  Profile Picture */
		.image-cropper {
			width: 100px;
			height: 100px;
			position: relative;
			overflow: hidden;
			border-radius: 50%;
		}

	/*	  Scroll */
		div.scroll {
			width: 100%;
			height: 400px;
			overflow: auto;
			text-align:justify;
			border-style: solid; 
			border-radius: 3px; 
			border-color:#B62D2D
		}

	/*      Styling for Required Asterick - Class */
		.required:after {
			content: " *";
			color: red;
		}

	/* The Modal (background) */
		.modal {
			display: none; 
			position: fixed;
			z-index: 1;
			left: 0;
			top: 0;
			width: 100%; 
			height: 100%;
			overflow: auto;
			background-color: rgb(0,0,0);
			background-color: rgba(0,0,0,0.4);
			padding-top: 20px;
		}		

	/* Modal Content/Box */
		.modal-content {
			background-color: #FEF8C4;
			margin: 5% auto 15% auto;
			border-radius: 5px;
			width: 80%;
		}

	/* The Close Button (x) */
		.close {
			position: absolute;
			right: 25px;
			top: 0;
			color: #000;
			font-size: 35px;
			font-weight: bold;
		}
		
		/* Button padding */
		.button-padding {
			padding-left: 10px;
		}

	.close:hover, .close:focus {
		cursor: pointer;
	}

	/* Add Zoom Animation */
	.animate {
	-webkit-animation: animatezoom 0.6s;
	animation: animatezoom 0.6s
	}

	@-webkit-keyframes animatezoom {
	from {-webkit-transform: scale(0)} 
	to {-webkit-transform: scale(1)}
	}
	
	@keyframes animatezoom {
	from {transform: scale(0)} 
	to {transform: scale(1)}
	}

	</style>
</head>
<body>
	<div class="overlay">
		<div class="container">
			<div class="row">
				<!-- Title -->
				<h2 class="col-10 titlefont mt-4" >Helping Hand</h2>
                <!-- Sign out button in top right corner-->
				<div class="col-2 mt-4">
					<form>
						<a href="signout" class="btn" role="button" style="background-color:#B62D2D; color:#FFFAE1">Sign Out</a>
					</form>
				</div>
				<!-- line -->
				<hr style="width:100%", size="3", color=#B62D2D> 
				<!-- profile picture -->
				<div class="col-12 text-center">
						<img src="https://www.w3schools.com/howto/img_avatar.png" class="image-cropper">
				</div>
				
				<h1 class="col-12 text-center mt-3">Welcome, ${name}</h1>
                <div class="col-12 text-center">${email} | ${phone}</div>
								
				<h4>Your Postings</h4>
				<!-- New Post Button -->
				<button id="add" class="btn" style="background-color:#B62D2D; color:#FFFAE1; display: block; margin-left: auto; margin-right: 0;" onclick="document.getElementById('addPage').style.display='block'">Create New Post</button>
				<form>
					<a href="posts" class="btn button-padding" role="button" style="background-color:#B62D2D; color:#FFFAE1">See All Posts</a>
				</form>
				<!-- List of all the user's postings -->
				<div class="scroll mt-2">
					<c:forEach var="job" items="${posts}">
					    <div class="col-12 mt-3">
	                        <div class="card">
	                            <div class="card-body" style="background-color:#FFFAE1;">
	                                <h5 class="card-title">Job #${job.id} | Offer: $${job.cost}</h5>
	                                <p class="card-text">${job.description}</p>
	                                <div> 
	                                	<a>This post is tagged with:</a>
								       <c:if test = "${job.tagAssembly == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#333C83; color:#FFFAE1" >#Assembly</button>
										</c:if>
										<c:if test = "${job.tagCleaning == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#FDAF75; color:#FFFAE1" >#Cleaning</button>
										</c:if>
										<c:if test = "${job.tagStorage == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#6BCB77; color:#FFFAE1" >#Storage</button>
										</c:if>
										<c:if test = "${job.tagRepair == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#EF2F88; color:#FFFAE1" >#Repair</button>
										</c:if>
										<c:if test = "${job.tagPetCare == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#23049D; color:#FFFAE1" >#Pet Care</button>
										</c:if>
										<c:if test = "${job.tagChildCare == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#59886B; color:#FFFAE1" >#Child Care</button>
										</c:if>
										<c:if test = "${job.tagMealPrep == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#B590CA; color:#FFFAE1" >#Meal Prep</button>
										</c:if>
										<c:if test = "${job.tagITHelp == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#6D3580; color:#FFFAE1" >#IT Help</button>
										</c:if>
										<c:if test = "${job.tagBooks == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#7F5283; color:#FFFAE1" >#Books</button>
										</c:if>
										<c:if test = "${job.tagSublets == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#630A10; color:#FFFAE1" >#Sublets</button>
										</c:if>
										<c:if test = "${job.tagOther == true}">
										    <button id="delete"  class="btn btn-tag" style="background-color:#F473B9; color:#FFFAE1" >#Other</button>
										</c:if>
										<a href="delete?id=${job.id}">
											<button id="delete" class="btn btn-tag" style="background-color:#B62D2D; color:#FFFFFF" >Delete Post</button>
										</a>
	                               	</div>
	                            </div>
	                        </div>
                    	</div>
					</c:forEach>

				</div>

				<!-- The Modal -->
				<div id="addPage" class="modal">
				
										<form class="modal-content animate" action="/action_page.php" method="post">
					  <div style="margin: 12px 0 12px 0;position: relative;">
						<span onclick="document.getElementById('addPage').style.display='none'" class="close" title="Close Modal">&times;</span>
					  </div>
				  
					  <!-- Message Text Area -->
					  <div style="padding: 50px;">
						<label for="description" class="required col-2 col-form-label">Description:</label>
						<div class="col-10">
						  <!-- Message Textarea Field -->
						  <textarea id="formDescription" name="message" class="form-control" id="message" rows="3" placeholder="Add a description for your job positing" style="background-color:#FFFAE1"></textarea>  
						</div>
					  </div>
					  
					  <!--  Price  -->
					  <div style="padding: 50px;">
						<label for="price" class="required col-2 col-form-label">Price:</label>
						<div class="col-10">
						  <!-- Message Textarea Field -->
						  <input id="formPrice" name="message" class="form-control" id="message"  type="number" style="background-color:#FFFAE1">  
						</div>
					  </div>
					  
					  	<!-- form element for the various tags in a dropdown box -->
						<div class="form-group row ">
						    <label for="tag" class="required col-2 col-form-label" style="margin-left:70px;">Tag Post With:</label>
						</div> <!-- .form-group -->
						
						<div class="form-group row">
							<div class="col-10">
						        <select id="formTag" name="tag" class="form-control" style="background-color:#FFFAE1; margin-left:70px;">
						            <option value="assembly_tag">Assembly</option>
						            <option value="cleaning_tag">Cleaning</option>
						            <option value="storage_tag">Storage</option>
						            <option value="repair_tag">Repair</option>
						            <option value="petCare_Tag">Pet Care</option>
						            <option value="childCare_Tag">Child Care</option>
						            <option value="mealPrep_Tag">Meal Prep</option>
						            <option value="ITHelp_Tag">IT Help</option>
						            <option value="books_tag">Books</option>
						            <option value="sublets_tag">Sublets</option>
						            <option value="other_tag">Other</option>
						        </select>
						    </div>
						</div>
				  
						<!-- Submit Button -->
						<div style="padding: 16px; padding-bottom: 20px;" class="col-10">
							<a onclick="performSearch()" class="btn" name="action" value="true" role="button" style="background-color:#B62D2D; color:#FFFAE1; margin-left:55px; padding-top: 10px;">Submit</a>
						</div>

					</form>
				</div>
				

			</div> <!-- .row -->
		</div> <!-- .container -->
	</div> <!-- .container-fluid -->

	<script type="text/javascript">
		document.getElementById("delete").onclick = function () {
			// location.href = "postings_page.html";
			// delete posting from database
		};

		// Get the modal
		var modal = document.getElementById('addPage');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
		
	    // Performing search
	    function performSearch() {
	        // string from formTitle input
	        var formTitle = "ignore";
	        // string from formDescription textarea
	        var formDescription = document.getElementById("formDescription").value;
	        // and value from select formTag
	        var formTag = document.getElementById("formTag").value;
	        // price 
	        var formPrice = document.getElementById("formPrice").value;
	        // get the current url and truncate it to the base url
	        var url = window.location.href;
	        url = url.substring(0, url.indexOf('/'));
	        // redirect with data to /Search
	        window.location.href = url + '/post?title=' + formTitle + '&description=' + formDescription + '&tag=' + formTag + "&price=" + formPrice;
	    }
	</script>

</body>
</html>
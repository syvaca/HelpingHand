<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Postings Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Include Twitter Bootstrap and jQuery: -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
	
	<!-- 	Bootstrap Link  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	
	<!--  Font -->
	<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Shrikhand'>

    <!-- jQuery CDN - Full version (=withAJAX) -->
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    
    <!-- Popper.JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
    
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    
	<!-- Dropdown plugin's CSS and JS: -->
    <script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>

	<style>
	    /* Title Font */
		.titlefont {
            font-family: 'Shrikhand'; 
			font-size: 30px;
			color:#B62D2D;
        }  

        .wrapper {
            background-color:#FEF8C4;
            display: flex;
            width: 100%;
            align-items: stretch;
        }

        /* Sidebar */
        .sidebarHeader {
            padding-top: 8%;
        }
        #sidebar {
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            z-index: 999;
            background: #FEF8C4;
            transition: all 0.3s;
            display: flex;
            flex-direction: column;
            /* align-self: stretch; */
            box-shadow: rgba(112, 118, 124, 0.277) 0px 8px 24px;
        }
        #sidebar.active {
            margin-left: -250px;
        }
        /* Sidebar Dropdowns */
         /* Dropdown Button */
        .dropbtn {
            background-color: #FEF8C4;
            box-shadow: rgba(112, 118, 124, 0.277) 0px 8px 24px;
            width: 80%;
            font-size: 16px;
            border-style: solid;
            border-color: black;
            border-width: thin;
            cursor: pointer;
        }
        /* Dropdown button on hover & focus */
        .dropbtn:hover, .dropbtn:focus {
            background-color: #302f2548;
        }
        /* The container <div> - needed to position the dropdown content */
        /* .dropdown {
            position: relative;
            display: inline-block;
        } */
        /* Dropdown Content (Hidden by Default) */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #FFFAE1;
            min-width: 160px;
            z-index: 1;
        }
        /* Links inside the dropdown */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        /* Change color of dropdown links on hover */
        .dropdown-content a:hover {
            background-color: #ddd;
        }
        /* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
        .show {
            display:block;
        }
        

        /* Custom Select */
         /* The container must be positioned relative: */
        /*the container must be positioned relative:*/
        .custom-select {
        position: relative;
        }

        .custom-select select {
        display: none; /*hide original SELECT element:*/
        }

        .select-selected {
        background-color: DodgerBlue;
        }

        /*style the arrow inside the select element:*/
        .select-selected:after {
            position: absolute;
            content: "";
            top: 14px;
            right: 10px;
            width: 0;
            height: 0;
            border: 6px solid transparent;
            border-color: #fff transparent transparent transparent;
        }

        /*point the arrow upwards when the select box is open (active):*/
        .select-selected.select-arrow-active:after {
            border-color: transparent transparent #fff transparent;
            top: 7px;
        }

        /*style the items (options), including the selected item:*/
        .select-items div,.select-selected {
        color: #ffffff;
        padding: 8px 16px;
        border: 1px solid transparent;
        border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
        cursor: pointer;
        user-select: none;
        }

        /*style items (options):*/
        .select-items {
        position: absolute;
        background-color: DodgerBlue;
        top: 100%;
        left: 0;
        right: 0;
        z-index: 99;
        }

        /*hide the items when the select box is closed:*/
        .select-hide {
        display: none;
        }

        .select-items div:hover, .same-as-selected {
        background-color: rgba(0, 0, 0, 0.1);
        }
        
        .btn-tag:hover {
		    cursor: default;
		}


        /* Post Job Button */
        #postJobContainer {
            padding-top: 8%;
        }
        #postJobBtn {
            width: 100%;
        }
        
        /* Content */
        #content {
            background: #FEF8C4;
            width: calc(100% - 250px);
            padding: 40px;
            min-height: 100vh;
            transition: all 0.3s;
            position: absolute;
            top: 0;
            right: 0;
        }
        #content.active {
            width: 100%;
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
        .close:hover, .close:focus {
            cursor: pointer;
        }


        /* Red Buttons */
        .red-btn {
            background-color:#B62D2D; 
            color:#FFFAE1
        }


        /* Add Zoom Animation */
        .animate {
            -webkit-animation: animatezoom 0.6s;
            animation: animatezoom 0.6s
        }


        /* Responsive Design */
        @media (max-width: 768px) {
            #sidebar {
                width: 175px;
                margin-left: -175px;
            }
            #sidebar.active {
                margin-left: 0;
            }
            #content {
                width: 100%;
            }
            #content.active {
                width: calc(100% - 175px);
            }
            #sidebarCollapse span {
                display: none;
            }
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
	<div class="container-fluid">
		<div class="wrapper">
            <!-- Sidebar -->
            <!-- from: https://bootstrapious.com/p/bootstrap-sidebar -->
            
            <nav id="sidebar">
                <div class="col-10">
                    <!-- Filter Dropdown -->
                    <!-- <h3 class="sidebarHeader">Filter by</h3>
                    <div class="dropdown">
                        <!-- <button onclick="dropdown_click(filterByBtn)" class="dropbtn">Select One</button>
                        <div class="myDropdown dropdown-content" id="filterByBtn">
                          <a href="#">Ascending</a>
                          <a href="#">Descending</a>
                        </div> 

                        <select id="date" multiple="multiple">
                            <option value="ascending">Ascending</option>
                            <option value="descending">Descending</option>
                        </select>

                        <small id="filterBy-error" class="form-text text-danger"></small>
                    </div> -->


                    <!-- Search Dropdown -->
                    <h3 class="sidebarHeader">Filter by</h3>
                    <div class="dropdown">

			             <select id="example-getting-started" multiple="multiple">
			                <option value="assembly">Assembly</option>
			                <option value="cleaning">Cleaning</option>
			                <option value="storage">Storage</option>
			                <option value="repair">Repair</option>
			                <option value="pet_care">Pet Care</option>
			                <option value="child_care">Child Care</option>
			                <option value="meal_prep">Meal Prep</option>
			                <option value="it_help">IT Help</option>
			                <option value="books">Books</option>
			                <option value="sublets_tag">Sublets</option>
			                <option value="other">Other</option>
			            </select>

                        <small id="searchBy-error" class="form-text text-danger"></small>
                    </div>
                    
                	<!--  Search Button -->
                	<a class="btn" role="button" style="background-color:#B62D2D; color:#FFFAE1" onclick="performSearch()">Apply Filters</a>
                    
                </div>
               

                
				<!--  Posting Page That Redirects to Profile -->
                <div class="col-10" id="postJobContainer">
                	<form action="profile">
                    	<input type="submit" id="postJobBtn" class="btn red-btn" value="Post Job">
                    </form>
                </div> 
            </nav>

            <div id="content">
                <div class="row">
                    <!-- Title -->
                    <h2 class="col-10 titlefont mt-4">Helping Hand</h2>
    
                    <!-- NavBar -->
                    <!-- Sign out button in top right corner-->
                    <div class="col-2 mt-4">
                        <form>                      
                            <c:choose>
							    <c:when test="${not empty email}">
							    	<!--  Sign Out  -->
							       <a href="signout" class="btn red-btn">Signout</a>
							    </c:when>    
							    <c:otherwise>
							       <!--  Sign In  -->
							       <a href="login" class="btn red-btn">Login</a>
							    </c:otherwise>
							</c:choose>     
                        </form>
                        <form>                      
                            <c:choose>
							    <c:when test="${not empty email}">
							    	<!--  Sign Out  -->
							       <a href="profile" class="btn red-btn">Profile</a>
							    </c:when>    
							    <c:otherwise>
							    	<!--  nothing  -->
							    </c:otherwise>
							</c:choose>     
                        </form>
                    </div>
                    <!-- line -->
                    <hr style="width:100%", size="3", color=#B62D2D> 
                </div>

                <!-- Sidebar Toggle -->                    
                <button type="button" id="sidebarCollapse" class="btn red-btn">Toggle Sidebar</button>
                
                <!-- List of all the user's postings -->
                <div class="scroll mt-2">
                
                	<h3> Search Results: </h3>

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
										<c:choose>
										    <c:when test="${not empty email}">
										    	<!--  Sign Out  -->
										       <a href="contact?sid=${job.user}" class="btn red-btn">Contact</a>
										    </c:when>    
										    <c:otherwise>
										    	<!--  empty  -->						    
										    </c:otherwise>
										</c:choose>     
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
                
                        <!-- Full Name -->
                        <div style="padding: 50px;" >
                        <label for="posting" class="required col-2 col-form-label">Posting Title</label>
                        <div class="col-10">
                            <!-- Example Text -->
                            <input type="title" name="posting" class="form-control" placeholder="Enter Posting Title" style="background-color:#FFFAE1">
                        </div>
                        </div> <!-- .form-group -->
                    
                        <!-- Message Text Area -->
                        <div style="padding: 50px;">
                        <label for="description" class="required col-2 col-form-label">Description:</label>
                        <div class="col-10">
                            <!-- Message Textarea Field -->
                            <textarea name="message" class="form-control" id="message" rows="3" placeholder="Add a description for your job positing" style="background-color:#FFFAE1"></textarea>  
                        </div>
                        </div>
                    
                        <!-- Submit Button -->
                        <div style="padding: 16px" class="col-10">
                            <a href="profile_page.html" class="btn" role="button" style="background-color:#B62D2D; color:#FFFAE1">Submit</a>
                        </div>

                    </form>
                </div>
            </div> <!-- .content -->
		</div> <!-- .wrapper -->
	</div> <!-- .container-fluid -->

	<script type="text/javascript">

		// Get the modal
		var modal = document.getElementById('addPage');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}

        // Sidebar Collapse
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar, #content').toggleClass('active');
                $('.collapse.in').toggleClass('in');
                $('a[aria-expanded=true]').attr('aria-expanded', 'false');
            });

        });
        
        // Performing search
		function performSearch() {
		    // tag is a single string
		    var tag = $('#example-getting-started').val();
		    // search is a boolean
		    var search = true;
		    // get the current url and truncate it to the base url
		    var url = window.location.href;
		    url = url.substring(0, url.lastIndexOf('/'));
		    // redirect with data to /Search
		    window.location.href = url + '/search?tag=' + tag + '&search=' + search;
		}
        
    </script>


</body>
</html>
    
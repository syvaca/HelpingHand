<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<style>
	body {
	  background-color: #FEF8C4;
	}
</style>
</head>
<body>

</body>
 <c:if test="${not empty alert}">
    <script>
       alert("${alert}");
       // get the current url and truncate it to the base url
	    var url = window.location.href;
	    url = url.substring(0, url.lastIndexOf('/'));
	    // redirect with data to /Search
	    window.location.href = url + '/posts';
    </script>
</c:if>

</html>
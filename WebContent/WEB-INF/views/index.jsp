<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Great Number Game</title>
</head>
<body>
	<h1> Welcome to the Great Number Game</h1>
	
<!-- 
<h3>Number is <c:out value ="${num}"/></h3> 
-->
	<h3>Guessed: <c:out value ="${guessed}"/></h3>
	<h3>Number of guesses: <c:out value ="${cnt}"/> / 5</h3>
	
	<h4>I am thinking of a number between 1 and 100!</h4>

	    <c:if test="${cnt=='0'}">
	    	<form action="" >
	    	Choose a custom random number<br>
			Lower:<input name="lower" type = "number" > <br>
	    	Upper:<input name="upper" type = "number" min ="1"> <br>
	    	<button type ="submit">Choose range</button>
	    	</form>
	    	<br>
	    </c:if>
	    <c:if test="${guessed=='false' && lost=='false'}">
		<form action="" method ="post">
			<input name ="playerguess" type="number">
			<button>Submit</button>
		</form>
	    </c:if>
	    <c:if test="${lost=='true'}">
	     Sorry, you've lost! The number was <c:out value ="${num}"/>
	    </c:if>	    
	    <c:if test="${guessed=='true'}">
	     Nice Job! The number was <c:out value ="${num}"/>
	    </c:if>	    
		
	    <c:if test="${guessed=='true' || lost=='true'}">
		<form action="" method="post">
			<button name ="reset" type="submit">Reset</button>
		</form>
	    </c:if>
</body>
</html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Select the President</title>
</head>
<body>
	<h1>Select the President That You Want to Learn More About:</h1>
	<p>This should say George: ${president.firstName}</p>
	<form action="PresDisplay" method="POST">
	<input type="submit" value="test">
	</form>
</body>
</html>
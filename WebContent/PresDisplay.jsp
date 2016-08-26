<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Select the President</title>
</head>
<body>
	<h1>Select the President That You Want to Learn More About:</h1>
		<p>This should say all info: ${president}</p>
		
		<form action="PresDisplay" method="POST">
		<p>Enter the term number here (two digit number): 
			<input type="text" name="termSelect" placeholder="enter term number"></p><br>
		<input type="submit" name="search" value="Term Lookup">
		<p><input type="submit" name="search" value="previous"><input type="submit" name="search" value="next"></p><br>
		</form>
</body>
</html>
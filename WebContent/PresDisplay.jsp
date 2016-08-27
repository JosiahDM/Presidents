<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Select the President</title>
</head>
<body>
	<h1 align=center>Select the President That You Want to Learn More About:</h1>
		<p align=center><strong>President Info: ${president}</strong></p>
		<p align=center><img src="/PresidentsWeb${president.image}" alt="test" width=200px></p>
		<p align=center>${president.funFact}</p>
		<form action="PresDisplay" method="POST">
		<p>Enter the term number here (two digit number): 
			<input type="text" name="termSelect" placeholder="enter term number">
		<input type="submit" name="search" value="Term Lookup"></p><br>
		<p><input type="submit" name="search" value="previous"><input type="submit" name="search" value="next"></p><br>
		</form>
</body>
</html>
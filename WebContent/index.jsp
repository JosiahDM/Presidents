<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Presidents</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<div class="flex-container">
<header>
  <h1>Presidential Information Gallery</h1>
</header>

<ul>
  <li><div method="GET" action="PresDisplay"><a class="active" href="index.jsp">Home</a></div></li>
  <li class="dropdown">
    <a href="javascript:void(0)" class="dropbtn" onclick="myFunction()">Presidents</a>
    <div class="dropdown-content" id="myDropdown">
      <a href="PresDisplay.jsp">Viewing Gallery</a>
      <a href="https://en.wikipedia.org/wiki/List_of_Presidents_of_the_United_States">Wikipedia - List of Presidents</a>

    </div>
  </li>
</ul>

<script>

function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(e) {
  if (!e.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    for (var d = 0; d < dropdowns.length; d++) {
      var openDropdown = dropdowns[d];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

</script>

	<div id="splashBackground">
	<form action="PresDisplay" method="GET">
	<p align=center id="searchString">Enter a President's term number here to start:

		<input type="text" name="termSelect" placeholder="Enter term number">
		<input id="searchButton" type="submit" name="search" value="Term Lookup"></p><br>
	</form>
	<!-- <p align=center><img id="declaration" src="img/hr-flag.jpg"></p> -->
	<br><br><br><br><br><br><br></div>
<footer>Copyright © Josiah Moye and Steve Nagle</footer>
</div>
</body>
</html>

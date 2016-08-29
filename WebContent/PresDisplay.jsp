<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Select the President</title>

<link rel="stylesheet" type="text/css" href="resultspage.css">

</head>
<body>

<div class="flex-container">
    <header>
      <h1>Presidential Information Gallery</h1>
    </header>

    <ul>
      <li><a class="active" href="index.html">Home</a></li>
      <li class="dropdown">
        <a href="javascript:void(0)" class="dropbtn" onclick="myFunction()">Presidents</a>
        <div class="dropdown-content" id="myDropdown">
          <a href="PresDisplay.jsp">Viewing Gallery</a>
          <a href="https://en.wikipedia.org/wiki/List_of_Presidents_of_the_United_States">Wikipedia - List of Presidents</a>

        </div>
      </li>
      <li><a href="about.html">About Us</a></li>
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

    <div class="contentArea">
        <div class="transparent">

    		<p align=center>
                <c:choose>
					<c:when test="${president.termNumber != 0}">
						<h1 id="textFilter">#${president.termNumber} ${president.firstName} ${president.middleName} ${president.lastName}</h1>
                        <h3 id="textFilter" align=center>${president.startYear}-${president.endYear}</h3>
                        <p align=center><img src="/PresidentsWeb${president.image}" alt="test" width=200px></p>
                        <p id="textFilter" align=center><strong>Fun Fact:</strong> ${president.funFact}</p>
                    </c:when>
                    <c:otherwise>
                        <p align=center><img src="/PresidentsWeb${president.image}" alt="test" width=200px></p>
                        <p id="textFilter" align=center><strong>${president.funFact}</strong></p>
                    </c:otherwise>
				</c:choose>
            </p><br>

    		<form action="PresDisplay" method="POST">
    			<input id="textInput" type="text" name="termSelect" placeholder="Enter Term Number Here"><br><br><br>
        		<input class="searchButton" type="submit" name="search" value="Term Lookup"></p><br>
    		    <input class="button" type="submit" name="search" value="<">
                <input class="button" type="submit" name="search" value=">">
    		</form>

        </div>
    </div>
    <footer>Copyright © Josiah Moye and Steve Nagle</footer>

</div>
</body>
</html>

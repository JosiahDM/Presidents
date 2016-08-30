# Presidents

This is the week 6 project at Skill Distillery.

This project was made after a week of CSS/HTML and Java Servlet programming. No in-depth Javascript or database development has been covered at this time.

## Directions
- Go to the main page at: http://52.88.199.131:8080/PresidentsWeb/index.html
- Enter the term number in the text box or click "Viewing Gallery" under the Presidents tab on the navbar.
- Select the left/right arrow key to navigate through the list, or enter a specific president's term number to directly search for that president.

## Features
- Displays basic presidential information, along with a Fun Fact about each president. 
- Some basic CSS to (somewhat) prettify buttons and input boxes.
- Next/Previous buttons will loop around if user reaches beginning or end of presidents list.
- Input validation on the text box--invalid entry will display an error message without breaking the program.

## Bugs/Challenges
- Clicking "Viewing Gallery" will take you to president #1, but clicking next/previous after that will continue from wherever the session was stored. We need to close the session when user navigates away from the PresDisplay page. This was one of the main challenges of the project--linking the requests to the correct session/servlet. 
- UI could be a bit smoother and more cohesive. 

### Collaborators:
Josiah Moye and Steve Nagle


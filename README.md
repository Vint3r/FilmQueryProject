# Film Query Data Base
---
A weekend project I completed for Skill Distillery, a Java coding bootcamp.

### Overview
---
This program simulates a film data base where users can look up lists of films and actors via a number of different ways. Users have the option to lookup data by id numbers or a key word search.  The id lookup is basic and just query's the database for that id.  Using the key word search will check if the word shows up in either the title or the description of the movie. If the movie is not found in the data base it displays a short message stating so and returns them to the main menu to try another search. If a movie / actor is found however, it presents them with another sub menu asking what they would like to view from it, such as all information on the given film / actor or simply to return to the previous menu.

### Technologies and Topics
---
The program is made up of 5 classes:
* Two "storage" classes:
  * The actor class which stores and retrieves all the information for the actors used in the program.
  * the film class which is responsible for the same as the actors but for films.
* The main application which is used for the entire user interface and is responsible for calling the right methods from the other classes to display the correct   information.
* A single interface to act as a blue print for the next class on the list and for possible future use by other classes.
* Finally, a data base accessor class that is responsible for opening and closing connections to the data base as well as retrieving information out of the data base.

Most of the user interface is made up of a different method for each menu system as well as extra method calls to actually sort through the lists and to check for actual data inside the objects to decide what to display to the user. All user input is taken in as a String object to make it a bit easier to check for valid user input.  This is done by surrounding it with a try catch block to verify the input. After it takes in the user input it calls the DAO class to pull info from the data base to display to the user using standard sql commands wrapped in a Prepared Statement query to help prevent things like SQL injection. After information is retrieved from the data base it displays to the user and also puts them into a sub menu. The sub menu prompts the user for how they would like to proceed.  They can display more info for the given object that was retrieved, be it an actor object or a film object. After the user is finished with the given object, they may return to the main menu to query for a new object or to just exit the program.

### Lessons Learned
---
The biggest hurdle for me in this project was getting the sql commands to work correctly, or at least grab the information I was looking for. I especially had issues when it came to more complicated commands like the one I wrote for finding a film by actor id. It was supposed to find all information for a film, find the languages available for the movies, along with still finding all the info for the actors. Other than that really the next biggest problem I had was just running into issues having too many connections open to the data base which would crash the program. This was caused by a method I created to setup the database connections to be passed off to other methods as needed.  I did this to try and save myself some time to avoid rewriting code over and over, but unfortunately it would not fully close the connection like normal when using the .close call.  I ended up removing the method and placing the database connection into the individual methods that needed to make calls.  I will continue to work on this as I would like to find a way to make this work.  I did some research and found a lot of information on using connection pools to address this.  Unfortunately I ran out of time and was unable to get this working.

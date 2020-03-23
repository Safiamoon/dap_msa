# Data Access Project 

- Creation of a client-server application (you can find the client part in this repository : (https://github.com/Safiamoon/Client)) as part of the house of code Lyon training. 
- This project uses Google APIs (GMAIL, CALENDAR) and will read unread emails, the list of labels in GMAIL, the next event in CALENDAR. It was built using Spring and Maven.

## To use this Google API project : 

 - Create a folder Dap in your user.home
 - Go to https://console.developers.google.com, activate the GMAIL & GOOLE CALENDAR APIS
 - In API & Services, Identifiers, click on create identifiers OAuth CLientID, then choose web application
 - Select add a URI. You must then indicate the following URI: "http: // localhost: 8080 / oAuth2Callback"
 - Finally click on save.
 - Then download this newly created identifier (on the identifier line on the far right)
 - Rename the download identifier as follows: 'credentials.json' and place it in the Dap folder (but not in the tokens   folder)
 - You are normally good at starting the server.
 
## The launch of the application

- To ad a new user : http://localhost/user/add?name=suzy
- To see the list of the users that you added go to : http://localhost/user/all
- To add an affiliate account to a user go to : http://localhost/account/add/{userId} = http://localhost :8080/account/add/1. You should have a Google page asking you to enter your Google credentials.
- Now once you are connected to a google account, you can see the number of unread emails and next event for example with the follwing url :
http://localhost:8080/Email/Unread?userKey=suzy
http://localhost:8081/event/next?userKey=suzy

 
 



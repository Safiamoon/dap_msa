# Data Access Project 

- Creation of a client-server application (you can find the client part in this repository : (https://github.com/Safiamoon/Client)) as part of "House Of Code"'s training. 
- This project uses Google APIs (GMAIL, CALENDAR) and will read unread emails, the list of labels in GMAIL, the next event in CALENDAR. It was built using Spring and Maven.

## To use this Google API project : 

#### API KEY

 - Create a folder Dap in your user.home
 - Go to https://console.developers.google.com, activate the GMAIL & GOOLE CALENDAR APIS
 - In API & Services, Identifiers, click on create identifiers OAuth CLientID, then choose web application
 - Select add a URI. You must then indicate the following URI: "http://localhost:8081/oAuth2Callback" 
 - Finally click on save.
 - Then download this newly created identifier (on the identifier line on the far right)
 - Rename the download identifier as follows: 'credentials.json' and place it in the Dap folder (but not in the tokens   folder)
 - You are normally good at starting the server.
 
#### Data Base 

- You can use whatever you want as a DB creation tool, in my case, I used MySql Workbench.
- Create a DataBase schema with the name that you want.
- In application.properties, you can add the following code (If you use MySql Workbench) : 

 ``` java
 # ===============================
# = DATA SOURCE
# ===============================

# Set here configurations for the database connection

# Connection url for the database "netgloo_blog"
spring.datasource.url = jdbc:mysql://localhost:[port of your DataBase]/[the name of your schema]

# Username and password
spring.datasource.username = [your username]
spring.datasource.password = [your password]

# ===============================
# = JPA / HIBERNATE
# ===============================

# Use spring.jpa.properties.* for Hibernate native properties (the prefix is
# stripped before adding them to the entity manager).

# Show or not log for each sql query
spring.jpa.show-sql = true

# Hibernate ddl auto (create, create-drop, update): with "update" the database
# schema will be automatically updated accordingly to java entities found in
# the project
spring.jpa.hibernate.ddl-auto = update

# Naming strategy
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy

# Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

server.port=[your server port]

```

  
 

 
## The launch of the application

- To add a new user : http://localhost:8081/user/add?name=suzy
- To see the list of the users that you added go to : http://localhost:8081/user/all
- To add an affiliate account to a user go to : http://localhost:8081/account/add/{userId}, for example http://localhost:8081/account/add/1. You should have a Google page asking you to enter your Google credentials.
- Now once you are connected to a google account, you can see the number of unread emails and next event for example with the following URIS :
http://localhost:8081/Email/Unread?userKey=suzy
http://localhost:8081/event/next?userKey=suzy
- You can also see your emails's labels using : http://localhost:8081/Email/Label

### P.S : 

- Use your OWN server port (For me as an example, it was 8081)
- Also, replace "suzy" by the name that you want.

 
 



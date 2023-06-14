# Introduction
The application is a small lightweight SpringBoot based java application for sending scheduled emails to preset email accounts.

# System Requirements
- Java 8
- PostgreSQL

# Usage
- Create database named GoglotekEmail in postgreSQL.
- Edit application.properties file and update the database connection settings.
- Gradle build the project.
- Run the generated jar file "java -jar xxx.jar". Database tables will be automatically created in the database.
- Update the database with company, email accounts and template data.
- Schedule the running of jar file using windows task scheduler or any task scheduler of your choice.
- You can add spring scheduler code to the application to schedule the sending of emails internally.

# Conclusion
We do not offer any support for the application since it's a free software. However we can offer support at a fee.
If you like the application then kindly recommend us Goglotek LTD or Robert Moi for any software related work. You can contact me via email address robmoi2010@gmail.com but only for paid support or job recommendation.
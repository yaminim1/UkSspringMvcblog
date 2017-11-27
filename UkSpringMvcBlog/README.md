## UKy Spring MVC Blog


##Aim:##
 To create a simple forum/blog to post Articles and add comments to it. 
Application uses spring Security to manage the users who can access the articles.  
Each user may post an Article   
User may edit/delete their own posts  
Each may see all articles posted by other users.  
Each may read all messages posted related to the article.  
Administrator can approve/reject articles and comments.  
Simple search can be performed. The application searches the keyword in title and description of the article and returns the articles.  

#Technologies Used:#

* Spring mvc
* Spring Security  
* Hibernate 
* MYSQL  
* Apache Tomcat  
* Maven
* Java  
* BootStrap  

##Project Structure:##
The project is implemented using Spring MVC and Hibernate. 
Application.properties- src/main/resources  
Table.sql-contains all the sql statements required for the application.  
ArticleController,ReplyController has the rest API calls.  
ArticleDaoImpl has the implemented functions.  
SecurityConfiguration.java has the spring security implementation.
#Project Dependency:#  
The project dependencies are present in pom.xml. All the jars required for the project are taken care by maven. Pom.xml has dependencies related to spring MVC, Hibernate, spring Security, mysql connector and others.  
#Tables:#
The required data and tables can be created by executing the queries present in table.sql  
* User – Contains all the user details  
* Role – Contains 2 roles Admin and User  
* User_role – Maps the users with different roles  
* Article – Article table has all the article data like title, description, createdBy, createdDate, reply_count, reply Flag, verify flag, del flag,etc.  
*Reply – This table contains data related to the comments.    
The User table contains two users with role admin and user.    
	Role Admin (id/pwd) – admin/admin  
	Role User (id/pwd) – yamini/yamini  
#Configuration:#  
applicationContext.xml has the DB related details.   
The db details can be changed and the url need to be set according to the mysql setup.  

#Application Demo:#   
Go to the github path and download the zip file and import it as a maven project in your eclipse.  
Modify applicationContext.xml file with correct url, username, password for the MySQL database.  
Create the required data and tables by running the commands present in tables.sql.  
Right click on the project and go to maven->Update Project.  
Right click on the project and click run on server or create a war file and deploy it in Apache Tomcat Server.  
Go to http://localhost:8080/UkSpringMvcBlog/login



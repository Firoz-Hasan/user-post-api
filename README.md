# User-Post-API
Spring boot applicaiton for crud operation

This is a demo application which demonstrates basic crud operation with RESTful web services, Spring boot, Spring MVC, JPA, Connect to MYSQL db, Spring Security  Sign up, Sign in, JSON web token (JWT) based authentication. 

The basic architecture of this application is given below. 
![alt text](
https://github.com/FirozHasan007/user-post-api/blob/master/High-Level-Design.png
)


The EER model diagram as follows 

![alt text](
https://github.com/FirozHasan007/user-post-api/blob/master/mydb.png
)

The diagram for Authentication Manager which is responsible for validate authenticaiton from db.
![alt text](
https://github.com/FirozHasan007/user-post-api/blob/master/authmanager.png
)
To make best use of spring boot application, it's mandatory to understand following concepts

1) Optional : Return type Optional is a new container type that wraps a single value. It is used 
    to represent a value is present or absent. The main advantage of this new construct is that 
    No more too many null checks and NullPointerException
2) @RepositoryRestResource(exported = false) hal disable kore
3) JsonBackReference
   	 * This annotation basically says that posts will not be part of the JSON
   	 * returned for tag (but each post will contain list of its tags in the
   	 * response). If the post was a part of the list then the program would fetch
   	 * the post's tags, which would then make the program fetch the post again
   	 * and again until we’d get a StackOverflowException.
   	 
4) @Service : hold the business logic and call method in repository layer
5) @Bean : create instance of object 
6) @Autowire : This is a dependency, I want actual object/instance, Pls create dependency for me.
7) @Component: To make spring annotonation aware that I may need object of this class.
    It's kinda like "I may need object of this class"
8) CSRF - Cross-Site Request Forgery vulnerability allows an attacker to force 
    a logged-in user to perform an important action without their consent or knowledge.
9) @Restcontroller - response body + controller --> return JSON/XML
10) @controller - returns mapping AKA view as html/jsp
11) JWT - json web token - used for api authentication 
12) @Repository - This is to indicate that the class defines a data repository.
13) ResponseEntity is meant to represent the entire HTTP response. 
    You can control anything that goes into it: status code, headers, and body.
14) @ResponseBody is a marker for the HTTP response body and @ResponseStatus declares the 
    status code of the HTTP response.
15) Cascading - Entity relationships often depend on the existence of another entity — for example, 
    the Person–Address relationship. Without the Person, the Address entity doesn't have any meaning of 
    its own. When we delete the Person entity, our Address entity should also get deleted.        
    Cascading is the way to achieve this. When we perform some action on the target entity, 
    the same action will be applied to the associated entity.
17) DTO is an abbreviation for Data Transfer Object, 
    so it is used to transfer the data between classes and modules of your application. 
    DTO should only contain private fields for your data, getters, setters and constructors. 
    It is not recommended to add business logic methods to such classes, but it is OK to add 
    some util methods. 
18) DAO is an abbreviation for Data Access Object, 
    so it should encapsulate the logic for retrieving, saving and updating data in your
    data storage (a database, a file-system, whatever).
19) Dao uses Spring JPA to perform DB operations whereas DaoImpl has implementation with 
    Hibernate for performing DB operations.


Procedure to test APIs
1- for SignUp (post)
 url - http://localhost:8083/api/auth/signup
 in the body 
 {
 "username" : "hos",
 "email": "hosgmail@sdf.com",
 "password" : "aaaaaa",
 "role" : ["admin"]
 }
 
 2- for SignIn (post)
 url - localhost:8083/api/auth/signin
 in the body 
 {
 "username" : "hos",
 "password" : "aaaaaa"
 }
 
 3- for getting lists of users/pages/posts/tags (Get)
 url - http://localhost:8083/users
 url - http://localhost:8083/pages
 url - http://localhost:8083/posts
 url - http://localhost:8083/tags
 
 Dont forget to add jwt token in the authorization header before calling any API request which you will get it during SignIn process
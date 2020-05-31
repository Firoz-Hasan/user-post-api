# user-post-api
Spring boot applicaiton for crud operation

1) Optional : Return type Optional is a new container type that wraps a single value
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

# sakila-springboot-maven
Full Stack Project using Spring Boot for backend and Ext JS for frontend

The backend is developed using the springboot to provide a flexible way to configuring Java Beans, XML configurations, and Database Transactions.

The springoot initilizer: https://start.spring.io/ with the following dependencies:
 1. Jersey 
 2. Spring Web Services
 3. Spring Web
 4. Spring Boot DevTools 

Project type : Maven;
Language : Java  
Spring boot version: 2.7.5

The backend files are present in the folder Sakila_film: 
  1. Sakila_film.dao
       > A structural pattern that allows us to isolate the application/business layer from the persistence layer (usually a relational database but could be any               other persistence mechanism) using an abstract API.The API hides from the application all the complexity of performing CRUD operations in the underlying               storage mechanism. This permits both layers to evolve separately without knowing anything about each other.
       >> FilmDao.java // for the CRUD applications on FILM Table  
       >> LanguageDao.java // for the mapping of language_id from both the LANGUAGE and FILM tables
  2. Sakila_film.dbconfig
        > DataSourceConfig.java   // JDBC connection   
  3. Sakila_film.dto
        > FilmDto.java //an object class that carries data between processes for the table FILM
  4. Sakila_film entity
        > Film.java // define the columns and primary key of FILM table
        > Language.java //define the columns and primary key of LANGUAGE table
  5. Sakila_film.manager
       > FilmManager.java //uses the object of FilmDao to declare the functions
  6. Sakila_film.service
       > FilmService.java //interface 
  7. Sakila film.serviceimpl
       > FilmServiceImpl.java //implements the above interface
       
The Fontend is implemented using VS Code and GO Extension is implemented for testing purpose.
The main UI is as below image:

<img width="805" alt="image" src="https://user-images.githubusercontent.com/77729425/202119040-3d90142e-f048-4b8c-9512-7f984c77a3d3.png">


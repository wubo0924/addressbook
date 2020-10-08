# Address Book
##Requirements
You have been asked to develop an address book that allows a user to store (between
successive runs of the program) the name and phone numbers of their friends, with the
following functionality:
• To be able to display the list of friends and their corresponding phone numbers sorted
by their name.
• Given another address book that may or may not contain the same friends, display the
list of friends that are unique to each address book (the union of all the relative
complements). For example given:
Book1 = { “Bob”, “Mary”, “Jane” }
Book2 = { “Mary”, “John”, “Jane” }
The friends that are unique to each address book are:
Book1 \ Book2 = { “Bob”, “John” }

##Design Decisions
Use microservice style to implement the requirements. It's easy to build, run and test. 
3 Apis are open to list, create and be unique the users.   

##Assumptions
- A user's addressbook can be empty

##API specification
1. List users<br/>
    GET - http://localhost:8080/address-book/users
2. Create users<br/>
    POST - http://localhost:8080/address-book/users
3. List unique users between two address books<br/>
    POST - http://localhost:8080/address-book/users/unique

##Build
Locate in project directory
```
mvn clean package
```

##Run
Locate in project directory
```
java -jar ./target/addressbook-1.0.0.jar 
```

##Test

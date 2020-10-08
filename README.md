# Address Book
## Requirements
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

## Design Decisions
Use microservice style to implement the requirements. It's easy to build, run and test. 
3 Apis are open to list, create and be unique the users.
H2 is used for data storage. It's easy to set up and truncate. There are 3 initial users that pre-loaded in H2 once service started. The user data file is located in resources/data.sql.
3 columns are designed in H2: id, name, phone. id column is only used for primary key, it won't appear in response. It's also not required while creating users. 

## Assumptions
- A user's addressbook can be empty
- A user CAN have two friends with the same name

## API specification
1. List users<br/>
    GET - http://localhost:8080/address-book/users
2. Create users<br/>
    POST - http://localhost:8080/address-book/users
3. List unique users between two address books<br/>
    POST - http://localhost:8080/address-book/users/unique
4. Clear users for data storage<br/>
    DELETE - http://localhost:8080/address-book/users/clear

## Build
Locate in project directory
```
mvn clean package
```

## Run
Locate in project directory
```
java -jar ./target/addressbook-1.0.0.jar 
```

## Test
• List all users
```
curl --location --request GET 'http://localhost:8080/address-book/users'
```
• Create users
```
curl --location --request POST 'http://localhost:8080/address-book/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "addressBookList": [
        {

            "name": "K1",
            "phoneNumber": "9999"
        },
        {

            "name": "K2",
            "phoneNumber": "8877"
        },
        {

            "name": "K3",
            "phoneNumber": "2324"
        }
    ]
}'
```
• List unique users between two address books
```
curl --location --request POST 'http://localhost:8080/address-book/users/unique' \
--header 'Content-Type: application/json' \
--data-raw '{
    "addressBookList": [
        {

            "name": "Mary",
            "phoneNumber": "9999"
        },
        {

            "name": "John",
            "phoneNumber": "324324234"
        },
        {

            "name": "Jane",
            "phoneNumber": "2324"
        }
    ]
}'
```
## Clear data
```
curl --location --request DELETE 'http://localhost:8080/address-book/users/clear'
```

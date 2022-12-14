# WALLET PROJECT

It is a project which is a simple wallet application running on the JVM that manages deposit/withdraw transactions.

## Restrictions

The balance in wallet can be modified by transactions , either deposit transactions (adding amount of transaction to balance in wallet) 
or withdraw transactions (removing amount of transaction to balance in wallet). Withdraw transaction will only succeed if there are sufficient funds in wallet (balance - amount >= 0 and status in wallet must be enabled). 

## Project Description

This project was implemented for creating wallet application. Wallet application was implemented
with Java11, Spring Boot and Maven. You can find the detail information regarding this project such as requirements, running procedure, 
testing procedure, api endpoints, out of scope and scalable system scope and JWT for security. 

##Technologies
1. Java11
2. Spring Boot
3. Spring Data JPA
4. MySQL
5. JWT
6. slf4j
7. Maven
8. JUnit

## Requirements and steps to run this application
1. Install Java 11
2. Maven to build the application. 
3. Download and install MySQL server
4. Connect to the MySQL server
5. Make mysql configurations in application.properties

```
CHANGE THIS AREA IN APPLICATON.PROPERTIES

spring.datasource.url = jdbc:mysql://localhost:3306/wallet_db?createDatabaseIfNotExist=true 
spring.datasource.username = hbstudent
spring.datasource.password = hbstudent
```

## Running

If you want to run this application you need to follow the "Requirements and steps to run this application" part.
You should run the "RestWebServiceApplication" class and this application is running on port 8070. You can change this
port from application.properties

server.port=8070

``` 
http://localhost:8070/e_wallet
``` 

## Testing

There are unit tests regarding the wallet application. I implemented unit test
for most of functionalities. For running tests I configured H2 which is set in
application.properties in resources in test folder.

Use THIS AREA IN APPLICATION.PROPERTIES

spring.datasource.url=jdbc:h2:mem:e_wallet_test;DB_CLOSE_DELAY=-1
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa 
spring.datasource.password=

``` 

## Some Endpoints
You can find the required parameters and detail information at postman.
``` 

--- USER CONTROLLER ----

POST http://localhost:8070/e_wallet/api
save user

POST http://localhost:8070/e_wallet/api/login
log in


--- TRANSACTION CONTROLLER ----

POST http://localhost:8070/e_wallet/transaction/chargeWallet
Add transaction of a given transaction and add balance(deposit)

POST http://localhost:8070/e_wallet/transaction/withdrawWallet
withdraw balance from wallet to transaction account(adding amount of transaction)

POST http://localhost:8070/e_wallet/moneyTransfer
transfer funds from source wallet to target wallet

GET http://localhost:8070/e_wallet/transaction/1
get transaction list for wallet find by id



--- WALLET CONTROLLER ---

POST http://localhost:8070/e_wallet/wallet
save wallet

PUT http://localhost:8070/e_wallet/wallet
update wallet name or status(ENABLED,DISABLED)

GET http://localhost:8070/e_wallet/wallet
fetching all wallets of user logged in

``` 

# REWARDS Application

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) Rewards Application


## Requirements

For building and running the application you need:

- [Maven 3](https://maven.apache.org)
- [JDK 11 or above]  (https://jdk.java.net/)
- [H2 Database]

### Packages
- Spring Boot JPA
- Lombok
- H2 Db

## Running the application locally

execute the `main` method in the `com.trasaction.management.RewardsApp`

or from shell

```shell
mvn spring-boot:run
```

Controller class

`com.trasaction.management.controller.TransactionController`

which has four end points 

http://localhost:8080/transaction/rewards/startDate=?/endDate=?

http://localhost:8080/transaction/rewards/{customerId}/startDate=?/endDate=?

http://localhost:8080/transaction/all

http://localhost:8080/trasaction/{customerId}

### Sample Data

https://github.com/sahithi9269/rewards/blob/main/src/main/resources/transactions.txt

#### Entity Class
 
#####  Transaction  {
	private int id;  // id of the transaaction
	private int customerId; // id of customer
	private double amount; // amount of transaction
	private LocalDate date; // date of transaction
	private int rewards; // rewards computed for the trasaction
}

#### Repository Class
TransactionRepository 
Used JPA to fetch transaction from database



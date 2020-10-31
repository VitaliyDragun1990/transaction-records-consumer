Spring boot application that demonstrates process of reading clients' transaction records
from XML file and storing such records to a relational database.

### Implementation details

- XML file with clients' transaction records called **transaction_records.xml** located at:
````
[project root folder]/src/main/resources/transaction_records.xml
````
 - Convenient **H2** in-memory database is used
 - Actual process of reading transaction records and storing them to the database is executed
 on application startup
 
 ### Building and running the application
 In order to build the application, open the app main directory in your terminal window, type in the following command 
 and hit "Enter" afterwards
 ````
gradlew build
````
This command executes all tests, builds the application and creates executable jar file called
 **transaction-records-consumer.jar**

In order to run the application, you can either use gradle by typing in the following command:
````
gradlew bootRun
````
or you can run application's jar file directly using given command:
````
java -jar build\libs\transaction-records-consumer.jar
````
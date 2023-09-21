# Charter_demo
This demo is for the assessment for Charter. When running jar file or starting the springboot project, the entry point will show on the console, which will be http://localhost:8080/

## Back-end
The back-end part is based on SpringBoot. The whole project contains three layers: control layer, service layer and DAO layer. Also there are unit test for the methods.

## Control Layer
The Control Layer contains one controller and one global exception handler to handle exceptions. The Controller has two apis. First is the one described in the file. It will receive an customer id, and return a Map contains some key-value pairs for front-end. There are five entries in the map

ThreeMonthsTotal key-value pair represents the total points for the customer in last three month
firstMonth represents the point in the first month in past three months
secondMonth represents the point in the second month in past three months
thirdMonth represents the point in the third month in past three months
code represents the response status, 0 for success, 1 for failure, 2 for invalid input value Second one is an api used to insert a record to the database. It accepts a Reward Object as parameter and return the Reward Object stored into the database In the global exception handler, there are two exception handlers. The first exception handler is used to handle a self-defined exception. The CustomerIdNotFoundException will rise when the database contains no record for the specific customer. The second exception handler is used to handle the MethodArgumentNotValidException. This exception will rise when the input value is invalid.
Service Layer
The service layer contains one service interface and an implementation class for that interface. The service layer contains three functions. The first function accepts a customer id and call the repository to find all the record with that customer id. Then it return the calculated reward for that user in past three months. The second function accepts a reward object and call the repository to store that object in the database. Also there is an auxiliary function for calculation.

## DAO Layer
The DAO layer use the JpaRepository for basic functions. The RewardRepository interface extends the JpaRepository, so it can use the JPQL for database manipulation.

## Unit Test
The test part mainly tests the function of control layer and service layer. Also there is an integration test for the connection between the control layer and service layer. In the control layer and service layer unit test, it tests the method to get the reward point with specific customer id and it works well on both situations - the situation with records and no record. Also the add new record method is tested. In the integration test, I used Mockito to test the communication between control layer and service layer, and all test cases passed.

## Front-end
I also created a basic front-end UI to show the result. The front-end is based on React and contains two components. The first component can fetch and show the reward point for with a customer id. The second component can insert a new record to the database.


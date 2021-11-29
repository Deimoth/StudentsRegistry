Java8   
SpringFramework     
Maven   
H2database  
AngularJS   


ABOUT

Simple students registry. Allows to:
1. list students
2. add student
3. edit student
4. delete student


QUICK START

Install database:
1. download H2 (https://h2database.com/h2-2019-03-13.zip)
2. unzip
3. run bin/h2.sh
4. wonder how beautiful is DB console at localhost:8082 
That's all, database and tables will be created on first application run. 
You may change database settings in application.properties file 

Build application:
1. mvn clean install

Run application:
1. java -jar target/registry-1.0-SNAPSHOT.jar
2. API appears at localhost:8080

Run frontend:
1. simply open ui/index.html in your browser
2. enjoy!
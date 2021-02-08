# yafi

yafi is a simple forum like application  

## Features:
* Create User  
* Create Topic  
* Create/Delete Thread  
* Like Thread

## 1.Requirements

jdk8  
maven  
mysql or mariadb - mariadb10 recommended

## 2.How to build&run

### From cmd
cd to project_dir  
mvn package  
cd target  
java -jar yafi-1.0-SNAPSHOT.jar  

### From IDE

open com.eg.yafi.Application  
IDE should show a play icon at the class name. Click it

### 3.Package Structure

**controller:** Rest endpoint classes are stored here  
**dto:** DTO classes are here, controller classes receive DTO return DTO, for separation of concerns  
**entity:** @Entity annotated classes are stored here, ORM mapped classes  
**projection:** for read-only SELECT queries use/add classes here - more performant than selecting @Entity annotated classes  
**repo:** JPA/Hibernate query methods are stored here, each @Entity class has its own Repo such as Company and CompanyRepo  
**service:** service classes are stored here, operations are done inside these classes  

**src/main/resources/application.properties:** project configuration file  
**important features:**  
* spring.jpa.hibernate.ddl-auto: create => drop existing tables create new tables none => don't change existing db tables
* server.port: port for the application to run

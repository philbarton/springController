# springController

Example using liquibase, jooq on postgres

## Start Postgres using docker
````
docker run --name jsa -e POSTGRES_PASSWORD=password -d postgres
````
## Start psql connection
````
docker run -it --rm --link jsa:postgres postgres psql -h postgres -U postgres
````

## liquibase 
````
 mvn resources:resources liquibase:update
 ````
 
## jooq
````
 mvn generate-sources
 ````
 
## Package
````
 mvn package
 ````
 
## run
````
 java -jar target/springController-0.0.1-SNAPSHOT.jar
 ````
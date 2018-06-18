# springController

Example using flyway, jooq on postgres

## Start Postgres using docker
````
docker run --name jsa -e POSTGRES_PASSWORD=password -d postgres
````
## Start psql connection
````
docker run -it --rm --link jsa:postgres postgres psql -h postgres -U postgres
````
## Run from command line
````
 mvn spring-boot:run
 ````
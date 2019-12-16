# City Groove

### Goals of the project
Create small Spring boot application and try following concepts:
1. REST Api
    1.1 GET/POST/DELETE
    1.2 Consume/Produce JSON
    1.3 Test receiving input through body/query params
2. Security with JWT token
    1.1 Filter
    1.2 AuthProvider
    1.3 AuthManger
3. Exception handling with custom exceptions
4. Spring Data with MySql and H2 DB for testing
6. Try software architecture based on article:

    https://herbertograca.com/2019/06/05/reflecting-architecture-and-domain-in-code/
5. Dockerizing app

Missing:
1. Logging
2. Better test coverage
3. Swagger documentation 

### Installing
Clone repository:
```
https://github.com/sekulicd/CityGroove.git
```
Bellow command will start spring-boot-project CityGroove and Mysql database in Docker containers:
```
cd ./docker
./start.sh
```

### Exposed APIs
## Public API

Register user with username and password, returns JWT token.
```
POST /public/register
curl -X POST http://localhost:8080/public/register -H 'Content-Type: application/json' -H 'Host: localhost:8080' -d '{"userName":"marti",	"password":"test"}'
```

Login with username and password, , returns JWT token.
```
POST /public/register
curl -X POST http://localhost:8080/public/register -H 'Content-Type: application/json' -H 'Host: localhost:8080' -d '{"userName":"marti",	"password":"test"}'
```

List all cities, by default ordered by create date. 
In order to list cities based on most liked cities use query param favouriteCount.
```
POST public/city
Oreder by create date:
curl -X GET http://localhost:8080/public/city -H 'Content-Type: application/json' -H 'Host: localhost:8080'
Order by favouriteCount:
curl -X GET http://localhost:8080/public/city?sortBy=favouriteCount -H 'Content-Type: application/json' -H 'Host: localhost:8080'
```

List all cities sorted by likes.
```
GET /public/likes
curl -X GET http://localhost:8080/public/likes -H 'Content-Type: application/json'
```

## Private API
Add new city.
```
POST /city
curl -X POST 'http://localhost:8080/city' -H 'Authorization: Bearer {{TOKEN}}' -H 'Content-Type: application/json' \
-d '{
	"name":"new york",
    "description":"big light city",
    "population": "2020000"
}'
```

User likes city. 
Copy JWT token from register/login output.
```
POST /city/like
curl -X POST 'http://localhost:8080/city/like' -H 'Authorization: Bearer {{TOKEN}}' -H 'Content-Type: application/json' \
-d '{
	"userName":"mark",
	"cityName":"madrid"
}'
```

Delete like from city. 
Copy JWT token from register/login output.
```
DELETE /city/like
curl -X DELETE 'http://localhost:8080/city/like' -H 'Authorization: Bearer {{TOKEN}}' -H 'Content-Type: application/json' \
-d '{
	"userName":"mark",
	"cityName":"madrid"
}'
```
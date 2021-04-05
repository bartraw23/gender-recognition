# Gender-recognition

Application designed for gender recognition of names.

## Endpoints
*POST*  *http://localhost:8080/name* 

* in body name and boolean value(_byAllTokens_) have to be included, _byAllTokens_ is a selector for recognition variant(false -> by first token, true -> by all tokens).

        {   
          "name": "queryName",
          "byAllTokens": false/true   
        } 

* return JSON with Name and result gender:

        {
          "name": "queryName",
          "gender": "FEMALE/MALE/INCONCLUSIVE"
        }


*GET*  *http://localhost:8080/tokens* 

* return JSON with Tokens:

        {"maleTokens": [
                          ...
        ],
        "femaleTokens": [
                          ...
        ]}
    
        

## Technology stack

* Spring Boot 2.4.4
* Java 11
* PostgresQL
* Gradle
* Docker
* Rest-assured
* JUnit
* Log4j

## Author

* **Bartłomiej Trawiński** - [github](https://github.com/bartraw23) - [linkedin](https://www.linkedin.com/in/bart%C5%82omiej-trawinski/)
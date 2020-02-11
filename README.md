# API

### Get all persons:
```
curl --location --request GET 'quiet-ravine-52493.herokuapp.com/persons' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
```
Sample response:
```json
{
  "_embedded": {
    "persons": [
      {
        "id": 1,
        "firstName": "John",
        "lastName": "Wick",
        "age": 48,
        "favouriteColour": "black",
        "hobbies": [
          {
            "name": "Pet the doggo"
          }
        ],
        "_links": {
          "self": {
            "href": "http://quiet-ravine-52493.herokuapp.com/persons/1"
          },
          "person": {
            "href": "http://quiet-ravine-52493.herokuapp.com/persons/1"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://quiet-ravine-52493.herokuapp.com/persons{?page,size,sort}",
      "templated": true
    },
    "profile": {
      "href": "http://quiet-ravine-52493.herokuapp.com/profile/persons"
    }
  },
  "page": {
    "size": 20,
    "totalElements": 1,
    "totalPages": 1,
    "number": 1
  }
}
```

### Create a Person
```shell script
curl --location --request POST 'quiet-ravine-52493.herokuapp.com/persons' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--data-raw '{
  "firstName": "John",
  "lastName": "Wick",
  "age": 40,
  "favouriteColour": "black",
  "hobbies": [
    {
      "name": "Hobby 1"
    },
    {
      "name": "Hobby 2"
    },
    {
      "name": "Hobby 3"
    }
  ]
}'
```
sample response:
```json
{
  "id": 3,
  "firstName": "John",
  "lastName": "Wick",
  "age": 40,
  "favouriteColour": "black",
  "hobbies": [
    {
      "name": "Hobby 2"
    },
    {
      "name": "Hobby 1"
    },
    {
      "name": "Hobby 3"
    }
  ],
  "_links": {
    "self": {
      "href": "http://quiet-ravine-52493.herokuapp.com/persons/3"
    },
    "person": {
      "href": "http://quiet-ravine-52493.herokuapp.com/persons/3"
    }
  }
}
```

### Update a person's info
```shell script
curl --location --request PUT 'quiet-ravine-52493.herokuapp.com/persons/1' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--data-raw '{
  "firstName": "Evan",
  "lastName": "Pop",
  "age": 20,
  "favouriteColour": "blue",
  "hobbies": [
    {
      "name": "Hobby 3"
    }
  ]
}'
```

Sample response:
```json
{
  "id": 1,
  "firstName": "Evan",
  "lastName": "Pop",
  "age": 20,
  "favouriteColour": "blue",
  "hobbies": [
    {
      "name": "Hobby 3"
    }
  ],
  "_links": {
    "self": {
      "href": "http://quiet-ravine-52493.herokuapp.com/persons/1"
    },
    "person": {
      "href": "http://quiet-ravine-52493.herokuapp.com/persons/1"
    }
  }
}
```

### Delete a person
```shell script
curl --location --request DELETE 'quiet-ravine-52493.herokuapp.com/persons/3' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
```
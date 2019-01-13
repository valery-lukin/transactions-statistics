# Transactions statistics
Transactions statistics REST API written in JAVA as a sample
=================================
This repository contains the open source Java REST API To work with simple transactions(Adding them, deleting them and getting statisctics)

Requirements
------------
- Java version 1.8
- Installed version of maven
--------------
To run unit tests:
```
mvn test
```

To run integration tests:
```
mvn integration-test
```
-----------------------------------
# REST API documentation
# DTO's:
##### `Transaction`

```
{
  "amount": String, (String representation of BigDecimal)
  "timestamp": String 
}
```

##### `Statistics`

```
{
  "sum": String,
  "avg": String, 
  "max": String,
  "min": String, 
  "count": Long 
}
```

* **URL**

  `/transactions`

* **Method:**

  `POST`
  
* **Body:**
    ##### `Transaction`

* **Success Response:**

  * **Code:** 201 CREATED
    **Content:** empty
    **Description:** Successfully added Transaction
 
* **Error Response:**

  * **Code:** 204 NO_CONTENT
    **Content:** empty
    **Description:** Transaction is older than 60 seconds and will not be added

  OR

  * **Code:** 400 BAD_REQUEST
    **Content:** empty
    **Description:** JSON is invalid

  OR
  * **Code:** 422 UNPROCESSABLE_ENTITY
    **Content:** empty
    **Description:** Some of the fields are not parsable or the transaction date is in the future

---------------
* **Method:**

  `DELETE`
  
* **Body:**
    `empty`

* **Success Response:**

  * **Code:** 204 NO_CONTENT
    **Content:** empty
    **Description:** Successfully removed all Transactions
 
----------------------------------------------

* **URL**

  `/statistics`

* **Method:**

  `GET`

* **Success Response:**
     * **Code:** 200 OK
    **Content:** `Statistics`
    **Description:** Returns Statistics based on last 60 seconds



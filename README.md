# basedbook
![maven.yml](https://github.com/DavidHaighton/basedbook/actions/workflows/maven.yml/badge.svg)

This contains the labs for the SYSC 4806 course. All lab submissions have tags associated with them.

## The operations that can be performed are:
POST http://localhost:8080/books | to make a new address book

POST http://localhost:8080/books/1 with the payload of "{"name":"person","phone":"number"}"| to make a new contact in address book 1

DELETE http://localhost:8080/books/1/1 | to remove contact with id 1

GET http://localhost:8080/books/1 | to get the webpage for a contact book


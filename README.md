# Ticket System SOAP Service

A Spring Framework SOAP web service for managing events and ticket bookings.
The application exposes SOAP endpoints for creating events and booking tickets, backed by a JDBC data layer and an embedded Tomcat server.

## Features

* Create new events
* Book a ticket for an event
* SOAP contract defined with XSD
* WSDL generation from the schema
* Embedded Tomcat runtime
* JDBC-based persistence
* H2 database support for local development

## Tech Stack

* Java 26
* Spring Framework 7
* Spring Web Services
* Spring JDBC
* Spring Transaction
* Apache Tomcat Embedded
* JAXB
* H2 Database
* Maven

## Project Structure

```text
src
└── main
    ├── java
    │   └── org.ticket
    │       ├── config
    │       ├── domain
    │       ├── endpoint
    │       ├── exception
    │       ├── repository
    │       └── service
    └── resources
        ├── application-dev.properties
        ├── data.sql
        ├── schema.sql
        └── ticket.xsd
```

## SOAP Contract

The service uses the namespace:

```text
http://ticket.org/ws
```

### Operations

#### Create Event

* **Request**: `CreateEventRequest`
* **Response**: `EventResponse`

#### Book Ticket

* **Request**: `BookingRequest`
* **Response**: `BookingResponse`

## Running the Project

### 1. Build the project

```bash
mvn clean install
```

### 2. Run the application

Start the embedded Tomcat server using the `MainApplication` class.

### 3. Open the WSDL

After startup, the WSDL should be available at:

```text
http://localhost:8080/ws/tickets.wsdl
```

## Postman Setup

Use a `POST` request to:

```text
http://localhost:8080/ws
```

Set this header:

```text
Content-Type: text/xml;charset=UTF-8
```

And send a full SOAP envelope in the request body.

### Example: Create Event

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:ws="http://ticket.org/ws">

    <soapenv:Header/>
    <soapenv:Body>
        <ws:CreateEventRequest>
            <ws:name>Spring Conference</ws:name>
            <ws:availableSeats>100</ws:availableSeats>
        </ws:CreateEventRequest>
    </soapenv:Body>
</soapenv:Envelope>
```

### Example: Book Ticket

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:ws="http://ticket.org/ws">

    <soapenv:Header/>
    <soapenv:Body>
        <ws:BookingRequest>
            <ws:eventId>1</ws:eventId>
            <ws:ownerName>Mohamed Farid</ws:ownerName>
        </ws:BookingRequest>
    </soapenv:Body>
</soapenv:Envelope>
```

## Database

The application uses SQL scripts located in `src/main/resources`:

* `schema.sql` for table creation
* `data.sql` for initial data

If you are using H2 locally, the schema is initialized automatically on startup.

## Configuration

Database connection settings are loaded from:

```text
application-dev.properties
```

Example properties:

```properties
db.driver=org.h2.Driver
db.url=jdbc:h2:~/ticket_db
db.username=sa
db.password=
```

## Notes

* SOAP request and response classes are defined from the XSD contract.
* The endpoint layer maps SOAP messages to the service layer.
* Custom exceptions are converted into SOAP faults.

## License

This project is for learning and portfolio purposes.

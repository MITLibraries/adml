# Archives Digital Media Log

This TEST webapp enables accessioning or processing archivists to create a log of media
items within archival collections. The tool will facilitate keeping track of media
that is in specific collection boxes/containers in aggregate terms per media format
type. The output from this tool will enhance the collection management records in
ArchivesSpace without needing to manually type that information again.

The stack is Spring Boot/MVC/Security/Data. 
The tool uses an embedded database (h2) in development mode and MySql in production mode.


Dependencies
-------------

- A running ArchivesSpace instance
- Maven for building the project.
- `aspace.props` (format below) to specify ArchivesSpace API and app user credentials
- `application-prod.properties` (modeled on application.properties for production use)
- A relational database (for production use)

When deploying to production:

- Supply application-prod.properties to specify database credentials. This will be replaced
with Tomcat JNDI connections in future.
- Grep for IPs for ASpace and production machine IP when deploying in production and change
the IPs from `localhost` to the new address.

Configuration
---------------

Make sure you have aspace.props or aspace-prod.properties in the following format for ASpace lookup and user credentials:

```sh
login_password=
login_url=
```


Build
--------------

The project can be built using Apache Maven, and the resulting .war file can just be dropped into Tomcat 
(or just launched with `java -jar`).

```sh

# from the folder, run the build, and package it:

mvn clean install -P dev

# to test it:

java -jar target/adml-0.0.1-SNAPSHOT.war

```

Test
--------

Confirm `http://localhost:8080/adml` works.


Production Setup Details
------------------------

Change ArchivesSpace URL in dm_items.js (to match properties file).
Change application.properties so that Spring Boot picks up the production environment (from debug to prod).
Make sure you have application-prod.properties which has the following format:


```sh
spring.thymeleaf.cache=false
server.contextPath=/adml
server.port=8080
logging.level.org.springframework.web: DEBUG

# for mysql
spring.jpa.hibernate.ddl-auto=validate
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

# other:

app.email=[email of users allowed access to the application]
app.ip=[ip of app]

```

Ensure that the connection.props file points to the right ASpace IP.


Here's how you build and deploy the .war:

```sh
# change application.properties with line spring.profiles.active=prod
# so that it picks up application-prod.properties

# from the folder, run the build, and package it:

mvn clean install -P prod

# copy the file to server as necessary

```

Server Setup
-------------------
- Install or use MySql
- Install httpd and enable reverse proxy.
- Install Tomcat 8+
- Drop the .war to webapps folder.



Users
------

- DDC (MIT)

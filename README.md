# minivns

## This is a pet project for students to manage their assignments

For running it you will need java 17 and postgreSql

1. Firstly, install Java 17, *PostgreSQL 15.


2. After that you need to create database*. You can use PgAdmin or SQLShell

	>*It is better to use as a credentials for  postgres user next values 
	>
	>username: `postgres`,
        >
	>password `1111`,
        >
	>Name for database `minivns`.
	>
	>But if you want to use your own credentials and your own name for database you need to change properties in `resources/application.properties` for this
>        
>```sh
>spring.datasource.url=jdbc:postgresql://localhost:5432/minivns{<-database name}
>spring.datasource.username=postgres{<-username}
>spring.datasource.password=1111{<-password}
>```
3. After that you need to install database scheme into your database, using file `resources/library.backup`. You can use SQLShell or PgAdmin.

 3.1. VIA  PgAdmin you can need firstly to provide path to bin folder with all of utilities.
	To do that go to
	
```File->Preferences->Binary paths->PostgreSQL Binary paths section, your version of PostgreSql->Click on folder icon and choose path to postgresql bin folder.```

4. After that you can run program and a server.
5. To firstly run or rerun a database you need to uncomment in `resources/application.properties`
```sh
#spring.jpa.hibernate.ddl-auto=update
#spring.sql.init.mode=always 
```
   and comment previous statement

 5.1. Also you may need  to create sequences. you can use  idea's tool to create sequence and can write in `data.sql`:	
```sh
CREATE SEQUENCE tasks_start8{<- for example} START WITH 8{<-for example} INCREMENT BY 1{<- for example} START 8 RESTART 8;
```
6.After that check if `role->users->subjects->tasks` were created and filled with records from `resources/data.sql`.

CREATE KEYSPACE "Keyspace_1" 
  WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
  }
AND DURABLE_WRITES = false; 

USE "Keyspace_1";

CREATE TABLE "Keyspace_1"."Passenger" (
  "passenger_ID" int,
  "firstname" text,
  "lastname" text,
  "passportNo" int,
  PRIMARY KEY ("passenger_ID", "firstname")
)
WITH CLUSTERING ORDER BY ("firstname" ASC);

CREATE TABLE "Keyspace_1"."Passenger_Details" (
  "passenger_ID" int,
  "birthdate" date,
  "sex" text,
  "street" text,
  "city" text,
  "zip" int,
  "country" text,
  "email" text,
  "telefone" int,
  PRIMARY KEY ("passenger_ID", "birthdate")
)
WITH CLUSTERING ORDER BY ("birthdate" ASC);

insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(231,'Andrew','Hill','P103241'  );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(248,'Perry','Lopez','P103258 ' );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(266,'Frederic','Raphael','P103276'  );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(304,'Archbishop Desmond','Tutu','P103314'  );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(307,'Veronica','Hurst','P103317'  );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(308,'Stan','Jones','P103318 ' );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(314,'Jim','Ringo','P103324'  );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(316,'Morley','Safer','P103326'  );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(318,'Brad','Sullivan','P103328' );
insert into keyspace_1.passenger(passenger_ID,firstname,lastname,passportNo) values(321,'Monica','Vitti','P103331 ' );



insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(273,'18/02/78','m','Prof.-Ficker-Weg 64','Litzelsdorf','7532','GERMANY','george.chambers@mtsmobility.com','0660 6659199');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(284,'18/09/11','m','Angerzellgasse 14','Oberwart','7400','CHILE','derek.nimmo@mobistar.be','07348 41606552');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(286,'18/01/89','m','Anton-Rauch-Straße 1','Rechnitz','7471','GREECE','willie.ruff@smsmail.lmt.lv','0291 75171921');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(291,'18/11/69','m','Blasius-Hueber-Straße 97','Stadtschlaining','7400','SEYCHELLES','jim.bunning@satelindogsm.com','0400 46161262');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(296,'18/10/00','m','Brunecker Straße 96','Unterwart','7501','BRAZIL','mickey.mantle@att.net','07135 41539619');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(297,'18/05/68','m','Bürgerstraße 34','Weiden bei Rechnitz','7461','ARGENTINA','eddie.mathews@correo.movistar.net','09363 43543995');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(305,'18/09/85','m','Elisabethstraße 80','Schandorf','7472','BRAZIL','stan.jones@page.hit.net','08796 96997931');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(330,'18/01/10','m','Innstraße 98','Gitschtal','9622','BRAZIL','romolo.girolami@page.hit.net','02135 90560992');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(24,'18/08/60','w','Nordkettenstraße 19','Graz','8046','CANADA','joanne.woodward@airtelkol.com','02110 39076274');
insert into keyspace_1.Passenger_Details(passenger_ID,birthdate,sex,street,city,zip,country,email,telefone) values(32,'18/02/19','w','Trientlgasse 8','Graz','8074','UNITED STATES','jan.howard@swmsg.com','09649 59767285');
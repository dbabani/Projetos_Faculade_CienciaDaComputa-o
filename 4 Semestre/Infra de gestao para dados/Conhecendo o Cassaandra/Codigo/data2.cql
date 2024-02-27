CREATE KEYSPACE "Keyspace_2" 
  WITH REPLICATION = {
    'class' : 'SimpleStrategy',
    'replication_factor' : 1
  }
AND DURABLE_WRITES = false; 

USE "Keyspace_2";

CREATE TABLE "Keyspace_2"."Flight" (
  "Flight_id" int,
  "departure" timestamp,
  "arrival" timestamp,
  PRIMARY KEY ("Flight_id", "departure")
)
WITH CLUSTERING ORDER BY ("departure" ASC);

CREATE TABLE "Keyspace_2"."Airport_arrival" (
  "Flight_ID" int,
  "nome" text,
  PRIMARY KEY ("Flight_ID", "nome")
)
WITH CLUSTERING ORDER BY ("nome" ASC);



insert into keyspace_2.Flight(flight_id,departure,arrival) values(142190,'2020-10-02 02:46:09','2023-11-18 10:21:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(142228,'2021-01-22 04:45:40','2023-09-18 09:29:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(142263,'2020-05-04 07:06:40','2023-05-18 19:36:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(142271,'2021-04-20 16:06:56','2023-05-18 11:05:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(142272,'2022-08-14 00:07:36','2023-02-19 07:15:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(142297,'2021-05-18 20:36:14','2023-05-19 01:19:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(142298,'2022-10-10 09:13:58','2023-11-19 06:24:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(139884,'2021-06-28 02:32:09','2023-12-18 12:53:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(142412,'2021-12-24 22:13:14','2023-05-18 18:00:00');
insert into keyspace_2.Flight(flight_id,departure,arrival) values(142419,'2020-04-16 19:46:59','2023-05-18 07:30:00');



insert into keyspace_2.Airport_arrival(flight_id,nome) values(141166,'AMILCAR CABRAL DE GAOUA');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(144032,'ANIAK');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(385102,'APATAKI');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(389343,'ARBRA');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(139790,'AREA DE MATERIAL');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(640351,'ATICO');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(143407,'ATIKOKAN MUN');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(640166,'BAD PYRMONT');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(142938,'BAD SASSENDORF');
insert into keyspace_2.Airport_arrival(flight_id,nome) values(140854,'BANNING MUN');
﻿



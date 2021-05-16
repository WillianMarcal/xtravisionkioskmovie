BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Movie" (
	"id"	INTEGER NOT NULL,
	"Movie"	TEXT NOT NULL,
	"Price"	INTEGER NOT NULL,
	"Quantity"	INTEGER NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "movie_record" (
	"record_no"	INTEGER NOT NULL,
	"Movie_id"	INTEGER NOT NULL,
	"person_name"	TEXT NOT NULL,
	"CNIC"	TEXT NOT NULL,
	"returned"	TEXT NOT NULL,
	"movie_purchased"	INTEGER,
	"total_amount"	INTEGER,
	PRIMARY KEY("record_no" AUTOINCREMENT)
);
INSERT INTO "Movie" ("id","Movie","Price","Quantity") VALUES (1,'avengers',1200,2);
INSERT INTO "Movie" ("id","Movie","Price","Quantity") VALUES (2,'End Game',2000,1);
COMMIT;

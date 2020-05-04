drop table foodsupplier_diet;
drop table animal_diet;
drop table medsupplier_medication;
drop table animal_sanctuary;
drop table medication_examination;
drop table vet_examination_animal;
drop table vet_specialization;
drop table animal_handler;
drop table animal;
drop table animalspecies;
drop table sanctuary;
drop table sanctuaryname;
drop table examination;
drop table medication;
drop table foodsupplier;
drop table medsupplier;
drop table supplier;
drop table specialization;
drop table veterinarian;
drop table handler;
drop table staff;
purge recyclebin;


CREATE TABLE AnimalSpecies(
Species char(35) PRIMARY KEY,
AType char(20)
);

CREATE TABLE Animal(
AID int PRIMARY KEY,
Name char(20),
Species char(35),
FOREIGN KEY (Species) REFERENCES AnimalSpecies(Species) ON DELETE CASCADE
);

CREATE TABLE SanctuaryName(
Name char(20) PRIMARY KEY,
SancType char(20)
);

CREATE TABLE Sanctuary(
SanctID int PRIMARY KEY,
Name char(20),
sanctSize int,
FOREIGN KEY (Name) REFERENCES SanctuaryName(Name) ON DELETE CASCADE
);

CREATE TABLE Examination(
EID int PRIMARY KEY,
examDate Date,
Description char(40),
examType char(20)
);

CREATE TABLE Medication(
MedID int PRIMARY KEY,
Name char(20),
MedType char(20)
);

CREATE TABLE Supplier(
SupID int PRIMARY KEY,
Name char(20),
City char(30),
Country char(30)
);

CREATE TABLE FoodSupplier(
SupID int primary key,
FOREIGN KEY (SupID) REFERENCES Supplier(SupID) ON DELETE CASCADE
);

CREATE TABLE MedSupplier(
SupID int primary key,
FOREIGN KEY (SupID) REFERENCES Supplier(SupID) ON DELETE CASCADE
);

CREATE TABLE Staff(
SID int PRIMARY KEY,
Name char(20),
Phone char(12),
Address char(50),
staffSIN int
);

CREATE TABLE Veterinarian(
SID int primary key,
FOREIGN KEY (SID) REFERENCES Staff(SID) ON DELETE CASCADE
);

CREATE TABLE Handler(
SID int primary key,
FOREIGN KEY (SID) REFERENCES Staff(SID) ON DELETE CASCADE
);

CREATE TABLE Specialization(
SpecID int PRIMARY KEY,
Name char(30)
);

CREATE TABLE Animal_Diet(
DType char(10),
Amount char(20),
AID int,
PRIMARY KEY (DType, AID),
FOREIGN KEY (AID) REFERENCES Animal(AID) ON DELETE CASCADE
);

CREATE TABLE FoodSupplier_Diet(
SupID int NOT NULL ,
DType char(10),
AID int,
PRIMARY KEY (DType, AID),
FOREIGN KEY (SupID) REFERENCES FoodSupplier(SupID) ON DELETE CASCADE,
FOREIGN KEY (DType, AID) REFERENCES Animal_Diet(DType, AID) ON DELETE CASCADE
);

CREATE TABLE MedSupplier_Medication(
SupID int NOT NULL,
MedID int,
PRIMARY KEY (MedID),
FOREIGN KEY (SupID) REFERENCES MedSupplier(SupID) ON DELETE CASCADE,
FOREIGN KEY (MedID) REFERENCES Medication(MedID) ON DELETE CASCADE
);

CREATE TABLE Animal_Sanctuary(
SanctID int NOT NULL,
AID int,
PRIMARY KEY (AID),
FOREIGN KEY (SanctID) REFERENCES Sanctuary(SanctID) ON DELETE CASCADE,
FOREIGN KEY (AID) REFERENCES Animal(AID) ON DELETE CASCADE
);

CREATE TABLE Medication_Examination(
EID int,
MedID int,
Dosage char(20),
PRIMARY KEY (EID,MedID),
FOREIGN KEY (EID) REFERENCES Examination(EID) ON DELETE CASCADE,
FOREIGN KEY(MedID) REFERENCES Medication(MedID) ON DELETE CASCADE
);

CREATE TABLE Vet_Examination_Animal(
AID int,
VID int,
EID int,
PRIMARY KEY (AID,VID,EID),
FOREIGN KEY (AID) REFERENCES Animal(AID) ON DELETE CASCADE,
FOREIGN KEY (VID) REFERENCES Veterinarian(SID) ON DELETE CASCADE,
FOREIGN KEY (EID) REFERENCES Examination(EID) ON DELETE CASCADE
);

CREATE TABLE Vet_Specialization(
VID int,
SpecID int NOT NULL,
PRIMARY KEY (VID),
FOREIGN KEY (VID) REFERENCES Veterinarian(SID) ON DELETE CASCADE,
FOREIGN KEY (SpecID) REFERENCES Specialization(SpecID) ON DELETE CASCADE
);

CREATE TABLE Animal_Handler(
AID int,
HID int NOT NULL,
PRIMARY KEY (AID),
FOREIGN KEY (AID) REFERENCES Animal(AID) ON DELETE CASCADE,
FOREIGN KEY (HID) REFERENCES Handler(SID) ON DELETE CASCADE
);

insert into animalspecies values ('Northern Giraffe', 'Mammal');
insert into animalspecies values ('Western Lowland Gorilla', 'Mammal');
insert into animalspecies values ('Sea Otter', 'Mammal');
insert into animalspecies values ('Natterjack Toad', 'Amphibian');
insert into animalspecies values ('Peregrine Falcon', 'Bird of Prey');

insert into animal values (1, 'Jerry', 'Northern Giraffe');
insert into animal values (2, 'Gary', 'Western Lowland Gorilla');
insert into animal values (3, 'Ollie', 'Sea Otter');
insert into animal values (4, 'Teddy', 'Natterjack Toad');
insert into animal values (5, 'Pete', 'Peregrine Falcon');

insert into animal_diet values ('Herbivore', '30 kg', 1);
insert into animal_diet values ('Herbivore', '30 kg', 2);
insert into animal_diet values ('Carnivore', '7 kg', 3);
insert into animal_diet values ('Omnivore', '1-3 food items', 4);
insert into animal_diet values ('Carnivore', '70 g', 5);

insert into sanctuaryname values ('Otter Aquarium', 'Aquarium');
insert into sanctuaryname values ('Giraffe Savannah', 'Savannah');
insert into sanctuaryname values ('Gorilla Rainforest', 'Rainforest');
insert into sanctuaryname values ('Falcon Enclosure', 'Avian Enclosure');
insert into sanctuaryname values ('Toad Marshes', 'Marsh');

insert into sanctuary values (1, 'Otter Aquarium', 400);
insert into sanctuary values (2, 'Giraffe Savannah', 1500);
insert into sanctuary values (3, 'Gorilla Rainforest', 1000);
insert into sanctuary values (4, 'Falcon Enclosure', 400);
insert into sanctuary values (5, 'Toad Marshes', 100);

insert into animal_sanctuary values (2, 1);
insert into animal_sanctuary values (3, 2);
insert into animal_sanctuary values (1, 3);
insert into animal_sanctuary values (5, 4);
insert into animal_sanctuary values (4, 5);

insert into examination values (1, '05-Nov-16', 'Rabies Vaccine', 'Vaccine');
insert into examination values (2, '20-Jan-16', 'Anxiety Examination', 'Psychological');
insert into examination values (3, '06-Apr-18', 'Depression Examination', 'Psychological');
insert into examination values (4, '30-Oct-19', 'Aggressive Behaviour Examination', 'Psychological');
insert into examination values (5, '28-Feb-20', 'Deworming Medication', 'Vaccine');
insert into examination values (6, '15-Mar-17', 'Rabies Vaccine', 'Vaccine');
insert into examination values (7, '30-Dec-19', 'Physical Examination', 'Physical');
insert into examination values (8, '02-May-20', 'Anxiety Examination', 'Psychological');
insert into examination values (9, '24-Oct-18', 'Deworming Medication', 'Vaccine');

insert into medication values (1, 'Rabies Vaccine', 'Vaccine');
insert into medication values (2, 'Diazepam', 'Anxiety Medication');
insert into medication values (3, 'Prozac', 'Antidepressant');
insert into medication values (4, 'Haloperidol', 'Antipsychotic');
insert into medication values (5, 'Mebendazole', 'De-wormer');

insert into medication_examination values (1, 1, '1.0 mL');
insert into medication_examination values (2, 2, '1 pill');
insert into medication_examination values (3, 3, '1 pill');
insert into medication_examination values (4, 4, '1 pill');
insert into medication_examination values (5, 5, '5.0 mL');
insert into medication_examination values (6, 1, '1.0 mL');
insert into medication_examination values (8, 2, '1 pill');
insert into medication_examination values (9, 5, '5.0 mL'); 

insert into supplier values (1, 'ABC Inc.', 'Vancouver', 'Canada');
insert into supplier values (2, 'FoodSuppers', 'Los Angeles', 'USA');
insert into supplier values (3, 'GoodFoods', 'Dallas', 'USA');
insert into supplier values (4, 'GHJ Inc.', 'Portland','USA');
insert into supplier values (5, 'Sunshine Suppliers', 'Toronto', 'Canada');
insert into supplier values (6, 'XYZ Inc.', 'Amsterdam','Netherlands');
insert into supplier values (7, 'MedSupplies', 'Rome','Italy');
insert into supplier values (8, 'GoodMeds', 'Pokhara','Nepal');
insert into supplier values (9, 'MDL Inc.', 'Barcelona','Spain');
insert into supplier values (10, 'Moonlight Suppliers', 'Yokohama','Japan');

insert into foodsupplier values (1);
insert into foodsupplier values (2);
insert into foodsupplier values (3);
insert into foodsupplier values (4);
insert into foodsupplier values (5);

insert into medsupplier values (6);
insert into medsupplier values (7);
insert into medsupplier values (8);
insert into medsupplier values (9);
insert into medsupplier values (10);

insert into staff values (1, 'Hugo Strange', '604 123 5421', '123 Strange Rd.', 123456789);
insert into staff values (2, 'Victor Fries', '604 345 3422', '342 Freeze Ave.', 234567890);
insert into staff values (3, 'Diana Prince', '604 899 0989', '132 Themyscira Dr.', 987654321);
insert into staff values (4, 'Pamela Isley', '604 689 4243', '432 Vine St.', 098765432);
insert into staff values (5, 'Jonathan Crane', '604 877 6554', '667 Fear Dr.', 111222334);
insert into staff values (6, 'John Stewart', '778 543 5432', '332 Lantern Ave.', 256680975);
insert into staff values (7, 'Bruce Wayne', '778 123 6789', '321 Crime Alley', 125790843);
insert into staff values (8, 'Bill Batson', '778 092 3456', '484 Shazam Ave.', 872401389);
insert into staff values (9, 'Selina Kyle', '778 988 2211', '444 Feline Str.', 231344990);
insert into staff values (10, 'Basil Karlo', '778 124 7664', '992 Clay Rd.', 212345785);

insert into veterinarian values (1);
insert into veterinarian values (2);
insert into veterinarian values (3);
insert into veterinarian values (4);
insert into veterinarian values (5);

insert into handler values (6);
insert into handler values (7);
insert into handler values (8);
insert into handler values (9);
insert into handler values (10);

insert into animal_handler values (1, 9);
insert into animal_handler values (2, 6);
insert into animal_handler values (3, 7);
insert into animal_handler values (4, 10);
insert into animal_handler values (5, 8);

insert into specialization values (1, 'Large Mammals Specialist');
insert into specialization values (2, 'Small Mammals Specialist');
insert into specialization values (3, 'Ornithologist');
insert into specialization values (4, 'Herpetologist');
insert into specialization values (5, 'Entomologist');

insert into vet_specialization values (1, 5);
insert into vet_specialization values (2, 2);
insert into vet_specialization values (3, 1);
insert into vet_specialization values (4, 3);
insert into vet_specialization values (5, 4);

insert into foodsupplier_diet values (1, 'Herbivore', 1);
insert into foodsupplier_diet values (2, 'Herbivore', 2);
insert into foodsupplier_diet values (3, 'Carnivore', 3);
insert into foodsupplier_diet values (4, 'Omnivore', 4);
insert into foodsupplier_diet values (5, 'Carnivore', 5);

insert into medsupplier_medication values (6, 3);
insert into medsupplier_medication values (6, 2);
insert into medsupplier_medication values (7, 4);
insert into medsupplier_medication values (8, 1);
insert into medsupplier_medication values (9, 5);

insert into vet_examination_animal values (1, 2, 9);
insert into vet_examination_animal values (2, 2, 8);
insert into vet_examination_animal values (3, 2, 1);
insert into vet_examination_animal values (4, 2, 6);
insert into vet_examination_animal values (5, 2, 7);
insert into vet_examination_animal values (2, 4, 3);
insert into vet_examination_animal values (3, 5, 2);
insert into vet_examination_animal values (4, 3, 5);
insert into vet_examination_animal values (5, 1, 4);

select table_name from user_tables;
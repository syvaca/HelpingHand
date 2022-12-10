DROP DATABASE if exists Project;
CREATE DATABASE Project;

CREATE TABLE Project.user(
    ID int(10) primary key not null auto_increment,
    fname varchar(100) not null,
    lname varchar(100) not null,
    pword varchar(100) not null,
    usc_email varchar(100) not null, 
    phone_number varchar(100) not null, 
    validated tinyint(1)
); 

CREATE TABLE Project.job_postings(
    ID int(10) primary key not null auto_increment,
    email varchar(100) not null,
    descript varchar(100) not null,
    cost int(11) not null,
    assembly_tag tinyint(1),
    cleaning_tag tinyint(1),
    storage_tag tinyint(1),
    repair_tag tinyint(1),
    petCare_tag tinyint(1),
    childCare_tag tinyint(1),
    mealPrep_tag tinyint(1),
    IThelp_tag tinyint(1),
    books_tag tinyint(1),
    sublets_tag tinyint(1),
    other_tag tinyint(1)
); 

INSERT INTO Project.user(fname, lname, pword, usc_email, phone_number, validated)
VALUES 
("joojoo", "luka", "joojooluca666", "jsriniva@usc.edu", 6509606018, 0);

INSERT INTO Project.job_postings(email, descript, cost, assembly_tag, cleaning_tag, storage_tag, repair_tag, petCare_tag, childCare_tag, mealPrep_tag, IThelp_tag, books_tag, sublets_tag , other_tag)
VALUES 
("jsriniva@usc.edu", "I need you", 69, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

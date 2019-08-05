CREATE DATABASE IF NOT EXISTS HR_MANAGEMENT;

USE HR_MANAGEMENT;

CREATE TABLE IF NOT EXISTS Employee ( EID varchar(10) NOT NULL, Title varchar(64) NOT NULL,   Name varchar(64) NOT NULL, Gender varchar(5) NOT NULL, DOB DATE NOT NULL, Date_Of_Joining DATE NOT NULL, Address varchar(255) NOT NULL, Type_of_employee varchar(64) NOT NULL, Highest_Degree varchar(64) NOT NULL, Designation varchar(64) NOT NULL, Phone_No varchar(64) NOT NULL, Contact_Info varchar(64) NOT NULL, PRIMARY KEY(EID) );

CREATE TABLE IF NOT EXISTS Roles( RID int NOT NULL, PRIMARY KEY(RID), EID varchar(10) NOT NULL, FOREIGN KEY(EID) REFERENCES Employee(EID), Role_Title varchar(64) NOT NULL, Privileges varchar(100), Email_Id char(50) NOT NULL);

CREATE TABLE IF NOT EXISTS Leave_Applications ( AID int NOT NULL, PRIMARY KEY(AID), EID varchar(10) NOT NULL, FOREIGN KEY (EID) REFERENCES Employee(EID), Application_Date DATE NOT NULL, Type_Of_Leave varchar(64) NOT NULL, Reason_For_Leave  varchar(255) NOT NULL, Start_Date DATE NOT NULL, End_Date DATE NOT NULL, HOD_Status TINYINT(1), Registrar_Status TINYINT(1), DOFA_Status TINYINT(1), Director_Status TINYINT(1), Application_Status TINYINT(1) DEFAULT 2)

CREATE TABLE IF NOT EXISTS Non_Teaching_staff( EID varchar(10) NOT NULL, FOREIGN KEY(EID) REFERENCES Employee(EID), Office varchar(64) NOT NULL);

CREATE TABLE IF NOT EXISTS Teaching_Staff( EID varchar(10) NOT NULL, FOREIGN KEY(EID) REFERENCES Employee(EID), Department varchar(64) NOT NULL, Research_Topics varchar(255), Committees varchar(255));

CREATE TABLE IF NOT EXISTS Leaves_Left( EID varchar(10) NOT NULL, FOREIGN KEY(EID) REFERENCES Employee(EID), CL varchar(10) NOT NULL, PL varchar(10) NOT NULL, CCL varchar(10) NOT NULL, ODL varchar(10) NOT NULL);

CREATE TABLE IF NOT EXISTS Appraisal( EID varchar(10) NOT NULL, FOREIGN KEY(EID) REFERENCES Employee(EID), Head_Of_Department varchar(100), Faculty_Mentor varchar(100));
                                                                                                                                                                                                                                                        
CREATE TABLE IF NOT EXISTS Conference( EID varchar(10) NOT NULL, FOREIGN KEY(EID) REFERENCES Employee(EID), Conference_Topic varchar(255) NOT NULL, Description varchar(255), Attended_OR_Conducted varchar(100), Start_Date DATE NOT NULL, End_Date DATE NOT NULL, Whether_Speaker TINYINT(1) NOT NULL);                                                                                                           
                                                                                                           
CREATE TABLE IF NOT EXISTS Paper( EID varchar(10) NOT NULL, FOREIGN KEY(EID) REFERENCES Employee(EID), Paper_Title varchar(255) NOT NULL, Description varchar(255), Journals varchar(255), Whether_First_Author TINYINT(1) NOT NULL);                                                                                                           
                                                                                                                                             
                                                                                                                                             

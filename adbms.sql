/**
	Assignment Date :-	4/10/2020
	Step 1 -> Create a new table name STUDENT	
	
**/
CREATE TABLE STUDENT (
  ID INT    NOT NULL,
  NAME VARCHAR(20)  NOT NULL,
  AGE INT   NOT NULL,
  ADDRESS VARCHAR(50)   NOT NULL,
  PRIMARY KEY(ID)
);


/**
	Step 2 -> Create a new table name STUDENT_BACKUP
**/
CREATE TABLE STUDENT_BACKUP AS SELECT *FROM STUDENT;
ALTER TABLE STUDENT_BACKUP ADD (OPERATIONS VARCHAR(20));


/**
	Step 3 -> Create a new table name STUDENT_MARKS
**/
CREATE TABLE STUDENT_MARKS 
(
  ROLL_NO VARCHAR2(20) NOT NULL 
, NAME VARCHAR2(20) NOT NULL 
, SUB1 INT NOT NULL 
, SUB2 INT NOT NULL 
, TOTAL_PERCENT NUMBER(3,2) NOT NULL 
, DIVISION CHAR NOT NULL 
);


/**
	Step 4 -> Create a new table name EMPLOYEE
**/
CREATE TABLE EMPLOYEE  
(  
EMPLOYEE_ID INT,  
FIRST_NAME VARCHAR2(255),  
LAST_NAME VARCHAR2(255),  
EMAIL VARCHAR2(255),  
ADDRESS_LINE VARCHAR2(255),  
CITY VARCHAR2(255)  
);  


/**
	Step 5 -> Create a new table name EMPLOYEE_BACKUP
**/
CREATE TABLE EMPLOYEE_BACKUP AS SELECT *FROM EMPLOYEE;
ALTER TABLE EMPLOYEE_BACKUP ADD (OPERATIONS VARCHAR(20));


/**
	Step 6 -> Update trigger to take STUDENT_BACKUP
**/
create or replace TRIGGER TRG_STUDENT
    BEFORE UPDATE 
    ON STUDENT
    FOR EACH ROW
BEGIN
  INSERT INTO STUDENT_BACKUP(ID, NAME, AGE, ADDRESS, OPERATIONS) VALUES(:NEW.ID, :NEW.NAME, :NEW.AGE, :NEW.ADDRESS, 'UPDATE');
END;


/**
	Step 7 -> Update trigger to take STUDENT_BACKUP
**/
create or replace TRIGGER TRG_STUDENT_DEL
    AFTER DELETE 
    ON STUDENT
    FOR EACH ROW
BEGIN
  SYS.DBMS_OUTPUT.PUT_LINE('you deleted a row');
  INSERT INTO STUDENT_BACKUP(ID, NAME, AGE, ADDRESS, OPERATIONS) VALUES(:OLD.ID, :OLD.NAME, :OLD.AGE, :OLD.ADDRESS, 'you deleted the row');
END;

insert into student values (5, 'E', 17, 'Gurgaon');
delete from student where id = 5;


/**
	Step 8 ->  Update trigger to take Student marks
**/
create or replace TRIGGER TRG_STUDENT_MARKS
    BEFORE INSERT OR UPDATE 
    ON STUDENT_MARKS
    FOR EACH ROW
BEGIN
  :NEW.TOTAL_PERCENT := ((:NEW.SUB1 + :NEW.SUB2)/2);
  IF :NEW.TOTAL_PERCENT >= 60 THEN 
    :NEW.DIVISION := 'A';
  ELSIF :NEW.TOTAL_PERCENT >= 45 AND :NEW.TOTAL_PERCENT < 60  THEN 
    :NEW.DIVISION := 'B';
  ELSIF :NEW.TOTAL_PERCENT >= 33 AND :NEW.TOTAL_PERCENT < 45  THEN 
    :NEW.DIVISION := 'C';  
  ELSE
    :NEW.DIVISION := 'F';
  END IF;
END;


insert into STUDENT_MARKS(ROLL_NO, NAME, SUB1, SUB2) values(1, 'AAA', 60, 62);
insert into STUDENT_MARKS(ROLL_NO, NAME, SUB1, SUB2) values(2, 'BBB', 50, 62);
insert into STUDENT_MARKS(ROLL_NO, NAME, SUB1, SUB2) values(3, 'CCC', 30, 50);
insert into STUDENT_MARKS(ROLL_NO, NAME, SUB1, SUB2) values(4, 'DDD', 23, 32);


/**
	Step 9 -> Update trigger employee
**/
create or replace TRIGGER TRG_EMPLOYEE
    BEFORE INSERT OR UPDATE ON EMPLOYEE
    FOR EACH ROW
BEGIN
    IF :NEW.AGE < 25 THEN
        RAISE_APPLICATION_ERROR(-20000, 'Employee below age 25 is not eligible.');
    END IF;
END;

insert into employee(EMPLOYEE_ID, AGE) values(1, 24);


/**
	Step 10 -> Update trigger employee_del
**/
create or replace TRIGGER TRG_EMPLOYEE_DEL
    BEFORE DELETE ON EMPLOYEE
    FOR EACH ROW
BEGIN
    INSERT INTO EMPLOYEE_BACKUP(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, ADDRESS_LINE, CITY, AGE, OPERATIONS) VALUES(:OLD.EMPLOYEE_ID, :OLD.FIRST_NAME, :OLD.LAST_NAME, :OLD.EMAIL, :OLD.ADDRESS_LINE, :OLD.CITY, :OLD.AGE, 'Delete');
END;


/**
	Step 11 -> Counting number of tuples
**/
create or replace TRIGGER TRG_EMPLOYEE_CNT
    BEFORE INSERT ON EMPLOYEE
    FOR EACH ROW
DECLARE
    CNT INT;
BEGIN
    Select COUNT(*) INTO CNT FROM EMPLOYEE;
    SYS.DBMS_OUTPUT.PUT (CNT || 'Records present in employee table');
 END;
 
 insert into employee(EMPLOYEE_ID, AGE) values(1, 30);

SET SERVEROUTPUT ON
 
BEGIN
 Dbms_Output.Put_Line(Systimestamp);
END;
/


/**
	Step 11 -> Reverse of a number
**/
SET SERVEROUTPUT ON; 
DECLARE 
NUM NUMBER; 
REV NUMBER; 
BEGIN
NUM :=12345;
REV :=0; 
WHILE NUM>0 LOOP 
REV:=(REV*10) + MOD(NUM,10); 
NUM:=FLOOR(NUM/10); 
END LOOP; 
DBMS_OUTPUT.PUT_LINE('Reversed number is: ' || REV); 
END; 
/  


/**
	Step 12 -> Factorial of a number
**/
SET SERVEROUTPUT ON; 
DECLARE 
NUM NUMBER;
FACT NUMBER;
BEGIN
NUM :=5;
FACT :=1;
WHILE NUM>1 LOOP 
FACT:= FACT*NUM;
NUM:= NUM-1;
END LOOP; 
DBMS_OUTPUT.PUT_LINE('Factorial of 5 number is: ' || FACT); 
END; 
/  


/**
	Step 12 -> Squareroot of a number
**/
SET SERVEROUTPUT ON; 
DECLARE 
NUM NUMBER;
SROOT NUMBER;
BEGIN
NUM :=2;
select sqrt(NUM) into SROOT from dual; 
DBMS_OUTPUT.PUT_LINE('Square root of 25 number is: ' || SROOT); 
END; 
/  


/**
	Step 13 -> Cursor to roll no. and name
**/
SET SERVEROUTPUT ON;
DECLARE 

ROLL_NO NUMBER;
NAME VARCHAR2(200);
CURSOR STUDENT_RECORDS IS SELECT * FROM STUDENT;
BEGIN
FOR STUDENT_RECORD IN STUDENT_RECORDS
lOOP
ROLL_NO:= STUDENT_RECORD.ID;
NAME:= STUDENT_RECORD.NAME;
SYS.Dbms_Output.Put_Line('ROLL NO.' || ROLL_NO || ' NAME ' || NAME);
END LOOP;

END;
/


/**
	Step 14 -> Implicit cursor Cursor employee salary
**/
DECLARE  
   total_rows number(2); 
BEGIN 
   UPDATE EMPLOYEE 
   SET salary = salary + 500 WHERE AGE > 30; 
   IF sql%notfound THEN 
      dbms_output.put_line('no employee selected'); 
   ELSIF sql%found THEN 
      total_rows := sql%rowcount;
      dbms_output.put_line( total_rows || ' employee selected '); 
   END IF;  
END; 
/   




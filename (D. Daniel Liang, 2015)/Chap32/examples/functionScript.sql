
-- DROP FUNCTION IF EXISTS studentFound;

DELIMITER //

CREATE FUNCTION studentFound(fName VARCHAR(20), lName VARCHAR(20))
    RETURNS INT
BEGIN
    DECLARE result INT;

    SELECT COUNT(*) INTO result
    FROM student
    WHERE student.firstName = fName AND
        student.lastName = lName;
    RETURN result;
END; //

DELIMITER ;

/* To use this function:

    SELECT studentFound("curtis", "newbie");
    
*/
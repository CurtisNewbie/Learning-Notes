DELIMITER //
CREATE PROCEDURE findStudent(IN fName VARCHAR(20), IN lName VARCHAR(20), OUT id CHAR(5))

BEGIN
	SELECT studentId INTO id FROM student WHERE fName = student.firstName AND lName = student.lastName;
END; //

DELIMITER ;
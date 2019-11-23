/* Stored Procedure */
DELIMITER //
CREATE procedure insertCustomer(IN firstN VARCHAR(30), IN lastN VARCHAR(30))
 
BEGIN
INSERT INTO customer (firstName, lastName) VALUES (firstN, lastN);
END //
DELIMITER ;
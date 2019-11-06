/*
Index:
    1. Terminology
    2. Login
    3. Load Scripts
    4. List Items
    5. SELECT Statement
    6. ORDER BY Statement
    7. Alias
*/

-------------------------------

-- 1. Terminology

------------------------------

/* Statement delimiter */
> ;  

-------------------------------

-- 2. Login

------------------------------

/* Login using credentials */
mysql -u <username> -p

/* Login as root */
mysql -u root -p

-------------------------------

-- 3. Load Scripts

------------------------------

/* Load scripts */
SOURCE "path_To_Sql_Script"

-------------------------------

-- 4. List Items

------------------------------

/* List all databses */
SHOW DATABASES;

/* List all tables */
SHOW TABLES;

-------------------------------

-- 5. SELECT Statement

------------------------------

/** SELECT statement to retrieve data */
SELECT column FROM tableName;

/* Convention: Place FROM keyword on a new line */
SELECT col
FROM tab;

/* 
    When MySQL evaluates SELECT statement, the FROM clause is evaluated first, then the SELECT clause.
*/

/* Retrieve data from multiple columns */
SELECT 
    col1,
    col2,
    col3
FROM 
    tab;

/* It is a good practice to use the SELECT * for the ad-hoc queries only. Since it may leaks data, produce unnecessary I/O and is less easier to predict.*/
SELECT * 

-------------------------------

-- 6. ORDER BY Statement

------------------------------

/* Select "col" from table "tab", where the results are sorted by col1 in an ascending or descending order. */
SELECT
    col1,
    col2
FROM
    tab
ORDER BY
    col1 [ASC|DECS];

/* This is possible to sort the results by multiple columns. The results are sorted by col1 first in ascending order, then the sorted results are further sorted by the col2. Ascending order is the default sorting order. */
SELECT
    col1,
    col2
FROM
    tab
ORDER BY
    col1,
    col2;

/* It is also possible to sort the results by multiple columns in different orders. It first sorts the results by col1 in ascending order, and then sorts the sorted results by col2 in descending order. */
SELECT
    col1,
    col2
FROM
    tab
ORDER BY
    col1 ASC,
    col2 DESC;

/* 
    When MySQL evaluates ORDER BY statement, the FROM clause is evaluated first, then the SELECT clause, and finally the ORDER BY clause.
*/

/* When using ORDER BY statement, it's not necessary to use the selected columns for sorting. E.g., col3 is a column in table tab */
SELECT
    col1,
    col2
FROM
    tab
ORDER BY
    col3 ASC;

/* Using ORDER BY with an expression. The following example calculates the quantityOrdered * priceEach, then uses the calculated result to sort the result set in descending order. */
SELECT 
    orderNumber 'Order No.', 
    orderLineNumber 'Line No.', 
    quantityOrdered * priceEach 'Total Price'
FROM
    orderdetails
ORDER BY 
   quantityOrdered * priceEach DESC
LIMIT 
    5;

/* Using ORDER BY with the function FIELD(), i.e., sorts the results set based on the values returned by the FIELD() function. With the ORDER BY and FIELD(), results sets are sorted based on the values of this specified column. 

FIELD function takes two types of arguments, a column name (or alias) or a string value to be searched, and a list of string values. The FIELD() function maps each string value to a number/index, and this method returns the index of the found value.

    FIELD(<string/column_to_be_searched>, <String1>, <String2>...). 

For example, in the following example, the results are sorted by values of the column orderStatus in each row, as for each value an index is returned that can be used for sorting.  For 'In Process',this function returns 1, for 'On Hold' returns 2. */
SELECT 
    orderNumber, 
    orderStatus
FROM
    orders
ORDER BY 
    FIELD(orderStatus,
        'In Process', -- return 1
        'On Hold',    -- return 2
        'Cancelled',  -- return 3
        'Resolved',   -- return 4
        'Disputed',   -- return 5
        'Shipped');   -- return 6

/* Another example for FIELD() */
SELECT DISTINCT status, FIELD(status, 'In Process', 'On Hold', 'Cancelled', 'Resolved', 'Disputed', 'Shipped') As 'FIELD Function'  FROM orders LIMIT 5;
/*
+-----------+----------------+
| status    | FIELD Function |
+-----------+----------------+
| Shipped   |              6 |
| Resolved  |              4 |
| Cancelled |              3 |
| On Hold   |              2 |
| Disputed  |              5 |
+-----------+----------------+
*/

-------------------------------

-- 7. Alias

------------------------------

/* Alias for columns */
SELECT
    col AS column
FROM
    tab;

/* Alias with spaces for columns */
SELECT
    col 'Column Name'
FROM
    tab;

/* Alias can be used in ORDER BY clause, as it is first evaluated by SELECT statement and ORDER BY is evaluated after SELECT statement. */
SELECT 
    priceEach * quantityOrdered as totalPrice 
FROM 
    orderdetails 
ORDER BY 
    totalPrice 
LIMIT 
    5;




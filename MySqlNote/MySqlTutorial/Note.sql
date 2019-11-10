/*
Index:
    1. Terminology
    2. Login
    3. Load Scripts
    4. List Items
    5. SELECT Statement
    6. ORDER BY Statement
    7. Alias
    8. WHERE Clause, AND, OR NOT, BETWEEN AND, IN, LIKE, IS NULL
    9. Distinct clause
    10. AND Operator
    11. OR Operator
    12. IN operator and NOT IN operator
    13. BETWEEEN AND operator, NOT and dates
    14. LIKE operator
    15. LIMIT clause
    16. IS NULL, IS NOT NULL
    17. Alias
    18. Join
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

-------------------------------

-- 8. WHERE Clause, AND, OR NOT, BETWEEN AND, IN, LIKE, IS NULL

------------------------------

/* WHERE clause specifies the search condition for the rows returned by the query. The search condition is a combination of one or more predicates, that may involve using AND, OR and NOT operators. */
SELECT
    col
FROM
    tab
WHERE
    num = 1 OR num = 2;
    
/*
For the WHERE clause, the order of evaluation is as follows:
    FROM -> WHERE -> SELECT -> ORDER BY
*/

/* WHERE with AND operator */
SELECT
    col
FROM 
    tab
WHERE
    UPPER(job) = 'SALES REP' AND
    money = 15;
ORDER BY
    col ASC;

/* WHERE with OR operator */
SELECT
    col
FROM
    tab
WHERE
    UPPER(job) = 'SALES REP' OR
    jobCode = 1;

/* WHERE with BETWEEN [] AND [] operators*/
SELECT
    col
FROM
    tab
WHERE
    officeCode BETWEEN 1 AND 3;

/* WHERE with LIKE operator. % wildcard matches any 0 or more chars, and _ wildcard mathces one single char. */
SELECT
    fname
FROM
    tab
WHERE
    fname like '%curtis_me'

/* WHERE with IN operator. Select within a range of values. */
SELECT
    title
FROM
    job
WHERE
    code IN (1, 2, 3);

/* WHERE with IS NULL and IS NOT NULL operators */
SELECT
    title
FROM
    job
WHERE
    office IS NULL;

SELECT
    title
FROM
    job
WHERE
    office IS NOT NULL;

/* 
Comparison operators:
    =
    <> or !=   means not equal to
    <
    >
    <=
    >=
*/
SELECT 
    * 
FROM 
    employees 
WHERE 
    officeCode <> 1;

-------------------------------

-- 9. Distinct clause

------------------------------

/* Basic syntax of DISTINCT clause */
SELECT DISTINCT
    *
FROM
    employees;

SELECT
    DISTINCT fname
FROM
    employees;

/* DISTINCT and NULL, In case where there are more than one NULL, it returns only one */
SELECT 
    DISTINCT state 
FROM 
    customers;

/* DISTINCT with multiple columns, it checks the unique combination of all the columns after DISTINCT clause */
SELECT DISTINCT
    col1, col2
FROM
    table1
WHERE
    col1 IS NOT NULL
ORDER BY 
    col1, 
    col2;

/* Using GROUP BY without Aggregate Funtions (e.g., SUM(), COUNT()), it acts like DISTINCT clause */
SELECT 
    col1
FROM
    table1
GROUP BY 
    col1;
-- is the same as (before mysql 8.0, it has additional sorting with GROUP BY) follows:
SELECT DISTINCT
    col1
FROM
    table1;

/* Using DISTINCT with Aggregate Functions */
SELECT 
    COUNT(DISTINCT col1)
FROM
    table1
WHERE cal1 like '%abc';

-------------------------------

-- 10. AND Operator

------------------------------
...
WHERE
    expression1 AND expression2;

...
WHERE 
    (expresssion1 OR expression2) AND expression3

-------------------------------

-- 11. OR Operator

------------------------------

...
WHERE
    expression1 OR expression2;

-------------------------------

-- 12. IN operator and NOT IN operator

-- (It internally sorts the list of values and uses binary search, thus it's very fast.)

------------------------------

/* Return true if col1 or col2 matches the values in the bracket */
...
WHERE  
    (col1|col2) IN ('value1', 'value2', ...);

/* Return true if the result of the expression1 or col1 mathces the values in the bracket */
...
WHERE
    (expression1|col1) IN ('value1', 'value2', ...);

...
WHERE
    (expression1|col1) NOT IN ('value1', 'value2', ...);

/* IN operator with subquery */
SELECT 
    *
FROM
    orders
WHERE
    orderNumber IN (
                    SELECT
                        orderNumber
                    FROM 
                        orderdetails
                    GROUP BY 
                        orderNumber
                    HAVING
                        SUM(quantityOrdered * priceEach) > 60000
                    );

-------------------------------

-- 13. BETWEEEN AND operator, NOT and dates

------------------------------

/* BETWEEN AND  */
...
WHERE
    col BETWEEN 100 AND 150;

/* BETWEEN AND and NOT clause */
...
WHERE
    col NOT BETWEEN 100 AND 150;

/* BETWEEN AND and NULL value, it returns NULL if any expression returns NULL */
...
WHERE
    col BETWEEN NULL AND 150;

/* Using expression with BETWEEN AND */
...
WHERE
    expression1 BETWEEN 100 AND 150;

...
WHERE
    expression1 BETWEEN expression2 AND expression3;

/* BETWEEN AND dates. CAST() function cast types */
...
WHERE
    orderdate BETWEEN 
        CAST('2019-01-01' AS DATE) AND
        CAST('2019-01-05' AS DATE);

-------------------------------

-- 14. LIKE operator

------------------------------

/* Use LIKE to match pattern, % wildcard for string of any lenght, and _ wildcard for one char. (case-insensitive)*/
...
WHERE
    fname LIKE 'curtis_y%';


/* Use LIKE with NOT */
...
WHERE
    fname NOT LIKE 'curtis_y%';

/* Use LIKE with ESCAPE or '\', the _ is escaped */
...
WHERE
    code LIKE '%\_20%';

/* Make '$' a escaped character, so that _ is escaped. The default escape character is \ */
...
WHERE
    code LIKE '%$_20$' ESCAPE '$';

-------------------------------

-- 15. LIMIT clause

------------------------------

/* LIMIT row count, return rowCount number of rows, starting from the first row returned */
FROM
    ...
LIMIT rowCount;

/* LIMIT offset and row count, starting from the offset (exclusive), 
e.g., if offset=4, the results returned starts from row 5. */
FROM
    ...
LIMIT [offset] , [rowCount];

FROM
    ...
LIMIT 5, 4;

/* Use LIMIT to find the one with largest value or least value */
FROM
    ...
ORDER BY 
    score DESC
LIMIT
    1;

/* Use LIMIT to find the nth largest value or nth least value. This example find 2nd largest */
FROM
    ...
ORDER BY
    score DESC
limit 1, 1;

-------------------------------

-- 16. IS NULL, IS NOT NULL 

------------------------------

...
    score IS NULL;

...
    score IS NOT NULL;

/* Special features for IS NULL and Date type, if the DATE column is defined to have a NOT NULL, 
IS NULL can be used to find DATE type with a value of '0000-00-00' */
SELECT
    *
FROM
    project_details
WHERE
    complete_date IS NULL; -- search for '0000-00-00'

-------------------------------

-- 17. Alias 

/* There is a difference between ' nad `, where '' or "" are used to dilimits strings, while `` are used for delimits identifiers. */

-- https://stackoverflow.com/questions/2672945/mysql-difference-between-%C2%B4-and

------------------------------

/* Alias for column */
SELECT
    col1 AS alias
FROM 
    table1;

/* Alias for expression. Here using delimiters '' are fine, as it is not used as identifier. */
SELECT
    expression1 AS 'alias with space'
FROM
    table1;

/* AS is optional and can be omited. Here using delimiters '' are fine, as it is not used as identifier. */
SELECT
    expression1 'alias with space'
FROM
    table1;

/* CONCAT_WS() is a function for concatenation with seperator. The first string is a seperator for the following list of string. Here using delimiters '' are fine, as it is not used as identifier.*/
SELECT
    CONCAT_WS(', ', lastName, firstName) AS 'Full Name'
FROM 
    employees
LIMIT
    5;

/* CONCAT() is a function for concatenation, note that || in Oracle doesn't work in MySQL unless config changed. Here using delimiters '' are fine, as it is not used as identifier. */
SELECT
    CONCAT(lastName, ', ', firstName) AS 'Full Name'
FROM
    employees
LIMIT
    5;

/* Column alisa can be used in ORDER BY, GROUP BY and HAVING clauses once they are defined. Must use `` as it is used for identifier, else it won't sort the result sets. */
SELECT
    CONCAT_WS(', ', lastName, firstname) `Full name`
FROM
    employees
ORDER BY
    `Full name`
LIMIT
    5;

/* Example of using Alias in GROUP BY and HAVING clauses. Note that it can't be used in WHERE, as WHERE is evaluated first. */
SELECT 
    orderNumber `Order no.`, SUM(quantityOrdered * priceEach) `Total`
FROM 
    orderDetails
GROUP BY 
    `Order no.`
HAVING 
    `Total` > 60000;

/* Alias for tables. AS keyword is optional and can be omitted, once the alias is used it must be used everywhere. Note that the order of evaluation is FROM -> WHERE -> SELECT -> ORDER BY*/
SELECT 
    e.firstName 
FROM 
    employees e 
WHERE 
    e.firstName 
LIKE 
    'A%' 
ORDER BY e.firstName;

/* Example of using column alias, table alias in different clauses. */
SELECT
    customerName, COUNT(ord.orderNumber) `Total Number Of Orders`
FROM
    customers cus, orders ord
WHERE
    cus.customerNumber = ord.customerNumber
GROUP BY 
    customerName
ORDER BY
    `Total Number Of Orders` DESC;

-------------------------------

-- 18. Join 

-- Reasons why using join instead of where if possible
    -- No difference in terms of performance
    -- Join is more readable, explicit, consistent and easy to maintain

-- https://stackoverflow.com/questions/2241991/in-mysql-queries-why-use-join-instead-of-where

------------------------------

/*
    Join is generally a method of linking data between one or more tables based on values of the common column between tables.

    MySql supports:
        1. Inner Join
        2. Left Join
        3. Right Join
        4. Cross Join
*/

/* 1. INNER JOIN - keeps matched from both tables

    "The inner join clause compares each row from the first table with every row from the second table. If values in both rows cause the join condition evaluates to true, the inner join clause creates a new row whose column contains all columns of the two rows from both tables and include this new row in the final result set. In other words, the inner join clause includes only rows whose values match."
 */

/* This query uses INNER JOIN to compare each rwo from the table1 with every row from the table2, and combine the data from both rows if the joinCondition is met. */
SELECT
    col1
FROM
    table1
INNER JOIN table2 on joinCondition;

/* In case where the = equal operator is used to compare the column from two tables, USING keyword can be used instead. */
SELECT
    col1
FROM
    table1
INNER JOIN table2 ON table1.pri_col = table2.pri_col;
-- is same as
SELECT
    col1
FROM
    table1
INNER JOIN table2 USING (pri_col);

/* Another example of INNER JOIN */
SELECT 
    m.name
FROM 
    members m
INNER JOIN committees USING (name);

SELECT 
    m.name
FROM 
    members m
INNER JOIN committees c ON m.name = c.name;

/* 2. LEFT JOIN  - keeps left matched

    The LEFT JOIN is very similar to the INNER JOIN except that "if the values in the two rows are not matched, the left join clause still creates a new row whose columns contain columns of the row in the left table and NULL for columns of the row in the right table."

*/
SELECT
    col
FROM
    table1
LEFT JOIN table2 USING (someColumn);

/* Example of LEFT JOIN. */
SELECT 
    * 
FROM 
    members 
LEFT JOIN committees USING (name);
/* Result Set:
    +--------+-----------+--------------+
    | name   | member_id | committee_id |
    +--------+-----------+--------------+
    | John   |         1 |            1 |
    | Mary   |         3 |            2 |
    | Amelia |         5 |            3 |
    | Jane   |         2 |         NULL |
    | David  |         4 |         NULL |
    +--------+-----------+--------------+
*/

/* Continued. With LEFT JOIN, if trying to find the people who are member but not in committee, simply using IS NULL operator. I.e., this query aims to find people who are only memebers (or who are only in left tables. */
SELECT 
    *
FROM
    members
LEFT JOIN committees USING (name)
WHERE committee_id IS NULL;
/* Result Set:
    +-------+-----------+--------------+
    | name  | member_id | committee_id |
    +-------+-----------+--------------+
    | Jane  |         2 |         NULL |
    | David |         4 |         NULL |
    +-------+-----------+--------------+
*/

/* 3. RIGHT JOIN - keeps right matched

    "The right join clause selects all rows from the right table and matches rows in the left table. If a row from the right table does not have matching rows from the left table, the column of the left table will have NULL in the final result set."

*/
SELECT
    col
FROM
    table1
RIGHT JOIN table2 USING (someColumn);

/* Example of RIGHT JOIN. Think of it as a oppisite version of LEFT JOIN. This query trying to find people who are in committees but not members. I.e., finding the rows that are only in committees table. */
SELECT 
    * 
FROM 
    members 
RIGHT JOIN committees USING (name)
WHERE member_id IS NULL;
/* Result Set:

    +------+--------------+-----------+
    | name | committee_id | member_id |
    +------+--------------+-----------+
    | Joe  |            4 |      NULL |
    +------+--------------+-----------+
*/

/* 4. CROSS JOIN - keeps all regardless of condition, i.e., create a cartesian product 

    "Unlike the inner join, left join, and right join, the cross join clause does not have a join condition. The right join makes a Cartesian product of rows from the joined tables. The cross join combines each row from the first table with every row from the right table to make the result set."

*/
SELECT
    *
FROM
    table1
CROSS JOIN table2;



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
    19. Group By
    20. Functions
    21. HAVING clause
    22. WITH ROLLUP & GROUPING
    23. Subquery 
    24. EXISTS
    25. Derived Table
    26. UNION Operator
    27. INSERT
    28. INSERT INTO SELECT
    29. INSERT, ON DUPLICATE KEY UPDATE
    30. INSERT IGNORE
    31. UPDATE
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
        5. Self Join

    In case where you need to join multiple tables, regardless of which join is used, it should follow a specific order, so that the table has the neede columns to join when needed. 
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

/* Example of joining multiple tables with INNER JOIN. */
SELECT 
    orders.orderNumber, 
    orders.orderDate, 
    orders.status,
    orderdetails.quantityOrdered,
    orderdetails.priceEach,
    products.productName
FROM
    orders
INNER JOIN orderdetails USING (orderNumber)
INNER JOIN products USING (productCode)
WHERE orders.orderNumber = 10100;

/* Complex conditions for INNER JOIN. 

Display order number, product name (of products in each order), and price of each product, where product code is 'S10_1678', and the priceEach is greater than 80. */
SELECT
    orders.orderNumber,
    products.productName,
    orderdetails.priceEach
FROM
    orders
INNER JOIN orderDetails 
    ON orders.orderNumber = orderdetails.orderNumber
    AND orderdetails.priceEach > 80
INNER JOIN products USING (productCode)
WHERE productCode = 'S10_1678';

/* WHERE clause and ON clause in INNER JOIN, they have equivalent effect */
SELECT 
    *
FROM
    table1
INNER JOIN table2
    ON table1.col1 = table2.col1
    AND table1.col2 = 10;
-- is same as
SELECT 
    *
FROM
    table1
INNER JOIN table2
    ON table1.col1 = table2.col1
WHERE
    table1.col2 = 10;

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

/* WHERE clause and ON clause in LEFT JOIN, they have different meanings. WHERE clause displays only the rows that meet the requirments, while ON still return the rows that doesn't meet the requirements and making the columns of table2 'NULL'. */
SELECT 
    *
FROM
    table1
LEFT JOIN table2
    ON table1.col1 = table2.col1
    AND table1.col2 = 10;
-- is different from
SELECT 
    *
FROM
    table1
LEFT JOIN table2
    ON table1.col1 = table2.col1
WHERE
    table1.col2 = 10;

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

/* WHERE clause and ON clause in RIGHT JOIN, they have different meanings. WHERE clause displays only the rows that meet the requirments, while ON clause still returns the rows that doesn't meet the requirements and making the columns of table1 'NULL'. */
SELECT 
    *
FROM
    table1
RIGHT JOIN table2
    ON table1.col1 = table2.col1
    AND table1.col2 = 10;
-- is different from
SELECT 
    *
FROM
    table1
RIGHT JOIN table2
    ON table1.col1 = table2.col1
WHERE
    table1.col2 = 10;


/* 4. CROSS JOIN - keeps all regardless of condition, i.e., create a cartesian product 

    "Unlike the inner join, left join, and right join, the cross join clause does not have a join condition. The right join makes a Cartesian product of rows from the joined tables. The cross join combines each row from the first table with every row from the right table to make the result set."

    I.e., It creates a table that has all the possible combinations. If table1 has 3 rows, table2 has 2 rows, there will have 6 combined rows in the CROSS JOIN table.

*/
SELECT
    *
FROM
    table1
CROSS JOIN table2;

/* Example of using CROSS JOIN to solve problems that cannot be solved with other JOIN.

Say, we have three tables, sales, products, and stores. If we want to know which store had no sales of a specific product, INNER JOIN cannot be used to solve this problem, as the NULL value are filtered out. We can use CROSS JOIN as it covers all the combinations as well as the NULL rows.

First, We select all associated products, stores and sales using inner join, if there is no sales of specific products, they are filtered out.
*/
SELECT 
    *
FROM products p
INNER JOIN sales sa ON p.id = sa.product_id 
INNER JOIN stores st ON sa.store_id = st.id; 
/* Result Set:
+----+--------------+---------+------------+----------+----------+------------+----+------------+
| id | product_name | price   | product_id | store_id | quantity | sales_date | id | store_name |
+----+--------------+---------+------------+----------+----------+------------+----+------------+
|  1 | iPhone       |  699.00 |          1 |        1 |    20.00 | 2017-01-02 |  1 | North      |
|  2 | iPad         |  599.00 |          2 |        1 |    15.00 | 2017-01-05 |  1 | North      |
|  3 | Macbook Pro  | 1299.00 |          3 |        1 |    25.00 | 2017-01-05 |  1 | North      |
|  1 | iPhone       |  699.00 |          1 |        2 |    30.00 | 2017-01-02 |  2 | South      |
|  2 | iPad         |  599.00 |          2 |        2 |    35.00 | 2017-01-05 |  2 | South      |
+----+--------------+---------+------------+----------+----------+------------+----+------------+
*/

/* Second, As the products in each store that have not sales are removed, we can take advantaged of this to find these products.  CROSS JOIN tables products and stores, and then LEFT JOIN the above table, so that when one product has not sale in specific store, the column on the table that is just left joined are null.*/
SELECT 
    *
FROM
    products p
CROSS JOIN
    stores st
LEFT JOIN
        (SELECT 
            p.id p_id, st.id st_id
        FROM products p
        INNER JOIN sales sa ON p.id = sa.product_id 
        INNER JOIN stores st ON sa.store_id = st.id) comb
    ON comb.p_id = p.id
    AND comb.st_id = st.id; 

/* Result Set:
+----+--------------+---------+----+------------+------+-------+
| id | product_name | price   | id | store_name | p_id | st_id |
+----+--------------+---------+----+------------+------+-------+
|  1 | iPhone       |  699.00 |  1 | North      |    1 |     1 |
|  1 | iPhone       |  699.00 |  2 | South      |    1 |     2 |
|  2 | iPad         |  599.00 |  1 | North      |    2 |     1 |
|  2 | iPad         |  599.00 |  2 | South      |    2 |     2 |
|  3 | Macbook Pro  | 1299.00 |  1 | North      |    3 |     1 |
|  3 | Macbook Pro  | 1299.00 |  2 | South      | NULL |  NULL |
+----+--------------+---------+----+------------+------+-------+
*/

/* Third, If the results on the right table (the one that is left-joined) are NULL (see the last row, whose' p_id and st_id are NULL), meaning that this product has not been sold in this store.*/
SELECT 
    *
FROM
    products p
CROSS JOIN
    stores st
LEFT JOIN
        (SELECT 
            p.id p_id, st.id st_id
        FROM products p
        INNER JOIN sales sa ON p.id = sa.product_id 
        INNER JOIN stores st ON sa.store_id = st.id) comb
    ON comb.p_id = p.id
    AND comb.st_id = st.id
WHERE comb.p_id IS NULL OR comb.st_id IS NULL;

/* However, we can also solve this problem with simple query without the use of sub-query. */
SELECT 
    *
FROM
    products p
CROSS JOIN
    stores st
LEFT JOIN sales sa 
    ON sa.product_id = p.id
    AND sa.store_id = st.id
WHERE
    sa.product_id IS NULL OR sa.store_id IS NULL; 

/* 5. Self Join - Join a table to itself using INNER JOIN or LEFT join.

    "The self join is often used to query hierarchical data or to compare a row with other rows within the same table. To perform a self join, you must use table aliases to not repeat the same table name twice in a single query."

*/

/* Using INNER JOIN for Self join. Say, a manager is an employee, and a number of employees need to report to a manager. In this case, a self-join is needed to find out the details of managers etc. However, the employees who are not managed by a manager is filtered out in this table by INNER JOIN. */
SELECT
    CONCAT(manager.lastName, ' ', manager.firstName) `Manager`,
    CONCAT(emp.lastName, ' ', emp.firstName) `Employees Being Managed`
FROM
    employees emp
INNER JOIN employees manager
    ON emp.reportsTo = manager.employeeNumber
ORDER BY  
    `Manager`;

/* Using Left Join for Self join. Different from using INNER JOIN, the employees who are not managed by a manager is not filtered out, instead, it has a value of NULL. It can be set to a more meaningful name using IFNULL() function, such as CEO (who are not managed by anyone). */ 
SELECT
    CONCAT(manager.lastName, ' ', manager.firstName) `Manager`,
    CONCAT(emp.lastName, ' ', emp.firstName) `Employees Being Managed`
FROM
    employees emp
LEFT JOIN employees manager
    ON emp.reportsTo = manager.employeeNumber
ORDER BY  
    `Manager`;
-- IF NULL, set to 'CEO'    
SELECT
    IFNULL(CONCAT(manager.lastName, ' ', manager.firstName), 'CEO') `Manager`,
    CONCAT(emp.lastName, ' ', emp.firstName) `Employees Being Managed`
FROM
    employees emp
LEFT JOIN employees manager
    ON emp.reportsTo = manager.employeeNumber
ORDER BY  
    `Manager`;

-------------------------------

-- 19. Group By 

------------------------------

/* Group By

        The GROUP BY clause returns ONE ROW for each group. In other words, it reduces the number of rows in the result set. This is often used with aggregate functions, e.g., SUM, AVG, MAX, MIN and COUNT.

        MYSQL evaluates cluases in such order:
            FROM -> WHERE -> SELECT -> GROUP BY -> HAVING -> ORDER BY -> LIMIT
*/
SELECT
    col1, col2, SUM(col3)
FROM
    table1
GROUP BY col1, col2, ..., col10;

/* Example of using GROUP BY clause and HAVING clause. Grouped rows in result set can also be ordered by adding DESC or ASC in GROUP BY clause. */
SELECT
    YEAR(orderDate) `Year`, SUM(quantityOrdered * priceEach) AS 'Revenue of shiped orders'
FROM 
    orders INNER JOIN orderdetails USING (orderNumber)
WHERE 
    status = 'Shipped'
GROUP BY 
    `Year` DESC
HAVING 
    `Year` > 2003;

-------------------------------

-- 20. Functions 

------------------------------

/* CONCAT function that concatenate strings */
CONCAT(string1, string2, string3)


/* CONCAT_WS is a function for concatenation with seperator. The first string is a seperator for the following list of string. */
CONCAT_WS(delimiter, string1, string2 ..)

/* YEAR function that extract year field from date */
YEAR(date)

/* Calculate sum */
SUM(col)

/* FIEDL function that identifies specified strings and return corresponding indices */
FIELD(orderStatus,
        'In Process', -- return 1
        'On Hold',    -- return 2
        'Cancelled',  -- return 3
        'Resolved',   -- return 4
        'Disputed',   -- return 5
        'Shipped');   -- return 6

/* UPPER function that returns the uppercase of the string */
UPPER(col)

/* LOWER function that returns the lowercase of the string */
LOWER(col)

/* COUNT function that counts the presense of the column */
COUNT(col)

/* CAST function that can cast a String to a Date type */
CAST('2019-01-01' AS DATE)

/* INFULL function that returns the content in the second parameter when the col or expression  in the first parameter return NULL value. */
IFNULL(col|expre, content)

/* IF function that checks the condition, if condition is true, return content1 else content2. This function creates a new column, thus is used in SELECT clause. */
SELECT
    IF(condition, content1, content2)

/* GROUPING function, To check whether NULL in the result set represents the subtotals or grand totals, you use the GROUPING() function. The GROUPING() function returns 1 when NULL occurs in a super-aggregate row, otherwise, it returns 0. */
SELECT
    productLine,
    SUM(orderValue),
    GROUPING(productLine)
FROM
    sales
GROUP BY
    productLine WITH ROLLUP;
/* Result Set:
    +------------------+-----------------+-----------------------+
    | productLine      | SUM(orderValue) | GROUPING(productLine) |
    +------------------+-----------------+-----------------------+
    | Classic Cars     |        19668.13 |                     0 |
    | Motorcycles      |         9044.15 |                     0 |
    | Planes           |        11700.79 |                     0 |
    | Ships            |        13147.86 |                     0 |
    | Trains           |         9021.03 |                     0 |
    | Trucks and Buses |        14194.95 |                     0 |
    | Vintage Cars     |        12245.78 |                     0 |
    | NULL             |        89022.69 |                     1 |
    +------------------+-----------------+-----------------------+
*/

/* AVG Function */
SELECT 
    AVG(salary)
FROM
    ...;

/* IN Function and NOT IN Funciton */
SELECT
    fname IN ('curtis', 'yongjie')
FROM
    ...;

SELECT
    fname NOT IN ('curtis', 'yongjie')
FROM
    ...;

/* CASE Function. It creates one or more CASES each has specified condition, if condition matches, returns a specified value (string). The CASE function returns an individual column. Alias are not supported in CASE Function, and ELSE is optional. */
SELECT
    customerNumber,
    (quantityOrdered * priceEach) AS sales,
    (CASE
        WHEN SUM(quantityOrdered * priceEach) < 10000 THEN 'Silver'
        WHEN SUM(quantityOrdered * priceEach) BETWEEN 10000 AND 100000 THEN 'Gold'
        WHEN SUM(quantityOrdered * priceEach) > 100000 THEN 'Platinum'
        ELSE "Pleb"
    END) customerGroup
FROM
    orderdetails INNER JOIN orders USING (orderNumber)
GROUP BY customerNumber;

/* CREATE TABLE ... LIKE Functions. It creates a table with a given name, and copy all definitions of fields and properties from the table sepcified after LIKE clause. It doesn't copy actual data, only the structrue of the table.*/
CREATE TABLE table1
LIKE table2;

-------------------------------

-- 21. HAVING clause 

------------------------------

/* The  HAVING clause 

    is used in the SELECT statement to specify filter conditions for a group of rows or aggregates. If GROUP BY is omitted, the HAVING clause behaves like WHERE clause.

    HAVING is evaluated as follows:
        FROM -> WHERER -> SELECT -> GROUP BY -> HAVING -> ORDER BY -> LIMIT

*/
SELECT
    col
FROM
    table1
WHERE
    condi
GROUP BY
    col2|expre
HAVING
    condi2;

/* Example of using GROUP BY, HAVING, AND and OR clauses. */
SELECT
    orderNumber 'Order No.',
    SUM(quantityOrdered) `Quantity`,
    SUM(priceEach * quantityOrdered) `Total Sales`
FROM
    orderDetails
GROUP BY 
    orderNumber
HAVING 
    `Total Sales` > 1000 AND
    `Quantity` > 600;

-------------------------------

-- 22. WITH ROLLUP & GROUPING

------------------------------

/* WITH ROLLUP

    A grouping set is a set of columns to which you want to group. For example, query "... GROUP BY column1;" creates a grouping set of column1. “WITH ROLLUP” is a modifier that is used with GROUP BY clause. Mainly, it causes the summary output to include extra rows that represent higher-level summary operations. For example, SUM(*) function is used with GROUP BY, by having the "WITH ROLL UP" extension, MySQL adds an additional column displaying the grand total of all.  
*/
SELECT
    col1
FROM
    table1
GROUP BY
    c1, c2, c3 WITH ROLL UP;

/* Example of WITH ROLL UP */
SELECT 
    productLine, 
    SUM(orderValue) totalOrderValue
FROM
    sales
GROUP BY 
    productline WITH ROLLUP;
/* Result Set:
    +------------------+-----------------+
    | productLine      | totalOrderValue |
    +------------------+-----------------+
    | Classic Cars     |        19668.13 |
    | Motorcycles      |         9044.15 |
    | Planes           |        11700.79 |
    | Ships            |        13147.86 |
    | Trains           |         9021.03 |
    | Trucks and Buses |        14194.95 |
    | Vintage Cars     |        12245.78 |
    | NULL             |        89022.69 |
    +------------------+-----------------+
*/

/* WITH ROLLUP can be hierarchical when more than one column speicified, the hierarchy is based on the order of columns specified. It will create the grouping set for the first column specified in GROUP BY clause, and create the second grouping set for the second columns and so on.

For example, in the following query, the grand total grouping set is created for productline, that returns the SUM of all orderValue of all productLine in all years(the one with two NULL), then it creates the grouping set for the orderYear, thus it returns the SUM of all years of each productline. The order does effect the result set.
*/
SELECT 
    productLine, 
    orderYear,
    SUM(orderValue) totalOrderValue
FROM
    sales
GROUP BY 
    productline, 
    orderYear 
WITH ROLLUP;

/* GROUPING function, To check whether NULL in the result set represents the subtotals or grand totals, you use the GROUPING() function. The GROUPING() function returns 1 when NULL occurs in a super-aggregate row, otherwise, it returns 0. */
SELECT
    productLine,
    SUM(orderValue),
    GROUPING(productLine)
FROM
    sales
GROUP BY
    productLine WITH ROLLUP;
/* Result Set:
    +------------------+-----------------+-----------------------+
    | productLine      | SUM(orderValue) | GROUPING(productLine) |
    +------------------+-----------------+-----------------------+
    | Classic Cars     |        19668.13 |                     0 |
    | Motorcycles      |         9044.15 |                     0 |
    | Planes           |        11700.79 |                     0 |
    | Ships            |        13147.86 |                     0 |
    | Trains           |         9021.03 |                     0 |
    | Trucks and Buses |        14194.95 |                     0 |
    | Vintage Cars     |        12245.78 |                     0 |
    | NULL             |        89022.69 |                     1 |
    +------------------+-----------------+-----------------------+
*/

/* We can take advantage of the GROUPING function, and identifies which are the Grand total, or super-aggregate row */
SELECT
    IF(GROUPING(productLine), "Grand Total Order Value", productLine) 'Product Line',  -- if is grouping, substitute NULL in productLine with the string.
    SUM(orderValue) 'Order Value'
FROM
    sales
GROUP BY
    productLine WITH ROLLUP;
/* Result Set:
    +-------------------------+-------------+
    | Product Line            | Order Value |
    +-------------------------+-------------+
    | Classic Cars            |    19668.13 |
    | Motorcycles             |     9044.15 |
    | Planes                  |    11700.79 |
    | Ships                   |    13147.86 |
    | Trains                  |     9021.03 |
    | Trucks and Buses        |    14194.95 |
    | Vintage Cars            |    12245.78 |
    | Grand Total Order Value |    89022.69 |
    +-------------------------+-------------+
*/

-------------------------------

-- 23. Subquery 

------------------------------

/* Syntax example of using subquery with IN and NOT IN  */
SELECT 
    *
FROM 
    employees
WHERE 
    officeCode IN (
            SELECT
                officeCode
            FROM
                offices
            WHERE
                country = 'UK');

/* Syntax example of using subquery in WHERE */
SELECT
     * 
FROM 
    payments 
WHERE 
    amount = (
                SELECT 
                    MAX(amount) 
                FROM 
                    payments);
                    
/* Syntax example of using subquery in WHERE with comparison operators */
SELECT
     * 
FROM 
    payments 
WHERE 
    amount > (
                SELECT 
                    AVG(amount) 
                FROM 
                    payments);

/* Syntax example of using subquery in FROM */
SELECT 
    orderNumber, -- this will be distinct already
    items
FROM
    (SELECT 
        orderNumber, COUNT(orderNumber) AS items
    FROM
        orderdetails
    GROUP BY orderNumber) AS lineitems
WHERE items = 10;

/* Subquery with EXISTS and NOT EXISTS, EXISTS return ture so long as the row returned by the correlated subquery exist and it identifies a row that matches the results returned by the subquery. Subquery must somehow correlated to the table being selected (* in SELECT clause) */
SELECT 
    *
FROM 
    table1
WHERE
    EXISTS( .. );

/* customers table is correlated with orders table, and orders table is correlated with orderdetails table. If there are no result sets returned by the subquery, EXISTS() returned false or 0, else it returns a temporary table for the outter query to SELECT (e.g., customerNumber and customerName) if it exists in the subquery table*/ 
SELECT 
    customerNumber, 
    customerName
FROM
    customers
WHERE
    EXISTS( SELECT 
            orderNumber, SUM(priceEach * quantityOrdered)
        FROM
            orderdetails
                INNER JOIN
            orders USING (orderNumber)
        WHERE
            customerNumber = customers.customerNumber
        GROUP BY orderNumber
        HAVING SUM(priceEach * quantityOrdered) > 60000);

-------------------------------

-- 24. EXISTS 

------------------------------

/* EXISTS() Function and NOT EXISTS() Function. The EXISTS operator is a Boolean operator that returns either true or false. The EXISTS operator is often used to test for the existence of rows returned by the subquery. It return True or 1 if exists (including the results), else FALSE or 0.

Note that you can use SELECT *, SELECT column, SELECT a_constant, or anything in the subquery. The results are the same because MySQL ignores the select list appeared in the SELECT clause.*/
SELECT 
    EXISTS (SELECT 
                * 
            FROM 
                table1 
            WHERE  
                fname = 'curtis');

SELECT
    *
FROM
    table1
WHERE [NOT] EXISTS (SELECT
                    *
                    FROM
                    ...);

-- Example of EXISTS() function.
-- Select cusotmer who has at least one order.
SELECT *
FROM
    customers
WHERE EXISTS
        (SELECT
            *
        FROM
            customers
        INNER JOIN 
            orders USING(customerNumber)); 
-- Select customers who don't have order, JOIN in subquery shouldn't be used, or else no rows are found.
SELECT 
    *
FROM
    customers
WHERE 
    NOT EXISTS
        (SELECT
            *
        FROM
            orders
        WHERE orders.customerNumber = customers.customerNumber); 

/*
    UPDATE EXISTS
*/
-- Say we want to find employees who work at the office in san francisco
SELECT *
FROM employees
WHERE 
    EXISTS(
        SELECT
            *
        FROM
            employees
        INNER JOIN offices USING (officeCode)
        WHERE city = 'San Francisco');
-- Then we want to update the rows in this table returned from subquery or EXISTS statement
UPDATE
    employees
SET
    employees.extension = CONCAT(extension, '1');
WHERE 
    EXISTS(
        SELECT
            *
        FROM
            employees
        INNER JOIN offices USING (officeCode)
        WHERE city = 'San Francisco');

/*
    INSERT EXISTS
*/
-- Suppose we want to find customers who don't have any sales orders, and we want to archive these rows to a new table. We first copy the structure of the customers table.
CREATE TABLE customer_archive
LIKE customers;
-- We then find customers who don't have any sales orders.
SELECT 
    *
FROM
    customers
WHERE NOT EXISTS
        (SELECT
            *
        FROM
            orders
        WHERE customers.customerNumber = orders.customerNumber);
-- We insert these data to the archive table
INSERT INTO customer_archive
SELECT 
    *
FROM
    customers
WHERE NOT EXISTS
        (SELECT
            *
        FROM
            orders
        WHERE customers.customerNumber = orders.customerNumber);

/*
    DELETE EXISTS
*/
-- Suppose that we want delete the customers exist in the customers_archive from the customers table. We first find these customsers.
SELECT 
    *
FROM
    customers
WHERE EXISTS
        (SELECT 
            *
        FROM
            customer_archive
        WHERE
            customer_archive.customerNumber = customers.customerNumber);
-- Then we delete them from customers table, exception occurs when this operation violates constraints.
DELETE FROM customers;
WHERE EXISTS
        (SELECT 
            *
        FROM
            customer_archive
        WHERE
            customer_archive.customerNumber = customers.customerNumber);

/*
    EXISTS vs. IN operator
*/
-- To find customer who has placed at least one order, IN can be used as well
SELECT 
    customerNumber, 
    customerName
FROM
    customers
WHERE customerNumber IN
    (SELECT
        customerNumber
    FROM
        orders);
-- While using EXISTS operator
SELECT 
    customerNumber, 
    customerName
FROM
    customers
WHERE 
    EXISTS
        (SELECT
            customerNumber -- this doesn't matter, as it is ignored
        FROM
            orders
        WHERE customers.customerNumber = orders.customerNumber);
/* From the performance perspective, the EXISTS way out-performs the IN way of finding the results if the table returned by the subquery is large. As with IN operator, the whole subquery is processed first. However, with EXISTS operator, it return the each matched results immediately as long as it exists, it will not keep going finding the next match for this same row. Nonetheless, if the subquery is a very small table, IN may be faster. Generally, use EXISTS if the subquery table is big.*/  

-------------------------------

-- 25. Derived Table 

------------------------------

/* A derived table is a virtual table returned from a SELECT statement. It is simliar to but is different from a Temporary Table. The term Derived Table and the term subquery are often used interchangeably. A derived table must have an alias, so that it can be referenced later. */

-- First we create a derived table, that groups the orders based on the product code and calculate the sum of sales of orders for each productCode.
SELECT 
    productCode,
    SUM(quantityOrdered * priceEach) Sales
FROM
    orderdetails INNER JOIN orders USING(orderNumber)
WHERE YEAR(orderDate) = 2003
Group BY
    productCode
ORDER BY
    SUM(quantityOrdered * priceEach) DESC
LIMIT 5;
/* Result Set:
+-------------+-----------+
| productCode | Sales     |
+-------------+-----------+
| S18_3232    | 103480.30 |
| S10_1949    |  67985.34 |
| S12_1108    |  59852.24 |
| S12_3891    |  57403.47 |
| S12_1099    |  56462.25 |
+-------------+-----------+
*/
-- Then we can use this subquery or derived table to JOIN another table for more information.
    SELECT
        products.productName,
        Sales
    FROM 
        (SELECT 
            productCode,
            SUM(quantityOrdered * priceEach) Sales
        FROM
            orderdetails INNER JOIN orders USING(orderNumber)
        WHERE YEAR(orderDate) = 2003
        Group BY
            productCode
        ORDER BY
            SUM(quantityOrdered * priceEach) DESC
        LIMIT 5) AS topFiveSaleProducts
    INNER JOIN products USING (productCode);
/*
+-----------------------------+-----------+
| productName                 | Sales     |
+-----------------------------+-----------+
| 1992 Ferrari 360 Spider red | 103480.30 |
| 1952 Alpine Renault 1300    |  67985.34 |
| 2001 Ferrari Enzo           |  59852.24 |
| 1969 Ford Falcon            |  57403.47 |
| 1968 Ford Mustang           |  56462.25 |
+-----------------------------+-----------+
*/

-------------------------------

-- 26. UNION Operator 

------------------------------

/*

UNION operator is used to combine two or more result sets of queires into one single result set. 
It is just like merging the results together into same columns vertically. This is different from 
JOIN in that the JOIN combine result sets horizontally.

    1. The number and the order of columns that appear in all SELECT statement must be the same. 
        (e.g., one column UNION with one column, and so on, so that they become one column)
    2. The data types of columns must be the same or compatible 
        (e.g., INT type combined with INT type)

Duplicates rows are removed if ALL is not specified explicitly, however, even without DISTINCT 
specified, duplicates are still removed. 

E.g., Suppose that you want to combine the names of all people (including customers and employees),
this becomes useful, you simply use UNION, that combines the name in these tables.

*/
SELECT col 
FROM tab1
UNION [DISTINCT | ALL]
SELECT col
FROM tab2
UNION [DISTINCT | ALL]
....
-- For example
SELECT
    id -- INT 
FROM
    t1
UNION
SELECT
    id -- INT
FROM t2; 
/* Result Set:
+----+
| id |
+----+
|  1 |
|  2 |
|  3 |
|  4 |
+----+
*/

-------------------------------

-- 27. INSERT 

------------------------------

/* Syntax for inserting data into tables */
INSERT INTO tableName (col1, col2, ...)
VALUES (val1, val2, ....)

/* Insert multiple rows or tuples */
INSERT INTO tableName (col1, col2, ...)
VALUES 
    (val1, val2, ....),
    (val1, val2, ....),
    (val1, val2, ....);

/* If an attribute in a column is given a default value,
we can refer to the DEFAULT value when we insert new tuple */
CREATE TABLE IF NOT EXISTS task(
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    start_date DATE,
    due_date DATE,
    priority TINYINT NOT NULL DEFAULT 3,
    description TEXT
);
-- This will give a priority of 3, as which is the default value.
INSERT INTO task(title, priority) VALUES ('I used default priority!', DEFAULT);

/* 
Insert Date

    The literal format of a date is 'YYYY-MM-DD' 
    We can also use CURRENT_DATE() function to insert DATE values.
*/
INSERT INTO task(title, start_date, due_date) 
VALUES 
    ("Insert DATE!", "2019-11-03", "2019-11-04"),
    ("Current Date", CURRENT_DATE(), CURRENT_DATE());

/* 
    Global Variables for INSERT operation 

    'max_allowed_packet' defines how many rows can be inserted in a single 
    INSERT statment. We can use "SHOW [GLOBAL | SESSION] VARIABLES [LIKE | WHERE = expr]" 
    function to check the value of this variable. Then we use SET statment to change the value.

    Note that it doesn't affect INSERT INTO SELECT statement, which can insert as many as wanted.
*/
SHOW VARIABLES LIKE 'max_allowed_packet';
SET [GLOBAL | SESSION] max_allowed_packet=123124123123;

-------------------------------

-- 28. INSERT INTO SELECT  

------------------------------

/*

    INSERT INTO SELECT allows you using the result of a SELECT statement as the data
    source for the INSERT statement, or i.e., copying data that match the requirements
    and INSERT these data into a table.

*/
INSERT INTO table1 (col...)
SELECT
    col
FROM
    table2
WHERE
    ...;
    
/*
    For example, we want to copy some of the rows in task, wherein their priority is 
    3. We can first create a table of same structure, then use INSERT INTO SELECT to
    copy those rows that match condition.
*/
CREATE TABLE taskArchived LIKE task;
INSERT INTO taskArchived SELECT * FROM task WHERE priority = 3;

-------------------------------

-- 29. INSERT, ON DUPLICATE KEY UPDATE

------------------------------

/*

    With INSERT statement, when there are duplicates in the UNIQUE attributes(or columns) 
    or primary keys, error issued as it violates the integrity (e.g., Entity Integrity) 
    and rules.

    With ON DUPLICATE KEY UPDATE, we can update the duplicates instead of issuing an error.

*/
INSERT INTO 
    table1 (col1)
VALUES 
    (val1)
ON DUPLICATE KEY UPDATE
    col1 = val1;
-- After the statuement ON DUPLICATE KEY UPDATE, we speicify what values should be updated 
-- or over-written. 
 
-------------------------------

-- 30. INSERT IGNORE

------------------------------
    
/*

    Similar to INSERT, ON DUPLICATE KEY UPDATE, the INSERT IGNORE simply ignores errors 
    caused by the insertion of a row. In case where multiple rows are inserted, it only
    ignores or abandon the insertion operation for those rows that cause errors, not all
    of them. 

    However, we can use SHOW WARNINGS to check the errors after the operation being finished.

*/
INSERT IGNORE INTO table1(col1)
VALUES 
    (val1),
    (val2),
    (val3);
SHOW WARNINGS;

-------------------------------

-- 31. UPDATE

------------------------------

/* 

    Syntax of UPDATE statement. 

    Note that there are two flags: 
        - LOW_PRIORITY
        - IGNORE
    
    LOW_PRIORITY means that the current UPDATE statement is of low priority,
    and can be delayed until there is no connection reading data from the table.

    IGNORE means that the query should continue updating the rows if there are
    errors occurred while updating some of the rows.
    
*/
UPDATE [LOW_PRIORITY] [IGNORE] table_name
SET
    col1 = expr1,
    col2 = expr2
WHERE
    ...;

/* Using UPDATE with subquery, note that the subquery should only return 
one value in this example. */
UPDATE table1
SET
    col1 = (SELECT
                .....
            FROM
                ...
            WHERE
                ...
            LIMIT 1;
            )
WHERE
    ...;
    








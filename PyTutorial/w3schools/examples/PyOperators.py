"""
    -> Arithmetic operators
    -> Assignment operators
    -> Comparison operators
    -> Logical operators
    -> Identity operators
    -> Membership operators
    -> Bitwise operators
"""

"""
    Arithmetic operators
        + - * / %
        ** # Exponentiation
        // # Floor division

"""
x = 10
y = 10
print(x ** y)  # x to the power of y

print("10/3", 10/3)  # 3.33333
print("10//3", 10//3)  # 3

print("-10/3 ", (-10)/3)  # -3.33333
print("-10//3", (-10)//3)  # -4

# x = {1, 2, 3}
y = {2, 3, 4}
a = {1, 2, 3}
a &= y  # a becomes {2,3} set intersection
print(a)

b = {1, 2, 3}
b |= y  # b becomes {1,2,3,4} set union
print(b)

b = {1, 2, 3}
b ^= y  # b becomes {1,2,3,4} set difference
print(b)


x = 12  # "0000 1100"
x <<= 2  # x becomes 48, which is "0011 0000"
print(x)

x = 48  # "0011 0000"
x >>= 2  # x becomes 12, which is "0000 1100"
print(x)

x = 6
print(str(x), "x < 5 and x < 10", x < 5 and x < 10)
x = 3
print(str(x), "x < 5 or x < 4", x < 5 or x < 4)
a = 0
print(str(x), "not(a > 0)", not(a > 0))


class Vehicle():
    def __len__():
        return 1


car1 = Vehicle()
car2 = car1
print(car1 is car2)
print(car1 is not car2)

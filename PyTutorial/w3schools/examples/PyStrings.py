# multiline string
print("""This is a multiline
string, so it has more than one line""")

# single quote and double quote
print('single quote')
print("double quotes")

# getting specific character
a = "Hello world"
print("Getting the first character from \"Hello world\"", a[0])

# slicing
print("Getting the characters from 0 to 5 from \"Hello world\"", a[0:5])

# slicing and negative indexing
print("Getting the characters from 0 to 5 from \"Hello world\"", a[-11:-6])
print("Getting the last character from \"Hello world\"", a[-1])

# string length
print("Length of a", len(a))

# strip()
a = "   Hello world   "
a_without_space = a.strip()  # "Hello world"
print("strip()", a_without_space)

# lower()
a = "AAAAAA"
print("lowercase", a.lower())

# upper()
a = "aaa"
print("uppercase", a.upper())  # AAA

# replace()
a = "abcaaa"
print(a.replace("a", "A", 1))

# split()
a = "aaa,bbbb"
splited_a = a.split(",")
print(splited_a)
print(type(splited_a))

# in, not in
a = "aaa,bbb"
print("Has \"aaa\"?", "aaa" in a)
print("Has \"c\"?", "c" in a)
print("Does Not Have \"c\"?", "c" not in a)

# concatenation
a = "Hello"
b = "World"
print(a + b)

# format()
age = 24
habit = "programming"
name = "Curtis is {} and I love {} "
print(name.format(age, habit))

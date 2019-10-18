"""
int
float
complex
"""

x = 1
y = 1.2
y2 = 1.2e10
z = 1j

print(type(x), type(y), type(y2), type(z))

# convert from int to float
a = 10
b = float(a)
print(type(b))

# convert from float to int
a = 10.5
b = int(a)
print(type(b))

# convert from int to complex
a = 10
b = complex(a)
print(type(a))

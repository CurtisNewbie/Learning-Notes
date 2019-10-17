"""

    Text Type: str

    Numeric Types: int, float, complex

    Sequence Types: list, tuple, range

    Mapping Type: dict

    Set Types: set, frozenset

    Boolean Type: bool

    Binary Types: bytes, bytearray, memoryview


"""
# str
x = "This is a str"
print(x, type(x), "\n")

# int
x = 20
print(x, type(x), "\n")

# float
x = 0.5
print(x, type(x), "\n")

# complex
x = 1j
print(x, type(x), "\n")

# list
x = [1, 2, 3]
print(x, type(x), "\n")

# tuple
x = (1, 2, 3)
print(x, type(x), "\n")

# range
x = range(5)
print(x, type(x), "\n")

# dict (key-value collection)
x = {"name": "John", "age": 100}
print(x, type(x), "\n")

# set
xset = {1, 2, 3}
print(xset, type(x), "\n")

# frozenset
x = frozenset({"apple", "banana", "cherry"})
print(x, type(x), "\n")

# boolean
x = True
print(x, type(x), "\n")

# bytes
x = b"Bytes"
print(x, type(x), "\n")

# bytearray
x = bytearray(5)
print(x, type(x), "\n")

# memoryview
x = memoryview(bytes(5))
print(x, type(x), "\n")

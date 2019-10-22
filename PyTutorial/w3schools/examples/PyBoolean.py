
print(True)
print("10 > 9", 10 > 9)
print("10 = 9 ", 10 == 9)
print("10 < 9", 10 < 9)

scream = True
if scream:
    print("AAhhhhhhh")

print("Evaluate variable using bool()")
print("Evaluate 10", bool(10))
print("Evaluate -10", bool(-10))
print("Evaluate \"abc\"", bool("abc"))
print("Evaluate 0", bool(0))
print("Evaluate []", bool([]))
print("Evaluate False", bool(False))
print("Evaluate None", bool(None))
print("Evaluate ()", bool(()))
print("Evaluate {}", bool({}))


class Car():
    def __len__(self):  # method used to check the length obj, called internally by len() method
        return 0


obj = Car()
# False, becuase __len__() method returns 0
print("Length is " + str(len(obj)) + ",", bool(obj))

# True
print("Whether the obj is instance of Car:", bool(isinstance(obj, Car)))

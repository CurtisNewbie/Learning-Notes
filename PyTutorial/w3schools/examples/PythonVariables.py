x, y, z = "X", "Y", "Z"
print(x, y, z)

x = y = z = "XYZ"
print(x, y, z)

x = y + "ABC"
print("XYZ + ABC =", x)
print("XYZ + ABC = " + x)

# this will cause exception
# print("1 + 1 = " + 2)
"""
Traceback (most recent call last):
  File "PythonVariables.py", line 12, in <module>
    print("1 + 1 = " + 2)
TypeError: can only concatenate str (not "int") to str
"""

print("1 + 1 = " + str(2))

print("1 + 1 =", 1+1)

text = "Some Text"


def showText():
    print(text)


showText()


def showInnerText():
    text = "Nah"
    print(text)


showInnerText()


def createGlobalVar():
    global globalVar 
    globalVar = "I am global variable created within a func"


createGlobalVar()
print(globalVar)

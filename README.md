# For Brock University COSC 4F90's project

- [x] Step 1: Create a class Foo with some private and public variable, pretend do not knowing anything. Using .getClass() method to get all the variable and its' value. In another hand, using Class class to get the elements which in class Foo.

- [x] Step 2: Pretend I only know the name of the class, the names of the field and values for each filed. Creating an object of the class Foo with those values. In another hand, using reflection. No new.. is used!!(new Foo())

- [x] Step 3: Making a new super class FooBase, it's subclass is Foo. Moving some variable from Foo to FooBase. Writing a method called getAllFields(Class clazz), in order to get all the fields that exists in both Foo and FooBase. You can use .getSuperClass() method. 

- [x] Step 4: Building the XML representation of an object. For that, start with only the primitive types (string is NOT primitive, it is an object). The XML representation should like as follows. Use the StringBuilder class for creating the XML string.
```
<object className=”…”>
<field type=”int” value=”42”/>   //similar for all other primitive types
…
</object>
```

- [x] Step 5: Using StringBuilder to make a xml string like below:
```
<object className="Foo" ref="0">
<int name="age">
<objVar className="objVar" ref="1">
</object>

<object className="objVar" ref="1">
<int height="182">
<objVar className="..." ref="2">
</object>

<object className="..." ref="2">
...
</object>
```
As we can see the ref which represent one object. First we print out object's variable as XML format. Then, if there is an object's variable which contain another object. We need to print out another object's variable until the last object's variable has no another object.
- [x] Step 6: 
I need to do implement the decorator design pattern (Decorator pattern - Wikipedia) twice for writing and reading objects as xml code. In  particular, we need the following:

1. Create a class XMLObjectWriter as a subclass of Writer. This will be a ConcreteDecorator class (see design pattern).
2. Implement a method void writeXMLObject(Object obj) that will convert obj into a string by using the previous code. Then this string containing xml code is written to the inner writer of the class (see design pattern).
3. Create a class XMLObjectReader as a subclass of Reader. This will be the second ConcreteDecorator.
4. In this calls you need to add a method Object readXMLObject() that reads an string with xml content from the inner Reader (see design pattern), and then creates an object out of this information. For this you need code that does the opposite of what you already implemented using our approach.

You can test your program by creating an XMLObjectWriter based on a FileWriter, and then use the method writeXMLObject(Object obj) for an example object. After this the file should contain the appropriate xml information. Then you can use a XMLObjectReader based on the same file to load the object again. This object should have the same values in its variables as before all of this.

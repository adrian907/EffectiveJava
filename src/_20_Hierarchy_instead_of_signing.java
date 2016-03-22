/**
 * Created by Adrian on 22.03.2016.
 */
public class _20_Hierarchy_instead_of_signing {
}

/*

Occasionally you may run across a class whose instances come in two or
more flavors and contain a tag field indicating the flavor of the instance. For
example, consider this class, which is capable of representing a circle or a
rectangle:
// Tagged class - vastly inferior to a class hierarchy!
class Figure {
enum Shape { RECTANGLE, CIRCLE };
// Tag field - the shape of this figure
final Shape shape;
// These fields are used only if shape is RECTANGLE
double length;
double width;
// This field is used only if shape is CIRCLE
double radius;
// Constructor for circle
Figure(double radius) {
shape = Shape.CIRCLE;
this.radius = radius;
}
// Constructor for rectangle
Figure(double length, double width) {
shape = Shape.RECTANGLE;
this.length = length;
this.width = width;
}
double area() {
switch(shape) {
case RECTANGLE:
return length * width;
case CIRCLE:
return Math.PI * (radius * radius);
default:
throw new AssertionError();
}
}
}

Such tagged classes have numerous shortcomings. They are cluttered with
boilerplate, including enum declarations, tag fields, and switch statements. Readability
is further harmed because multiple implementations are jumbled together
in a single class. Memory footprint is increased because instances are burdened
with irrelevant fields belonging to other flavors. Fields can’t be made final unless
constructors initialize irrelevant fields, resulting in more boilerplate. Constructors
must set the tag field and initialize the right data fields with no help from the compiler:
if you initialize the wrong fields, the program will fail at runtime. You can’t
add a flavor to a tagged class unless you can modify its source file. If you do add a
flavor, you must remember to add a case to every switch statement, or the class
will fail at runtime. Finally, the data type of an instance gives no clue as to its flavor.
In short, tagged classes are verbose, error-prone, and inefficient.


A tagged class is just a poor imitation of a class hierarchy.


To transform a tagged class into a class hierarchy, first define an abstract class
containing an abstract method for each method in the tagged class whose behavior
depends on the tag value. In the Figure class, there is only one such method,
which is area. This abstract class is the root of the class hierarchy. If there are any
methods whose behavior does not depend on the value of the tag, put them in this
class. Similarly, if there are any data fields used by all the flavors, put them in this
class. There are no such flavor-independent methods or fields in the Figure class.
Next, define a concrete subclass of the root class for each flavor of the original
tagged class. In our example, there are two: circle and rectangle. Include in each
subclass the data fields particular to its flavor. In our example, radius is particular
to circle, and length and width are particular to rectangle. Also include in each
subclass the appropriate implementation of each abstract method in the root class.



Here is the class hierarchy corresponding to the original Figure class:
// Class hierarchy replacement for a tagged class
abstract class Figure {
abstract double area();
}
class Circle extends Figure {
final double radius;
Circle(double radius) { this.radius = radius; }
double area() { return Math.PI * (radius * radius); }
}
class Rectangle extends Figure {
final double length;
final double width;
Rectangle(double length, double width) {
this.length = length;
this.width = width;
}
double area() { return length * width; }
}

Another advantage of class hierarchies is that they can be made to reflect natural
hierarchical relationships among types, allowing for increased flexibility and
better compile-time type checking.


In summary, tagged classes are seldom appropriate. If you’re tempted to write
a class with an explicit tag field, think about whether the tag could be eliminated
and the class replaced by a hierarchy. When you encounter an existing class with a
tag field, consider refactoring it into a hierarchy.










 */
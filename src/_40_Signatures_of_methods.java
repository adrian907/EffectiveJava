/**
 * Created by Adrian on 27.03.2016.
 */
public class _40_Signatures_of_methods {
}
/*
Choose method names carefully.

Don’t go overboard in providing convenience methods. Every method
should “pull its weight.” Too many methods make a class difficult to learn, use,
document, test, and maintain.

Avoid long parameter lists. Aim for four parameters or fewer. Most programmers
can’t remember longer parameter lists.


There are three techniques for shortening overly long parameter lists:
1) One is to break the method up into multiple methods, each of which requires only a subset
of the parameters.  For example, consider the java.util.List interface. It does not provide methods to find the
first or last index of an element in a sublist, both of which would require three
parameters. Instead it provides the subList method, which takes two parameters
and returns a view of a sublist. This method can be combined with the indexOf or
lastIndexOf methods, each of which has a single parameter, to yield the desired
functionality. The resulting API has a very high power-to-weight ratio.

2) A second technique for shortening long parameter lists is to create helper
classes to hold groups of parameters. Typically these helper classes are static
member classes (Item 22). This technique is recommended if a frequently occurring
sequence of parameters is seen to represent some distinct entity. For example,
suppose you are writing a class representing a card game, and you find yourself
constantly passing a sequence of two parameters representing a card’s rank and its
suit. Your API, as well as the internals of your class, would probably benefit if you
added a helper class to represent a card and replaced every occurrence of the
parameter sequence with a single parameter of the helper class.

3) A third technique that combines aspects of the first two is to adapt the Builder
pattern (Item 2) from object construction to method invocation. If you have a
method with many parameters, especially if some of them are optional, it can be
beneficial to define an object that represents all of the parameters, and to allow the
client to make multiple “setter” calls on this object, each of which sets a single
parameter or a small, related group. Once the desired parameters have been set,
the client invokes the object’s “execute” method, which does any final validity
checks on the parameters and performs the actual computation.



For parameter types, favor interfaces over classes (Item 52). If there is an
appropriate interface to define a parameter, use it in favor of a class that implements
the interface. For example, there is no reason ever to write a method that
takes HashMap on input—use Map instead. This lets you pass in a Hashtable, a
HashMap, a TreeMap, a submap of a TreeMap, or any Map implementation yet to be
written. By using a class instead of an interface, you restrict your client to a particular
implementation.



Prefer two-element enum types to boolean parameters. It makes your code
easier to read and to write, especially if you’re using an IDE that supports autocompletion.
Also, it makes it easy to add more options later. For example, you might
have a Thermometer type with a static factory that takes a value of this enum:

public enum TemperatureScale { FAHRENHEIT, CELSIUS }

Not only does Thermometer.newInstance(TemperatureScale.CELSIUS)
make a lot more sense than Thermometer.newInstance(true), but you can add
KELVIN to TemperatureScale in a future release without having to add a new
static factory to Thermometer.


 */
/**
 * Created by Adrian on 29.03.2016.
 */
public class _52_Refer_to_objects_by_interfaces {
}
/*

More generally, you should favor the use of interfaces rather than
classes to refer to objects. If appropriate interface types exist, then parameters,
return values, variables, and fields should all be declared using interface
types. The only time you really need to refer to an object’s class is when you’re
creating it with a constructor.


// Good - uses interface as type
List<Subscriber> subscribers = new Vector<Subscriber>();

rather than this:

// Bad - uses class as type!
Vector<Subscriber> subscribers = new Vector<Subscriber>();

If you get into the habit of using interfaces as types, your program will be
much more flexible. If you decide that you want to switch implementations, all
you have to do is change the class name in the constructor (or use a different static
factory). For example, the first declaration could be changed to read:

List<Subscriber> subscribers = new ArrayList<Subscriber>();

and all of the surrounding code would continue to work.

If you implemented some additional functionality with Vector class, then switch is not that simple. You have
to provide such functionality in a new class as well or forbid this idea.

So why would you want to change implementations? Because the new implementation
offers better performance or because it offers desirable extra functionality.

It is entirely appropriate to refer to an object by a class rather than an
interface if no appropriate interface exists.

For example, consider value classes, such as String and BigInteger. Value classes are rarely written with
multiple implementations in mind. They are often final and rarely have corresponding
interfaces. It is perfectly appropriate to use such a value class as a
parameter, variable, field, or return type. More generally, if a concrete class has no
associated interface, then you have no choice but to refer to it by its class whether
or not it represents a value. The Random class falls into this category.

A second case in which there is no appropriate interface type is that of objects
belonging to a framework whose fundamental types are classes rather than
interfaces. If an object belongs to such a class-based framework, it is preferable to
refer to it by the relevant base class, which is typically abstract, rather than by its
implementation class. The java.util.TimerTask class falls into this category.


A final case in which there is no appropriate interface type is that of classes
that implement an interface but provide extra methods not found in the interface—
for example, LinkedHashMap. Such a class should be used to refer to its instances
only if the program relies on the extra methods. It should rarely be used as a
parameter type (Item 40).
These cases are not meant to be exhaustive but merely to convey the flavor of
situations where it is appropriate to refer to an object by its class. In practice, it
should be apparent whether a given object has an appropriate interface. If it does,
your program will be more flexible if you use the interface to refer to the object; if
not, just use the least specific class in the class hierarchy that provides the required
functionality.

 */
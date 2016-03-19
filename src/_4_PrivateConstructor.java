/**
 * Created by Adrian on 17.03.2016.
 */
public class _4_PrivateConstructor {

}
/*
Occasionally you’ll want to write a class that is just a grouping of static methods
and static fields.
They can be used to group related methods on primitive values or arrays, in the manner
of java.lang.Math or java.util.Arrays. They can also be used to group static
methods, including factory methods, for objects that implement a particular
interface, in the manner of java.util.Collections. Lastly, they can be used
to group methods on a final class, instead of extending the class.

Such utility classes were not designed to be instantiated: an instance would be
nonsensical.

Attempting to enforce noninstantiability by making a class abstract does
not work.The class can be subclassed and the subclass instantiated.

A class can be made noninstantiable by including a private constructor.

// Noninstantiable utility class
public class UtilityClass {
// Suppress default constructor for noninstantiability
private UtilityClass() {
throw new AssertionError();
}
... // Remainder omitted
}

Because the explicit constructor is private, it is inaccessible outside of the
class. The AssertionError isn’t strictly required, but it provides insurance in
case the constructor is accidentally invoked from within the class.
As a side effect, this idiom also prevents the class from being subclassed. All
constructors must invoke a superclass constructor, explicitly or implicitly, and a
subclass would have no accessible superclass constructor to invoke.


*
* */
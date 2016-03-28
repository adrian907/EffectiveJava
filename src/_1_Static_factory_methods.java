/**
 * Created by Adrian on 27.03.2016.
 */
public class _1_Static_factory_methods {


    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
}

/*PLUSES :
*One advantage of static factory methods is that, unlike constructors, they
have names.(In cases where a class seems to require multiple
constructors with the same signature, replace the constructors with static factory
methods and carefully chosen names to highlight their differences.)

*A second advantage of static factory methods is that, unlike constructors,
they are not required to create a new object each time they’re invoked
(The ability of static factory methods to return the same object from repeated
invocations allows classes to maintain strict control over what instances exist at
any time. Classes that do this are said to be instance-controlled.)
Instance control allows a class to guarantee that it is a singleton or noninstantiable.
Also, it allows an immutable class to make the guarantee that no two equal instances
exist: a.equals(b) if and only if a==b,which may result in improved performance. Used in enums.

*A third advantage of static factory methods is that, unlike constructors,
they can return an object of any subtype of their return type.
(One application of this flexibility is that an API can return objects without
making their classes public.Hiding implementation classes in this fashion leads to
a very compact API. This technique lends itself to interface-based frameworks.)
Interfaces can’t have static methods, so by convention, static factory methods for
an interface named Type are put in a noninstantiable class (Item 4) named Types.

*A fourth advantage of static factory methods is that they reduce the verbosity
of creating parameterized type instances.

Map<String, List<String>> m =
new HashMap<String, List<String>>();
With static factories, however, the compiler can figure out the type parameters for you.
This is known as type inference(WNIOSKOWANIE TYPÓW). For example, suppose that HashMap provided this static factory:
public static <K, V> HashMap<K, V> newInstance() {
return new HashMap<K, V>();
}
Then you could replace the wordy declaration above with this succinct alternative:
Map<String, List<String>> m = HashMap.newInstance();
*/

//MINUSES :
/*The main disadvantage of providing only static factory methods is that
classes without public or protected constructors cannot be subclassed.

*A second disadvantage of static factory methods is that they are not
readily distinguishable from other static methods.
*/

/*NAMING CONVENTION : Here are some common names for static factory methods:
• valueOf—Returns an instance that has, loosely speaking, the same value as its
parameters. Such static factories are effectively type-conversion methods.
• of—A concise alternative to valueOf, popularized by EnumSet (Item 32).
• getInstance—Returns an instance that is described by the parameters but
cannot be said to have the same value. In the case of a singleton, getInstance
takes no parameters and returns the sole instance.
• newInstance—Like getInstance, except that newInstance guarantees that
each instance returned is distinct from all others.
• getType—Like getInstance, but used when the factory method is in a different
class. Type indicates the type of object returned by the factory method.
• newType—Like newInstance, but used when the factory method is in a different
class. Type indicates the type of object returned by the factory method
*/

/* TITBIT : A class can have only a single constructor with a given signature. Programmers
have been known to get around this restriction by providing two constructors
whose parameter lists differ only in the order of their parameter types.

TITBIT: The class java.util.EnumSet, introduced in release 1.5, has no
public constructors, only static factories. They return one of two implementations,
depending on the size of the underlying enum type: if it has sixty-four or fewer
elements, as most enum types do, the static factories return a RegularEnumSet
instance, which is backed by a single long; if the enum type has sixty-five or more
elements, the factories return a JumboEnumSet instance, backed by a long array.
 */

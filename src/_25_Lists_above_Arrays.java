/**
 * Created by Adrian on 22.03.2016.
 */
public class _25_Lists_above_Arrays {
}
/*

Arrays differ from generic types in two important ways. First, arrays are covariant.

Arrays differ from generic types in two important ways. First, arrays are covariant.
This scary-sounding word means simply that if Sub is a subtype of Super, then the
array type Sub[] is a subtype of Super[]. Generics, by contrast, are invariant: for
any two distinct types Type1 and Type2, List<Type1> is neither a subtype nor a
supertype of List<Type2>.


You might think this  means that generics are deficient, but arguably it is arrays that are deficient.
This code fragment is legal:

// Fails at runtime!
Object[] objectArray = new Long[1];
objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

but this one is not:

// Won't compile!
List<Object> ol = new ArrayList<Long>(); // Incompatible types
ol.add("I don't fit in");

With an array you find out that you’ve made a mistake at runtime; with a list,
you find out at compile time.

The second major difference between arrays and generics is that arrays are
reified (uściślone). This means that arrays know and enforce their element types at
runtime. Generics, by contrast, are implemented by erasure [JLS, 4.6]. This means that
they enforce their type constraints only at compile
time and discard (or erase) their element type information at runtime. Erasure is
what allows generic types to interoperate freely with legacy code that does not use
generics (Item 23).


It is illegal to create an array of a generic type, a parameterized
type, or a type parameter. None of these array creation expressions are legal: new
List<E>[], new List<String>[], new E[]. All will result in generic array creation
errors at compile time.

Why is it illegal to create a generic array? Because it isn’t typesafe.This would violate the fundamental guarantee
provided by the generic type system.
Ex :

To make this more concrete, consider the following code fragment:
// Why generic array creation is illegal - won't compile!

List<String>[] stringLists = new List<String>[1]; // (1)
List<Integer> intList = Arrays.asList(42); // (2)
Object[] objects = stringLists; // (3)
objects[0] = intList; // (4)
String s = stringLists[0].get(0); // (5)

Let’s pretend that line 1, which creates a generic array, is legal. Line 2 creates and
initializes a List<Integer> containing a single element. Line 3 stores the
List<String> array into an Object array variable, which is legal because arrays
are covariant. Line 4 stores the List<Integer> into the sole element of the
Object array, which succeeds because generics are implemented by erasure: the
runtime type of a List<Integer> instance is simply List, and the runtime type of
a List<String>[] instance is List[], so this assignment doesn’t generate an
ArrayStoreException. Now we’re in trouble. We’ve stored a List<Integer>
instance into an array that is declared to hold only List<String> instances. In
line 5, we retrieve the sole element from the sole list in this array. The compiler
automatically casts the retrieved element to String, but it’s an Integer, so we get
a ClassCastException at runtime. In order to prevent this from happening, line 1
(which creates a generic array) generates a compile-time error.


The runtime type of a List<Integer> instance is simply List.


Types such as E, List<E>, and List<String> are technically known as nonreifiable
types. Intuitively speaking, a non-reifiable type is one whose
runtime representation contains less information than its compile-time representation.
The only parameterized types that are reifiable are unbounded wildcard types
such as List<?> and Map<?,?> (Item 23). It is legal, though infrequently useful, to
create arrays of unbounded wildcard types.


The prohibition on generic array creation can be annoying. It means, for example,
that it’s not generally possible for a generic type to return an array of its element
type (but see Item 29 for a partial solution). It also means that you can get
confusing warnings when using varargs methods (Item 42) in combination with
generic types. This is because every time you invoke a varargs method, an array is
created to hold the varargs parameters. If the element type of this array is not reifiable,
you get a warning. There is little you can do about these warnings other than
to suppress them (Item 24), and to avoid mixing generics and varargs in your APIs.



When you get a generic array creation error, the best solution is often to use
the collection type List<E> in preference to the array type E[]. You might sacrifice
some performance or conciseness, but in exchange you get better type safety
and interoperability.


In summary, arrays and generics have very different type rules. Arrays are
covariant and reified; generics are invariant and erased. As a consequence, arrays
provide runtime type safety but not compile-time type safety and vice versa for
generics. Generally speaking, arrays and generics don’t mix well. If you find
yourself mixing them and getting compile-time errors or warnings, your first
impulse should be to replace the arrays with lists.






 */
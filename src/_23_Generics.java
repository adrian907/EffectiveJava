/**
 * Created by Adrian on 22.03.2016.
 */
public class _23_Generics {
}
/*
A class or interface whose declaration has one or more type
parameters is a generic class or interface.

Each generic type defines a set of parameterized types, which consist of the
class or interface name followed by an angle-bracketed list of actual type parameters
corresponding to the generic type’s formal type parameters.

Finally, each generic type defines a raw type, which is the name of the generic
type used without any accompanying actual type parameters, the raw type corresponding to List<E> is List.
Raw types behave as if all of  the generic type information were erased from the type declaration.
For all practical purposes, the raw type List behaves the same way as the interface type List
did before generics were added to the platform.


Thanks to generics there's no possibility to insert object of different type into Collection.
Problem will be found at compile-time , not just runtime.

As an added benefit, you no longer have to cast manually when removing elements
from collections. The compiler inserts invisible casts for you and guarantees
that they won’t fail (assuming, again, that all of your code was compiled with
a generics-aware compiler and did not produce or suppress any warnings).


As noted above, it is still legal to use collection types and other generic types
without supplying type parameters, but you should not do it. If you use raw
types, you lose all the safety and expressiveness benefits of generics.

What is the difference between the raw type List and the
parameterized type List<Object>? Loosely speaking, the former has opted out of
generic type checking, while the latter has explicitly told the compiler that it is
capable of holding objects of any type. While you can pass a List<String> to a
parameter of type List, you can’t pass it to a parameter of type List<Object>.
There are subtyping rules for generics, and List<String> is a subtype of the raw
type List, but not of the parameterized type List<Object> (Item 25).


// Uses raw type (List) - fails at runtime!
public static void main(String[] args) {
List<String> strings = new ArrayList<String>();
unsafeAdd(strings, new Integer(42));
String s = strings.get(0); // Compiler-generated cast
}
private static void unsafeAdd(List list, Object o) {
list.add(o);
}
This program compiles, but because it uses the raw type List, you get a warning:
Test.java:10: warning: unchecked call to add(E) in raw type List
list.add(o);
^
And indeed, if you run the program, you get a ClassCastException when the
program tries to cast the result of the invocation strings.get(0) to a String.
This is a compiler-generated cast, so it’s normally guaranteed to succeed, but in
this case we ignored a compiler warning and paid the price.


**************************HOW NOT TO WRITE GENERIC METHODS
// Use of raw type for unknown element type - don't do this!
static int numElementsInCommon(Set s1, Set s2) {
int result = 0;
for (Object o1 : s1)
if (s2.contains(o1))
result++;
return result;
}
***************************************************************

This method works but it uses raw types, which are dangerous. Since release
1.5, Java has provided a safe alternative known as unbounded wildcard types. If
you want to use a generic type but you don’t know or care what the actual type
parameter is, you can use a question mark instead. For example, the unbounded
wildcard type for the generic type Set<E> is Set<?> (read “set of some type”). It
is the most general parameterized Set type, capable of holding any set. Here is
how the numElementsInCommon method looks with unbounded wildcard types:


// Unbounded wildcard type - typesafe and flexible
static int numElementsInCommon(Set<?> s1, Set<?> s2) {
int result = 0;
for (Object o1 : s1)
if (s2.contains(o1))
result++;
return result;
}

The wildcard type is safe and the raw type isn’t.

You can’t put any element (other than null) into a Collection<?>.

Not only can’t you put any element (other than null) into a Collection<?>,
but you can’t assume anything about the type of the objects that you get out. If
these restrictions are unacceptable, you can use generic methods (Item 27) or
bounded wildcard types (Item 28).


***********************************************************
* WHEN TO USE RAW TYPES :

There are two minor exceptions to the rule that you should not use raw types
in new code, both of which stem from the fact that generic type information is
erased at runtime (Item 25). You must use raw types in class literals. The specification
does not permit the use of parameterized types (though it does permit
array types and primitive types) [JLS, 15.8.2]. In other words, List.class,
String[].class, and int.class are all legal, but List<String>.class and
List<?>.class are not.

The second exception to the rule concerns the instanceof operator. Because
generic type information is erased at runtime, it is illegal to use the instanceof
operator on parameterized types other than unbounded wildcard types. The use of
unbounded wildcard types in place of raw types does not affect the behavior of the
instanceof operator in any way. In this case, the angle brackets and question
marks are just noise. This is the preferred way to use the instanceof operator
with generic types:

// Legitimate use of raw type - instanceof operator
if (o instanceof Set) { // Raw type
Set<?> m = (Set<?>) o; // Wildcard type
...
}
Note that once you’ve determined that o is a Set, you must cast it to the wildcard
type Set<?>, not the raw type Set. This is a checked cast, so it will not cause a
compiler warning.


In summary, using raw types can lead to exceptions at runtime, so don’t use
them in new code. They are provided only for compatibility and interoperability
with legacy code that predates the introduction of generics. As a quick review,
Set<Object> is a parameterized type representing a set that can contain objects of
any type, Set<?> is a wildcard type representing a set that can contain only
objects of some unknown type, and Set is a raw type, which opts out of the
generic type system. The first two are safe and the last is not.


Term                        Example
Parameterized type          List<String>
Actual type parameter       String
Generic type                List<E>
Formal type parameter       E
Unbounded wildcard type     List<?>
Raw type                    List
Bounded type parameter      <E extends Number>
Recursive type bound        <T extends Comparable<T>>
Bounded wildcard type       List<? extends Number>
Generic method              static <E> List<E> asList(E[] a)
Type token                  String.class




















 */
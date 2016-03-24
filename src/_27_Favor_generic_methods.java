/**
 * Created by Adrian on 23.03.2016.
 */
public class _27_Favor_generic_methods {
}
/*

Just as classes can benefit from generification, so can methods. Static utility methods
are particularly good candidates for generification. All of the “algorithm”
methods in Collections (such as binarySearch and sort) have been generified.


// Uses raw types - unacceptable! (Item 23)
public static Set union(Set s1, Set s2) {
Set result = new HashSet(s1);
result.addAll(s2);
return result;
}


This method compiles, but with two warnings:

Union.java:5: warning: [unchecked] unchecked call to
HashSet(Collection<? extends E>) as a member of raw type HashSet
Set result = new HashSet(s1);
^
Union.java:6: warning: [unchecked] unchecked call to
addAll(Collection<? extends E>) as a member of raw type Set
result.addAll(s2);
^

To fix these warnings and make the method typesafe, modify the method declaration
to declare a type parameter representing the element type for the three sets
(two arguments and the return value) and use the type parameter in the method.
The type parameter list, which declares the type parameter, goes between the
method’s modifiers and its return type. In this example, the type parameter list
is <E> and the return type is Set<E>. The

// Generic method
public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
Set<E> result = new HashSet<E>(s1);
result.addAll(s2);
return result;
}

A limitation of the union method is that the types of all three sets (both input
parameters and the return value) have to be the same.


One noteworthy feature of generic methods is that you needn’t specify the
value of the type parameter explicitly as you must when invoking generic constructors.
The compiler figures out the value of the type parameters by examining
the types of the method arguments. In the case of the program above, the compiler
sees that both arguments to union are of type Set<String>, so it knows that the
type parameter E must be String. This process is called type inference.


A related pattern is the generic singleton factory. On occasion, you will need
to create an object that is immutable but applicable to many different types.
Because generics are implemented by erasure (Item 25), you can use a single
object for all required type parameterizations, but you need to write a static factory
method to repeatedly dole out the object for each requested type parameterization.
This pattern is most frequently used for function objects (Item 21) such as
Collections.reverseOrder, but it is also used for collections such as Collections.
emptySet.
Suppose you have an interface that describes a function that accepts and
returns a value of some type T:
public interface UnaryFunction<T> {
T apply(T arg);
}
Now suppose that you want to provide an identity function. It would be wasteful
to create a new one each time it’s required, as it’s stateless. If generics were reified,
you would need one identity function per type, but since they’re erased you
need only a generic singleton. Here’s how it looks:
// Generic singleton factory pattern
private static UnaryFunction<Object> IDENTITY_FUNCTION =
new UnaryFunction<Object>() {
public Object apply(Object arg) { return arg; }
};
// IDENTITY_FUNCTION is stateless and its type parameter is
// unbounded so it's safe to share one instance across all types.
@SuppressWarnings("unchecked")
public static <T> UnaryFunction<T> identityFunction() {
return (UnaryFunction<T>) IDENTITY_FUNCTION;
}

Here is a sample program that uses our generic singleton as a UnaryFunction<
String> and a UnaryFunction<Number>. As usual, it contains no casts and
compiles without errors or warnings:
// Sample program to exercise generic singleton
public static void main(String[] args) {
String[] strings = { "jute", "hemp", "nylon" };
UnaryFunction<String> sameString = identityFunction();
for (String s : strings)
System.out.println(sameString.apply(s));
Number[] numbers = { 1, 2.0, 3L };
UnaryFunction<Number> sameNumber = identityFunction();
for (Number n : numbers)
System.out.println(sameNumber.apply(n));
}


It is permissible, though relatively rare, for a type parameter to be bounded by
some expression involving that type parameter itself. This is what’s known as a
recursive type bound. The most common use of recursive type bounds is in connection
with the Comparable interface, which defines a type’s natural ordering:
public interface Comparable<T> {
int compareTo(T o);
}
The type parameter T defines the type to which elements of the type implementing
Comparable<T> can be compared. In practice, nearly all types can be compared
only to elements of their own type. So, for example, String implements Comparable<
String>, Integer implements Comparable<Integer>, and so on.

Here is a method to go with the declaration above. It calculates the maximum
value of a list according to its elements’ natural order, and it compiles without
errors or warnings:
// Returns the maximum value in a list - uses recursive type bound
public static <T extends Comparable<T>> T max(List<T> list) {
Iterator<T> i = list.iterator();
T result = i.next();
while (i.hasNext()) {
T t = i.next();
if (t.compareTo(result) > 0)
result = t;
}
return result;
}
Recursive type bounds can get much more complex than this, but luckily it
doesn’t happen too often.


In summary, generic methods, like generic types, are safer and easier to use
than methods that require their clients to cast input parameters and return values.
Like types, you should make sure that your new methods can be used without
casts, which will often mean making them generic. And like types, you should
generify your existing methods to make life easier for new users without breaking
existing clients (Item 23).

 */
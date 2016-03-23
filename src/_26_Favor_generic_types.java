/**
 * Created by Adrian on 23.03.2016.
 */
public class _26_Favor_generic_types {
}
/*
As explained in Item 25, you can’t create an array of a non-reifiable type, such
as E. This problem arises every time you write a generic type that is backed by an
array. There are two ways to solve it. The first solution directly circumvents the
prohibition on generic array creation: create an array of Object and cast it to the
generic array type.Now in place of an error, the compiler will emit a warning.
This usage is legal, but it’s not (in general) typesafe:
Stack.java:8: warning: [unchecked] unchecked cast
found : Object[], required: E[]
elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];


// The elements array will contain only E instances from push(E).
// This is sufficient to ensure type safety, but the runtime
// type of the array won't be E[]; it will always be Object[]!
@SuppressWarnings("unchecked")
public Stack() {
elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
}


The second way to eliminate the generic array creation error in Stack is to
change the type of the field elements from E[] to Object[]. If you do this, you’ll
get a different error:
Stack.java:19: incompatible types
found : Object, required: E
E result = elements[--size];
^
You can change this error into a warning by casting the element retrieved from the
array from Object to E:
Stack.java:19: warning: [unchecked] unchecked cast
found : Object, required: E
E result = (E) elements[--size];
^
Because E is a non-reifiable type, there’s no way the compiler can check the
cast at runtime.


But in a more realistic generic class than Stack, you
would probably be reading from the array at many points in the code, so choosing
the second solution would require many casts to E rather than a single cast to E[],
which is why the first solution is used more commonly.

The foregoing example may appear to contradict Item 25, which encourages
the use of lists in preference to arrays. It is not always possible or desirable to use
lists inside your generic types. Java doesn’t support lists natively, so some generic
types, such as ArrayList, must be implemented atop arrays. Other generic types,
such as HashMap, are implemented atop arrays for performance.

The great majority of generic types can be created like a :  Stack<Object>,
Stack<int[]>, Stack<List<String>>, or a Stack of any other object reference
type.

Note that you can’t create a Stack of a primitive type: trying to create a
Stack<int> or Stack<double> will result in a compile-time error.

There are some generic types that restrict the permissible values of their type
parameters. For example, consider java.util.concurrent.DelayQueue, whose
declaration looks like this:

class DelayQueue<E extends Delayed> implements BlockingQueue<E>;

This allows the DelayQueue implementation and its clients to take advantage of Delayed
methods on the elements of a DelayQueue, without the need for explicit casting or
the risk of a ClassCastException. The type parameter E is known as a bounded
type parameter. Note that the subtype relation is defined so that every type is a
subtype of itself [JLS, 4.10], so it is legal to create a DelayQueue<Delayed>.


In summary, generic types are safer and easier to use than types that require
casts in client code. When you design new types, make sure that they can be used
without such casts. This will often mean making the types generic. Generify your
existing types as time permits. This will make life easier for new users of these
types without breaking existing clients (Item 23).








 */
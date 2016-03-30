/**
 * Created by Adrian on 29.03.2016.
 */
public class _57_Exceptions_use {
}
/*

// Horrible abuse of exceptions. Don't ever do this!
try {
int i = 0;
while(true)
range[i++].climb();
} catch(ArrayIndexOutOfBoundsException e) {
}

There are three things wrong with this reasoning:
• Because exceptions are designed for exceptional circumstances, JVM implementations
don't optimize well such situations. Creating, submitting and catching exceptions is a costful operation.
• Placing code inside a try-catch block inhibits certain optimizations that modern
JVM implementations might otherwise perform.
• The standard idiom for looping through an array doesn’t necessarily result in
redundant checks. Modern JVM implementations optimize them away.


Not only does the exception-based loop obfuscate the purpose of the code and
reduce its performance, but it’s not guaranteed to work! If a computation inside the loop, would generate exception
but touching different array, not connected with above-mentioned, then it would stop working and return false results.

Exceptions are, as their name implies, to be used only for exceptional conditions;
they should never be used for ordinary control flow.

A well-designed API must not force its clients to use exceptions for ordinary control flow.

A class with a “state-dependent” method that can be invoked only under certain unpredictable
conditions should generally have a separate “state-testing” method indicating
whether it is appropriate to invoke the state-dependent method. For example, the
Iterator interface has the state-dependent method next and the corresponding
state-testing method hasNext.

for (Iterator<Foo> i = collection.iterator(); i.hasNext(); ) {
Foo foo = i.next();
...
}


// Do not use this hideous code for iteration over a collection!
try {
Iterator<Foo> i = collection.iterator();
while(true) {
Foo foo = i.next();
...
}
} catch (NoSuchElementException e) {
}


An alternative to providing a separate state-testing method is to have the statedependent
method return a distinguished value such as null if it is invoked with
the object in an inappropriate state.

Here are some guidelines to help you choose between a state-testing method
and a distinguished return value. If an object is to be accessed concurrently without
external synchronization or is subject to externally induced state transitions, you
must use a distinguished return value, as the object’s state could change in the
interval between the invocation of a state-testing method and its state-dependent
method. Performance concerns may dictate that a distinguished return value be
used if a separate state-testing method would duplicate the work of the statedependent
method. All other things being equal, a state-testing method is mildly
preferable to a distinguished return value. It offers slightly better readability, and
incorrect use may be easier to detect: if you forget to call a state-testing method,
the state-dependent method will throw an exception, making the bug obvious; if
you forget to check for a distinguished return value, the bug may be subtle.




 */
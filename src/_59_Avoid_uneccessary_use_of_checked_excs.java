/**
 * Created by Adrian on 30.03.2016.
 */
public class _59_Avoid_uneccessary_use_of_checked_excs {
}
/*

Checked exceptions are a wonderful feature of the Java programming language.
Unlike return codes, they force the programmer to deal with exceptional conditions,
greatly enhancing reliability.

If a method throws one or more checked exceptions, the
code that invokes the method must handle the exceptions in one or more catch
blocks, or it must declare that it throws the exceptions and let them propagate outward.
Either way, it places a nontrivial burden on the programmer.

The burden is justified if the exceptional condition cannot be prevented by
proper use of the API and the programmer using the API can take some useful
action once confronted with the exception. Unless both of these conditions hold,
an unchecked exception is more appropriate.



} catch(TheCheckedException e) {
throw new AssertionError(); // Can't happen!
}
How about this?
} catch(TheCheckedException e) {
e.printStackTrace(); // Oh well, we lose.
System.exit(1);
}
If the programmer using the API can do no better, an unchecked exception
would be more appropriate. One example of an exception that fails this test is
CloneNotSupportedException. It is thrown by Object.clone, which should be
invoked only on objects that implement Cloneable (Item 11).



One technique for turning a checked exception into an unchecked exception is
to break the method that throws the exception into two methods, the first of which
returns a boolean that indicates whether the exception would be thrown. This API
refactoring transforms the calling sequence from this:
// Invocation with checked exception
try {
obj.action(args);
} catch(TheCheckedException e) {
// Handle exceptional condition
...
}
to this:
// Invocation with state-testing method and unchecked exception
if (obj.actionPermitted(args)) {
obj.action(args);
} else {
// Handle exceptional condition
...
}

While the latter calling sequence is no prettier
than the former, the resulting API is more flexible.

The API resulting from this refactoring is essentially
identical to the state-testing method API in Item 57 and the same caveats
apply: if an object is to be accessed concurrently without external synchronization
or it is subject to externally induced state transitions, this refactoring is inappropriate,
as the object’s state may change between the invocations of actionPermitted
and action.











 */
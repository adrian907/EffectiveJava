/**
 * Created by Adrian on 30.03.2016.
 */
public class _61_Throw_exceptions_appropriate_to_the_abstraction {
}
/*

It is disconcerting when a method throws an exception that has no apparent connection
to the task that it performs. This often happens when a method propagates an
exception thrown by a lower-level abstraction. Not only is this disconcerting, but it
pollutes the API of the higher layer with implementation details. If the implementation
of the higher layer changes in a subsequent release, the exceptions that it throws
will change too, potentially breaking existing client programs.


Higher layers should catch lower-level exceptions and, in their place, throw exceptions that
can be explained in terms of the higher-level abstraction. This idiom is known as exception translation:
// Exception Translation
try {
// Use lower-level abstraction to do our bidding
...
} catch(LowerLevelException e) {
throw new HigherLevelException(...);
}



Here is an example of exception translation taken from the
AbstractSequentialList class, which is a skeletal implementation (Item 18) of
the List interface. In this example, exception translation is mandated by the
specification of the get method in the List<E> interface:
*
* Returns the element at the specified position in this list.
* @throws IndexOutOfBoundsException if the index is out of range
* ({@code index < 0 || index >= size()}).
*
public E get(int index) {
    ListIterator<E> i = listIterator(index);
    try {
        return i.next();
    } catch(NoSuchElementException e) {
        throw new IndexOutOfBoundsException("Index: " + index);
    }
}


EXCEPTION CHAINING :
A special form of exception translation called exception chaining is appropriate
in cases where the lower-level exception might be helpful to someone debugging
the problem that caused the higher-level exception. The lower-level
ITEM 61: THROW EXCEPTIONS APPROPRIATE TO THE ABSTRACTION 251
exception (the cause) is passed to the higher-level exception, which provides an
accessor method (Throwable.getCause) to retrieve the lower-level exception:
// Exception Chaining
try {
... // Use lower-level abstraction to do our bidding
} catch (LowerLevelException cause) {
throw new HigherLevelException(cause);
}

The higher-level exception’s constructor passes the cause to a chaining-aware
superclass constructor, so it is ultimately passed to one of Throwable’s chainingaware
constructors, such as Throwable(Throwable):
// Exception with chaining-aware constructor
class HigherLevelException extends Exception {
HigherLevelException(Throwable cause) {
super(cause);
}
}


Most standard exceptions have chaining-aware constructors. For exceptions that
don’t, you can set the cause using Throwable’s initCause method. Not only does
exception chaining let you access the cause programmatically (with getCause), but
it integrates the cause’s stack trace into that of the higher-level exception.


While exception translation is superior to mindless propagation of exceptions
from lower layers, it should not be overused. Where possible, the best
way to deal with exceptions from lower layers is to avoid them, by ensuring that
lower-level methods succeed. Sometimes you can do this by checking the validity
of the higher-level method’s parameters before passing them on to lower layers.

In summary, if it isn’t feasible to prevent or to handle exceptions from lower
layers, use exception translation, unless the lower-level method happens to guarantee
that all of its exceptions are appropriate to the higher level. Chaining provides
the best of both worlds: it allows you to throw an appropriate higher-level
exception, while capturing the underlying cause for failure analysis (Item 63).


 */












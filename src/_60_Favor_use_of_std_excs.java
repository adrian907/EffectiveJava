/**
 * Created by Adrian on 30.03.2016.
 */
public class _60_Favor_use_of_std_excs {
}
/*

One of the attributes that most strongly distinguishes expert programmers from less
experienced ones is that experts strive for and usually achieve a high degree of code
reuse.


Reusing preexisting exceptions has several benefits:
- makes your API easier to learn and use.
- programs using your API are easier to read
- fewer exception classes mean a smaller memory footprint and less time spent loading classes.

The most commonly reused exception is IllegalArgumentException.

IllegalStateException - this would be the exception to throw if the
caller attempted to use some object before it had been properly initialized.

Arguably, all erroneous method invocations boil down to an illegal argument
or illegal state, but other exceptions are standardly used for certain kinds of illegal
arguments and states.


Another general-purpose exception worth knowing about is ConcurrentModificationException.
This exception should be thrown if an object that was
designed for use by a single thread or with external synchronization detects that it
is being (or has been) concurrently modified.


A last general-purpose exception worthy of note is UnsupportedOperation-
Exception. This is the exception to throw if an object does not support an
attempted operation. Its use is rare compared to the other exceptions discussed in
this item, as most objects support all the methods they implement. This exception
is used by implementations that fail to implement one or more optional operations
defined by an interface.


If an exception fits your needs, go ahead and use it,
but only if the conditions under which you would throw it are consistent with the
exception’s documentation.






 */
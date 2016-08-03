/**
 * Created by Adrian on 30.03.2016.
 */
public class _64_Strive_for_failure_atomicity {
}
/*

Generally speaking, a failed method invocation should leave
the object in the state that it was in prior to the invocation.
A method with this property is said to be failure atomic.

Ways to achieve failure atomicity :
- designing immutable objects.
- checking parameter validity before operation. ( mutable objects - This causes any exception to get thrown before object modification
commences)
-order the computation (any part that may fail takes place before any part that modifies the
object. This approach is a natural extension of the previous one when arguments
cannot be checked without performing a part of the computation. For example,
consider the case of TreeMap, whose elements are sorted according to some ordering.
In order to add an element to a TreeMap, the element must be of a type that
can be compared using the TreeMap’s ordering. Attempting to add an incorrectly
typed element will naturally fail with a ClassCastException as a result of
searching for the element in the tree, before the tree has been modified in any way.)
-write recovery code that intercepts a failure that occurs in the midst of an operation
and causes the object to roll back its state to the point before the operation
-perform the operation on a temporary copy of the object and to replace the contents of the object with the
temporary copy once the operation is complete. (For example, Collections.sort dumps its
input list into an array prior to sorting to reduce the cost of accessing elements in
the inner loop of the sort. This is done for performance, but as an added benefit, it
ensures that the input list will be untouched if the sort fails.)


Errors (as opposed to exceptions) are unrecoverable, and methods need not even attempt to preserve failure atomicity
when throwing errors.

While failure atomicity is generally desirable, it is not always achievable. For
example, if two threads attempt to modify the same object concurrently without
proper synchronization, the object may be left in an inconsistent state. It would
therefore be wrong to assume that an object was still usable after catching a ConcurrentModificationException.

Even where failure atomicity is possible, it is not always desirable. For some
operations, it would significantly increase the cost or complexity.

As a rule, any generated exception that is part of a method’s specification
should leave the object in the same state it was in prior to the method invocation.
Where this rule is violated, the API documentation should clearly indicate what
state the object will be left in. Unfortunately, plenty of existing API documentation
fails to live up to this ideal.









 */
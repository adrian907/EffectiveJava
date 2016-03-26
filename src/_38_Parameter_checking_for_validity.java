/**
 * Created by Adrian on 26.03.2016.
 */
public class _38_Parameter_checking_for_validity {
}
/*
Most methods and constructors have some restrictions on what values may be
passed into their parameters. For example, it is not uncommon that index values
must be non-negative and object references must be non-null. You should clearly
document all such restrictions and enforce them with checks at the beginning of
the method body.

For public methods, use the Javadoc @throws tag to document the exception
that will be thrown if a restriction on parameter values is violated (Item 62). Typically
the exception will be IllegalArgumentException, IndexOutOfBounds
Exception, or NullPointerException (Item 60).

EX :
* Returns a BigInteger whose value is (this mod m). This method
* differs from the remainder method in that it always returns a
* non-negative BigInteger.
*
* @param m the modulus, which must be positive
* @return this mod m
* @throws ArithmeticException if m is less than or equal to 0

public BigInteger mod(BigInteger m) {
    if (m.signum() <= 0)
        throw new ArithmeticException("Modulus <= 0: " + m);
    ... // Do the computation
}


For an unexported method, you as the package author control the circumstances
under which the method is called, so you can and should ensure that only
valid parameter values are ever passed in. Therefore, nonpublic methods should
generally check their parameters using assertions.

Constructors represent a special case of the principle that you should check
the validity of parameters that are to be stored away for later use. It is critical to
check the validity of constructor parameters to prevent the construction of an
object that violates its class invariants.


There are exceptions to the rule that you should check a method’s parameters
before performing its computation. An important exception is the case in which
the validity check would be expensive or impractical and the validity check is performed
implicitly in the process of doing the computation. For example, consider
a method that sorts a list of objects, such as Collections.sort(List). All of the
objects in the list must be mutually comparable. In the process of sorting the list,
every object in the list will be compared to some other object in the list. If the
objects aren’t mutually comparable, one of these comparisons will throw a Class-
CastException, which is exactly what the sort method should do. Therefore,
there would be little point in checking ahead of time that the elements in the list
were mutually comparable.



Do not infer from this item that arbitrary restrictions on parameters are a good
thing. On the contrary, you should design methods to be as general as it is practical
to make them. The fewer restrictions that you place on parameters, the better,
assuming the method can do something reasonable with all of the parameter values
that it accepts.

















 */
/**
 * Created by Adrian on 27.03.2016.
 */
public class _42_Varargs {
}

/*
In release 1.5, varargs methods, formally known as variable arity methods,
were added to the language. Varargs methods accept zero or more
arguments of a specified type. The varargs facility works by first creating an array
whose size is the number of arguments passed at the call site, then putting the
argument values into the array, and finally passing the array to the method.


Varargs were designed for printf, which was added to the platform in release 1.5, and for
the core reflection facility (Item 53), which was retrofitted to take advantage of
varargs in that release. Both printf and reflection benefit enormously from
varargs.


You can retrofit an existing method that takes an array as its final parameter to
take a varargs parameter instead with no effect on existing clients. But just
because you can doesn’t mean that you should!


Don’t retrofit every method that has a final array
parameter; use varargs only when a call really operates on a variable-length
sequence of values.


Exercise care when using the varargs facility in performance-critical situations.
Every invocation of a varargs method causes an array allocation and initialization.
If you have determined empirically that you can’t afford this cost but you
need the flexibility of varargs, there is a pattern that lets you have your cake and
eat it too. Suppose you’ve determined that 95 percent of the calls to a method have
three or fewer parameters. Then declare five overloadings of the method, one each
with zero through three ordinary parameters, and a single varargs method for use
when the number of arguments exceeds three:

public void foo() { }
public void foo(int a1) { }
public void foo(int a1, int a2) { }
public void foo(int a1, int a2, int a3) { }
public void foo(int a1, int a2, int a3, int... rest) { }

Now you know that you’ll pay the cost of the array creation only in the 5 percent
of all invocations where the number of parameters exceeds three. Like most
performance optimizations, this technique usually isn’t appropriate, but when it is,
it’s a lifesaver.
















 */
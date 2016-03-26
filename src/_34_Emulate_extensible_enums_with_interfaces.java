/**
 * Created by Adrian on 26.03.2016.
 */
public class _34_Emulate_extensible_enums_with_interfaces {
}
/*
In almost all respects, enum types are superior to the typesafe enum pattern. On the face of it, one exception
concerns extensibility, which was possible under the original pattern but is
not supported by the language construct. In other words, using the pattern, it was
possible to have one enumerated type extend another; using the language feature,
it is not. This is no accident. For the most part, extensibility of enums turns out to
be a bad idea. It is confusing that elements of an extension type are instances of
the base type and not vice versa. There is no good way to enumerate over all of the
elements of a base type and its extension. Finally, extensibility would complicate
many aspects of the design and implementation.


There is at least one compelling use case for extensible enumerated
types, which is operation codes, also known as opcodes. An opcode is an enumerated
type whose elements represent operations on some machine, such as the
Operation type in Item 30.


Luckily, there is a nice way to achieve this effect using enum types. The basic
idea is to take advantage of the fact that enum types can implement arbitrary interfaces
by defining an interface for the opcode type and an enum that is the standard
implementation of the interface. For example, here is an extensible version of
Operation type from Item 30:

// Emulated extensible enum using an interface
public interface Operation {
double apply(double x, double y);
}
public enum BasicOperation implements Operation {
PLUS("+") {
public double apply(double x, double y) { return x + y; }
},
MINUS("-") {
public double apply(double x, double y) { return x - y; }
},
TIMES("*") {
public double apply(double x, double y) { return x * y; }
},
DIVIDE("/") {
public double apply(double x, double y) { return x / y; }
};


private final String symbol;
BasicOperation(String symbol) {
this.symbol = symbol;
}
@Override public String toString() {
return symbol;
}
}

While the enum type (BasicOperation) is not extensible, the interface type
(Operation) is, and it is the interface type that is used to represent operations in
APIs. You can define another enum type that implements this interface and use
instances of this new type in place of the base type. For example, suppose you
want to define an extension to the operation type above, consisting of the exponentiation
and remainder operations. All you have to do is write an enum type that
implements the Operation interface:

// Emulated extension enum
public enum ExtendedOperation implements Operation {
EXP("^") {
public double apply(double x, double y) {
return Math.pow(x, y);
}
},
REMAINDER("%") {
public double apply(double x, double y) {
return x % y;
}
};



Not only is it possible to pass a single instance of an “extension enum” anywhere
a “base enum” is expected; it is possible to pass in an entire extension enum
type and use its elements in addition to or instead of those of the base type.


Example of test-checker program :
// Program to process marker annotations
import java.lang.reflect.*;
public class RunTests {
public static void main(String[] args) throws Exception {
int tests = 0;
int passed = 0;
Class testClass = Class.forName(args[0]);
for (Method m : testClass.getDeclaredMethods()) {
if (m.isAnnotationPresent(Test.class)) {
tests++;
try {
m.invoke(null);
passed++;
} catch (InvocationTargetException wrappedExc) {
Throwable exc = wrappedExc.getCause();
System.out.println(m + " failed: " + exc);
} catch (Exception exc) {
System.out.println("INVALID @Test: " + m);
}
}
}
System.out.printf("Passed: %d, Failed: %d%n",
passed, tests - passed);
}
}



Now let’s add support for tests that succeed only if they throw a particular
exception. We’ll need a new annotation type for this:

// Annotation type with a parameter
import java.lang.annotation.*;
**
* Indicates that the annotated method is a test method that
* must throw the designated exception to succeed.
*
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Exception> value();
}

The type of the parameter for this annotation is Class<? extends Exception>.
This wildcard type is, admittedly, a mouthful. In English, it means “the
Class object for some class that extends Exception,” and it allows the user of the
annotation to specify any exception type. This usage is an example of a bounded
type token (Item 29). Here’s how the annotation looks in practice. Note that class
literals are used as the values for the annotation parameter:

// Program containing annotations with a parameter
public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1() { // Test should pass
        int i = 0;
        i = i / i;
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m2() { // Should fail (wrong exception)
        int[] a = new int[0];
        int i = a[1];
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m3() { } // Should fail (no exception)
}


Taking our exception testing example one step further, it is possible to envision
a test that passes if it throws any one of several specified exceptions. The
annotation mechanism has a facility that makes it easy to support this usage. Suppose
we change the parameter type of the ExceptionTest annotation to be an
array of Class objects:

// Annotation type with an array parameter
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
Class<? extends Exception>[] value();
}

The syntax for array parameters in annotations is flexible. It is optimized for
single-element arrays. All of the previous ExceptionTest annotations are still
valid with the new array-parameter version of ExceptionTest and result in single-
element arrays. To specify a multiple-element array, surround the elements
with curly braces and separate them with commas:
// Code containing an annotation with an array parameter
@ExceptionTest({ IndexOutOfBoundsException.class,
NullPointerException.class })
public static void doublyBad() {
List<String> list = new ArrayList<String>();
// The spec permits this method to throw either
// IndexOutOfBoundsException or NullPointerException
list.addAll(5, null);
}























In summary, while you cannot write an extensible enum type, you can
emulate it by writing an interface to go with a basic enum type that implements
the interface. This allows clients to write their own enums that implement
the interface. These enums can then be used wherever the basic enum type can be
used, assuming APIs are written in terms of the interface.

 */
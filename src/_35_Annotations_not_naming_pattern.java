/**
 * Created by Adrian on 26.03.2016.
 */
public class _35_Annotations_not_naming_pattern {
}
/*

Prior to release 1.5, it was common to use naming patterns to indicate that some
program elements demanded special treatment by a tool or framework. For example,
the JUnit testing framework originally required its users to designate test
methods by beginning their names with the characters test. This technique
works, but it has several big disadvantages:

First, typographical errors may result in silent failures.
For example, suppose you accidentally name a test method
tsetSafetyOverride instead of testSafetyOverride. JUnit will not complain,
but it will not execute the test either, leading to a false sense of security.

A second disadvantage of naming patterns is that there is no way to ensure
that they are used only on appropriate program elements. For example, suppose
you call a class testSafetyMechanisms in hopes that JUnit will automatically
test all of its methods, regardless of their names. Again, JUnit won’t complain, but
it won’t execute the tests either.

A third disadvantage of naming patterns is that they provide no good way to
associate parameter values with program elements. For example, suppose you
want to support a category of test that succeeds only if it throws a particular
exception. The exception type is essentially a parameter of the test. You could
encode the exception type name into the test method name using some elaborate
naming pattern, but this would be ugly and fragile (Item 50). The compiler would
have no way of knowing to check that the string that was supposed to name an
exception actually did. If the named class didn’t exist or wasn’t an exception, you
wouldn’t find out until you tried to run the test.

Annotations solve all of these problems nicely. Suppose you want
to define an annotation type to designate simple tests that are run automatically
and fail if they throw an exception. Here’s how such an annotation type, named
Test, might look:

// Marker annotation type declaration
import java.lang.annotation.*;
**
* Indicates that the annotated method is a test method.
* Use only on parameterless static methods.
*
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}

The declaration for the Test annotation type is itself annotated with Retention
and Target annotations. Such annotations on annotation type declarations
are known as meta-annotations. The @Retention(RetentionPolicy.RUNTIME)
meta-annotation indicates that Test annotations should be retained at runtime.
Without it, Test annotations would be invisible to the test tool. The @Target(
ElementType.METHOD) meta-annotation indicates that the Test annotation is
legal only on method declarations: it cannot be applied to class declarations, field
declarations, or other program elements.


Note the comment above the Test annotation declaration that says, “Use only
on parameterless static methods.” It would be nice if the compiler could enforce
this restriction, but it can’t.

If the programmer were to misspell Test, or to apply the Test annotation to a program
element other than a method declaration, the program wouldn’t compile.


EXAMPLE :
// Program containing marker annotations
public class Sample {
@Test public static void m1() { } // Test should pass
public static void m2() { }
@Test public static void m3() { // Test Should fail
throw new RuntimeException("Boom");
}
public static void m4() { }
@Test public void m5() { } // INVALID USE: nonstatic method
public static void m6() { }
@Test public static void m7() { // Test should fail
throw new RuntimeException("Crash");
}
public static void m8() { }
}



Annotations never change the semantics of the annotated code, but
enable it for special treatment by tools.



























 */
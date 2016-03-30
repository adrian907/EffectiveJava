/**
 * Created by Adrian on 30.03.2016.
 */
public class _62_Document_exceptions {
}
/*
Always declare checked exceptions individually, and document precisely
the conditions under which each one is thrown using the Javadoc @throws
tag.


As an extreme example, never declare
that a method “throws Exception” or, worse yet, “throws Throwable.”


While the language does not require programmers to declare the unchecked
exceptions that a method is capable of throwing, it is wise to document them as
carefully as the checked exceptions.


A well-documented list of the unchecked exceptions that a method can throw effectively describes the
preconditions for its successful execution. It is essential that each method’s documentation
describe its preconditions, and documenting its unchecked exceptions
is the best way to satisfy this requirement.


It is particularly important that methods in interfaces document the unchecked
exceptions they may throw.

Use the Javadoc @throws tag to document each unchecked exception that
a method can throw, but do not use the throws keyword to include unchecked
exceptions in the method declaration.

If an exception is thrown by many methods in a class for the same reason,
it is acceptable to document the exception in the class’s documentation comment
rather than documenting it individually for each method.A common example
is NullPointerException. It is fine for a class’s documentation comment to
say, “All methods in this class throw a NullPointerException if a null object reference
is passed in any parameter,”

In summary, document every exception that can be thrown by each method
that you write. This is true for unchecked as well as checked exceptions, and for
abstract as well as concrete methods. Provide individual throws clauses for each
checked exception and do not provide throws clauses for unchecked exceptions.
If you fail to document the exceptions that your methods can throw, it will be difficult
or impossible for others to make effective use of your classes and interfaces.






 */
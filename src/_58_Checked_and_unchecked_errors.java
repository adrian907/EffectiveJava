/**
 * Created by Adrian on 29.03.2016.
 */
public class _58_Checked_and_unchecked_errors {
}
/*

The cardinal rule in deciding whether to use a checked or an unchecked
exception is this: use checked exceptions for conditions from which the caller
can reasonably be expected to recover.

There are two kinds of unchecked throwables: runtime exceptions and errors.

Use runtime exceptions to indicate programming errors.

The great majority of runtime exceptions indicate precondition violations.

While the Java Language Specification does not require it, there is a strong
convention that errors are reserved for use by the JVM to indicate resource deficiencies,
invariant failures, or other conditions that make it impossible to continue
execution.

All of the unchecked throwables you implement should subclass RuntimeException.

It is possible to define a throwable that is not a subclass of Exception, RuntimeException,
or Error. When should you use such a beast? In a word, never. It has no benefits over an ordinary
checked exception and would merely serve to confuse the user of your API.

To summarize, use checked exceptions for recoverable conditions and runtime
exceptions for programming errors.

If it isn’t clear whether recovery is possible, you’re probably
better off using an unchecked exception, for reasons discussed in Item 59.

API designers often forget that exceptions are full-fledged objects on which
arbitrary methods can be defined.

code that parses the string representation of an exception is
likely to be nonportable and fragile.

Because checked exceptions generally indicate recoverable conditions, it’s
especially important for such exceptions to provide methods that furnish information
that could help the caller to recover. For example, suppose a checked exception
is thrown when an attempt to make a purchase with a gift card fails because
the card doesn’t have enough money left on it. The exception should provide an
accessor method to query the amount of the shortfall, so the amount can be
relayed to the shopper.

 */
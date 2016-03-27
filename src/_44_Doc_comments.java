/**
 * Created by Adrian on 27.03.2016.
 */
public class _44_Doc_comments {
}

/*

Javadoc generates API documentation automatically from source code with specially
formatted documentation comments, more commonly known as doc comments.

To document your API properly, you must precede every exported class,
interface, constructor, method, and field declaration with a doc comment. If a
class is serializable, you should also document its serialized form (Item 75).

To write maintainable code, you should also write doc comments for most unexported classes,
interfaces, constructors, methods, and fields.

The doc comment for a method should describe succinctly the contract
between the method and its client. With the exception of methods in classes
designed for inheritance (Item 17), the contract should say what the method does
rather than how it does its job. The doc comment should enumerate all of the
method’s preconditions, which are the things that have to be true in order for a client
to invoke it, and its postconditions, which are the things that will be true after
the invocation has completed successfully. Typically, preconditions are described
implicitly by the @throws tags for unchecked exceptions; each unchecked exception
corresponds to a precondition violation. Also, preconditions can be specified
along with the affected parameters in their @param tags.
In addition to preconditions and postconditions, methods should document
any side effects. A side effect is an observable change in the state of the system
that is not obviously required in order to achieve the postcondition. For example,
if a method starts a background thread, the documentation should make note of it.
Finally, documentation comments should describe the thread safety of a class or
method, as discussed in Item 70.


CONVENTION
To describe a method’s contract fully, the doc comment should have an
@param tag for every parameter, an @return tag unless the method has a void
return type, and an @throws tag for every exception thrown by the method,
whether checked or unchecked (Item 62). By convention, the text following an
@param tag or @return tag should be a noun phrase describing the value represented
by the parameter or return value. The text following an @throws tag
should consist of the word “if,” followed by a clause describing the conditions
under which the exception is thrown. Occasionally, arithmetic expressions are
used in place of noun phrases. By convention, the phrase or clause following an
@param, @return, or @throws tag is not terminated by a period. All of these conventions
are illustrated by the following short doc comment:
**
* Returns the element at the specified position in this list.
*
* <p>This method is <i>not</i> guaranteed to run in constant
* time. In some implementations it may run in time proportional
* to the element position.
*
* @param index index of element to return; must be
* non-negative and less than the size of this list
* @return the element at the specified position in this list
* @throws IndexOutOfBoundsException if the index is out of range
* ({@code index < 0 || index >= this.size()})
*
E get(int index);


Notice the use of HTML tags in this doc comment (<p> and <i>). The Javadoc
utility translates doc comments into HTML, and arbitrary HTML elements in doc
comments end up in the resulting HTML document.


Also notice the use of the Javadoc {@code} tag around the code fragment in
the @throws clause. This serves two purposes: it causes the code fragment to be
rendered in code font, and it suppresses processing of HTML markup and nested
Javadoc tags in the code fragment. The latter property is what allows us to use the
less-than sign (<) in the code fragment even though it’s an HTML metacharacter.


To include a multiline code example in a doc comment, use a Javadoc {@code} tag wrapped
inside an HTML <pre> tag. In other words, precede the multiline code example
with the characters <pre>{@code and follow it with the characters }</pre>.


Don’t forget that you must take special action to generate documentation containing
HTML metacharacters, such as the less-than sign (<), the greater-than sign
(>), and the ampersand (&). The best way to get these characters into documentation
is to surround them with the {@literal} tag, which suppress processing of
HTML markup and nested Javadoc tags. It is like the {@code} tag, except that it
doesn’t render the text in code font. For example, this Javadoc fragment:

* The triangle inequality is {@literal |x + y| < |x| + |y|}.

produces the documentation: “The triangle inequality is |x + y| < |x| + |y|.” The
{@literal} tag could have been placed around just the less-than sign rather than
the entire inequality with the same resulting documentation, but the doc comment
would have been less readable in the source code. This illustrates the general
principle that doc comments should be readable in both the source code and in the
generated documentation. If you can’t achieve both, generated documentation
readability trumps source code readability.


The first “sentence” of each doc comment (as defined below) becomes the
summary description of the element to which the comment pertains. For example,
the summary description in the doc comment on page 204 is “Returns the element
at the specified position in this list.” The summary description must stand on its
own to describe the functionality of the element it summarizes. To avoid confusion,
no two members or constructors in a class or interface should have the
same summary description. Pay particular attention to overloadings, for which it
is often natural to use the same first sentence in a prose description (but unacceptable
in doc comments).


Be careful if the intended summary description contains a period, because the
period can prematurely terminate the description. For example, a doc comment
that begins with the phrase “A college degree, such as B.S., M.S. or Ph.D.”
will result in the summary description “A college degree, such as B.S., M.S.” The
problem is that the summary description ends at the first period that is followed by
a space, tab, or line terminator (or at the first block tag) [Javadoc-ref]. In this case,
the second period in the abbreviation “M.S.” is followed by a space. The best solution
is to surround the offending period and any associated text with a {@literal}
tag, so the period is no longer followed by a space in the source code:
**
* A college degree, such as B.S., {@literal M.S.} or Ph.D.
* College is a fountain of knowledge where many go to drink.
*
public class Degree { ... }


It is somewhat misleading to say that the summary description is the first sentence
in a doc comment. Convention dictates that it should seldom be a complete
sentence. For methods and constructors, the summary description should be a full
verb phrase (including any object) describing the action performed by the method.

For example,
• ArrayList(int initialCapacity)—Constructs an empty list with the specified
initial capacity.
• Collection.size()—Returns the number of elements in this collection.

For classes, interfaces, and fields, the summary description should be a noun
phrase describing the thing represented by an instance of the class or interface or
by the field itself. For example,

• TimerTask—A task that can be scheduled for one-time or repeated execution
by a Timer.
• Math.PI—The double value that is closer than any other to pi, the ratio of the
circumference of a circle to its diameter.




When documenting a generic type
or method, be sure to document all type parameters:
**
* An object that maps keys to values. A map cannot contain
* duplicate keys; each key can map to at most one value.
*
* (Remainder omitted)
*
* @param <K> the type of keys maintained by this map
* @param <V> the type of mapped values
*
public interface Map<K, V> {
    ... // Remainder omitted
}



When documenting an enum type, be sure to document the constants as
well as the type and any public methods. Note that you can put an entire doc comment
on one line if it’s short:
**
* An instrument section of a symphony orchestra.
*
public enum OrchestraSection {
    ** Woodwinds, such as flute, clarinet, and oboe. *
    WOODWIND,
    ** Brass instruments, such as french horn and trumpet. *
    BRASS,
    /** Percussion instruments, such as timpani and cymbals *
    PERCUSSION,
    /** Stringed instruments, such as violin and cello. *
    STRING;
}


            When documenting an annotation type, be sure to document any members
            as well as the type itself. Document members with noun phrases, as if they
            were fields. For the summary description of the type, use a verb phrase that says
            what it means when a program element has an annotation of this type:
**
 * Indicates that the annotated method is a test method that
 * must throw the designated exception to succeed.
 *
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    /**
     * The exception that the annotated test method must throw
     * in order to pass. (The test is permitted to throw any
     * subtype of the type described by this class object.)
     *
    Class<? extends Exception> value();
}


As of release 1.5, package-level doc comments should be placed in a file
called package-info.java instead of package.html. In addition to packagelevel
doc comments, package-info.java can (but is not required to) contain a
package declaration and package annotations.


You
can also inherit parts of doc comments from supertypes using the {@inheritDoc}
tag. This means, among other things, that classes can reuse doc comments from
interfaces they implement, rather than copying these comments.


To summarize, documentation comments are the best, most effective way to
document your API. Their use should be considered mandatory for all exported
API elements. Adopt a consistent style that adheres to standard conventions.
Remember that arbitrary HTML is permissible within documentation comments
and that HTML metacharacters must be escaped.














 */
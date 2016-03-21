/**
 * Created by Adrian on 21.03.2016.
 */
public class _17_Inheritance_and_its_design {
}

/*

Item 16 alerted you to the dangers of subclassing a “foreign” class that was not
designed and documented for inheritance. So what does it mean for a class to be
designed and documented for inheritance?
First, the class must document precisely the effects of overriding any method.
In other words, the class must document its self-use of overridable methods.
For each public or protected method or constructor, the documentation must
indicate which overridable methods the method or constructor invokes, in what
sequence, and how the results of each invocation affect subsequent processing.
(By overridable, we mean nonfinal and either public or protected.) More
generally, a class must document any circumstances under which it might invoke
an overridable method.


By convention, a method that invokes overridable methods contains a description
of these invocations at the end of its documentation comment. The description
begins with the phrase “This implementation.”

Proper documentation leaves no doubts to programmer extending a class, whether or not
can he perform some action.

But doesn’t this violate the dictum that good API documentation should
describe what a given method does and not how it does it? Yes, it does! This is an
unfortunate consequence of the fact that inheritance violates encapsulation. To
document a class so that it can be safely subclassed, you must describe implementation
details that should otherwise be left unspecified.

To allow programmers to write efficient subclasses without undue pain, a
class may have to provide hooks into its internal workings in the form of judiciously
chosen protected methods or, in rare instances, protected fields.

So how do you decide what protected members to expose when you design a
class for inheritance? Unfortunately, there is no magic bullet. The best you can do
is to think hard, take your best guess, and then test it by writing subclasses. You
should expose as few protected members as possible, because each one represents
a commitment to an implementation detail. On the other hand, you must not
expose too few, as a missing protected member can render a class practically
unusable for inheritance.


The only way to test a class designed for inheritance is to write subclasses.
If you omit a crucial protected member, trying to write a subclass will make the
omission painfully obvious. Conversely, if several subclasses are written and none
uses a protected member, you should probably make it private. Experience shows
that three subclasses are usually sufficient to test an extendable class. One or more
of these subclasses should be written by someone other than the superclass author.


You must test your class by writing subclasses before you release it.



There are a few more restrictions that a class must obey to allow inheritance.

Constructors must not invoke overridable methods, directly or indirectly.
The superclass constructor runs before the subclass constructor, so the overriding
method in the subclass will get invoked before the subclass constructor has run.


The Cloneable and Serializable interfaces present special difficulties
when designing for inheritance. It is generally not a good idea for a class designed
for inheritance to implement either of these interfaces, as they place a substantial
burden on programmers who extend the class.


If you do decide to implement Cloneable or Serializable in a class
designed for inheritance, you should be aware that because the clone and
readObject methods behave a lot like constructors, a similar restriction applies:
neither clone nor readObject may invoke an overridable method, directly or
indirectly. In the case of the readObject method, the overriding method will run
before the subclass’s state has been deserialized. In the case of the clone method,
the overriding method will run before the subclass’s clone method has a chance to
fix the clone’s state.



Finally, if you decide to implement Serializable in a class designed for
inheritance and the class has a readResolve or writeReplace method, you must
make the readResolve or writeReplace method protected rather than private. If
these methods are private, they will be silently ignored by subclasses.


public class Super {
// Broken - constructor invokes an overridable method
public Super() {
overrideMe();
}
public void overrideMe() {
}
}

public final class Sub extends Super {
private final Date date; // Blank final, set by constructor
Sub() {
date = new Date();
}
// Overriding method invoked by superclass constructor
@Override public void overrideMe() {
System.out.println(date);
}
public static void main(String[] args) {
Sub sub = new Sub();
sub.overrideMe();
}
}                       // null i data zostana wypisane . W konstruktorze nadklasy zostanie wywolana przeslonieta ( juz ) metoda.


By now it should be apparent that designing a class for inheritance places
substantial limitations on the class.


The best solution to this problem is to prohibit subclassing in classes that
are not designed and documented to be safely subclassed.



If a class implements some interface that captures its essence, such as Set, List, or
Map, then you should feel no compunction about prohibiting subclassing.
The wrapper class pattern, described in Item 16, provides a superior alternative to
inheritance for augmenting the functionality.


If a concrete class does not implement a standard interface, then you may
inconvenience some programmers by prohibiting inheritance. If you feel that you
must allow inheritance from such a class, one reasonable approach is to ensure
that the class never invokes any of its overridable methods and to document this
fact. In other words, eliminate the class’s self-use of overridable methods entirely.
In doing so, you’ll create a class that is reasonably safe to subclass. Overriding a
method will never affect the behavior of any other method.


You can eliminate a class’s self-use of overridable methods mechanically,
without changing its behavior. Move the body of each overridable method to a private
“helper method” and have each overridable method invoke its private helper
method. Then replace each self-use of an overridable method with a direct invocation
of the overridable method’s private helper method.



 */
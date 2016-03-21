/**
 * Created by Adrian on 21.03.2016.
 */
public class _13_Classes_and_members {
}

/*

The single most important factor that distinguishes a well-designed module from a
poorly designed one is the degree to which the module hides its internal data and
other implementation details from other modules. A well-designed module hides
all of its implementation details, cleanly separating its API from its implementation.
This concept, known as information hiding or encapsulation,
is one of the fundamental tenets of software design.


Information hiding is important for many reasons, most of which stem from
the fact that it decouples the modules that comprise a system, allowing them to be
developed, tested, optimized, used, understood, and modified in isolation. This
speeds up system development because modules can be developed in parallel. It
eases the burden of maintenance because modules can be understood more
quickly and debugged with little fear of harming other modules. While information
hiding does not, in and of itself, cause good performance, it enables effective
performance tuning: once a system is complete and profiling has determined
which modules are causing performance problems (Item 55), those modules can
be optimized without affecting the correctness of other modules. Information hiding
increases software reuse because modules that aren’t tightly coupled often
prove useful in other contexts besides the ones for which they were developed.
Finally, information hiding decreases the risk in building large systems, because
individual modules may prove successful even if the system does not.


Proper use of these modifiers (private, protected, and public) is essential to information hiding.

The rule of thumb is simple: make each class or member as inaccessible as
possible.

If a top-level class or interface can be made package-private, it should be. By
making it package-private, you make it part of the implementation rather than the
exported API, and you can modify it, replace it, or eliminate it in a subsequent
release without fear of harming existing clients. If you make it public, you are
obligated to support it forever to maintain compatibility.


If a package-private top-level class (or interface) is used by only one class,
consider making the top-level class a private nested class of the sole class that uses
it (Item 22). This reduces its accessibility from all the classes in its package to the
one class that uses it.


After carefully designing your class’s public API, your reflex should be to
make all other members private. Only if another class in the same package really
needs to access a member should you remove the private modifier, making the
member package-private. If you find yourself doing this often, you should reexamine
the design of your system to see if another decomposition might yield
classes that are better decoupled from one another.

For members of public classes, a huge increase in accessibility occurs when
the access level goes from package-private to protected. A protected member is
part of the class’s exported API and must be supported forever. Also, a protected
member of an exported class represents a public commitment to an implementation
detail (Item 17). The need for protected members should be relatively rare.


There is one rule that restricts your ability to reduce the accessibility of methods.
If a method overrides a superclass method, it is not permitted to have a lower
access level in the subclass than it does in the superclass [JLS, 8.4.8.3]. This is
necessary to ensure that an instance of the subclass is usable anywhere that an
instance of the superclass is usable. A special case of this
rule is that if a class implements an interface, all of the class methods that are also
present in the interface must be declared public. This is so because all members of
an interface are implicitly public.


To facilitate testing, you may be tempted to make a class, interface, or member
more accessible. This is fine up to a point. It is acceptable to make a private
member of a public class package-private in order to test it, but it is not acceptable
to raise the accessibility any higher than that.

Instance fields should never be public (Item 14). If an instance field is nonfinal,
or is a final reference to a mutable object, then by making the field public,
you give up the ability to limit the values that can be stored in the field. This
means you also give up the ability to enforce invariants involving the field. Also,
you give up the ability to take any action when the field is modified, so classes
with public mutable fields are not thread-safe. Even if a field is final and refers
to an immutable object, by making the field public you give up the flexibility to
switch to a new internal data representation in which the field does not exist.


The same advice applies to static fields, with the one exception. You can
expose constants via public static final fields, assuming the constants form an integral
part of the abstraction provided by the class. By convention, such fields have
names consisting of capital letters, with words separated by underscores (Item
56). It is critical that these fields contain either primitive values or references to
immutable objects (Item 15). A final field containing a reference to a mutable
object has all the disadvantages of a nonfinal field. While the reference cannot be
modified, the referenced object can be modified—with disastrous results.
Note that a nonzero-length array is always mutable, so it is wrong for a class
to have a public static final array field, or an accessor that returns such a
field. If a class has such a field or accessor, clients will be able to modify the contents
of the array. This is a frequent source of security holes:
// Potential security hole!
public static final Thing[] VALUES = { ... };


Beware of the fact that many IDEs generate accessors that return references to private
array fields, resulting in exactly this problem. There are two ways to fix the
problem. You can make the public array private and add a public immutable list:
private static final Thing[] PRIVATE_VALUES = { ... };
public static final List<Thing> VALUES =
Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
Alternatively, you can make the array private and add a public method that
returns a copy of a private array:
private static final Thing[] PRIVATE_VALUES = { ... };
public static final Thing[] values() {
return PRIVATE_VALUES.clone();
}


To summarize, you should always reduce accessibility as much as possible.
After carefully designing a minimal public API, you should prevent any stray
classes, interfaces, or members from becoming a part of the API. With the exception
of public static final fields, public classes should have no public fields.
Ensure that objects referenced by public static final fields are immutable.



















 */
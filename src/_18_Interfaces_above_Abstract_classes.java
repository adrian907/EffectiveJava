/**
 * Created by Adrian on 21.03.2016.
 */
public class _18_Interfaces_above_Abstract_classes {
}

/*
Existing classes can be easily retrofitted to implement a new interface.

Existing classes cannot, in general, be retrofitted to extend a
new abstract class. If you want to have two classes extend the same abstract class,
you have to place the abstract class high up in the type hierarchy where it
subclasses an ancestor of both classes. Unfortunately, this causes great collateral
damage to the type hierarchy, forcing all descendants of the common ancestor to
extend the new abstract class whether or not it is appropriate for them to do so.


Interfaces are ideal for defining mixins. Loosely speaking, a mixin is a type
that a class can implement in addition to its “primary type” to declare that it provides
some optional behavior. For example, Comparable is a mixin interface that
allows a class to declare that its instances are ordered with respect to other mutually
comparable objects.



Type hierarchies are great for organizing some things, but other things don’t fall
neatly into a rigid hierarchy. For example, suppose we have an interface representing
a singer and another representing a songwriter:
public interface Singer {
AudioClip sing(Song s);
}
public interface Songwriter {
Song compose(boolean hit);
}
In real life, some singers are also songwriters. Because we used interfaces
rather than abstract classes to define these types, it is perfectly permissible for a
single class to implement both Singer and Songwriter. In fact, we can define a
third interface that extends both Singer and Songwriter and adds new methods
that are appropriate to the combination:
public interface SingerSongwriter extends Singer, Songwriter {
AudioClip strum();
void actSensitive();
}


The alternative is a bloated class hierarchy containing a separate class
for every supported combination of attributes. If there are n attributes in the type
system, there are 2n possible combinations that you might have to support. This is
what’s known as a combinatorial explosion.


Interfaces enable safe, powerful functionality enhancements via the wrapper
class idiom, described in Item 16.


You can combine the virtues of interfaces and
abstract classes by providing an abstract skeletal implementation class to go
with each nontrivial interface that you export.The interface still defines the
type, but the skeletal implementation takes all of the work out of implementing it.


By convention, skeletal implementations are called AbstractInterface, where
Interface is the name of the interface they implement. For example, the Collections
Framework provides a skeletal implementation to go along with each main
collection interface: AbstractCollection, AbstractSet, AbstractList, and
AbstractMap.



When properly designed, skeletal implementations can make it very easy for
programmers to provide their own implementations of your interfaces. For example,
here’s a static factory method containing a complete, fully functional List
implementation:
// Concrete implementation built atop skeletal implementation
static List<Integer> intArrayAsList(final int[] a) {
if (a == null)
throw new NullPointerException();
return new AbstractList<Integer>() {
public Integer get(int i) {
return a[i]; // Autoboxing (Item 5)
}
@Override public Integer set(int i, Integer val) {
int oldVal = a[i];
a[i] = val; // Auto-unboxing
return oldVal; // Autoboxing
}
public int size() {
return a.length;
}
};
}
When you consider all that a List implementation does for you, this example
is an impressive demonstration of the power of skeletal implementations. Incidentally,
the example is an Adapter [Gamma95, p. 139] that allows an int array to be
viewed as a list of Integer instances.
Note that a static factory is provided and that the class
is an inaccessible anonymous class (Item 22) hidden inside the static factory.



The beauty of skeletal implementations is that they provide the implementation
assistance of abstract classes without imposing the severe constraints that
abstract classes impose when they serve as type definitions. For most implementors
of an interface, extending the skeletal implementation is the obvious choice,
but it is strictly optional. If a preexisting class cannot be made to extend the skeletal
implementation, the class can always implement the interface manually. Furthermore,
the skeletal implementation can still aid the implementor’s task. The
class implementing the interface can forward invocations of interface methods to a
contained instance of a private inner class that extends the skeletal implementa
tion. This technique, known as simulated multiple inheritance, is closely related to
the wrapper class idiom discussed in Item 16. It provides most of the benefits of
multiple inheritance, while avoiding the pitfalls.



Writing a skeletal implementation is a relatively simple, if somewhat tedious,
process. First you must study the interface and decide which methods are the
primitives in terms of which the others can be implemented. These primitives will
be the abstract methods in your skeletal implementation. Then you must provide
concrete implementations of all the other methods in the interface. For example,
here’s a skeletal implementation of the Map.Entry interface:

A minor variant on the skeletal implementation is the simple implementation,
exemplified by AbstractMap.SimpleEntry. A simple implementation is like a
skeletal implementation in that it implements an interface and is designed for
inheritance, but it differs in that it isn’t abstract: it is the simplest possible working
implementation.

Using abstract classes to define types that permit multiple implementations
has one great advantage over using interfaces: It is far easier to evolve an
abstract class than an interface.You could limit the damage somewhat by adding the new
method to the skeletal implementation at the same time as you add it to the interface,
but this really wouldn’t solve the problem. Any implementation that didn’t
inherit from the skeletal implementation would still be broken.

Once an interface is released and widely implemented, it is almost impossible to change.

The best thing to do when releasing a new interface is to have as many programmers
as possible implement the interface in as many ways as possible before the
interface is frozen. This will allow you to discover flaws while you can still correct
them.


To summarize, an interface is generally the best way to define a type that
permits multiple implementations. An exception to this rule is the case where ease
of evolution is deemed more important than flexibility and power. Under these
circumstances, you should use an abstract class to define the type, but only if you
understand and can accept the limitations. If you export a nontrivial interface, you
should strongly consider providing a skeletal implementation to go with it.
Finally, you should design all of your public interfaces with the utmost care and
test them thoroughly by writing multiple implementations.

Ex : // Skeletal Implementation
public abstract class AbstractMapEntry<K,V>
implements Map.Entry<K,V> {
// Primitive operations
public abstract K getKey();
public abstract V getValue();
// Entries in modifiable maps must override this method
public V setValue(V value) {
throw new UnsupportedOperationException();
}
// Implements the general contract of Map.Entry.equals
@Override public boolean equals(Object o) {
if (o == this)
return true;
if (! (o instanceof Map.Entry))
return false;
Map.Entry<?,?> arg = (Map.Entry) o;
return equals(getKey(), arg.getKey()) &&
equals(getValue(), arg.getValue());
}
private static boolean equals(Object o1, Object o2) {
return o1 == null ? o2 == null : o1.equals(o2);
}
// Implements the general contract of Map.Entry.hashCode
@Override public int hashCode() {
return hashCode(getKey()) ^ hashCode(getValue());
}
private static int hashCode(Object obj) {
return obj == null ? 0 : obj.hashCode();
}
}



 */
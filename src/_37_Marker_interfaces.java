/**
 * Created by Adrian on 26.03.2016.
 */
public class _37_Marker_interfaces {
}
/*

A marker interface is an interface that contains no method declarations, but
merely designates (or “marks”) a class that implements the interface as having
some property. For example, consider the Serializable interface (Chapter 11).
By implementing this interface, a class indicates that its instances can be written
to an ObjectOutputStream (or “serialized”).


You may hear it said that marker annotations (Item 35) make marker interfaces obsolete.
This assertion is incorrect. Marker interfaces have two advantages
over marker annotations.

First and foremost, marker interfaces define a type
that is implemented by instances of the marked class; marker annotations do
not. The existence of this type allows you to catch errors at compile time that you
couldn’t catch until runtime if you used a marker annotation.

Another advantage of marker interfaces over marker annotations is that they
can be targeted more precisely. If an annotation type is declared with target ElementType.
TYPE, it can be applied to any class or interface. Suppose you have a
marker that is applicable only to implementations of a particular interface. If you
define it as a marker interface, you can have it extend the sole interface to which it
is applicable, guaranteeing that all marked types are also subtypes of the sole
interface to which it is applicable.

(Arguably, the Set interface is just such a restricted marker interface. It is
applicable only to Collection subtypes, but it adds no methods beyond those
defined by Collection. It is not generally considered to be a marker interface
because it refines the contracts of several Collection methods, including add,
equals, and hashCode. But it is easy to imagine a marker interface that is applicable
only to subtypes of some particular interface and does not refine the contracts
of any of the interface’s methods as such. Such a marker interface might describe
some invariant of the entire object or indicate that instances are eligible for processing
by a method of some other class .)




The chief advantage of marker annotations over marker interfaces is that it is
possible to add more information to an annotation type after it is already in use, by
adding one or more annotation type elements with defaults [JLS, 9.6]. What starts
life as a mere marker annotation type can evolve into a richer annotation type over
time. Such evolution is not possible with marker interfaces, as it is not generally
possible to add methods to an interface after it has been implemented (Item 18).

Another advantage of marker annotations is that they are part of the larger
annotation facility. Therefore, marker annotations allow for consistency in frameworks
that permit annotation of a variety of program elements.


So when should you use a marker annotation and when should you use a
marker interface? Clearly you must use an annotation if the marker applies to any
program element other than a class or interface, as only classes and interfaces can
be made to implement or extend an interface. If the marker applies only to classes
and interfaces, ask yourself the question, Might I want to write one or more methods
that accept only objects that have this marking? If so, you should use a marker
interface in preference to an annotation. This will make it possible for you to use
the interface as a parameter type for the methods in question, which will result in
the very real benefit of compile-time type checking.

If you answered no to the first question, ask yourself one more: Do I want to
limit the use of this marker to elements of a particular interface, forever? If so, it
makes sense to define the marker as a subinterface of that interface. If you
answered no to both questions, you should probably use a marker annotation.


 */
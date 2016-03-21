/**
 * Created by Adrian on 20.03.2016.
 */
public class _12_Comparable {
}

/*
Unlike the other methods discussed in this chapter, the compareTo method is not
declared in Object. Rather, it is the sole method in the Comparable interface. It is
similar in character to Object’s equals method, except that it permits order comparisons
in addition to simple equality comparisons, and it is generic. By implementing
Comparable, a class indicates that its instances have a natural ordering.


By implementing Comparable, you allow your class to interoperate with all of
the many generic algorithms and collection implementations that depend on this
interface. You gain a tremendous amount of power for a small amount of effort.
Virtually all of the value classes in the Java platform libraries implement Comparable.
If you are writing a value class with an obvious natural ordering, such as
alphabetical order, numerical order, or chronological order, you should strongly
consider implementing the interface:

public interface Comparable<T> {
int compareTo(T t);
}


The general contract of the compareTo method is similar to that of equals:
Returns a negative integer,zero, or a positive integer as this object is less than, equal to, or greater
than the specified object. Throws ClassCastException if the specified object’s
type prevents it from being compared to this object.


RULES :
• The implementor must ensure sgn(x.compareTo(y)) == -sgn(y.compare-
To(x)) for all x and y. (This implies that x.compareTo(y) must throw an exception
if and only if y.compareTo(x) throws an exception.)
• The implementor must also ensure that the relation is transitive: (x.compareTo(
y) > 0 && y.compareTo(z) > 0) implies x.compareTo(z) > 0.
• Finally, the implementor must ensure that x.compareTo(y) == 0 implies that
sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
• It is strongly recommended, but not strictly required, that (x.compareTo(y)
== 0) == (x.equals(y)). Generally speaking, any class that implements the
Comparable interface and violates this condition should clearly indicate this
fact. The recommended language is “Note: This class has a natural ordering
that is inconsistent with equals.”


Across classes, compareTo, unlike equals, doesn’t have to work: it is permitted to throw ClassCastException
if two object references being compared refer to objects of different classes. Usually, that is
exactly what compareTo should do, and what it will do if the class is properly
parameterized. While the contract doesn’t preclude interclass comparisons, there
are, as of release 1.6, no classes in the Java platform libraries that support them.


Class that violates the compareTo contract can break other
classes that depend on comparison. Classes that depend on comparison include
the sorted collections TreeSet and TreeMap, and the utility classes Collections
and Arrays, which contain searching and sorting algorithms.


One consequence of these three provisions is that the equality test imposed by
a compareTo method must obey the same restrictions imposed by the equals contract:
reflexivity, symmetry, and transitivity.Therefore the same caveat applies:
there is no way to extend an instantiable class with a new value component while
preserving the compareTo contract, unless you are willing to forgo the benefits of
object-oriented abstraction (Item 8).If you want to add a value component to a class that
implements Comparable, don’t extend it; write an unrelated class containing an instance of the first class.
Then provide a “view” method that returns this instance. This frees you to implement
whatever compareTo method you like on the second class, while allowing its client
to view an instance of the second class as an instance of the first class when needed.


The ordering imposed by the compareTo method is said to be consistent with equals when it obeys the 4th provision.
If it’s violated, the ordering is said to be inconsistent with equals.

A class whose compareTo method imposes an order
that is inconsistent with equals will still work, but sorted collections containing
elements of the class may not obey the general contract of the appropriate collection
interfaces (Collection, Set, or Map).


For example, consider the BigDecimal class, whose compareTo method is
inconsistent with equals. If you create a HashSet instance and add new
BigDecimal("1.0") and new BigDecimal("1.00"), the set will contain two
elements because the two BigDecimal instances added to the set are unequal
when compared using the equals method. If, however, you perform the same
procedure using a TreeSet instead of a HashSet, the set will contain only one
element because the two BigDecimal instances are equal when compared using
the compareTo method. (See the BigDecimal documentation for details.)


Because the Comparable interface is parameterized,
the compareTo method is statically typed, so you don’t need to type check or
cast its argument.


The field comparisons in a compareTo method are order comparisons rather
than equality comparisons. Compare object reference fields by invoking the
compareTo method recursively. If a field does not implement Comparable, or you
need to use a nonstandard ordering, you can use an explicit Comparator instead.
Either write your own, or use a preexisting one as in this compareTo method for
the CaseInsensitiveString class in Item 8.
public final class CaseInsensitiveString
implements Comparable<CaseInsensitiveString> {
public int compareTo(CaseInsensitiveString cis) {
return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
}
... // Remainder omitted
}


Compare integral primitive fields using the relational operators < and >. For
floating-point fields, use Double.compare or Float.compare in place of the
relational operators, which do not obey the general contract for compareTo when
applied to floating point values. For array fields, apply these guidelines to each
element.


If a class has multiple significant fields, the order in which you compare them
is critical. You must start with the most significant field and work your way down.


public int compareTo(PhoneNumber pn) {
// Compare area codes
int areaCodeDiff = areaCode - pn.areaCode;
if (areaCodeDiff != 0)
return areaCodeDiff;
// Area codes are equal, compare prefixes
int prefixDiff = prefix - pn.prefix;
if (prefixDiff != 0)
return prefixDiff;
// Area codes and prefixes are equal, compare line numbers
return lineNumber - pn.lineNumber;
}


This trick works fine here but should be used with extreme caution. Don’t use
it unless you’re certain the fields in question are non-negative or, more generally,
that the difference between the lowest and highest possible field values is less than
or equal to Integer.MAX_VALUE (231-1).



























 */
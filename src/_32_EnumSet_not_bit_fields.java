/**
 * Created by Adrian on 25.03.2016.
 */
public class _32_EnumSet_not_bit_fields {
}

/*

If the elements of an enumerated type are used primarily in sets, it is traditional to
use the int enum pattern (Item 30), assigning a different power of 2 to each constant:

// Bit field enumeration constants - OBSOLETE!
public class Text {
public static final int STYLE_BOLD = 1 << 0; // 1
public static final int STYLE_ITALIC = 1 << 1; // 2
public static final int STYLE_UNDERLINE = 1 << 2; // 4
public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8
// Parameter is bitwise OR of zero or more STYLE_ constants
public void applyStyles(int styles) { ... }
}


This representation lets you use the bitwise OR operation to combine several constants
into a set, known as a bit field:
text.applyStyles(STYLE_BOLD | STYLE_ITALIC);


Bit fields have all the disadvantages of int enum constants and more. It is even harder to interpret a bit
field than a simple int enum constant when it is printed as a number. The java.util package provides the
EnumSet class to efficiently represent sets of values drawn from a single enum
type. This class implements the Set interface, providing all of the richness, type
safety, and interoperability you get with any other Set implementation. But internally,
each EnumSet is represented as a bit vector. If the underlying enum type has
sixty-four or fewer elements—and most do—the entire EnumSet is represented
with a single long , so its performance is comparable to that of a bit field.


Here is how the previous example looks when modified to use enums instead
of bit fields. It is shorter, clearer, and safer:

// EnumSet - a modern replacement for bit fields
public class Text {
public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }
// Any Set could be passed in, but EnumSet is clearly best
public void applyStyles(Set<Style> styles) { ... }
}

Here is client code that passes an EnumSet instance to the applyStyles method.
EnumSet provides a rich set of static factories for easy set creation, one of which is
illustrated in this code:

text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));

Note that the applyStyles method takes a Set<Style> rather than an Enum-
Set<Style>, it is good practice to accept the interface type rather than the implementation
type. This allows for the possibility of an unusual client to pass in some other
Set implementation .


In summary, just because an enumerated type will be used in sets, there is
no reason to represent it with bit fields. The EnumSet class combines the conciseness
and performance of bit fields with all the many advantages of enum types
described in Item 30. The one real disadvantage of EnumSet is that it is not, as of
release 1.6, possible to create an immutable EnumSet.
























 */
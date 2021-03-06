/**
 * Created by Adrian on 21.03.2016.
 */
public class _14_Accessor_methods {
}
/*

Occasionally, you may be tempted to write degenerate classes that serve no purpose
other than to group instance fields:
// Degenerate classes like this should not be public!
class Point {
public double x;
public double y;
}

Hard-line object-oriented programmers
feel that such classes are anathema and should always be replaced by classes with
private fields and public accessor methods (getters) and, for mutable classes,
mutators (setters):
// Encapsulation of data by accessor methods and mutators
class Point {
private double x;
private double y;
public Point(double x, double y) {
this.x = x;
this.y = y;
}
public double getX() { return x; }
public double getY() { return y; }
public void setX(double x) { this.x = x; }
public void setY(double y) { this.y = y; }
}

Certainly, the hard-liners are correct when it comes to public classes: if a class
is accessible outside its package, provide accessor methods, to preserve the
flexibility to change the class’s internal representation. If a public class exposes its
data fields, all hope of changing its representation is lost, as client code can be distributed
far and wide.

However, if a class is package-private or is a private nested class, there is
nothing inherently wrong with exposing its data fields—assuming they do an
adequate job of describing the abstraction provided by the class. This approach
generates less visual clutter than the accessor-method approach, both in the class
definition and in the client code that uses it. While the client code is tied to the
class’s internal representation, this code is confined to the package containing the
class. If a change in representation becomes desirable, you can make the change
without touching any code outside the package. In the case of a private nested
class, the scope of the change is further restricted to the enclosing class.


Several classes in the Java platform libraries violate the advice that public
classes should not expose fields directly.

While it’s never a good idea for a public class to expose fields directly, it is
less harmful if the fields are immutable. You can’t change the representation of
such a class without changing its API, and you can’t take auxiliary actions when a
field is read, but you can enforce invariants.

In summary, public classes should never expose mutable fields. It is less
harmful, though still questionable, for public classes to expose immutable fields.
It is, however, sometimes desirable for package-private or private nested classes to
expose fields, whether mutable or immutable.



















 */
/**
 * Created by Adrian on 27.03.2016.
 */
public class _39_Defensive_copies {
}
/*
You must program defensively, with the assumption that
clients of your class will do their best to destroy its invariants.


While it is impossible for another class to modify an object’s internal state
without some assistance from the object, it is surprisingly easy to provide such
assistance without meaning to do so. For example, consider the following class,
which purports to represent an immutable time period:

// Broken "immutable" time period class
public final class Period {
private final Date start;
private final Date end;
**
* @param start the beginning of the period
* @param end the end of the period; must not precede start
* @throws IllegalArgumentException if start is after end
* @throws NullPointerException if start or end is null
*
public Period(Date start, Date end) {
    if (start.compareTo(end) > 0)
        throw new IllegalArgumentException(
                start + " after " + end);
    this.start = start;
    this.end = end;
}
    public Date start() {
        return start;
    }
public Date end() {
return end;
}
... // Remainder omitted
}


At first glance, this class may appear to be immutable and to enforce the
invariant that the start of a period does not follow its end. It is, however, easy to
violate this invariant by exploiting the fact that Date is mutable:

// Attack the internals of a Period instance
Date start = new Date();
Date end = new Date();
Period p = new Period(start, end);
end.setYear(78); // Modifies internals of p!

To protect the internals of a Period instance from this sort of attack, it is
essential to make a defensive copy of each mutable parameter to the constructor
and to use the copies as components of the Period instance in place of the
originals:

// Repaired constructor - makes defensive copies of parameters
public Period(Date start, Date end) {
this.start = new Date(start.getTime());
this.end = new Date(end.getTime());
if (this.start.compareTo(this.end) > 0)
throw new IllegalArgumentException(start +" after "+ end);
}



******************************************************************************************
With the new constructor in place, the previous attack will have no effect on
the Period instance. Note that defensive copies are made before checking the
validity of the parameters (Item 38), and the validity check is performed on
the copies rather than on the originals. While this may seem unnatural, it is necessary.
It protects the class against changes to the parameters from another thread
during the “window of vulnerability” between the time the parameters are checked
and the time they are copied. (In the computer security community, this is known
as a time-of-check/time-of-use or TOCTOU attack
******************************************************************************************


CLONE
Note also that we did not use Date’s clone method to make the defensive copies.
Because Date is nonfinal, the clone method is not guaranteed to return an
object whose class is java.util.Date: it could return an instance of an untrusted
subclass specifically designed for malicious mischief. Such a subclass could, for
example, record a reference to each instance in a private static list at the time of its
creation and allow the attacker to access this list. This would give the attacker free
reign over all instances. To prevent this sort of attack, do not use the clone
method to make a defensive copy of a parameter whose type is subclassable
by untrusted parties.



// Second attack on the internals of a Period instance
Date start = new Date();
Date end = new Date();
Period p = new Period(start, end);
p.end().setYear(78); // Modifies internals of p!
To defend against the second attack, merely modify the accessors to return
defensive copies of mutable internal fields:
// Repaired accessors - make defensive copies of internal fields
public Date start() {
return new Date(start.getTime());
}
public Date end() {
return new Date(end.getTime());
}


Defensive copying of parameters is not just for immutable classes. Anytime
you write a method or constructor that enters a client-provided object into an
internal data structure, think about whether the client-provided object is potentially
mutable. If it is, think about whether your class could tolerate a change in
the object after it was entered into the data structure. If the answer is no, you must
defensively copy the object and enter the copy into the data structure in place of
the original. For example, if you are considering using a client-provided object
reference as an element in an internal Set instance or as a key in an internal Map
instance, you should be aware that the invariants of the set or map would be
destroyed if the object were modified after it is inserted.



Remember that nonzero-length arrays are always mutable. Therefore, you should always make a defensive copy
of an internal array before returning it to a client. Alternatively, you could return
an immutable view of the array.


Experienced programmers often use the primitive long returned by Date.getTime() as an internal time representation instead of using a
Date reference. They do this primarily because Date is mutable.


Defensive copying can have a performance penalty associated with it and isn’t
always justified. If a class trusts its caller not to modify an internal component,
perhaps because the class and its client are both part of the same package, then it
may be appropriate to dispense with defensive copying. Under these circumstances,
the class documentation must make it clear that the caller must not modify
the affected parameters or return values.








In summary, if a class has mutable components that it gets from or returns to
its clients, the class must defensively copy these components. If the cost of the
copy would be prohibitive and the class trusts its clients not to modify the components
inappropriately, then the defensive copy may be replaced by documentation
outlining the client’s responsibility not to modify the affected components.


















 */
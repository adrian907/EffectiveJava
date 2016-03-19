/**
 * Created by Adrian on 18.03.2016.
 */
public class _5_UnneccessaryObjects {
}

/*
It is often appropriate to reuse a single object instead of creating a new functionally
equivalent object each time it is needed. Reuse can be both faster and more
stylish. An object can always be reused if it is immutable

String s = new String("stringette"); // DON'T DO THIS!

If this usage occurs in a loop or in a frequently
invoked method, millions of String instances can be created needlessly.
The improved version is simply the following:

String s = "stringette";

This version uses a single String instance, rather than creating a new one
each time it is executed.

You can often avoid creating unnecessary objects by using static factory methods
(Item 1) in preference to constructors on immutable classes that provide both.
For example, the static factory method Boolean.valueOf(String) is almost
always preferable to the constructor Boolean(String).


In addition to reusing immutable objects, you can also reuse mutable objects
if you know they won’t be modified.

public class Person {
private final Date birthDate;
// Other fields, methods, and constructor omitted

// DON'T DO THIS!
public boolean isBabyBoomer() {
// Unnecessary allocation of expensive object
Calendar gmtCal =
Calendar.getInstance(TimeZone.getTimeZone("GMT"));
gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
Date boomStart = gmtCal.getTime();
gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
Date boomEnd = gmtCal.getTime();
return birthDate.compareTo(boomStart) >= 0 &&
birthDate.compareTo(boomEnd) < 0;
}
}

Better vrsion  :
class Person {
private final Date birthDate;
// Other fields, methods, and constructor omitted

private static final Date BOOM_START;
    private static final Date BOOM_END;
static {
        Calendar gmtCal =
        Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
        }
public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 &&
        birthDate.compareTo(BOOM_END) < 0;
        }
        }


The improved version of the Person class creates Calendar, TimeZone, and
Date instances only once, when it is initialized, instead of creating them every
time isBabyBoomer is invoked. This results in significant performance gains if the
method is invoked frequently. On my machine, the original version takes 32,000
ms for 10 million invocations, while the improved version takes 130 ms, which is
about 250 times faster.


In the previous examples in this item, it was obvious that the objects in question
could be reused because they were not modified after initialization. There are
other situations where it is less obvious. Consider the case of adapters,
also known as views. An adapter is an object that delegates to a backing
object, providing an alternative interface to the backing object. Because an adapter
has no state beyond that of its backing object, there’s no need to create more than
one instance of a given adapter to a given object.
For example, the keySet method of the Map interface returns a Set view of the
Map object, consisting of all the keys in the map. Naively, it would seem that every
call to keySet would have to create a new Set instance, but every call to keySet
on a given Map object may return the same Set instance. Although the returned
Set instance is typically mutable, all of the returned objects are functionally identical:
when one of the returned objects changes, so do all the others because
they’re all backed by the same Map instance

There’s a new way to create unnecessary objects in release 1.5. It is called
autoboxing.

// Hideously slow program! Can you spot the object creation?
public static void main(String[] args) {
Long sum = 0L;
for (long i = 0; i < Integer.MAX_VALUE; i++) {
sum += i;
}
System.out.println(sum);
}

The variable sum is declared as a Long
instead of a long, which means that the program constructs about 231 unnecessary
Long instances (roughly one for each time the long i is added to the Long sum).
Changing the declaration of sum from Long to long reduces the runtime from 43
seconds to 6.8 seconds on my machine. The lesson is clear: prefer primitives to
boxed primitives, and watch out for unintentional autoboxing.

This item should not be misconstrued to imply that object creation is expensive
and should be avoided. On the contrary, the creation and reclamation of small
objects whose constructors do little explicit work is cheap, especially on modern
JVM implementations. Creating additional objects to enhance the clarity, simplicity,
or power of a program is generally a good thing.
Conversely, avoiding object creation by maintaining your own object pool is a
bad idea unless the objects in the pool are extremely heavyweight. The classic
example of an object that does justify an object pool is a database connection. The
cost of establishing the connection is sufficiently high that it makes sense to reuse
these objects. Also, your database license may limit you to a fixed number of connections.








* */
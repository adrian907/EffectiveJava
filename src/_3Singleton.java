/**
 * Created by Adrian on 17.03.2016.
 */

//Enforce the singleton property with a private constructor or an enum type
public class _3Singleton {
//A singleton is simply a class that is instantiated exactly once



}

/*Making a class a singleton can make it difficult to test its clients,as it’s impossible to substitute a mock implementation
for a singleton unless it implements an interface that serves as its type.

Before release 1.5, there were two ways to implement singletons. Both are
based on keeping the constructor private and exporting a public static member to
provide access to the sole instance. In one approach, the member is a final field:

// Singleton with public final field

public class Elvis {
public static final Elvis INSTANCE = new Elvis();
private Elvis() { ... }
public void leaveTheBuilding() { ... }
}

Nothing that a client does can make it possible to create more than one instance, with one caveat: a privileged client can invoke the private
constructor reflectively with the aid of the AccessibleObject.setAccessible method. If you need to defend against this attack, modify the
constructor to make it throw an exception if it’s asked to create a second instance.

In the second approach to implementing singletons, the public member is a
static factory method:

// Singleton with static factory

public class Elvis {
private static final Elvis INSTANCE = new Elvis();
private Elvis() { ... }
public static Elvis getInstance() { return INSTANCE; }
public void leaveTheBuilding() { ... }
}

All calls to Elvis.getInstance return the same object reference, and no other
Elvis instance will ever be created (with the same caveat mentioned above).

One advantage of the factory-method approach is that it gives you the flexibility
to change your mind about whether the class should be a singleton without
changing its API.
The factory method returns the sole instance but could easily be
modified to return, say, a unique instance for each thread that invokes it.

To make a singleton class that is implemented using either of the previous
approaches serializable , it is not sufficient merely to add implements
Serializable to its declaration. To maintain the singleton guarantee, you
have to declare all instance fields transient and provide a readResolve method.
Otherwise, each time a serialized instance is deserialized, a new
instance will be created, leading, in the case of our example, to spurious Elvis
sightings. To prevent this, add this readResolve method to the Elvis class:

// readResolve method to preserve singleton property
private Object readResolve() {
// Return the one true Elvis and let the garbage collector
// take care of the Elvis impersonator.
return INSTANCE;
}


As of release 1.5, there is a third approach to implementing singletons. Simply
make an enum type with one element:

// Enum singleton - the preferred approach
public enum Elvis {
INSTANCE;
public void leaveTheBuilding() { ... }
}

This approach is functionally equivalent to the public field approach, except that it
is more concise, provides the serialization machinery for free, and provides an
ironclad guarantee against multiple instantiation, even in the face of sophisticated
serialization or reflection attacks. While this approach has yet to be widely
adopted, a single-element enum type is the best way to implement a singleton.























*/
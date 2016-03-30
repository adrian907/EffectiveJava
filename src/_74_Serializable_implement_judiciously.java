/**
 * Created by Adrian on 30.03.2016.
 */
public class _74_Serializable_implement_judiciously {
}
/*

Encoding an object as a byte stream is known as serializing
the object; the reverse process is known as deserializing it.

A major cost of implementing Serializable is that it decreases the flexibility
to change a class’s implementation once it has been released.

If you accept the default serialized form, the class’s private and package-private instance
fields become part of its exported API, and the practice of minimizing access to
fields (Item 13) loses its effectiveness as a tool for information hiding.

If you accept the default serialized form and later change the class’s internal
representation, an incompatible change in the serialized form might result. Clients
attempting to serialize an instance using an old version of the class and deserialize
it using the new version will experience program failures. It is possible to change
the internal representation while maintaining the original serialized form (using
ObjectOutputStream.putFields and ObjectInputStream.readFields), but it
can be difficult and leaves visible warts in the source code.


























 */
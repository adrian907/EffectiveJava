/**
 * Created by Adrian on 30.03.2016.
 */
public class _67_Avoid_excessive_synchronization {
}
/*

Depending on the situation, excessive synchronization can
cause reduced performance, deadlock, or even nondeterministic behavior.

Inside a synchronized region, do not invoke a method that is designed to be overridden
or one provided by a client in the form of a function object (Item 21).From the perspective of the
class with the synchronized region, such methods are alien.




























 */
/**
 * Created by Adrian on 30.03.2016.
 */
public class _66_Synchronize_access_to_shared_mutable_data {
}
/*

The synchronized keyword ensures that only a single thread can execute a method
or block at one time.

Proper use of synchronization guarantees that no method will ever observe the object in an inconsistent state.

Without synchronization, one thread’s changes might not be visible to other threads. Not only does synchronization
prevent a thread from observing an object in an inconsistent state, but it
ensures that each thread entering a synchronized method or block sees the effects
of all previous modifications that were guarded by the same lock.

The language specification guarantees that reading or writing a variable is
atomic unless the variable is of type long or double.

Synchronization is required for reliable communication
between threads as well as for mutual exclusion.

You may hear it said that to improve performance, you should avoid synchronization
when reading or writing atomic data. This advice is dangerously wrong.
While the language specification guarantees that a thread will not see an arbitrary
value when reading a field, it does not guarantee that a value written by one thread
will be visible to another.

Do not use Thread.stop. It's deprecated !

A recommended way to stop one thread from another is to have the first thread poll a
boolean field that is initially false but can be set to true by the second thread to
indicate that the first thread is to stop itself.


Because reading and writing a boolean field is atomic,
some programmers dispense with synchronization when accessing the field:
// Broken! - How long would you expect this program to run?
public class StopThread {
private static boolean stopRequested;
public static void main(String[] args)
throws InterruptedException {
Thread backgroundThread = new Thread(new Runnable() {
public void run() {
int i = 0;
while (!stopRequested)
i++;
}
});
backgroundThread.start();
TimeUnit.SECONDS.sleep(1);
stopRequested = true;                           *****//*****
}
}

Program will work infinitely long, all you have t do is to put stopRequested variable into a synchronized method or a block :

private static synchronized void requestStop() {
stopRequested = true;
}

 and invoke it in place of *****//*****

volatile - allows a field to be atomic.

Increment operator (++) is not atomic. First it reads the value, then it writes
 back a new value, equal to the old value plus one. If a second thread reads the field
 between the time a thread reads the old value and writes back a new one, the second
 thread will see the same value as the first.


Use the class AtomicLong instead of long in concurrency programs, which is part of java.util.concurrent.atomic

The best way to avoid the problems discussed in this item is not to share mutable
 data. Either share immutable data (Item 15), or don’t share at all.

 Confine mutable data to a single thread

 It is acceptable for one thread to modify a data object for a while and then to
 share it with other threads, synchronizing only the act of sharing the object reference.
 Other threads can then read the object without further synchronization, so
 long as it isn’t modified again. Such objects are said to be effectively immutable
 [Goetz06 3.5.4]. Transferring such an object reference from one thread to others is
 called safe publication [Goetz06 3.5.3]. There are many ways to safely publish an
 object reference: you can store it in a static field as part of class initialization; you
 can store it in a volatile field, a final field, or a field that is accessed with normal
 locking; or you can put it into a concurrent collection

 In summary, when multiple threads share mutable data, each thread that
 reads or writes the data must perform synchronization. Without synchronization,
 there is no guarantee that one thread’s changes will be visible to another. The
 penalties for failing to synchronize shared mutable data are liveness and safety
 failures. These failures are among the most difficult to debug. They can be intermittent
 and timing-dependent, and program behavior can vary radically from one
 VM to another. If you need only inter-thread communication, and not mutual
 exclusion, the volatile modifier is an acceptable form of synchronization, but it
 can be tricky to use correctly.


*/
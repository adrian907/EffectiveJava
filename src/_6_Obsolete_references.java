/**
 * Created by Adrian on 18.03.2016.
 */
public class _6_Obsolete_references {
}

/*
Automatic reclamation.

// Can you spot the "memory leak"?
public class Stack {
private Object[] elements;
private int size = 0;
private static final int DEFAULT_INITIAL_CAPACITY = 16;
public Stack() {
elements = new Object[DEFAULT_INITIAL_CAPACITY];
}
public void push(Object e) {
ensureCapacity();
elements[size++] = e;
}
public Object pop() {
if (size == 0)
throw new EmptyStackException();
return elements[--size];
}

private void ensureCapacity() {
    if (elements.length == size)
        elements = Arrays.copyOf(elements, 2 * size + 1);
}
}

Loosely speaking, the program has a “memory leak,” which can silently manifest itself as reduced performance due to
increased garbage collector activity or increased memory footprint. In extreme
cases, such memory leaks can cause disk paging and even program failure with an
OutOfMemoryError, but such failures are relatively rare.

So where is the memory leak? If a stack grows and then shrinks, the objects
that were popped off the stack will not be garbage collected, even if the program
using the stack has no more references to them. This is because the stack maintains
obsolete references to these objects. An obsolete reference is simply a reference
that will never be dereferenced again. In this case, any references outside of
the “active portion” of the element array are obsolete. The active portion consists
of the elements whose index is less than size.

If an object reference is unintentionally retained, not only is that object excluded from garbage collection,
but so too are any objects referenced by that object, and so on.

The fix for this sort of problem is simple: null out references once they
become obsolete.

public Object pop() {
if (size == 0)
throw new EmptyStackException();
Object result = elements[--size];
elements[size] = null; // Eliminate obsolete reference
return result;
}

An added benefit of nulling out obsolete references is that, if they are subsequently
dereferenced by mistake, the program will immediately fail with a
NullPointerException, rather than quietly doing the wrong thing.

When programmers are first stung by this problem, they may overcompensate
by nulling out every object reference as soon as the program is finished using it.
This is neither necessary nor desirable, as it clutters up the program unnecessarily.
Nulling out object references should be the exception rather than the norm.

The best way to eliminate an obsolete reference is to let the variable that contained
the reference fall out of scope. This occurs naturally if you define each variable in
the narrowest possible scope.

Generally speaking, whenever a class manages its own memory, the programmer
should be alert for memory leaks.Whenever an element is freed, any
object references contained in the element should be nulled out.

2)Another common source of memory leaks is caches. ( cache - bufor)

If you’re lucky enough to implement a cache for which an entry is relevant exactly
so long as there are references to its key outside of the cache, represent the cache
as a WeakHashMap; entries will be removed automatically after they become obsolete.
Remember that WeakHashMap is useful only if the desired lifetime of cache
entries is determined by external references to the key, not the value.

More commonly, the useful lifetime of a cache entry is less well defined, with
entries becoming less valuable over time. Under these circumstances, the cache
should occasionally be cleansed of entries that have fallen into disuse. This can be
done by a background thread (perhaps a Timer or ScheduledThreadPoolExecutor)
or as a side effect of adding new entries to the cache. The LinkedHashMap
class facilitates the latter approach with its removeEldestEntry method. For
more sophisticated caches, you may need to use java.lang.ref directly.

3) A third common source of memory leaks is listeners and other callbacks.

If you implement an API where clients register callbacks but don’t deregister them
explicitly, they will accumulate unless you take some action. The best way to
ensure that callbacks are garbage collected promptly is to store only weak references
to them, for instance, by storing them only as keys in a WeakHashMap.

















* */
/**
 * Created by Adrian on 24.03.2016.
 */
public class _28_Bounded_Wildcards {
}
/*
List<String> is not a subtype of List<Object>.


Suppose we want to add a method that takes a sequence of elements and
pushes them all onto the stack. Here’s a first attempt:

// pushAll method without wildcard type - deficient!
public void pushAll(Iterable<E> src) {
for (E e : src)
push(e);
}

This method compiles cleanly, but it isn’t entirely satisfactory. If the element type
of the Iterable src exactly matches that of the stack, it works fine. But suppose
you have a Stack<Number> and you invoke push(intVal), where intVal is of
type Integer. This works, because Integer is a subtype of Number. So logically,
it seems that this should work, too:

Stack<Number> numberStack = new Stack<Number>();
Iterable<Integer> integers = ... ;
numberStack.pushAll(integers);

If you try it, however, you’ll get this error message because, as noted above,
parameterized types are invariant:

StackTest.java:7: pushAll(Iterable<Number>) in Stack<Number>
cannot be applied to (Iterable<Integer>)
numberStack.pushAll(integers);
^

The language provides a special kind of parameterized
type call a bounded wildcard type to deal with situations like this. The type of
the input parameter to pushAll should not be “Iterable of E” but “Iterable of
some subtype of E,” and there is a wildcard type that means precisely that: Iterable<?
extends E>.



Now suppose you want to write a popAll method to go with pushAll. The
popAll method pops each element off the stack and adds the elements to the given
collection. Here’s how a first attempt at writing the popAll method might look:

// popAll method without wildcard type - deficient!
public void popAll(Collection<E> dst) {
while (!isEmpty())
dst.add(pop());
}

Again, this compiles cleanly and works fine if the element type of the destination
collection exactly matches that of the stack. But again, it doesn’t seem entirely
satisfactory. Suppose you have a Stack<Number> and variable of type Object. If
you pop an element from the stack and store it in the variable, it compiles and runs
without error. So shouldn’t you be able to do this, too?

Stack<Number> numberStack = new Stack<Number>();
Collection<Object> objects = ... ;
numberStack.popAll(objects);

If you try to compile this client code against the version of popAll above, you’ll
get an error very similar to the one that we got with our first version of pushAll:
Collection<Object> is not a subtype of Collection<Number>. Once again,
wildcard types provide a way out. The type of the input parameter to popAll
should not be “collection of E” but “collection of some supertype of E” (where
supertype is defined such that E is a supertype of itself [JLS, 4.10]). Again, there is
a wildcard type that means precisely that: Collection<? super E>. Let’s modify
popAll to use it:

// Wildcard type for parameter that serves as an E consumer
public void popAll(Collection<? super E> dst) {
while (!isEmpty())
dst.add(pop());
}


For maximum flexibility, use wildcard types on input parameters that represent producers or consumers.


If an input parameter is both a producer and a consumer, then wildcard types will do you no good: you
need an exact type match, which is what you get without any wildcards.


PECS stands for producer-extends, consumer-super.


Now let’s look at the union method from Item 27. Here is the declaration:

public static <E> Set<E> union(Set<E> s1, Set<E> s2)

Both parameters, s1 and s2, are E producers, so the PECS mnemonic tells us that
the declaration should be:

public static <E> Set<E> union(Set<? extends E> s1,Set<? extends E> s2)

Note that the return type is still Set<E>. Do not use wildcard types as return types.


Properly used, wildcard types are nearly invisible to users of a class. They
cause methods to accept the parameters they should accept and reject those they
should reject.

If the user of a class has to think about wildcard types, there is
probably something wrong with the class’s API.



Unfortunately, the type inference rules are quite complex. They take up sixteen
pages in the language specification , and they don’t always
do what you want them to.
Looking at the revised declaration for union, you might think that you could do this:

Set<Integer> integers = ... ;
Set<Double> doubles = ... ;
Set<Number> numbers = union(integers, doubles);

If you try it you’ll get this error message:

Union.java:14: incompatible types
found : Set<Number & Comparable<? extends Number &
Comparable<?>>>
required: Set<Number>
Set<Number> numbers = union(integers, doubles);
^

Luckily there is a way to deal with this sort of error. If the compiler doesn’t
infer the type that you wish it had, you can tell it what type to use with an explicit
type parameter. This is not something that you have to do very often, which is a
good thing, as explicit type parameters aren’t very pretty. With the addition of this
explicit type parameter, the program compiles cleanly:

Set<Number> numbers = Union.<Number>union(integers, doubles);

Comparables are always consumers, so you should always use Comparable<? super T> in preference
to Comparable<T>.

The same is true of comparators, so you should always
use Comparator<? super T> in preference to Comparator<T>.


There is one more wildcard-related topic that bears discussing. There is a
duality between type parameters and wildcards, and many methods can be
declared using one or the other. For example, here are two possible declarations
for a static method to swap two indexed items in a list. The first uses an
unbounded type parameter (Item 27) and the second an unbounded wildcard:

// Two possible declarations for the swap method
public static <E> void swap(List<E> list, int i, int j);
public static void swap(List<?> list, int i, int j);


Which of these two declarations is preferable, and why? In a public API, the
second is better because it’s simpler. You pass in a list—any list—and the method
swaps the indexed elements. There is no type parameter to worry about. As a rule,
if a type parameter appears only once in a method declaration, replace it with
a wildcard. If it’s an unbounded type parameter, replace it with an unbounded
wildcard; if it’s a bounded type parameter, replace it with a bounded wildcard.


There’s one problem with the second declaration for swap, which uses a wildcard
in preference to a type parameter: the straightforward implementation won’t
compile:

public static void swap(List<?> list, int i, int j) {
list.set(i, list.set(j, list.get(i)));
}

Trying to compile it produces this less-than-helpful error message:

Swap.java:5: set(int,capture#282 of ?) in List<capture#282 of ?>
cannot be applied to (int,Object)
list.set(i, list.set(j, list.get(i)));
^

It doesn’t seem right that we can’t put an element back into the list that we just
took it out of. The problem is that the type of list is List<?>, and you can’t put
any value except null into a List<?>. Fortunately, there is a way to implement
this method without resorting to an unsafe cast or a raw type. The idea is to write a
private helper method to capture the wildcard type. The helper method must be a
generic method in order to capture the type. Here’s how it looks:

public static void swap(List<?> list, int i, int j) {
swapHelper(list, i, j);
}

// Private helper method for wildcard capture
private static <E> void swapHelper(List<E> list, int i, int j) {
list.set(i, list.set(j, list.get(i)));
}

The swapHelper method knows that list is a List<E>. Therefore, it knows
that any value it gets out of this list is of type E, and that it’s safe to put any value
of type E into the list. This slightly convoluted implementation of swap compiles
cleanly. It allows us to export the nice wildcard-based declaration of swap, while
taking advantage of the more complex generic method internally. Clients of the
swap method don’t have to confront the more complex swapHelper declaration,
but they do benefit from it.



In summary, using wildcard types in your APIs, while tricky, makes the APIs
far more flexible. If you write a library that will be widely used, the proper use of
wildcard types should be considered mandatory. Remember the basic rule: producer-
extends, consumer-super (PECS). And remember that all comparables
and comparators are consumers.


















 */
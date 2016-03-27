/**
 * Created by Adrian on 27.03.2016.
 */
public class _41_Overloading_judiciously {
}

/*

// Broken! - What does this program print?
public class CollectionClassifier {
public static String classify(Set<?> s) {
return "Set";
}
public static String classify(List<?> lst) {
return "List";
}
public static String classify(Collection<?> c) {
return "Unknown Collection";
}
public static void main(String[] args) {
Collection<?>[] collections = {
new HashSet<String>(),
new ArrayList<BigInteger>(),
new HashMap<String, String>().values()
};
for (Collection<?> c : collections)         //Here's the bug! With overriding it would be fine.
System.out.println(classify(c));
}
}


It prints Unknown Collection three times. Why does this happen? Because the classify method is overloaded,
and the choice of which overloading to invoke is made at compile time. For all three iterations of
the loop, the compile-time type of the parameter is the same: Collection<?>. The
runtime type is different in each iteration, but this does not affect the choice of
overloading. Because the compile-time type of the parameter is Collection<?>,
the only applicable overloading is the third one, classify(Collection<?>), and
this overloading is invoked in each iteration of the loop.

The behavior of this program is counterintuitive because selection among
overloaded methods is static, while selection among overridden methods is dynamic.
The correct version of an overridden method is chosen at runtime,


The compile-time type of an object has no effect on which method is executed when an overridden method is invoked;

It is bad practice to write code whose behavior is likely to confuse
programmers.


A safe, conservative policy is never to export two overloadings with
the same number of parameters. If a method uses varargs, a conservative policy
is not to overload it at all .


Exporting multiple overloadings with the same number of parameters is
unlikely to confuse programmers if it is always clear which overloading will apply
to any given set of actual parameters. This is the case when at least one corresponding
formal parameter in each pair of overloadings has a “radically different”
type in the two overloadings. Two types are radically different if it is clearly
impossible to cast an instance of either type to the other.


Prior to release 1.5, all primitive types were radically different from all reference
types, but this is no longer true in the presence of autoboxing, and it has
caused real trouble. Consider the following program:
public class SetList {
public static void main(String[] args) {
Set<Integer> set = new TreeSet<Integer>();
List<Integer> list = new ArrayList<Integer>();
for (int i = -3; i < 3; i++) {
set.add(i);
list.add(i);
}
for (int i = 0; i < 3; i++) {
set.remove(i);
list.remove(i);
}
System.out.println(set + " " + list);
}
}


Prints [-3, -2, -1] [-2, 0, 2].
Here’s what’s happening: The call to set.remove(i) selects the overloading
remove(E), where E is the element type of the set (Integer), and autoboxes i
from int to Integer. This is the behavior you’d expect, so the program ends up
removing the positive values from the set. The call to list.remove(i), on the
other hand, selects the overloading remove(int i), which removes the element at
the specified position from a list. If you start with the list [-3, -2, -1, 0, 1, 2]
and remove the zeroth element, then the first, and then the second, you’re left with
[-2, 0, 2], and the mystery is solved.


Array types and classes other than Object are radically different. Also, array
types and interfaces other than Serializable and Cloneable are radically different.

Two distinct classes are said to be unrelated if neither class is a descendant of
the other. For example, String and Throwable are unrelated. It is
impossible for any object to be an instance of two unrelated classes, so unrelated
classes are radically different.


While the Java platform libraries largely adhere to the spirit of the advice in
this item, there are a number of classes that violate it. For example, the String
class exports two overloaded static factory methods, valueOf(char[]) and valueOf(
Object), that do completely different things when passed the same object
reference. There is no real justification for this, and it should be regarded as an
anomaly with the potential for real confusion.
To summarize, just because you can overload methods doesn’t mean you
should. You should generally refrain from overloading methods with multiple signatures
that have the same number of parameters. In some cases, especially where
constructors are involved, it may be impossible to follow this advice. In that case,
you should at least avoid situations where the same set of parameters can be
passed to different overloadings by the addition of casts. If such a situation cannot
be avoided, for example, because you are retrofitting an existing class to implement
a new interface, you should ensure that all overloadings behave identically
when passed the same parameters. If you fail to do this, programmers will be hard
pressed to make effective use of the overloaded method or constructor, and they
won’t understand why it doesn’t work.








 */
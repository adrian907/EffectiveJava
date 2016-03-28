/**
 * Created by Adrian on 28.03.2016.
 */
public class _49_Primitives_not_boxed_primitives{
public static void main( String[]  args){
    Integer a = 313;
    Integer b =313;
    System.out.println(a==b);
}
}

/*

Java has a two-part type system, consisting of primitives, such as int, double, and
boolean, and reference types, such as String and List. Every primitive type has
a corresponding reference type, called a boxed primitive.


There are three major differences between primitives and boxed primitives.
First, primitives have only their values, whereas boxed primitives have identities
distinct from their values. In other words, two boxed primitive instances can have
the same value and different identities. Second, primitive types have only fully
functional values, whereas each boxed primitive type has one nonfunctional value,
which is null, in addition to all of the functional values of its corresponding primitive
type. Last, primitives are generally more time- and space-efficient than boxed
primitives. All three of these differences can get you into real trouble if you aren’t
careful.



EXAMPLE:

public class Unbelievable {
static Integer i;
public static void main(String[] args) {
if (i == 42)
System.out.println("Unbelievable");
}
}
No, it doesn’t print Unbelievable—but what it does is almost as strange. It
throws a NullPointerException when evaluating the expression (i == 42). The
problem is that i is an Integer, not an int, and like all object reference fields, its
initial value is null.



EXAMPLE :
// Hideously slow program! Can you spot the object creation?
public static void main(String[] args) {
Long sum = 0L;
for (long i = 0; i < Integer.MAX_VALUE; i++) {
sum += i;
}
System.out.println(sum);
}
This program is much slower than it should be because it accidentally declares a
local variable (sum) to be of the boxed primitive type Long instead of the primitive
type long. The program compiles without error or warning, and the variable is
repeatedly boxed and unboxed, causing the observed performance degradation.


When your program does mixed-type computations involving
boxed and unboxed primitives, it does unboxing, and when your program does
unboxing, it can throw a NullPointerException. Finally, when your program
boxes primitive values, it can result in costly and unnecessary object creations.
Primitive types are simpler and faster.













 */
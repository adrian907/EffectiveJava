/**
 * Created by Adrian on 22.03.2016.
 */
public class _24_Unchecked_warnings {
}
/*
When you program with generics, you will see many compiler warnings:
unchecked cast warnings, unchecked method invocation warnings, unchecked
generic array creation warnings, and unchecked conversion warnings.



Many unchecked warnings are easy to eliminate. For example, suppose you
accidentally write this declaration:
Set<Lark> exaltation = new HashSet();
The compiler will gently remind you what you did wrong:
Venery.java:4: warning: [unchecked] unchecked conversion
found : HashSet, required: Set<Lark>
Set<Lark> exaltation = new HashSet();
^
You can then make the indicated correction, causing the warning to disappear:
Set<Lark> exaltation = new HashSet<Lark>();


Eliminate every unchecked warning that you can.If you
eliminate all warnings, you are assured that your code is typesafe.
It means that you won’t get a ClassCastException at runtime, and it
increases your confidence that your program is behaving as you intended.


If you can’t eliminate a warning, and you can prove that the code that
provoked the warning is typesafe, then (and only then) suppress the warning
with an @SuppressWarnings("unchecked") annotation.


If, however, you ignore unchecked warnings that you know to be safe (instead of suppressing them),
you won’t notice when a new warning crops up that represents a real problem. The
new warning will get lost amidst all the false alarms that you didn’t silence.


Always use the Suppress- Warnings annotation on the smallest scope possible. Typically
this will be a variable declaration or a very short method or constructor. Never use
Suppress-Warnings on an entire class. Doing so could mask critical warnings.

******************************************************************************
* Ex :
If you find yourself using the SuppressWarnings annotation on a method or
constructor that’s more than one line long, you may be able to move it onto a local
variable declaration.

public <T> T[] toArray(T[] a) {
if (a.length < size)
return (T[]) Arrays.copyOf(elements, size, a.getClass());           //wrong.
System.arraycopy(elements, 0, a, 0, size);
if (a.length > size)
a[size] = null;
return a;
}


It is illegal to put a SuppressWarnings annotation on the return statement,
because it isn’t a declaration [JLS, 9.7]. You might be tempted to put the annotation
on the entire method, but don’t. Instead, declare a local variable to hold the
return value and annotate its declaration, like so:


// Adding local variable to reduce scope of @SuppressWarnings
public <T> T[] toArray(T[] a) {
if (a.length < size) {
// This cast is correct because the array we're creating
// is of the same type as the one passed in, which is T[].
@SuppressWarnings("unchecked") T[] result =
(T[]) Arrays.copyOf(elements, size, a.getClass());
return result;
}
System.arraycopy(elements, 0, a, 0, size);
if (a.length > size)
a[size] = null;
return a;
}


Every time you use an @SuppressWarnings("unchecked") annotation,
add a comment saying why it’s safe to do so.


In summary, unchecked warnings are important. Don’t ignore them. Every
unchecked warning represents the potential for a ClassCastException at runtime.
Do your best to eliminate these warnings. If you can’t eliminate an
unchecked warning and you can prove that the code that provoked it is typesafe,
suppress the warning with an @SuppressWarnings("unchecked") annotation in
the narrowest possible scope. Record the rationale for your decision to suppress
the warning in a comment.












 */
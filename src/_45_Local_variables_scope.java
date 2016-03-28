/**
 * Created by Adrian on 28.03.2016.
 */
public class _45_Local_variables_scope {
}
/*

By minimizing the scope of local variables, you increase the readability
and maintainability of your code and reduce the likelihood of error.


Java lets you declare variables anywhere a statement is legal.

The most powerful technique for minimizing the scope of a local variable
is to declare it where it is first used.If a variable is declared before it is used, it’s
just clutter—one more thing to distract the reader who is trying to figure out what
the program does. By the time the variable is used, the reader might not remember
the variable’s type or initial value.


If a variable is initialized by a method that throws a checked
exception, it must be initialized inside a try block. If the value must be used
outside of the try block, then it must be declared before the try block, where it
cannot yet be “sensibly initialized.”



Prefer for loops to while loops, assuming the
contents of the loop variable aren’t needed after the loop terminates.


For example, here is the preferred idiom for iterating over a collection (Item 46):

// Preferred idiom for iterating over a collection
for (Element e : c) {
doSomething(e);
}

Before release 1.5, this was the preferred idiom (and it still has valid uses):

// No for-each loop or generics before release 1.5
for (Iterator i = c.iterator(); i.hasNext(); ) {
doSomething((Element) i.next());
}

To see why these for loops are preferable to a while loop, consider the following
code fragment, which contains two while loops and one bug:

Iterator<Element> i = c.iterator();
while (i.hasNext()) {
doSomething(i.next());
}

...

Iterator<Element> i2 = c2.iterator();
while (i.hasNext()) { // BUG!
doSomethingElse(i2.next());
}

The second loop contains a cut-and-paste error: it initializes a new loop variable,
i2, but uses the old one, i, which is, unfortunately, still in scope. The resulting
code compiles without error and runs without throwing an exception, but it does
the wrong thing. Instead of iterating over c2, the second loop terminates immediately,
giving the false impression that c2 is empty. Because the program errs
silently, the error can remain undetected for a long time.


Here is another loop idiom that minimizes the scope of local variables:
for (int i = 0, n = expensiveComputation(); i < n; i++) {
doSomething(i);
}
The important thing to notice about this idiom is that it has two loop variables, i
and n, both of which have exactly the right scope. The second variable, n, is used
to store the limit of the first, thus avoiding the cost of a redundant computation on
every iteration. As a rule, you should use this idiom if the loop test involves a
method invocation that is guaranteed to return the same result on each iteration.


A final technique to minimize the scope of local variables is to keep methods
small and focused. If you combine two activities in the same method, local variables
relevant to one activity may be in the scope of the code performing the other
activity. To prevent this from happening, simply separate the method into two: one
for each activity.













 */
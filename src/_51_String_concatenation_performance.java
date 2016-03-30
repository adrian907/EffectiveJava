/**
 * Created by Adrian on 29.03.2016.
 */
public class _51_String_concatenation_performance {
}
/*

Using the string
concatenation operator repeatedly to concatenate n strings requires time quadratic
in n. It is an unfortunate consequence of the fact that strings are immutable .


To achieve acceptable performance, use a StringBuilder in place of a String to store the
statement under construction. (The StringBuilder class, added in release 1.5, is
an unsynchronized replacement for StringBuffer, which is now obsolete.)


EX :
// Inappropriate use of string concatenation - Performs horribly!
public String statement() {
String result = "";
for (int i = 0; i < numItems(); i++)
result += lineForItem(i); // String concatenation
return result;
}


EX :
//Appropriate, faster version.
public String statement() {
StringBuilder b = new StringBuilder(numItems() * LINE_WIDTH);
for (int i = 0; i < numItems(); i++)
b.append(lineForItem(i));
return b.toString();
}


The moral is simple: don’t use the string concatenation operator to combine
more than a few strings unless performance is irrelevant. Use StringBuilder’s
append method instead. Alternatively, use a character array, or process the strings
one at a time instead of combining them.














 */
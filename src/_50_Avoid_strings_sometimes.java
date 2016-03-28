/**
 * Created by Adrian on 29.03.2016.
 */
public class _50_Avoid_strings_sometimes {
}
/*

Strings are poor substitutes for other value types.
More generally, if there’s an appropriate value type to which you can translate string, whether primitive
or object reference, you should use it; if there isn’t, you should write one.

Strings are poor substitutes for enum types.

Strings are poor substitutes for aggregate types. If an entity has multiple
components, it is usually a bad idea to represent it as a single string. For example,
here’s a line of code that comes from a real system—identifier names have been
changed to protect the guilty:
// Inappropriate use of string as aggregate type
String compoundKey = className + "#" + i.next();
This approach has many disadvantages. If the character used to separate fields
occurs in one of the fields, chaos may result. To access individual fields, you have
to parse the string, which is slow, tedious, and error-prone. You can’t provide
equals, toString, or compareTo methods but are forced to accept the behavior
that String provides. A better approach is simply to write a class to represent the
aggregate, often a private static member class (Item 22).


Strings are poor substitutes for capabilities.


To summarize, avoid the natural tendency to represent objects as strings when
better data types exist or can be written. Used inappropriately, strings are more
cumbersome, less flexible, slower, and more error-prone than other types. Types
for which strings are commonly misused include primitive types, enums, and
aggregate types.






















 */
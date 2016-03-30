/**
 * Created by Adrian on 29.03.2016.
 */
public class _56_Naming_conventions {
}
/*
Package names should be hierarchical with the components separated by periods.
Components should consist of lowercase alphabetic characters and, rarely,
digits. The name of any package that will be used outside your organization
should begin with your organization’s Internet domain name with the top-level
domain first, for example, edu.cmu, com.sun, gov.nsa. The standard libraries and
optional packages, whose names begin with java and javax, are exceptions to
this rule. Users must not create packages whose names begin with java or javax.

The remainder of a package name should consist of one or more components
describing the package. Components should be short, generally eight or fewer
characters. Meaningful abbreviations are encouraged, for example, util rather
than utilities. Acronyms are acceptable, for example, awt. Components should
generally consist of a single word or abbreviation.

Many packages have names with just one component in addition to the
Internet domain name. Additional components are appropriate for large facilities
whose size demands that they be broken up into an informal hierarchy. For
example, the javax.swing package has a rich hierarchy of packages with names
such as javax.swing.plaf.metal. Such packages are known as subpackages,
although there is no linguistic support for package hierarchies.

Class and interface names, including enum and annotation type names, should
consist of one or more words, with the first letter of each word capitalized, for
example, Timer or FutureTask. Abbreviations are to be avoided, except for acronyms
and certain common abbreviations like max and min.

If an acronym occurs as the first word of a method or field name, it should be lowercase.

Local variable names have similar typographical naming conventions to member
names, except that abbreviations are permitted, as are individual characters
and short sequences of characters whose meaning depends on the context in which
the local variable occurs, for example, i, xref, houseNumber.


Type parameter names usually consist of a single letter. Most commonly it is
one of these five: T for an arbitrary type, E for the element type of a collection, K
and V for the key and value types of a map, and X for an exception. A sequence of
arbitrary types can be T, U, V or T1, T2, T3.


***********GRAMATICAL CONVENTIONS :
There are no grammatical naming conventions to speak of for packages.

Classes, including enum types, are generally named with a
singular noun or noun phrase, for example, Timer, BufferedWriter.

Interfaces are named like classes, for example, Collection or Comparator,
or with an adjective ending in able or ible, for example, Runnable,Iterable, or Accessible.

Because annotation types have so many uses, no part
of speech predominates. Nouns, verbs, prepositions, and adjectives are all common,
for example, BindingAnnotation, Inject, ImplementedBy, or Singleton.

Methods that perform some action are generally named with a verb or verb
phrase (including object), for example, append or drawImage. Methods that return
a boolean value usually have names that begin with the word is or, less commonly,
has, followed by a noun, noun phrase, or any word or phrase that functions
as an adjective, for example, isDigit, isProbablePrime, isEmpty, isEnabled,
or hasSiblings.

Methods that return a non-boolean function or attribute of the object on
which they’re invoked are usually named with a noun, a noun phrase, or a verb
phrase beginning with the verb get, for example, size, hashCode, or getTime.


A few method names deserve special mention. Methods that convert the type
of an object, returning an independent object of a different type, are often called
toType, for example, toString, toArray. Methods that return a view (Item 5)
whose type differs from that of the receiving object are often called asType, for
example, asList. Methods that return a primitive with the same value as the
object on which they’re invoked are often called typeValue, for example,
intValue. Common names for static factories are valueOf, of, getInstance,
newInstance, getType, and newType (Item 1, page 10).






 */
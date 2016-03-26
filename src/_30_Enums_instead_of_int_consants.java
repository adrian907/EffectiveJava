/**
 * Created by Adrian on 25.03.2016.
 */
public class _30_Enums_instead_of_int_consants {
}
/*
An enumerated type is a type whose legal values consist of a fixed set of constants,
such as the seasons of the year, the planets in the solar system, or the suits
in a deck of playing cards.

Before enum types were added to the language, a common
pattern for representing enumerated types was to declare a group of named
int constants, one for each member of the type:

// The int enum pattern - severely deficient!
public static final int APPLE_FUJI = 0;
public static final int APPLE_PIPPIN = 1;
public static final int APPLE_GRANNY_SMITH = 2;

public static final int ORANGE_NAVEL = 0;
public static final int ORANGE_TEMPLE = 1;
public static final int ORANGE_BLOOD = 2;

This technique, known as the int enum pattern, has many shortcomings. It
provides nothing in the way of type safety and little in the way of convenience.
The compiler won’t complain if you pass an apple to a method that expects an
orange, compare apples to oranges with the == operator, or worse:
// Tasty citrus flavored applesauce!
int i = (APPLE_FUJI - ORANGE_TEMPLE) / APPLE_PIPPIN;

Note that the name of each apple constant is prefixed with APPLE_ and the
name of each orange constant is prefixed with ORANGE_. This is because Java
doesn’t provide namespaces for int enum groups. Prefixes prevent name clashes
when two int enum groups have identically named constants.

There is no easy way to translate int enum constants into printable strings. If
you print such a constant or display it from a debugger, all you see is a number,
which isn’t very helpful. There is no reliable way to iterate over all the int enum
constants in a group, or even to obtain the size of an int enum group.

You may encounter a variant of this pattern in which String constants are
used in place of int constants.

This variant, known as the String enum pattern, is
even less desirable. While it does provide printable strings for its constants, it can
lead to performance problems because it relies on string comparisons. Worse, it
can lead naive users to hard-code string constants into client code instead of using
field names. If such a hard-coded string constant contains a typographical error, it
will escape detection at compile time and result in bugs at runtime.



Java provides enum types instead of int and string enum patterns.
Java’s enum types are full-fledged classes, far more powerful than their counterparts in these other languages,
where enums are essentially int values.


The basic idea behind Java’s enum types is simple: they are classes that export
one instance for each enumeration constant via a public static final field. Enum
types are effectively final, by virtue of having no accessible constructors. Because
clients can neither create instances of an enum type nor extend it, there can be no
instances but the declared enum constants. In other words, enum types are
instance-controlled (page 6).


Enums provide compile-time type safety. If you declare a parameter to be of
type Apple, you are guaranteed that any non-null object reference passed to the
parameter is one of the three valid Apple values. Attempts to pass values of the
wrong type will result in compile-time errors, as will attempts to assign an expression
of one enum type to a variable of another, or to use the == operator to compare
values of different enum types.


In addition to rectifying the deficiencies of int enums, enum types let you add
arbitrary methods and fields and implement arbitrary interfaces. They provide
high-quality implementations of all the Object methods (Chapter 3), they implement
Comparable (Item 12) and Serializable (Chapter 11), and their serialized
form is designed to withstand most changes to the enum type.


To associate data with enum constants, declare instance fields and write a constructor that takes the
data and stores it in the fields.

Enums are by their nature immutable, so all fields
should be final (Item 15). They can be public, but it is better to make them private
and provide public accessors (Item 14).


All enums, has a static values() method that returns an array of its values in the order they were declared.
Note also that the toString method returns the declared name of each enum value, enabling easy printing by println
and printf.

If an enum is generally useful, it should be a top-level class; if its use is tied to
a specific top-level class, it should be a member class of that top-level class (Item
22).


The techniques demonstrated in the Planet example are sufficient for most
enum types, but sometimes you need more. There is different data associated with
each Planet constant, but sometimes you need to associate fundamentally different
behavior with each constant.

For example, suppose you are writing an enum type to represent the operations on a basic four-function calculator,
and you want to provide a method to perform the arithmetic operation represented by each constant.
One way to achieve this is to switch on the value of the enum:

// Enum type that switches on its own value - questionable
public enum Operation {
PLUS, MINUS, TIMES, DIVIDE;
// Do the arithmetic op represented by this constant
double apply(double x, double y) {
switch(this) {
case PLUS: return x + y;
case MINUS: return x - y;
case TIMES: return x * y;
case DIVIDE: return x / y;
}
throw new AssertionError("Unknown op: " + this);
}
}


This code works, but is isn’t very pretty. It won’t compile without the throw
statement because the end of the method is technically reachable, even though it
will never be reached. Worse, the code is fragile. If you add a new
enum constant but forget to add a corresponding case to the switch, the enum will
still compile, but it will fail at runtime when you try to apply the new operation.


Luckily, there is a better way to associate a different behavior with each enum
constant: declare an abstract apply method in the enum type, and override it with
a concrete method for each constant in a constant-specific class body. Such methods
are known as
constant-specific method implementations:

// Enum type with constant-specific method implementations
public enum Operation {
PLUS { double apply(double x, double y){return x + y;} },
MINUS { double apply(double x, double y){return x - y;} },
TIMES { double apply(double x, double y){return x * y;} },
DIVIDE { double apply(double x, double y){return x / y;} };
abstract double apply(double x, double y);
}

If you add a new constant to the second version of Operation, it is unlikely
that you’ll forget to provide an apply method, as the method immediately follows
each constant declaration. In the unlikely event that you do forget, the compiler
will remind you, as abstract methods in an enum type must be overridden with
concrete methods in all of its constants.



Ex :

// Enum type with constant-specific class bodies and data
public enum Operation {
PLUS("+") {
double apply(double x, double y) { return x + y; }
},
MINUS("-") {
double apply(double x, double y) { return x - y; }
},
TIMES("*") {
double apply(double x, double y) { return x * y; }
},
DIVIDE("/") {
double apply(double x, double y) { return x / y; }
};
private final String symbol;
Operation(String symbol) { this.symbol = symbol; }
@Override public String toString() { return symbol; }
abstract double apply(double x, double y);
}


CONCLUSION : SWITCHES ARE NOT ADVISABLE BECAUSE OF HIGH RISK OF FORGETTING TO ADD A NEW CASE .

When there's no possibility of making code concise thanks to constant-specific methods implmentations
we have to use different solution , like this :

What you really want is to be forced to choose an overtime pay strategy each
time you add an enum constant. Luckily, there is a nice way to achieve this. The
idea is to move the overtime pay computation into a private nested enum, and to
pass an instance of this strategy enum to the constructor for the PayrollDay enum.
The PayrollDay enum then delegates the overtime pay calculation to the strategy
enum, eliminating the need for a switch statement or constant-specific method
implementation in PayrollDay. While this pattern is less concise than the switch
statement, it is safer and more flexible:

// The strategy enum pattern
enum PayrollDay {
MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY),
WEDNESDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY),
FRIDAY(PayType.WEEKDAY),
SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
private final PayType payType;
PayrollDay(PayType payType) { this.payType = payType; }
double pay(double hoursWorked, double payRate) {
return payType.pay(hoursWorked, payRate);
}
// The strategy enum type
private enum PayType {
WEEKDAY {
double overtimePay(double hours, double payRate) {
return hours <= HOURS_PER_SHIFT ? 0 :
(hours - HOURS_PER_SHIFT) * payRate / 2;
}
},
WEEKEND {
double overtimePay(double hours, double payRate) {
return hours * payRate / 2;
}
};
private static final int HOURS_PER_SHIFT = 8;
abstract double overtimePay(double hrs, double payRate);
double pay(double hoursWorked, double payRate) {
double basePay = hoursWorked * payRate;
return basePay + overtimePay(hoursWorked, payRate);
}
}
}


Enums are, generally speaking, comparable in performance to int constants.
A minor performance disadvantage of enums over int constants is that there is a
space and time cost to load and initialize enum types.



So when should you use enums? Anytime you need a fixed set of constants.
Of course, this includes “natural enumerated types,” such as the planets, the days
of the week, and the chess pieces. But it also includes other sets for which you
know all the possible values at compile time, such as choices on a menu, operation
codes, and command line flags. It is not necessary that the set of constants in an
enum type stay fixed for all time. The enum feature was specifically designed to
allow for binary compatible evolution of enum types.



In summary, the advantages of enum types over int constants are compelling.
Enums are far more readable, safer, and more powerful. Many enums require no
explicit constructors or members, but many others benefit from associating data
with each constant and providing methods whose behavior is affected by this data.
Far fewer enums benefit from associating multiple behaviors with a single
method. In this relatively rare case, prefer constant-specific methods to enums that
switch on their own values. Consider the strategy enum pattern if multiple enum
constants share common behaviors.














 */
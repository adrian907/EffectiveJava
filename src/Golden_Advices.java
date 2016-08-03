/**
 * Created by Adrian on 26.03.2016.
 */
public class Golden_Advices {
}


/*

#27
Casting is fragile with generics.


#33
Don't use ordinal() for indexing array!

#34
We can't create extensible enums, thats's why we create and implement interfaces allowing emulating
such functionality.


#35
Use annotations instead of naming patterns.

#36
Use @Override annotation to prevent hard to find bugs on EVERY method you try to override.(even from interfaces)

#37
Marker interfaces should be used for marking interfaces and classes, marker annotations for every other situation.

#38
Check methods parameters ASAP if any restrictions occur(but be wise,generalise), you can prevent from hard to find bugs.

#39

Be aware of TOC/TOU attack (window vulnerability).
Don't use clone for making defensive copies.

#40
Don't create methods with too many parameters.

#41
The correct version of an overridden method is chosen at runtime,
based on the runtime type of the object on which the method is invoked.

The choice of which overloading to invoke is made at compile time.

Avoid confusing uses of overloading.

Don't create overloading methods with the same amount of parameters.


#42
Even though you can replace final parameter of a method which is array with varargs doesn't mean you should.

#43
If needed, always return empty array or collection, not NULL value.

#44
Making documentation comments is the best way to document your API. Restrict conventions .

#45
Use for loops instead of while to minimize variables scope.

#46
Use for-each loops instead of traditional, because they provide clarity, prevent from bugs and
don't influence on performance.

#47
Be familiar with standard Java API, mainly what you want already has been made.

#48
Avoid float and double types in accurate calculations, use BigDecimal instead.

#49
Use primitives rather than boxed primitives, they enhance performance and reduce bug possibility.

#50
Avoid strings if more appropriate data storing type exists, or can be easily created.

#51
If concatenation of more than few strings is needed, use StringBuilder to increase performance dramatically.

#52
If object's class implements interface, refer to it with interface's name, otherwise look for abstract class
implemented by object's class and lastly use object's class.

#53
Reflection is a very sophisticated and powerful tool with many disadvantages.

#54
Avoid native methods, they're error-prone. If its use is inevitable, write as little lines of code as you can.

#55
Write good programs first, then focus on optimization. Architecture first, optimization ( focus on algorithms) later.

#56
Follow naming conventions, otherwise you will be stoned by other programmers .

#57
Exceptions are designed for use in exceptional conditions. Don’t
use them for ordinary control flow, and don’t write APIs that force others to do so.

#58
Use checked exceptions for recoverable conditions and unchecked for program errors.

#59
Checked exceptions sometimes should be broken into two methods as they are hard to deal with sometimes.

#60
Code reuse is what you should focus on. Take advantage of Java exceptions API.

#61
If you can't prevent from lower layer exceptions, use Exception Translation, or Exception Chaining.

#62
Document checked as well as unchecked exceptions.

#63
Failure-capture messages should be concise and include parameter info .

#64
Failed method invocation should leave the object in the state that it was in prior to the invocation.
(atomic method)

#65
Never ignore exceptions, it leads to future failure.

#66
Use synchronization, whenever two threads attempt to change state of some object.






 */
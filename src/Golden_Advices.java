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

























 */
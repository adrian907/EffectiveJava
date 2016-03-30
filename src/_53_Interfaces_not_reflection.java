/**
 * Created by Adrian on 29.03.2016.
 */
public class _53_Interfaces_not_reflection {
}
/*

Reflection is used only at design time.
As a rule, objects should not be accessed reflectively in normal applications at runtime.

The core reflection facility, java.lang.reflect, offers programmatic access to
information about loaded classes. Given a Class object, you can obtain Constructor,
Method, and Field instances representing the constructors, methods, and fields
of the class represented by the Class instance. These objects provide programmatic
access to the class’s member names, field types, method signatures, and so on.
Moreover, Constructor, Method, and Field instances let you manipulate
their underlying counterparts reflectively: you can construct instances, invoke
methods, and access fields of the underlying class by invoking methods on the
Constructor, Method, and Field instances. For example, Method.invoke lets
you invoke any method on any object of any class (subject to the usual security
constraints). Reflection allows one class to use another, even if the latter class did
not exist when the former was compiled.

This power, however, comes at a price:

• You lose all the benefits of compile-time type checking, including exception
checking. If a program attempts to invoke a nonexistent or inaccessible method
reflectively, it will fail at runtime unless you’ve taken special precautions.

• The code required to perform reflective access is clumsy and verbose. It is
tedious to write and difficult to read.

• Performance suffers. Reflective method invocation is much slower than
normal method invocation. Exactly how much slower is hard to say, because
there are so many factors at work. On my machine, the speed difference can be
as small as a factor of two or as large as a factor of fifty.


There are a few sophisticated applications that require reflection. Examples
include class browsers, object inspectors, code analysis tools, and interpretive
embedded systems. Reflection is also appropriate for use in remote procedure call
(RPC) systems to eliminate the need for stub compilers.

You can obtain many of the benefits of reflection while incurring few of
its costs by using it only in a very limited form. For many programs that must
use a class that is unavailable at compile time, there exists at compile time an
appropriate interface or superclass by which to refer to the class (Item 52). If this
is the case, you can create instances reflectively and access them normally via
their interface or superclass. If the appropriate constructor has no parameters,
then you don’t even need to use java.lang.reflect; the Class.newInstance
method provides the required functionality.


In summary, reflection is a powerful facility that is required for certain sophisticated
system programming tasks, but it has many disadvantages. If you are writing
a program that has to work with classes unknown at compile time, you should,
if at all possible, use reflection only to instantiate objects, and access the objects
using some interface or superclass that is known at compile time.
















 */
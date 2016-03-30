/**
 * Created by Adrian on 29.03.2016.
 */
public class _54_Native_methods_use_them_judiciously {
}
/*

The Java Native Interface (JNI) allows Java applications to call native methods,
which are special methods written in native programming languages such as C or
C++. Native methods can perform arbitrary computation in native languages
before returning to the Java programming language.

USES OF NATIVE METHODS :
- provided access to platform-specific facilities such as registries and file locks;
- provided access to libraries of legacy code, which could in turn provide access to legacy data.
- were used to write performance-critical parts of applications in native languages for improved performance.

As java platform evolves, more and more functionalities provided only by native methods become part of a Java language,
so they are no longer needed.

It is rarely advisable to use native methods for improved performance.


In early releases (prior to 1.3), it was often necessary, but JVM implementations have
gotten much faster. For most tasks, it is now possible to obtain comparable performance
without resorting to native methods. For example, when java.math was
added to the platform in release 1.1, BigInteger was implemented atop a fast
multiprecision arithmetic library written in C. At the time, this was necessary for
adequate performance. In release 1.3, BigInteger was rewritten entirely in Java
and carefully tuned.


Because native languages are not safe (Item 39), applications using native methods are no longer
immune to memory corruption errors.
Because native languages are platform dependent, applications using native methods are far less portable.
Applications using native code are far more difficult to debug.
There is a fixed cost associated with going into and out of native code, so native methods can decrease performance
if they do only a small amount of work.
Finally, native methods require “glue code” that is difficult to read and tedious to write.

In summary, think twice before using native methods. Rarely, if ever, use them
for improved performance. If you must use native methods to access low-level
resources or legacy libraries, use as little native code as possible and test it thoroughly.
A single bug in the native code can corrupt your entire application.






 */
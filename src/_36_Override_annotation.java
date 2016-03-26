/**
 * Created by Adrian on 26.03.2016.
 */
public class _36_Override_annotation {
}

/*
You should use the Override annotation on every method declaration
that you believe to override a superclass declaration. There is one minor
exception to this rule. If you are writing a class that is not labeled abstract, and
you believe that it overrides an abstract method, you needn’t bother putting the
Override annotation on that method. In a class that is not declared abstract, the
compiler will emit an error message if you fail to override an abstract superclass
method. However, you might wish to draw attention to all of the methods in your
class that override superclass methods, in which case you should feel free to annotate
these methods too.



Modern IDEs provide another reason to use the Override annotation consistently.
Such IDEs have automated checks known as code inspections. If you
enable the appropriate code inspection, the IDE will generate a warning if you
have a method that doesn’t have an Override annotation but does override a
superclass method. If you use the Override annotation consistently, these warnings
will alert you to unintentional overriding.

If you are using release 1.6 or a later release, the Override annotation provides
even more help in finding bugs. In release 1.6, it became legal to use the
Override annotation on method declarations that override declarations from interfaces
as well as classes. Again, you may choose to include these annotations simply
to draw attention to interface methods, but it isn’t strictly necessary.
In an abstract class or an interface, however, it is worth annotating all methods
that you believe to override superclass or superinterface methods, whether concrete
or abstract. For example, the Set interface adds no new methods to the Collection
interface, so it should include Override annotations on all of its method
declarations, to ensure that it does not accidentally add any new methods to the
Collection interface.
















 */
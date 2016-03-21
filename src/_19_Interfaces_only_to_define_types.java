/**
 * Created by Adrian on 21.03.2016.
 */
public class _19_Interfaces_only_to_define_types {
}
/*

When a class implements an interface, the interface serves as a type that can
be used to refer to instances of the class.That a class implements an interface
should therefore say something about what a client can do with instances of the
class. It is inappropriate to define an interface for any other purpose.
One kind of interface that fails this test is the so-called constant interface.
Such an interface contains no methods; it consists solely of static final fields, each
exporting a constant. Classes using these constants implement the interface to
avoid the need to qualify constant names with a class name. Here is an example:


// Constant interface antipattern - do not use!
public interface PhysicalConstants {
// Avogadro's number (1/mol)
static final double AVOGADROS_NUMBER = 6.02214199e23;
// Boltzmann constant (J/K)
static final double BOLTZMANN_CONSTANT = 1.3806503e-23;
// Mass of the electron (kg)
static final double ELECTRON_MASS = 9.10938188e-31;
}


The constant interface pattern is a poor use of interfaces.

If a nonfinal class implements a constant interface, all of its subclasses will have their
namespaces polluted by the constants in the interface.

There are several constant interfaces in the Java platform libraries, such as
java.io.ObjectStreamConstants. These interfaces should be regarded as
anomalies and should not be emulated.


If you want to export constants, there are several reasonable choices. If the
constants are strongly tied to an existing class or interface, you should add them to
the class or interface. For example, all of the boxed numerical primitive classes,
such as Integer and Double, export MIN_VALUE and MAX_VALUE constants. If the
constants are best viewed as members of an enumerated type, you should export

them with an enum type (Item 30). Otherwise, you should export the constants
with a noninstantiable utility class (Item 4). Here is a utility class version of the
PhysicalConstants example above:

// Constant utility class
package com.effectivejava.science;
public class PhysicalConstants {
private PhysicalConstants() { } // Prevents instantiation
public static final double AVOGADROS_NUMBER = 6.02214199e23;
public static final double BOLTZMANN_CONSTANT = 1.3806503e-23;
public static final double ELECTRON_MASS = 9.10938188e-31;
}



In summary, interfaces should be used only to define types. They should not
be used to export constants.

 */
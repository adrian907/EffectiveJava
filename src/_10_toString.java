/**
 * Created by Adrian on 19.03.2016.
 */
public class _10_toString {
}

/*
While java.lang.Object provides an implementation of the toString method,
the string that it returns is generally not what the user of your class wants to see. It
consists of the class name followed by an “at” sign (@) and the unsigned hexadecimal
representation of the hash code, for example, “PhoneNumber@163b91.”

“It is recommended that all subclasses override this method.”
While it isn’t as important as obeying the equals and hashCode contracts
(Item 8, Item 9), providing a good toString implementation makes your class
much more pleasant to use.
The toString method is automatically invoked
when an object is passed to println, printf, the string concatenation operator, or
assert, or printed by a debugger.

When practical, the toString method should return all of the interesting
information contained in the object.

One important decision you’ll have to make when implementing a toString
method is whether to specify the format of the return value in the documentation.
It is recommended that you do this for value classes, such as phone numbers or
matrices. The advantage of specifying the format is that it serves as a standard,
unambiguous, human-readable representation of the object. This representation
can be used for input and output and in persistent human-readable data objects,
such as XML documents. If you specify the format, it’s usually a good idea to provide
a matching static factory or constructor so programmers can easily translate
back and forth between the object and its string representation. This approach is
taken by many value classes in the Java platform libraries, including BigInteger,
BigDecimal, and most of the boxed primitive classes.
The disadvantage of specifying the format of the toString return value is that
once you’ve specified it, you’re stuck with it for life, assuming your class is
widely used. Programmers will write code to parse the representation, to generate
it, and to embed it into persistent data. If you change the representation in a future
release, you’ll break their code and data, and they will yowl. By failing to specify
a format, you preserve the flexibility to add information or improve the format in
a subsequent release.


Whether or not you decide to specify the format, you should clearly document
your intentions. If you specify the format, you should do so precisely. For
example, here’s a toString method to go with the PhoneNumber class in Item 9:
*************COMMENT
* Returns the string representation of this phone number.
* The string consists of fourteen characters whose format
* is "(XXX) YYY-ZZZZ", where XXX is the area code, YYY is
* the prefix, and ZZZZ is the line number. (Each of the
* capital letters represents a single decimal digit.)
*
* If any of the three parts of this phone number is too small
* to fill up its field, the field is padded with leading zeros.
* For example, if the value of the line number is 123, the last
* four characters of the string representation will be "0123".
*
* Note that there is a single space separating the closing
* parenthesis after the area code from the first digit of the
* prefix.
************COMMENT
@Override public String toString() {
    return String.format("(%03d) %03d-%04d",
            areaCode, prefix, lineNumber);
}


If you decide not to specify a format, the documentation comment should read
something like this:
****************COMMENT
* Returns a brief description of this potion. The exact details
* of the representation are unspecified and subject to change,
* but the following may be regarded as typical:
*
* "[Potion #9: type=love, smell=turpentine, look=india ink]"
************COMMENT
*
@Override public String toString() { ... }
    After reading this comment, programmers who produce code or persistent
        data that depends on the details of the format will have no one but themselves to
        blame when the format is changed.
        Whether or not you specify the format, provide programmatic access to all
        of the information contained in the value returned by toString. For example,
        the PhoneNumber class should contain accessors for the area code, prefix, and line
        number. If you fail to do this, you force programmers who need this information
        to parse the string. Besides reducing performance and making unnecessary work
        for programmers, this process is error-prone and results in fragile systems that
        break if you change the format. By failing to provide accessors, you turn the string
        format into a de facto API, even if you’ve specified that it’s subject to change.

 */
/**
 * Created by Adrian on 28.03.2016.
 */
public class _48_Float_and_Double_avoid {
}
/*
The float and double types are particularly illsuited
for monetary calculations because it is impossible to represent 0.1 (or any
other negative power of ten) as a float or double exactly.

System.out.println(1.03 - .42);
Unfortunately, it prints out 0.6100000000000001.

System.out.println(1.00 - 9 * .10);
According to this program fragment, you get $0.09999999999999998.


// Broken - uses floating point for monetary calculation!
public static void main(String[] args) {
double funds = 1.00;
int itemsBought = 0;
for (double price = .10; funds >= price; price += .10) {
funds -= price;
itemsBought++;
}
System.out.println(itemsBought + " items bought.");
System.out.println("Change: $" + funds);
}
If you run the program, you’ll find that you can afford three pieces of candy, and
you have $0.3999999999999999 left.


BIGDECIMAL VERSION :
public static void main(String[] args) {
final BigDecimal TEN_CENTS = new BigDecimal( ".10");
int itemsBought = 0;
BigDecimal funds = new BigDecimal("1.00");
for (BigDecimal price = TEN_CENTS;
funds.compareTo(price) >= 0;
price = price.add(TEN_CENTS)) {
itemsBought++;
funds = funds.subtract(price);
}
System.out.println(itemsBought + " items bought.");
System.out.println("Money left over: $" + funds);
}


In summary, don’t use float or double for any calculations that require an
exact answer. Use BigDecimal if you want the system to keep track of the decimal
point and you don’t mind the inconvenience and cost of not using a primitive type.
Using BigDecimal has the added advantage that it gives you full control over
rounding, letting you select from eight rounding modes whenever an operation
that entails rounding is performed. This comes in handy if you’re performing
business calculations with legally mandated rounding behavior. If performance is
of the essence, you don’t mind keeping track of the decimal point yourself, and
the quantities aren’t too big, use int or long. If the quantities don’t exceed nine
decimal digits, you can use int; if they don’t exceed eighteen digits, you can use
long. If the quantities might exceed eighteen digits, you must use BigDecimal.






















 */
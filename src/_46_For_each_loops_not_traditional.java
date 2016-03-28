/**
 * Created by Adrian on 28.03.2016.
 */
public class _46_For_each_loops_not_traditional {
}
/*

Not only does the for-each loop let you iterate over collections and arrays, it
lets you iterate over any object that implements the Iterable interface.

Prior to release 1.5, this was the preferred idiom for iterating over a collection:
// No longer the preferred idiom to iterate over a collection!
for (Iterator i = c.iterator(); i.hasNext(); ) {
doSomething((Element) i.next()); // (No generics before 1.5)
}
This was the preferred idiom for iterating over an array:
// No longer the preferred idiom to iterate over an array!
for (int i = 0; i < a.length; i++) {
doSomething(a[i]);
}


The iterator and the index variables are both just clutter. Furthermore, they represent
opportunities for error. The iterator and the index variable occur three times in
each loop, which gives you two chances to get them wrong. If you do, there is no
guarantee that the compiler will catch the problem.

The for-each loop, introduced in release 1.5, gets rid of the clutter and the
opportunity for error by hiding the iterator or index variable completely. The
resulting idiom applies equally to collections and arrays:

// The preferred idiom for iterating over collections and arrays
for (Element e : elements) {
doSomething(e);
}


When you see the colon (:), read it as “in.” Thus, the loop above reads as “for
each element e in elements.”

For-each loops are not slower, sometimes in special circumstances they are even faster than traditional loops.



EXAMPLE :
The advantages of the for-each loop over the traditional for loop are even
greater when it comes to nested iteration over multiple collections. Here is a common
mistake that people make when they try to do nested iteration over two collections:

// Can you spot the bug?
enum Suit { CLUB, DIAMOND, HEART, SPADE }
enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
NINE, TEN, JACK, QUEEN, KING }
...
Collection<Suit> suits = Arrays.asList(Suit.values());
Collection<Rank> ranks = Arrays.asList(Rank.values());
List<Card> deck = new ArrayList<Card>();
for (Iterator<Suit> i = suits.iterator(); i.hasNext(); )
for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
deck.add(new Card(i.next(), j.next()));

Don’t feel bad if you didn’t spot the bug. Many expert programmers have
made this mistake at one time or another. The problem is that the next method is
called too many times on the iterator for the outer collection (suits). It should be
called from the outer loop, so that it is called once per suit, but instead it is called
from the inner loop, so it is called once per card. After you run out of suits, the
loop throws a NoSuchElementException.


If you’re really unlucky and the size of the outer collection is a multiple of the
size of the inner collection—perhaps because they’re the same collection—the
loop will terminate normally, but it won’t do what you want. For example, consider
this ill-conceived attempt to print all of the possible rolls of a pair of dice:

// Same bug, different symptom!
enum Face { ONE, TWO, THREE, FOUR, FIVE, SIX }
...
Collection<Face> faces = Arrays.asList(Face.values());
for (Iterator<Face> i = faces.iterator(); i.hasNext(); )
for (Iterator<Face> j = faces.iterator(); j.hasNext(); )
System.out.println(i.next() + " " + j.next());

This program doesn’t throw an exception but it prints only the six “doubles” (from
“ONE ONE” to “SIX SIX”), instead of the expected thirty-six combinations.
To fix the bugs in these examples, you must add a variable in the scope of the
outer loop to hold the outer element:

// Fixed, but ugly - you can do better!
for (Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
Suit suit = i.next();
for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
deck.add(new Card(suit, j.next()));
}

If instead you use a nested for-each loop, the problem simply disappears. The
resulting code is as succinct as you could wish for:

// Preferred idiom for nested iteration on collections and arrays
for (Suit suit : suits)
for (Rank rank : ranks)
deck.add(new Card(suit, rank));

Not only does the for-each loop let you iterate over collections and arrays, it
lets you iterate over any object that implements the Iterable interface. This simple
interface, which consists of a single method, was added to the platform at the
same time as the for-each loop. Here is how it looks:
public interface Iterable<E> {
// Returns an iterator over the elements in this iterable
Iterator<E> iterator();
}






Not only does the for-each loop let you iterate over collections and arrays, it
lets you iterate over any object that implements the Iterable interface. This simple
interface, which consists of a single method, was added to the platform at the
same time as the for-each loop. Here is how it looks:
public interface Iterable<E> {
// Returns an iterator over the elements in this iterable
Iterator<E> iterator();
}
It is not hard to implement the Iterable interface. If you are writing a type
that represents a group of elements, have it implement Iterable even if you
choose not to have it implement Collection. This will allow your users to iterate
over your type using the for-each loop, and they will be forever grateful.





Unfortunately, there are three common situations
where you can’t use a for-each loop:

1. Filtering—If you need to traverse a collection and remove selected elements,
then you need to use an explicit iterator so that you can call its remove method.

2. Transforming—If you need to traverse a list or array and replace some or all
of the values of its elements, then you need the list iterator or array index in
order to set the value of an element.

3. Parallel iteration—If you need to traverse multiple collections in parallel,
then you need explicit control over the iterator or index variable, so that all iterators
or index variables can be advanced in lockstep (as demonstrated unintentionally
in the buggy card and dice examples above).

If you find yourself in any of these situations, use an ordinary for loop, be wary ofq
the traps mentioned in this item, and know that you’re doing the best you can.





 */
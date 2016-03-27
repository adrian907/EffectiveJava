/**
 * Created by Adrian on 27.03.2016.
 */
public class _43_Return_empty_arrays_or_collections_not_nulls {
}
/*

There is no reason to make a special case for the situation where no cheeses
are available for purchase. Doing so requires extra code in the client to handle the
null return value, for example:
Cheese[] cheeses = shop.getCheeses();
if (cheeses != null &&
Arrays.asList(cheeses).contains(Cheese.STILTON))
System.out.println("Jolly good, just the thing.");
instead of:
if (Arrays.asList(shop.getCheeses()).contains(Cheese.STILTON))
System.out.println("Jolly good, just the thing.");


It is sometimes argued that a null return value is preferable to an empty array
because it avoids the expense of allocating the array. This argument fails on two
counts. First, it is inadvisable to worry about performance at this level unless profiling
has shown that the method in question is a real contributor to performance
problems (Item 55). Second, it is possible to return the same zero-length array
from every invocation that returns no items because zero-length arrays are immutable
and immutable objects may be shared freely (Item 15).


In similar fashion, a collection-valued method can be made to return the same
immutable empty collection every time it needs to return an empty collection. The
Collections.emptySet, emptyList, and emptyMap methods provide exactly
what you need, as shown below:

// The right way to return a copy of a collection
public List<Cheese> getCheeseList() {
if (cheesesInStock.isEmpty())
return Collections.emptyList(); // Always returns same list
else
return new ArrayList<Cheese>(cheesesInStock);
}

In summary, there is no reason ever to return null from an array- or
collection-valued method instead of returning an empty array or collection.





















 */
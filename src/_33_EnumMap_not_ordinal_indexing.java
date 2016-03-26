/**
 * Created by Adrian on 25.03.2016.
 */
public class _33_EnumMap_not_ordinal_indexing {
}
/*

Occasionally you may see code that uses the ordinal method (Item 31) to index
into an array. For example, consider this simplistic class meant to represent a culinary herb:

public class Herb {
public enum Type { ANNUAL, PERENNIAL, BIENNIAL }
private final String name;
private final Type type;
Herb(String name, Type type) {
this.name = name;
this.type = type;
}
@Override public String toString() {
return name;
}
}


Now suppose you have an array of herbs representing the plants in a garden,
and you want to list these plants organized by type (annual, perennial, or biennial).
To do this, you construct three sets, one for each type, and iterate through the garden,
placing each herb in the appropriate set. Some programmers would do this by
putting the sets into an array indexed by the type’s ordinal:

// Using ordinal() to index an array - DON'T DO THIS!
Herb[] garden = ... ;
Set<Herb>[] herbsByType = // Indexed by Herb.Type.ordinal()
(Set<Herb>[]) new Set[Herb.Type.values().length];
for (int i = 0; i < herbsByType.length; i++)
herbsByType[i] = new HashSet<Herb>();
for (Herb h : garden)
herbsByType[h.type.ordinal()].add(h);
// Print the results
for (int i = 0; i < herbsByType.length; i++) {
System.out.printf("%s: %s%n",
Herb.Type.values()[i], herbsByType[i]);
}

This technique works, but it is fraught with problems. Because arrays are not
compatible with generics (Item 25), the program requires an unchecked cast and
will not compile cleanly. Because the array does not know what its index represents,
you have to label the output manually. But the most serious problem with
this technique is that when you access an array that is indexed by an enum’s ordinal,
it is your responsibility to use the correct int value; ints do not provide the
type safety of enums. If you use the wrong value, the program will silently do the
wrong thing or—if you’re lucky—throw an ArrayIndexOutOfBoundsException.


SOLUTION :

Luckily, there is a much better way to achieve the same effect. The array is
effectively serving as a map from the enum to a value, so you might as well use a
Map. More specifically, there is a very fast Map implementation designed for use
with enum keys, known as java.util.EnumMap. Here is how the program looks
when it is rewritten to use EnumMap:

// Using an EnumMap to associate data with an enum
Map<Herb.Type, Set<Herb>> herbsByType =
new EnumMap<Herb.Type, Set<Herb>>(Herb.Type.class);
for (Herb.Type t : Herb.Type.values())
herbsByType.put(t, new HashSet<Herb>());
for (Herb h : garden)
herbsByType.get(h.type).add(h);
System.out.println(herbsByType);

This program is shorter, clearer, safer, and comparable in speed to the original
version. There is no unsafe cast; no need to label the output manually, as the map
keys are enums that know how to translate themselves to printable strings; and no
possibility for error in computing array indices. The reason that EnumMap is comparable
in speed to an ordinal-indexed array is that EnumMap uses such an array
internally. Note that the EnumMap constructor takes the Class object of the key type: this is a bounded type
token, which provides runtime generic type information (Item 29).



2nd PROBLEM

You may see an array of arrays indexed (twice!) by ordinals used to represent
a mapping from two enum values. For example, this program uses such an array to
map two phases to a phase transition (liquid to solid is freezing, liquid to gas is
boiling, and so forth):

// Using ordinal() to index array of arrays - DON'T DO THIS!
public enum Phase { SOLID, LIQUID, GAS;
public enum Transition {
MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;
// Rows indexed by src-ordinal, cols by dst-ordinal
private static final Transition[][] TRANSITIONS = {
{ null, MELT, SUBLIME },
{ FREEZE, null, BOIL },
{ DEPOSIT, CONDENSE, null }
};
// Returns the phase transition from one phase to another
public static Transition from(Phase src, Phase dst) {
return TRANSITIONS[src.ordinal()][dst.ordinal()];
}
}
}


Again, you can do much better with EnumMap. Because each phase transition
is indexed by a pair of phase enums, you are best off representing the relationship
as a map from one enum (the source phase) to a map from the second enum (the
destination phase) to the result (the phase transition). The two phases associated
with a phase transition are best captured by associating data with the phase transition
enum, which is then used to initialize the nested EnumMap:

// Using a nested EnumMap to associate data with enum pairs
public enum Phase {
SOLID, LIQUID, GAS;
public enum Transition {
MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
final Phase src;
final Phase dst;
Transition(Phase src, Phase dst) {
this.src = src;
this.dst = dst;
}
// Initialize the phase transition map
private static final Map<Phase, Map<Phase,Transition>> m =
new EnumMap<Phase, Map<Phase,Transition>>(Phase.class);
static {
for (Phase p : Phase.values())
m.put(p,new EnumMap<Phase,Transition>(Phase.class));
for (Transition trans : Transition.values())
m.get(trans.src).put(trans.dst, trans);
}
public static Transition from(Phase src, Phase dst) {
return m.get(src).get(dst);
}
}
}

The code to initialize the phase transition map may look a bit complicated but
it isn’t too bad. The type of the map is Map<Phase, Map<Phase, Transition>>,
which means “map from (source) phase to map from (destination) phase to transition.”
The first loop in the static initializer block initializes the outer map to contain
the three empty inner maps. The second loop in the block initializes the inner
maps using the source and destination information provided by each state transition
constant.


Internally, the map of maps is implemented as an array of arrays, so you pay little
in space or time cost for the added clarity, safety, and ease of maintenance.


In summary, it is rarely appropriate to use ordinals to index arrays: use
EnumMap instead. If the relationship that you are representing is multidimensional,
use EnumMap<..., EnumMap<...>>. This is a special case of the general principle
that application programmers should rarely, if ever, use Enum.ordinal (Item 31).

 */
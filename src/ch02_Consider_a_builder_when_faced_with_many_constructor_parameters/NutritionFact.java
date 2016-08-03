package ch02_Consider_a_builder_when_faced_with_many_constructor_parameters;


// JAVA BEANS PATTERN FOR MANY PARAMETERS USING SETTERS. 

//A second alternative when you are faced with many constructor parameters is
//the JavaBeans pattern, in which you call a parameterless constructor to create the
//object and then call setter methods to set each required parameter and each
//optional parameter of interest:

public class NutritionFact {
//Parameters initialized to default values (if any)
	
	
@SuppressWarnings("unused")
private int servingSize = -1; // Required; no default value
@SuppressWarnings("unused")
private int servings = -1; // " " " "
@SuppressWarnings("unused")
private int calories = 0;
@SuppressWarnings("unused")
private int fat = 0;
@SuppressWarnings("unused")
private int sodium = 0;
public NutritionFact() { }


//Setters
public void setServingSize(int val) { servingSize = val; }
public void setServings(int val) { servings = val; }
public void setCalories(int val) { calories = val; }
public void setFat(int val) { fat = val; }
public void setSodium(int val) { sodium = val; }
public void setCarbohydrate(int val) { }

}
//PLUSES : This pattern has none of the disadvantages of the telescoping constructor pattern.
//It is easy, if a bit wordy, to create instances, and easy to read the resulting code

//MINUSES : 
/*Because construction is split across multiple calls, a JavaBean may be in an
inconsistent state partway through its construction. The class does not have
the option of enforcing consistency merely by checking the validity of the constructor
parameters. Attempting to use an object when it’s in an inconsistent state
may cause failures that are far removed from the code containing the bug, hence
difficult to debug. A related disadvantage is that the JavaBeans pattern precludes
the possibility of making a class immutable (Item 15), and requires
added effort on the part of the programmer to ensure thread safety.
 * */


/*It is possible to reduce these disadvantages by manually “freezing” the object
when its construction is complete and not allowing it to be used until frozen, but
this variant is unwieldy and rarely used in practice. Moreover, it can cause errors
at runtime, as the compiler cannot ensure that the programmer calls the freeze
method on an object before using it.*/
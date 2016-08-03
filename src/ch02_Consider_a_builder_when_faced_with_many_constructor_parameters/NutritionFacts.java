package ch02_Consider_a_builder_when_faced_with_many_constructor_parameters;


//TELESCOPING CONSTRUCTOR PATTERN FOR MANY PARAMETERS.


/*Telescoping constructor pattern-
 *you provide a constructor with only the required parameters, another with a
 *single optional parameter, a third with two optional parameters, and so on, culminating
 *in a constructor with all the optional parameters.
 *For brevity’s sake, only four optional fields are shown in this example:
 */

// Telescoping constructor pattern - does not scale well!

public class NutritionFacts {
	@SuppressWarnings("unused")
	private final int servingSize; // (mL) required
	@SuppressWarnings("unused")
	private final int servings; // (per container) required
	@SuppressWarnings("unused")
	private final int calories; // optional
	@SuppressWarnings("unused")
	private final int fat; // (g) optional
	@SuppressWarnings("unused")
	private final int sodium; // (mg) optional
	@SuppressWarnings("unused")
	private final int carbohydrate; // (g) optional

	public NutritionFacts(int servingSize, int servings) {
		this(servingSize, servings, 0);
	}

	public NutritionFacts(int servingSize, int servings, int calories) {
		this(servingSize, servings, calories, 0);
	}

	public NutritionFacts(int servingSize, int servings, int calories, int fat) {
		this(servingSize, servings, calories, fat, 0);
	}

	public NutritionFacts(int servingSize, int servings, int calories, int fat,
			int sodium) {
		this(servingSize, servings, calories, fat, sodium, 0);
	}

	public NutritionFacts(int servingSize, int servings, int calories, int fat,
			int sodium, int carbohydrate) {
		this.servingSize = servingSize;
		this.servings = servings;
		this.calories = calories;
		this.fat = fat;
		this.sodium = sodium;
		this.carbohydrate = carbohydrate;
	}
}

/*In short, the telescoping constructor pattern works, but it is hard to write
client code when there are many parameters, and harder still to read it.
*/
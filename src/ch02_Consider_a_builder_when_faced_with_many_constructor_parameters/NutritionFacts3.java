package ch02_Consider_a_builder_when_faced_with_many_constructor_parameters;

//BUILDER PATTERN . 

/*A third alternative that combines the safety of the telescoping
 constructor pattern with the readability of the JavaBeans pattern. It is a form of the
 Builder pattern. Instead of making the desired object directly,
 the client calls a constructor (or static factory) with all of the required parameters
 and gets a builder object. Then the client calls setter-like methods on the builder
 object to set each optional parameter of interest. Finally, the client calls a parameterless
 build method to generate the object, which is immutable. The builder is a
 static member class of the class it builds.
 */
//Builder Pattern
public class NutritionFacts3 {
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;

	public static class Builder {
		// Required parameters
		private final int servingSize;
		private final int servings;
		// Optional parameters - initialized to default values
		private int calories = 0;
		private int fat = 0;
		private int carbohydrate = 0;
		private int sodium = 0;

		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		public Builder calories(int val) {
			calories = val;
			return this;
		}

		public Builder fat(int val) {
			fat = val;
			return this;
		}

		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}

		public Builder sodium(int val) {
			sodium = val;
			return this;
		}

		public NutritionFacts3 build() {
			return new NutritionFacts3(this);
		}
	}

	private NutritionFacts3(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}
}
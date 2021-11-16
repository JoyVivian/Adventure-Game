package random;

/**
 * RandomFactory is a class that use the factory method
 * to generate RandomValue objects. When type is "RealRandom",
 * it returns a RealRandom object. When type is "FalseRandom"
 * it returns a FalseRandom object.
 */
public class RandomFactory {
  /**
   * Used to generate RandomValue instances according the type parameter.
   *
   * @param isReal     Represents which RandomValue object to generate.
   * @param lowerBound The lowerBound of a range that random values will come from.
   * @param upperBound The upperBound of a range that random values will come from.
   * @return A RandomValue instance.
   * @throws IllegalArgumentException If the type parameters is null, an IAE will be thrown.
   */
  public RandomValue createRandomInstance(boolean isReal, int lowerBound, int upperBound)
          throws IllegalArgumentException {
    if (isReal) {
      return new RealRandom(lowerBound, upperBound);
    } else {
      return new FalseRandom(lowerBound, upperBound);
    }
  }
}

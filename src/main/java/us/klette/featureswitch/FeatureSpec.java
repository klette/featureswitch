package us.klette.featureswitch;

import java.util.Objects;

public class FeatureSpec {
  private final String name;
  private final short percent;

  /**
   * Create a new FeatureSpec instance.
   *
   * @param name The name of the feature that is specified. Must be non-null and non-blank.
   * @param percent The percentage of calls that should have this feature enabled.
   */
  public FeatureSpec(final String name, final short percent) {
    this.name = Objects.requireNonNull(name, "featurespec must be provided with a name");
    this.percent = percent;

    if (name.isEmpty()) {
      throw new IllegalArgumentException("FeatureSpec name must be non-blank String");
    }

  }

  public String getName() {
    return name;
  }

  public short getPercent() {
    return percent;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    FeatureSpec that = (FeatureSpec) other;
    return percent == that.percent
        && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, percent);
  }
}

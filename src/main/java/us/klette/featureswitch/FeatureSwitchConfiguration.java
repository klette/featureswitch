package us.klette.featureswitch;

import java.time.Duration;
import java.util.Objects;

public class FeatureSwitchConfiguration {

  private final Duration localCacheDuration;

  public FeatureSwitchConfiguration(final Duration localCacheDuration) {
    this.localCacheDuration = localCacheDuration;
  }

  public Duration getLocalCacheDuration() {
    return localCacheDuration;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    FeatureSwitchConfiguration that = (FeatureSwitchConfiguration) other;
    return Objects.equals(localCacheDuration, that.localCacheDuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localCacheDuration);
  }
}

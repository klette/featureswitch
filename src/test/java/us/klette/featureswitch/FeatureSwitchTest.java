package us.klette.featureswitch;


import org.junit.Before;
import org.junit.Test;
import us.klette.featureswitch.provider.InMemoryStorageProvider;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class FeatureSwitchTest {

  private final FeatureSwitchConfiguration configuration = new FeatureSwitchConfiguration(Duration.ZERO);
  private final InMemoryStorageProvider inMemoryStorageProvider = new InMemoryStorageProvider();
  private final FeatureSwitch fs = new FeatureSwitch(configuration, inMemoryStorageProvider);

  @Before
  public void setUp() throws Exception {
    inMemoryStorageProvider.clear();
  }

  @Test
  public void shouldDefaultToFalseForNonExistingTestName() throws Exception {
    assertThat(fs.isEnabled("non-existing-feature")).isFalse();
  }

  @Test
  public void shouldReturnFalseIfFeatureNameIsNullOrBlank() throws Exception {
    assertThat(fs.isEnabled("")).isFalse();
    assertThat(fs.isEnabled(null)).isFalse();
  }

  @Test
  public void shouldReturnTrueForExistingFeatureThatIsEnabled() throws Exception {
    inMemoryStorageProvider.addFeature(new FeatureSpec("my-feature", (short) 100));

    assertThat(fs.isEnabled("my-feature")).isTrue();
  }

  @Test
  public void shouldReturnTrueForTheCorrectAmountOfCalls() throws Exception {
    inMemoryStorageProvider.addFeature(new FeatureSpec("my-test-feature", (short) 50));

    int trueCount = 0;
    for (int i = 0; i < 10000; i++) {
      if (fs.isEnabled("my-test-feature")) {  trueCount++; }
    }

    assertThat(trueCount).isEqualTo(5000);
  }
}

package us.klette.featureswitch;

import us.klette.featureswitch.provider.FeatureSwitchStorageProvider;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class FeatureSwitch {

  private static final Logger logger = Logger.getLogger(FeatureSwitch.class.getName());

  private final FeatureSwitchConfiguration configuration;
  private final FeatureSwitchStorageProvider storageProvider;

  private final Map<FeatureSpec, AtomicInteger> callRegistry = new ConcurrentHashMap<>();

  public FeatureSwitch(final FeatureSwitchConfiguration configuration,
                       final FeatureSwitchStorageProvider storageProvider) {

    this.configuration = Objects.requireNonNull(configuration,
        "configuration object must be non-null");

    this.storageProvider = Objects.requireNonNull(storageProvider,
        "storageProvider must be provided");
  }

  public boolean isEnabled(final String featureName) {

    if (featureName == null || featureName.isEmpty()) {
      return false;
    }

    logger.fine("Checking if feature \"" + featureName + "\" is enabled");

    final Optional<FeatureSpec> feature = storageProvider.getFeature(featureName);


    return feature
        .map(ft -> {
          final AtomicInteger counter = callRegistry
              .computeIfAbsent(ft, featureSpec -> new AtomicInteger(0));

          final int newCallCount = counter.getAndIncrement();
          if (newCallCount > 0 && newCallCount % 100 == 0) {
            counter.set(0);
          }
          return newCallCount < ft.getPercent();
        })
        .orElse(false);
  }
}

package us.klette.featureswitch.provider;

import us.klette.featureswitch.FeatureSpec;

import java.util.List;
import java.util.Optional;

public interface FeatureSwitchStorageProvider {
  Optional<FeatureSpec> getFeature(final String featureName);

  List<FeatureSpec> getAllFeatures();
}

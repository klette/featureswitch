package us.klette.featureswitch.provider;

import us.klette.featureswitch.FeatureSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStorageProvider implements FeatureSwitchStorageProvider {

  private final ConcurrentHashMap<String, FeatureSpec> db = new ConcurrentHashMap<>();

  @Override
  public Optional<FeatureSpec> getFeature(final String featureName) {
    return Optional.ofNullable(db.get(featureName));
  }

  @Override
  public List<FeatureSpec> getAllFeatures() {
    return new ArrayList<>(db.values());
  }

  public void addFeature(FeatureSpec feature) {
    db.put(feature.getName(), feature);
  }

  public void clear() {
    db.clear();
  }
}

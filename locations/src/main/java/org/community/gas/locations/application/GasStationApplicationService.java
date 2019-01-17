package org.community.gas.locations.application;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.community.gas.locations.model.GasLocation;
import org.community.gas.locations.model.GasLocationRepository;
import org.reactivestreams.Publisher;

@Singleton
public class GasStationApplicationService {

  @Inject
  GasLocationRepository repository;

  public Publisher<GasLocation> getGasStations(GetGasStationsCommand command) {
    return repository.findGasStations(command);
  }
}

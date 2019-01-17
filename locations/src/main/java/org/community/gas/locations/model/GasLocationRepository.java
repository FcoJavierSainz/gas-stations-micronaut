package org.community.gas.locations.model;

import org.community.gas.locations.application.GetGasStationsCommand;
import org.reactivestreams.Publisher;

public interface GasLocationRepository {

  Publisher<GasLocation> findGasStations(GetGasStationsCommand command);
}

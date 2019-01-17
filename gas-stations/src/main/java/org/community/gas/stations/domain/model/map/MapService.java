package org.community.gas.stations.domain.model.map;

import io.reactivex.Flowable;
import java.util.List;
import org.community.gas.stations.domain.model.locations.GasStation;
import org.community.gas.stations.domain.model.locations.GasStation.Location;

public interface MapService {

  Flowable<Destination> getDistance(List<GasStation> gasStations, Location origin);
}

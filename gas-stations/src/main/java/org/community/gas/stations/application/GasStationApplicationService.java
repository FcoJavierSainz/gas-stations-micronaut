package org.community.gas.stations.application;

import io.reactivex.Flowable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.community.gas.stations.domain.model.locations.GasStation;
import org.community.gas.stations.domain.model.locations.GasStation.Location;
import org.community.gas.stations.domain.model.locations.GasStationService;
import org.community.gas.stations.domain.model.map.MapService;
import org.community.gas.stations.domain.model.price.GasPriceService;

@Singleton
public class GasStationApplicationService {

  @Inject
  GasPriceService gasPriceService;
  @Inject
  MapService mapService;
  @Inject
  GasStationService gasStationService;
  ExecutorService service = Executors.newFixedThreadPool(100);

  public Flowable<GasStation> getStationsNear(double longitude, double latitude, int radio) {
    return gasStationService.getStationsNear(longitude, latitude, radio)
        .buffer(20)
        .flatMap(gasStations ->
            Flowable.zip(Flowable.fromIterable(gasStations),
                gasPriceService.getPrices(gasStations),
                mapService.getDistance(gasStations, new Location(longitude, latitude)),
                (gasStation, gasStationPrices, destination) -> {
                  gasStation.setPrices(gasStationPrices);
                  gasStation.setDestination(destination);
                  return gasStation;
                }
            )
        );
  }

}

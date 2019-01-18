package org.community.gas.stations.resources;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import javax.inject.Inject;
import org.community.gas.stations.application.GasStationApplicationService;
import org.community.gas.stations.domain.model.locations.GasStation;
import org.reactivestreams.Publisher;

@Controller("/api/v1/gasStations")
public class GasStationResources {

  @Inject
  GasStationApplicationService service;

  @Get(value = "/near", produces = {MediaType.APPLICATION_JSON_STREAM, MediaType.APPLICATION_JSON})
  public Publisher<GasStation> getLocationsNearTo(@QueryValue("longitude") double longitude,
      @QueryValue("latitude") double latitude,
      @QueryValue(value = "radio", defaultValue = "1000") int radio) {
    return service.getStationsNear(longitude, latitude, radio);
  }
}

package org.community.gas.locations.resources;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import javax.inject.Inject;
import org.community.gas.locations.application.GasStationApplicationService;
import org.community.gas.locations.application.GetGasStationsCommand;
import org.community.gas.locations.model.GasLocation;
import org.reactivestreams.Publisher;

@Controller("/api/v1/locations")
public class GasStationResources {

  @Inject
  GasStationApplicationService service;

  @Get(value = "/near", produces = {MediaType.APPLICATION_JSON_STREAM, MediaType.APPLICATION_JSON})
  public Publisher<GasLocation> getLocationsNearTo(@QueryValue("longitude") double longitude,
      @QueryValue("latitude") double latitude,
      @QueryValue(value = "radio", defaultValue = "1000") int radio) {
    return service.getGasStations(new GetGasStationsCommand(longitude, latitude, radio));
  }
}

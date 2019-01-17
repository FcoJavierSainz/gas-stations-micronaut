package org.community.gas.stations.domain.model.locations;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;

@Client(id = "locations")
public interface GasStationService {

  @Get(value = "/api/v1/locations/near", processes = MediaType.APPLICATION_JSON_STREAM)
  Flowable<GasStation> getStationsNear(@QueryValue double longitude, @QueryValue double latitude,
      @QueryValue int radio);
}

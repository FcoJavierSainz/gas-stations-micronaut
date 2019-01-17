package org.community.gas.prices.resources;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import java.util.Set;
import javax.inject.Inject;
import org.community.gas.prices.application.GasPriceApplicationService;
import org.community.gas.prices.model.station.GasStationPrices;
import org.reactivestreams.Publisher;

@Controller("/api/v1/prices")
public class GasPricesResource {

  private GasPriceApplicationService service;

  @Inject
  public GasPricesResource(GasPriceApplicationService service) {
    this.service = service;
  }

  @Get(produces = {MediaType.APPLICATION_JSON_STREAM, MediaType.APPLICATION_JSON})
  public Publisher<GasStationPrices> getPrice(@QueryValue("placeId") Set<Integer> places) {
    return service.getPrices(places);
  }
}

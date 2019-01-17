package org.community.gas.stations.port.adapter.http.prices;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxStreamingHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.community.gas.stations.domain.model.locations.GasStation;
import org.community.gas.stations.domain.model.price.GasPriceService;
import org.community.gas.stations.domain.model.price.GasStationPrices;

@Singleton
public class HttpGasPriceService implements GasPriceService {

  @Client("prices")
  @Inject
  private RxStreamingHttpClient httpClient;

  @Override
  public Flowable<GasStationPrices> getPrices(List<GasStation> stations) {
    return httpClient.jsonStream(
        HttpRequest.GET(getUrl(stations)).accept(MediaType.APPLICATION_JSON_STREAM_TYPE),
        GasStationPrices.class);
  }

  private String getUrl(List<GasStation> stations) {
    return "/api/v1/prices?" + stations.stream()
        .map(gasStation -> "placeId=" + gasStation.getId())
        .collect(Collectors.joining("&"));
  }

}

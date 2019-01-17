package org.community.gas.stations.port.adapter.http.maps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.community.gas.stations.domain.model.locations.GasStation;
import org.community.gas.stations.domain.model.locations.GasStation.Location;
import org.community.gas.stations.domain.model.map.Destination;
import org.community.gas.stations.domain.model.map.MapService;

@Singleton
public class GoogleMapsService implements MapService {

  @Client("maps")
  @Inject
  RxHttpClient httpClient;

  @Value("${app.maps.api-key}")
  private String apiKey;

  @Override
  public Flowable<Destination> getDistance(List<GasStation> gasStations, Location origin) {
    return httpClient.retrieve(HttpRequest.GET(getUrl(gasStations, origin)), JsonNode.class)
        .flatMap(this::toList);
  }

  private Flowable<Destination> toList(JsonNode node) {
    if (!"OK".equals(node.get("status").asText())) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
    ArrayNode addresses = (ArrayNode) node.get("destination_addresses");
    String origin = node.get("origin_addresses").get(0).asText();
    ArrayNode rows = (ArrayNode) node.get("rows").get(0).get("elements");
    return Flowable.fromIterable(mergeData(addresses, rows, origin));
  }

  private List<Destination> mergeData(ArrayNode addresses, ArrayNode rows, String origin) {
    return IntStream.range(0, addresses.size())
        .mapToObj(index -> toDistance(addresses.get(index), rows.get(index), origin))
        .collect(Collectors.toList());
  }

  private Destination toDistance(JsonNode address, JsonNode data, String origin) {
    JsonNode distance = data.get("distance");
    JsonNode duration = data.get("duration");
    return new Destination(origin, address.asText(), duration.get("text").asText(),
        distance.get("text").asText(), duration.get("value").asInt(),
        distance.get("value").asInt());
  }

  private String getUrl(List<GasStation> gasStations, Location origin) {
    StringBuilder url = new StringBuilder("/maps/api/distancematrix/json");
    String destinations = gasStations.stream()
        .map(gasStation -> gasStation.getLocation().getLatitude() + "," + gasStation.getLocation()
            .getLongitude())
        .collect(Collectors.joining("|"));
    try {
      return url.append("?origins=").append(origin.getLatitude()).append(",")
          .append(origin.getLongitude())
          .append("&destinations=").append(URLEncoder.encode(destinations, "UTF-8"))
          .append("&key=").append(apiKey).toString();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return "";
    }
  }
}

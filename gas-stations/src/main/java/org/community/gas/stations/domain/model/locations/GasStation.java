package org.community.gas.stations.domain.model.locations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.community.gas.stations.domain.model.map.Destination;
import org.community.gas.stations.domain.model.price.GasStationPrices;

public class GasStation {

  private Integer id;
  private String name;
  private String address;
  private String category;
  private String brand;
  private Location location;

  private GasStationPrices prices;
  private Destination destination;

  public static class Location {

    private double longitude;
    private double latitude;

    public Location() {
    }

    public Location(double longitude, double latitude) {
      this.longitude = longitude;
      this.latitude = latitude;
    }

    public double getLongitude() {
      return longitude;
    }

    public void setLongitude(double longitude) {
      this.longitude = longitude;
    }

    public double getLatitude() {
      return latitude;
    }

    public void setLatitude(double latitude) {
      this.latitude = latitude;
    }
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Location getLocation() {
    return location;
  }

  private void setLocation(Location location) {
    this.location = location;
  }

  @SuppressWarnings("unchecked")
  @JsonProperty("location")
  private void unpackNested(Map<String, Object> node) {
    Map<String, Object> position = (Map<String, Object>) node.get("position");
    List<Float> values = (List<Float>) position.get("values");
    setLocation(new Location(values.get(0).doubleValue(), values.get(1).doubleValue()));
  }


  public GasStationPrices getPrices() {
    return prices;
  }

  public void setPrices(GasStationPrices prices) {
    this.prices = prices;
  }

  public Destination getDestination() {
    return destination;
  }

  public void setDestination(Destination destination) {
    this.destination = destination;
  }
}

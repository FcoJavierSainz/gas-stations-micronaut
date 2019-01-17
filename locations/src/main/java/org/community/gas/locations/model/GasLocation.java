package org.community.gas.locations.model;

import com.mongodb.client.model.geojson.Point;

public class GasLocation {

  public GasLocation() {
  }

  public GasLocation(
      long id,
      String name,
      String address,
      String category,
      String brand,
      Point location) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.category = category;
    this.brand = brand;
    this.location = location;
  }

  private long id;
  private String name;
  private String address;
  private String category;
  private String brand;
  private Point location;

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public Point getLocation() {
    return location;
  }

  public void setLocation(Point location) {
    this.location = location;
  }
}

package org.community.gas.locations.application;

public class GetGasStationsCommand {

  private double longitude;
  private double latitude;
  private int radio;

  public GetGasStationsCommand(double longitude, double latitude, int radio) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.radio = radio;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public int getRadio() {
    return radio;
  }
}

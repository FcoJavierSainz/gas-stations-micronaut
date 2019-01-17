package org.community.gas.stations.domain.model.map;

public class Destination {

  private String originAddress;
  private String destinationAddress;
  private String durationText;
  private String distanceText;
  private int duration;
  private int distance;

  public Destination(String originAddress, String destinationAddress, String durationText,
      String distanceText, int duration, int distance) {
    this.originAddress = originAddress;
    this.destinationAddress = destinationAddress;
    this.durationText = durationText;
    this.distanceText = distanceText;
    this.duration = duration;
    this.distance = distance;
  }

  public String getOriginAddress() {
    return originAddress;
  }

  public void setOriginAddress(String originAddress) {
    this.originAddress = originAddress;
  }

  public String getDestinationAddress() {
    return destinationAddress;
  }

  public void setDestinationAddress(String destinationAddress) {
    this.destinationAddress = destinationAddress;
  }

  public String getDurationText() {
    return durationText;
  }

  public void setDurationText(String durationText) {
    this.durationText = durationText;
  }

  public String getDistanceText() {
    return distanceText;
  }

  public void setDistanceText(String distanceText) {
    this.distanceText = distanceText;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) {
    this.distance = distance;
  }
}

package org.community.gas.stations.domain.model.price;

import java.time.Instant;

public class GasStationPrices {

  private int placeId;
  private Double regularPrice;
  private Instant regularUpdatedAt;
  private Double premiumPrice;
  private Instant premiumUpdatedAt;
  private Double dieselPrice;
  private Instant dieselUpdatedAt;

  public int getPlaceId() {
    return placeId;
  }

  public void setPlaceId(int placeId) {
    this.placeId = placeId;
  }

  public Double getRegularPrice() {
    return regularPrice;
  }

  public void setRegularPrice(Double regularPrice) {
    this.regularPrice = regularPrice;
  }

  public Instant getRegularUpdatedAt() {
    return regularUpdatedAt;
  }

  public void setRegularUpdatedAt(Instant regularUpdatedAt) {
    this.regularUpdatedAt = regularUpdatedAt;
  }

  public Double getPremiumPrice() {
    return premiumPrice;
  }

  public void setPremiumPrice(Double premiumPrice) {
    this.premiumPrice = premiumPrice;
  }

  public Instant getPremiumUpdatedAt() {
    return premiumUpdatedAt;
  }

  public void setPremiumUpdatedAt(Instant premiumUpdatedAt) {
    this.premiumUpdatedAt = premiumUpdatedAt;
  }

  public Double getDieselPrice() {
    return dieselPrice;
  }

  public void setDieselPrice(Double dieselPrice) {
    this.dieselPrice = dieselPrice;
  }

  public Instant getDieselUpdatedAt() {
    return dieselUpdatedAt;
  }

  public void setDieselUpdatedAt(Instant dieselUpdatedAt) {
    this.dieselUpdatedAt = dieselUpdatedAt;
  }
}
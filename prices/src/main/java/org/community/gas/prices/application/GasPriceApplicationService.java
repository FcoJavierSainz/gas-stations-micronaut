package org.community.gas.prices.application;

import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.community.gas.prices.model.station.GasPriceRepository;
import org.community.gas.prices.model.station.GasStationPrices;
import org.reactivestreams.Publisher;

@Singleton
public class GasPriceApplicationService {

  @Inject
  private GasPriceRepository repository;

  public Publisher<GasStationPrices> getPrices(Set<Integer> places) {
    return repository.getPrices(places);
  }
}

package org.community.gas.prices.model.station;

import java.util.Set;
import org.reactivestreams.Publisher;

public interface GasPriceRepository {

  Publisher<GasStationPrices> getPrices(Set<Integer> places);
}

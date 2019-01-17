package org.community.gas.stations.domain.model.price;

import io.reactivex.Flowable;
import java.util.List;
import org.community.gas.stations.domain.model.locations.GasStation;


public interface GasPriceService {

  Flowable<GasStationPrices> getPrices(List<GasStation> places);
}

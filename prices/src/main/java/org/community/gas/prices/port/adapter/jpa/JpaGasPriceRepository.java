package org.community.gas.prices.port.adapter.jpa;

import com.ibm.reactive.jpa.Database;
import io.micronaut.retry.annotation.CircuitBreaker;
import io.micronaut.retry.annotation.Retryable;
import java.sql.SQLTransientException;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.community.gas.prices.model.station.GasPriceRepository;
import org.community.gas.prices.model.station.GasStationPrices;
import org.reactivestreams.Publisher;

@Singleton
@CircuitBreaker
//@Parallel
public class JpaGasPriceRepository implements GasPriceRepository {

  private Database database;

  @Inject
  public JpaGasPriceRepository(Database database) {
    this.database = database;
  }

  private static final String query = "from GasStationPrices where placeId "
      + "in (:places) ORDER BY placeId";

  @Retryable(SQLTransientException.class)
  @CircuitBreaker
  @Override
  public Publisher<GasStationPrices> getPrices(Set<Integer> places) {
    return database.stream(query, GasStationPrices.class)
        .addParameter("places", places)
        .fetchSize(10)
        .flux();
  }
}

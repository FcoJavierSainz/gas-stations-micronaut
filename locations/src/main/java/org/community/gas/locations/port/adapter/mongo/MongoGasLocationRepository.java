package org.community.gas.locations.port.adapter.mongo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.community.gas.locations.application.GetGasStationsCommand;
import org.community.gas.locations.model.GasLocation;
import org.community.gas.locations.model.GasLocationRepository;
import org.reactivestreams.Publisher;

public class MongoGasLocationRepository implements GasLocationRepository {

  @Inject
  MongoClient client;

  MongoCollection<GasLocation> collection;

  @PostConstruct
  private void initDatabase() {
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    collection = client.getDatabase("gasStations").withCodecRegistry(pojoCodecRegistry)
        .getCollection("places", GasLocation.class);
  }

  public Publisher<GasLocation> findGasStations(GetGasStationsCommand command) {
    return collection.find(createQuery(command)).sort(Sorts.ascending("_id"));
  }

  private Bson createQuery(GetGasStationsCommand command) {
    Point point = new Point(new Position(command.getLongitude(), command.getLatitude()));
    return Filters.near("location", point, (double) command.getRadio(), 0.0);
  }

}

package org.community.gas.stations.resources.metrics;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micronaut.management.endpoint.annotation.Endpoint;
import io.micronaut.management.endpoint.annotation.Read;
import javax.inject.Inject;

@Endpoint(id = "prometheus", value = "/prometheus", defaultSensitive = false)
public class PrometheusResource {

  @Inject
  PrometheusMeterRegistry prometheusMeterRegistry;

  @Read
  public String scrape() {
    return prometheusMeterRegistry.scrape();
  }

}
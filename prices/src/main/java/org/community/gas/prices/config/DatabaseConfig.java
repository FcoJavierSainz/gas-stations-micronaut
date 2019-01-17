package org.community.gas.prices.config;

import com.ibm.reactive.jpa.Database;
import com.ibm.reactive.jpa.PoolConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.jpa.HibernateMetrics;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Parallel;
import io.micronaut.context.annotation.Value;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.hikaricp.internal.HikariCPConnectionProvider;

@Factory
public class DatabaseConfig {

  @Value("${db.uri}")
  private String dbUri;

  private ApplicationContext applicationContext;

  /**
   * Default constructor.
   *
   * @param applicationContext The application context
   */
  public DatabaseConfig(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
  @Singleton
  Database database() {
    HashMap<String, String> settings = new HashMap<>();
    settings.put("hibernate.connection.url", dbUri);
    //settings.put("hibernate.show_sql", "true");

    List<String> packages = Collections.singletonList("org.community.gas.prices.model.station");
    PoolConfiguration configuration = PoolConfiguration.builder().maxPoolSize(10).build();
    return new Database(settings, packages, configuration);
  }

  //@Bean
  //@Parallel
  HikariDataSource registerHikariMetrics(Database database) {
    HikariCPConnectionProvider cp = (HikariCPConnectionProvider) database.getSessionFactory()
        .getSessionFactoryOptions().getServiceRegistry().
            getService(ConnectionProvider.class);
    HikariDataSource dataSource = cp.unwrap(HikariDataSource.class);
    MeterRegistry meterRegistry = this.applicationContext.getBean(MeterRegistry.class);
    dataSource.setMetricRegistry(meterRegistry);
    return dataSource;
  }

  //@Bean
  HibernateMetrics metrics(Database database) {
    return new HibernateMetrics(database.getSessionFactory(), "mysql", Tags.empty());
  }

}

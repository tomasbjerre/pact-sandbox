package se.bjurr.pactsandbox.consumerwiremock.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import se.bjurr.pactsandbox.consumerwiremock.integration.clientfactory.RestEasyClientFactory;
import se.bjurr.pactsandbox.provider.api.IntegrationAPI;
import se.bjurr.pactsandbox.provider.api.model.AnimalsIntegrationVO;

@Repository
public class IntegrationDelegatingService {
  private final IntegrationAPI api;

  public IntegrationDelegatingService(@Value("${basepath}") final String basePath) {
    this.api = RestEasyClientFactory.create(IntegrationAPI.class, basePath);
  }

  public AnimalsIntegrationVO getAnimals() {
    return this.api.getAnimals();
  }
}

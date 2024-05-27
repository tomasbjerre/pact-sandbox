package se.bjurr.pactsandbox.consumerwiremock.logic;

import org.springframework.stereotype.Service;
import se.bjurr.pactsandbox.consumerwiremock.integration.IntegrationDelegatingService;
import se.bjurr.pactsandbox.consumerwiremock.integration.api.model.AnimalIntegrationVO;
import se.bjurr.pactsandbox.consumerwiremock.integration.api.model.AnimalsIntegrationVO;

@Service
public class AnimalsLogicDelegatingService {

  private final IntegrationDelegatingService integrationService;

  public AnimalsLogicDelegatingService(final IntegrationDelegatingService integrationService) {
    this.integrationService = integrationService;
  }

  public AnimalsIntegrationVO getAnimals() {
    return this.integrationService.getAnimals();
  }

  public AnimalIntegrationVO getAnimal(final String id) {
    return this.integrationService.getAnimal(id);
  }

  public void postAnimals(final AnimalsIntegrationVO animals) {
    this.integrationService.postAnimals(animals);
  }

  public void postAnimal(final AnimalIntegrationVO animal) {
    this.integrationService.postAnimal(animal);
  }
}

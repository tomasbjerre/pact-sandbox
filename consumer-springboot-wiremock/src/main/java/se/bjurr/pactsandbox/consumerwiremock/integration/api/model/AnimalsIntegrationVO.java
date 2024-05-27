package se.bjurr.pactsandbox.consumerwiremock.integration.api.model;

import java.util.List;

public class AnimalsIntegrationVO {
  private final List<AnimalIntegrationVO> animals;

  public AnimalsIntegrationVO() {
    this.animals = null;
  }

  public AnimalsIntegrationVO(final List<AnimalIntegrationVO> animals) {
    this.animals = animals;
  }

  public List<AnimalIntegrationVO> getAnimals() {
    return this.animals;
  }
}

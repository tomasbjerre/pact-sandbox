package se.bjurr.pactsandbox.consumerpactjvm.logic;

import org.springframework.stereotype.Service;
import se.bjurr.pactsandbox.consumerpactjvm.integration.IntegrationDelegatingService;
import se.bjurr.pactsandbox.provider.api.model.AnimalsIntegrationVO;

@Service
public class AnimalsLogicDelegatingService {

  private final IntegrationDelegatingService integrationService;

  public AnimalsLogicDelegatingService(final IntegrationDelegatingService integrationService) {
    this.integrationService = integrationService;
  }

  public AnimalsIntegrationVO getAnimals() {
    return this.integrationService.getAnimals();
  }
}

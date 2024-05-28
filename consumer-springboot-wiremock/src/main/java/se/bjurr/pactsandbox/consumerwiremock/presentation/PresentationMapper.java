package se.bjurr.pactsandbox.consumerwiremock.presentation;

import java.util.List;

import org.springframework.stereotype.Service;

import se.bjurr.pactsandbox.consumerwiremock.presentation.model.AnimalDTO;
import se.bjurr.pactsandbox.consumerwiremock.presentation.model.AnimalsDTO;
import se.bjurr.pactsandbox.provider.api.model.AnimalIntegrationVO;
import se.bjurr.pactsandbox.provider.api.model.AnimalsIntegrationVO;

@Service
public class PresentationMapper {

  public AnimalsDTO toAnimalsDTO(final AnimalsIntegrationVO animals) {
    final List<AnimalDTO> toAnimals =
        animals.getAnimals().stream().map(it -> this.toAnimalDTO(it)).toList();
    return new AnimalsDTO(toAnimals);
  }

  public AnimalDTO toAnimalDTO(final AnimalIntegrationVO it) {
    return new AnimalDTO(it.getId(), it.getName());
  }
}

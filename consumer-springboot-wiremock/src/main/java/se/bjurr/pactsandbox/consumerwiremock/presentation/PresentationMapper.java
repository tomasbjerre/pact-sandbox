package se.bjurr.pactsandbox.consumerwiremock.presentation;

import java.util.List;
import org.springframework.stereotype.Service;
import se.bjurr.pactsandbox.consumerwiremock.integration.api.model.AnimalIntegrationVO;
import se.bjurr.pactsandbox.consumerwiremock.integration.api.model.AnimalsIntegrationVO;
import se.bjurr.pactsandbox.consumerwiremock.presentation.model.AnimalDTO;
import se.bjurr.pactsandbox.consumerwiremock.presentation.model.AnimalsDTO;

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

  public AnimalsIntegrationVO toAnimals(final AnimalsDTO animals) {
    final List<AnimalIntegrationVO> animalsList =
        animals.getAnimals().stream().map(it -> this.toAnimal("0", it)).toList();
    return new AnimalsIntegrationVO(animalsList);
  }

  public AnimalIntegrationVO toAnimal(final String id, final AnimalDTO animal) {
    return new AnimalIntegrationVO(id, animal.getName());
  }
}

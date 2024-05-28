package se.bjurr.pactsandbox.consumerpactjvm.testcases;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import se.bjurr.pactsandbox.consumerpactjvm.integration.IntegrationDelegatingService;

@ExtendWith(PactConsumerTestExt.class)
public class GetAnimalsTest {

  public IntegrationDelegatingService integrationLayer;

  @BeforeEach
  public void before() {
    System.setProperty("pact.rootDir", "src/test/resources/pact-json");
  }

  @Pact(provider = "PactSandboxProvider", consumer = "PactSandboxPactjvmConsumer")
  public V4Pact getAnimalsPact(final PactDslWithProvider builder) {
    return builder
        .uponReceiving("a request to get animals")
        .path("/animals")
        .method("GET")
        .willRespondWith()
        .status(202)
        .headers(Map.of("Content-Type", "application/json"))
        .body(
            """
{
 "animals": [
   {
     "id": "1",
     "name": "Zack"
   },
   {
     "id": "2",
     "name": "Alfons"
   }
 ]
}
""")
        .toPact(V4Pact.class);
  }

  @Test
  @PactTestFor(pactMethod = "getAnimalsPact")
  public void testGetAnimal(final MockServer mockServer) throws Exception {
    this.integrationLayer = new IntegrationDelegatingService(mockServer.getUrl());

    assertThat(this.toJson(this.integrationLayer.getAnimals()))
        .isEqualToIgnoringNewLines(
            """
{
  "animals" : [ {
    "id" : "1",
    "name" : "Zack"
  }, {
    "id" : "2",
    "name" : "Alfons"
  } ]
}
	""");
  }

  private String toJson(final Object it) throws JsonProcessingException {
    return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(it);
  }
}

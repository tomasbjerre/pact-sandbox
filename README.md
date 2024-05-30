# Pact Sandbox

This repository is intended as a sandbox to help fiddle with [Pact](https://docs.pact.io/).

It contains:

- `client-factory` - A client factory with [RestEasy](https://resteasy.dev/).
- `provider-api` - A JAXRS API.
- `consumer-springboot` - Consumer with SpringBoot and API mocked with [Pact JVM](https://docs.pact.io/implementation_guides/jvm).
- `consumer-springboot-wiremock` - Consumer with SpringBoot and API mocked with [wiremock-pact](https://github.com/wiremock/wiremock-pact).
- `provider-springboot` - Provider with SpringBoot and provider-test with [Pact JVM](https://docs.pact.io/implementation_guides/jvm).

Build it with `./gradlew build`. Needs Java 17, get that with [SDKMAN](https://sdkman.io/).

## Upload Pacts

There are some alternatives for publishing Pacts here: <https://docs.pact.io/pact_broker/publishing_and_retrieving_pacts>

I made [a script using `curl`](https://github.com/tomasbjerre/pactflow-publish-sh), it can be used like this:

```sh
 current_version=$(npx git-changelog-command-line \
  --patch-version-pattern "^fix.*" \
  --print-current-version)
 git_hash=`git rev-parse --short HEAD`
 participant_version_number="$current_version-$git_hash"

 npx pactflow-publish-sh \
 --username=dXfltyFMgNOFZAxr8io9wJ37iUpY42M \
 --password=O5AIZWxelWbLvqMd8PkAVycBJh2Psyg1 \
 --pactflow-broker-url=https://test.pactflow.io/contracts/publish \
 --build-url=https://github.com/tomasbjerre/pact-sandbox \
 --pact-json-folder=consumer-springboot-wiremock/src/test/resources/pact-json \
 --participant-version-number=$participant_version_number
```

After this command, you can see the published pact here: <https://test.pactflow.io/>

## Consumer SpringBoot WireMock

![Pact With WireMock](/docs/pact-with-wiremock.png)

## Consumer SpringBoot Pact JVM

![Pact With Pact JVM](/docs/pact-with-pactjvm.png)

## Provider SpringBoot Pact JVM

![Provider With Pact JVM](/docs/pact-provider.png)

## Topics of discussion

- What is the value of doing contract testing?
  - You get contracts
  - You get a mock
  - It becomes easier to know which consumers, and providers, exist. Also how they depend on each other.
  - ...

- What if I already have a mock (like WireMock) and formal API specifications (like OpenAPI)?
  - What features in the API:s are being used?
  - Are all the attributes in each JSON object neccessary?
  - ...

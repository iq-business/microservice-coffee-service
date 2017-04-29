package net.iqbusiness.masterclass.webservices;

import net.iqbusiness.masterclass.model.Coffee;
import net.iqbusiness.masterclass.model.RouteConfig;
import net.iqbusiness.masterclass.repository.CoffeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abawa on 2017/04/17.
 */

@RestController
@RequestMapping("/api/coffee")
public class CoffeeMakerWebService {

    private static final Logger log = LoggerFactory.getLogger(CoffeeMakerWebService.class);

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Value("${service.registry.enabled:false}")
    private boolean serviceRegistryEnabled;

    @Value("${service.registry.baseUrl:http://localhost:9090}")
    private String serviceRegistryBaseUrl;

    @Value("${coffee.service.baseUrl:http://localhost:9292}")
    private String coffeeServiceBaseUrl;

    @PostConstruct
    private void doServiceRegistration() {
        log.info("\n\n\t >> ServiceRegistryBaseUrl = [{}]  - service.registry.enabled [{}] - CoffeeServiceBaseUrl = [{}]<< \n\n",
                serviceRegistryBaseUrl,
                serviceRegistryEnabled,
                coffeeServiceBaseUrl);

        if (serviceRegistryEnabled) {

            // Dynamically Registering with Service Registry and API Gateway
            UriComponentsBuilder registerUrlBuilder = UriComponentsBuilder.fromUriString(serviceRegistryBaseUrl)
                    .path("/gateway/config/coffee")
                    .queryParam("serviceName", "Dynamic Coffee Service 3.0")
                    .queryParam("serviceUrl", String.format("%s/api/coffee", coffeeServiceBaseUrl));
            log.info("\n\n\t >> Registering Service to URL = [{}]  << \n\n", registerUrlBuilder.build().toUri());
            RouteConfig newCoffeeRouteConfig = new RestTemplate().postForObject(registerUrlBuilder.build().toUri(),
                    null,
                    RouteConfig.class
            );
            log.info("\n\n\t >> Added New RouteConfig Dynamically = [{}]  << \n\n", newCoffeeRouteConfig);

            // Dynamically Activating Routing with API Gateway
            UriComponentsBuilder activateUrlBuilder = UriComponentsBuilder.fromUriString(serviceRegistryBaseUrl)
                    .path("/gateway/config/coffee/active/{configId}");
            Map<String, Long> uriParams = new HashMap<String, Long>();
            uriParams.put("configId", newCoffeeRouteConfig.getId());
            log.info("\n\n\t >> Activating Route with URL = [{}]  << \n\n", activateUrlBuilder.buildAndExpand(uriParams).toUri());
            new RestTemplate().postForEntity(activateUrlBuilder.buildAndExpand(uriParams).toUri(), null, String.class);
            log.info("\n\n\t >> Activating New RouteConfig Dynamically << \n\n");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Coffee makeCoffee() {
        log.info("\n\n\t>>> Request to makeCoffee() >>>\n");
        Coffee coffee = coffeeRepository.getCoffee();
        log.info("\n\n\t<<< Response to makeCoffee(): {} <<<\n", coffee);
        return coffee;
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}

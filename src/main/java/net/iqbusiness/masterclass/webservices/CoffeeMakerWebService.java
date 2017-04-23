package net.iqbusiness.masterclass.webservices;

import net.iqbusiness.masterclass.model.Coffee;
import net.iqbusiness.masterclass.repository.CoffeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by abawa on 2017/04/17.
 */

@RestController
@RequestMapping("/api/coffee")
public class CoffeeMakerWebService {

    private static final Logger log = LoggerFactory.getLogger(CoffeeMakerWebService.class);

    @Autowired
    private CoffeeRepository coffeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Coffee makeCoffee() {
        log.info("\n\n\t>>> Request to makeCoffee() >>>\n");
        Coffee coffee = coffeeRepository.getCoffee();
        log.info("\n\n\t<<< Response to makeCoffee(): {} <<<\n", coffee);
        return coffee;
    }
}

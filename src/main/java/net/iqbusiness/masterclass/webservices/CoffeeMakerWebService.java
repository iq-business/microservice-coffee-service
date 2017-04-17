package net.iqbusiness.masterclass.webservices;

import net.iqbusiness.masterclass.model.Coffee;
import net.iqbusiness.masterclass.repository.CoffeeRepository;
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

    @Autowired
    private CoffeeRepository coffeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Coffee makeCoffee() {
            return coffeeRepository.getCoffee();
    }
}

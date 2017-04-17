package net.iqbusiness.masterclass.repository;

import net.iqbusiness.masterclass.model.Coffee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by abawa on 2017/04/17.
 */
@Repository
public class CoffeeRepository {

    public static Coffee getCoffee() {
        return new Coffee("Tatooine Dark Roast", new BigDecimal("23.50"));
    }
}

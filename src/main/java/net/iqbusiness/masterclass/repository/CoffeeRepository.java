package net.iqbusiness.masterclass.repository;

import net.iqbusiness.masterclass.model.Coffee;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by abawa on 2017/04/17.
 */
@Repository
public class CoffeeRepository {

    private static final int maximumBeveragePrice = 30;
    private static final int minimumBeveragePrice = 10;

    public static Coffee getCoffee() {
        return new Coffee("Tatooine Dark Roast", CoffeeRepository.getRandomPrice());
    }

    private static BigDecimal getRandomPrice() {
        return new BigDecimal((new Random().nextInt(maximumBeveragePrice * 10 - minimumBeveragePrice  * 10 + 1) + minimumBeveragePrice  * 10) / 10);
    }
}

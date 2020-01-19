package kata.supermarket.discounts;

import kata.supermarket.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static kata.supermarket.TestedItems.aPintOfMilk;
import static org.junit.jupiter.api.Assertions.*;

class BuyOneGetOneFreeDiscountTest {

    @DisplayName("Buy 1 get 1 free discount provides correct discount for...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountIsCorrectlyCalculated(String description, String expectedDiscount, Item item) {
        final BuyOneGetOneFreeDiscount discount = new BuyOneGetOneFreeDiscount(item);
        assertEquals(new BigDecimal(expectedDiscount), discount.applyDiscount());
    }

    static Stream<Arguments> discountIsCorrectlyCalculated() {
        return Stream.of(
                Arguments.of("no items", "0", null),
               Arguments.of("one item by unit", "0.49", aPintOfMilk())
        );
    }
}
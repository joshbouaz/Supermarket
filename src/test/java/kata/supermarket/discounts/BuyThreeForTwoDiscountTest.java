package kata.supermarket.discounts;

import kata.supermarket.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static kata.supermarket.TestedItems.aPackOfDigestives;
import static kata.supermarket.TestedItems.aPintOfMilk;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyThreeForTwoDiscountTest {

    @DisplayName("Buy 3 for 2 discount provides correct discount for...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountIsCorrectlyCalculated(String description, String expectedDiscount, List<Item> items) {
        final BuyThreeForTwoDiscount discount = new BuyThreeForTwoDiscount(items);
        assertEquals(new BigDecimal(expectedDiscount), discount.applyDiscount());
    }

    static Stream<Arguments> discountIsCorrectlyCalculated() {
        return Stream.of(
                Arguments.of("no items", "0", null),
               Arguments.of("2 expensive and 1 cheap items", "0.49", Arrays.asList(aPackOfDigestives(), aPintOfMilk(), aPackOfDigestives()))
        );
    }
}
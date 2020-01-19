package kata.supermarket.discounts;

import kata.supermarket.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static kata.supermarket.TestedItems.aPackOfDigestives;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoForOnePoundDiscountTest {

    @DisplayName("2 for £1 discount provides correct discount for...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void discountIsCorrectlyCalculated(String description, String expectedDiscount, Item item1, Item item2) {
        final TwoForOnePoundDiscount discount = new TwoForOnePoundDiscount(item1, item2);
        assertEquals(new BigDecimal(expectedDiscount), discount.applyDiscount());
    }

    static Stream<Arguments> discountIsCorrectlyCalculated() {
        return Stream.of(
                Arguments.of("no items", "0", null, null),
               Arguments.of("two item by unit", "2.10", aPackOfDigestives(), aPackOfDigestives())
        );
    }
}
package kata.supermarket;

import kata.supermarket.discounts.Discount;
import kata.supermarket.discounts.BuyOneGetOneFreeDiscount;
import kata.supermarket.discounts.TwoForOnePoundDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static kata.supermarket.TestedItems.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalDiscountedValue(String description, String expectedTotal, Iterable<Item> items, Iterable<Discount> discounts) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        discounts.forEach(basket::addDiscount);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalDiscountedValue() {
        return Stream.of(
                buyOneGetOneFreeDiscountedItems(),
                twoForOnePoundDiscountedItems()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments buyOneGetOneFreeDiscountedItems() {
        return Arguments.of("multiple items with Buy 1 get 1 free discount", "0.49",
                Arrays.asList(aPintOfMilk(), aPintOfMilk()), Arrays.asList(new BuyOneGetOneFreeDiscount(aPintOfMilk())));
    }
    private static Arguments twoForOnePoundDiscountedItems() {
        return Arguments.of("two items for £1 discount", "1.00",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives()), Arrays.asList(new TwoForOnePoundDiscount(aPackOfDigestives(), aPackOfDigestives())));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }


}
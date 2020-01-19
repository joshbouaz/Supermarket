package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class BuyThreeForTwoDiscount implements Discount {

    private final List<Item> discountedItems;

    public BuyThreeForTwoDiscount(List<Item> discountedItems) {
        this.discountedItems = discountedItems;
    }

    @Override
    public BigDecimal applyDiscount() {
        if (discountedItems == null || discountedItems.size() != 3) return BigDecimal.ZERO;
        discountedItems.sort(Comparator.comparing(Item :: price).reversed());
        return discountedItems.get(2).price();
    }
}

package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;

public class TwoForOneDiscount implements Discount {

    private final Item discountedItem;

    public TwoForOneDiscount(Item discountedItem) {
        this.discountedItem = discountedItem;
    }

    @Override
    public BigDecimal applyDiscount() {
        if (discountedItem == null) return BigDecimal.ZERO;
        return discountedItem.price();
    }
}

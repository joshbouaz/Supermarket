package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;

public class BuyOneGetOneFreeDiscount implements Discount {

    private final Item discountedItem;

    public BuyOneGetOneFreeDiscount(Item discountedItem) {
        this.discountedItem = discountedItem;
    }

    @Override
    public BigDecimal applyDiscount() {
        if (discountedItem == null) return BigDecimal.ZERO;
        return discountedItem.price();
    }
}

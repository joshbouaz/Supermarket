package kata.supermarket.discounts;

import kata.supermarket.Item;

import java.math.BigDecimal;

public class TwoForOnePoundDiscount implements Discount {

    private final Item item1;
    private final Item item2;

    public TwoForOnePoundDiscount(Item item1, Item item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    public BigDecimal applyDiscount() {
        if(item1 != null && item2 != null){
            BigDecimal total = item1.price().add(item2.price());
            return total.subtract(BigDecimal.ONE);
        }
        return BigDecimal.ZERO;
    }
}

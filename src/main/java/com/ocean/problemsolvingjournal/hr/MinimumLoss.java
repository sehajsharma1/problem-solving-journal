package hr.code.java;

import java.util.List;
import java.util.TreeSet;

/*Lauren has a chart of distinct projected prices for a house over the next several years. She must buy the house in one year and sell it in another, and she must do so at a loss. She wants to minimize her financial loss.*/
public class MinimumLoss {
    public static void main(String[] args) {
        minimumLoss(List.of(20l, 7l, 8l, 2l, 5l));
    }
    public static long minimumLoss(List<Long> price) {
        TreeSet<Long> priceSet = new TreeSet<>();
        Long min = Long.MAX_VALUE;
        for (int i = 0; i < price.size(); i++) {
            Long higher = priceSet.higher(price.get(i));
            if (higher != null) {
                min = Math.min(higher - price.get(i), min);
            }
            priceSet.add(price.get(i));
        }
        return min.intValue();
    }
}

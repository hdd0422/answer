import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShoppingCart
 * @Author Mr.Han
 * @create 2023/3/27 20:35
 */
public class ShoppingCart {
    private HashMap<String, Double> products; // key: product name, value: unit price
    private HashMap<String, Double> discounts; // key: promotion date, value: discount
    private HashMap<String, Integer> numbers; // key: product name, value: number
    private Coupon coupon;


    public ShoppingCart() {
        products = new HashMap<>();
        discounts = new HashMap<>();
        numbers = new HashMap<>();
    }


    public void addProduct(String productName, double unitPrice) {
        products.put(productName, unitPrice);
        numbers.put(productName, 0);
    }


    public void addDiscount(String promotionDate, double discount) {
        discounts.put(promotionDate, discount);
    }


    public void addCoupon(String expirationDate, int minPrice, int discount) {
        coupon = new Coupon(expirationDate, minPrice, discount);
    }


    public void addProductNumber(String productName, int number) {
        numbers.put(productName, number);
    }


    public double calculatePrice(String settlementDate) {
        double totalPrice = 0;
        double discount = 1;
        // Calculate the total price and discount of the shopping cart
        for (Map.Entry<String, Double> product : products.entrySet()) {
            String productName = product.getKey();
            double unitPrice = product.getValue();
            int number = numbers.get(productName);
            totalPrice += unitPrice * number;
            // Calculate the discount if there is a promotion
            if (discounts.containsKey(settlementDate)) {
                discount *= discounts.get(settlementDate);
            }
        }
        // Calculate the discount if there is a coupon
        if (coupon != null && totalPrice >= coupon.minPrice) {
            discount *= (1 - coupon.discount / 100.0);
        }
        return totalPrice * discount;
    }


    private class Coupon {
        String expirationDate;
        int minPrice;
        int discount;


        public Coupon(String expirationDate, int minPrice, int discount) {
            this.expirationDate = expirationDate;
            this.minPrice = minPrice;
            this.discount = discount;
        }
    }


    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct("ipad", 2399.00);
        cart.addProduct("显示器", 1799.00);
        cart.addProduct("啤酒", 25.00);
        cart.addProduct("面包", 9.00);
        cart.addDiscount("2013.11.11", 0.7);
        cart.addCoupon("2014.3.21", 1000, 200);
        cart.addProductNumber("ipad", 1);
        cart.addProductNumber("显示器", 1);
        cart.addProductNumber("啤酒", 12);
        cart.addProductNumber("面包", 5);


        double price = cart.calculatePrice("2013.11.11");
        System.out.println(String.format("%.2f", price));
    }
}


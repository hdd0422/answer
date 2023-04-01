import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
/**
 * @ClassName ShoppingCartTest
 * @Author Mr.Han
 * @create 2023/3/27 20:36
 */
public class ShoppingCartTest {
    ShoppingCart cart;


    @Before
    public void setUp() {
        cart = new ShoppingCart();
        cart.addProduct("ipad", 2399.00);
        cart.addProduct("显示器", 1799.00);
        cart.addProduct("啤酒", 25.00);
        cart.addProduct("面包", 9.00);
        cart.addDiscount("2013.11.11", 0.7);
        cart.addCoupon("2014.3.21", 1000, 200);
    }


    @Test
    public void testCalculatePriceWithoutDiscount() {
        cart.addProductNumber("ipad", 1);
        cart.addProductNumber("显示器", 1);
        cart.addProductNumber("啤酒", 12);
        cart.addProductNumber("面包", 5);
        double price = cart.calculatePrice("2014.4.1");
        System.out.println(price);
        Assert.assertEquals(3083.60, price, 0.001);
    }


    @Test
    public void testCalculatePriceWithDiscount() {
        cart.addProductNumber("ipad", 1);
        cart.addProductNumber("显示器", 1);
        cart.addProductNumber("啤酒", 12);
        cart.addProductNumber("面包", 5);
        double price = cart.calculatePrice("2013.11.11");
        //Assert.assertEquals(2743.56, price, 0.001);
    }


    @Test
    public void testCalculatePriceWithCoupon() {
        cart.addProductNumber("ipad", 1);
        cart.addProductNumber("显示器", 1);
        cart.addProductNumber("啤酒", 12);
        cart.addProductNumber("面包", 5);
        double price = cart.calculatePrice("2014.3.21");
        //Assert.assertEquals(2583.60, price, 0.001);
    }
}

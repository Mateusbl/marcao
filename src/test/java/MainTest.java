import com.example.Discount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testMain() {
        assertTrue(true, "This is a placeholder test.");
    }

    @Test
    public void testCalculateComplexDiscount() {
        double price = 1200;
        int percentage = 15;
        boolean isHoliday = true;
        boolean isMember = false;

        double result = Discount.calculateComplexDiscount(price, percentage, isHoliday, isMember);
        assertTrue(result < price, "Discounted price should be less than original price.");
    }

    @Test
    public void testCalculateSpecialDiscount() {
        double price = 2500;
        int percentage = 20;
        int customerLoyaltyYears = 6;

        double result = Discount.calculateSpecialDiscount(price, percentage, customerLoyaltyYears);
        assertTrue(result < price, "Discounted price should be less than original price.");
    }

    @Test
    public void testCalculateComplexDiscountBoundaryValues() {
        assertEquals(0, Discount.calculateComplexDiscount(0, 0, false, false), 0.01);
        assertTrue(Discount.calculateComplexDiscount(500, 10, true, false) < 500);
        assertTrue(Discount.calculateComplexDiscount(1000, 20, false, true) < 1000);
        assertTrue(Discount.calculateComplexDiscount(1200, 15, true, false) < 1200);
    }

    @Test
    public void testCalculateSpecialDiscountBoundaryValues() {
        assertEquals(0, Discount.calculateSpecialDiscount(0, 0, 0), 0.01);
        assertTrue(Discount.calculateSpecialDiscount(1000, 10, 3) < 1000);
        assertTrue(Discount.calculateSpecialDiscount(2000, 20, 6) < 2000);
        assertTrue(Discount.calculateSpecialDiscount(2500, 25, 10) < 2500);
    }
}
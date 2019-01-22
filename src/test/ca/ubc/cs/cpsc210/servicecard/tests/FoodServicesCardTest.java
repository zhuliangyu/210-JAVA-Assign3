package ca.ubc.cs.cpsc210.servicecard.tests;

import ca.ubc.cs.cpsc210.servicecard.model.FoodServicesCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// Unit tests for FoodServiceCard class
public class FoodServicesCardTest {
    // Hint: this constant should be useful!
    private static final int INITIAL_BALANCE = 10000;

    private FoodServicesCard testCard;

    // TODO: design unit tests for the FoodServiceCard class

    @BeforeEach
    public void runBefore() {
        testCard = new FoodServicesCard(INITIAL_BALANCE);
    }

    @Test
    public void testConstructor() {
        assertEquals(INITIAL_BALANCE, testCard.getBalance());
        assertEquals(0, testCard.getRewardPoints());

    }

    @Test
    public void testReloadOneTime() {
        testCard.reload(5);
        assertEquals(INITIAL_BALANCE + 5, testCard.getBalance());
    }

    @Test
    public void testReloadMultiTimes() {
        int time = 0;
        for (int i = 0; i < 5; i++) {
            testCard.reload(5);
            time++;
        }
        assertEquals(INITIAL_BALANCE + 5 * time, testCard.getBalance());
    }

    @Test
    public void testMakePurchaseNoMoney() {

        FoodServicesCard cardNoMoney = new FoodServicesCard(0);
        assertFalse(cardNoMoney.makePurchase(1));

        FoodServicesCard cardNoMoney2 = new FoodServicesCard(1);
        assertTrue(cardNoMoney2.makePurchase(1));

    }

    @Test
    public void testMakePurchaseEnoughMoneyNoReward() {

        int initialPoint = 100;
        testCard.points = initialPoint;
        int amount = 5;
        assertTrue(testCard.makePurchase(amount));
        assertEquals(INITIAL_BALANCE - amount, testCard.getBalance());
        assertEquals(initialPoint + testCard.REWARD_POINTS_PER_CENT_CHARGED * amount, testCard.getRewardPoints());

    }

    @Test
    public void testMakePurchaseEnoughMoneyCashBack() {

        int initialPoint = 1999;
        testCard.points = initialPoint;
        int amount = 50;
        assertTrue(testCard.makePurchase(amount));
        assertEquals(INITIAL_BALANCE - amount + testCard.CASH_BACK_REWARD, testCard.getBalance());
        assertEquals(initialPoint + testCard.REWARD_POINTS_PER_CENT_CHARGED * amount - testCard.POINTS_NEEDED_FOR_CASH_BACK, testCard.getRewardPoints());

    }

    @Test
    public void testMakePurchaseEnoughMoneyVertLargePurchase() {

        int initialPoint = 1999;
        testCard.points = initialPoint;
        int amount = 4000;
        assertTrue(testCard.makePurchase(amount));
        assertEquals(INITIAL_BALANCE - amount + testCard.CASH_BACK_REWARD * 2, testCard.getBalance());
        assertEquals(initialPoint + testCard.REWARD_POINTS_PER_CENT_CHARGED * amount - testCard.POINTS_NEEDED_FOR_CASH_BACK * 2, testCard.getRewardPoints());

    }

    @Test
    public void testGetBalance() {
        assertEquals(INITIAL_BALANCE, testCard.getBalance());
    }

    @Test
    public void testGetRewardPoints() {
        assertEquals(0, testCard.getRewardPoints());
    }


}
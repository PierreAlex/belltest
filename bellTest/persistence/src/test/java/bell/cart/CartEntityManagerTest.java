package bell.cart;

import java.util.Collection;

import bell.cart.persistence.entity.CartEntity;
import bell.cart.persistence.manager.AbstactDBEntityManager;
import bell.cart.persistence.manager.CartEntityManager;
import bell.cart.persistence.manager.ShoppingEntityManager;
import junit.framework.TestCase;

/**
 * Cart Entity manager testsuite.
 */
public class CartEntityManagerTest extends TestCase {

	AbstactDBEntityManager dBEntityManager;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public CartEntityManagerTest(String testName) {
		super(testName);
		// AbstactDBEntityManager.getEntityManager();
	}

	/**
	 * Test add and get and also make sure the get all cart item return the
	 * right amount of result
	 */
	public void testAddAndGet() {
		String username = "Pierre";
		CartEntityManager manager = new CartEntityManager();
		CartEntity item = new CartEntity();
		item.setUser(username);
		ShoppingEntityManager shoppingItemManager = new ShoppingEntityManager();
		item.setShoppingItem(shoppingItemManager.getShoppingItemById(1));
		manager.addCartItem(item);
		Collection<CartEntity> result = manager.getShoppingListFormCart(username);
		assertEquals(1, result.size());
		for (CartEntity itemFromDB : result) {
			assertEquals("Tiefschwarz", itemFromDB.getShoppingItem().getArtist());
		}
		item = new CartEntity();
		item.setUser(username);
		item.setShoppingItem(shoppingItemManager.getShoppingItemById(2));
		manager.addCartItem(item);
		result = manager.getShoppingListFormCart(username);
		assertEquals(2, result.size());
	}

}

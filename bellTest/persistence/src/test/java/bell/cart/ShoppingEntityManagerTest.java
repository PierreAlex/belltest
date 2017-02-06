package bell.cart;

import bell.cart.persistence.entity.ShoppingEntity;
import bell.cart.persistence.manager.ShoppingEntityManager;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class ShoppingEntityManagerTest extends TestCase {

	/**
	 * Test that the manager is capable of adding, getting a specific result and
	 * also getting the right number of result when getting all the shopping
	 * list
	 */
	public void testManager() {
		ShoppingEntityManager manager = new ShoppingEntityManager();
		ShoppingEntity itemToAdd = new ShoppingEntity();
		itemToAdd.setArtist("Jean");
		itemToAdd.setName("De");
		itemToAdd.setDescription("La");
		itemToAdd.setGenre("Fontaine");
		itemToAdd.setImgUrl("ecrit");
		itemToAdd.setUrl("des");
		itemToAdd.setPrice(1);
		assertEquals(5, manager.getShoppingList().size());
		Integer id = manager.addShoppingEntity(itemToAdd);
		ShoppingEntity itemFromDB = manager.getShoppingItemById(id);
		assertEquals(itemToAdd.getArtist(), itemFromDB.getArtist());
		assertEquals(itemToAdd.getDescription(), itemFromDB.getDescription());
		assertEquals(itemToAdd.getGenre(), itemFromDB.getGenre());
		assertEquals(itemToAdd.getImgUrl(), itemFromDB.getImgUrl());
		assertEquals(itemToAdd.getName(), itemFromDB.getName());
		assertEquals(itemToAdd.getPrice(), itemFromDB.getPrice());
		assertEquals(itemToAdd.getUrl(), itemFromDB.getUrl());
		assertEquals(itemToAdd.getId(), itemFromDB.getId());
		assertEquals(6, manager.getShoppingList().size());
	}

}

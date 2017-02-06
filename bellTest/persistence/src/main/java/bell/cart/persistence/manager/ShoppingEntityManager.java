package bell.cart.persistence.manager;

import java.util.Collection;

import javax.persistence.Query;

import bell.cart.persistence.entity.ShoppingEntity;

/**
 * The Shopping entity manager. Is responsible for any DB action regarding the
 * shopping item.
 * 
 * @author pa
 *
 */
public class ShoppingEntityManager extends AbstactDBEntityManager {

	/**
	 * Return a shopping item with an id
	 * 
	 * @param id
	 * @return
	 */
	public ShoppingEntity getShoppingItemById(Integer id) {
		return getEntityManager().find(ShoppingEntity.class, id);
	}

	/**
	 * Add a shopping entity to the DB and return it's assigned id
	 * 
	 * @param item
	 * @return
	 */
	public Integer addShoppingEntity(ShoppingEntity item) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(item);
		getEntityManager().getTransaction().commit();
		return item.getId();
	}

	/**
	 * Get all the shopping item
	 * 
	 * @return
	 */
	public Collection<ShoppingEntity> getShoppingList() {
		Query query = getEntityManager().createQuery("from " + ShoppingEntity.class.getName());
		return query.getResultList();
	}
}

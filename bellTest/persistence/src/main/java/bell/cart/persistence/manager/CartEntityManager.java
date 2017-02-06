package bell.cart.persistence.manager;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import bell.cart.persistence.entity.CartEntity;

/**
 * The cart entity manager responsible for storing or getting CartEntity
 * 
 * @author pa
 *
 */
public class CartEntityManager extends AbstactDBEntityManager {

	/**
	 * Add a cart item to the database.
	 * 
	 * @param cartItem
	 */
	public void addCartItem(CartEntity cartItem) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(cartItem);
		getEntityManager().getTransaction().commit();
	}

	/**
	 * Get all the CartItem for an user
	 * 
	 * @param username
	 * @return
	 */
	public Collection<CartEntity> getShoppingListFormCart(String username) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<CartEntity> query = criteriaBuilder.createQuery(CartEntity.class);
		Root<CartEntity> cartItem = query.from(CartEntity.class);
		CriteriaQuery<CartEntity> joinQuery = query.where(criteriaBuilder.equal(cartItem.get("user"), username));
		return getEntityManager().createQuery(joinQuery).getResultList();
	}
}

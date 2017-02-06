package bell.cart.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Cart entity to be stored in the database it contain an ID a reference to a
 * shopping item and a username. A user can only have 1 copy of each item hence
 * the Unique constrain on user and shopping item
 * 
 * @author pa
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user", "shoppingItem_id" }) })
public class CartEntity {

	@Id
	@GeneratedValue
	private Integer Id;

	private String user;

	@OneToOne(targetEntity = ShoppingEntity.class)
	private ShoppingEntity shoppingItem;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ShoppingEntity getShoppingItem() {
		return shoppingItem;
	}

	public void setShoppingItem(ShoppingEntity shoppingItemId) {
		this.shoppingItem = shoppingItemId;
	}
}

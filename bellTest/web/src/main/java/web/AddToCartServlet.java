package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bell.cart.persistence.entity.CartEntity;
import bell.cart.persistence.manager.CartEntityManager;
import bell.cart.persistence.manager.ShoppingEntityManager;

/**
 * Servlet responsible to add a shopping entity to the cart of a user
 * 
 * @author pa
 *
 */
public class AddToCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	CartEntityManager cartManager;
	ShoppingEntityManager shoppingItemManager;

	public void init() throws ServletException {
		cartManager = new CartEntityManager();
		shoppingItemManager = new ShoppingEntityManager();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		CartEntity cartItem = new CartEntity();
		cartItem.setShoppingItem(shoppingItemManager.getShoppingItemById(Integer.parseInt(request.getParameterMap().get("id")[0])));
		cartItem.setUser(request.getParameterMap().get("username")[0]);
		cartManager.addCartItem(cartItem);
	}

	public void destroy() {
		cartManager.close();
		shoppingItemManager.close();
	}
}

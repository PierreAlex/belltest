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

	private static final String SUCCESS = "Success";
	private static final String TABLE2 = "</table>";
	private static final String DIV2 = "</div>";
	private static final String DIV = "<div>";
	private static final String TR2 = "</tr>";
	private static final String TD2 = "</td>";
	private static final String P2 = "</p>";
	private static final String TD = "<td>";
	private static final String P = "<p>";
	private static final String TR = "<tr>\n";
	private static final String TABLE = "<table>\n";

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

package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bell.cart.persistence.entity.CartEntity;
import bell.cart.persistence.entity.ShoppingEntity;
import bell.cart.persistence.manager.CartEntityManager;

/**
 * Servlet responsible to return the cart item of an user. It output an html
 * representation of the shopping entity
 * 
 * @author pa
 *
 */
public class CartListServlet extends HttpServlet {

	private static final String TABLE2 = "</table>";
	private static final String TH2 = "</th>\n";
	private static final String TH = "<th>";
	private static final String TR = "<tr>\n";
	private static final String TR2 = "</tr>";
	private static final String TABLE = "<table>\n";

	private static final long serialVersionUID = 1L;

	CartEntityManager cartItemManager;

	public void init() throws ServletException {
		cartItemManager = new CartEntityManager();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		StringBuilder builder = new StringBuilder();
		builder.append("<h1>Cart</h1>");
		builder.append(TABLE);
		builder.append(TR);
		builder.append(TH + "ID" + TH2);
		builder.append(TH + "Artist" + TH2);
		builder.append(TH + "Name" + TH2);
		builder.append(TH + "Genre" + TH2);
		builder.append(TH + "Price" + TH2);
		builder.append(TH + TH2);
		builder.append(TR2);
		for (CartEntity cartItem : cartItemManager.getShoppingListFormCart(request.getParameterMap().get("username")[0])) {
			ShoppingEntity item = cartItem.getShoppingItem();
			builder.append(TR);
			builder.append(TH + item.getId() + TH2);
			builder.append(TH + item.getArtist() + TH2);
			builder.append(TH + item.getName() + TH2);
			builder.append(TH + item.getGenre() + TH2);
			builder.append(TH + item.getPrice() + TH2);
			builder.append(TH + "<button onClick=\"loadDescription(this.id)\" id=\"" + item.getId() + "\">Description</button>" + TH2);
			builder.append(TR2);
		}
		builder.append(TABLE2);

		// button to hide the cart section
		builder.append("<button onClick=\"hideCart()\">Hide cart</button>");
		out.println(builder.toString());
	}

	public void destroy() {
		cartItemManager.close();
	}
}

package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bell.cart.persistence.entity.ShoppingEntity;
import bell.cart.persistence.manager.ShoppingEntityManager;

/**
 * Servlet responsible to get the item description of a servlet and return it as
 * html content to update the page
 * 
 * @author pa
 *
 */
public class ItemDescriptionServlet extends HttpServlet {

	private static final String TABLE2 = "</table>";
	private static final String DIV2 = "</div>";
	private static final String DIV = "<div>";
	private static final String TR2 = "</tr>";
	private static final String TD2 = "</td>";
	private static final String P2 = "</p>";
	private static final String TD = "<td>";
	private static final String P = "<p>";
	private static final String TR = "<tr>\n";

	private static final long serialVersionUID = 1L;

	ShoppingEntityManager shoppingManager;

	public void init() throws ServletException {
		shoppingManager = new ShoppingEntityManager();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		ShoppingEntity item = shoppingManager.getShoppingItemById(Integer.parseInt(request.getParameterMap().get("id")[0]));

		StringBuilder builder = new StringBuilder();
		builder.append("<table style=\"border:0px transparent>\"");
		builder.append(TR);
		builder.append("<td style=\"width: 223px;\"><img src=\"" + item.getImgUrl() + "\" style=\"width: 220px;\"></td>");
		builder.append(TD);
		builder.append(DIV);
		builder.append(P + item.getArtist() + " - " + item.getName() + P2);
		builder.append(P + item.getGenre() + P2);
		builder.append(P + item.getPrice() + "$</p>");
		builder.append(P + item.getDescription() + " <a href=" + item.getUrl() + ">Website</a>" + P2);
		builder.append("<button onClick=\"hideDescription()\"\">Close description</button>");
		builder.append(DIV2);
		builder.append(TD2);
		builder.append(TR2);
		builder.append(TABLE2);

		out.println(builder.toString());
	}

	public void destroy() {
		shoppingManager.close();
	}
}

package bell.cart.persistence.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bell.cart.persistence.entity.ShoppingEntity;

/**
 * Base Abstract class that contain the entity manager. It initialize the
 * EntityManager when the user does it's first operation
 * 
 * @author pa
 *
 */
public abstract class AbstactDBEntityManager {

	private static final String BELLTEST = "BELLTEST";
	private static EntityManager entityManager;

	/**
	 * Utility method to populate the database with some object. This was done
	 * that way for the purpose of this exercice : to add some data will
	 * facilitate the usability of this application
	 */
	private void populateDataBase(EntityManager manager) {

		ShoppingEntity shoppingItem = new ShoppingEntity();
		manager.getTransaction().begin();
		shoppingItem = new ShoppingEntity();
		shoppingItem.setArtist("Tiefschwarz");
		shoppingItem.setGenre("Afro house");
		shoppingItem.setName("Calling Home (Original Mix)");
		shoppingItem.setPrice(1.99);
		shoppingItem.setImgUrl("http://geo-static.traxsource.com/files/images/703444_large.jpg");
		shoppingItem.setUrl("http://www.traxsource.com/title/740089/calling-home");
		shoppingItem.setDescription(
				"For Studio Kreuzberg’s debut release, we call on the world famous DJ duo Tiefschwarz and Studio Kreuzberg founder Yawk to set the stage for things to come from the Studio Kreuzberg lab itself. The boys deliver a brilliant collaboration, a 4 track EP ‘Calling Home’ including remixes from Nick Galemore and Eluize. The original mix opens the EP, giving off an air of experimentalism and freedom, the same that led to the creation of the label itself. The track plays with your senses as it moves your feet, form the chopped vocals to the gritty organic vibe it carries. Next, Nick Galemore dives deeper, creating an atmospheric ambience while slowly pulling back the wool to reveal an emotionally and physically moving remix. From here Eluize takes over with her ‘Mum’s The Word’ remix. Opening on a bouncy groove she quickly enchants with a beautiful blend of percussion and ambience. Before long, she’s captured us with a bubbling synth that pours over the track with elegance. Lastly, the instrumental mix strips down the vocals to the bones to deliver a pure experience, ending the EP in the same place it began.");
		if (manager.contains(shoppingItem))
			return;
		manager.persist(shoppingItem);

		shoppingItem = new ShoppingEntity();
		shoppingItem.setArtist("Marie Joly, Black Coffee, Rebecca Murray, Pablo Fierro");
		shoppingItem.setGenre("Afro house");
		shoppingItem.setName("Gratitude (Pablo Fierro Remix)");
		shoppingItem.setPrice(1.99);
		shoppingItem.setImgUrl("http://geo-static.traxsource.com/files/images/683888_large.jpg");
		shoppingItem.setUrl("http://www.traxsource.com/title/718994/gratitude-remixes");
		shoppingItem.setDescription(
				"Marie Joly started her career with a smash hit untitled “Lovin U” that was remixed by House Legend Louie Vega. She never ceased to provide consistent quality through the years and earlier this year encountered another great success with the song “Gratitude” in collaboration with Black Coffee featuring Rebecca Murray which has amassed over a million plays on Spotify. Following this unexpected success, Marie Joly returns with a remix package that is sure to confirm that this is not a one hit wonder. Top producers like Pablo Fierro, MYNY, Boddhi Satva, Doug Gomez & Enoo Napa have given this song their respective touches and sure the package is very strong and sure to encounter a place of choice in your playlist or sets.");
		manager.persist(shoppingItem);

		shoppingItem = new ShoppingEntity();
		shoppingItem.setArtist("Stones & Bones, Toshi, Spellband");
		shoppingItem.setGenre("Afro house");
		shoppingItem.setName("Amahloni (Spellband Remix)");
		shoppingItem.setPrice(1.99);
		shoppingItem.setImgUrl("http://geo-static.traxsource.com/files/images/709338_large.jpg");
		shoppingItem.setUrl("http://www.traxsource.com/title/746172/amahloni");
		shoppingItem.setDescription(
				"Amahloni by Stones & Bones which features the unrivaled sweet seducing vocals of Toshi was originally released in March of last year through House of Stone, end of last year MoBlack Records introduced us to the fantastic Abicah Soul remix of the song as part of their ingenious Organic Wave compilation. Finally we get the full single release which includes the wonderful organic original version in its full extended glory together with a breathtaking truly eclectic selection of remixes by Abicah Soul, Chymamusique, Manoo and Spellband all injecting their beloved signature afro/deep touch to take the song to a whole new level, making it next to impossible to pick a favorite version. Essential...");
		manager.persist(shoppingItem);

		shoppingItem = new ShoppingEntity();
		shoppingItem.setArtist("Moon Rocket, Mattei & Omich, Bel-Ami");
		shoppingItem.setGenre("Afro house");
		shoppingItem.setName("Find Your Way");
		shoppingItem.setImgUrl("http://geo-static.traxsource.com/files/images/705210_large.jpg");
		shoppingItem.setUrl("http://www.traxsource.com/title/741957/find-your-way");
		shoppingItem.setPrice(1.99);
		shoppingItem.setDescription("Written by Raffa Scoccia, Patrizio Matteo, Amir Bakari Bellamy");
		manager.persist(shoppingItem);

		shoppingItem = new ShoppingEntity();
		shoppingItem.setArtist("Art Of Tones");
		shoppingItem.setGenre("Deep house");
		shoppingItem.setName("So Worried");
		shoppingItem.setPrice(1.99);
		shoppingItem.setImgUrl("http://geo-static.traxsource.com/files/images/706382_large.jpg");
		shoppingItem.setUrl("http://www.traxsource.com/title/743237/so-worried-got-2-get-2");
		shoppingItem.setDescription("The wonderful Ludovic Llorca aka Art Of Tones gets his head together with our own thatmanmonkz on this split four tracker, with another secret guest appearance from Steel City's Ducktape maestro on the dub.");
		manager.persist(shoppingItem);
		manager.getTransaction().commit();

	}

	/**
	 * The entityManager getter inspired on the singleton pattern. On first call it
	 * will set the entity manager then use it afterward
	 */
	public EntityManager getEntityManager() {
		if (entityManager == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(BELLTEST);
			EntityManager manager = emf.createEntityManager();
			populateDataBase(manager);
			entityManager = manager;
		}

		return entityManager;
	}

	public void close() {
		Persistence.createEntityManagerFactory(BELLTEST).close();
	}
}

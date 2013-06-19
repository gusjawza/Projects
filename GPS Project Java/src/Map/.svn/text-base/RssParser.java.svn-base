package Map;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class contains methods that parses XML information and is designed for
 * RSS feed for Yahoo weather as a GPS feature. It uses SAX parser to parse the
 * XML info. The method names are self-explanatory.
 * 
 * @author Batbilig Bavuudorj
 * 
 */

public class RssParser extends DefaultHandler {
	private String urlString;
	private RssFeed rssFeed;
	private StringBuilder text;
	private Item item;
	private String attrValue;

	/**
	 * Takes a url address of the feed to be parsed.
	 * 
	 * @param url
	 */
	public RssParser(String url) {
		this.urlString = url;
		this.text = new StringBuilder();
	}

	/**
	 * Parses the url with SAX parser by getting an input stream.
	 */
	public void parse() {
		InputStream urlInputStream = null;
		SAXParserFactory spf = null;
		SAXParser sp = null;

		try {
			URL url = new URL(this.urlString);
			_setProxy(); // Set the proxy if needed
			urlInputStream = url.openConnection().getInputStream();
			spf = SAXParserFactory.newInstance();
			if (spf != null) {
				sp = spf.newSAXParser();
				sp.parse(urlInputStream, this);
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
		} finally {
			try {
				if (urlInputStream != null)
					urlInputStream.close();
			} catch (Exception e) {
			}
		}
	}

	public RssFeed getFeed() {
		return (this.rssFeed);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		if (qName.equalsIgnoreCase("channel"))
			this.rssFeed = new RssFeed();

		else if (qName.equalsIgnoreCase("item") && (this.rssFeed != null)) {
			this.item = new Item();
			this.rssFeed.addItem(this.item);
		}

		else if ("yweather:condition".equalsIgnoreCase(qName)) {

			attrValue = attributes.getValue(2);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (this.rssFeed == null)
			return;

		if (qName.equalsIgnoreCase("item"))
			this.item = null;

		else if (qName.equalsIgnoreCase("title")) {
			if (this.item != null)
				this.item.title = this.text.toString().trim();
			else
				this.rssFeed.title = this.text.toString().trim();
		} else if (qName.equalsIgnoreCase("link")) {
			if (this.item != null)
				this.item.link = this.text.toString().trim();
			else
				this.rssFeed.link = this.text.toString().trim();
		} else if (qName.equalsIgnoreCase("description")) {
			if (this.item != null)
				this.item.description = this.text.toString().trim();
			else
				this.rssFeed.description = this.text.toString().trim();
		}

		// yweather
		else if (qName.equalsIgnoreCase("yweather:condition")) {
			this.item.condition = this.attrValue;
		} else if (qName.equalsIgnoreCase("category") && (this.item != null))
			this.rssFeed.addItem(this.text.toString().trim(), this.item);
		this.text.setLength(0);
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		this.text.append(ch, start, length);
	}

	/**
	 * For handling the proxy.
	 * 
	 * @throws IOException
	 */
	public static void _setProxy() throws IOException {
		Properties sysProperties = System.getProperties();
		sysProperties.put("proxyHost", "<Proxy IP Address>");
		sysProperties.put("proxyPort", "<Proxy Port Number>");
		System.setProperties(sysProperties);
	}

	/**
	 * This class contains a few methods that handles adding items to a list
	 * using hashmap.
	 * 
	 * @author Batbilig Bavuudorj
	 * 
	 */
	public class RssFeed {

		public String title;
		public String description;
		public String link;
		public String condition;
		public String lon;
		public String lat;
		private ArrayList<Item> items;
		private HashMap<String, ArrayList<Item>> category;

		/**
		 * Adds weather objects to a list
		 * @param item
		 */
		public void addItem(Item item) {
			if (this.getItems() == null)
				this.setItems(new ArrayList<Item>());
			this.getItems().add(item);
		}

		/**
         * Adds weather objects to a list as a category. It implements hashmap.
         * @param category
         * @param item
         */
		public void addItem(String category, Item item) {
			if (this.category == null)
				this.category = new HashMap<String, ArrayList<Item>>();
			if (!this.category.containsKey(category))
				this.category.put(category, new ArrayList<Item>());
			this.category.get(category).add(item);
		}

		/**
         * Sets the given value to an item.
         * @param items
         */
		public void setItems(ArrayList<Item> items) {
			this.items = items;
		}

		/**
		 * Returns an item.
		 * @return
		 */
		public ArrayList<Item> getItems() {
			return items;
		}
	}

	/**
     * This class creates an weather object that has title and description with current temperature.  
     *
     */
	public class Item {
		public String title;
		public String description;
		public String link;
		public String condition;

		@Override
		public String toString() {
			return (this.title + ": " + this.description + "," + this.condition);
		}
	}

	/**
     * Wrapper method that calls a weather feed object.
     * @return
     */
	public String call() {
		RssParser rp = new RssParser(
				"http://weather.yahooapis.com/forecastrss?w=24606084&u=c");
		rp.parse();
		RssParser.RssFeed feed = rp.getFeed();
		return feed.getItems().get(0).condition;
	}
}

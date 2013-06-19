package Map;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This is PlaceMarks Class
 * @author Rated-R Coder: Rashid Darwish
 *
 */
public class PlaceMarks extends JPanel{
	private ArrayList<String> ref;
	private String id;
	private String kind;
	private String name;
	private int x;
	private int y;
	
	/**
	 * @param id PlaceMarks Constructor
	 */
	PlaceMarks(String id){
		this.id = id;
		ref = new ArrayList<String>();
	}

	/**
	 * Node referencer getter
	 * @return ref
	 */
	public ArrayList<String> getRef() {
		return ref;
	}

	/**
	 * Node referencer setter
	 * @param ref the ref set
	 */
	public void setRef(String ref) {
		this.ref.add(ref);
	}

	/**
	 * id getter
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * id setter
	 * @param id 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * kind getter
	 * @return kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * kind setter
	 * @param kind 
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}

	/**
	 * name getter
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * name setter
	 * @param name 
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * pixels coordinates x getter
	 * @return the x
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * pixels coordinates x setter
	 * @param x 
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * pixels coordinates y setter
	 * @return the y
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * pixels coordinates y setter
	 * @param y 
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Reference setter
	 * @param ref 
	 */
	public void setRef(ArrayList<String> ref) {
		this.ref = ref;
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#toString()
	 */
	@Override
	    public String toString() {
	        return ("Place: ID: " + id + "   kind: " + kind + "   name: " + name + " RefNodes" + ref);
	    }
}

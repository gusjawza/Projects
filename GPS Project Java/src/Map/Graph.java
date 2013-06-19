package Map;
import java.util.HashMap;
import java.util.List;

/**
 * This is the Graph for the connection of the nodes and edges
 * @author Rated-R Coder: Rashid Darwish
 *
 */
public class Graph{
	private HashMap<String, Node> nodesMap;
    private List<Edges> edgesList;

    /**
     * Graph Constructor
     * @param nodesMap
     * @param edgesList
     */
    public Graph(HashMap<String, Node> nodesMap, List<Edges> edgesList) {
        this.nodesMap = nodesMap ;
        this.edgesList = edgesList;
    }
    /**
     * @return List<Edges> edgesList
     */
    public List<Edges> getEdgesList() {
        return edgesList;
    }
	/**
	 * @return HashMap<String, Nodes> nodesMap
	 */
	public HashMap<String, Node> getNodesMap() {
		return nodesMap;
	}  
}


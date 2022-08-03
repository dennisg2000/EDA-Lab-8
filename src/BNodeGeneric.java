
import java.util.ArrayList;
public class BNodeGeneric<T> {
	ArrayList<T> keys; // keys of nodes
    int MinDeg; // Minimum degree of B-tree node
    ArrayList<BNodeGeneric<T>> children; // Child node
    int count; // Number of keys of node
    boolean isLeaf; // True when leaf node

    // Constructor
    public BNodeGeneric(int deg){

        this.MinDeg = deg;
        
        this.keys = new ArrayList<T>(deg); // Node has 2*MinDeg-1 keys at most
        this.children = new ArrayList<BNodeGeneric<T>>(deg);
        for(int i=0;i<deg;i++) {
        	this.keys.add(null);
        	this.children.add(null);
        }
    }
    public boolean nodeFull(int nEle){
    	return this.count == nEle;
    	}
    public boolean nodeEmpty(int nEle){
    	return this.count < (nEle/2);
    }
    public String toString(){
    	String s = "(";
    	for(int i = 0; i < this.count; i++){
    		s+= this.keys.get(i);
    		if (i< this.count-1)
    			s+= ", ";
    		else
    			s+= ")";
    	}
    	return s;

    }
}


public class BTreeGeneric<E extends Comparable<E>> {
	BNodeGeneric<E> root;
	int MinDeg;
	
	private boolean up;
	private BNodeGeneric<E> ndes;
	
	public BTreeGeneric(int deg){
        this.root = null;
        this.MinDeg = deg;
    }
	public boolean isEmpty() {
		return this.root==null;
	}
	
    public void add(E value) {
    	up=false;
    	E mediana;
    	BNodeGeneric<E> pnew;
    	mediana=push(this.root,value);
    	if(up){
    		pnew = new BNodeGeneric<E>(this.MinDeg);
    		pnew.count = 1;
    		pnew.keys.set(0, mediana);
    		pnew.children.set(0, this. root);
    		pnew.children.set(1, ndes);
    		this. root= pnew;
    		}
    }
    private E push (BNodeGeneric<E> current, E value){
    	int pos[] = new int[1];
    	E mediana;
    	if(current == null){
    		up = true;
    		ndes = null;
    		return value;
    	}
    	else{
    		boolean fl;
    		fl= searchNode(current, value, pos);
    		if(fl) {
    			System.out.println("Item duplicado\n");
    			up = false;
    			return null;
    		}
    		mediana = push(current.children.get(pos[0]), value);
    		if (up) {
    			if(current.nodeFull (this.MinDeg-1))
    					mediana = dividedNode(current, mediana, pos [0]);
    			else{
    				up = false;
    	    		putNode(current, mediana, ndes, pos[0]);
    			}
    		}
    		return mediana;
    	}
    		
    }
    private void putNode(BNodeGeneric<E> current, E cl, BNodeGeneric<E> rd, int k){
    	int i;
    	for(i= current.count-1; i >= k; i--) {
    	current.keys.set(i+1, current.keys.get(i));
    	current.children.set (i+2, current.children.get(i+1));
    	}
    	current.keys.set(k, cl);
    	current.children.set(k+1, rd);
    	current.count++;
    	}
    private E dividedNode(BNodeGeneric<E> current, E cl,int k){
    	BNodeGeneric<E> rd = ndes;
    	int i, posMdna;
    	posMdna = (k <= this.MinDeg/2) ? this.MinDeg/2 : this.MinDeg/2+1;
    	ndes = new BNodeGeneric<E>(this.MinDeg);
    	for(i= posMdna; i < this.MinDeg-1; i++) {
    		ndes.keys.set(i-posMdna, current.keys.get(i));
    		ndes.children.set(i-posMdna+1, current.children.get(i+1));
    	}
    	
    	ndes.count = (this.MinDeg-1) - posMdna;
    	current.count = posMdna;
    	if(k<= this.MinDeg/2)
    		putNode(current, cl, rd,k-posMdna);
    	else
    		putNode(ndes, cl, rd,k-posMdna);
    	E median  = current.keys.get(current.count-1);
    	ndes.children.set(0, current.children.get(current.count));
    	current.count--;
    	return median;
    	}
    public E remove(E value) {
        //TODO implement here!
        return null;
    }

    public void clear() {
        this.root=null;    }

    public boolean search(E value) {
        
        return search(this.root,value);
    }
    public boolean search(BNodeGeneric<E> current, E value) {
        int pos[] = new int[1];
        boolean fl;
        if (current== null)
        	return false;
        else {
        	fl = searchNode(current, value,pos);
        	if(fl) {
        		System.out.println("elem "+value+" en la pos "+pos[0]);
        		return true;
        	}
        	else 
        		return search(current.children.get(pos[0]),value);
        }
    }
    
    private boolean searchNode(BNodeGeneric<E> current, E value, int pos[]) {
    	pos[0]=0;
    	while(pos [0]<current.count && current.keys.get(pos[0]).compareTo(value)<0)
    		pos[0]++;
    	if (pos[0]==current.count)
    		return false;
    	return(value.equals(current.keys.get(pos[0])));
    }
    public int size() {
        //TODO implement here!
        return 0;
    }
}
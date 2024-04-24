package tree;
import java.util.ArrayList;
import java.util.Iterator;

public class GenericTreeNode<E> {
	E data;
	//<some list of children>
	ArrayList<GenericTreeNode<E>> children;
	
	public GenericTreeNode(E theItem) {
		data = theItem;
		children = new ArrayList<>();
	}
	
	public void addChild(GenericTreeNode<E> theItem) {
		children.add(theItem);
	}
	
	public void removeChild(E theItem) {
		// this one is a little harder.
		// what do you do when the item has children?
		// I suggest "give them to the parent"

		if (children == null || children.isEmpty()) {
            return;
        }

        Iterator<GenericTreeNode<E>> iterator = children.iterator();
        while (iterator.hasNext()) {
            GenericTreeNode<E> child = iterator.next();
            if (child.data.equals(theItem)) {
                
                iterator.remove();
                for (GenericTreeNode<E> grandchild : child.children) {
                    this.addChild(grandchild); 
                }
                break;
            }
        }

	}
	
	
} 

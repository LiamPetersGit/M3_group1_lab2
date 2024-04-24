package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
 
public class OrgChartImpl implements OrgChart{

	//Employee is your generic 'E'..
	private List<GenericTreeNode<Employee>> nodes = new ArrayList<>();
	
	@Override
	public void addRoot(Employee e) {
		GenericTreeNode<Employee> rootEmployee = new GenericTreeNode<Employee>(e);
		nodes.add(rootEmployee);
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

		nodes.clear();
		
	}

	@Override
	public void addDirectReport(Employee manager, Employee newPerson) {
		for (GenericTreeNode<Employee> node : nodes) {
			if (node.data.equals(manager)) {
				GenericTreeNode<Employee> newEmployeeNode = new GenericTreeNode<>(newPerson);
				node.addChild(newEmployeeNode);
				nodes.add(newEmployeeNode);
				return;
			}
		}
		
	}

	@Override
	public void removeEmployee(Employee firedPerson) {
		// TODO Auto-generated method stub
		
	GenericTreeNode<Employee> parent = null;
    GenericTreeNode<Employee> toRemove = null;

    
    for (GenericTreeNode<Employee> node : nodes) {
        if (node.data.equals(firedPerson)) {
            toRemove = node; 
            break;
        }
        for (GenericTreeNode<Employee> child : node.children) {
            if (child.data.equals(firedPerson)) {
                parent = node;
                toRemove = child;
                break;
            }
        }
        if (toRemove != null) break;
    }

    if (toRemove != null) {
        if (parent != null) {
            int removeIndex = parent.children.indexOf(toRemove);
            parent.children.remove(toRemove);
            
            int reinsertIndex = Math.max(0, removeIndex);
            for (GenericTreeNode<Employee> child : toRemove.children) {
                parent.children.add(reinsertIndex, child);
                reinsertIndex++;
            }
        }
        nodes.remove(toRemove);
    }
	}

	@Override
	public void showOrgChartDepthFirst() {
		if (!nodes.isEmpty()) {
			showOrgChartDepthFirst(nodes.get(0), "");  
		}
	}
	
	private void showOrgChartDepthFirst(GenericTreeNode<Employee> node, String indent) {
		if (node == null) return;
		System.out.println(indent + node.data);
		for (GenericTreeNode<Employee> child : node.children) {
			showOrgChartDepthFirst(child, indent + "  ");
		}
	}

	@Override
	public void showOrgChartBreadthFirst() {
		// TODO Auto-generated method stub
		
		if (nodes.isEmpty()) return;
    
		Queue<GenericTreeNode<Employee>> queue = new LinkedList<>();
		queue.add(nodes.get(0));  
		
		while (!queue.isEmpty()) {
			GenericTreeNode<Employee> current = queue.poll();
			System.out.println(current.data);
			queue.addAll(current.children);
		}

	}
	
	
}

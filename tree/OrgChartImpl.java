package tree;

import java.util.ArrayList;
import java.util.List;
 
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
		
	}

	@Override
	public void addDirectReport(Employee manager, Employee newPerson) {
		for (int i = 0; i < nodes.size(); i++) {
			GenericTreeNode<Employee> currentEmployee = nodes.get(i);
			if (currentEmployee.data.equals(manager)) {
				GenericTreeNode<Employee> newE = new GenericTreeNode<Employee>(newPerson);
				
				currentEmployee.addChild(newE);
				
				nodes.add(newE);
				break;
			}
		}
		
	}

	@Override
	public void removeEmployee(Employee firedPerson) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOrgChartDepthFirst() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOrgChartBreadthFirst() {
		// TODO Auto-generated method stub
		
	}
	
	
}

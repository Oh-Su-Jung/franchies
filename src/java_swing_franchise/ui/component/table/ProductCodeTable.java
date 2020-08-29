package java_swing_franchise.ui.component.table;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java_swing_franchise.dto.Product;

@SuppressWarnings("serial")
public class ProductCodeTable extends JTable {
	private ArrayList<Product> productCode;
	private CustomModel model;
	
	public ProductCodeTable() {
		initComponents();
		loadDate();
	}
	
	public ProductCodeTable(ArrayList<Product> productCode) {
		this.productCode = productCode;
		initComponents();
	}
	
	public void setProduct(ArrayList<Product> productCode) {
		this.productCode = productCode;
		loadDate();
	}
	
	private void loadDate() {
		model = new CustomModel(getRows(), getColNames());
		setModel(model);
	}

	private void initComponents() {
		
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private Object[] getColNames() {
		return new String[] {"제품코드", "제품명"};
	}

	private Object[][] getRows() {
		/*Object[][] rows = new Object[productCode.size()][];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = toArray(productCode.get(i));
		}
		return rows;*/
		return new Object[][] {
			{"A001", "아메리카노"},
			{"A002", "카푸치노"},
			{"A003", "헤이즐넛"},
			{"A004", "에스프레소"},
			{"B001", "딸기쉐이크"},
			{"B002", "후르츠와인"},
			{"B003", "팥빙수"},
			{"B004", "아이스초코"},
		};
	}
	
	private Object[] toArray(Product pro) {
		return new Object[] {pro.getCode(), pro.getName()};
	}

	private class CustomModel extends DefaultTableModel {

		public CustomModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}

}

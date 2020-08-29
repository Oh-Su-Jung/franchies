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
public class OutputTable extends JTable {
	private ArrayList<Product> productCode;
	private CustomModel model;
	
	public OutputTable() {
		initComponents();
	}

	private void initComponents() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	public void setProduct(ArrayList<Product> productCode) {
		this.productCode = productCode;
		loadData();
		loadData();
	}
	
	public void loadData() {
		model = new CustomModel(getRows(), getColName());
		setModel(model);
		
		tableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		tableCellAlign(SwingConstants.RIGHT, 3, 4, 5, 6, 7, 8, 9);
	}

	private Object[] getColName() {
		return new String[] { "순위", "제품코드", "제품명", "제품단가", "판매수량", "공급가액", "부가세액", "판매금액", "마진율", "마진액" };
	}

	private Object[][] getRows() {
		Object[][] rows = new Object[productCode.size()+1][];
		int totalSupply = 0;
		int totalVat = 0;
		int totalPrice = 0;
		int totalProfit = 0;
		
		for (int i = 0; i < rows.length-1; i++) {
			rows[i] = toArray(productCode.get(i));
			totalSupply += productCode.get(i).getSupply();
			totalVat += productCode.get(i).getVat();
			totalPrice += productCode.get(i).getTotalPrice();
			totalProfit += productCode.get(i).getProfitPrice();
		}
		rows[rows.length-1] = new Object[] { "합계", "", "", "", "", String.format("%,d", totalSupply), String.format("%,d", totalVat), String.format("%,d", totalPrice), "", String.format("%,d", totalProfit)};
		return rows;
	}
	
	private Object[] toArray(Product pro) {
		return new Object[] {pro.getNum(), pro.getCode(), pro.getName(), String.format("%,d", pro.getPrice()), pro.getLength(), String.format("%,d", pro.getSupply()), String.format("%,d", pro.getVat()), String.format("%,d", pro.getTotalPrice()), pro.getProfit(), String.format("%,d", pro.getProfitPrice())};
	}

	private void tableCellAlign(int align, int...idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel cModel = getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			cModel.getColumn(idx[i]).setCellRenderer(dtcr);
		}
		
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

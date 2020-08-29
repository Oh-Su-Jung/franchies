package java_swing_franchise.ui.component.content;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java_swing_franchise.dto.Product;
import java_swing_franchise.ui.component.table.ProductCodeTable;
import java_swing_franchise.ui.exception.InValidTfValue;

@SuppressWarnings("serial")
public class FranchiseWritePanel extends JPanel {
	private JTextField tfCode;
	private JTextField tfPrice;
	private JTextField tfLength;
	private JTextField tfProfit;
	private JLabel lblName2;
	private ProductCodeTable table;

	
	public FranchiseWritePanel() {

		initComponents();
	}
	
	private void initComponents() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(0, 2, 5, 5));
		
		JPanel pCode = new JPanel();
		add(pCode);
		pCode.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCode = new JLabel("제품코드");
		lblCode.setHorizontalAlignment(SwingConstants.CENTER);
		pCode.add(lblCode);
		
		MyDocumentListener docListener = new MyDocumentListener();
		
		tfCode = new JTextField();
		pCode.add(tfCode);
		tfCode.getDocument().addDocumentListener(docListener);
		tfCode.setColumns(10);
		
		JPanel pName = new JPanel();
		add(pName);
		
		JLabel lblName = new JLabel("제품명");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		pName.add(lblName);
		
		lblName2 = new JLabel("");
		lblName2.setForeground(Color.RED);
		lblName2.setHorizontalAlignment(SwingConstants.RIGHT);
		pName.add(lblName2);
		
		JPanel pPrice = new JPanel();
		add(pPrice);
		pPrice.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblPrice = new JLabel("제품단가");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		pPrice.add(lblPrice);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		pPrice.add(tfPrice);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JPanel pLength = new JPanel();
		add(pLength);
		pLength.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblLength = new JLabel("판매수량");
		lblLength.setHorizontalAlignment(SwingConstants.CENTER);
		pLength.add(lblLength);
		
		tfLength = new JTextField();
		tfLength.setColumns(10);
		pLength.add(tfLength);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JPanel pProfit = new JPanel();
		add(pProfit);
		pProfit.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblProfit = new JLabel("마진율");
		lblProfit.setHorizontalAlignment(SwingConstants.CENTER);
		pProfit.add(lblProfit);
		
		tfProfit = new JTextField();
		tfProfit.setColumns(10);
		pProfit.add(tfProfit);
		
		JPanel panel_5 = new JPanel();
		add(panel_5);
	}
	
	public Product getProduct() {
		if (!isValidTf()) {
			throw new InValidTfValue("제품단가 및 판매수량은 8자리 이내, 마진율은 2자리 이내의 숫자로만 입력이 가능합니다.");
		}
		
		String chart = String.valueOf(tfCode.getText().charAt(0));
		String str = chart.toUpperCase();
		String str2 = tfCode.getText().substring(1);
		tfCode.setText(str + str2);

		String code = tfCode.getText().trim();
		String name = lblName2.getText().trim();
		int price = Integer.parseInt(tfPrice.getText().trim());
		int length = Integer.parseInt(tfLength.getText().trim());
		int profit = Integer.parseInt(tfProfit.getText().trim());
		return new Product(code, name, price, length, profit);
	}
	
	private boolean isValidTf() {
		String price = tfPrice.getText().trim();
		String length = tfLength.getText().trim();
		String profit = tfProfit.getText().trim();

		boolean priceCheck = Pattern.matches("\\d{1,8}", price);
		boolean lengthCheck = Pattern.matches("\\d{1,8}", length);
		boolean profitCheck = Pattern.matches("\\d{1,2}", profit);
		return priceCheck && lengthCheck && profitCheck;
	}

	public void setProduct(Product pro) {
		tfCode.setText(pro.getCode());
		String name = findName();
		lblName2.setText(name);
		tfPrice.setText(String.valueOf(pro.getPrice()));
		tfLength.setText(String.valueOf(pro.getLength()));
		tfProfit.setText(String.valueOf(pro.getProfit()));
	}
	
	public void clearTf() {
		tfCode.setText("");
		lblName2.setText("");
		tfPrice.setText("");
		tfLength.setText("");
		tfProfit.setText("");
	}
	
	class MyDocumentListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {
			changeTotalAndAverage();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			changeTotalAndAverage();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			changeTotalAndAverage();
		}
		
		private void changeTotalAndAverage() {
			table = new ProductCodeTable();
			String name = findName();
			
			lblName2.setText(name);
		}
	}

	private String findName() {
		table = new ProductCodeTable();
		String name = null;
		for (int i = 0; i < table.getRowCount(); i++) {
			if (tfCode.getText().equalsIgnoreCase((String) table.getValueAt(i, 0))) {
				name = (String) table.getValueAt(i, 1);
				break;
			}
		}
		return name;
	}

}

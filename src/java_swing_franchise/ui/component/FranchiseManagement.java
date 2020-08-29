package java_swing_franchise.ui.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java_swing_franchise.dto.Product;
import java_swing_franchise.ui.component.content.FranchiseWritePanel;
import java_swing_franchise.ui.component.table.ProductCodeTable;

@SuppressWarnings("serial")
public class FranchiseManagement extends JFrame implements ActionListener {

	private JPanel contentPane;
	private FranchiseWritePanel pWrite;
	private JPanel pBtns;
	private JButton btnAdd;
	private JButton btnOutput1;
	private JButton btnOutput2;
	private ArrayList<Product> proList;
	private JPanel pProductCode;
	private JScrollPane scrollPane;
	private ProductCodeTable table;
	
	private FranchiseOutputDlg1 outputDlg1;
	private FranchiseOutputDlg2 outputDlg2;

	public FranchiseManagement() {
		proList = new ArrayList<>();
		proList.add(new Product("A001", "아메리카노", 4500, 150, 10));
		proList.add(new Product("A002", "카푸치노", 3800, 140, 15));
		proList.add(new Product("B001", "딸기쉐이크", 5200, 250, 12));
		proList.add(new Product("B002", "후르츠와인", 4300, 110, 11));
		
		initComponents();
		
		Product testPro = new Product("A001", 4500, 150, 10);
		
		
		pWrite.setProduct(testPro);
		table.setProduct(proList);
		
		/*ArrayList<Product> productCode = new ArrayList<>();
		productCode.add(new Product("A001", "아메리카노"));
		productCode.add(new Product("A002", "카푸치노"));
		productCode.add(new Product("A003", "헤이즐넛"));
		productCode.add(new Product("A004", "헤이즐넛"));
		table.setProduct(productCode);*/
	}
	
	private void initComponents() {
		setTitle("프랜차이즈 커피전문점 입력");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pWrite = new FranchiseWritePanel();
		contentPane.add(pWrite);
		
		pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("입력");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnOutput1 = new JButton("출력1");
		btnOutput1.addActionListener(this);
		pBtns.add(btnOutput1);
		
		btnOutput2 = new JButton("출력2");
		btnOutput2.addActionListener(this);
		pBtns.add(btnOutput2);
		
		pProductCode = new JPanel();
		contentPane.add(pProductCode);
		pProductCode.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pProductCode.add(scrollPane, BorderLayout.CENTER);
		
		table = new ProductCodeTable();
		scrollPane.setViewportView(table);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOutput2) {
			actionPerformedBtnOutput2(e);
		}
		if (e.getSource() == btnOutput1) {
			actionPerformedBtnOutput1(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	
	// 입력
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Product newPro = pWrite.getProduct();
		if (isExist(newPro)) {
			System.out.println("해당 메뉴가 존재합니다.");
			return;
		}
		proList.add(newPro);
		// System.out.println(proList); 테스트
		pWrite.clearTf();
		
		outputDlg1.setProduct(proList);
		outputDlg2.setProduct(proList);
	}
	
	// 중복입력 체크
	private boolean isExist(Product pro) {
		for (Product p : proList) {
			if (p.getCode().equals(pro.getCode())) {
				return true;
			}
		}
		return false;
	}
	
	// 출력1
	protected void actionPerformedBtnOutput1(ActionEvent e) {
		if (outputDlg1 == null) {
			outputDlg1 = new FranchiseOutputDlg1();
		}
		outputDlg1.setProduct(proList);
		outputDlg1.setVisible(true);
	}
	protected void actionPerformedBtnOutput2(ActionEvent e) {
		if (outputDlg2 == null) {
			outputDlg2 = new FranchiseOutputDlg2();
		}
		outputDlg2.setProduct(proList);
		outputDlg2.setVisible(true);
	}
}

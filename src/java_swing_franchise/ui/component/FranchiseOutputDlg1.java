package java_swing_franchise.ui.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java_swing_franchise.dto.Product;
import java_swing_franchise.ui.component.table.OutputTable;

@SuppressWarnings("serial")
public class FranchiseOutputDlg1 extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private OutputTable table;
	private JButton okButton;
	
	public void setProduct(ArrayList<Product> pro) {
		// System.out.println(pro); 테스트
		Comparator<Product> test = new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o2.getTotalPrice() - o1.getTotalPrice();
			}
		};
		
		Collections.sort(pro, test); // 판매금액으로 정렬
		
		int lank = 1;
		int num = 1;
		for (int i = 0; i < pro.size(); i++) {
			if (num < pro.size()) {
				if (pro.get(i).getTotalPrice() == pro.get(num).getTotalPrice()){
					pro.get(i).setNum(lank);
					num++;
					continue;
				}
			}
			num++;
			pro.get(i).setNum(lank++);
		}
		
		table.setProduct(pro);
	}

	public FranchiseOutputDlg1() {
		setBounds(100, 100, 755, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblTitle = new JLabel("판매금액순위");
				lblTitle.setFont(new Font("굴림", Font.BOLD, 18));
				panel.add(lblTitle);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new OutputTable();
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			actionPerformedOkButton(e);
		}
	}

	protected void actionPerformedOkButton(ActionEvent e) {
		dispose();
	}
}

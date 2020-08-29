package java_swing_franchise;

import java.awt.EventQueue;

import java_swing_franchise.ui.component.FranchiseManagement;

public class TestMain {
	
	public static void main(String[] args) {
		/*String str = "A001";
		String chart2 = String.valueOf(str.charAt(0));
		String str2 = chart2.toUpperCase();
		System.out.println(str2);
		
		String str3 = str.substring(1);
		System.out.println(str3);
		
		String str4 = str2 + str3;
		System.out.println(str4);*/
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FranchiseManagement frame = new FranchiseManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

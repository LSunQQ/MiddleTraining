import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame{
	
	private JButton allButtons[];
	private JTextField operationLeft, operationRight;
	
	private void addOperationListeners() {
		operationLeft.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
					return;
				} else {
					e.consume(); //屏蔽掉非数字输入
				}
			}
		});
		operationRight.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
					return;
				} else {
					e.consume(); //屏蔽掉非数字输入
				}
			}
		});
	}
	
	private void addOperatorListeners() {
//		for (int i = 3; i < 7; ++i) {
//			allButtons[i].addActionListener(new ActionListener() {
//				
//				public void actionPerformed(ActionEvent e) {
//					allButtons[0].setText(allButtons[i].getText());
//				}
//			});
//		}
		allButtons[3].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				allButtons[0].setText(allButtons[3].getText());
			}
		});
		allButtons[4].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				allButtons[0].setText(allButtons[4].getText());
			}
		});
		allButtons[5].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				allButtons[0].setText(allButtons[5].getText());
			}
		});
		allButtons[6].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				allButtons[0].setText(allButtons[6].getText());
			}
		});
		
	}
	
	private void addOKListeners() {
		allButtons[7].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double ans;
				double left = Double.parseDouble(operationLeft.getText());
				double right = Double.parseDouble(operationRight.getText());
				String sign = allButtons[0].getText();
				if (sign.equals("+")) {
					ans = left + right;
				} else if (sign.equals("-")) {
					ans = left - right;
				} else if (sign.equals("*")) {
					ans = left * right;
				} else {
					if (right == 0) {
						ans = 0;
						JFrame jFrame = new JFrame("Wrong Message");
						Container container = jFrame.getContentPane();
						JLabel jLabel = new JLabel("除数不能为零！");
						container.add(jLabel);
						jFrame.setVisible(true);
						jFrame.setSize(300, 150);
					} else {
						ans = left / right;
					}
				}
				allButtons[2].setText(ans + "");
			}
		});
	}
	
	public Calculator() {
		Container show = getContentPane();
		show.setLayout(new GridLayout(2, 5));
		allButtons = new JButton[8];
		
		allButtons[0] = new JButton(""); // show operator
		allButtons[1] = new JButton("=");
		allButtons[2] = new JButton(""); // show answer
		allButtons[3] = new JButton("+");
		allButtons[4] = new JButton("-");
		allButtons[5] = new JButton("*");
		allButtons[6] = new JButton("/");
		allButtons[7] = new JButton("OK");
		
		operationLeft = new JTextField(5);
		operationLeft.setHorizontalAlignment(JTextField.CENTER);
		operationRight = new JTextField(5);
		operationRight.setHorizontalAlignment(JTextField.CENTER);
		
		for (int i = 0; i < 3; ++i) {
			allButtons[i].setEnabled(false);
		}
		
	    show.add(operationLeft);
	    show.add(allButtons[0]);
	    show.add(operationRight);
	    for (int i = 1; i < 8; ++i) {
	    	show.add(allButtons[i]);
	    }
	    
	    addOperationListeners();
	    addOperatorListeners();
	    addOKListeners();
	    
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to use the Calculator!");
		Calculator test = new Calculator();
		test.setSize(400, 200);
		test.setTitle("Calculator");
		test.setVisible(true);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

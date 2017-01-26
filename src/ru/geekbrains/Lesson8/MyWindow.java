package ru.geekbrains.Lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

public class MyWindow extends JFrame {
	JTextField jtf;
	double d1;
	double d2;
	String operation;

	public MyWindow() {
		setTitle("My Window");
		setBounds(800, 400, 400, 500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		jtf = new JTextField();
		add(jtf, BorderLayout.NORTH);
		jtf.setEditable(false);
		jtf.setBackground(Color.WHITE);
		jtf.setFont(new Font("Arial", Font.PLAIN, 36));
		JPanel jpan = new JPanel();
		jpan.setLayout(new GridLayout(4, 5));

		String[] sb = {"7", "8", "9", "/", "<<", "4", "5", "6", "*", "+-", "1", "2", "3", "-", "%", "0", ".", "C", "+", "="};
		for (int i = 0; i < sb.length; i++) {
			JButton jb = new JButton(sb[i]);
			jb.setFont(new Font("Arial", Font.PLAIN, 24));
			jpan.add(jb);
			final String bn = sb[i];

			try {
				Integer.parseInt(bn);
				jb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addSymbol(bn);
					}
				});
				continue;
			} catch (Exception e) {
			}

			if (bn.equals("+") || bn.equals("-") || bn.equals("*") || bn.equals("/")) {
				jb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setOperation(bn);
					}
				});
				continue;
			}

			if(bn.equals("=")) {
				jb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ravno();
					}
				});
				continue;
			}
		}
		add(jpan, BorderLayout.CENTER);
		setVisible(true);
	}

	public void addSymbol(String s) { // 1
		jtf.setText(jtf.getText() + s);
	}

	public void setOperation(String operation) {
		this.operation = operation;
		if(d1 == 0.0) { // d1 = 150,
			d1 = Double.parseDouble(jtf.getText());
			jtf.setText("");
		} else {
			ravno();
			jtf.setText("");
		}
	}

	public void ravno() {
		double res = 0.0;
		d2 = Double.parseDouble(jtf.getText());
		switch (operation) {
			case "+":
				res = d1 + d2;
				break;
			case "-":
				res = d1 - d2;
				break;
			case "*":
				res = d1 * d2;
				break;
			case "/":
				res = d1 / d2;
				break;
		}
		d1 = res;
		d2 = 0.0;
		jtf.setText(String.valueOf(d1));
	}
}

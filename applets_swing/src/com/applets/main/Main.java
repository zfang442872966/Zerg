package com.applets.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	// FlowLayout类是最简单的布局管理器。它按照和页面上排列单词的类似方式来安排组件----从左到右，直至没有多余的空间，然后转到下一行。
	public static void addComponentsToPane(Container pane) {
		pane.setLayout(new FlowLayout());
		pane.add(new JButton("Button 1"));
		pane.add(new JButton("Button 2"));
		pane.add(new JButton("Button 3"));
		pane.add(new JButton("Long-Named Button 4"));
		pane.add(new JButton("5"));
	}

	// 一个BorderLayout对象将界面分成五大区域，分别用BorderLayout类的静态常量指定：-PAGE_START-PAGE_END-LINE_START-LINE_END-CENTER
	public static void addComponentsToPane2(Container pane) {
		JButton button = new JButton("Button 1 (PAGE_START)");
		pane.add(button, BorderLayout.PAGE_START);
		button = new JButton("Button 2 (CENTER)");
		button.setPreferredSize(new Dimension(200, 100));
		pane.add(button, BorderLayout.CENTER);
		button = new JButton("Button 3 (LINE_START)");
		pane.add(button, BorderLayout.LINE_START);
		button = new JButton("Long-Named Button 4 (PAGE_END)");
		pane.add(button, BorderLayout.PAGE_END);
		button = new JButton("5 (LINE_END)");
		pane.add(button, BorderLayout.LINE_END);
	}

	// BoxLayout可以将组件由上至下或由左至右依次加入当前面板
	public static void addComponentsToPane3(Container pane) {
		JPanel xPanel = new JPanel();
		xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.X_AXIS));
		addButtons(xPanel);
		JPanel yPanel = new JPanel();
		yPanel.setLayout(new BoxLayout(yPanel, BoxLayout.Y_AXIS));
		addButtons(yPanel);

		pane.add(yPanel, BorderLayout.PAGE_START);
		pane.add(xPanel, BorderLayout.PAGE_END);
	}

	private static void addAButton(String text, Container container) {
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(button);
	}

	private static void addButtons(Container container) {
		addAButton("Button 1", container);
		addAButton("Button 2", container);
		addAButton("Button 3", container);
		addAButton("Long-Named Button 4", container);
		addAButton("5", container);
	}

	// CardLayout卡片布局和其他布局不同，因为它隐藏了一些组件。卡片布局就是一组容器或者组件，它们一次仅仅显是一个，组中的每个容器称为卡片。
	public static void addComponentsToPane4(Container pane) {
		final JPanel contentPanel = new JPanel();
		JPanel controlPanel = new JPanel();
		final CardLayout cardLayout = new CardLayout();
		;
		pane.setLayout(new BorderLayout());
		pane.add(contentPanel, BorderLayout.CENTER);
		pane.add(controlPanel, BorderLayout.PAGE_END);
		controlPanel.setLayout(new FlowLayout());

		JButton[] b = new JButton[10];
		for (int i = 0; i < 10; i++) {
			b[i] = new JButton("No." + i);
			contentPanel.add(b[i]);
		}
		contentPanel.setLayout(cardLayout);
		JButton nextButton = new JButton("next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(contentPanel);
			}
		});
		controlPanel.add(nextButton);
	}

	// GridLayout让你建立一个组件表格，并且当组件加入时，会依序又左至右，由上至下填充到每个格子，它不能由你指定想放那个格子就放那个格子
	public static void addComponentsToPane5(Container pane) {
		JButton[] buttons = new JButton[9];
		pane.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(i + "");
			pane.add(buttons[i]);
		}
	}

	// GridBagLayout是所有AWT布局管理器当中最复杂的，同时他的功能也是最强大的。GridBagLayout同GridLayout一样，在容器中以网格形式来管理组件。但GridBagLayout功能要来得强大得多。
	public static void addComponentsToPane6(Container pane) {
		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		button = new JButton("Button 1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Long-Named Button 4");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40; // make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(button, c);

		button = new JButton("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 2; // 2 columns wide
		c.gridy = 2; // third row
		pane.add(button, c);
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("FlowLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set up the content pane.
		addComponentsToPane6(frame.getContentPane());
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}
}

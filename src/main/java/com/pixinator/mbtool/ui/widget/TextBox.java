package com.pixinator.mbtool.ui.widget;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.pixinator.mbtool.Utility;

public class TextBox extends JTextField implements DocumentListener {
	// ############################################################
	// # VARIABLES
	// ############################################################

	private String textChangedMethodName;
	private Object textChangedMethodSrc;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public TextBox() {
		super();
		super.getDocument().addDocumentListener(this);

		super.setBorder(
				new CompoundBorder(new MatteBorder(2, 2, 2, 2, Utility.COL_E_BORDER_M), new EmptyBorder(2, 2, 2, 2)));
		super.setForeground(Color.BLACK);
		super.setSelectionColor(Utility.COL_E_MAIN);
		super.setSelectedTextColor(Color.WHITE);

		super.setText("");
	}

	// ############################################################
	// # METHODS
	// ############################################################

	@Override
	public void changedUpdate(DocumentEvent e) {
		this.callMethod();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.callMethod();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.callMethod();
	}

	private void callMethod() {
		if (this.textChangedMethodName != null && !this.textChangedMethodName.equals("")) {
			Method method;
			try {
				method = this.textChangedMethodSrc.getClass().getMethod(this.textChangedMethodName, Object.class);
				method.invoke(this.textChangedMethodSrc, this);
			} catch (NoSuchMethodException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (InvocationTargetException ex) {
				ex.printStackTrace();
			}
		}
	}

	// ############################################################
	// # GETTERS, SETTERS
	// ############################################################

	public void setTextChanged(String methodName, Object methodSrc) {
		this.textChangedMethodName = methodName;
		this.textChangedMethodSrc = methodSrc;
	}

}

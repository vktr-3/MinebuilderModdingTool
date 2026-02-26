package com.pixinator.mbtool.ui.widget;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.pixinator.mbtool.utils.Utils;

public class IntTextBox extends JTextField implements DocumentListener {
	// ############################################################
	// # VARIABLES
	// ############################################################

	private String textChangedMethodName;
	private Object textChangedMethodSrc;

	private boolean nullable;
	private List<Integer> blackList;
	private int minValue;
	private int maxValue;

	private boolean valid;
	private ErrorType errorType;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public IntTextBox() {
		super();
		super.getDocument().addDocumentListener(this);

		super.setBorder(
				new CompoundBorder(new MatteBorder(2, 2, 2, 2, Utils.COL_E_BORDER_M), new EmptyBorder(2, 2, 2, 2)));
		super.setForeground(Color.BLACK);
		super.setSelectionColor(Utils.COL_E_MAIN);
		super.setSelectedTextColor(Color.WHITE);

		this.nullable = true;
		this.blackList = new ArrayList<Integer>();
		this.minValue = Integer.MIN_VALUE;
		this.maxValue = Integer.MAX_VALUE;

		super.setText("0");
	}

	// ############################################################
	// # METHODS
	// ############################################################

	private void validateText() {
		try {
			int value = Integer.parseInt(super.getText());

			// CHECK IF VALUE IS IN RANGE
			this.valid = value >= this.minValue && value <= this.maxValue;
			if (!valid) {
				this.errorType = ErrorType.OutOFRange;
			}

			// CHECK IF VALUE IS
			for (int item : this.blackList) {
				if (value == item) {
					valid = false;
					break;
				}
			}
			if (!valid) {
				this.errorType = ErrorType.BlackListValue;
			}

		} catch (Exception e) {
			if (this.nullable) {
				this.valid = super.getText() == null || super.getText().isEmpty();
				if (!this.valid) {
					this.errorType = ErrorType.InvalidCharacters;
				}
			} else {
				this.valid = false;
				this.errorType = ErrorType.InvalidCharacters;
			}
		}

		if (this.valid) {
			super.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, Utils.COL_E_BORDER_M),
					new EmptyBorder(2, 2, 2, 2)));
		} else {
			super.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, Color.RED), new EmptyBorder(2, 2, 2, 2)));
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		this.validateText();
		this.callMethod();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.validateText();
		this.callMethod();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.validateText();
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

	public void clearBlackList() {
		this.blackList.clear();

		this.validateText();
	}

	public void addBlackListItem(int item) {
		this.blackList.add(item);

		this.validateText();
	}

	public void removeBlackListItem(int item) {
		this.blackList.remove((Integer) item);

		this.validateText();
	}

	// ############################################################
	// # GETTERS, SETTERS
	// ############################################################

	public void setTextChanged(String methodName, Object methodSrc) {
		this.textChangedMethodName = methodName;
		this.textChangedMethodSrc = methodSrc;
	}

	public boolean isNullable() {
		return this.nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;

		this.validateText();
	}

	public int getMinValue() {
		return this.minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;

		this.validateText();
	}

	public int getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;

		this.validateText();
	}

	public boolean isValid() {
		return this.valid;
	}

	public ErrorType getErrorType() {
		return this.errorType;
	}

	// ############################################################
	// # ENUMS
	// ############################################################

	public enum ErrorType {
		OutOFRange, InvalidCharacters, BlackListValue
	}

}

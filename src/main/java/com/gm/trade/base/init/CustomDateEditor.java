package com.gm.trade.base.init;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CustomDateEditor extends PropertyEditorSupport {

	private final DateFormat longDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final DateFormat shortDateFormat=new SimpleDateFormat("yyyy-MM-dd");

	private final boolean allowEmpty=true;

	private final int maxDateLength=19;



	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}
		else if (text != null && this.maxDateLength >= 0 && text.length() > this.maxDateLength) {
			throw new IllegalArgumentException(
					"Could not parse date: it is  longer than" + this.maxDateLength + " characters ");
		}
		else {

			try {
				if (text.length() > 10) {
					setValue(this.longDateFormat.parse(text));
				} else {
					setValue(this.shortDateFormat.parse(text));

				}

			}
			catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? this.longDateFormat.format(value) : "");
	}

}

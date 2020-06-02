package com.exercise.course.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementation of the rut validation
 * @author Luis San Martin
 *
 */
public class RutValidator implements ConstraintValidator<Rut, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!value.matches("^[0-9]+[-|‐]{1}[0-9kK]{1}$")) {
			return false;
		}
		String[] valueSplitted = value.split("-");
		double rut = Double.parseDouble(valueSplitted[0]);
		String dv = valueSplitted[1];
		if (dv.equals("K")) {
			dv = "k";
		}

		return getDv(rut).equals(dv);
	}

	public String getDv(double rut) {

		Integer c = 0;
		double calculatedDv = 1d;

		for (; rut > 0; rut = Math.floor(rut / 10)) {
			calculatedDv = (calculatedDv + rut % 10 * (9 - c++ % 6)) % 11;
		}

		return calculatedDv > 0 ? String.valueOf((int) (calculatedDv - 1)) : "k";
	}

}

package com.progresssoft.yazan.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Currency;

public class CurrencyCodeValidator implements ConstraintValidator<CurrencyCode, String> {
    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext context) {
        if (currencyCode == null) {
            return false;
        }

        try {
            Currency.getInstance(currencyCode);
            return true; // Currency code is valid
        } catch (IllegalArgumentException e) {
            return false; // Currency code is invalid
        }
    }
}

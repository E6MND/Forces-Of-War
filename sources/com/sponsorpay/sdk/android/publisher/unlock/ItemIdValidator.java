package com.sponsorpay.sdk.android.publisher.unlock;

import java.util.regex.Pattern;

public class ItemIdValidator {
    private Pattern mCompiledPattern;
    private String mValue;

    public ItemIdValidator(String itemId) {
        setValue(itemId);
    }

    public void setValue(String value) {
        String previousValue = this.mValue;
        this.mValue = value;
        if (value == null || !value.equals(previousValue)) {
            this.mCompiledPattern = null;
        }
    }

    public boolean validate() {
        if (this.mValue == null || "".equals(this.mValue)) {
            return false;
        }
        if (this.mCompiledPattern == null) {
            this.mCompiledPattern = Pattern.compile("^[A-Z0-9_]+$");
        }
        return this.mCompiledPattern.matcher(this.mValue).find();
    }

    public String getValidationDescription() {
        return "An Unlock Item ID can only contain uppercase letters, numbers and the _ underscore symbol.";
    }
}

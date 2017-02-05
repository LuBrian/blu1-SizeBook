package com.assign1.brianlu.sizebook;

/**
 * Created by brianlu on 2017-02-01.
 */

public class NoNegativeValueException extends Exception {
    public NoNegativeValueException() {
    }

    public NoNegativeValueException(String detailMessage) {
        super(detailMessage);
    }
}

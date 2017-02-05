package com.assign1.brianlu.sizebook;

/**
 * Created by brianlu on 2017-02-01.
 */

public class StringTooLongException extends Exception {
    public StringTooLongException() {
    }

    public StringTooLongException(String detailMessage) {
        super(detailMessage);
    }
}

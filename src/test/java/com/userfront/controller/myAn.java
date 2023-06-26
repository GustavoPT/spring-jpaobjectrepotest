package com.userfront.controller;

import java.lang.annotation.Annotation;

public class myAn implements Annotation {

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {

        return null;
    }
}

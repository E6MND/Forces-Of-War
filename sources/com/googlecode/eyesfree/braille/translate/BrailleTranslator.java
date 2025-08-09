package com.googlecode.eyesfree.braille.translate;

public interface BrailleTranslator {
    String backTranslate(byte[] bArr);

    byte[] translate(String str);
}

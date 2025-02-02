package com.practice.urlshorten.generator;

public class UniqueCodeGen {
    private static final String base64 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateCode(int size){
        StringBuilder code = new StringBuilder(size);
        for(int i = 0; i < size; i++){
            code.append(base64.charAt((int) (Math.random() * base64.length())));
        }
        return code.toString();
    }
}

package com.petclinicrest.utils;

public class GenerateNo {

    private static final long LIMIT = 10000000000L;
    private static long last = 0;

    public static long createNo() {
        // 10 digits.
        long no = System.currentTimeMillis() % LIMIT;
        if ( no <= last ) {
            no = (last + 1) % LIMIT;
        }
        return last = no;
    }

}

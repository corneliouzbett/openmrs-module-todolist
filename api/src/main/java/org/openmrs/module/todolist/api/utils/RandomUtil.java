package org.openmrs.module.todolist.api.utils;

import java.security.SecureRandom;

public class RandomUtil {

    private static final long MSB = 0x8000000000000000L;
    // Maxim: Copied from UUID implementation :)
    private static volatile SecureRandom numberGenerator = null;

    public static String unique() {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }

        return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
    }
}

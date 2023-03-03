package org.iproute.windows11.utils;

import java.io.IOException;

/**
 * CmdUtils
 *
 * @author zhuzhenjie
 * @since 2022/7/1
 */
public class CmdUtils {
    public static void restart() {
        try {
            Runtime.getRuntime().exec("shutdown -r -t 0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void shutdown() {
        try {
            Runtime.getRuntime().exec("shutdown /p");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

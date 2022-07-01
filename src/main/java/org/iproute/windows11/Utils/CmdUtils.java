package org.iproute.windows11.Utils;

import java.io.IOException;

/**
 * CmdUtils
 *
 * @author zhuzhenjie
 * @since 2022/7/1
 */
public class CmdUtils {
    public static void restartComputer() {
        try {
            Runtime.getRuntime().exec("shutdown -r -t 0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

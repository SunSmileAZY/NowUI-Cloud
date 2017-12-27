package com.nowui.cloud.constant;

/**
 * @author ZhongYongQiang
 */
public class Constant {

    public static String PRIVATE_KEY;

    public static final String CODE = "code";

    public static final String TOTAL = "total";

    public static final String DATA = "data";

    public static final String LIST = "list";

    public static final String MESSAGE = "message";

    public static final int DEFAULT_LOAD_FACTOR = 1;

    static {
        PRIVATE_KEY = System.getenv("PRIVATE_KEY");

        if (PRIVATE_KEY == null) {
            throw new RuntimeException("私密不能为空");
        }
    }

}

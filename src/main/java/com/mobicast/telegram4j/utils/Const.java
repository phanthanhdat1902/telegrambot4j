package com.mobicast.telegram4j.utils;

import java.util.HashMap;
import java.util.Map;

public class Const {
    public static final class ACTION {
        public static final String DELETE_BRANCH = " đã xóa nhánh ";
    }

    public static final class TELEGRAM {
        public static final String URL = "api.telegram.org";
        public static final String GROUP_ID = "-784751066";
        public static final String BOT_SEGMENT = "bot5762714286:AAFSGe7nZiuM6kfCXNkmEmamXqfeo4KZhSQ";
        public static final String ACTION = "sendMessage";
    }

    public static final class GITLAB {
        public static final String HASH_CODE_DELETE_BRANCH = "0000000000000000000000000000000000000000";
        public static final String REQUEST_MERGE_STATUS = "preparing";
        public static final String MERGE_DONE = "can_be_merged";
    }

    public static final class TELEGRAM_USER_ID {
        public static Map<String, String> TELEGRAM_USER = new HashMap<String, String>() {{
            put("hoangvm", "hoangvm1");
            put("datpt19", "datpt19");
        }};
    }
}

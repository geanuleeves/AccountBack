package com.wildb.account.common.utils;

import com.wildb.account.common.exceptions.RRException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 *
 * 数据校验
 * author 郑邦振
 */
public class Assert {
    public static void isBlank(String str, String err) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(err, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    public static void isNull(Object object, String err) {
        if (object == null) {
            throw new RRException(err, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }
}

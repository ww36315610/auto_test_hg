package com.test.hg.restassured.wechart.comment;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 生成时间戳
 */
public class TimerTools {

    //时间戳
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    //三个随机字母
    public static String selectAChar() {
        String s = "a1q2w3e4r5t6y7u8i9o0plkjhgfdsxcvbnmz";
        Random random = new Random();
        int index = random.nextInt(s.length());
        String a = String.valueOf(s.charAt(index));
        int index1 = random.nextInt(s.length());
        String b = String.valueOf(s.charAt(index1));
        int index2 = random.nextInt(s.length());
        String c = String.valueOf(s.charAt(index2));
        String d = "";
        d = d.concat(a).concat(b).concat(c);
        return d;

    }
}

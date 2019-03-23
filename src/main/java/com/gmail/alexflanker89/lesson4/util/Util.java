package com.gmail.alexflanker89.lesson4.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;
import java.util.regex.Pattern;

@UtilityClass
public class Util {
    public static int getNumAnswer(String answer) {
        Pattern pattern = Pattern.compile("^\\d+$");
        if (pattern.matcher(answer).matches()) {
            return Integer.parseInt(answer);
        } else {
            return -1;
        }

    }


    public static boolean checkUserData(String input) {
        if (StringUtils.isEmpty(input)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^(\\w+)\\s(\\w+)$");
        return pattern.matcher(input).matches();

    }
}

package com.example;

import java.util.ArrayList;

public class App {

    public static String sakir(int[] nums, ArrayList<Integer> index, String target, boolean isReversed) {
        if (nums == null || index == null || target == null) {
            return null;
        }
        for (int i = 0; i < index.size(); i++) {
            if (index.get(i) >= target.length()) {
                target += (char) nums[i];
            } else if (index.get(i) >= 0) {
                String left, right;
                char changer = (char) ((int) target.charAt(index.get(i)) + nums[i]);
                left = target.substring(0, index.get(i));
                right = target.substring(index.get(i) + 1);
                target = left + changer + right;
            }
        }
        if (isReversed) {
            int len = target.length();
            for (int i = len - 1; i >= 0; i--) {
                target += target.charAt(i);
            }
            target = target.substring(len);
        }
        return target;
    }
}
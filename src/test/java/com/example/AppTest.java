package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class AppTest {

    ArrayList<Integer> index = new ArrayList<>();
    int[] nums = new int[100];
    String target = "deneme";

    @Test
    public void testIndex() {
        for (int i = 0; i < target.length(); i++) {
            index.add(i);
            nums[i] = 1;
        }
        assertEquals("efofnf", App.sakir(nums, index, "deneme", false));
    }

    @Test
    public void testnums() {
        ArrayList<Integer> index = new ArrayList<>();
        int[] nums = new int[] { 75, 101, 114, 101, 77, 95, 107, 97, 122, 65, 110, 100, 105, 82 };
        for (int i = 0; i < 14; i++) {
            index.add(i);
        }

        assertEquals("KereM_kazAndiR", App.sakir(nums, index, "", false));
    }

    @Test
    public void testReverse() {
        ArrayList<Integer> index = new ArrayList<>();
        int[] nums = new int[100];
        assertEquals("aykavols", App.sakir(nums, index, "slovakya", true));
    }

    @Test
    public void testNegative() {
        ArrayList<Integer> index = new ArrayList<>();
        int[] nums = new int[100];
        for (int i = 0; i < 10; i++) {
            index.add(i - 9);
            nums[i] = 1;
        }
        assertEquals("halatasaray", App.sakir(nums, index, "galatasaray", false));
    }

    // null tester
    @Test
    public void testNull() {
        assertEquals(null, App.sakir(null, index, "denemeler", false));
    }

}
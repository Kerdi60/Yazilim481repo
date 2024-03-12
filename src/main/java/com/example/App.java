package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

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

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Kerem's App");

        post("/compute", (req, res) -> {
            // System.out.println(req.queryParams("input1"));
            // System.out.println(req.queryParams("input2"));

            String input1 = req.queryParams("input1");
            java.util.Scanner sc1 = new java.util.Scanner(input1);
            sc1.useDelimiter("-");
            int[] input1asArr = new int[100];
            for (int i = 0; sc1.hasNextInt(); i++) {
                input1asArr[i] = sc1.nextInt();
            }

            String input2 = req.queryParams("input2");
            java.util.Scanner sc2 = new java.util.Scanner(input2);
            sc2.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> input2List = new java.util.ArrayList<>();
            while (sc2.hasNext()) {
                int value = Integer.parseInt(sc2.next().replaceAll("\\s", ""));
                input2List.add(value);
            }
            System.out.println(input2List);

            String input3 = req.queryParams("input3").replaceAll("\\s", "");

            String input4 = req.queryParams("input4");
            Boolean input4asBool;
            if (input4.equals("T"))
                input4asBool = true;
            else
                input4asBool = false;

            String result = App.sakir(input1asArr, input2List, input3, input4asBool);

            Map map = new HashMap();
            map.put("result", result);
            map.put("oldStr", input3);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());

        get("/compute",
                (rq, rs) -> {
                    Map map = new HashMap();
                    map.put("oldStr", "not computed yet!");
                    map.put("result", "not computed yet!");
                    return new ModelAndView(map, "compute.mustache");
                },
                new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}
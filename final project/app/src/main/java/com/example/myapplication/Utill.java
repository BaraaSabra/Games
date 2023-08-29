package com.example.myapplication;

public class Utill {
    public static Question generteQuestion() {
        String[][] x = new String[3][3];
        int startNumber = (int) (Math.random() * 10) + 1;
        int incStrtNumber = (int) (Math.random() * 5) + 1;
        int stredNamer;

        int number = -1;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                stredNamer = startNumber + incStrtNumber;

                if (i == 1 && j == 1) {
                    x[i][j] = "??";
                    number = startNumber;

                } else {
                    x[i][j] = startNumber + "";

                }
                incStrtNumber += 2;
               startNumber=stredNamer;
            }

        }
        return new Question(x,number);
    }
}

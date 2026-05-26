package javaSDET;

import java.util.ArrayList;
import java.util.List;

public class Topic_08_For {
    public static void main(String[] args) {
        //Biểu thức vòng lặp (loop)
        int number = 100;
        //for (classic - iterator)
        for (int i = 0;i<number;i++){
            System.out.println(i);
        }

        //Collection: List/Set/Queue/Map
        List<String> name = new ArrayList<String>();
        name.add("Manual testing");
        name.add("Automation testing");
        name.add("UI testing");
        name.add("API testing");
        //for-each
        for(String a:name){
            System.out.println(a);
        }

        //do-while
        int i = 0;
        do {
            System.out.println(i);
            i++;
        }while (i < number);

        //while
        while (i < number){
            System.out.println(i);
            i++;
        }
    }
}

package javaSDET;

import com.sun.jdi.FloatType;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.*;

public class Topic_09_List {
    public static void main(String[] args) {
        // Java collection
        RemoteWebDriver driver;//cha
        driver = new FirefoxDriver();//con
        driver = new ChromeDriver();//con

        ArrayList<String> arrayList = new ArrayList<>();
        Vector<Float> studentPoit = new Vector<>();
        LinkedList<Integer> studentAge = new LinkedList<>();
        Stack<Boolean> status = new Stack<>();

        //interface List là cha của class Stack
        List<String> studentName = new Stack<>();

        List<String> address = new ArrayList<String>();
        address.add("Ho Chi Minh");
        address.add("Da Nang");
        address.add("Hue");
        address.add("Ha Noi");
        address.add("Hai Phong");

        //for
        for (int i = 0; i< address.size();i++){
            System.out.println(address.get(i));
        }
        //for-each
        for(String a: address){
            System.out.println(a);
        }
    }
}

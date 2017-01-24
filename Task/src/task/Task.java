/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author IPC2
 */
class items {
    public int itemNo;
    public int satisfaction;
    public int time;
    public float averageSatisfaction;

    public items() {
        satisfaction = 0;
        time = 0;
        averageSatisfaction = 0.0f;
    }
};

public class Task {

    static int maximumTimeAvailable = 0;
    static int satisfactionGot = 0;
    static int noOfDishes = 0;
    static int completedTime = 0;

    public static void doinsertionSort(ArrayList<items> input) {
        //System.out.println("input size: " + input.size());
        items itemTemp;
        for (int i = 1; i < input.size(); ++i) {
            for (int j = i; j > 0; j--) {
                if (input.get(j).averageSatisfaction < input.get(j - 1).averageSatisfaction) {
                    itemTemp = input.get(j);
                    input.set(j, input.get(j - 1));
                    input.set(j - 1, itemTemp);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<items> itemList = new ArrayList<items>();
        ArrayList<items> satisFiedList = new ArrayList<items>();

        try {
            Scanner sc = new Scanner(new File("./data.txt"));
            maximumTimeAvailable = sc.nextInt();
            noOfDishes = sc.nextInt();
            System.out.println("maximumTimeAvailable: " + maximumTimeAvailable);
            System.out.println("noOfDishes: " + noOfDishes);
            while (sc.hasNext()) {
                items item = new items();
                item.satisfaction = sc.nextInt();
                item.time = sc.nextInt();
//                System.out.println("satisfaction: " + item.satisfaction
//                        + "\t  time: " + item.time);
                item.averageSatisfaction = item.satisfaction;
                item.averageSatisfaction = item.averageSatisfaction / item.time;
                item.itemNo = itemList.size() + 1;
                itemList.add(item);
            }
            sc.close();
            doinsertionSort(itemList);

            for (int i = itemList.size() - 1; i >= 0; --i) {
                if (completedTime + itemList.get(i).time <= maximumTimeAvailable) {
                    completedTime += itemList.get(i).time;
                    satisfactionGot += itemList.get(i).satisfaction;
                    satisFiedList.add(itemList.get(i));
                }
//            System.out.println("satisfaction: " + itemList.get(i).satisfaction
//                    + "\t  time: " + itemList.get(i).time + "\t  Average: " + itemList.get(i).averageSatisfaction);
            }

//            for (int i = satisFiedList.size() - 1; i >= 0; --i) { 
//                System.out.println("satisfaction: " + satisFiedList.get(i).satisfaction
//                        + "\t  time: " + satisFiedList.get(i).time + "\t  Average: " + satisFiedList.get(i).averageSatisfaction);
//            }
            System.out.println("Given Time: "+ maximumTimeAvailable
                    +"\tTaken Time: " + completedTime + " \t Satisfaction Got:" + satisfactionGot);
        } catch (Exception ex) {
            System.out.println("ex: " + ex.getMessage());
        }
    }
}

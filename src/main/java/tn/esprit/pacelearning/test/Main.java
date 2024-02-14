package tn.esprit.pacelearning.test;

import tn.esprit.pacelearning.utils.MyDatabase;

public class Main {
    public static void main (String [] args){
    MyDatabase db = MyDatabase.getInstance();
        MyDatabase db2 = MyDatabase.getInstance();
        System.out.println(db);

    }
}

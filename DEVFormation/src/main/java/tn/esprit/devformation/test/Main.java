package tn.esprit.devformation.test;

import tn.esprit.devformation.models.Formation;
import tn.esprit.devformation.services.FormationService;
import tn.esprit.devformation.services.Iservice;
import tn.esprit.devformation.utils.MyDataBase;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        MyDataBase db = MyDataBase.getInstance();
        /*Formation f=new Formation("GL");
        Iservice I = new FormationService();
        I.add(f);*/
        Formation f1 = new Formation(6,"ALGO");
        //Formation f2 = new Formation("GL");
        Iservice I1 = new FormationService();
        //I1.add(f1);
        I1.update(f1);
        /*Iservice I = new FormationService();
        I.delete(3);*/
        Iservice I = new FormationService();
        ArrayList<Formation> l =new ArrayList<>();
        l= (ArrayList<Formation>) I.getAll();
    }
}

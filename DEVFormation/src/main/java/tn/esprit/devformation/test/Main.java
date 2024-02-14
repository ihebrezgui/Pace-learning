package tn.esprit.devformation.test;

import tn.esprit.devformation.models.Chapitre;
import tn.esprit.devformation.models.Formation;
import tn.esprit.devformation.services.ChapitreService;
import tn.esprit.devformation.services.FormationService;
import tn.esprit.devformation.models.Cours;
import tn.esprit.devformation.services.CoursService;
import tn.esprit.devformation.services.Iservice;
import tn.esprit.devformation.utils.MyDataBase;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        MyDataBase db = MyDataBase.getInstance();
        Iservice I1 = new FormationService();
        Iservice I2 = new CoursService();
        /*Formation f=new Formation("GL");
        Iservice I = new FormationService();
        I.add(f);
        Formation f1 = new Formation("ALGO");
        Formation f2 = new Formation("GL");
        Iservice I1 = new FormationService();
        I1.update(f2);
        Iservice I = new FormationService();
        I.delete(3);
        Iservice I = new FormationService();
        ArrayList<Formation> l =new ArrayList<>();
        l= (ArrayList<Formation>) I.getAll();
        Formation f=new Formation("Devops");
        Iservice I = new FormationService();
        I.add(f);
        Cours c=new Cours(1,"lecon4","Duree : 5 semaines , HTML","Devops",500);
        I1.delete(5);
       ArrayList<Formation> l =new ArrayList<>();
        l= (ArrayList<Formation>) I1.getAll();
        I1.sort(l);
        Cours c1=new Cours(1,"lecon3","Duree : 20 semaines , CSS ","Devops",1000);
        I1.add(c1);
        ArrayList<Cours> c =new ArrayList<>();
        c= (ArrayList<Cours>) I2.getAll();
        I2.sortByCategorie(c);
        Chapitre ch1 = new Chapitre(1,"Introduction","cours de 16 hrs : DEVOPS") ;
        Chapitre ch2 = new Chapitre(3,"Introduction","cours de 10 hrs : PYTHON") ;
        Chapitre ch3 = new Chapitre(1,"Chap1","cours de 10 : DEVOPS") ;

        I.add(ch1);
        I.add(ch2);
        I.add(ch3);
        ChapitreService I = new ChapitreService();
        ArrayList<Chapitre> c =new ArrayList<>();
        c= (ArrayList<Chapitre>) I.getAll();
        I.sortByCategorie(c);*/
        ArrayList<Formation> L =new ArrayList<>();
        FormationService f = new FormationService();
        L = f.search("PYTHON");
        ArrayList<Cours> C =new ArrayList<>();
        CoursService d = new CoursService();
        C = d.search("DEVOPS");

    }
}
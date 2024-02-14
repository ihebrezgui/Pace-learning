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


        FormationService I1 = new FormationService();
        CoursService I2 = new CoursService();
        ChapitreService I = new ChapitreService();
        //Formations

        //ADD FORMATION
        Formation f=new Formation("GL");
        I1.add(f);
        Formation f1 = new Formation("ALGO");
        Formation f2 = new Formation("GL");
        //UPDATE FORMATION
        I1.update(f2);
        //DELETE FORMATION
        I1.delete(3);
        //DISPLAY
        ArrayList<Formation> l =new ArrayList<>();
        l= (ArrayList<Formation>) I1.getAll();
        //SORT
        I1.sortByCategorie(l);

        //COURSES
        Cours c=new Cours(1,"lecon4","Duree : 5 semaines , HTML","Devops",500);
        Cours c1=new Cours(1,"lecon3","Duree : 20 semaines , CSS ","Devops",1000);
        //ADD
        I2.add(c1);
        //DISPLAY + SORT
        ArrayList<Cours> c =new ArrayList<>();
        c= (ArrayList<Cours>) I2.getAll();
        I2.sortByCategorie(c);



        //Chapters
        Chapitre ch1 = new Chapitre(1,"Introduction","cours de 16 hrs : DEVOPS") ;
        Chapitre ch2 = new Chapitre(3,"Introduction","cours de 10 hrs : PYTHON") ;
        Chapitre ch3 = new Chapitre(1,"Chap1","cours de 10 : DEVOPS") ;
        //ADD
        I.add(ch1);
        I.add(ch2);
        I.add(ch3);
        //DISPLAY + SORT
        ArrayList<Chapitre> ch =new ArrayList<>();
        ch= (ArrayList<Chapitre>) I.getAll();
        I.sortByCategorie(ch);





        //TEST SEARCH (formation + cours )
        ArrayList<Formation> L =new ArrayList<>();
        FormationService fs = new FormationService();
        L = fs.search("PYTHON");
        ArrayList<Cours> C =new ArrayList<>();
        CoursService d = new CoursService();
        C = d.search("DEVOPS");

    }
}
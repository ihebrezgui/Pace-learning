package tn.esprit.formation.utils;

import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.ChapitreService;
import esprit.tn.formation.services.FormationService;
import esprit.tn.formation.models.Cours;
import esprit.tn.formation.services.CoursService;
import esprit.tn.formation.utils.MyDataBase;

import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        MyDataBase db = MyDataBase.getInstance();


        FormationService I1 = new FormationService();
        CoursService I2 = new CoursService();
        ChapitreService I = new ChapitreService();
        //Formations

        //ADD FORMATION
        Formation f=new Formation("DEVOPS");
        /*I1.add(f);
        Formation f1 = new Formation("ALGO");
        Formation f2 = new Formation("GL");
        //UPDATE FORMATION
         I1.update(f);*/
        //DELETE FORMATION
        //I1.delete(16);/*
        //DISPLAY
        ArrayList<Formation> l =new ArrayList<>();
        l= (ArrayList<Formation>) I1.getAll();
        //SORT
        // I1.sortByCategorie(l);

        //COURSES
        Cours c=new Cours(2,"lecon4","Duree : 5 semaines , HTML","Devops",250);
        Cours c1=new Cours(2,"lecon3","Duree : 20 semaines , CSS ","Devops",850);
        //ADD
        I2.add(c);
        I2.add(c1);
        //DISPLAY + SORT
        ArrayList<Cours> cl =new ArrayList<>();
        cl= (ArrayList<Cours>) I2.getAll();
        I2.search("Devops");
        //I2.sortByPrice(cl);


        /*
        //Chapters
        Chapitre ch1 = new Chapitre(13,"Introduction","cours de 16 hrs : DEVOPS") ;
        //Chapitre ch2 = new Chapitre(5,"Introduction","cours de 10 hrs : PYTHON") ;
        //Chapitre ch3 = new Chapitre(5,"Chap1","cours de 10 : DEVOPS") ;
        //ADD
        I.add(ch1);
        //I.add(ch2);
        //I.add(ch3);
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

        //Test Delete CASCADE
        I1.delete(1);
        I2.getAll();*/

    }
}
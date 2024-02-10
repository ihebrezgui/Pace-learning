package com.example.guser.test;
import java.sql.SQLException;
import com.example.guser.utils.MyDataBase;

public class main {

        public static void main(String[] args){
        MyDataBase db1= MyDataBase.getInstance();
        db1 = new MyDataBase();

        System.out.println(db1.hashCode());

    }
}

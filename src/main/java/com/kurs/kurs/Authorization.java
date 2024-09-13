package com.kurs.kurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Objects;

public class Authorization {
    private String name;
    private String password;
    private String date;
    public Authorization(){

    }
    public boolean login(String name,String password){
        this.name = name;
        this.password = password;
        DB database = new DB();
        database.connectToDb();

        if(database.checkUser(this.name,this.password)){
            System.out.println("You authorized");
            return true;
        }
        else {
            System.out.println("Anser wrong or user dont exist");
            return false;
        }
    }
    public void registration(String name,String password,String date){
        this.name = name;
        this.password = password;
        this.date=  date;
        DB database = new DB();
        database.connectToDb();

        if(database.checkUser(this.name,this.password,this.date)){
            System.out.println("User already exists");
        }
        else {
            database.insertUser(this.name,this.password,this.date);
            System.out.println("User registered");
        }
    }
}

package com.kurs.kurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DB {
    private String db_name;
    private String db_user;
    private String db_password;
    private String db_url;
    private String host_url;
    private Connection db_con;
    public DB()
    {
        db_name = "library";
        db_user = "postgres";
        db_password = "root";
        db_url = "jdbc:postgresql://localhost:5432/"+db_name;
        host_url = "jdbc:postgresql://localhost:5432/";
        db_con = null;
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public void connectToDb()
    {
        try{
            db_con = DriverManager.getConnection(db_url, db_user, db_password);
        }
        catch (org.postgresql.util.PSQLException e){
            System.out.println("DB isn't initialized");
            initNewDb();
            initTablesInDb();
            fillTags(new ArrayList<String>(Arrays.asList("Млекопитающее", "Птица", "Пресмыкающееся", "Земноводное", "Рыба")));
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    private void initTablesInDb(){
        String request = "create table tags (\n" +
                "            id SERIAL PRIMARY KEY,\n" +
                "            name VARCHAR(30)\n" +
                "            );\n"+
                "create table animals (\n" +
                "            id SERIAL PRIMARY KEY,\n" +
                "            name VARCHAR(100),\n" +
                "            owner VARCHAR(100),\n" +
                "            price INT,\n" +
                "            age INT,\n" +
                "            tags VARCHAR(30)[],\n" +
                "            bornDate DATE,\n" +
                "            description VARCHAR(1000)\n" +
                "            );\n" +
                "create table request_animals (\n" +
                "            id SERIAL PRIMARY KEY,\n" +
                "            name VARCHAR(100),\n" +
                "            owner VARCHAR(100),\n" +
                "            price INT,\n" +
                "            age INT,\n" +
                "            tags VARCHAR(30)[],\n" +
                "            bornDate DATE,\n" +
                "            description VARCHAR(1000)\n" +
                "            );\n" +
                "create table logins (\n" +
                "              id SERIAL PRIMARY KEY,\n" +
                "              login VARCHAR(100),\n" +
                "              password VARCHAR(100),\n" +
                "              role VARCHAR(100) DEFAULT 'USER',\n" +
                "              balance INT DEFAULT 0,\n" +
                "              animals INT DEFAULT 0,\n" +
                "              last_enter DATE\n," +
                "              birthday DATE\n" +
                "            );\n" +
                "            create table authors (\n" +
                "              id SERIAL PRIMARY KEY,\n" +
                "              name VARCHAR(100),\n" +
                "              animals INT DEFAULT 0,\n" +
                "              maximum_age INT DEFAULT 0\n" +
                "            );"+
                "create table purchases (\n" +
                "            id SERIAL PRIMARY KEY,\n" +
                "            name VARCHAR(100),\n" +
                "            owner INT,\n" +
                "            price INT,\n" +
                "            age INT,\n" +
                "            tags VARCHAR(30)[],\n" +
                "            bornDate DATE,\n" +
                "            description VARCHAR(1000)\n" +
                "            );\n";

        try{
            db_con = DriverManager.getConnection(db_url,db_user,db_password);
            Statement st = db_con.createStatement();
            st.execute(request);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
    private void initNewDb(){
        String request = "create database library;";

        try{
            Connection tempCon = DriverManager.getConnection(host_url,db_user,db_password);
            Statement st = tempCon.createStatement();
            st.execute(request);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public boolean checkUser(String name, String password,String date){
        try {
            Statement st = db_con.createStatement();
            String query = "SELECT * FROM logins WHERE login = \'" + name+"\' AND password = \'"+
                    password + "\' AND birthday = " + "\'"+date+"\';";
            st.executeQuery(query);
            ResultSet rs = st.getResultSet();
            if(rs.next()){
                System.out.println("data exist in db");
                System.out.println();
                return true;
            }
            else {
                System.out.println("data dont exist in db");
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
            return false;
        }


    }
    public boolean checkUser(String name, String password){
        try {
            Statement st = db_con.createStatement();
            String query = "SELECT * FROM logins WHERE login = \'" + name+"\' AND password = \'"+
                    password + "\';";
            st.executeQuery(query);
            ResultSet rs = st.getResultSet();
            if(rs.next()){
                System.out.println("data exist in db");
                System.out.println();
                return true;
            }
            else {
                System.out.println("data dont exist in db");
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
            return false;
        }


    }
    public String getUserStatus(String name,String password){
        try {
            Statement st = db_con.createStatement();
            String query = "SELECT role FROM logins WHERE login = \'" + name+"\' AND password = \'"+
                    password + "\';";
            st.executeQuery(query);
            ResultSet rs = st.getResultSet();
            if(rs.next()){
                return rs.getString("role");
            }
            else return null;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
    public int getUserId(String name,String password){
        try {
            Statement st = db_con.createStatement();
            String query = "SELECT id FROM logins WHERE login = \'" + name+"\' AND password = \'"+
                    password + "\';";
            st.executeQuery(query);
            ResultSet rs = st.getResultSet();
            if(rs.next()){
                return rs.getInt("id");
            }
            else return -1;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return -1;
        }
    }
    public void insertUser(String name, String password,String date){
//        DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String date = LocalDate.now().format(ft).toString();
        try {
            Statement st = db_con.createStatement();
            String query = String.format("insert into logins" +
                    "(login,password,birthday) values " +
                    "(\'%s\', \'%s\',\'%s\')",name,password,date);
            st.execute(query);
            System.out.println("successfully insert");
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println("unsuccessfully insert");
        }
        try {
            Statement st = db_con.createStatement();
            String query = String.format("insert into authors" +
                    "(name,animals,maximum_age) values " +
                    "('%s', '%d','%d')",name,0,0);
            st.execute(query);
            System.out.println("successfully insert");
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println("unsuccessfully insert");
        }
        System.out.println();
    }
    public void insertAnimal(Animal animal){
        //String name, int release, int length, String[] tags, String description, String author
        try {
            String tm_tags="";
            if (animal.getTags().size()!=0){
                tm_tags="{";
                for (int i = 0;i<animal.getTags().size();i++){
                    tm_tags+="\""+animal.getTags().get(i)+"\",";
                }
                tm_tags = tm_tags.substring(0,tm_tags.length()-1)+"}";
            }



            Statement st = db_con.createStatement();
            String query = String.format("insert into request_animals (name,owner,price,age,tags,bornDate,description) values" +
                    "(\'%s\'," +
                    "\'%s\'," +
                    "\'%d\'," +
                    "\'%d\'," +
                    "\'%s\'," +
                    "\'%s\',"+
                    "\'%s\'"+
                    ");",animal.getName(),animal.getOwner(),animal.getPrice(),animal.getAge(),tm_tags,
                    animal.getBornDate(),animal.getDescription());
            System.out.println(query);
            st.execute(query);
            System.out.println("book published");
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
        try {
            Statement st = db_con.createStatement();
            String query = String.format("select animals from authors where name = '%s';",animal.getOwner());
            ResultSet rs = st.executeQuery(query);
            int animals=0;
            while (rs.next()){
                animals = rs.getInt("animals");
            }
            animals++;
            String query1 = String.format("update authors set animals = " +Integer.toString(animals)+" where name = \'"+animal.getOwner()+"\';");
            st.execute(query1);

            System.out.println(query);
            st.execute(query);
            System.out.println("book published");
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
    public ArrayList<String> getTags(){
        ArrayList<String> tm = new ArrayList<String>();
        try {
            Statement st = db_con.createStatement();
            String query = "select name from tags;";
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                tm.add(rs.getString("name"));
            }
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
        return tm;
    }
    public void fillTags(ArrayList<String> tags){
        for(String tag : tags){
            try {
                String query = String.format("insert into tags (name) values ('%s')",tag);
                Statement st = db_con.createStatement();
                st.execute(query);
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }
    public Animal findAnimal(String owner, String animalName, String animalAge){
        try {
            Statement st = db_con.createStatement();
            String query = "select * from animals WHERE owner = \'"+owner+"\' and name = \'"+animalName+"\' and " +
                    "age = \'"+animalAge + "\';";
            ResultSet rs;
            rs = st.executeQuery(query);
            Animal animal = new Animal();
            while(rs.next()){

                System.out.println(rs.getString("name"));
                animal.setName(rs.getString("name"));
                animal.setOwner(rs.getString("owner"));
                animal.setPrice(rs.getInt("price"));
                animal.setAge(rs.getInt("age"));
                animal.setBornDate(rs.getString("bornDate"));
                animal.setDescription(rs.getString("description"));
                Array tags = rs.getArray("tags");
                animal.setTags(new ArrayList<String>(Arrays.asList((String[]) tags.getArray())));
            }

            if (animal.getName() == null) return null;
            return animal;
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            System.out.println("error");
            return null;
        }
    }
    public ObservableList<PieChart.Data> getAuthors(){
        try {
            ObservableList<PieChart.Data> tm = FXCollections.observableArrayList();
            Statement st = db_con.createStatement();
            String query = String.format("select name, animals from" +
                    " authors where animals>0;");
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                tm.add(new PieChart.Data(rs.getString("name"),rs.getInt("animals")));
            }
            System.out.println("successfully diagramm1");
            return tm;
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println("unsuccessfully diagramm1");
            return null;
        }
    }

    public ObservableList<PieChart.Data> getPopularTags(){
        try {
            ObservableList<PieChart.Data> tm = FXCollections.observableArrayList();

            ArrayList<String> tags = new ArrayList<String >();
            ArrayList<Integer> tags_value = new ArrayList<Integer>();
            Statement st = db_con.createStatement();
            String query = String.format("select * from tags");
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                System.out.println(rs.getString("name"));
                tags.add(rs.getString("name"));
            }
            System.out.println("successfully checking tags");
            for (String tag: tags){
                query = String.format("select count(*) from request_animals where \'%s\' = ANY(tags);",tag);
                rs = st.executeQuery(query);
                while (rs.next()){
                    tags_value.add(rs.getInt(1));
                }
            }
            for (int i =0;i<tags.size();i++){
                tm.add(new PieChart.Data(tags.get(i),tags_value.get(i)));
            }
            System.out.println("successfully diagramm2");
            return tm;
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println("unsuccessfully diagramm2");
            return null;
        }
    }
    public ObservableList<PieChart.Data> getPopularOwners(){
        try {
            ObservableList<PieChart.Data> tm = FXCollections.observableArrayList();
            HashMap<Integer,Integer> id_animals = new HashMap<Integer,Integer>();
            //ArrayList<Integer> ids = new ArrayList<Integer>();
            //ArrayList<Integer> tags_value = new ArrayList<Integer>();
            Statement st = db_con.createStatement();
            String query = String.format("select * from purchases");
            ResultSet rs = st.executeQuery(query);

            //Соответсвие id количеству животных
            while (rs.next()){
                System.out.println(rs.getString("name"));
                if (id_animals.containsKey(rs.getInt("owner"))){
                    id_animals.put(rs.getInt("owner"),id_animals.get(rs.getInt("owner"))+1);
                }
                else{
                    id_animals.put(rs.getInt("owner"),1);
                }
            }



            for (Map.Entry<Integer,Integer> obj : id_animals.entrySet()){
                String owners = String.format("select login from logins where id = %d",obj.getKey());
                Statement statement = db_con.createStatement();
                ResultSet resultSet = statement.executeQuery(owners);
                while (resultSet.next()){
                    tm.add(new PieChart.Data(resultSet.getString("login"),obj.getValue()));
                }
            }
            System.out.println("successfully diagramm3");
            return tm;
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println("unsuccessfully diagramm3");
            return null;
        }
    }
    public ArrayList<Animal> findRequestAnimal(){
        ArrayList<Animal> animalsArr = new ArrayList<Animal>();
        try {
            Statement st = db_con.createStatement();
            String query = "select * from request_animals;";
            ResultSet rs;
            rs = st.executeQuery(query);


            while(rs.next()){
                Animal animal = new Animal();
                System.out.println(rs.getString("name"));
                animal.setName(rs.getString("name"));
                animal.setOwner(rs.getString("owner"));
                animal.setPrice(rs.getInt("price"));
                animal.setAge(rs.getInt("age"));
                animal.setBornDate(rs.getString("bornDate"));
                animal.setDescription(rs.getString("description"));
                Array tags = rs.getArray("tags");
                animal.setTags(new ArrayList<String>(Arrays.asList((String[]) tags.getArray())));
                animalsArr.add(animal);
            }

            return animalsArr;
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }
    public boolean checkAnimal(Animal animal){
        try {
            Statement st = db_con.createStatement();
            String query = "select * from animals WHERE owner = \'"+animal.getOwner()+"\' and name = \'"+animal.getName()+"\' and " +
                    "age = \'"+animal.getAge() + "\';";
            st.executeQuery(query);
            ResultSet rs = st.getResultSet();
            if(rs.next()){
                System.out.println("data exist in db");
                System.out.println();
                return true;
            }
            else {
                System.out.println("data dont exist in db");
                return false;
            }
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            System.out.println("error");
            return false;
        }
    }
    public void publicAnimal(Animal animal){
        try {

            String tm_tags="";
            if (animal.getTags().size()!=0){
                tm_tags="{";
                for (int i = 0;i<animal.getTags().size();i++){
                    tm_tags+="\""+animal.getTags().get(i)+"\",";
                }
                tm_tags = tm_tags.substring(0,tm_tags.length()-1)+"}";
            }

            Statement st = db_con.createStatement();
            String query1 = String.format("insert into animals (name,owner,price,age,tags,bornDate,description) values" +
                    "(\'%s\'," +
                    "\'%s\'," +
                    "\'%d\'," +
                    "\'%d\'," +
                    "\'%s\'," +
                    "\'%s\',"+
                    "\'%s\'"+
                    ");",animal.getName(),animal.getOwner(),animal.getPrice(),animal.getAge(),
                    tm_tags,animal.getBornDate(),animal.getDescription());
            st.execute(query1);
            String query2 = "delete from request_animals where name = \'"+animal.getName()+"\'" +
                    " and age = \'"+Integer.toString(animal.getAge())+"\' and owner = \'"+animal.getOwner()+"\';";
            st.execute(query2);
            System.out.println("Book added in library");
        }
        catch (Exception ex){
            System.out.println("Error with adding book");
            System.out.println(ex.toString());
        }
    }
    public void rejectAnimal(Animal animal){
        String query = String.format("delete from request_animals where name = \'"+animal.getName()+"\'" +
                " and age = \'"+Integer.toString(animal.getAge())+"\' and owner = \'"+animal.getOwner()+"\';");
        try{
            Statement st = db_con.createStatement();
            st.execute(query);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public ArrayList<Animal> findAnimalsByAuthor(String owner){
        ArrayList<Animal> animalsList = new ArrayList<Animal>();
        try {
            Statement st = db_con.createStatement();
            String query = "select * from animals where owner = \'"+owner+"\';";
            ResultSet rs;
            rs = st.executeQuery(query);

            while(rs.next()){
                Animal animal = new Animal();
                System.out.println(rs.getString("name"));
                animal.setName(rs.getString("name"));
                animal.setOwner(rs.getString("owner"));
                animal.setPrice(rs.getInt("price"));
                animal.setAge(rs.getInt("age"));
                animal.setBornDate(rs.getString("bornDate"));
                animal.setDescription(rs.getString("description"));
                Array tags = rs.getArray("tags");
                animal.setTags(new ArrayList<String>(Arrays.asList((String[]) tags.getArray())));
                animalsList.add(animal);

            }
            //if (tmb.name == null) return null;

            return animalsList;
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }
    public ArrayList<Animal> findAnimalsByUser(int id){
        ArrayList<Animal> animalsList = new ArrayList<Animal>();
        try {
            Statement st = db_con.createStatement();
            String query = String.format("select * from purchases where owner = %d;",id) ;
            ResultSet rs;
            rs = st.executeQuery(query);
            while(rs.next()){
                Animal animal = new Animal();
                System.out.println(rs.getString("name"));
                animal.setName(rs.getString("name"));
                animal.setOwner(rs.getString("owner"));
                animal.setPrice(rs.getInt("price"));
                animal.setAge(rs.getInt("age"));
                animal.setBornDate(rs.getString("bornDate"));
                animal.setDescription(rs.getString("description"));
                Array tags = rs.getArray("tags");
                animal.setTags(new ArrayList<String>(Arrays.asList((String[]) tags.getArray())));
                animalsList.add(animal);

            }
            //if (tmb.name == null) return null;

            return animalsList;
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }
    public void animalPurchase(int id, Animal animal){
        String tm_tags="";
        if (animal.getTags().size()!=0){
            tm_tags="{";
            for (int i = 0;i<animal.getTags().size();i++){
                tm_tags+="\""+animal.getTags().get(i)+"\",";
            }
            tm_tags = tm_tags.substring(0,tm_tags.length()-1)+"}";
        }

        String query = String.format("insert into purchases (name,owner,price,age,tags,bornDate,description) values" +
                        "(\'%s\'," +
                        "\'%d\'," +
                        "\'%d\'," +
                        "\'%d\'," +
                        "\'%s\'," +
                        "\'%s\',"+
                        "\'%s\'"+
                        ");",animal.getName(),id,animal.getPrice(),animal.getAge(),
                tm_tags,animal.getBornDate(),animal.getDescription());
        try {
            Statement st = db_con.createStatement();
            st.execute(query);
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }

    }
    public boolean deleteAnimal(String name, String owner, String age){
        try {

            Statement st = db_con.createStatement();
            String query = String.format("delete from animals where name = \'%s\' and " +
                    "age = %s and owner = \'%s\';" ,name,age,owner);
            System.out.println(query);
            st.execute(query);
            System.out.println("book deleted");
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return false;
        }
        try {
            Statement st = db_con.createStatement();
            String query = String.format("select animals from authors where name = \'" + owner+
                    "\';");
            ResultSet rs = st.executeQuery(query);
            int animals=0;
            while (rs.next()){
                animals = rs.getInt("animals");
            }
            animals--;
            String query1 = String.format("update authors set animals = " +Integer.toString(animals)+" where name = \'"+owner+"\';");
            st.execute(query1);

            System.out.println(query);
            st.execute(query);
            System.out.println("user books count decrease");
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return false;
        }
        return true;
    }
    public int checkBalance(int id){
        String query = String.format("select balance from logins where id = %d",id);
        try {
            Statement st = db_con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            return rs.getInt("balance");
        }
        catch (Exception e){
            System.out.println(e.toString());
            return -1;
        }

    }
    public int increaseBalance(int id, int sum){
        int balance = checkBalance(id);
        balance+=sum;
        String query = String.format("update logins set balance = %d where id = %d",balance,id);
        try{
            Statement st = db_con.createStatement();
            st.execute(query);
            return 1;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return -1;
        }

    }
    public int decreaseBalance(int id,int sum){
        int balance = checkBalance(id);
        balance-=sum;
        if (balance<0) return -1;
        String query = String.format("update logins set balance = %d where id = %d",balance,id);
        try{
            Statement st = db_con.createStatement();
            st.execute(query);
            return 1;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return -1;
        }
    }
}

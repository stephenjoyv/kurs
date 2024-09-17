package com.kurs.kurs;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                "create table books (\n" +
                "            id SERIAL PRIMARY KEY,\n" +
                "            name VARCHAR(100),\n" +
                "            release INT,\n" +
                "            length INT,\n" +
                "            tags VARCHAR(30)[],\n" +
                "            description VARCHAR(1000),\n" +
                "            marks INT DEFAULT 0\n" +
                "            );\n" +
                "create table marks(\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    comment VARCHAR(100),\n" +
                "    book INT,\n" +
                "    mark INT DEFAULT 0\n" +
                "    \n" +
                ");\n" +
                "create table request_books (\n" +
                "            id SERIAL PRIMARY KEY,\n" +
                "            name VARCHAR(100),\n" +
                "            release INT,\n" +
                "            length INT,\n" +
                "            tags VARCHAR(30)[],\n" +
                "            description VARCHAR(1000),\n" +
                "            marks INT DEFAULT 0\n" +
                "            );\n" +
                "create table logins (\n" +
                "              id SERIAL PRIMARY KEY,\n" +
                "              login VARCHAR(100),\n" +
                "              password VARCHAR(100),\n" +
                "                role VARCHAR(100),\n" +
                "              last_enter DATE\n," +
                " birthday DATE\n" +
                "            );\n" +
                "            create table authors (\n" +
                "              id SERIAL PRIMARY KEY,\n" +
                "              name VARCHAR(100),\n" +
                "              books INT DEFAULT 0,\n" +
                "              maximum_size INT DEFAULT 0\n" +
                "            );";

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
        System.out.println();
    }
    public void insertBook(String name,int release, int length, String[] tags,String description, String author){
        try {
            String tm_tags="";
            if (tags.length!=0){
                tm_tags="{";
                for (int i = 0;i<tags.length;i++){
                    tm_tags+="\""+tags[i]+"\",";
                }
                tm_tags = tm_tags.substring(0,tm_tags.length()-1)+"}";
            }



            Statement st = db_con.createStatement();
            String query = String.format("insert into request_books (name,release,length,tags,description,author) values" +
                    "(\'%s\'," +
                    "\'%d\'," +
                    "\'%d\'," +
                    "\'%s\'," +
                    "\'%s\'," +
                    "\'%s\'"+
                    ");",name,release,length,tm_tags,description,author);
            System.out.println(query);
            st.execute(query);
            System.out.println("book published");
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
        try {
            Statement st = db_con.createStatement();
            String query = String.format("select books from logins where login = \'" + author+
                    "\';");
            ResultSet rs = st.executeQuery(query);
            int books=0;
            while (rs.next()){
                books = rs.getInt("books");
            }
            books++;
            String query1 = String.format("update logins set books = " +Integer.toString(books)+" where login = \'"+author+"\';");
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
    public Book findBook(String author,String book,String red){
        try {
            Statement st = db_con.createStatement();
            String query = "select * from books WHERE author = \'"+author+"\' and name = \'"+book+"\' and " +
                    "release = \'"+red + "\';";
            ResultSet rs;
            rs = st.executeQuery(query);
            Book tm = new Book();
            while(rs.next()){

                System.out.println(rs.getString("name"));
                tm.name = rs.getString("name");
                tm.release = rs.getInt("release");
                tm.author = rs.getString("author");
                tm.description = rs.getString("description");
                Array a = rs.getArray("tags");
                tm.tags = new ArrayList<String>(Arrays.asList((String[]) a.getArray()));
                tm.marks = rs.getInt("marks");
                tm.length = rs.getInt("length");
            }

            if (tm.name == null) return null;
            return tm;
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
            String query = String.format("select login, books from" +
                    " logins where books>0;");
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                tm.add(new PieChart.Data(rs.getString("login"),rs.getInt("books")));
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
                tags.add(rs.getString("name"));
            }
            System.out.println("successfully checking tags");
            for (String tag: tags){
                query = String.format("select count(*) from request_books where \'%s\' = ANY(tags);",tag);
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
    public ArrayList<Book> findRequestBook(){
        ArrayList<Book> tm = new ArrayList<Book>();
        try {
            Statement st = db_con.createStatement();
            String query = "select * from request_books;";
            ResultSet rs;
            rs = st.executeQuery(query);

            while(rs.next()){
                Book tmb = new Book();
                System.out.println(rs.getString("name"));
                tmb.name = rs.getString("name");
                tmb.release = rs.getInt("release");
                tmb.author = rs.getString("author");
                tmb.description = rs.getString("description");
                Array a = rs.getArray("tags");
                tmb.tags = new ArrayList<String>(Arrays.asList((String[]) a.getArray()));
                tmb.marks = rs.getInt("marks");
                tmb.length = rs.getInt("length");
                tm.add(tmb);

            }
            //if (tmb.name == null) return null;

            return tm;
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }
    public boolean checkBook(Book book){
        try {
            Statement st = db_con.createStatement();
            String query = "select * from books WHERE author = \'"+book.author+"\' and name = \'"+book.name+"\' and " +
                    "release = \'"+book.release + "\';";
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
    public void public_book(Book book){
        try {

            String tm_tags="";
            if (book.tags.size()!=0){
                tm_tags="{";
                for (int i = 0;i<book.tags.size();i++){
                    tm_tags+="\""+book.tags.get(i)+"\",";
                }
                tm_tags = tm_tags.substring(0,tm_tags.length()-1)+"}";
            }

            Statement st = db_con.createStatement();
            String query1 = String.format("insert into books (name,release,length,tags,description,author) values" +
                    "(\'%s\'," +
                    "\'%d\'," +
                    "\'%d\'," +
                    "\'%s\'," +
                    "\'%s\'," +
                    "\'%s\'"+
                    ");",book.name,book.release,book.length,tm_tags,book.description,book.author);
            st.execute(query1);
            String query2 = "delete from request_books where name = \'"+book.name+"\'" +
                    " and release = \'"+Integer.toString(book.release)+"\' and author = \'"+book.author+"\';";
            st.execute(query2);
            System.out.println("Book added in library");
        }
        catch (Exception ex){
            System.out.println("Error with adding book");
            System.out.println(ex.toString());
        }
    }
    public ArrayList<Book> findBooksByAuthor(String author){
        ArrayList<Book> tm = new ArrayList<Book>();
        try {
            Statement st = db_con.createStatement();
            String query = "select * from books where author = \'"+author+"\';";
            ResultSet rs;
            rs = st.executeQuery(query);

            while(rs.next()){
                Book tmb = new Book();
                System.out.println(rs.getString("name"));
                tmb.name = rs.getString("name");
                tmb.release = rs.getInt("release");
                tmb.author = rs.getString("author");
                tmb.description = rs.getString("description");
                Array a = rs.getArray("tags");
                tmb.tags = new ArrayList<String>(Arrays.asList((String[]) a.getArray()));
                tmb.marks = rs.getInt("marks");
                tmb.length = rs.getInt("length");
                tm.add(tmb);

            }
            //if (tmb.name == null) return null;

            return tm;
        }
        catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }
    public boolean deleteBook(String name, String author, String release){
        try {

            Statement st = db_con.createStatement();
            String query = String.format("delete from books where name = \'%s\' and " +
                    "release = %s and author = \'%s\';" ,name,release,author);
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
            String query = String.format("select books from logins where login = \'" + author+
                    "\';");
            ResultSet rs = st.executeQuery(query);
            int books=0;
            while (rs.next()){
                books = rs.getInt("books");
            }
            books--;
            String query1 = String.format("update logins set books = " +Integer.toString(books)+" where login = \'"+author+"\';");
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
}

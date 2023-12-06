import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        System.out.println("WELCOME TO MOVIE TICKET BOOKING ");
        System.out.println("Movie name = Jawan");
        System.out.println("Movie Timing = 2pm");
        System.out.println("Ticket price = 250/-");
        while(true) {
            System.out.println("\n1) Book ticket\n2)show ticket\n3)cancel booking\n4)Show available seats\n5) exit\nEnter choice");
            int ch;
            ch = sc.nextInt();
            switch (ch) {

                case 1:

                try {
                    String databaseURL = "jdbc:ucanaccess://Database41.accdb";
                    Connection con = DriverManager.getConnection(databaseURL);
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("BOOKING DETAILS ");
                    System.out.print("Enter no of seats : ");
                    int seat = Integer.parseInt(br.readLine());
                    String sql6 = "select total from movie where ID=1";
                    Statement stmt11=con.createStatement();
                    ResultSet rs6 = stmt11.executeQuery(sql6);
                    while (rs6.next()) {
                        if(seat<rs6.getInt("total")) {
                            System.out.println("Total Amount "+seat*250);
                        }
                        else{
                            System.out.println(seat+"are not available");
                            System.out.println("Available no. of seats : " + rs6.getInt("total")+"\nPlease select seats less than "+rs6.getInt("total"));
                            System.out.print("Enter no of seats : ");
                            seat = Integer.parseInt(br.readLine());
                        }
                    }
                    String sql = "insert into movie(email,pno,seat) values (?,?,?)";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    System.out.print("Enter your Email: ");
                    String email = br.readLine();
                    System.out.print("Enter your Contact number: ");
                    long pno = Long.parseLong(br.readLine());
                    System.out.print("Booking Successful ");
                    stmt.setString(1, email);
                    stmt.setLong(2, pno);
                    stmt.setInt(3, seat);
                    stmt.executeUpdate();
                    // int i = stmt.executeUpdate(sql);
                    //  if (i > 0) {
                    //      System.out.println("ROW INSERTED");
                    //   } else {
                    //      System.out.println("ROW NOT INSERTED");
                    //  }

                    //  pst.setInt(2,s.getPno());
                    //  pst.setInt(3,s.getSeat());
                    //pst.execute();
                    // pst.executeUpdate();
                    // flag=true;



                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
                case 2:
                    try {
                        String databaseURL1 = "jdbc:ucanaccess://Database41.accdb";
                        Connection con1 = DriverManager.getConnection(databaseURL1);
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Enter Contact Number");
                        long p = Long.parseLong(br1.readLine());
                        String sql1 = "select id,email,pno,seat from movie where pno=" + p;
                        Statement stmt = con1.createStatement();
                        ResultSet rs = stmt.executeQuery(sql1);
                        System.out.println("Your Booking Details Are");
                        while(rs.next()){
                            System.out.println("Booking id: " + rs.getInt("id"));
                            System.out.println("Contact Number: " + rs.getInt("pno"));
                            System.out.println("Email: " + rs.getString("email"));
                            System.out.println("no of seats: " + rs.getInt("seat"));
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    try {
                        String databaseURL2 = "jdbc:ucanaccess://Database41.accdb";
                        Connection con2 = DriverManager.getConnection(databaseURL2);
                        System.out.println("Booking Cancellation ");
                        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Enter Contact Number ");
                        Long p1=Long.parseLong(br2.readLine());
                        Statement stmt1 = con2.createStatement();
                        String sql2 = "delete from movie where pno="+p1;
                        stmt1.executeUpdate(sql2);
                        ResultSet rs1 = stmt1.executeQuery(sql2);
                        rs1.close();
                        System.out.println("Your Booking Has Been Cancelled ");
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                case 4:
                    try {
                        String databaseURL3 = "jdbc:ucanaccess://Database41.accdb";
                        Connection con3 = DriverManager.getConnection(databaseURL3);
                        Statement stmt3 = con3.createStatement();
                        String sql3 = "update movie set total=150-(select sum(seat)from movie) where id=1";
                        stmt3.executeUpdate(sql3);
                        String sql4 = "select total from movie where ID=1";
                        //Statement stmt0=con3.createStatement();
                        ResultSet rs3 = stmt3.executeQuery(sql4);
                        while (rs3.next()) {
                            System.out.println("Total seats= 150");
                            System.out.println("Available no. of seats : " + rs3.getInt("total"));

                        }
                        rs3.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    System.exit(0);
            }
        }

    }

}
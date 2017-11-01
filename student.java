import java.io.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

class database
{
   private Connection conn;
   private Statement st;
   private ResultSet rs;  
   private String sql;
   database()
   {
     try
     {
       Class.forName("com.mysql.jdbc.Driver");
       conn=DriverManager.getConnection("jdbc:mysql://localhost/testdb","root","123ashi!!");
       st=conn.createStatement(); 
       sql="CREATE TABLE IF NOT EXISTS student "+
           "(roll varchar(20),name varchar(60),dept varchar(60),"+
           "division varchar(5),total int(10),"+
           "primary key(roll))";
       st.executeUpdate(sql);
     }
     catch(Exception e)
     {
       System.out.println("Error :"+e);
     }
   }

   public void insert()
   {
      try
      {
        Scanner sc=new Scanner(System.in);
        Scanner r=new Scanner(System.in);
        Scanner d=new Scanner(System.in);
        Scanner di=new Scanner(System.in);
        Scanner m=new Scanner(System.in);

        System.out.println("Enter roll number:");
        int roll=sc.nextInt();

        System.out.println("Enter name:");
        String name=r.nextLine();

        System.out.println("Enter department:");
        String dept=d.nextLine();

        System.out.println("Enter division:");
        String div=di.nextLine();

        System.out.println("Enter mark:");
        int mark=m.nextInt();

        sql="INSERT INTO student "+
            "VALUES "+
            "("+roll+",'"+name+"','"+dept+"','"+div+"',"+mark+")";

        st.executeUpdate(sql);
        System.out.println("Inserted records into the table");
      }

      catch(Exception e)
      {
            System.out.println("Error :"+e);
      }
   }

   public void delete()
   {
      try
      {
         sql="TRUNCATE student";
         st.executeUpdate(sql);
         sql="DELETE FROM student";
         st.executeUpdate(sql);
         System.out.println();
         System.out.println("Deleted all details from the table");
      }
      catch(Exception e)
      {
         System.out.println("Error :"+e);
      }
   }

   public void display_all()
   {
      try
      {
         sql="SELECT * FROM student";
         int d=0;
         rs=st.executeQuery(sql);
         System.out.println();
         System.out.println("Records of student table");
         while(rs.next())
         {
            d++;
            String rollno=rs.getString("roll");
            String name=rs.getString("name");
            String dept=rs.getString("dept");
            String divi=rs.getString("division");
            String marks=rs.getString("total");
            System.out.println();
            System.out.println("Rollno : "+rollno+"\nName : "+name+"\nDepartment : "+dept+"\nDivision : "+divi+ "\nMarks : "+marks);
         }
         if(d==0)
         {
            System.out.println("TABLE IS EMPTY");
         }
      }
      catch(Exception e)
      {
         System.out.println("Error :"+e);
      }
   }

   public void searchData()
   {
      try
      {
         int f=0;
         System.out.println();
         System.out.println("Enter the rollno to be searched");
         Scanner in=new Scanner(System.in);
         int r=in.nextInt();
         String query="select * from student where roll="+r;
         rs=st.executeQuery(query);
         while(rs.next())
         {
            f++;
            String rollno=rs.getString("roll");
            String name=rs.getString("name");
            String dept=rs.getString("dept");
            String divi=rs.getString("division");
            String marks=rs.getString("total");
            System.out.println();
            System.out.println("Rollno : "+rollno+"\nName : "+name+"\nDepartment : "+dept+"\nDivision : "+divi+ "\nMarks : "+marks);
         }
         if(f==0)
         {
             System.out.println("ROLLNO NOT FOUND IN TABLE Student");
         }
      }
      catch(Exception e)
      {
         System.out.println("Error :"+e);
      }
   }
}

class student extends JFrame implements ActionListener
{
   private JButton b1,b2,b3,b4;

   student()
   {
     GridLayout grid=new GridLayout(4,1);

     JFrame frame=new JFrame("Student Record");

     b1=new JButton("Insert");
     b2=new JButton("Search by roll no");
     b3=new JButton("Print all details");
     b4=new JButton("Delete all details");
    
     frame.setLayout(grid);
     frame.setSize(250,250);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);

     frame.add(b1);
     frame.add(b2);
     frame.add(b3);
     frame.add(b4);

     b1.addActionListener(this);
     b2.addActionListener(this);
     b3.addActionListener(this);
     b4.addActionListener(this);
   }


          public void actionPerformed(ActionEvent e)
          {
             if(e.getSource()==b1)
             {
               database connect=new database();
               connect.insert();
             }

             if(e.getSource()==b2)
             {
               database connect=new database();
               connect.searchData();
             }

             if(e.getSource()==b3)
             {
               database connect=new database();
               connect.display_all();
             }

             if(e.getSource()==b4)
             {
               database connect=new database();
               connect.delete();
             }
          }

   public static void main(String ar[])
   {
     new student();
   }
}

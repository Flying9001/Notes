import java.sql.*;
public class GetConnection {
    public static void main(String[] args){
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/mysql";    //JDBC的URL    
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn;
        try {
            conn = DriverManager.getConnection(url,    "root","");
            //创建一个Statement对象
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.print("成功连接到数据库！");
            stmt.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

// 2. 查询数据表
// 　　在询数据表时，需要用到ResultSet接口，它类似于一个数据表，通过该接口的实例可以获得检索结果集，以及对应数据表的接口信息。
import java.sql.*;

public class SelectTable {
    
    public static void main(String[] args){
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
                
            String url="jdbc:mysql://localhost:3306/aniu";    //JDBC的URL    
            Connection conn;

            conn = DriverManager.getConnection(url,    "root","");
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = "select * from stu";    //要执行的SQL
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
                System.out.println("编号"+"\t"+"姓名"+"\t"+"年龄");
                while (rs.next()){
                    System.out.print(rs.getInt(1) + "\t");
                    System.out.print(rs.getString(2) + "\t");
                    System.out.print(rs.getInt(3) + "\t");
                    System.out.println();
                }
                rs.close();
                stmt.close();
                conn.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
    }
}

// 3. 修改和删除数据库
//修改删除数据
import java.sql.*;
public class UpdateDeleteDemo {
    public static void main(String[] args)throws Exception{
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
                
            String url="jdbc:mysql://localhost:3306/aniu";    //JDBC的URL    
            Connection conn;

            conn = DriverManager.getConnection(url,    "root","");
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            //查询数据的代码
            String sql = "select * from stu";    //要执行的SQL
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
                System.out.println("编号"+"\t"+"姓名"+"\t"+"年龄");
                while (rs.next()){
                    System.out.print(rs.getInt(1) + "\t");
                    System.out.print(rs.getString(2) + "\t");
                    System.out.print(rs.getInt(3) + "\t");
                    System.out.println();
                }
                
            //修改数据的代码
            String sql2 = "update stu set name=? where number=?";
            PreparedStatement pst = conn.prepareStatement(sql2);
            pst.setString(1,"8888");
            pst.setInt(2,198);
            pst.executeUpdate();
                
            //删除数据的代码
            String sql3 = "delete from stu where number=?";
            pst = conn.prepareStatement(sql3);
            pst.setInt(1,701);
            pst.executeUpdate();
                
            ResultSet rs2 = stmt.executeQuery(sql);//创建数据对象
            System.out.println("编号"+"\t"+"姓名"+"\t"+"年龄");
            while (rs.next()){
                System.out.print(rs2.getInt(1) + "\t");
                System.out.print(rs2.getString(2) + "\t");
                System.out.print(rs2.getInt(3) + "\t");
                System.out.println();
            }
                
            rs.close();
            stmt.close();
            conn.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
    }
}
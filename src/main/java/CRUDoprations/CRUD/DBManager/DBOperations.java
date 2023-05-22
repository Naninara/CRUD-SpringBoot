package CRUDoprations.CRUD.DBManager;

import CRUDoprations.CRUD.DAO.Persons;
import CRUDoprations.CRUD.RequestData.CreateUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {

    public static volatile Connection connection;

    public static Connection startConnection() throws SQLException {
        if(connection==null){
            synchronized (DBOperations.class) {
                if (connection == null) {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "Admin@2814");

                }
            }
        }
        return connection;
    }

    public  static void CloseConnection() throws SQLException{
            if(connection!=null){
                synchronized (DBOperations.class){
                    if (connection!=null){
                        connection = null;
                    }
                }

            }
    }

    public static  void insertUser(CreateUser user) throws SQLException {
        startConnection();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO PERSONDATA VALUES(null,?,?)");
        statement.setString(1,user.getName());
        statement.setInt(2,user.getAge());

        statement.execute();
//        Persons person = new Persons(user.getId(),user.getName(),user.getAge());
//        Statement statement = connection.createStatement();
//        statement.executeQuery("INSERT INTO PERSONDATA VALUES(null"+person.getName()+""+person.getAge()+")");

        CloseConnection();
    }

    public Persons getPersonById(){
        return null;
    }

    public static List<Persons> getAllUsers() throws SQLException {
        startConnection();
        Statement statement = connection.createStatement();
        List<Persons> allPersonData= new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM PERSONDATA");
            while (result.next()){
                int id = result.getInt(1);
                String name = result.getString(2);
                int age = result.getInt(3);
                Persons p = new Persons(id,name,age);
                allPersonData.add(p);
            }
        CloseConnection();
            return allPersonData;
    }

    public static void DeletePerson(int id)throws SQLException{
        startConnection();

        Statement st = connection.createStatement();
        st.execute("DELETE FROM PERSONDATA WHERE id="+id+"");

        CloseConnection();
    }

    public static void UpdatePerson(Persons person) throws SQLException {

        startConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE PERSONDATA SET name=?,age=? WHERE id=?");
        statement.setString(1,person.getName());
        statement.setInt(2,person.getAge());
        statement.setInt(3,person.getId());
        statement.execute();
        System.out.println("updated");
        CloseConnection();
    }


}

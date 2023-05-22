package CRUDoprations.CRUD.Controller;

import CRUDoprations.CRUD.DAO.Persons;
import CRUDoprations.CRUD.DBManager.DBOperations;
import CRUDoprations.CRUD.RequestData.CreateUser;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ApiController {
    @GetMapping("/getAllUser")
    public List<Persons> getAllUsers() throws SQLException {
        return DBOperations.getAllUsers();
    }



    @PostMapping("/insertUser")
    public void insertUser(@RequestBody CreateUser request) throws SQLException {
        DBOperations.insertUser(request);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id) throws SQLException {
        DBOperations.DeletePerson(id);
    }

    @PatchMapping("/updateUser")
    public void updateUser(@RequestBody Persons person) throws SQLException {
        DBOperations.UpdatePerson(person);
    }

}

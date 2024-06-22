package az.bcc.dogapplicationspringbootrelations.repository;

import az.bcc.dogapplicationspringbootrelations.connection.DatabaseConnection;
import az.bcc.dogapplicationspringbootrelations.model.Person;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class PersonRepository {
    private Connection connection;

    public void addPerson(Person person) {
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO persondogapplication_persons (name,surname) VALUES (?,?)");
            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.execute();
            System.out.println("Person added");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Person> getAllPersons() {
        ArrayList<Person> persons = new ArrayList<>();
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM persondogapplication_persons");
            ResultSet rs = statement.executeQuery(); // databaseden gelen cavab
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                persons.add(person);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public Person getPersonById(Long id) {
        Person person = null;
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM persondogapplication_persons WHERE id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery(); // executeQuery: her hansisa cavab alanda executeQuery istifade edilir.

            if (rs.next()) {
                person = new Person();
                person.setId(id);
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
            }
            rs.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void deletePerson(Long id) {
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM persondogapplication_persons WHERE id = ? ");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Person deleted");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

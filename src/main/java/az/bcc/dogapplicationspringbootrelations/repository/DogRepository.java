package az.bcc.dogapplicationspringbootrelations.repository;

import az.bcc.dogapplicationspringbootrelations.connection.DatabaseConnection;
import az.bcc.dogapplicationspringbootrelations.model.Dog;
import az.bcc.dogapplicationspringbootrelations.model.Person;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class DogRepository {
    private Connection connection;

    public void addDog(Dog dog) {
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO persondogapplication_dogs (name,age,owner_id) VALUES (?,?,?)");
            statement.setString(1, dog.getName());
            statement.setInt(2, dog.getAge());
            statement.setLong(3, dog.getOwner().getId());
            statement.execute();
            System.out.println("Dog added");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Dog> getAlldogs() {
        ArrayList<Dog> dogs = new ArrayList<>();
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM persondogapplication_dogs");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Dog dog = new Dog();
                dog.setId(rs.getLong("id"));
                dog.setName(rs.getString("name"));
                dog.setAge(rs.getInt("age"));
                Person person = new Person();
                person.setId(rs.getLong("owner_id"));
                dog.setOwner(person);
                dogs.add(dog);
            }
            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dogs;
    }

    public void updateDog(Long id, Dog dog) {
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE persondogapplication_dogs SET name = ?,age = ?,owner_id = ? WHERE id = ? ");
            statement.setString(1, dog.getName());
            statement.setInt(2, dog.getAge());
            if (dog.getOwner().getId() != null) {
                statement.setLong(3, dog.getOwner().getId());
            } else {
                statement.setNull(3, 0);
            }

            statement.setLong(4, id);
            statement.executeUpdate();
            System.out.println("Dog updated");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteDog(Long id) {
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM persondogapplication_dogs WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Dog deleted");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dog findDogByOwnerId(Long ownerId) {  // elimizde olan insan id-ni itler cedvelinde ona aid itin olmasini tapiriq.
        Dog dog = null;
        connection = DatabaseConnection.connect();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM persondogapplication_dogs WHERE owner_id = ?");
            statement.setLong(1, ownerId);
            ResultSet rs = statement.executeQuery();// databaseden gelen cavabdir/
            if (rs.next()) {
                dog = new Dog();
                dog.setId(rs.getLong("id"));
                dog.setName(rs.getString("name"));
                dog.setAge(rs.getInt("age"));
                Person person = new Person();
                person.setId(rs.getLong("owner_id"));
                dog.setOwner(person);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dog;
    }
}

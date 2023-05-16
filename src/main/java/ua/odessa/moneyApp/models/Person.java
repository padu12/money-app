package ua.odessa.moneyApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class  Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @NotEmpty(message = "Email shouldn't be empty")
  @Column(name = "username")
  private String username;

  @NotEmpty(message = "Имя не должно быть пустым")
  @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длинной")
  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  public Person() {
  }

  public Person(String username, String name, String password) {
    this.username = username;
    this.name = name;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Person{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}

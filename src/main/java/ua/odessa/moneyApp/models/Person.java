package ua.odessa.moneyApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
public class  Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @NotEmpty(message = "Username shouldn''t be empty")
  @Size(min = 2, max = 100, message = "Username''s length should be bigger then 2 characters")
  @Column(name = "name")
  private String name;

  @NotEmpty(message = "Email shouldn''t be empty")
  @Email(message = "Email does not match the email template")
  @Column(name = "username")
  private String username;

  @Size(min = 8, max = 20, message = "Your password''s length should be between 8 and 20 characters")
  @NotEmpty(message = "Password shouldn''t be empty")
  @Column(name = "password")
  private String password;

  @Transient
  private String repeatPassword;

  @OneToMany(mappedBy = "personId")
  private List<Recording> recordings;

  public Person() {
  }

  public Person(String username, String name, String password) {
    this.username = username;
    this.name = name;
    this.password = password;
  }

  public String getRepeatPassword() {
    return repeatPassword;
  }

  public void setRepeatPassword(String repeatPassword) {
    this.repeatPassword = repeatPassword;
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

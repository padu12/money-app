package ua.odessa.moneyApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @OneToOne()
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person person;

  @Column(name = "balance")
  private int balance;

  @Column(name = "income")
  private int income;

  @Column(name = "outgo")
  private int outgo;

  public Users() {
  }

  public Users(Person person, int balance, int income, int outgo) {
    this.person = person;
    this.balance = balance;
    this.income = income;
    this.outgo = outgo;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public int getIncome() {
    return income;
  }

  public void setIncome(int income) {
    this.income = income;
  }

  public int getOutgo() {
    return outgo;
  }

  public void setOutgo(int outgo) {
    this.outgo = outgo;
  }
}

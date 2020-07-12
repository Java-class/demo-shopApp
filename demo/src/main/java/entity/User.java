package entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@JsonFilter("entity.User")
public class User implements Serializable {

    @Id
    private String username;

    private String password;

    private String phone;

    private String name;

    private String family;

    private double balance;

    @Column(name = "add_date")
    private Timestamp addDate;


    @OneToMany(mappedBy = "user")
    private List<Purchase> purchaseList = new ArrayList<>();


    public User(String username, String name, String family) {
        this.username = username;
        this.name = name;
        this.family = family;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", balance=" + balance +
                ", addDate=" + addDate +
                '}';
    }
}

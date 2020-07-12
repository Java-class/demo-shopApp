package entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@DynamicInsert
@DynamicUpdate
public class Purchase implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private int count;

    private double price;

    @Column(name = "shop_date")
    private Timestamp shopDate;

    public Purchase(User user, Product product, int count, double price) {
        this.user = user;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    public Purchase() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getShopDate() {
        return shopDate;
    }

    public void setShopDate(Timestamp shopDate) {
        this.shopDate = shopDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", count=" + count +
                ", price=" + price +
                ", shopDate=" + shopDate +
                '}';
    }

    public JSONObject toJSON(){
        JSONObject temp = new JSONObject();
        temp.put("username", this.getUser().getUsername());
        temp.put("product_id", this.getProduct().getId());
        temp.put("product_name", this.getProduct().getName());
        temp.put("count", this.getCount());
        temp.put("shop_date", this.getShopDate().toString());
        return temp;
    }
}

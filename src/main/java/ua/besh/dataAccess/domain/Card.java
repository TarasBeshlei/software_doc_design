package ua.besh.dataAccess.domain;

import javax.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    private Long cardNumber;
    private String dateExpired;
    private int cvv;
    private String cardHolder;

    @OneToOne
    @MapsId
    private User user;

    public Card() {
    }

    public Card(Long cardNumber, String dateExpired, int cvv, String cardHolder, User user) {
        this.cardNumber = cardNumber;
        this.dateExpired = dateExpired;
        this.cvv = cvv;
        this.cardHolder = cardHolder;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }
}

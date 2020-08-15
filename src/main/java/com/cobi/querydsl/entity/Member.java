package com.cobi.querydsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Phone phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    @Builder.Default
    private List<Favorite> favoriteList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    @Builder.Default
    private List<Order> orderList = new ArrayList<>();

    public void setPhone(Phone phone) {
        phone.setMember(this);
        this.phone = phone;
    }

    public void addFavorite(Favorite favorite){
        favorite.setMember(this);
        favoriteList.add(favorite);
    }

    public void addOrder(Order order){
        order.setMember(this);
        orderList.add(order);
    }
}

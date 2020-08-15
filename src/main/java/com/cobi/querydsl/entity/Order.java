package com.cobi.querydsl.entity;

import lombok.*;

import javax.persistence.*;

@ToString(exclude = "member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_price")
    private Integer totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;
}

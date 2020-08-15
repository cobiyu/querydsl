package com.cobi.querydsl.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "phone")
@Entity
public class Phone {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Setter
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

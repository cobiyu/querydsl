package com.cobi.querydsl;

import com.cobi.querydsl.entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cobi.querydsl.entity.QFavorite.favorite;
import static com.cobi.querydsl.entity.QMember.member;
import static com.cobi.querydsl.entity.QOrder.*;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final JPAQueryFactory query;

    public Order createOrderByFavorite(Long memberId){
        List<Tuple> tuples = query
                .select(favorite.name,
                        favorite.price)
                .from(favorite)
                .where(favorite.member.id.eq(memberId))
                .fetch();

        Integer totalPrice = tuples.stream()
                .mapToInt(value -> value.get(favorite.price))
                .sum();

        return Order.builder()
                .name("ordername")
                .totalPrice(totalPrice)
                .member(Member.builder().id(memberId).build())
                .build();
    }

    public Member findMember(Long memberId){
        return query
                .selectFrom(member)
                .where(member.id.eq(memberId))
                .leftJoin(member.orderList, order).fetchJoin()
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}

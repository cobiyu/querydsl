package com.cobi.querydsl;

import com.cobi.querydsl.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import static com.cobi.querydsl.entity.QFavorite.favorite;
import static com.cobi.querydsl.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final JPAQueryFactory query;

    public Order createOrderByFavorite(Long memberId){
        Member selectMember = query
                .selectFrom(QMember.member)
                .leftJoin(QMember.member.favoriteList, favorite).fetchJoin()
                .where(QMember.member.id.eq(memberId))
                .fetchOne();

        Integer totalPrice = selectMember.getFavoriteList().stream().
                mapToInt(Favorite::getPrice).sum();

        return Order.builder()
                .name("ordername")
                .totalPrice(totalPrice)
                .member(selectMember)
                .build();
    }
}

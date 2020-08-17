package com.cobi.querydsl.service;

import com.cobi.querydsl.MemberRepository;
import com.cobi.querydsl.entity.Favorite;
import com.cobi.querydsl.entity.Member;
import com.cobi.querydsl.entity.Order;
import com.cobi.querydsl.entity.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final EntityManager em;

    public void init(){
        for (int i = 0; i < 5; i++) {
            Member member = Member.builder().email("testemail" + i).name("testname" + i).build();
            Phone phone = Phone.builder().number("testphonename"+i).build();

            Favorite favorite = Favorite.builder()
                    .name("favoritename" + i).price(100*(i+1))
                    .build();
            Favorite favorite2 = Favorite.builder()
                    .name("favoritename" + (i + 2))
                    .price(100*(i + 2))
                    .build();

            member.addFavorite(favorite);
            member.addFavorite(favorite2);
            member.setPhone(phone);

            em.persist(member);
        }
    }

    public Order createOrderByFavorite(Long memberId){
        Order orderByFavorite = memberRepository.createOrderByFavorite(memberId);
        em.persist(orderByFavorite);
        return orderByFavorite;
    }
}

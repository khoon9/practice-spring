package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;
// @Repository 을 저장하는 곳임을 명시
@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}

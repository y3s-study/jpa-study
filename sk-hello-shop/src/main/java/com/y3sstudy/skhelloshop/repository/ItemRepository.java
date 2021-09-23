package com.y3sstudy.skhelloshop.repository;

import com.y3sstudy.skhelloshop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

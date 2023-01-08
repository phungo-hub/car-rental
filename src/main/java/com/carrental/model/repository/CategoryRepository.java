package com.carrental.model.repository;

import com.carrental.model.entity.Category;
import com.carrental.model.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Iterable<Category> findAllByNameContaining(String catName);
}

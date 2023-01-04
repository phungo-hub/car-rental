package com.carrental.model.repository;

import com.carrental.model.dto.CategoryDto;
import com.carrental.model.dto.OwnerDto;
import com.carrental.model.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository  extends JpaRepository<Car, Long> {
    Iterable<Car> findAllByOwner(OwnerDto ownerDto);
    Iterable<Car> findAllByCategory(CategoryDto categoryDto);
    Page<Car> findAllByCarNameContaining(String carName, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT c.name FROM cars c " +
                    "INNER JOIN categories cat ON cat.id = c.cat_id " +
                    "WHERE c.name = :name")
    List<String> findCarNameByCategory(@Param("name") String name);
}

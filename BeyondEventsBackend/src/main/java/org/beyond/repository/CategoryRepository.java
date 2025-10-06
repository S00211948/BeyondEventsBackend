package org.beyond.repository;

import org.beyond.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.UUID;
import java.lang.Long;
import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> { 
    Category findByid(Long id);
}

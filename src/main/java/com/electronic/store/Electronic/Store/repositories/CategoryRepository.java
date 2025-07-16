package com.electronic.store.Electronic.Store.repositories;

import com.electronic.store.Electronic.Store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String>
{
}

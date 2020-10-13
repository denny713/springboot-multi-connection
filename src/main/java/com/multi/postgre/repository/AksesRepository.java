package com.multi.postgre.repository;

import com.multi.postgre.entity.Akses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AksesRepository extends JpaRepository<Akses, String> {
}

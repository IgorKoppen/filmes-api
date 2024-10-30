package com.github.igorkoppen.filmes.api.repository;

import com.github.igorkoppen.filmes.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}

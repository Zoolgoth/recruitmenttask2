package com.dackow.recruitmenttask.repositories;

import com.dackow.recruitmenttask.models.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

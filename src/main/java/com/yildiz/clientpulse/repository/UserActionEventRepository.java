package com.yildiz.clientpulse.repository;

import com.yildiz.clientpulse.models.UserActionEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionEventRepository extends JpaRepository<UserActionEventEntity, Long> {
}
package com.yildiz.clientpulse.repository;

import com.yildiz.clientpulse.models.UserActionEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserActionEventRepository extends JpaRepository<UserActionEventEntity, Long> {
    List<UserActionEventEntity> findByUserId(Long userId);
    @Query("SELECT u.actionType, COUNT(u) FROM UserActionEventEntity u GROUP BY u.actionType")
    List<Object[]> countEventsByType();
}

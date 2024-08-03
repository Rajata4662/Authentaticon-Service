package dev.rajat.User.service.Repository;

import dev.rajat.User.service.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {


    Optional<Session> findByTokenAndUser_Id(String token, Long userId);
}
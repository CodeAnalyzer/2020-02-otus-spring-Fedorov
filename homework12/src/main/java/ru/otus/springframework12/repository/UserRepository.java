package ru.otus.springframework12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springframework12.domain.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    User findByName(String name);
}

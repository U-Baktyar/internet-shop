package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.entity.user.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByLogin(String login);
}

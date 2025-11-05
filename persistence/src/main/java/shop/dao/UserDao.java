package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.entity.user.User;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.login =:login")
    User findByLogin(@Param("login") String login);

}

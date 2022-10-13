package base.module.repositories;

import java.util.Optional;

import base.module.beans.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<UserDetail, Integer> {

	public abstract Optional<UserDetail> findByUsername(String username);
}

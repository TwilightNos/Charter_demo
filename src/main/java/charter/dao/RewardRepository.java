package charter.dao;

import charter.pojo.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RewardRepository extends JpaRepository<Reward,Integer> {




    @Query(value = "select * from Reward where customer_id=:id",nativeQuery = true)
    List<Reward> findAllByCustomerId(@Param("id") Integer id);
}

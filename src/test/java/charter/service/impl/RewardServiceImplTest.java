package charter.service.impl;

import charter.exception.CustomerIdNotFoundException;
import charter.pojo.Reward;
import charter.service.RewardService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class RewardServiceImplTest {


    @Autowired
    private RewardService service;


    @Test
    @Sql(scripts = "classpath:data.sql")
    public void getRewardPointById() {

        Map<String,Integer> result = new HashMap<String,Integer>(){{
            put("thirdMonth",120);
            put("secondMonth",180);
            put("firstMonth",90);
            put("ThreeMonthsTotal",390);
            put("code",0);
        }};
        assertEquals(result,service.getRewardPointById(1));


    }


    @Test
    @Sql(scripts = "classpath:data.sql")
    public void getRewardPointByIdNotFound(){
        assertThrows(CustomerIdNotFoundException.class,()->service.getRewardPointById(2));
    }


    @Test
    @Sql(scripts = "classpath:data.sql")
    public void testAddReward(){
        Reward reward = new Reward();
        reward.setTransactionId(8);
        reward.setCustomerId(2);
        reward.setTransactionAmount(100);
        reward.setTransactionDate(LocalDate.of(2023,5,25));
        assertEquals(reward,service.addReward(reward));
    }


}
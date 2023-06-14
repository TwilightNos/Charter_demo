package charter.service.impl;

import charter.pojo.Reward;
import charter.service.RewardService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
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
    void getRewardPointById() {

        Map<String,Integer> rewardList = service.getRewardPointById(1);
        System.out.println(rewardList);


    }
}
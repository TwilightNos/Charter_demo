package charter.integrationTest;

import charter.controller.RewardController;
import charter.exception.CustomerIdNotFoundException;
import charter.pojo.Reward;
import charter.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class RewardAPIIntegrationTest {


    @Mock
    RewardService rewardService;

    @InjectMocks
    RewardController rewardController;

    @BeforeEach
    public void configMock(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetPoints(){
        Mockito.when(rewardService.getRewardPointById(ArgumentMatchers.anyInt()))
                .thenReturn(new HashMap<String,Integer>(){{
                    put("thirdMonth",50);
                    put("secondMonth",70);
                    put("firstMonth",90);
                    put("ThreeMonthsTotal",210);
                    put("code",0);
                }});

        Map<String,Integer> response= rewardController.getRewardPointById(1);

        assertEquals(50,response.get("thirdMonth"));
        assertEquals(70,response.get("secondMonth"));
        assertEquals(90,response.get("firstMonth"));
        assertEquals(210,response.get("ThreeMonthsTotal"));
        assertEquals(0,response.get("code"));

        verify(rewardService).getRewardPointById(1);
    }

    @Test
    public void testInsertRecords(){
        Mockito.when(rewardService.addReward(ArgumentMatchers.any(Reward.class)))
                .thenReturn(new Reward(1,1,100, LocalDate.of(2023,5,25)));
        Reward result = rewardController.addReward(new Reward());
        assertEquals(new Reward(1,1,100, LocalDate.of(2023,5,25)),result);
        verify(rewardService).addReward(new Reward());
    }


    @Test
    public void testCustomerIdNotFound(){
        Mockito.when(rewardService.getRewardPointById(ArgumentMatchers.any()))
                .thenThrow(new CustomerIdNotFoundException());
        assertThrows(CustomerIdNotFoundException.class,()->rewardService.getRewardPointById(1));
        verify(rewardService).getRewardPointById(1);
    }


}

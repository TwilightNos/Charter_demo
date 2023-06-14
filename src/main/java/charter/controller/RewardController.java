package charter.controller;

import charter.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/Reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;



    /**
     *
     * @param id: The 'id' Param is transported to backend in the url, get this parameter use @PathVariable in the endpoint
     * @return the return value of this endpoint is a 2-length int array.
     *         This array contains 2 results, including the reward points earned for the customer with the id transported
     *         from the parameter, this api will call the service layer to calculate the per-month and total reward and
     *         store it inside the return array.
     */
    @GetMapping("/{id}")
    public Map<String,Integer> getRewardPointById(@PathVariable Integer id){

        Map<String,Integer> rewardPoints = rewardService.getRewardPointById(id);
        System.out.println(rewardPoints);


        return rewardPoints;
    }



}

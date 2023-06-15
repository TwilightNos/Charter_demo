package charter.controller;

import charter.pojo.Reward;
import charter.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/Reward")
@CrossOrigin(origins = "*")
public class RewardController {

    @Autowired
    private RewardService rewardService;



    /**
     * This method will get result from the service layer and send it to front end
     * @param id: The 'id' Param is transported to backend in the url, get this parameter use @PathVariable in the endpoint
     * @return The return value is a map contains the result sent to the front-end
     */
    @GetMapping("/{id}")
    public Map<String,Integer> getRewardPointById(@PathVariable Integer id){
        return rewardService.getRewardPointById(id);
    }

    /**
     * This method will receive a new reward record from front-end and send it to service layer to add it
     * @param reward a Reward Object in request body
     * @return it returns a Reward Object which is the result from service layer
     */
    @PostMapping
    public Reward addReward(@Valid @RequestBody Reward reward){
        return rewardService.addReward(reward);
    }



}

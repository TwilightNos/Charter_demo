package charter.service;

import charter.pojo.Reward;

import java.util.Map;

public interface RewardService {


    public Map<String, Integer> getRewardPointById(Integer id);

    public Reward saveReward(Reward reward);

}

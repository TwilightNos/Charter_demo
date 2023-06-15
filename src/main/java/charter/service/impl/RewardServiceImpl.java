package charter.service.impl;

import charter.dao.RewardRepository;
import charter.exception.CustomerIdNotFoundException;
import charter.pojo.Reward;
import charter.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository repository;




    /**
     * This method will use the customer id received to calculate the reward point for that customer according to the
     * records in the database
     * @param id: when called, the controller will transport the id this method, and this parameter will be used to
     *            search in the database for specific customer records
     * @return this method will return the calculated value based on the data from the DAO layer
     */
    @Override
    public Map<String, Integer> getRewardPointById(Integer id) {

        List<Reward> rewards = repository.findAllByCustomerId(id);
        if(rewards.size() == 0){
            throw new CustomerIdNotFoundException();
        }

        // get current Date
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();


        LocalDate latestDate = LocalDate.of(year,month,day);
        LocalDate thirdMonth = latestDate.minusMonths(1);
        LocalDate secondMonth = thirdMonth.minusMonths(1);
        LocalDate firstMonth = secondMonth.minusMonths(1);
        /**
         * This Map contains five key-value pairs
         * ThreeMonthsTotal key-value pair represents the total points for the customer in last three month
         * firstMonth represents the point in the first month in past three months
         * secondMonth represents the point in the second month in past three months
         * thirdMonth represents the point in the third month in past three months
         * code represents the response status, 0 for success, 1 for failure, 2 for invalid input value
         */
        Map<String,Integer> rewardPoints = new HashMap<>();
        rewardPoints.put("thirdMonth",calculatePointByDate(thirdMonth,latestDate,rewards));
        rewardPoints.put("secondMonth",calculatePointByDate(secondMonth,thirdMonth,rewards));
        rewardPoints.put("firstMonth",calculatePointByDate(firstMonth,secondMonth,rewards));
        rewardPoints.put("ThreeMonthsTotal",calculatePointByDate(firstMonth,latestDate,rewards));
        rewardPoints.put("code",0);
        return rewardPoints;
    }


    /**
     * To avoid conflict, this method will create a new Reward Object according to the Reward Object from the
     * control layer, set all values according to the data from control layer except the transaction id, which
     * should be automatically generated. It calls the save method from the JpaRepository to insert a new record
     * to database.
     * @param reward: a Reward Object from the control layer
     * @return save method in JpaRepository will return the Object it saved to the database.
     */
    @Override
    public Reward addReward(Reward reward) {

        Reward newReward = new Reward();
        newReward.setCustomerId(reward.getCustomerId());
        newReward.setTransactionAmount(reward.getTransactionAmount());
        newReward.setTransactionDate(reward.getTransactionDate());

        return repository.save(newReward);
    }

    /**
     * This method is for the usage to calculate the point according to the start and end time of a certain time period,
     * which inside this scenario should be one month, and a list of records, which contains the date of transaction.
     * @param beginDate the beginning date of the time period, not included when calculating
     * @param endDate the end date of the time period, included when calculating
     * @param rewardList the list used to count for the point of certain customer
     * @return the return value is the point for that customer in that time period
     */
    private int calculatePointByDate(LocalDate beginDate,LocalDate endDate, List<Reward> rewardList){
        int point = 0;
        for(Reward r : rewardList){
            if(r.getTransactionDate().compareTo(beginDate)>0 && r.getTransactionDate().compareTo(endDate) <= 0){
                int amount = r.getTransactionAmount();
                if(amount>100){
                    point += 50 + 2*(amount-100);
                } else if (amount > 50) {
                    point += amount-50;
                }
            }
        }
        return point;
    }
}

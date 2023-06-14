package charter.service.impl;

import charter.dao.RewardRepository;
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
     * This is the override method of the RewardService, to get more compatibility, use a implementation class
     * and an interface to achieve this feature
     *
     * @param id: when called, the controller will transport the id it received to this method, and this parameter will
     *            be transported to the DAO layer to get the data
     * @return this method will return the calculated value for the user with certain id based on the data from the
     * DAO layer
     */
    @Override
    public Map<String, Integer> getRewardPointById(Integer id) {

        List<Reward> rewards = repository.findAllByCustomerId(id);

        // get current Date
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();


        LocalDate latestDate = LocalDate.of(year,month,day);
        LocalDate lastMonth = latestDate.minusMonths(1);
        LocalDate lastTwoMonth = lastMonth.minusMonths(1);
        LocalDate lastThreeMonth = lastTwoMonth.minusMonths(1);

        Map<String,Integer> rewardPoints = new HashMap<>();
        rewardPoints.put("lastMonth",calculatePointByDate(lastMonth,latestDate,rewards));
        rewardPoints.put("lastTwoMonth",calculatePointByDate(lastTwoMonth,lastMonth,rewards));
        rewardPoints.put("lastThreeMonth",calculatePointByDate(lastThreeMonth,lastTwoMonth,rewards));
        rewardPoints.put("ThreeMonthsTotal",calculatePointByDate(lastThreeMonth,latestDate,rewards));
        return rewardPoints;
    }

    /**
     * This method is for the usage to calculate the point according to the start and end time of a certain time period,
     * which inside this scenario should be one month, and a list of records, which contains the date of transaction.
     * @param beginDate the beginning date of the time period
     * @param endDate the end date of the time period
     * @param rewardList the list used to count for the point of certain customer
     * @return the return value is the point for that customer in that time period
     */
    private int calculatePointByDate(LocalDate beginDate,LocalDate endDate, List<Reward> rewardList){
        int point = 0;
        for(Reward r : rewardList){
            // the time period contains the endDate and excludes the beginDate
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

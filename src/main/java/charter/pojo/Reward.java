package charter.pojo;



import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * This Pojo Class is used to Store The Data
 */
@Entity
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "transaction_amount")
    @Min(value = 0)
    private Integer transactionAmount;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    public Reward(Integer transactionId, Integer customerId, Integer transactionAmount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public Reward() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reward reward = (Reward) o;
        return Objects.equals(transactionId, reward.transactionId) && Objects.equals(customerId, reward.customerId) && Objects.equals(transactionAmount, reward.transactionAmount) && Objects.equals(transactionDate, reward.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, customerId, transactionAmount, transactionDate);
    }

    @Override
    public String toString() {
        return "Reward{" +
                "transactionId=" + transactionId +
                ", customerId=" + customerId +
                ", transactionAmount=" + transactionAmount +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}

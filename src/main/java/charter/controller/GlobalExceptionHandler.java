package charter.controller;

import charter.exception.CustomerIdNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This exception handler will receive a CustomerIdNotFoundException, and receice a hashMap with code 1
     * to indicate the front end that this customer id is not in record.
     * @param ex the exception from the controller
     * @return this exception handler will return a hashmap with same structure as normal situation, just different at
     *         the value of the code. Code 1 represents failure and code 0 represents success. Other values in the map
     *         are default values.
     */
    @ExceptionHandler(CustomerIdNotFoundException.class)
    @ResponseBody
    public Map<String,Integer> exceptionHandlerCustomerIdNotFound(Exception ex){
        Map<String,Integer> response = new HashMap<>();
        response.put("thirdMonth",0);
        response.put("secondMonth",0);
        response.put("firstMonth",0);
        response.put("ThreeMonthsTotal",0);
        response.put("code",1);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String,Integer> exceptionHandlerMethodArgumentNotValid(Exception ex){
        Map<String,Integer> response = new HashMap<>();
        response.put("thirdMonth",0);
        response.put("secondMonth",0);
        response.put("firstMonth",0);
        response.put("ThreeMonthsTotal",0);
        response.put("code",2);
        return response;
    }


}

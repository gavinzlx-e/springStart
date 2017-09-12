package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by @author linxin on 09/09/2017br>
 */
@RestController
public class PriceService {

    @RequestMapping(value = "/getPrice" ,method= RequestMethod.GET)
    public String getPrice(){
        return "100";
    }




}

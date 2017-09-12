package hello;

import com.alibaba.fastjson.JSON;
import com.yyfq.wap.core.model.templateMsg.TemplateMsgPart.TemplateValue;
import com.yyfq.wap.core.model.templateMsg.TemplateReceiveMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by @author linxin on 11/09/2017.  <br>
 */
@RestController
public class WapTest {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    Logger logger= LoggerFactory.getLogger(WapTest.class);

    public TemplateReceiveMsg getMsg(){

        TemplateReceiveMsg msg1=new TemplateReceiveMsg();
        Map data=new HashMap<String,TemplateValue>();

        data.put("first",new TemplateValue("恭喜您，您的信用额度获得提高"));
        data.put("keyword1",new TemplateValue("5000"));
        data.put("keyword2",new TemplateValue("100"));
        data.put("keyword3",new TemplateValue("2015年8月21日"));
        data.put("remark",new TemplateValue("点击下方的“业务办理”-“取现金”，或直接点击详情，即可全额取现！"));

        msg1.setData(data);
        msg1.setTemplateId("MLosFzG3lRtYRSy8tQG2IWUHxFsoo22AsLVYRo1ipb8");
        msg1.setUserId("1281073018920");

        String x= UUID.randomUUID().toString();
        msg1.setSerialNo( x.substring( x.length()-20,x.length()-1)) ;

        return msg1;
    }

    @RequestMapping("/wapTest")
    public String postRequestToWap() throws URISyntaxException {
        String str=JSON.toJSONString(getMsg()) ;
        RequestEntity requestEntity=new RequestEntity(HttpMethod.POST, new URI("http://localhost:8080/templateMsg/send"));

        ResponseEntity responseEntity=restTemplate().postForEntity("http://localhost:8080/templateMsg/send",str,String.class);
        String response=JSON.toJSONString(responseEntity);

        logger.info("reponse: {}",response);
        return response;

    }
}

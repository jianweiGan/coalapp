package com.skyblue.coalapp.server.LogIn.service;

import com.skyblue.coalapp.server.example.domain.User;
import com.skyblue.coalapp.server.example.service.UserService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by 张杨 on 2017/5/16.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public String getVerificationCode(String phoneNum) {

        //生成随机六位数验证码
        Integer randNum =  new Random().nextInt(999999);

        String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "23552267";
        String secret=  "23b317591f330b629a89b5df217084ff";

        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend( "" );
        req.setSmsType( "normal" );
        req.setSmsFreeSignName( "金太阳" );
        req.setRecNum(phoneNum);
        req.setSmsTemplateCode( "SMS_33485709" );
        req.setSmsParamString( "{coupon:'"+ randNum +"'}" );
       /* try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            boolean success = rsp.getResult().getSuccess();
            if(success) {
                randNum = null;
            }
        } catch (ApiException e) {

        }*/

        return randNum.toString();
    }

    @Override
    public User login(String phoneNum) {

        // try to get the user from DB
        User userInfo = userService.findByPhone(phoneNum);

        if(null == userInfo){
            // if cant get then create new User
            userInfo = userService.CreateUserAndSave(phoneNum);
        }

        return userInfo;
    }
}
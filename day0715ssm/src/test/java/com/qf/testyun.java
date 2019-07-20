package com.qf;

import com.qf.mapper.AcTest;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.junit.Test;

import java.util.Map;

public class testyun extends AcTest {
   @Test
    public void testyun(){
       YunpianClient clnt = new YunpianClient("416968376a9b5bb10f31cc082209bdcd").init();

       int code=(int)((Math.random()*9+1)*100000);
       System.out.println(code);

       //发送短信API
       Map<String, String> param = clnt.newParam(2);
       param.put(YunpianClient.MOBILE, "15821965770");
       param.put(YunpianClient.TEXT, "【云片网】您的验证码是"+code);
       Result<SmsSingleSend> r = clnt.sms().single_send(param);
//获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

//账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

//释放clnt
       clnt.close();
   }
}

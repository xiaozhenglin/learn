package com.chehaha.api.wechat.event;

import com.chehaha.channel.entity.UserRegInfo;
import com.chehaha.common.data.pojo.Criterion;
import com.chehaha.common.data.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.xml.ws.Action;
import java.util.Arrays;

/**
 * @author DK
 * @date 2018/8/20 22:48
 */
@Component
public class SubscribeListener implements ApplicationListener<SubscribeEvent> {
    @Autowired
    ICrudService crudService;

    @Override
    @Async
    public void onApplicationEvent(SubscribeEvent event) {
        UserRegInfo openId = crudService.findOne(Arrays.asList(Criterion.instance("openId", event.getOpenId())), UserRegInfo.class);
        if(openId!=null){
            openId.setAttention(event.isAttention());
            crudService.save(openId);
        }

    }
}

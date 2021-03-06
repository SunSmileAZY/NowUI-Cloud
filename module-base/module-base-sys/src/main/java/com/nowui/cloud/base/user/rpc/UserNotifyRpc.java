package com.nowui.cloud.base.user.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 用户消息队列表服务调用
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Component(value = "userNotifyRpc")
@FeignClient(name = "module-base")
public interface UserNotifyRpc {

}
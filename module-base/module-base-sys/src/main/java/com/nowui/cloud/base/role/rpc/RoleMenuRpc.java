package com.nowui.cloud.base.role.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 角色菜单服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "roleMenuRpc")
@FeignClient(name = "module-base")
public interface RoleMenuRpc {

}
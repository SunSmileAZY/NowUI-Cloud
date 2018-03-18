package com.nowui.cloud.hospital.course.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 课程视频服务调用
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Component(value = "courseVideoRpc")
@FeignClient(name = "module-hospital")
public interface CourseVideoRpc {

}
package com.nowui.cloud.sns.topic.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 会员主页信息
 * @author xupengfei
 *
 * 2018年3月4日
 */
@Component

@TableName(value = "member_homepage")
public class MemberHomepage extends BaseEntity {

    /**
     * 会员主页编号
     */
	@Id
    @TableId
    @NotNull(message = "会员主页编号不能为空")
    @Length(max = 32, message = "会员主页编号长度超出限制")
    private String memberHomepageId;
    public static final String MEMBER_HOMEPAGE_ID = "memberHomepageId";

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

}
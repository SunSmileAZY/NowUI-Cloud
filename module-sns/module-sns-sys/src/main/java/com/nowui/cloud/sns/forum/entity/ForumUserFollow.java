package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 论坛用户关注
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@Document(indexName = "nowui", type = "forum_user_follow_info")
@TableName(value = "forum_user_follow_info")
public class ForumUserFollow extends BaseEntity {

    /**
     * 论坛用户关注编号
     */
	@Id
    @TableId
    @NotNull(message = "论坛用户关注编号不能为空")
    @Length(max = 32, message = "论坛用户关注编号长度超出限制")
    private String forumUserFollowId;
    public static final String FORUM_USER_FOLLOW_ID = "forumUserFollowId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 论坛编号
     */
    @Field
    @TableField
    @NotNull(message = "论坛编号不能为空")
    @Length(max = 32, message = "论坛编号长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";
    
    /**
     * 论坛编号列表
     */
    public static final String FORUM_ID_LSIT = "forumIdList";
    
    public String getForumUserFollowId() {
        return getString(FORUM_USER_FOLLOW_ID);
    }
    
    public void setForumUserFollowId(String forumUserFollowId) {
        put(FORUM_USER_FOLLOW_ID, forumUserFollowId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getForumId() {
        return getString(FORUM_ID);
    }
    
    public void setForumId(String forumId) {
        put(FORUM_ID, forumId);
    }


}
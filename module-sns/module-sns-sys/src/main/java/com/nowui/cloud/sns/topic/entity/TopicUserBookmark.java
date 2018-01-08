package com.nowui.cloud.sns.topic.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 话题用户收藏关联
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@TableName(value = "topic_user_bookmark_map")
public class TopicUserBookmark extends BaseEntity {

    /**
     * 用户收藏id
     */
    @TableId
    @NotNull(message = "用户收藏id不能为空")
    @Length(max = 32, message = "用户收藏id长度超出限制")
    private String userBookMarked;
    public static final String USER_BOOK_MARKED = "userBookMarked";

    /**
     * 应用Id
     */
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 话题Id
     */
    @TableField
    @NotNull(message = "话题Id不能为空")
    @Length(max = 32, message = "话题Id长度超出限制")
    private String topicId;
    public static final String TOPIC_ID = "topicId";

    /**
     * 用户ID
     */
    @TableField
    @NotNull(message = "用户ID不能为空")
    @Length(max = 11, message = "用户ID长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getUserBookMarked() {
        return getString(USER_BOOK_MARKED);
    }
    
    public void setUserBookMarked(String userBookMarked) {
        put(USER_BOOK_MARKED, userBookMarked);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getTopicId() {
        return getString(TOPIC_ID);
    }
    
    public void setTopicId(String topicId) {
        put(TOPIC_ID, topicId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }


}
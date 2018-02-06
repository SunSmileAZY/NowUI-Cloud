package com.nowui.cloud.sns.topic.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserUnlike;
import com.nowui.cloud.sns.topic.router.TopicCommentUserLikeRouter;
import com.nowui.cloud.sns.topic.router.TopicCommentUserUnlikeRouter;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicCommentUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicCommentUserUnlikeView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题评论的取消点赞移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Api(value = "话题评论的取消点赞", description = "话题评论的取消点赞移动端接口管理")
@RestController
public class TopicCommentUserUnlikeMobileController extends BaseController {

	@Autowired
    private TopicCommentUserUnlikeService topicCommentUserUnlikeService;
	
	@Autowired
    private TopicCommentUserLikeService topicCommentUserLikeService;
	
	@ApiOperation(value = "新增话题评论的取消点赞")
    @RequestMapping(value = "/topic/comment/user/unlike/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        TopicCommentUserUnlike body = getEntry(TopicCommentUserUnlike.class);

        validateRequest(
        		body,
                TopicCommentUserUnlike.APP_ID,
                TopicCommentUserUnlike.COMMENT_ID
        );
        String appId = body.getAppId();
        String commentId = body.getCommentId();
        String userId = body.getSystemRequestUserId();
        
        // 先去取消评论点赞表查询有没有记录
        TopicCommentUserUnlikeView userUnlike = topicCommentUserUnlikeService.findTheCommentUserUnlike(appId, commentId, userId);
        // 有: 就不做操作,并返回提示
        if (userUnlike != null) {
        	throw new BusinessException("已经取消过点赞了");
		}
        
//        body.setAppId(appId);
//        body.setCommentId(commentId);
//        body.setUserId(userId);
        
        // 没有: 就去取消评论点赞表插入一条记录
//        Boolean result = topicCommentUserUnlikeService.save(body, appId, TopicCommentUserUnlikeRouter.TOPIC_COMMENT_USER_UNLIKE_V1_SAVE, Util.getRandomUUID(), userId);
        TopicCommentUserUnlike result = topicCommentUserUnlikeService.save(body, Util.getRandomUUID(), userId);
        
        boolean success = false;
        
        if (result != null) {
        	
        	// 去点赞表删除点赞记录
        	topicCommentUserLikeService.deleteByCommentIdAndUserIdWithRedis(commentId, appId,userId, userId);
        	
        	//sendMessage(result, TopicCommentUserUnlikeRouter.TOPIC_COMMENT_USER_UNLIKE_V1_SAVE, appId, userId);

        	success = true;
		}
        
        return renderJson(success);
    }
}
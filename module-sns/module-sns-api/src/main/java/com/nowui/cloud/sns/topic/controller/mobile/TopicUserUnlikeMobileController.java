package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicUserUnlikeView;
import com.nowui.cloud.sns.topic.view.TopicView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 话题用户取消点赞关联移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题用户取消点赞关联", description = "话题用户取消点赞关联移动端接口管理")
@RestController
public class TopicUserUnlikeMobileController extends BaseController {

	@Autowired
    private TopicUserUnlikeService topicUserUnlikeService;
	
	@Autowired
	private TopicUserLikeService topicUserLikeService;
	
	@Autowired
	private TopicService topicService;
	
	@ApiOperation(value = "新增话题用户取消点赞关联")
    @RequestMapping(value = "/topic/user/unlike/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
		TopicUserUnlike body = getEntry(TopicUserUnlike.class);
        validateRequest(
                body,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.TOPIC_ID,
                TopicUserUnlike.SYSTEM_REQUEST_USER_ID,
                TopicUserUnlike.MEMBER_ID
        );
        
        String requestUserId = body.getSystemRequestUserId();
        String memberId = body.getMemberId();
        
        String topicId = body.getTopicId();
        String appId = body.getAppId();
        
        TopicUserUnlikeView unlike = topicUserUnlikeService.findByTopciIdAndMemberId(topicId, memberId);
        
        if (!Util.isNullOrEmpty(unlike)) {
        	throw new BusinessException("已经取消点赞过了");
		}
        
        body.setMemberId(memberId);
        TopicUserUnlike result = topicUserUnlikeService.save(body, Util.getRandomUUID(), requestUserId);

        boolean success = false;

        if (result != null) {
            TopicUserLike userLike = topicUserLikeService.deleteByTopicIdAndMemberId(topicId, memberId, appId, requestUserId);
            
            
            if (userLike != null) {
            	// TODO 把MongoDB中的点赞记录逻辑删除
              	TopicUserLikeView userLikeView = JSON.parseObject(userLike.toJSONString(), TopicUserLikeView.class);
              	topicUserLikeService.delete(userLikeView);
			}
            
            
            // 将取消点赞记录 保存到MongoDB中
            TopicUserUnlikeView unlikeView = JSON.parseObject(result.toJSONString(), TopicUserUnlikeView.class);
            topicUserUnlikeService.save(unlikeView);
            
            // TODO 更新话题视图中的点赞数量: 减1
            TopicView topicView = topicService.find(topicId);
            Integer topicCountLike = topicView.getTopicCountLike();
            topicView.setTopicCountLike(topicCountLike - 1);
            topicService.update(topicView);
            
            //sendMessage(result, TopicUserUnlikeRouter.TOPIC_USER_UNLIKE_V1_SAVE, appId, userId);
            success = true;
        }
        return renderJson(success);
    }
}
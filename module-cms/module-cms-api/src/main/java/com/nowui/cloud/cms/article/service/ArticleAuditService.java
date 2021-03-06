package com.nowui.cloud.cms.article.service;
import java.util.List;

import com.nowui.cloud.cms.article.entity.ArticleAudit;
import com.nowui.cloud.cms.article.view.ArticleAuditView;
import com.nowui.cloud.service.SuperService;

/**
 * 文章审核业务接口
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
public interface ArticleAuditService extends SuperService<ArticleAudit, ArticleAuditView> {

    /**
     * 文章审核统计
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 审核人编号
     * @param articleAuditStatus 审核状态
     * @return Integer 文章审核统计
     */
    Integer countForAdmin(String appId, String articleId, String userId, String articleAuditStatus);

    /**
     * 文章审核列表
     *
     * @param appId 应用编号
     * @param articleId 文章编号
     * @param userId 审核人编号
     * @param articleAuditStatus 审核状态
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ArticleAudit> 文章审核列表
     */
    List<ArticleAudit> listForAdmin(String appId, String articleId, String userId, String articleAuditStatus, Integer pageIndex, Integer pageSize);
}

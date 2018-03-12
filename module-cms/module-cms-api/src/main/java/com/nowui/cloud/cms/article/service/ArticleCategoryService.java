package com.nowui.cloud.cms.article.service;

import java.util.List;
import java.util.Map;

import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.rpc.ArticleCategoryRpc;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import com.nowui.cloud.service.BaseService;

/**
 * 文章分类业务接口
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
public interface ArticleCategoryService extends BaseService<ArticleCategory, ArticleCategoryView>, ArticleCategoryRpc {
    
    /**
     * 文章分类统计
     *
     * @param appId 应用编号
     * @param articleCategoryName 文章分类名称
     * @param articleCategoryCode 文章分类编码
     * @return Integer 文章分类数量
     */
    Integer countForAdmin(String appId, String articleCategoryName, String articleCategoryCode);

    /**
     * 文章分类列表
     *
     * @param appId 应用编号
     * @param articleCategoryName 文章分类名称
     * @param articleCategoryCode 文章分类编码
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ArticleCategoryView> 文章分类视图列表
     */
    List<ArticleCategoryView> listForAdmin(String appId, String articleCategoryName, String articleCategoryCode, Integer pageIndex, Integer pageSize);
    
    /**
     * 文章分类树形分页列表
     *
     * @param appId 应用编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Map<String, Object>> 文章分类树形分页列表
     */
    List<Map<String, Object>> adminTreeList(String appId, Integer pageIndex, Integer pageSize);
    
    /**
     * 所有文章分类树形列表
     *
     * @param appId 应用编号
     * @return List<Map<String, Object>> 所有文章分类树形列表
     */
    List<Map<String, Object>> adminAllTreeList(String appId);
    
    /**
     * 根据文章分类编码查询文章分类信息
     * 
     * @param appId 应用编号
     * @param articleCategoryCode 文章分类编码
     * @return ArticleCategoryView 文章分类视图信息
     */
    ArticleCategoryView findByCategoryCode(String appId, String articleCategoryCode);
    
}

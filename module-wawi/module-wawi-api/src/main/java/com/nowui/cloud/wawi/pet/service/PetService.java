package com.nowui.cloud.wawi.pet.service;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.wawi.pet.entity.Pet;
import com.nowui.cloud.wawi.pet.view.PetView;

import java.util.List;

/**
 * 宠物业务接口
 *
 * @author hucy
 *
 * 2018-01-21
 */
public interface PetService extends SuperService<Pet, PetView> {

    /**
     * 宠物统计
     *
     * @param appId 应用编号
     * @param petCategoryId 宠物分类编号
     * @param petName 宠物名称
     * @return Integer 宠物统计
     */
    Integer countForAdmin(String appId, String petCategoryId, String petName);

    /**
     * 宠物列表
     *
     * @param appId 应用编号
     * @param petCategoryId 宠物分类编号
     * @param petName 宠物名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Pet> 宠物列表
     */
    List<Pet> listForAdmin(String appId, String petCategoryId, String petName, Integer pageIndex, Integer pageSize);

    /**
     * 我的宠物列表
     * @param appId 应用编号
     * @param userId 用户编号
     * @param petName 宠物名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Pet> 我的宠物列表
     */
    List<Pet> listByUserId(String appId, String userId, Integer pageIndex, Integer pageSize);

    /**
     * 我的宠物个数
     * @param appId 应用编号
     * @param userId 用户编号
     * @return Integer 我的宠物个数
     */
    Integer countByUserId(String appId, String userId);
}

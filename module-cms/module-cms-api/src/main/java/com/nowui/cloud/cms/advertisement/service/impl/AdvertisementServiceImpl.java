package com.nowui.cloud.cms.advertisement.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.mapper.AdvertisementMapper;
import com.nowui.cloud.cms.advertisement.repository.AdvertisementRepository;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 广告业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class AdvertisementServiceImpl extends SuperServiceImpl<AdvertisementMapper, Advertisement, AdvertisementRepository, AdvertisementView> implements AdvertisementService {
    
    @Override
    public Integer countForAdmin(String appId, String advertisementCategoryCode, String advertisementTitle) {
        Integer count = count(
                new BaseWrapper<Advertisement>()
                        .eq(Advertisement.APP_ID, appId)
                        .like(Advertisement.ADVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
                        .like(Advertisement.ADVERTISEMENT_TITLE, advertisementTitle)
                        .eq(Advertisement.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<AdvertisementView> listForAdmin(String appId, String advertisementCategoryCode, String advertisementTitle, Integer pageIndex, Integer pageSize) {
//        List<Advertisement> advertisementList = list(
//                new BaseWrapper<Advertisement>()
//                        .eq(Advertisement.APP_ID, appId)
//                        .like(Advertisement.ADVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
//                        .like(Advertisement.ADVERTISEMENT_TITLE, advertisementTitle)
//                        .eq(Advertisement.SYSTEM_STATUS, true)
//                        .orderAsc(Arrays.asList(Advertisement.ADVERTISEMENT_SORT))
//                ,m
//                ,n
//        );

        Criteria criteria = Criteria.where(AdvertisementView.APP_ID).is(appId)
                .and(AdvertisementView.ADVERTISEMENT_CATEGORY_CODE).regex(".*?" + advertisementCategoryCode + ".*")
                .and(AdvertisementView.ADVERTISEMENT_TITLE).regex(".*?" + advertisementTitle + ".*")
                .and(AdvertisementView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, AdvertisementView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<AdvertisementView> advertisementViewList = list(query, sort, pageIndex, pageSize);
        
        return advertisementViewList;
    }

	@Override
	public List<Advertisement> mobileList(String appId, String advertisementCategoryCode) {
		List<Advertisement> bannerList = list(new BaseWrapper<Advertisement>()
				.eq(Advertisement.APP_ID, appId)
				.eq(Advertisement.ADVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
				.eq(Advertisement.ADVERTISEMENT_IS_EFFICIENT, false)
				.eq(Advertisement.SYSTEM_STATUS, true)
				.orderAsc(Arrays.asList(Advertisement.ADVERTISEMENT_SORT))
			);

		return bannerList;
	}

    @Override
    public List<Advertisement> listByCategoryCode(String appId, String advertisementCategoryCode) {
        List<Advertisement> bannerList = list(new BaseWrapper<Advertisement>()
                .eq(Advertisement.APP_ID, appId)
                .eq(Advertisement.ADVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode)
                .eq(Advertisement.ADVERTISEMENT_IS_EFFICIENT, false)
                .eq(Advertisement.SYSTEM_STATUS, true)
                .orderAsc(Arrays.asList(Advertisement.ADVERTISEMENT_SORT))
            );

        return bannerList;
    }
    
}

package com.nowui.cloud.cms.navigation.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.mapper.NavigationMapper;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import com.nowui.cloud.cms.toolbar.entity.Toolbar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 导航栏业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class NavigationServiceImpl extends BaseServiceImpl<NavigationMapper, Navigation> implements NavigationService {

	@Autowired
	private FileRpc fileRpc;
	    
	
    @Override
    public Integer countForAdmin(String appId, String navigationCategoryCode, String navigationCode, String navigationName) {
        Integer count = count(
                new BaseWrapper<Navigation>()
                        .eq(Navigation.APP_ID, appId)
                        .likeAllowEmpty(Navigation.NAVIGATION_CATEGORY_CODE, navigationCategoryCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_CODE, navigationCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_NAME, navigationName)
                        .eq(Navigation.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Navigation> listForAdmin(String appId, String navigationCategoryCode, String navigationCode, String navigationName, Integer m, Integer n) {
        List<Navigation> navigationList = list(
                new BaseWrapper<Navigation>()
                        .eq(Navigation.APP_ID, appId)
                        .likeAllowEmpty(Navigation.NAVIGATION_CATEGORY_CODE, navigationCategoryCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_CODE, navigationCode)
                        .likeAllowEmpty(Navigation.NAVIGATION_NAME, navigationName)
                        .eq(Navigation.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Navigation.SYSTEM_CREATE_TIME)),
                m,
                n
        );
        
      //查询工具栏图片
        for (Navigation navigation : navigationList) {
            File file = fileRpc.find(navigation.getNavigationImage());
            file.keep(File.FILE_ID, File.FILE_PATH);
            navigation.put(Navigation.NAVIGATION_IMAGE, file);
        }

        return navigationList;
    }

	@Override
	public List<Navigation> mobileList(String appId, String navigationCategoryCode) {
		
		List<Navigation> resultList = list(new BaseWrapper<Navigation>()
				.eq(Navigation.APP_ID, appId)
				.eq(Navigation.SYSTEM_STATUS, true)
				.eq(Navigation.NAVIGATION_CATEGORY_CODE, navigationCategoryCode)
				.orderDesc(Arrays.asList(Navigation.NAVIGATION_SORT))
			);
		
		return resultList;
	}

}
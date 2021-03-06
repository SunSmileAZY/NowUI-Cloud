package com.nowui.cloud.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author marcus
 * @since 2017-12-20
 */
@Component
@ConfigurationProperties(prefix = "config")
public class Config {
    
    private String uploadFilePath;

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }
    
}

package com.dyhospital.cloudhis.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * Description: Spring代码形式注入bean
 * User: zhouzhou
 * Date: 2019-09-07
 * Time: 11:36
 */
@Component
public class SpringContextUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

    private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    private static StringValueResolver stringValueResolver;

    /**
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境
     *
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }


    /**
     * 动态获取配置文件中的值
     *
     * @param name
     * @return
     */
    public static String getPropertiesValue(String name) {
        try {
            name = "${" + name + "}";
            return stringValueResolver.resolveStringValue(name);
        } catch (Exception e) {
            logger.error(String.format("当前环境变量中没有{%s}的配置", name), e);
            // 获取失败则返回null
            return null;
        }
    }

    // 判断当前环境是否为test/local
    public static boolean isTestEnv() {
        String[] activeProfiles = getActiveProfiles();
        if (activeProfiles.length < 1) {
            return false;
        }

        for (String activeProfile : activeProfiles) {
            logger.info("token请求的activeProfile为====================="+activeProfile);
            if (StringUtils.equals(activeProfile, "local") ||
                    StringUtils.equals(activeProfile, "test")) {
                return true;
            }
        }
        return false;
    }

    // 获取当前环境
    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();

    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.stringValueResolver = stringValueResolver;
    }
}
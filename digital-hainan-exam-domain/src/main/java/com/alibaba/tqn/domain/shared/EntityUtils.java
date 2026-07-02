package com.alibaba.tqn.domain.shared;

import java.util.Date;
import com.alibaba.tqn.common.application.AppRequestContextUtils;
import org.apache.commons.lang3.StringUtils;

public class EntityUtils {

    public static void initOperatingInfo(Entity entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            initModifyInfo(entity);
        } else {
            initCreateInfo(entity);
        }
    }

    public static void initCreateInfo(Entity entity) {
        String operator = getOperator();
        if (StringUtils.isBlank(entity.getCreator())) {
            entity.setCreator(operator);
        }
        if (StringUtils.isBlank(entity.getModifier())) {
            entity.setModifier(operator);
        }
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setModifiedTime(now);
    }

    public static void initModifyInfo(Entity entity) {
        String email = AppRequestContextUtils.getCurrentUserEmail();
        if (StringUtils.isNotBlank(email)) {
            entity.setModifier(email);
        }
        entity.setModifiedTime(new Date());
    }

    private static String getOperator() {
        String email = AppRequestContextUtils.getCurrentUserEmail();
        return StringUtils.isNotBlank(email) ? email : "System";
    }
}

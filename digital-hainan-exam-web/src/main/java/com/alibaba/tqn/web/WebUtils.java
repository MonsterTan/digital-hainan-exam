package com.alibaba.tqn.web;

import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.tqn.shared.result.BizResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebUtils {

    public static void writeApiResponse(HttpServletResponse response, BizResult<?> result) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(JSON.toJSONString(result));
            writer.flush();
        } catch (Exception e) {
            log.warn("Failed to write api response", e);
        }
    }
}

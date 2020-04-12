/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.admin.service.service;

import com.hy.yyzx.common.model.SysLogAction;
import com.hy.yyzx.common.model.SysLogActionExample;
import me.jason.generator.service.BaseService;

import javax.servlet.http.HttpServletRequest;

public interface SysLogActionService extends BaseService<SysLogAction, SysLogActionExample, Integer> {
    /**
     * 保存操作日志
     * @param request
     * @param id
     */
    void saveLog(HttpServletRequest request, Integer id);
}
package com.hy.yyzx.admin.service.controller;

import com.hy.yyzx.admin.service.annotations.PermissionAction;
import com.hy.yyzx.admin.service.annotations.PermissionControl;
import com.hy.yyzx.common.modules.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jason
 * @DateTime: 2020/4/19 11:43
 * @Description: TODO
 */
@RestController
@RequestMapping("/test")
@PermissionControl(menukey = "test", superUser = true)
@Api(value = "测试", tags = "测试")
public class TestController {

    @PostMapping(value = "/api")
    @ApiOperation(value = "测试api", notes = "测试api")
    @PermissionControl(action = PermissionAction.EDIT)
    public ResultMsg test(Integer id){
        if (id==null){
            return new ResultMsg(false,"参数错误！");
        }
        return new ResultMsg(true,"接口测试通过");
    }
}

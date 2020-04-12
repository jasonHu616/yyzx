package com.hy.yyzx.admin.service.interceptor;

import com.github.pagehelper.PageInfo;
import com.hy.yyzx.admin.service.annotations.CommonPermissionControl;
import com.hy.yyzx.admin.service.annotations.PermissionAction;
import com.hy.yyzx.admin.service.annotations.PermissionControl;
import com.hy.yyzx.admin.service.cache.MenuKeyCache;
import com.hy.yyzx.admin.service.cache.PermissionCache;
import com.hy.yyzx.common.model.SysRolePermission;
import com.hy.yyzx.common.model.SysUser;
import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.modules.ResultData;
import com.hy.yyzx.common.modules.ResultMsg;
import com.hy.yyzx.common.utils.JSONUtils;
import com.hy.yyzx.common.utils.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 19:50
 * @Description: 权限拦截器
 */
public class PermissionInterceptor extends BaseInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);


    @Autowired
    PermissionCache permissionCache;

    @Autowired
    MenuKeyCache menuKeyCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SysUser user = getLoginUser(request);
        if (handler.getClass().equals(HandlerMethod.class)) {
            HandlerMethod hm = (HandlerMethod) handler;
            String menukey = null;
            PermissionControl pc = hm.getBeanType().getAnnotation(PermissionControl.class);    //判断Controller权限
            logger.info("判断controller权限");
            if (pc != null) {
                if (pc.menukey() != null && !pc.menukey().equals("")) {
                    menukey = pc.menukey();
                    menuKeyCache.setMenu(user.getId(), pc.menukey());
                }
                logger.info("menukey ： " + menukey);
                if (pc.devmode()) {    //开发权限
                    if (user.getAdmintype().intValue() != 2) {
                        outputMessage(request, response, hm);
                        return false;
                    }
                }
                if (pc.superUser()) {    //超管权限
                    if (user.getAdmintype().intValue() < 1) {
                        outputMessage(request, response, hm);
                        return false;
                    }
                }

                if (user.getAdmintype().intValue() == 0) {    //普通用户权限判断
                    if (menukey != null && !menukey.trim().equals("")) {
                        if (!checkPermission(user, null, menukey, null, request)) {
                            outputMessage(request, response, hm);
                            return false;
                        }
                    }
                }
            }

            logger.info("判断Method权限");
            pc = hm.getMethod().getAnnotation(PermissionControl.class);    //判断Method权限
            if (pc != null) {
                if (pc.menukey() != null && !pc.menukey().trim().equals("")) {
                    menukey = pc.menukey();
                    //request.setAttribute(Globals.REQUEST_MENUKEY, pc.menukey());
                }
                if (pc.devmode()) {    //开发权限
                    if (user.getAdmintype().intValue() != 2) {
                        outputMessage(request, response, hm);
                        return false;
                    }
                }
                if (pc.superUser()) {    //超管权限
                    if (user.getAdmintype().intValue() < 1) {
                        outputMessage(request, response, hm);
                        return false;
                    }
                }

                if (user.getAdmintype().intValue() == 0) {    //普通用户权限判断
                    if (menukey != null && !menukey.trim().equals("")) {
                        PermissionAction action = pc.action();
                        if (!checkPermission(user, pc.idField(), menukey, action, request)) {
                            outputMessage(request, response, hm);
                            return false;
                        }
                    }
                }
            }

            //公用部分(common)权限判断
            logger.info("公用部分(common)权限判断");
            CommonPermissionControl cpc = hm.getBeanType().getAnnotation(CommonPermissionControl.class);
            /*if (cpc != null) {
                Class superClass = hm.getBeanType().getSuperclass();
                if (superClass != null && superClass.equals(CommonController.class)) {
                    PermissionControl[] permissionControlList = cpc.permissionControl();
                    if (permissionControlList != null && permissionControlList.length > 0) {
                        for (PermissionControl c : permissionControlList) {
                            if (c != null) {
                                if (c.methodName() != null && !c.methodName().equals("")) {
                                    if (c.methodName().equals(hm.getMethod().getName())) {
                                        if (c.devmode()) {    //开发权限
                                            if (user.getDevmode().intValue() == 0) {
                                                outputMessage(request, response, hm);
                                                return false;
                                            }
                                        }
                                        if (c.superUser()) {    //超管权限
                                            if (user.getDevmode().intValue() == 0 && user.getSuperuser().intValue() == 0) {
                                                outputMessage(request, response, hm);
                                                return false;
                                            }
                                        }

                                        if (user.getDevmode().intValue() == 0 && user.getSuperuser().intValue() == 0) {    //普通用户权限判断
                                            if (menukey != null && !menukey.equals("")) {
                                                PermissionAction action = c.action();
                                                if (!checkPermission(user, c.idField(), menukey, action, request)) {
                                                    outputMessage(request, response, hm);
                                                    return false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }*/
        }
        return true;
    }


    private boolean checkPermission(SysUser user, String idField, String menukey, PermissionAction action, HttpServletRequest request) throws Exception {
        logger.info("验证用户权限：" + menukey + "/" + action);
        boolean result = false;
        if (user == null) return false;
        if (menukey == null || menukey.trim().equals("")) return false;
        SysRolePermission permission = getPermission(user.getRoleid(), menukey);
        if (action != null && !action.equals(PermissionAction.NONE)) {
            if (action.equals(PermissionAction.ADD)) {
                //sql += " AND addscope=1";
                return checkActions(permission.getActions(), "ADD");
            } else if (action.equals(PermissionAction.EDIT)) {
                //sql += " AND editscope=1";
                return checkActions(permission.getActions(), "EDIT");
            }else if (action.equals(PermissionAction.DELETE)) {
                return checkActions(permission.getActions(), "DELETE");
            }else if (action.equals(PermissionAction.LIST)) {
                //sql += " AND listscope=1";
                return checkActions(permission.getActions(), "LIST");
            } else if (action.equals(PermissionAction.ANY)) {
                //sql += " AND (addscope=1 OR editscope=1 OR listscope=1)";
                return (
                        checkActions(permission.getActions(), "ADD")
                                || checkActions(permission.getActions(), "EDIT")
                                || checkActions(permission.getActions(), "LIST")
                                || checkActions(permission.getActions(), "DELETE")
                );
            } else if (action.equals(PermissionAction.ADDOREDIT)) {
                if (idField != null && !idField.trim().equals("")) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
                    String line;
                    StringBuffer str = new StringBuffer();
                    while ((line = in.readLine()) != null) {
                        str.append(line);
                    }
                    Map<String, Object> map = JSONUtils.parse(str.toString(), Map.class);

                    Object id = map.get(idField);
                    String idVal = id == null ? "" : id.toString();
//                    String idVal = request.getParameter(idField);

                    if (idVal == null || idVal.trim().equals("") || idVal.trim().equals("0")) {
                        //sql += " AND addscope=1";
                        return checkActions(permission.getActions(), "ADD");
                    } else if (ValidateUtils.isInteger(idVal) && Integer.parseInt(idVal) != 0) {
                        //sql += " AND editscope=1";
                        return checkActions(permission.getActions(), "EDIT");
                    } else {
                        logger.error("ID验证参数错误");
                        return false;
                    }
                } else {
                    //sql += " AND addscope=1";
                    return checkActions(permission.getActions(), "ADD");
                }
            }
        } else if (action == null) {
            return (permission == null ||
                    checkActions(permission.getActions(), "ADD")
                    || checkActions(permission.getActions(), "EDIT")
                    || checkActions(permission.getActions(), "LIST")
                    || checkActions(permission.getActions(), "DELETE")
            );
        }
        //result = GroupPermissionDao.checkExist(sql, new Object[]{user.getGroupid(), menu.getId()});
        return result;
    }

    /**
     * 查找用户组菜单权限项
     *
     * @param roleid  int 用户角色id
     * @param menukey 菜单key
     * @return
     */
    private SysRolePermission getPermission(int roleid, String menukey) {
        List<SysRolePermission> permissions = permissionCache.getPermissionByRoleid(roleid);
        if (permissions == null) {
            permissionCache.reload();
        }
        if (permissions != null && !permissions.isEmpty()) {
            for (SysRolePermission p : permissions) {
                if (p.getMenukey().trim().equals(menukey.trim())) {
                    return p;
                }
            }
        }
        return new SysRolePermission();
    }

    /**
     * 验证是否包含权限
     *
     * @param actions 权限集合
     * @param action  具体权限
     * @return
     */
    private boolean checkActions(String actions, String action) {
        if (action == null || action.trim().equals("")) return false;
        if (actions == null || actions.trim().equals("")) return false;
        String[] actionsArr = actions.split(",");
        for (String temp : actionsArr) {
            if (temp.equalsIgnoreCase(action)) return true;
        }
        return false;
    }

    /**
     * 输出无权限信息
     *
     * @param request
     * @param response
     * @param handlerMethod
     */
    private void outputMessage(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();

            Class<?> returnType = handlerMethod.getMethod().getReturnType();
            if (returnType.equals(ResultMsg.class) || returnType.equals(ResultMsg.class)) {
                out.println(JSONUtils.toJSONString(new ResultMsg(false, "您没有当前数据的操作权限，请与管理员联系")));
            } else if (returnType.equals(ResultData.class)) {
                out.println(JSONUtils.toJSONString(ResultData.noauth()));
            } else if (returnType.equals(PageInfo.class)) {
                out.println(JSONUtils.toJSONString(new PageInfo<>()));
            } else if (returnType.equals(List.class)) {
                out.println("[]");
            } else if (returnType.equals(MapEntity.class)) {
                out.println("{}");
            }
        } catch (Exception e) {
            logger.error("输出权限控制信息出错", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

const getters = {
    sidebar: state => state.app.sidebar,
    language: state => state.app.language,
    device: state => state.app.device,
    visitedViews: state => state.tagsView.visitedViews,
    cachedViews: state => state.tagsView.cachedViews,
    token: state => state.user.token,
    user: state => state.user.user,
    steward: state => state.user.steward,
    roles: state => state.user.roles,
    permission_routers: state => state.permission.routers,
    addRouters: state => state.permission.addRouters,
    routers: state => state.permission.routers,
    errorLogs: state => state.errorLog.logs
}
export default getters

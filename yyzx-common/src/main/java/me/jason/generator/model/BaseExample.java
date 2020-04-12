package me.jason.generator.model;

/**
 * BaseExample 基类
 * @ClassName BaseExample 支持分组条件
 * @author Jason
 * @date 2020年4月8日 13:53:42
 */
public class BaseExample {

    protected String groupByClause;

    public String getGroupByClause() {
        return groupByClause;
    }

    public void setGroupByClause(String groupByClause) {
        this.groupByClause = groupByClause;
    }

}
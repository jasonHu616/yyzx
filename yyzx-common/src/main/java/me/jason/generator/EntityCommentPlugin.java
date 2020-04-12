package me.jason.generator;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.JDBCConnectionFactory;
import org.mybatis.generator.internal.util.StringUtility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * Comment of Entity,only support MySQL
 * Model 类注释(表的描述): MySQL
 * @ClassName CommentPlugin 
 * @Description 
 * @author Jason
 */
public class EntityCommentPlugin extends PluginAdapter {
        
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addModelClassComment(topLevelClass, introspectedTable);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {

        addModelClassComment(topLevelClass, introspectedTable);
        return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
    }

    protected void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        String createDDL=getTableCreateDDL(table);
        String tableComment = getTableComment(createDDL);
        topLevelClass.addJavaDocLine("/**");
        if(StringUtility.stringHasValue(tableComment))
            topLevelClass.addJavaDocLine(" * " + tableComment);
        topLevelClass.addJavaDocLine(" * " + table.toString());
        topLevelClass.addJavaDocLine(" * @date " + new Date().toString());
        topLevelClass.addJavaDocLine(" *");
        topLevelClass.addJavaDocLine(" */");

        //添加实体注解，用swagger
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
        topLevelClass.addAnnotation("@ApiModel(value = \""+topLevelClass.getType().getShortName()+"\", description = " +
                "\""+tableComment+"\")");
        //遍历字段，添加字段swagger注解
        List<Field> fields=topLevelClass.getFields();
        if (fields!=null && fields.size()>0){
            for (int i=0;i<fields.size();i++){
                fields.get(i).addAnnotation("@ApiModelProperty(value = \""+
                        (fields.get(i).getJavaDocLines().get(3).contains("IS AUTO_INCREMENT")?"ID 自增序列":fields.get(i).getJavaDocLines().get(1).substring(3).replaceAll("\n",""))
                        +"\", allowEmptyValue="
                        +(fields.get(i).getJavaDocLines().get(2).contains("NOT NULL")?"false":"true")+")");
            }
        }
    }

    /**
     * 获取表创建语句
     * @author Jason
     * @date 2020年4月8日 15:19:46
     * @param table
     */
    private String getTableCreateDDL(FullyQualifiedTable table) {
        String createDDL = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            JDBCConnectionFactory jdbc = new JDBCConnectionFactory(context.getJdbcConnectionConfiguration());
            connection = jdbc.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SHOW CREATE TABLE " + table.getIntrospectedTableName());
            if (rs != null && rs.next()) {
                createDDL = rs.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, rs);
        }
        return createDDL;
    }

    /**
     * 获取数据表注释
     * @param createDDL
     * @return
     */
    private String getTableComment(String createDDL){
        String tableComment = "";
        int index = createDDL.indexOf("COMMENT='");
        if (index < 0) {
            tableComment = "";
        } else {
            try {
                tableComment = createDDL.substring(index + 9);
                tableComment = tableComment.substring(0, tableComment.length() - 1);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return tableComment;
    }

    /**
     * 
     * @author Jason
     * @date 2020年4月8日 15:19:53
     * @param connection
     * @param statement
     * @param rs
     */
    private void closeConnection(Connection connection, Statement statement, ResultSet rs) {
        try {
            if (null != rs)
                rs.close();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();

            } finally {

                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * This plugin is always valid - no properties are required
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
}
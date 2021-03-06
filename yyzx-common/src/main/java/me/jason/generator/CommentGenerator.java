package me.jason.generator;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Date;
import java.util.Properties;

/**
 * Comment Generator
 * 根据数据库表或字段的COMMENT生成注释.
 * @Date 生成的时间可根据需要自己定义格式
 * @ClassName CommentGenerator 
 * @Description 
 * @author Jason
 */
public class CommentGenerator extends DefaultCommentGenerator {
    private Properties properties;
    private boolean suppressDate;
    private boolean suppressAllComments;

    public CommentGenerator() {
        this.properties = new Properties();
        this.suppressDate = false;
        this.suppressAllComments = false;
    }

    public void addJavaFileComment(CompilationUnit compilationUnit) {

        compilationUnit.addFileCommentLine("/*** copyright (c) 2020 Jason  ***/");
    }

    /**
     * XML file Comment
     */
    public void addComment(XmlElement xmlElement) {
        if (this.suppressAllComments) {
            return;
        }

    }

    public void addRootComment(XmlElement rootElement) {
    }

    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);

        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));

        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
    }

    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append("@date");
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    protected String getDateString() {
        if (this.suppressDate) {
            return null;
        }
        return new Date().toString();
    }

    /**
     * Comment of Example inner class(GeneratedCriteria ,Criterion)
     */
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
            return;
        }

        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "<p/>");
        innerClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().toString());
        addJavadocTag(innerClass, false);
        innerClass.addJavaDocLine(" */");
    }

    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        innerEnum.addJavaDocLine("/**");
        innerEnum.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().getAlias() + "<p/>");
        innerEnum.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString());

        addJavadocTag(innerEnum, false);

        innerEnum.addJavaDocLine(" */");
    }

    /**
     * entity filed Comment
     */
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        if (this.suppressAllComments) {
            return;
        }

//      if(introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().trim().equals(""))

        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        field.addJavaDocLine(" * " + (introspectedColumn.isNullable()?"CAN BE NULL":"NOT NULL"));
        field.addJavaDocLine(" * " + (introspectedColumn.isIdentity()?"IS AUTO_INCREMENT":"NOT AUTO_INCREMENT"));
        field.addJavaDocLine(" * @author Jason");
        field.addJavaDocLine(" * @date " + getDateString());
        field.addJavaDocLine(" * @return");
        field.addJavaDocLine(" */");
    }

    /**
     * Comment of EXample filed
     */
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
            return;
        }
        field.addJavaDocLine("/**");
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }

    /**
     * Comment of Example method
     */
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
            return;
        }
    }

    /**
     * entity Getter Comment
     */
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (this.suppressAllComments) {
            return;
        }
        method.addJavaDocLine("/**");


        method.addJavaDocLine(" * @return " + introspectedTable.getFullyQualifiedTable().getAlias() + " : " + introspectedColumn.getRemarks());
        method.addJavaDocLine(" */");
    }

    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (this.suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        method.addJavaDocLine("/**");

        Parameter parm = (Parameter) method.getParameters().get(0);
        sb.append(" * @param ");
        sb.append(parm.getName());
        sb.append(" : ");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }

    /**
     * Comment of Example inner class(Criteria)
     */
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (this.suppressAllComments) {
            return;
        }

        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().getAlias() + "<p/>");
        innerClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().toString());
        addJavadocTag(innerClass, markAsDoNotDelete);

        innerClass.addJavaDocLine(" */");
    }
}
package me.jason.generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

public class FullyQualifiedJavaTypeProxyFactory  extends FullyQualifiedJavaType{
    
    private static FullyQualifiedJavaType pageInfoInstance = new FullyQualifiedJavaType("me.jason.generator.model.PageModel");
    private static FullyQualifiedJavaType baseExampleInstance = new FullyQualifiedJavaType("me.jason.generator.model.BaseExample");
    private static FullyQualifiedJavaType baseMapperInstance = new FullyQualifiedJavaType("me.jason.generator.mapper.BaseMapper");
    private static FullyQualifiedJavaType baseServiceInstance = new FullyQualifiedJavaType("me.jason.generator.service.BaseService");
    private static FullyQualifiedJavaType baseServiceImplInstance = new FullyQualifiedJavaType("me.jason.generator.service.impl.BaseServiceImpl");
    
    public FullyQualifiedJavaTypeProxyFactory(String fullTypeSpecification) {
        super(fullTypeSpecification);
    }
    
    public static final FullyQualifiedJavaType getPageInfoInstanceInstance() {

        return pageInfoInstance;
    }
    public static final FullyQualifiedJavaType getBaseExampleInstance() {
        
        return baseExampleInstance;
    }
    
    public static final FullyQualifiedJavaType getBaseMapperInstance() {
        
        return baseMapperInstance;
    }
    public static final FullyQualifiedJavaType getBaseServiceInstance() {
        
        return baseServiceInstance;
    }
    public static final FullyQualifiedJavaType getBaseServiceImplInstance() {
        
        return baseServiceImplInstance;
    }
}
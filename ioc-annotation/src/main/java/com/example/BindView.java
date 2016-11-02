package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wxy on 2016/10/31.
 */

@Retention(RetentionPolicy.CLASS) //CLASS 程序编译时保留 SOURCE 留在源文件中 RUNTIME 保留在运行时

//public enum ElementType {
//    /**
//     * Class, interface or enum declaration.
//     */
//    TYPE,
//    /**
//     * Field declaration.
//     */
//    FIELD,
//    /**
//     * Method declaration.
//     */
//    METHOD,
//    /**
//     * Parameter declaration.
//     */
//    PARAMETER,
//    /**
//     * Constructor declaration.
//     */
//    CONSTRUCTOR,
//    /**
//     * Local variable declaration.
//     */
//    LOCAL_VARIABLE,
//    /**
//     * Annotation type declaration.
//     */
//    ANNOTATION_TYPE,
//    /**
//     * Package declaration.
//     */
//    PACKAGE
//}
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})//声明此类可以注解的对象
public @interface BindView {
    int value();
}

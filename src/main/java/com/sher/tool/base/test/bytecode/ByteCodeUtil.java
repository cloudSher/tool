package com.sher.tool.base.test.bytecode;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/21.
 */
public class ByteCodeUtil {

    public static void main(String args[]) throws NotFoundException, CannotCompileException, NoSuchFieldException {
        printAnnotation(info("com.sher.tool.base.test.bytecode.Table","field","name","javax.annotation.Resource","2222"),"field");

    }


    public static Class info(String entityClassPath,String fieldName,String memberName,String annotationName,String annotationValue) throws NotFoundException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(entityClassPath);

        ctClass.getDeclaredClasses();
        CtField field = ctClass.getField(fieldName);
        FieldInfo fieldInfo = field.getFieldInfo();
        System.out.println(fieldInfo.toString()+"=== attribute is "+field.getName());
        ConstPool constPool = fieldInfo.getConstPool();

        Annotation annotation = new Annotation(annotationName,constPool);
        annotation.addMemberValue(memberName,new StringMemberValue(annotationValue,constPool));

        AnnotationsAttribute  attribute = (AnnotationsAttribute) fieldInfo.getAttribute(AnnotationsAttribute.visibleTag);
        attribute.addAnnotation(annotation);
        fieldInfo.addAttribute(attribute);

        System.out.println("修改后的注解=="+attribute.getAnnotation(annotationName).getMemberValue(memberName));

        return ctClass.toClass();

    }


    public static void printAnnotation(Class clazz,String fieldName) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        java.lang.annotation.Annotation[] annotations = field.getAnnotations();
        Arrays.asList(annotations).forEach(System.out::println);
    }
}

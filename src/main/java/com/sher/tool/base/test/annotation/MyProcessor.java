package com.sher.tool.base.test.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/16.
 */
public class MyProcessor extends AbstractProcessor {

    Elements elementUtil;


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        elementUtil = processingEnv.getElementUtils();
        //获取注解声明元素
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Serialize.class);
        TypeElement typeElement;   //类元素
        List<VariableElement> list;
        for(Element el : elements){
            if(el.getKind() == ElementKind.CLASS){

            }
        }
        return false;
    }



}

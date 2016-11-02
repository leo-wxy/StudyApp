package com.example;

import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by wxy on 2016/11/1.
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.example.BindView"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class IocProcessor extends AbstractProcessor {

    private Filer mFileUtils;//和文件相关的辅助类，生产Code用
    private Elements mElementUtils;//获取元素相关信息
    private Messager mMessager;
    HashMap<String, ProxyInfo> proxyInfoHashMap;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFileUtils = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        proxyInfoHashMap = new HashMap<>();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<String>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            annotationTypes.add(annotation.getCanonicalName());//返回类的完整名称
        }
        return annotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(BindView.class);
        return annotationTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        String fqClassName, className, packageName;

//        Messager messager = processingEnv.getMessager();
//        for (TypeElement te : annotations) {
//            for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
//
//                TypeElement classElement = (TypeElement) e;
//                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: aaa" + e.toString()+
//                classElement.getQualifiedName().toString());
//            }
//        }
//
        for (Element element : roundEnv.getElementsAnnotatedWith(BindView.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement classElement = (TypeElement) element;
                PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();
                fqClassName = classElement.getQualifiedName().toString();
                className = classElement.getSimpleName().toString();
                packageName = packageElement.getQualifiedName().toString();
                int layoutId = classElement.getAnnotation(BindView.class).value();
                if (proxyInfoHashMap.containsKey(fqClassName)) {
                    proxyInfoHashMap.get(fqClassName).setLayoutId(layoutId);
                } else {
                    ProxyInfo proxyInfo = new ProxyInfo(packageName, className);
                    proxyInfo.setClassElement(classElement);
                    proxyInfo.setLayoutId(layoutId);
                    proxyInfoHashMap.put(fqClassName, proxyInfo);
                }
            }
        }
        return true;
    }
}




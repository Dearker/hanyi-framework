package com.hanyi.framework.springboot;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import com.hanyi.framework.annotation.ExtSpringBootApplication;
import com.hanyi.framework.config.PropertyConfig;
import com.hanyi.framework.springmvc.ExtDispatcherServlet;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.springboot ExtSpringApplication
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-01 21:41
 * @Version: 1.0
 */
public class ExtSpringApplication {

    private static final String PATHNAME = "target/classes";

    private static final String WEBAPP = "/WEB-INF/classes";

    private static final String CONSTIPATE = "/";

    private static final String FILEPATH = "src/main";

    private ExtSpringApplication(String... packageName) {
        new ExtDispatcherServlet(packageName);
    }

    public static void run(Class<?> primarySource, String... args){

        Assert.notNull(primarySource, "primarySource must not be null");
        String aPackage = ClassUtil.getPackage(primarySource);

        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(aPackage, ExtSpringBootApplication.class);
        Assert.notEmpty(classes," application run failed ");

        String[] packages = getPackages(primarySource,classes);

        new ExtSpringApplication(packages).start();
    }


    private static String[] getPackages(Class<?> primarySource,Set<Class<?>> classes){

        Set<String[]> packAgeSet = new HashSet<>(4 << 1);

        for (Class<?> classInfo : classes) {
            ExtSpringBootApplication extSpringBootApplication = classInfo.getDeclaredAnnotation(ExtSpringBootApplication.class);
            String[] basePackages = extSpringBootApplication.scanBasePackages();
            if(ArrayUtil.isNotEmpty(basePackages)){
                packAgeSet.add(basePackages);
            }
        }

        if(CollUtil.isEmpty(packAgeSet)){
            String name = primarySource.getPackage().getName();
            packAgeSet.add(new String[]{name});
        }

        Set<String> packages = new HashSet<>(4 << 1);
        for (String[] strings : packAgeSet) {
            packages.addAll(Arrays.asList(strings));
        }

        Object[] objects = packages.toArray();

        return Arrays.copyOf(objects, objects.length, String[].class);
    }


    private void start() {

        Tomcat tomcatServer = new Tomcat();
        tomcatServer.setPort(getPort());

        try {
            StandardContext ctx = (StandardContext) tomcatServer.addWebapp(CONSTIPATE, new File(FILEPATH).getAbsolutePath());
            ctx.setReloadable(false);

            File additionWebInfClasses = new File(PATHNAME);
            WebResourceRoot resources = new StandardRoot(ctx);
            resources.addPreResources(
                    new DirResourceSet(resources, WEBAPP, additionWebInfClasses.getAbsolutePath(), CONSTIPATE));
            tomcatServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tomcatServer.getServer().await();

    }

    private static int getPort() {
        Integer port = PropertyConfig.PROPERTYCONFIG.getInteger("server.port");
        return port == null ? 8080 : port;
    }

}

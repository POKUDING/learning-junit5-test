package com.luv2code.junitdemo;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class ConditionalTest {

    @Test
    @Disabled("해결되기 전까지 비활성화")
    void basicTest(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testOnWindowsOnly(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testOnMacOnly(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void testOnMacAndWindowsOnly(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testOnLinuxOnly(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testOnlyForJava17(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_13, max=JRE.JAVA_17)
    void testOnlyForJavaRangeMin13Max17(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_13)
    void testOnlyForJavaRangeMin13(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "POKU_ENV", matches = "DEV")
    void testOnlyForDevEnvironment(){
        //메소드 실행 및 결과 확인
    }

    @Test
    @EnabledIfSystemProperty(named = "POKU_SYS_PROP", matches = "CI_CD_DEPLOY")
    void testOnlyForSystemProperty(){
        //메소드 실행 및 결과 확인
    }

}

package com.luv2code.junitdemo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class) // 메소드 이름에서 () 제거하여 테스트 이름 표시
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // 테스트 이름에 _ 를 공백으로 대체
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class) // 테스트 Class 이름과 메소드 이름을 조합하여 표시
//@TestMethodOrder(MethodOrderer.MethodName.class) // 메소드 이름 기준으로 정렬
//@TestMethodOrder(MethodOrderer.DisplayName.class) // DisplayName 에 지정된 이름 기준으로 정렬
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Order Annotation 에 지정된 순서대로 정렬
@TestMethodOrder(MethodOrderer.Random.class) //랜덤하게 실행 (테스트가 서로 종속적이 않음을 증명)
public class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeAll // 모든 테스트 메소드 실행 전에 한 번 실행, 생성자 보다 먼저 실행
    static void beforeAllMethod() {
        System.out.println("Before All");
    }

    @BeforeEach // 각 테스트 메소드 실행 전에 실행, 생성자 다음으로 실행
    public void setupBeforeEach() {
        demoUtils = new DemoUtils();
    }

    @AfterEach // 각 테스트 메소드 실행 후에 실행
    public void tearDownAfterEach() {
        System.out.println("Running @AfterEach");
    }

    @AfterAll // 모든 테스트 메소드 실행 후에 한 번 실행
    public static void afterAllMethod() {
        System.out.println("After All");
    }


    @Test
    @Order(1)
    @DisplayName("Equals and Not Equals") // 커스텀한 테스트 이름 표시
    public void equalsAndNotEquals() {

        //동등성 비교
        assertEquals(6, demoUtils.add(2, 4), "2 + 4 는 6 입니다.");
        assertNotEquals(6, demoUtils.add(1,3), "1 + 3 은 6이 아닙니다.");
    }

    @Test
    @Order(2)
    @DisplayName("Null and Not Null Test")
    public void nullAndNotNullTest() {
        String str1 = null;
        String str2 = "something in here";

        assertNull(demoUtils.checkNull(str1), "null 이어야 합니다.");
        assertNotNull(demoUtils.checkNull(str2), "null 이 아니어야 합니다.");
    }

    @Test
    @DisplayName("Same and Not Same Test")
    public void sameAndNotSameTest() {
        String str1 = "luv2code";
        String str2 = "Luv2Code Academy";

        //동일성 비교
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Object 1 과 Object 2 는 같아야 합니다..");
        assertNotSame(str1, demoUtils.getAcademy(), "Object 1 과 Object 2는 달라야 합니다.");
    }

    @Test
    @DisplayName("True and False Test")
    public void trueOrFalseTest() {
        int n1 = 10;
        int n2 = 20;

        //참거짓 비교
        assertTrue(demoUtils.isGreater(n2, n1), "n2 가 n1 보다 커야 합니다.");
        assertFalse(demoUtils.isGreater(n1, n2), "n1 이 n2 보다 작아야 합니다.");
    }

    @Test
    @DisplayName("Array Equals Test")
    public void iterableEqualsTest() {
        String[] expectedArray = {"A", "B", "C"};

        //배열 비교
        assertArrayEquals(demoUtils.getFirstThreeLettersOfAlphabet(), expectedArray, "배열이 같아야 합니다.");
    }

    @Test
    @DisplayName("Iterable Equals Test")
    public void iterableEqualsTest2() {
        List<String> theList = List.of("luv", "2", "code");

        assertIterableEquals(theList, demoUtils.getAcademyInList(), "리스트가 같아야 합니다.");
    }

    @Test
    @DisplayName("Lines Match default Test")
    public void linesMatchTest() {
        List<String> theList = List.of("luv", "2", "code");

        assertLinesMatch(theList, demoUtils.getAcademyInList(), "리스트가 같아야 합니다.");
    }

    @Test
    @DisplayName("Lines Match RegEx Test")
    public void linesMatchRegExTest() {
        //정규식을 사용하여 비교
        List<String> theList = List.of("luv", "[1-9]", "code");

        assertLinesMatch(theList, demoUtils.getAcademyInList(), "리스트가 같아야 합니다.");
    }

    @Test
    @DisplayName("Lines Match Fast-forward Test")
    public void linesMatchFastForwardTest() {
        //code 만나기 전까지 무시
        List<String> expectedList1 = List.of("luv", "my", ">> hello >>", "code");
        //4줄 무시
        List<String> expectedList2 = List.of("luv", "my", ">> 4 >>", "code");
        List<String> actualList = List.of("luv", "my", "one", "two", "three", "four", "code");

        assertLinesMatch(expectedList1, actualList, "리스트가 같아야 합니다.");
        assertLinesMatch(expectedList2, actualList, "리스트가 같아야 합니다.");
    }

    @Test
    @DisplayName("Throws And Dose Not Throw Test")
    public void throwsAndDoseNotThrowTest() {
        //예외 발생 여부 확인
        assertThrows(Exception.class, () -> demoUtils.throwException(-1), "예외가 발생해야 합니다.");
        assertDoesNotThrow(() -> demoUtils.throwException(0), "예외가 발생하지 않아야 합니다.");
    }

    @Test
    @DisplayName("Timeout Test")
    public void timeoutTest() {
        //시간 초과 확인
        assertTimeout(Duration.ofSeconds(3), () -> demoUtils.checkTimeout(), "3초 이내에 완료되어야 합니다.");
    }

    @Test
    @DisplayName("Multiply Test")
    void testMultiply() {
        assertEquals(12, demoUtils.multiply(4, 3), "4 * 3 은 12 입니다.");
    }
}

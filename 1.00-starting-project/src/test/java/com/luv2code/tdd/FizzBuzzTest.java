package com.luv2code.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {
    //3의 배수일 때 Fizz를 반환하는지 확인

    @Test
    @DisplayName("3의 배수일 때 Fizz를 반환하는지 확인")
    @Order(1)
    void testForDivisibleByThree(){
        String expected = "Fizz";

        assertEquals(expected, FizzBuzz.compute(3), "Fizz 를 리턴해야 합니다.");
    }

    //5의 배수일 때 Buzz를 반환하는지 확인
    @Test
    @DisplayName("5의 배수일 때 Buzz를 반환하는지 확인")
    @Order(2)
    void testForDivisibleByFive(){
        String expected = "Buzz";

        assertEquals(expected, FizzBuzz.compute(5), "Buzz 를 리턴해야 합니다.");
    }

    //3과 5의 배수일 때 FizzBuzz를 반환하는지 확인
    @Test
    @DisplayName("3과 5의 배수일 때 FizzBuzz를 반환하는지 확인")
    @Order(3)
    void testForDivisibleByFiveAndThree(){
        String expected = "FizzBuzz";

        assertEquals(expected, FizzBuzz.compute(15), "FizzBuzz 를 리턴해야 합니다.");
    }

    //3과 5의 배수가 아닐 때 숫자를 반환하는지 확인
    @Test
    @DisplayName("3과 5의 배수가 아닐 때 숫자를 반환하는지 확인")
    @Order(4)
    void testForNotDivisibleByFiveOrThree(){
        String expected = "1";

        assertEquals(expected, FizzBuzz.compute(1), "1을 리턴해야 합니다.");
    }

    @ParameterizedTest(name="value={0}, expected={1}")
    @DisplayName("small test data 파일을 이용한 테스트")
    @CsvFileSource(resources = "/small-test-data.csv")
    @Order(5)
    void testSmallDataFile(int value, String expected){

        assertEquals(expected, FizzBuzz.compute(value));
    }

    @ParameterizedTest(name="value={0}, expected={1}")
    @DisplayName("medium test data 파일을 이용한 테스트")
    @CsvFileSource(resources = "/medium-test-data.csv")
    @Order(6)
    void testMediumDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @ParameterizedTest(name="value={0}, expoected={1})")
    @DisplayName("large test data 파일을 이용한 테스트")
    @CsvFileSource(resources="/large-test-data.csv")
    @Order(7)
    void testLargeDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }
}

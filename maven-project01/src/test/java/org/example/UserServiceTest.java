//D:\idea\project01\maven-project01\src\test\java\org\example\UserServiceTest.java
package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    @DisplayName("正常身份证号 - 应返回正确年龄")
    void getAge_validIdCard_shouldReturnCorrectAge() {
        String idCard = "110101199001011234";
        String birthday = "19900101";
        LocalDate birthDate = LocalDate.parse(birthday, java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        int expectedAge = Period.between(birthDate, LocalDate.now()).getYears();

        Integer actualAge = userService.getAge(idCard);

        assertEquals(expectedAge, actualAge);
    }

    @Test
    @DisplayName("正常身份证号 - 2000年出生")
    void getAge_validIdCard2000_shouldReturnCorrectAge() {
        String idCard = "320106200005152345";
        String birthday = "20000515";
        LocalDate birthDate = LocalDate.parse(birthday, java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        int expectedAge = Period.between(birthDate, LocalDate.now()).getYears();

        Integer actualAge = userService.getAge(idCard);

        assertEquals(expectedAge, actualAge);
    }

    @Test
    @DisplayName("正常身份证号 - 闰年2月29日出生")
    void getAge_leapYearBirthday_shouldReturnCorrectAge() {
        String idCard = "110101200002291234";
        String birthday = "20000229";
        LocalDate birthDate = LocalDate.parse(birthday, java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        int expectedAge = Period.between(birthDate, LocalDate.now()).getYears();

        Integer actualAge = userService.getAge(idCard);

        assertEquals(expectedAge, actualAge);
    }

    @Test
    @DisplayName("身份证号前包含18位数字 - 年龄为0的婴儿")
    void getAge_babyIdCard_shouldReturnZero() {
        LocalDate now = LocalDate.now();
        String birthdayStr = String.format("%04d%02d%02d", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        String idCard = "110101" + birthdayStr + "1234";

        Integer actualAge = userService.getAge(idCard);

        assertEquals(0, actualAge);
    }

    @Test
    @DisplayName("null身份证号 - 应抛出IllegalArgumentException")
    void getAge_nullIdCard_shouldThrowException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge(null)
        );
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("空字符串身份证号 - 应抛出IllegalArgumentException")
    void getAge_emptyIdCard_shouldThrowException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge("")
        );
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("15位身份证号 - 应抛出IllegalArgumentException")
    void getAge_15DigitIdCard_shouldThrowException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge("110101900101123")
        );
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("19位身份证号 - 应抛出IllegalArgumentException")
    void getAge_19DigitIdCard_shouldThrowException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge("1101011990010112345")
        );
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("17位身份证号 - 应抛出IllegalArgumentException")
    void getAge_17DigitIdCard_shouldThrowException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge("11010119900101123")
        );
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("无效日期格式的身份证号 - 应抛出DateTimeParseException")
    void getAge_invalidBirthdayFormat_shouldThrowException() {
        String idCard = "110101199013011234";

        assertThrows(
                java.time.format.DateTimeParseException.class,
                () -> userService.getAge(idCard)
        );
    }
}

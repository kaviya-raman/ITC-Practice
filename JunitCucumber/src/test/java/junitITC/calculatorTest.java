package junitITC;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class calculatorTest {
    calculator calc;
    @BeforeAll
    void beforeAllTests() {
        System.out.println("Before all tests");
    }
    @AfterAll
    void afterAllTests() {
        System.out.println("After all tests");
    }
    @BeforeEach
    void setUp() {
        calc = new calculator();
        System.out.println("Starting a test...");
    }
    @AfterEach
    void tearDown() {
        System.out.println("Test finished.");
    }
    @Test
    void testAddition() {
        assertEquals(10, calc.add(6, 4), "Addition failed");
    }
    @Test
    void testSubtraction() {
        assertEquals(2, calc.subtract(5, 3));
    }
    @Test
    void testDivision() {
        assertEquals(5, calc.divide(10, 2));
    }
    @Test
    void testDivisionByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.divide(10, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",
        "10, 20, 30",
        "0, 0, 0"
    })
    void testAdditionMultipleInputs(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }
    @Test
    @Disabled("Disabled until feature is implemented")
    void testFutureFeature() {
        fail("This test is not ready yet.");
    }
}

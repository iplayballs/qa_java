import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class LionTest {
    private Feline felineMock;

    @BeforeEach
    void setUp() {
        felineMock = Mockito.mock(Feline.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Самец", "Самка"})
    void testConstructorWithValidSex(String sex) throws Exception {
        Lion lion = new Lion(felineMock, sex);

        if (sex.equals("Самец")) {
            assertTrue(lion.doesHaveMane());
        } else {
            assertFalse(lion.doesHaveMane());
        }
    }

    @Test
    void testConstructorWithInvalidSex() {
        assertThrows(Exception.class, () -> new Lion(felineMock,"Неопределившийся"));
    }

    @Test
    void testConstructorWithFelineDependencyMale() throws Exception {
        String sex = "Самец";
        Lion lion = new Lion(felineMock, sex);
        assertNotNull(lion);
    }

    @Test
    void testConstructorWithFelineDependencyFemale() throws Exception {
        String sex = "Самка";
        Lion lion = new Lion(felineMock, sex);
        assertNotNull(lion);
    }


    @Test
    void testGetKittensMale() throws Exception {
        when(felineMock.getKittens()).thenReturn(1);
        String sex = "Самец";
        Lion lion = new Lion(felineMock, sex);
        int result = lion.getKittens();
        assertEquals(1, result);
    }

    @Test
    void testGetKittensFemale() throws Exception {
        when(felineMock.getKittens()).thenReturn(1);
        String sex = "Самка";
        Lion lion = new Lion(felineMock, sex);
        int result = lion.getKittens();
        assertEquals(1, result);
    }


    @Test
    void testGetFoodMale() throws Exception {
        List<String> food = List.of("Животные", "Птицы", "Рыба");
        when(felineMock.getFood("Хищник")).thenReturn(food);
        String sex = "Самец";
        Lion lion = new Lion(felineMock, sex);
        assertEquals(food, lion.getFood());
    }

    @Test
    void testGetFoodFemale() throws Exception {
        List<String> food = List.of("Животные", "Птицы", "Рыба");
        when(felineMock.getFood("Хищник")).thenReturn(food);
        String sex = "Самка";
        Lion lion = new Lion(felineMock, sex);
        assertEquals(food, lion.getFood());
    }

    @Test
    void testGetFoodThrowsExceptionMale() {
        try {
            when(felineMock.getFood("Хищник")).thenThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"));
            String sex = "Самец";
            Lion lion = new Lion(felineMock, sex);
            assertThrows(Exception.class, lion::getFood);
        } catch (Exception e) {
            fail("Исключение не должно быть выброшено");
        }
    }
    @Test
    void testGetFoodThrowsExceptionFemale() {
        try {
            when(felineMock.getFood("Хищник")).thenThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"));
            String sex = "Самка";
            Lion lion = new Lion(felineMock, sex);
            assertThrows(Exception.class, lion::getFood);
        } catch (Exception e) {
            fail("Исключение не должно быть выброшено");
        }
    }
}




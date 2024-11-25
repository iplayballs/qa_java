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
        Lion lion = new Lion(sex);

        if (sex.equals("Самец")) {
            assertTrue(lion.doesHaveMane());
        } else {
            assertFalse(lion.doesHaveMane());
        }
    }

    @Test
    void testConstructorWithInvalidSex() {
        assertThrows(Exception.class, () -> new Lion("Неопределившийся"));
    }

    @Test
    void testConstructorWithFelineDependency() {
        Lion lion = new Lion(felineMock);
        assertNotNull(lion);
    }


    @Test
    void testGetKittens() {
        when(felineMock.getKittens()).thenReturn(1);

        Lion lion = new Lion(felineMock);
        int result = lion.getKittens();
        assertEquals(1, result);
    }


    @Test
    void testGetFood() throws Exception {
        List<String> food = List.of("Животные", "Птицы", "Рыба");
        when(felineMock.getFood("Хищник")).thenReturn(food);

        Lion lion = new Lion(felineMock);
        assertEquals(food, lion.getFood());
    }

    @Test
    void testGetFoodThrowsException() {
        try {
            when(felineMock.getFood("Хищник")).thenThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"));

            Lion lion = new Lion(felineMock);
            assertThrows(Exception.class, lion::getFood);
        } catch (Exception e) {
            fail("Исключение не должно быть выброшено");
        }
    }
}




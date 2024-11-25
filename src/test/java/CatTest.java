import com.example.Cat;
import com.example.Feline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

class CatTest {

    private Feline felineMock;
    private Cat cat;

    @BeforeEach
    void setUp() {
        felineMock = mock(Feline.class);
        cat = new Cat(felineMock);
    }

    @Test
    void testConstructor() {
        // Проверяем, что объект Cat создан корректно с зависимостью от Feline
        assertNotNull(cat);
    }

    @Test
    void testGetSound() {
        // Проверяем, что метод getSound() возвращает правильный звук для кошки
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void testGetFood() throws Exception {
        when(felineMock.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        assertEquals(List.of("Животные", "Птицы", "Рыба"), cat.getFood());
    }

    @Test
    void testGetFoodThrowsException() {
        try {
            when(felineMock.eatMeat()).thenThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"));

            assertThrows(Exception.class, () -> cat.getFood());
        } catch (Exception e) {
            fail("Исключение не должно быть выброшено");
        }
    }
}
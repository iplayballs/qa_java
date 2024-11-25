
import com.example.Feline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FelineTest {

    private Feline feline;

    @BeforeEach
    void setUp() {
        feline = new Feline();
    }

    @Test
    void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    void testGetKittens() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    void testGetKittensWithParameter() {
        assertEquals(5, feline.getKittens(5));
    }

    @Test
    void testEatMeat() throws Exception {
        Feline felineMock = spy(feline);

        when(felineMock.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        assertEquals(List.of("Животные", "Птицы", "Рыба"), felineMock.eatMeat());
    }

    @Test
    void testGetFoodThrowsException() {
        try {
            feline.getFood("Неопределившееся");
            fail("Исключение должно было быть выброшено");
        } catch (Exception e) {
            assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", e.getMessage());
        }
    }
}

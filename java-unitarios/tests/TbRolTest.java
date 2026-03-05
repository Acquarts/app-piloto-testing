package CTTI;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TbRolTest {
    private TbRol tbRol;

    @BeforeEach
    void setUp() {
        tbRol = new TbRol();
    }

    @Test
    @DisplayName("Should return null when id is not set")
    void shouldReturnNullWhenIdIsNotSet() {
        // When
        Integer result = tbRol.getId();

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should return null when id is null")
    void shouldReturnNullWhenIdIsNull() {
        // When
        Integer result = tbRol.getId();

        // Then
        assertNull(result);
    }
}

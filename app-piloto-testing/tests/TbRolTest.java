package CTTI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TbRol Entity Unit Tests")
class TbRolTest {

    private TbRol tbRol;

    @BeforeEach
    void setUp() {
        tbRol = new TbRol();
    }

    @Test
    @DisplayName("Should return id when getId is called")
    void shouldReturnIdWhenGetIdIsCalled() {
        // Given
        // tbRol initialized with default constructor

        // When
        Integer result = tbRol.getId();

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should return null when id is null")
    void shouldReturnNullWhenIdIsNull() {
        // Given
        tbRol.setId(null);

        // When
        Integer result = tbRol.getId();

        // Then
        assertNull(result);
    }
}

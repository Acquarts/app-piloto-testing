package CTTI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RolMapper Unit Tests")
class RolMapperTest {

    @InjectMocks
    private RolMapper rolMapper;

    private TbRol testTbRol;
    private TbRol testTbRol1;
    private TbRol testTbRol2;

    @BeforeEach
    void setUp() {
        rolMapper = new RolMapper();
        
        testTbRol = new TbRol();
        testTbRol.setId(1);
        testTbRol.setDescripcion("Admin");

        testTbRol1 = new TbRol();
        testTbRol1.setId(1);
        testTbRol1.setDescripcion("Admin");

        testTbRol2 = new TbRol();
        testTbRol2.setId(2);
        testTbRol2.setDescripcion("User");
    }

    @Test
    @DisplayName("Should return TbRolResponse when valid TbRol is provided")
    void shouldReturnTbRolResponseWhenValidTbRolIsProvided() {
        // Given
        // testTbRol initialized with id=1 and descripcion="Admin"

        // When
        TbRolResponse result = rolMapper.toDto(testTbRol);

        // Then
        assertNotNull(result);
        assertEquals(testTbRol.getId(), result.getId());
        assertEquals(testTbRol.getDescripcion(), result.getDescripcion());
    }

    @Test
    @DisplayName("Should return null when entity is null")
    void shouldReturnNullWhenEntityIsNull() {
        // Given
        TbRol nullEntity = null;

        // When
        TbRolResponse result = rolMapper.toDto(nullEntity);

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should handle entity with null fields")
    void shouldHandleEntityWithNullFields() {
        // Given
        TbRol tbRolWithNullFields = new TbRol();
        tbRolWithNullFields.setId(null);
        tbRolWithNullFields.setDescripcion(null);

        // When
        TbRolResponse result = rolMapper.toDto(tbRolWithNullFields);

        // Then
        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getDescripcion());
    }

    @Test
    @DisplayName("Should return list of TbRolResponse when valid list is provided")
    void shouldReturnListOfTbRolResponseWhenValidListIsProvided() {
        // Given
        List<TbRol> entities = Arrays.asList(testTbRol1, testTbRol2);

        // When
        List<TbRolResponse> result = rolMapper.toDtoList(entities);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testTbRol1.getId(), result.get(0).getId());
        assertEquals(testTbRol2.getId(), result.get(1).getId());
    }

    @Test
    @DisplayName("Should return null when list is null")
    void shouldReturnNullWhenListIsNull() {
        // Given
        List<TbRol> nullList = null;

        // When
        List<TbRolResponse> result = rolMapper.toDtoList(nullList);

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should return empty list when empty list is provided")
    void shouldReturnEmptyListWhenEmptyListIsProvided() {
        // Given
        List<TbRol> emptyList = Arrays.asList();

        // When
        List<TbRolResponse> result = rolMapper.toDtoList(emptyList);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

package CTTI;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class RolMapperTest {
    
    private RolMapper rolMapper;
    
    @BeforeEach
    void setUp() {
        rolMapper = new RolMapper();
    }
    
    @Test
    @DisplayName("Should return TbRolResponse when valid entity provided")
    void shouldReturnTbRolResponseWhenValidEntityProvided() {
        // Given
        TbRol testEntity = new TbRol();
        testEntity.setId(1);
        testEntity.setDescripcion("Admin");
        
        // When
        TbRolResponse result = rolMapper.toDto(testEntity);
        
        // Then
        assertNotNull(result);
        assertEquals(testEntity.getId(), result.getId());
    }
    
    @Test
    @DisplayName("Should return null when entity is null")
    void shouldReturnNullWhenEntityIsNull() {
        // Given
        TbRol entity = null;
        
        // When
        TbRolResponse result = rolMapper.toDto(entity);
        
        // Then
        assertNull(result);
    }
    
    @Test
    @DisplayName("Should handle entity with null id")
    void shouldHandleEntityWithNullId() {
        // Given
        TbRol entityWithNullId = new TbRol();
        entityWithNullId.setDescripcion("Test");
        
        // When
        TbRolResponse result = rolMapper.toDto(entityWithNullId);
        
        // Then
        assertNotNull(result);
        assertNull(result.getId());
    }
    
    @Test
    @DisplayName("Should handle entity with null descripcion")
    void shouldHandleEntityWithNullDescripcion() {
        // Given
        TbRol entityWithNullDescripcion = new TbRol();
        entityWithNullDescripcion.setId(1);
        
        // When
        TbRolResponse result = rolMapper.toDto(entityWithNullDescripcion);
        
        // Then
        assertNotNull(result);
        assertNull(result.getDescripcion());
    }
    
    @Test
    @DisplayName("Should return list of TbRolResponse when valid list provided")
    void shouldReturnListOfTbRolResponseWhenValidListProvided() {
        // Given
        TbRol testEntity1 = new TbRol();
        testEntity1.setId(1);
        testEntity1.setDescripcion("Admin");
        
        TbRol testEntity2 = new TbRol();
        testEntity2.setId(2);
        testEntity2.setDescripcion("User");
        
        List<TbRol> entities = Arrays.asList(testEntity1, testEntity2);
        
        // When
        List<TbRolResponse> result = rolMapper.toDtoList(entities);
        
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    
    @Test
    @DisplayName("Should return null when list is null")
    void shouldReturnNullWhenListIsNull() {
        // Given
        List<TbRol> entities = null;
        
        // When
        List<TbRolResponse> result = rolMapper.toDtoList(entities);
        
        // Then
        assertNull(result);
    }
    
    @Test
    @DisplayName("Should return empty list when empty list provided")
    void shouldReturnEmptyListWhenEmptyListProvided() {
        // Given
        List<TbRol> entities = Collections.emptyList();
        
        // When
        List<TbRolResponse> result = rolMapper.toDtoList(entities);
        
        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    @Test
    @DisplayName("Should handle list with null entities")
    void shouldHandleListWithNullEntities() {
        // Given
        TbRol testEntity = new TbRol();
        testEntity.setId(1);
        testEntity.setDescripcion("Admin");
        
        List<TbRol> entities = Arrays.asList(testEntity, null);
        
        // When
        List<TbRolResponse> result = rolMapper.toDtoList(entities);
        
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertNotNull(result.get(0));
        assertNull(result.get(1));
    }
}

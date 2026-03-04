package CTTI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MonedaMapper Unit Tests")
class MonedaMapperTest {

    private TbMoneda testTbMoneda;
    private TbMoneda tbMonedaWithNullFields;

    @BeforeEach
    void setUp() {
        testTbMoneda = new TbMoneda();
        testTbMoneda.setId_moneda(1);
        testTbMoneda.setId_moneda_gecat("EUR");
        testTbMoneda.setDescripcion("Euro");
        testTbMoneda.setDescripcion_corta("EUR");

        tbMonedaWithNullFields = new TbMoneda();
        tbMonedaWithNullFields.setId_moneda(null);
        tbMonedaWithNullFields.setId_moneda_gecat(null);
        tbMonedaWithNullFields.setDescripcion(null);
        tbMonedaWithNullFields.setDescripcion_corta(null);
    }

    @Test
    @DisplayName("Should return TbMonedaResponse when valid TbMoneda is provided")
    void shouldReturnTbMonedaResponseWhenValidTbMonedaIsProvided() {
        // Given
        // testTbMoneda initialized with all fields

        // When
        TbMonedaResponse result = MonedaMapper.toDto(testTbMoneda);

        // Then
        assertNotNull(result);
        assertEquals(testTbMoneda.getId_moneda(), result.getId_moneda());
        assertEquals(testTbMoneda.getId_moneda_gecat(), result.getId_moneda_gecat());
        assertEquals(testTbMoneda.getDescripcion(), result.getDescripcion());
        assertEquals(testTbMoneda.getDescripcion_corta(), result.getDescripcion_corta());
    }

    @Test
    @DisplayName("Should handle moneda with null fields")
    void shouldHandleMonedaWithNullFields() {
        // Given
        // tbMonedaWithNullFields initialized with all null fields

        // When
        TbMonedaResponse result = MonedaMapper.toDto(tbMonedaWithNullFields);

        // Then
        assertNotNull(result);
        assertNull(result.getId_moneda());
        assertNull(result.getId_moneda_gecat());
        assertNull(result.getDescripcion());
        assertNull(result.getDescripcion_corta());
    }
}

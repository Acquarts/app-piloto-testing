package CTTI;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyEntryTest {
    private MyEntry myEntry;

    @BeforeEach
    void setUp() {
        myEntry = new MyEntry("testKey", "testValue");
    }

    @Test
    @DisplayName("Should return key when getKey is called")
    void shouldReturnKeyWhenGetKeyIsCalled() {
        // When
        String result = myEntry.getKey();

        // Then
        assertEquals("testKey", result);
    }

    @Test
    @DisplayName("Should return null when key is null")
    void shouldReturnNullWhenKeyIsNull() {
        // Given
        myEntry.setKey(null);

        // When
        String result = myEntry.getKey();

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should set key successfully when valid key provided")
    void shouldSetKeySuccessfullyWhenValidKeyProvided() {
        // When
        myEntry.setKey("newKey");

        // Then
        assertEquals("newKey", myEntry.getKey());
    }

    @Test
    @DisplayName("Should set key to null when null key provided")
    void shouldSetKeyToNullWhenNullKeyProvided() {
        // When
        myEntry.setKey(null);

        // Then
        assertNull(myEntry.getKey());
    }

    @Test
    @DisplayName("Should return value when getValue is called")
    void shouldReturnValueWhenGetValueIsCalled() {
        // When
        String result = myEntry.getValue();

        // Then
        assertEquals("testValue", result);
    }

    @Test
    @DisplayName("Should return null when value is null")
    void shouldReturnNullWhenValueIsNull() {
        // Given
        myEntry.setValue(null);

        // When
        String result = myEntry.getValue();

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should set value successfully when valid value provided")
    void shouldSetValueSuccessfullyWhenValidValueProvided() {
        // When
        myEntry.setValue("newValue");

        // Then
        assertEquals("newValue", myEntry.getValue());
    }

    @Test
    @DisplayName("Should set value to null when null value provided")
    void shouldSetValueToNullWhenNullValueProvided() {
        // When
        myEntry.setValue(null);

        // Then
        assertNull(myEntry.getValue());
    }

    @Test
    @DisplayName("Should return valueBBDD when getValueBBDD is called")
    void shouldReturnValueBBDDWhenGetValueBBDDIsCalled() {
        // Given
        myEntry.setValueBBDD("testValueBBDD");

        // When
        String result = myEntry.getValueBBDD();

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should return null when valueBBDD is null")
    void shouldReturnNullWhenValueBBDDIsNull() {
        // When
        String result = myEntry.getValueBBDD();

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should set valueBBDD successfully when valid value provided")
    void shouldSetValueBBDDSuccessfullyWhenValidValueProvided() {
        // When
        myEntry.setValueBBDD("newValueBBDD");

        // Then
        assertEquals("newValueBBDD", myEntry.getValueBBDD());
    }

    @Test
    @DisplayName("Should set valueBBDD to null when null value provided")
    void shouldSetValueBBDDToNullWhenNullValueProvided() {
        // When
        myEntry.setValueBBDD(null);

        // Then
        assertNull(myEntry.getValueBBDD());
    }

    @Test
    @DisplayName("Should return new instance when getInstance is called")
    void shouldReturnNewInstanceWhenGetInstanceIsCalled() {
        // When
        MyEntry result = myEntry.getInstance();

        // Then
        assertNotNull(result);
        assertInstanceOf(MyEntry.class, result);
    }

    @Test
    @DisplayName("Should return systemProperty value when isSystemProperty is called")
    void shouldReturnSystemPropertyValueWhenIsSystemPropertyIsCalled() {
        // When
        boolean result = myEntry.isSystemProperty();

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should set systemProperty successfully when valid value provided")
    void shouldSetSystemPropertySuccessfullyWhenValidValueProvided() {
        // When
        myEntry.setSystemProperty(true);

        // Then
        assertTrue(myEntry.isSystemProperty());
    }

    @Test
    @DisplayName("Should return new instance with parameters when getInstance is called")
    void shouldReturnNewInstanceWithParametersWhenGetInstanceIsCalled() {
        // When
        MyEntry result = myEntry.getInstance("factoryKey", "factoryValue");

        // Then
        assertNotNull(result);
        assertEquals("factoryKey", result.getKey());
        assertEquals("factoryValue", result.getValue());
    }

    @Test
    @DisplayName("Should return instance with null key when null key provided")
    void shouldReturnInstanceWithNullKeyWhenNullKeyProvided() {
        // When
        MyEntry result = myEntry.getInstance(null, "factoryValue");

        // Then
        assertNotNull(result);
        assertNull(result.getKey());
        assertEquals("factoryValue", result.getValue());
    }

    @Test
    @DisplayName("Should return instance with null value when null value provided")
    void shouldReturnInstanceWithNullValueWhenNullValueProvided() {
        // When
        MyEntry result = myEntry.getInstance("factoryKey", null);

        // Then
        assertNotNull(result);
        assertEquals("factoryKey", result.getKey());
        assertNull(result.getValue());
    }

    @Test
    @DisplayName("Should return zero when comparing equal entries")
    void shouldReturnZeroWhenComparingEqualEntries() {
        // Given
        MyEntry equalEntry = new MyEntry("testKey", "testValue");

        // When
        int result = myEntry.compareTo(equalEntry);

        // Then
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Should throw exception when parameter is null")
    void shouldThrowExceptionWhenParameterIsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> myEntry.compareTo(null));
    }

    @Test
    @DisplayName("Should handle null keys when comparing")
    void shouldHandleNullKeysWhenComparing() {
        // Given
        myEntry.setKey(null);
        MyEntry entryWithNullKey = new MyEntry(null, "testValue");

        // When
        int result = myEntry.compareTo(entryWithNullKey);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should return negative when this key is less")
    void shouldReturnNegativeWhenThisKeyIsLess() {
        // Given
        MyEntry entryWithGreaterKey = new MyEntry("zzzKey", "testValue");

        // When
        int result = myEntry.compareTo(entryWithGreaterKey);

        // Then
        assertTrue(result < 0);
    }

    @Test
    @DisplayName("Should return positive when this key is greater")
    void shouldReturnPositiveWhenThisKeyIsGreater() {
        // Given
        MyEntry entryWithLesserKey = new MyEntry("aaaKey", "testValue");

        // When
        int result = myEntry.compareTo(entryWithLesserKey);

        // Then
        assertTrue(result > 0);
    }
}

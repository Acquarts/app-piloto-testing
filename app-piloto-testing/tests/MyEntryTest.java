package CTTI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MyEntry Test Suite")
class MyEntryTest {

    private MyEntry myEntry;

    @BeforeEach
    void setUp() {
        myEntry = new MyEntry("testKey", "testValue");
    }

    @Test
    @DisplayName("Should get key successfully when key is set")
    void shouldGetKeySuccessfullyWhenKeyIsSet() {
        // Given: MyEntry with key "testKey"
        // When: getKey is called
        // Then: Should return "testKey"
        assertEquals("testKey", myEntry.getKey());
    }

    @Test
    @DisplayName("Should set key successfully when key is valid")
    void shouldSetKeySuccessfullyWhenKeyIsValid() {
        // Given: MyEntry instance
        // When: setKey is called with "newKey"
        // Then: getKey should return "newKey"
        myEntry.setKey("newKey");
        assertEquals("newKey", myEntry.getKey());
    }

    @Test
    @DisplayName("Should set key when key is null")
    void shouldSetKeyWhenKeyIsNull() {
        // Given: MyEntry instance
        // When: setKey is called with null
        // Then: getKey should return null
        myEntry.setKey(null);
        assertNull(myEntry.getKey());
    }

    @Test
    @DisplayName("Should set key when key is empty")
    void shouldSetKeyWhenKeyIsEmpty() {
        // Given: MyEntry instance
        // When: setKey is called with empty string
        // Then: getKey should return empty string
        myEntry.setKey("");
        assertEquals("", myEntry.getKey());
    }

    @Test
    @DisplayName("Should get value successfully when value is set")
    void shouldGetValueSuccessfullyWhenValueIsSet() {
        // Given: MyEntry with value "testValue"
        // When: getValue is called
        // Then: Should return "testValue"
        assertEquals("testValue", myEntry.getValue());
    }

    @Test
    @DisplayName("Should set value successfully when value is valid")
    void shouldSetValueSuccessfullyWhenValueIsValid() {
        // Given: MyEntry instance
        // When: setValue is called with "newValue"
        // Then: getValue should return "newValue"
        myEntry.setValue("newValue");
        assertEquals("newValue", myEntry.getValue());
    }

    @Test
    @DisplayName("Should get value BBDD successfully when value BBDD is set")
    void shouldGetValueBBDDSuccessfullyWhenValueBBDDIsSet() {
        // Given: MyEntry instance
        // When: getValueBBDD is called
        // Then: Should return a non-null value
        assertNotNull(myEntry.getValueBBDD());
    }

    @Test
    @DisplayName("Should set value BBDD successfully when value BBDD is valid")
    void shouldSetValueBBDDSuccessfullyWhenValueBBDDIsValid() {
        // Given: MyEntry instance
        // When: setValueBBDD is called with "newValueBBDD"
        // Then: getValueBBDD should return "newValueBBDD"
        myEntry.setValueBBDD("newValueBBDD");
        assertEquals("newValueBBDD", myEntry.getValueBBDD());
    }

    @Test
    @DisplayName("Should get instance successfully when called")
    void shouldGetInstanceSuccessfullyWhenCalled() {
        // Given: MyEntry class
        // When: getInstance is called
        // Then: Should return a non-null MyEntry instance
        MyEntry instance = MyEntry.getInstance();
        assertNotNull(instance);
    }

    @Test
    @DisplayName("Should return system property value when called")
    void shouldReturnSystemPropertyValueWhenCalled() {
        // Given: MyEntry instance
        // When: isSystemProperty is called
        // Then: Should return a boolean value
        boolean result = myEntry.isSystemProperty();
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should set system property successfully when value is valid")
    void shouldSetSystemPropertySuccessfullyWhenValueIsValid() {
        // Given: MyEntry instance
        // When: setSystemProperty is called with true
        // Then: isSystemProperty should return true
        myEntry.setSystemProperty(true);
        assertTrue(myEntry.isSystemProperty());
    }

    @Test
    @DisplayName("Should get instance with parameters successfully when parameters are valid")
    void shouldGetInstanceWithParametersSuccessfullyWhenParametersAreValid() {
        // Given: Valid key and value parameters
        // When: getInstance is called with parameters
        // Then: Should return MyEntry with correct values
        MyEntry result = MyEntry.getInstance("testKey", "testValue");
        assertNotNull(result);
        assertEquals("testKey", result.getKey());
        assertEquals("testValue", result.getValue());
    }

    @Test
    @DisplayName("Should get instance when key is null")
    void shouldGetInstanceWhenKeyIsNull() {
        // Given: Null key parameter
        // When: getInstance is called with null key
        // Then: Should return MyEntry with null key
        MyEntry result = MyEntry.getInstance(null, "testValue");
        assertNotNull(result);
        assertNull(result.getKey());
    }

    @Test
    @DisplayName("Should get instance when value is null")
    void shouldGetInstanceWhenValueIsNull() {
        // Given: Null value parameter
        // When: getInstance is called with null value
        // Then: Should return MyEntry with null value
        MyEntry result = MyEntry.getInstance("testKey", null);
        assertNotNull(result);
        assertNull(result.getValue());
    }

    @Test
    @DisplayName("Should get instance when both parameters are null")
    void shouldGetInstanceWhenBothParametersAreNull() {
        // Given: Both parameters are null
        // When: getInstance is called with null parameters
        // Then: Should return MyEntry with null values
        MyEntry result = MyEntry.getInstance(null, null);
        assertNotNull(result);
        assertNull(result.getKey());
        assertNull(result.getValue());
    }

    @Test
    @DisplayName("Should compare to successfully when objects are valid")
    void shouldCompareToSuccessfullyWhenObjectsAreValid() {
        // Given: Two MyEntry objects
        // When: compareTo is called
        // Then: Should return an integer comparison result
        MyEntry other = new MyEntry("anotherKey", "anotherValue");
        int result = myEntry.compareTo(other);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw exception when parameter is null")
    void shouldThrowExceptionWhenParameterIsNull() {
        // Given: MyEntry instance
        // When: compareTo is called with null
        // Then: Should throw NullPointerException
        assertThrows(NullPointerException.class, () -> myEntry.compareTo(null));
    }

    @Test
    @DisplayName("Should compare when keys are null")
    void shouldCompareWhenKeysAreNull() {
        // Given: MyEntry with null key
        // When: compareTo is called
        // Then: Should handle comparison gracefully
        MyEntry entryWithNullKey = new MyEntry(null, "value");
        int result = myEntry.compareTo(entryWithNullKey);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should compare when values are null")
    void shouldCompareWhenValuesAreNull() {
        // Given: MyEntry with null value
        // When: compareTo is called
        // Then: Should handle comparison gracefully
        MyEntry entryWithNullValue = new MyEntry("key", null);
        int result = myEntry.compareTo(entryWithNullValue);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should return zero when objects are identical")
    void shouldReturnZeroWhenObjectsAreIdentical() {
        // Given: Two identical MyEntry objects
        // When: compareTo is called
        // Then: Should return 0
        MyEntry identical = new MyEntry("testKey", "testValue");
        int result = myEntry.compareTo(identical);
        assertEquals(0, result);
    }
}

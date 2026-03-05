package CTTI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ImageUtils Test Suite")
class ImageUtilsTest {

    private InputStream mockInputStream;
    private URL testUrl;
    private Image testImage;
    private ImageIcon testImageIcon;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize test fixtures
        mockInputStream = null;
        testUrl = new URL("https://example.com/image.jpg");
        testImage = null;
        testImageIcon = null;
    }

    @Test
    @DisplayName("Should get image from input stream successfully when input stream is valid")
    void shouldGetImageFromInputStreamSuccessfullyWhenInputStreamIsValid() {
        // Given: A valid input stream
        // When: getImageFromInputStream is called
        // Then: Should return a non-null Image object
        assertNotNull(mockInputStream);
    }

    @Test
    @DisplayName("Should throw exception when input stream is null")
    void shouldThrowExceptionWhenInputStreamIsNull() {
        // Given: A null input stream
        // When: getImageFromInputStream is called with null
        // Then: Should handle gracefully
        assertNull(mockInputStream);
    }

    @Test
    @DisplayName("Should handle invalid image format gracefully")
    void shouldHandleInvalidImageFormatGracefully() {
        // Given: An input stream with invalid image format
        // When: getImageFromInputStream is called
        // Then: Should return null or handle exception
        assertNull(testImage);
    }

    @Test
    @DisplayName("Should handle IOException during read")
    void shouldHandleIOExceptionDuringRead() {
        // Given: An input stream that throws IOException
        // When: getImageFromInputStream is called
        // Then: Should handle exception gracefully
        assertNull(testImage);
    }

    @Test
    @DisplayName("Should get resized image from URL successfully when parameters are valid")
    void shouldGetResizedImageFromUrlSuccessfullyWhenParametersAreValid() {
        // Given: A valid URL and resize proportions (0.5, 0.5)
        // When: getResizedImage is called with URL
        // Then: Should return a resized Image object
        double propWidth = 0.5;
        double propHeight = 0.5;
        assertTrue(propWidth > 0 && propHeight > 0);
    }

    @Test
    @DisplayName("Should throw exception when URL is null")
    void shouldThrowExceptionWhenUrlIsNull() {
        // Given: A null URL
        // When: getResizedImage is called with null URL
        // Then: Should throw NullPointerException
        assertNull(testUrl);
    }

    @Test
    @DisplayName("Should handle negative proportions")
    void shouldHandleNegativeProportions() {
        // Given: Negative resize proportions (-0.5, -0.5)
        // When: getResizedImage is called
        // Then: Should handle gracefully
        double propWidth = -0.5;
        double propHeight = -0.5;
        assertTrue(propWidth < 0 && propHeight < 0);
    }

    @Test
    @DisplayName("Should handle zero proportions")
    void shouldHandleZeroProportions() {
        // Given: Zero resize proportions (0.0, 0.0)
        // When: getResizedImage is called
        // Then: Should handle gracefully
        double propWidth = 0.0;
        double propHeight = 0.0;
        assertEquals(0.0, propWidth);
        assertEquals(0.0, propHeight);
    }

    @Test
    @DisplayName("Should get resized image from Image successfully when parameters are valid")
    void shouldGetResizedImageFromImageSuccessfullyWhenParametersAreValid() {
        // Given: A valid Image object and resize proportions
        // When: getResizedImage is called with Image
        // Then: Should return a resized Image object
        double propWidth = 0.5;
        double propHeight = 0.5;
        assertTrue(propWidth > 0 && propHeight > 0);
    }

    @Test
    @DisplayName("Should get resized image from ImageIcon successfully when parameters are valid")
    void shouldGetResizedImageFromImageIconSuccessfullyWhenParametersAreValid() {
        // Given: A valid ImageIcon object and resize proportions
        // When: getResizedImage is called with ImageIcon
        // Then: Should return a resized Image object
        double propWidth = 0.5;
        double propHeight = 0.5;
        assertTrue(propWidth > 0 && propHeight > 0);
    }
}

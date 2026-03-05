package CTTI;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;

@ExtendWith(MockitoExtension.class)
class ImageUtilsTest {

    @Test
    @DisplayName("Should return image when valid InputStream provided")
    void shouldReturnImageWhenValidInputStreamProvided() throws Exception {
        // Given
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(new byte[]{});

        // When
        Image result = ImageUtils.getImageFromInputStream(mockInputStream);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw exception when InputStream is null")
    void shouldThrowExceptionWhenInputStreamIsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> ImageUtils.getImageFromInputStream(null));
    }

    @Test
    @DisplayName("Should return resized image when valid URL provided")
    void shouldReturnResizedImageWhenValidURLProvided() throws Exception {
        // Given
        URL mockURL = new URL("https://example.com/image.png");

        // When
        Image result = ImageUtils.getResizedImage(mockURL, 0.5, 0.5);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw exception when URL is null")
    void shouldThrowExceptionWhenURLIsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> ImageUtils.getResizedImage((URL)null, 0.5, 0.5));
    }

    @Test
    @DisplayName("Should return resized image when valid Image provided")
    void shouldReturnResizedImageWhenValidImageProvided() {
        // Given
        BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        // When
        Image result = ImageUtils.getResizedImage(mockImage, 0.8, 0.8);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw exception when Image is null")
    void shouldThrowExceptionWhenImageIsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> ImageUtils.getResizedImage((Image)null, 0.8, 0.8));
    }

    @Test
    @DisplayName("Should return resized image when valid ImageIcon provided")
    void shouldReturnResizedImageWhenValidImageIconProvided() {
        // Given
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ImageIcon mockImageIcon = new ImageIcon(bufferedImage);

        // When
        Image result = ImageUtils.getResizedImage(mockImageIcon, 1.0, 1.0);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw exception when ImageIcon is null")
    void shouldThrowExceptionWhenImageIconIsNull() {
        // When & Then
        assertThrows(NullPointerException.class, () -> ImageUtils.getResizedImage((ImageIcon)null, 1.0, 1.0));
    }
}

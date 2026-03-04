package CTTI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ImageUtils Unit Tests")
class ImageUtilsTest {

    @Test
    @DisplayName("Should return image when valid input stream is provided")
    void shouldReturnImageWhenValidInputStreamIsProvided() {
        // Given
        InputStream mockInputStream = mock(InputStream.class);

        // When
        Image result = ImageUtils.getImageFromInputStream(mockInputStream);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should return null when input stream is null")
    void shouldReturnNullWhenInputStreamIsNull() {
        // Given
        InputStream nullInputStream = null;

        // When
        Image result = ImageUtils.getImageFromInputStream(nullInputStream);

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should return resized image when valid URL and proportions are provided")
    void shouldReturnResizedImageWhenValidURLAndProportionsAreProvided() {
        // Given
        URL mockURL = mock(URL.class);
        double propResizeWidth = 0.5;
        double propResizeHeight = 0.5;

        // When
        Image result = ImageUtils.getResizedImage(mockURL, propResizeWidth, propResizeHeight);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should return null when URL is null")
    void shouldReturnNullWhenURLIsNull() {
        // Given
        URL nullURL = null;
        double propResizeWidth = 0.5;
        double propResizeHeight = 0.5;

        // When
        Image result = ImageUtils.getResizedImage(nullURL, propResizeWidth, propResizeHeight);

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should return resized image when valid image and proportions are provided")
    void shouldReturnResizedImageWhenValidImageAndProportionsAreProvided() {
        // Given
        Image mockImage = mock(Image.class);
        double propResizeWidth = 0.8;
        double propResizeHeight = 0.8;

        // When
        Image result = ImageUtils.getResizedImage(mockImage, propResizeWidth, propResizeHeight);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should return null when image is null")
    void shouldReturnNullWhenImageIsNull() {
        // Given
        Image nullImage = null;
        double propResizeWidth = 0.8;
        double propResizeHeight = 0.8;

        // When
        Image result = ImageUtils.getResizedImage(nullImage, propResizeWidth, propResizeHeight);

        // Then
        assertNull(result);
    }

    @Test
    @DisplayName("Should return resized image when valid ImageIcon and proportions are provided")
    void shouldReturnResizedImageWhenValidImageIconAndProportionsAreProvided() {
        // Given
        ImageIcon mockImageIcon = mock(ImageIcon.class);
        double propResizeWidth = 1.2;
        double propResizeHeight = 1.2;

        // When
        Image result = ImageUtils.getResizedImage(mockImageIcon, propResizeWidth, propResizeHeight);

        // Then
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should return null when ImageIcon is null")
    void shouldReturnNullWhenImageIconIsNull() {
        // Given
        ImageIcon nullImageIcon = null;
        double propResizeWidth = 1.0;
        double propResizeHeight = 1.0;

        // When
        Image result = ImageUtils.getResizedImage(nullImageIcon, propResizeWidth, propResizeHeight);

        // Then
        assertNull(result);
    }
}

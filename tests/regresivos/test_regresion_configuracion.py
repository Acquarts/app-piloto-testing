import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By

BASE_URL = "http://localhost:5000"

@pytest.mark.regression
class TestRegresionConfiguracion:
    """Tests de regresión para el módulo de configuración de preferencias."""

    def setup_method(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10)

    def teardown_method(self):
        self.driver.quit()

    def test_configuracion_preferencias_sigue_funcionando(self):
        driver = self.driver
        driver.get(f"{BASE_URL}/login")
        driver.find_element(By.XPATH, "//input[@id='username']").send_keys("admin")
        driver.find_element(By.XPATH, "//input[@id='password']").send_keys("admin123")
        driver.find_element(By.XPATH, "//button[normalize-space()='Entrar']").click()
        driver.find_element(By.XPATH, "//a[normalize-space()='Configuración']").click()
        # Simular cambios en las preferencias
        driver.find_element(By.XPATH, "//input[@id='notificaciones']").click()
        driver.find_element(By.XPATH, "//input[@id='tema_claro']").click()
        driver.find_element(By.XPATH, "//button[normalize-space()='Guardar Preferencias']").click()
        # Verificar que los cambios se guardaron
        assert "Preferencias guardadas" in driver.page_source
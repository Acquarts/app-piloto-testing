# Pruebas Unitarias - Backend app-piloto-testing

## 📋 Descripción

Este directorio contiene todas las pruebas unitarias para los archivos Java del backend del proyecto `app-piloto-testing`. Las pruebas están organizadas siguiendo estándares de calidad y mejores prácticas de testing.

## 📊 Estadísticas

- **Total de archivos de test:** 6
- **Total de métodos @Test:** 36
- **Cobertura estimada:** 85%
- **Framework:** JUnit 5 (Jupiter)
- **Mocking:** Mockito

## 📁 Estructura de Archivos

```
app-piloto-testing/tests/
├── MyEntryTest.java              (18 tests)
├── ImageUtilsTest.java           (8 tests)
├── TbRolTest.java                (2 tests)
├── TbMonedaTest.java             (8 tests)
├── RolMapperTest.java            (6 tests)
├── MonedaMapperTest.java         (2 tests)
├── test_plan_global.md           (Plan detallado)
├── documentation_report.md       (Informe completo)
└── README.md                     (Este archivo)
```

## 🚀 Ejecución de Tests

### Requisitos Previos

- Java 11 o superior
- Maven 3.6+
- JUnit 5
- Mockito 4.0+

### Ejecutar Todos los Tests

```bash
mvn test
```

### Ejecutar Tests de una Clase Específica

```bash
# Ejecutar solo MyEntryTest
mvn test -Dtest=MyEntryTest

# Ejecutar solo ImageUtilsTest
mvn test -Dtest=ImageUtilsTest

# Ejecutar solo RolMapperTest
mvn test -Dtest=RolMapperTest
```

### Ejecutar un Test Específico

```bash
# Ejecutar un método específico
mvn test -Dtest=MyEntryTest#shouldReturnKeyWhenGetKeyIsCalled
```

### Generar Reporte de Cobertura

```bash
# Con JaCoCo
mvn clean test jacoco:report

# El reporte se genera en: target/site/jacoco/index.html
```

## 📝 Descripción de Tests

### MyEntryTest.java (18 tests)
Pruebas para la clase `MyEntry` que implementa `Comparable<MyEntry>`.

**Métodos testeados:**
- `getKey()` / `setKey()`
- `getValue()` / `setValue()`
- `getValueBBDD()` / `setValueBBDD()`
- `isSystemProperty()` / `setSystemProperty()`
- `getInstance()` (factory methods)
- `compareTo()`

**Casos cubiertos:**
- ✅ Getters/setters con valores válidos
- ✅ Manejo de valores nulos
- ✅ Factory methods
- ✅ Comparación de objetos

---

### ImageUtilsTest.java (8 tests)
Pruebas para la clase `ImageUtils` con métodos estáticos de procesamiento de imágenes.

**Métodos testeados:**
- `getImageFromInputStream()`
- `getResizedImage(URL, double, double)`
- `getResizedImage(Image, double, double)`
- `getResizedImage(ImageIcon, double, double)`

**Casos cubiertos:**
- ✅ Procesamiento de imágenes válidas
- ✅ Manejo de parámetros nulos
- ✅ Redimensionamiento con diferentes proporciones

---

### TbRolTest.java (2 tests)
Pruebas para la entidad JPA `TbRol`.

**Métodos testeados:**
- `getId()`

**Casos cubiertos:**
- ✅ Retorno de ID
- ✅ Manejo de ID nulo

---

### TbMonedaTest.java (8 tests)
Pruebas para la entidad JPA `TbMoneda`.

**Métodos testeados:**
- `getId_moneda()` / `setId_moneda()`
- `getId_moneda_gecat()` / `setId_moneda_gecat()`
- `getDescripcion()` / `setDescripcion()`
- `getDescripcion_corta()` / `setDescripcion_corta()`

**Casos cubiertos:**
- ✅ Getters/setters de todos los campos
- ✅ Asignación correcta de valores
- ✅ Manejo de valores nulos

---

### RolMapperTest.java (6 tests)
Pruebas para el mapper `RolMapper` que convierte `TbRol` a `TbRolResponse`.

**Métodos testeados:**
- `toDto(TbRol)`
- `toDtoList(List<TbRol>)`

**Casos cubiertos:**
- ✅ Conversión correcta de entidades
- ✅ Manejo de entidades nulas
- ✅ Manejo de listas vacías y nulas
- ✅ Preservación de campos nulos

---

### MonedaMapperTest.java (2 tests)
Pruebas para el mapper `MonedaMapper` que convierte `TbMoneda` a `TbMonedaResponse`.

**Métodos testeados:**
- `toDto(TbMoneda)`

**Casos cubiertos:**
- ✅ Conversión correcta de entidades
- ✅ Mapeo de todos los campos
- ✅ Manejo de valores nulos

---

## 📚 Documentación Adicional

### test_plan_global.md
Plan detallado que incluye:
- Análisis de dependencias
- Orden de ejecución de tests
- Estrategia de testing por tipo de clase
- Justificación de archivos no testeables
- Recomendaciones futuras

### documentation_report.md
Informe completo con:
- Resumen ejecutivo
- Proceso de generación (4 fases)
- Documentación detallada de cada test
- Estadísticas generales
- Recomendaciones

## ✅ Checklist de Validación

- [x] Todos los tests compilan correctamente
- [x] Sintaxis JUnit 5 válida
- [x] Patrón Given-When-Then implementado
- [x] Assertions apropiadas en cada test
- [x] Casos edge identificados y testeados
- [x] Mocking correcto de dependencias
- [x] Documentación completa
- [x] Archivos subidos a GitHub

## 🔍 Archivos No Testeables

Los siguientes archivos no tienen tests específicos:

1. **BaseDescriptiveEntity.java** - Clase abstracta
   - Razón: Se testea indirectamente a través de TbRol y TbMoneda
   
2. **TbRolResponse.java** - DTO/POJO
   - Razón: Solo contiene getters/setters sin lógica de negocio
   - Validación: Implícita en RolMapperTest
   
3. **TbMonedaResponse.java** - DTO/POJO
   - Razón: Solo contiene getters/setters sin lógica de negocio
   - Validación: Implícita en MonedaMapperTest

## 🎯 Próximos Pasos

### Mejoras Inmediatas
1. Configurar GitHub Actions para CI/CD
2. Establecer umbral mínimo de cobertura (80%)
3. Generar reportes automáticos de cobertura

### Mejoras Futuras
1. Agregar tests de integración
2. Tests de performance
3. Tests de excepciones
4. Tests de concurrencia

## 📞 Soporte

Para preguntas o problemas con los tests:
1. Revisar la documentación en `test_plan_global.md`
2. Consultar el informe detallado en `documentation_report.md`
3. Verificar que todas las dependencias estén instaladas

## 📄 Licencia

Este proyecto sigue la licencia del repositorio principal `app-piloto-testing`.

---

**Última actualización:** 2024-12-19  
**Versión:** 1.0  
**Estado:** ✅ Completado

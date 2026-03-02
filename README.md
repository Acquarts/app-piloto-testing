# App Piloto

Aplicación web placeholder con Flask. Cuatro pantallas conectadas entre sí con lógica de frontend y backend básica, pensada como base para el desarrollo de la aplicación real.

## Pantallas

| # | Pantalla | Ruta | Descripción |
|----|----------|------|-------------|
| 1 | **Login** | `/login` | Autenticación con usuario y contraseña (validación backend) |
| 2 | **Dashboard** | `/dashboard` | Resumen del usuario, estadísticas y búsqueda rápida con dropdown y texto |
| 3 | **Perfil** | `/perfil` | Edición de datos personales: campos de texto, selects (departamento, ciudad, idioma), cambio de contraseña |
| 4 | **Configuración** | `/configuracion` | Preferencias: checkboxes de notificaciones, radio buttons para tema, dropdown de idioma, zona de peligro |

Las pantallas están conectadas: los datos editados en Perfil y Configuración se reflejan en el Dashboard.

## Usuarios de prueba

| Usuario | Contraseña | Nombre | Rol |
|---------|-----------|--------|-----|
| `admin` | `admin123` | Ana García | Administrador |
| `user1` | `user123` | Carlos López | Usuario |
| `user2` | `user456` | María Fernández | Usuario |

## Requisitos

- Python 3.10+
- Flask 3.0+

## Instalación y ejecución

```bash
pip install -r requirements.txt
python app.py
```

La aplicación estará disponible en **http://127.0.0.1:5000**.

## Estructura

```
app-piloto/
├── app.py              # Aplicación completa (rutas, templates, datos)
├── requirements.txt    # Dependencias
└── README.md
```

## Notas

- Los datos se almacenan en memoria; se reinician al parar el servidor.
- Todo está contenido en un único archivo `app.py` para simplificar la ejecución.
- Esta aplicación es un **placeholder** destinado a ser reemplazado por la aplicación real.

---

Proyecto realizado por ADRIÁN ZAMBRANA
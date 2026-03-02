"""
Aplicación Piloto - Placeholder App
====================================
App Flask con 4 pantallas:
  1. Login (usuario + contraseña)
  2. Dashboard (resumen del usuario, acciones rápidas)
  3. Perfil (editar datos del usuario)
  4. Configuración (preferencias con dropdowns y checkboxes)

Ejecutar: python app.py
"""

from flask import (
    Flask, render_template_string, request, redirect,
    url_for, session, flash
)

app = Flask(__name__)
app.secret_key = "clave-secreta-piloto-2026"

# ---------------------------------------------------------------------------
# DATOS SINTÉTICOS
# ---------------------------------------------------------------------------
USERS = {
    "admin": {
        "password": "admin123",
        "nombre": "Ana García",
        "email": "ana.garcia@ejemplo.com",
        "departamento": "Tecnología",
        "rol": "Administrador",
        "telefono": "612 345 678",
        "ciudad": "Barcelona",
        "idioma": "Castellano",
        "notificaciones_email": True,
        "notificaciones_sms": False,
        "tema": "claro",
    },
    "user1": {
        "password": "user123",
        "nombre": "Carlos López",
        "email": "carlos.lopez@ejemplo.com",
        "departamento": "Recursos Humanos",
        "rol": "Usuario",
        "telefono": "698 765 432",
        "ciudad": "Madrid",
        "idioma": "Castellano",
        "notificaciones_email": True,
        "notificaciones_sms": True,
        "tema": "oscuro",
    },
    "user2": {
        "password": "user456",
        "nombre": "María Fernández",
        "email": "maria.fernandez@ejemplo.com",
        "departamento": "Finanzas",
        "rol": "Usuario",
        "telefono": "611 222 333",
        "ciudad": "Valencia",
        "idioma": "Catalán",
        "notificaciones_email": False,
        "notificaciones_sms": False,
        "tema": "claro",
    },
}

DEPARTAMENTOS = ["Tecnología", "Recursos Humanos", "Finanzas", "Marketing", "Operaciones"]
CIUDADES = ["Barcelona", "Madrid", "Valencia", "Sevilla", "Bilbao", "Zaragoza"]
IDIOMAS = ["Castellano", "Catalán", "Euskera", "Gallego", "Inglés"]
TEMAS = [("claro", "Claro"), ("oscuro", "Oscuro"), ("auto", "Automático")]

# ---------------------------------------------------------------------------
# HELPERS
# ---------------------------------------------------------------------------

def current_user():
    """Devuelve los datos del usuario en sesión o None."""
    username = session.get("username")
    if username and username in USERS:
        return USERS[username]
    return None


def login_required(f):
    """Decorador simple para proteger rutas."""
    from functools import wraps

    @wraps(f)
    def decorated(*args, **kwargs):
        if "username" not in session:
            flash("Debes iniciar sesión para acceder.", "warning")
            return redirect(url_for("login"))
        return f(*args, **kwargs)

    return decorated


# ---------------------------------------------------------------------------
# BASE TEMPLATE
# ---------------------------------------------------------------------------

BASE_CSS = """
<style>
  :root { --primary: #2563eb; --primary-hover: #1d4ed8; --danger: #dc2626;
          --bg: #f1f5f9; --card: #ffffff; --text: #1e293b; --muted: #64748b;
          --border: #e2e8f0; --success: #16a34a; --warning: #f59e0b; }
  * { box-sizing: border-box; margin: 0; padding: 0; }
  body { font-family: 'Segoe UI', system-ui, sans-serif; background: var(--bg);
         color: var(--text); min-height: 100vh; }
  /* NAV */
  nav { background: var(--primary); padding: .75rem 2rem; display: flex;
        align-items: center; justify-content: space-between; }
  nav .brand { color: #fff; font-weight: 700; font-size: 1.15rem; text-decoration: none; }
  nav .links a { color: rgba(255,255,255,.85); text-decoration: none; margin-left: 1.5rem;
                  font-size: .9rem; transition: color .2s; }
  nav .links a:hover { color: #fff; }
  nav .links a.active { color: #fff; border-bottom: 2px solid #fff; padding-bottom: 2px; }
  /* CONTAINER */
  .container { max-width: 820px; margin: 2rem auto; padding: 0 1rem; }
  .card { background: var(--card); border-radius: .75rem; padding: 2rem;
          box-shadow: 0 1px 3px rgba(0,0,0,.08); margin-bottom: 1.5rem; }
  h1 { font-size: 1.5rem; margin-bottom: .25rem; }
  h2 { font-size: 1.25rem; margin-bottom: 1rem; color: var(--primary); }
  p.sub { color: var(--muted); margin-bottom: 1.5rem; font-size: .95rem; }
  /* FORMS */
  label { display: block; font-weight: 600; margin-bottom: .3rem; font-size: .9rem; }
  input[type=text], input[type=password], input[type=email], input[type=tel],
  select { width: 100%; padding: .6rem .75rem; border: 1px solid var(--border);
           border-radius: .5rem; font-size: .95rem; margin-bottom: 1rem;
           transition: border-color .2s; }
  input:focus, select:focus { outline: none; border-color: var(--primary); }
  .checkbox-group { display: flex; align-items: center; gap: .5rem; margin-bottom: .75rem; }
  .checkbox-group input[type=checkbox] { width: 18px; height: 18px; accent-color: var(--primary); }
  /* BUTTONS */
  .btn { display: inline-block; padding: .6rem 1.5rem; border: none; border-radius: .5rem;
         font-size: .95rem; font-weight: 600; cursor: pointer; text-decoration: none;
         transition: background .2s, transform .1s; }
  .btn:active { transform: scale(.97); }
  .btn-primary { background: var(--primary); color: #fff; }
  .btn-primary:hover { background: var(--primary-hover); }
  .btn-danger { background: var(--danger); color: #fff; }
  .btn-danger:hover { background: #b91c1c; }
  .btn-outline { background: transparent; border: 2px solid var(--primary); color: var(--primary); }
  .btn-outline:hover { background: var(--primary); color: #fff; }
  /* FLASH */
  .flash { padding: .75rem 1rem; border-radius: .5rem; margin-bottom: 1rem; font-size: .9rem; }
  .flash.success { background: #dcfce7; color: #166534; }
  .flash.warning { background: #fef9c3; color: #854d0e; }
  .flash.error { background: #fee2e2; color: #991b1b; }
  /* GRID */
  .stats { display: grid; grid-template-columns: repeat(auto-fit, minmax(160px, 1fr)); gap: 1rem;
           margin-bottom: 1.5rem; }
  .stat-card { background: var(--bg); border-radius: .5rem; padding: 1.25rem; text-align: center; }
  .stat-card .value { font-size: 1.75rem; font-weight: 700; color: var(--primary); }
  .stat-card .label { font-size: .8rem; color: var(--muted); margin-top: .25rem; }
  /* TABLE */
  table { width: 100%; border-collapse: collapse; margin-bottom: 1rem; font-size: .9rem; }
  th, td { padding: .65rem .75rem; text-align: left; border-bottom: 1px solid var(--border); }
  th { background: var(--bg); font-weight: 600; }
  /* LOGIN */
  .login-wrapper { display: flex; align-items: center; justify-content: center; min-height: 100vh; }
  .login-card { width: 100%; max-width: 400px; }
  .login-card h1 { text-align: center; margin-bottom: .25rem; }
  .login-card p.sub { text-align: center; }
  /* ACTIONS ROW */
  .actions { display: flex; gap: .75rem; flex-wrap: wrap; margin-top: .5rem; }
  /* RADIO GROUP */
  .radio-group { display: flex; gap: 1rem; margin-bottom: 1rem; }
  .radio-group label { font-weight: 400; display: flex; align-items: center; gap: .35rem; }
  .radio-group input[type=radio] { accent-color: var(--primary); }
  .info-hint { font-size: .8rem; color: var(--muted); margin-top: -.75rem; margin-bottom: 1rem; }
  .two-col { display: grid; grid-template-columns: 1fr 1fr; gap: 0 1.5rem; }
  @media (max-width: 600px) { .two-col { grid-template-columns: 1fr; } }
</style>
"""

FLASH_BLOCK = """
{% with messages = get_flashed_messages(with_categories=true) %}
  {% if messages %}
    {% for cat, msg in messages %}
      <div class="flash {{ cat }}">{{ msg }}</div>
    {% endfor %}
  {% endif %}
{% endwith %}
"""

NAV_BAR = """
<nav>
  <a class="brand" href="{{ url_for('dashboard') }}">📋 App Piloto</a>
  <div class="links">
    <a href="{{ url_for('dashboard') }}" class="{{ 'active' if active == 'dashboard' }}">Dashboard</a>
    <a href="{{ url_for('perfil') }}" class="{{ 'active' if active == 'perfil' }}">Perfil</a>
    <a href="{{ url_for('configuracion') }}" class="{{ 'active' if active == 'config' }}">Configuración</a>
    <a href="{{ url_for('logout') }}">Cerrar sesión</a>
  </div>
</nav>
"""

# ---------------------------------------------------------------------------
# PANTALLA 1 – LOGIN
# ---------------------------------------------------------------------------
LOGIN_HTML = (
    "<!DOCTYPE html><html lang='es'><head><meta charset='utf-8'>"
    "<meta name='viewport' content='width=device-width,initial-scale=1'>"
    "<title>Login – App Piloto</title>"
    + BASE_CSS
    + "</head><body>"
    + """
<div class="login-wrapper">
  <div class="card login-card">
    <h1>Iniciar sesión</h1>
    <p class="sub">Introduce tus credenciales para acceder</p>
    """
    + FLASH_BLOCK
    + """
    <form method="post">
      <label for="username">Usuario</label>
      <input type="text" id="username" name="username" placeholder="Ej: admin" required autofocus>
      <label for="password">Contraseña</label>
      <input type="password" id="password" name="password" placeholder="••••••••" required>
      <button type="submit" class="btn btn-primary" style="width:100%;margin-top:.5rem;">Entrar</button>
    </form>
    <p style="text-align:center;margin-top:1rem;font-size:.82rem;color:var(--muted);">
      Usuarios de prueba: <b>admin / admin123</b> — <b>user1 / user123</b> — <b>user2 / user456</b>
    </p>
  </div>
</div>
</body></html>
"""
)

# ---------------------------------------------------------------------------
# PANTALLA 2 – DASHBOARD
# ---------------------------------------------------------------------------
DASHBOARD_HTML = (
    "<!DOCTYPE html><html lang='es'><head><meta charset='utf-8'>"
    "<meta name='viewport' content='width=device-width,initial-scale=1'>"
    "<title>Dashboard – App Piloto</title>"
    + BASE_CSS
    + "</head><body>"
    + NAV_BAR
    + """
<div class="container">
  """
    + FLASH_BLOCK
    + """
  <h1>Bienvenido/a, {{ user.nombre }}</h1>
  <p class="sub">Panel de control — resumen de tu cuenta</p>

  <div class="stats">
    <div class="stat-card"><div class="value">{{ user.departamento[:3] | upper }}</div>
      <div class="label">Departamento</div></div>
    <div class="stat-card"><div class="value">{{ user.rol }}</div>
      <div class="label">Rol</div></div>
    <div class="stat-card"><div class="value">{{ user.ciudad }}</div>
      <div class="label">Ciudad</div></div>
    <div class="stat-card"><div class="value">{{ user.idioma[:3] | upper }}</div>
      <div class="label">Idioma</div></div>
  </div>

  <div class="card">
    <h2>Datos de contacto</h2>
    <table>
      <tr><th>Email</th><td>{{ user.email }}</td></tr>
      <tr><th>Teléfono</th><td>{{ user.telefono }}</td></tr>
      <tr><th>Departamento</th><td>{{ user.departamento }}</td></tr>
    </table>
    <div class="actions">
      <a href="{{ url_for('perfil') }}" class="btn btn-primary">Editar perfil</a>
      <a href="{{ url_for('configuracion') }}" class="btn btn-outline">Configuración</a>
    </div>
  </div>

  <!-- Mini formulario: búsqueda rápida (lógica frontend) -->
  <div class="card">
    <h2>Búsqueda rápida</h2>
    <form onsubmit="event.preventDefault(); doSearch();">
      <div class="two-col">
        <div>
          <label for="q">Término de búsqueda</label>
          <input type="text" id="q" placeholder="Escribe algo...">
        </div>
        <div>
          <label for="cat">Categoría</label>
          <select id="cat">
            <option value="">— Todas —</option>
            <option>Documentos</option>
            <option>Usuarios</option>
            <option>Informes</option>
          </select>
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Buscar</button>
      <span id="search-result" style="margin-left:1rem;font-size:.9rem;color:var(--muted);"></span>
    </form>
  </div>
</div>
<script>
function doSearch(){
  const q = document.getElementById('q').value.trim();
  const cat = document.getElementById('cat').value || 'todas las categorías';
  const el = document.getElementById('search-result');
  if(!q){ el.textContent='Introduce un término.'; return; }
  el.textContent = 'Buscando "' + q + '" en ' + cat + '… (funcionalidad placeholder)';
}
</script>
</body></html>
"""
)

# ---------------------------------------------------------------------------
# PANTALLA 3 – PERFIL
# ---------------------------------------------------------------------------
PERFIL_HTML = (
    "<!DOCTYPE html><html lang='es'><head><meta charset='utf-8'>"
    "<meta name='viewport' content='width=device-width,initial-scale=1'>"
    "<title>Perfil – App Piloto</title>"
    + BASE_CSS
    + "</head><body>"
    + NAV_BAR
    + """
<div class="container">
  """
    + FLASH_BLOCK
    + """
  <h1>Mi perfil</h1>
  <p class="sub">Actualiza tu información personal</p>

  <div class="card">
    <form method="post">
      <div class="two-col">
        <div>
          <label for="nombre">Nombre completo</label>
          <input type="text" id="nombre" name="nombre" value="{{ user.nombre }}" required>
        </div>
        <div>
          <label for="email">Correo electrónico</label>
          <input type="email" id="email" name="email" value="{{ user.email }}" required>
        </div>
        <div>
          <label for="telefono">Teléfono</label>
          <input type="tel" id="telefono" name="telefono" value="{{ user.telefono }}">
        </div>
        <div>
          <label for="departamento">Departamento</label>
          <select id="departamento" name="departamento">
            {% for d in departamentos %}
              <option {% if d == user.departamento %}selected{% endif %}>{{ d }}</option>
            {% endfor %}
          </select>
        </div>
        <div>
          <label for="ciudad">Ciudad</label>
          <select id="ciudad" name="ciudad">
            {% for c in ciudades %}
              <option {% if c == user.ciudad %}selected{% endif %}>{{ c }}</option>
            {% endfor %}
          </select>
        </div>
        <div>
          <label for="idioma">Idioma preferido</label>
          <select id="idioma" name="idioma">
            {% for i in idiomas %}
              <option {% if i == user.idioma %}selected{% endif %}>{{ i }}</option>
            {% endfor %}
          </select>
        </div>
      </div>

      <div class="actions" style="margin-top:.5rem;">
        <button type="submit" class="btn btn-primary">Guardar cambios</button>
        <a href="{{ url_for('dashboard') }}" class="btn btn-outline">Cancelar</a>
      </div>
    </form>
  </div>

  <!-- Cambiar contraseña (placeholder) -->
  <div class="card">
    <h2>Cambiar contraseña</h2>
    <form onsubmit="event.preventDefault(); cambiarPass();">
      <label for="pass_actual">Contraseña actual</label>
      <input type="password" id="pass_actual" placeholder="••••••••">
      <div class="two-col">
        <div>
          <label for="pass_nueva">Nueva contraseña</label>
          <input type="password" id="pass_nueva" placeholder="••••••••">
        </div>
        <div>
          <label for="pass_confirmar">Confirmar nueva</label>
          <input type="password" id="pass_confirmar" placeholder="••••••••">
        </div>
      </div>
      <span id="pass-msg" style="font-size:.85rem;"></span><br><br>
      <button type="submit" class="btn btn-primary">Actualizar contraseña</button>
    </form>
  </div>
</div>
<script>
function cambiarPass(){
  const a = document.getElementById('pass_actual').value;
  const n = document.getElementById('pass_nueva').value;
  const c = document.getElementById('pass_confirmar').value;
  const el = document.getElementById('pass-msg');
  if(!a||!n||!c){ el.style.color='var(--danger)'; el.textContent='Rellena todos los campos.'; return; }
  if(n !== c){ el.style.color='var(--danger)'; el.textContent='Las contraseñas no coinciden.'; return; }
  if(n.length < 6){ el.style.color='var(--danger)'; el.textContent='Mínimo 6 caracteres.'; return; }
  el.style.color='var(--success)'; el.textContent='Contraseña actualizada (placeholder).';
}
</script>
</body></html>
"""
)

# ---------------------------------------------------------------------------
# PANTALLA 4 – CONFIGURACIÓN
# ---------------------------------------------------------------------------
CONFIG_HTML = (
    "<!DOCTYPE html><html lang='es'><head><meta charset='utf-8'>"
    "<meta name='viewport' content='width=device-width,initial-scale=1'>"
    "<title>Configuración – App Piloto</title>"
    + BASE_CSS
    + "</head><body>"
    + NAV_BAR
    + """
<div class="container">
  """
    + FLASH_BLOCK
    + """
  <h1>Configuración</h1>
  <p class="sub">Ajusta las preferencias de tu cuenta</p>

  <div class="card">
    <h2>Preferencias de notificaciones</h2>
    <form method="post">
      <div class="checkbox-group">
        <input type="checkbox" id="noti_email" name="notificaciones_email" value="1"
               {% if user.notificaciones_email %}checked{% endif %}>
        <label for="noti_email" style="font-weight:400;">Recibir notificaciones por email</label>
      </div>
      <div class="checkbox-group">
        <input type="checkbox" id="noti_sms" name="notificaciones_sms" value="1"
               {% if user.notificaciones_sms %}checked{% endif %}>
        <label for="noti_sms" style="font-weight:400;">Recibir notificaciones por SMS</label>
      </div>

      <label>Tema de la interfaz</label>
      <div class="radio-group">
        {% for val, lbl in temas %}
        <label>
          <input type="radio" name="tema" value="{{ val }}"
                 {% if user.tema == val %}checked{% endif %}> {{ lbl }}
        </label>
        {% endfor %}
      </div>

      <label for="idioma_cfg">Idioma de la aplicación</label>
      <select id="idioma_cfg" name="idioma">
        {% for i in idiomas %}
          <option {% if i == user.idioma %}selected{% endif %}>{{ i }}</option>
        {% endfor %}
      </select>

      <div class="actions">
        <button type="submit" class="btn btn-primary">Guardar preferencias</button>
        <a href="{{ url_for('dashboard') }}" class="btn btn-outline">Volver al dashboard</a>
      </div>
    </form>
  </div>

  <!-- Zona de peligro -->
  <div class="card" style="border: 2px solid var(--danger);">
    <h2 style="color: var(--danger);">Zona de peligro</h2>
    <p style="margin-bottom:1rem;font-size:.9rem;color:var(--muted);">
      Estas acciones son irreversibles (placeholder).
    </p>
    <button class="btn btn-danger" onclick="confirmDelete()">Eliminar mi cuenta</button>
    <span id="delete-msg" style="margin-left:1rem;font-size:.85rem;"></span>
  </div>
</div>
<script>
function confirmDelete(){
  const el = document.getElementById('delete-msg');
  if(confirm('¿Seguro que deseas eliminar tu cuenta? (acción placeholder)')){
    el.style.color='var(--danger)';
    el.textContent='Cuenta marcada para eliminación (placeholder).';
  }
}
</script>
</body></html>
"""
)


# ---------------------------------------------------------------------------
# RUTAS
# ---------------------------------------------------------------------------

@app.route("/", methods=["GET"])
def index():
    if "username" in session:
        return redirect(url_for("dashboard"))
    return redirect(url_for("login"))


@app.route("/login", methods=["GET", "POST"])
def login():
    if request.method == "POST":
        username = request.form.get("username", "").strip()
        password = request.form.get("password", "")
        user = USERS.get(username)
        if user and user["password"] == password:
            session["username"] = username
            flash(f"Bienvenido/a, {user['nombre']}.", "success")
            return redirect(url_for("dashboard"))
        flash("Usuario o contraseña incorrectos.", "error")
    return render_template_string(LOGIN_HTML)


@app.route("/dashboard")
@login_required
def dashboard():
    user = current_user()
    return render_template_string(DASHBOARD_HTML, user=user, active="dashboard")


@app.route("/perfil", methods=["GET", "POST"])
@login_required
def perfil():
    user = current_user()
    if request.method == "POST":
        user["nombre"] = request.form.get("nombre", user["nombre"])
        user["email"] = request.form.get("email", user["email"])
        user["telefono"] = request.form.get("telefono", user["telefono"])
        user["departamento"] = request.form.get("departamento", user["departamento"])
        user["ciudad"] = request.form.get("ciudad", user["ciudad"])
        user["idioma"] = request.form.get("idioma", user["idioma"])
        flash("Perfil actualizado correctamente.", "success")
        return redirect(url_for("perfil"))
    return render_template_string(
        PERFIL_HTML,
        user=user, active="perfil",
        departamentos=DEPARTAMENTOS, ciudades=CIUDADES, idiomas=IDIOMAS,
    )


@app.route("/configuracion", methods=["GET", "POST"])
@login_required
def configuracion():
    user = current_user()
    if request.method == "POST":
        user["notificaciones_email"] = "notificaciones_email" in request.form
        user["notificaciones_sms"] = "notificaciones_sms" in request.form
        user["tema"] = request.form.get("tema", user["tema"])
        user["idioma"] = request.form.get("idioma", user["idioma"])
        flash("Preferencias guardadas.", "success")
        return redirect(url_for("configuracion"))
    return render_template_string(
        CONFIG_HTML,
        user=user, active="config",
        idiomas=IDIOMAS, temas=TEMAS,
    )


@app.route("/logout")
def logout():
    session.clear()
    flash("Sesión cerrada.", "success")
    return redirect(url_for("login"))


# ---------------------------------------------------------------------------
# MAIN
# ---------------------------------------------------------------------------
if __name__ == "__main__":
    print("=" * 50)
    print("  App Piloto corriendo en http://127.0.0.1:5000")
    print("  Usuarios: admin/admin123, user1/user123, user2/user456")
    print("=" * 50)
    app.run(debug=True, port=5000)

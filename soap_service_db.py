from spyne import Application, rpc, ServiceBase, Unicode, Integer, Iterable
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
import psycopg2

# --- Conexión a PostgreSQL ---
def get_connection():
    return psycopg2.connect(
        dbname="crud_db",
        user="postgres",          # cambia si tu usuario es distinto
        password="tu_contraseña", # <--- reemplaza aquí
        host="localhost",
        port="5432"
    )

# --- Servicio con CRUD + LOGIN ---
class UsuarioService(ServiceBase):

    # CREATE
    @rpc(Unicode, Unicode, Unicode, _returns=Unicode)
    def crear_usuario(ctx, nombre, email, password):
        conn = get_connection()
        cur = conn.cursor()
        try:
            cur.execute("INSERT INTO usuarios (nombre, email, password) VALUES (%s, %s, %s)",
                        (nombre, email, password))
            conn.commit()
            return f"Usuario '{nombre}' creado correctamente."
        except psycopg2.errors.UniqueViolation:
            conn.rollback()
            return "Error: El correo ya está registrado."
        except Exception as e:
            conn.rollback()
            return f"Error al crear usuario: {str(e)}"
        finally:
            cur.close()
            conn.close()

    # READ
    @rpc(_returns=Iterable(Unicode))
    def listar_usuarios(ctx):
        conn = get_connection()
        cur = conn.cursor()
        cur.execute("SELECT id, nombre, email FROM usuarios")
        rows = cur.fetchall()
        cur.close()
        conn.close()
        for r in rows:
            yield f"ID: {r[0]}, Nombre: {r[1]}, Email: {r[2]}"

    # UPDATE
    @rpc(Integer, Unicode, Unicode, _returns=Unicode)
    def actualizar_usuario(ctx, id, nuevo_nombre, nuevo_email):
        conn = get_connection()
        cur = conn.cursor()
        cur.execute("UPDATE usuarios SET nombre=%s, email=%s WHERE id=%s",
                    (nuevo_nombre, nuevo_email, id))
        conn.commit()
        filas = cur.rowcount
        cur.close()
        conn.close()
        if filas > 0:
            return f"Usuario {id} actualizado correctamente."
        else:
            return "Usuario no encontrado."

    # DELETE
    @rpc(Integer, _returns=Unicode)
    def eliminar_usuario(ctx, id):
        conn = get_connection()
        cur = conn.cursor()
        cur.execute("DELETE FROM usuarios WHERE id=%s", (id,))
        conn.commit()
        filas = cur.rowcount
        cur.close()
        conn.close()
        if filas > 0:
            return f"Usuario {id} eliminado correctamente."
        else:
            return "Usuario no encontrado."

    # LOGIN
    @rpc(Unicode, Unicode, _returns=Unicode)
    def login(ctx, email, password):
        conn = get_connection()
        cur = conn.cursor()
        cur.execute("SELECT nombre FROM usuarios WHERE email=%s AND password=%s", (email, password))
        user = cur.fetchone()
        cur.close()
        conn.close()
        if user:
            return f"Bienvenido, {user[0]}."
        else:
            return "Credenciales incorrectas."


# --- Configurar aplicación SOAP ---
application = Application(
    [UsuarioService],
    tns='spyne.examples.fullcrud',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

wsgi_app = WsgiApplication(application)

if __name__ == '__main__':
    from wsgiref.simple_server import make_server
    print("Servidor SOAP corriendo en http://localhost:8000")
    server = make_server('0.0.0.0', 8000, wsgi_app)
    server.serve_forever()

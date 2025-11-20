from spyne import Application, rpc, ServiceBase, Unicode, Integer, Iterable
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
import mysql.connector

# ============================
#  CONEXIÓN A BASE DE DATOS
# ============================
def db():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="password",
        database="name"
    )


# ============================
#     SERVICIO SOAP
# ============================
class IoTService(ServiceBase):

    # ----------------------------------------------------
    # VALIDACIÓN SIMPLE DE CAMPOS
    # ----------------------------------------------------
    def validar_texto(self, valor, campo):
        if valor is None or valor.strip() == "":
            raise ValueError(f"El campo '{campo}' no puede estar vacío")

    # ----------------------------------------------------
    # CRUD: DISPOSITIVOS IoT
    # ----------------------------------------------------

    @rpc(Unicode, Unicode, Unicode, Integer, Unicode, Integer, _returns=Unicode)
    def crearDispositivo(ctx, tipo_dispositivo, marca, num_serie,
                          frecuencia_cardiaca, resumen, user_id):
        """
        Crea un dispositivo IoT.
        """
        # VALIDACIONES
        for valor, campo in [
            (tipo_dispositivo, "tipo_dispositivo"),
            (marca, "marca"),
            (num_serie, "num_serie")
        ]:
            IoTService.validar_texto(None, valor, campo)

        cn = db()
        cur = cn.cursor()

        try:
            sql = """INSERT INTO dispositivos 
                     (tipo_dispositivo, marca, num_serie, frecuencia_cardiaca, resumen, user_id)
                     VALUES (%s,%s,%s,%s,%s,%s)"""
            cur.execute(sql, (tipo_dispositivo, marca, num_serie,
                              frecuencia_cardiaca, resumen, user_id))
            cn.commit()
            return "Dispositivo creado correctamente"
        except mysql.connector.Error as e:
            return f"Error al crear dispositivo: {str(e)}"
        finally:
            cur.close()
            cn.close()

    # ----------------------------------------------------
    @rpc(Integer, _returns=Iterable(Unicode))
    def obtenerDispositivo(ctx, id_dispositivo):
        """
        Obtiene un dispositivo por ID.
        """
        cn = db()
        cur = cn.cursor()

        try:
            cur.execute("SELECT * FROM dispositivos WHERE id_dispositivo = %s", (id_dispositivo,))
            row = cur.fetchone()
            if row:
                return [str(x) for x in row]
            return ["No existe ese dispositivo"]
        finally:
            cur.close()
            cn.close()

    # ----------------------------------------------------
    @rpc(Integer, Unicode, Unicode, Unicode, Integer, Unicode, Integer, _returns=Unicode)
    def actualizarDispositivo(ctx, id_dispositivo, tipo_dispositivo, marca,
                              num_serie, frecuencia_cardiaca, resumen, user_id):
        """
        Actualiza un dispositivo por ID.
        """
        cn = db()
        cur = cn.cursor()

        try:
            sql = """UPDATE dispositivos SET 
                     tipo_dispositivo=%s, marca=%s, num_serie=%s,
                     frecuencia_cardiaca=%s, resumen=%s, user_id=%s
                     WHERE id_dispositivo=%s"""
            cur.execute(sql, (tipo_dispositivo, marca, num_serie,
                              frecuencia_cardiaca, resumen, user_id, id_dispositivo))
            cn.commit()

            if cur.rowcount > 0:
                return "Dispositivo actualizado"
            return "No existe el dispositivo"
        finally:
            cur.close()
            cn.close()

    # ----------------------------------------------------
    @rpc(Integer, _returns=Unicode)
    def eliminarDispositivo(ctx, id_dispositivo):
        """
        Elimina un dispositivo por ID.
        """
        cn = db()
        cur = cn.cursor()

        try:
            cur.execute("DELETE FROM dispositivos WHERE id_dispositivo = %s", (id_dispositivo,))
            cn.commit()

            if cur.rowcount > 0:
                return "Dispositivo eliminado"
            return "No existe el dispositivo"
        finally:
            cur.close()
            cn.close()

    # ======================================================
    # RESUMEN DIARIO (solo crear/listar)
    # ======================================================

    @rpc(Integer, Unicode, _returns=Unicode)
    def crearResumenDiario(ctx, user_id, resumen):
        """
        Crea un resumen diario de actividad por usuario.
        """
        IoTService.validar_texto(None, resumen, "resumen")

        cn = db()
        cur = cn.cursor()

        try:
            sql = "INSERT INTO resumen_diario (user_id, resumen) VALUES (%s,%s)"
            cur.execute(sql, (user_id, resumen))
            cn.commit()
            return "Resumen diario registrado"
        finally:
            cur.close()
            cn.close()

    @rpc(Integer, _returns=Iterable(Unicode))
    def obtenerResumenesPorUsuario(ctx, user_id):
        """
        Obtiene todos los resúmenes diarios por usuario.
        """
        cn = db()
        cur = cn.cursor()

        try:
            cur.execute("SELECT * FROM resumen_diario WHERE user_id=%s", (user_id,))
            rows = cur.fetchall()

            return [str(r) for r in rows]
        finally:
            cur.close()
            cn.close()

    # ======================================================
    # FRECUENCIA CARDÍACA (Registrar)
    # ======================================================

    @rpc(Integer, Integer, _returns=Unicode)
    def registrarFrecuencia(ctx, user_id, frecuencia):
        """
        Registra un dato de frecuencia cardiaca.
        """
        cn = db()
        cur = cn.cursor()

        try:
            sql = "INSERT INTO frecuencia_cardiaca (user_id, frecuencia) VALUES (%s,%s)"
            cur.execute(sql, (user_id, frecuencia))
            cn.commit()
            return "Frecuencia cardiaca registrada"
        finally:
            cur.close()
            cn.close()


# ============================
#  PUBLICACIÓN DEL SERVICIO
# ============================
application = Application(
    [IoTService],
    tns='spyne.iot',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

wsgi_app = WsgiApplication(application)

if __name__ == '__main__':
    from wsgiref.simple_server import make_server
    print("Servicio SOAP corriendo en http://localhost:8000/?wsdl")
    server = make_server('localhost', 8000, wsgi_app)
    server.serve_forever()

from spyne import Application, rpc, ServiceBase, Unicode, Boolean
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication

class LoginService(ServiceBase):

    @rpc(Unicode, Unicode, _returns=Unicode)
    def login(ctx, username, password):
        # Simulación: en lugar de conectarse a una BD
        # se usan credenciales de ejemplo
        if username == "admin" and password == "1234":
            return "Login exitoso. Bienvenido admin."
        else:
            return "Credenciales incorrectas."

# Definimos la aplicación SOAP
application = Application(
    [LoginService],
    tns='spyne.examples.login',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

# Creamos el servidor WSGI
wsgi_app = WsgiApplication(application)

if __name__ == '__main__':
    from wsgiref.simple_server import make_server
    print("Servidor SOAP corriendo en http://localhost:8000")
    server = make_server('0.0.0.0', 8000, wsgi_app)
    server.serve_forever()
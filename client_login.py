from zeep import Client

# URL del WSDL
wsdl = 'http://localhost:8000/?wsdl'

client = Client(wsdl=wsdl)

response = client.service.login("admin", "1234")
print(response)
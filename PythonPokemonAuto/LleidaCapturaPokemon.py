from datetime import time

import requests
import time as std_time

# Lista de zonas
zonas = [
    "67372c56ec018d7dedd34ee3",
    "6737278e28aebf267e089bec",
    "67372c39c499cd12be6bef9e",
    "67372c61f269e28d2f86f063",
    "6710c41ed814fc8dae914171",
    "67372c23ea45b856683249f4",
    "67372c1c7a5c6e90024299e1",
    "67372c44db061db993104ce1",
    "67372c4a591a6cbabccfc012"
]

# URL base de la API
base_url = "https://hackeps-poke-backend.azurewebsites.net/events/"

# Datos del cuerpo de la solicitud
data = {
    "team_id": "c588d253-e2b5-4e25-9b0c-1252f747e451"
}

# Cabeceras de la solicitud
headers = {
    "accept": "application/json",
    "Content-Type": "application/json"
}

# Función para ejecutar las solicitudes
def ejecutar_solicitudes():
    for zona in zonas:
        url = f"{base_url}{zona}"  # Añade la zona al final de la URL
        try:
            response = requests.post(url, json=data, headers=headers)
            # Verifica el estado de la respuesta
            if response.status_code == 200:
                print(f"Éxito para la zona {zona}: {response.json()}")
            else:
                print(f"Error en la zona {zona}: {response.status_code} - {response.text}")
        except Exception as e:
            print(f"Excepción para la zona {zona}: {str(e)}")

def hola():
    print(f"hola")

# Bucle infinito para ejecutar cada 10 minutos
while True:
    print("Ejecutando solicitudes...")
    ejecutar_solicitudes()
    print("Esperando 13 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 12 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 11 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 10 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 9 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 8 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 7 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 6 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 5 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 4 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 3 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 2 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
    print("Esperando 1 minutos para la próxima ejecución...")
    std_time.sleep(60)  # Espera 10 minutos (600 segundos)
import requests
import time

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

# Nombre del equipo a buscar
equipo_a_buscar = "PandaEnjoyers"

# ID del equipo para enviar en el cuerpo del POST
team_id = "c588d253-e2b5-4e25-9b0c-1252f747e451"

# URL base de la API
base_url = "https://hackeps-poke-backend.azurewebsites.net/zones/"
post_url = "https://hackeps-poke-backend.azurewebsites.net/events/"


# Función para comprobar y enviar la solicitud POST si es necesario
def comprobar_zonas():
    for zona_id in zonas:
        try:
            # Hacer la solicitud GET para la zona
            response = requests.get(f"{base_url}{zona_id}", headers={"accept": "application/json"})
            response.raise_for_status()

            # Obtener los datos en formato JSON
            datos_zona = response.json()

            # Extraer la lista de last_requests_by_team
            equipos = datos_zona.get("last_requests_by_team", [])

            # Verificar si el equipo está en la lista
            equipo_encontrado = any(equipo["name"] == equipo_a_buscar for equipo in equipos)

            if not equipo_encontrado:
                print(
                    f"El equipo '{equipo_a_buscar}' NO está en la zona '{datos_zona.get('name', 'Desconocida')}' (ID: {zona_id}). Enviando solicitud POST...")

                # Hacer la solicitud POST
                post_response = requests.post(
                    f"{post_url}{zona_id}",
                    headers={
                        "accept": "application/json",
                        "Content-Type": "application/json"
                    },
                    json={"team_id": team_id}
                )
                post_response.raise_for_status()

                print(f"Solicitud POST enviada correctamente para la zona '{datos_zona.get('name', 'Desconocida')}'.")
            else:
                print(
                    f"El equipo '{equipo_a_buscar}' está presente en la zona '{datos_zona.get('name', 'Desconocida')}' (ID: {zona_id}).")

        except requests.RequestException as e:
            print(f"Error al procesar la zona {zona_id}: {e}")


# Comprobación continua cada minuto
while True:
    print("Comprobando zonas...")
    comprobar_zonas()
    print("Esperando 1 minuto para la próxima comprobación...")
    time.sleep(60)  # Esperar 1 minuto

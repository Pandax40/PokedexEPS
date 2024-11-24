import requests
from collections import Counter

# Función para obtener el nombre del Pokémon dado su ID
def get_pokemon_name(pokemon_id):
    pokemon_url = f'https://hackeps-poke-backend.azurewebsites.net/pokemons/{pokemon_id}'
    response = requests.get(pokemon_url)
    pokemon_data = response.json()
    return pokemon_data['name']

# URL para obtener el equipo
team_url = 'https://hackeps-poke-backend.azurewebsites.net/teams/c588d253-e2b5-4e25-9b0c-1252f747e451'

# Realizamos la llamada para obtener el equipo
response = requests.get(team_url)
team_data = response.json()

# Extraemos los números de los Pokémon junto con sus IDs y nombres
pokemon_data = [(pokemon['pokemon_id'], get_pokemon_name(pokemon['pokemon_id'])) for pokemon in team_data['captured_pokemons']]

# Ordenamos la lista de tu equipo por el ID de Pokémon
pokemon_data_sorted = sorted(pokemon_data, key=lambda x: x[0])

# Contamos las ocurrencias de cada Pokémon
pokemon_counts = Counter(name for _, name in pokemon_data_sorted)

# Imprimimos los nombres y sus cantidades ordenados por ID
print("Pokémon en tu equipo (y su cantidad) ordenados por ID:")
for name, count in pokemon_counts.items():
    print(f"{name}: {count}")

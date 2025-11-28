¡Excelente! Aquí tienes el archivo README.md completo y profesional para tu repositorio. Incluye todas las especificaciones técnicas que definimos, la estructura del proyecto y, lo más importante, la documentación detallada de los endpoints usando tu link real de Render.

Copia y pega el siguiente contenido en un archivo llamado README.md en la raíz de tu proyecto backend.

VidaSalud Backend API
Backend oficial para la aplicación móvil VidaSalud. Este servicio RESTful gestiona la autenticación de usuarios y el almacenamiento de publicaciones (comidas) con imágenes.

Base URL (Producción): https://vidasaludbackend.onrender.com

🛠 Tecnologías
Lenguaje: Java 17 (o superior)

Framework: Spring Boot 3.2.3

Gestor de Dependencias: Maven

Base de Datos: PostgreSQL

Seguridad: Spring Security + JWT (JSON Web Tokens)

Persistencia: Spring Data JPA (Hibernate)

📂 Estructura del Proyecto
El código está organizado en los siguientes paquetes principales bajo com.vidasalud.backend:

model: Entidades de la base de datos (User, Post).

repository: Interfaces para la comunicación con la base de datos (JPA).

controller: Endpoints de la API REST (AuthController, PostController).

config: Configuración de seguridad y utilidades JWT (SecurityConfig, JwtUtil).

🔐 Autenticación & Seguridad
La API utiliza Tokens JWT (Bearer Token).

El usuario se registra o inicia sesión en /auth.

El servidor devuelve un token.

Para acceder a rutas protegidas (como crear posts), se debe enviar este token en el Header de la petición:

Authorization: Bearer <tu_token_aqui>
Nota: Las rutas de /auth/** y /uploads/** son públicas. Todas las demás requieren autenticación.

📡 Documentación de Endpoints
1. Autenticación (/auth)
Registrar Usuario
Crea una nueva cuenta de usuario en la base de datos.

Método: POST

URL: https://vidasaludbackend.onrender.com/auth/register

Body (JSON):

JSON

{
  "username": "usuario123",
  "password": "mypassword",
  "email": "usuario@ejemplo.com"
}
Respuesta Exitosa (200 OK): "Usuario registrado"

Iniciar Sesión
Verifica las credenciales y devuelve el token de acceso.

Método: POST

URL: https://vidasaludbackend.onrender.com/auth/login

Body (JSON):

JSON

{
  "username": "usuario123",
  "password": "mypassword"
}
Respuesta Exitosa (200 OK):

JSON

{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3V..."
}
2. Publicaciones (/posts)
🔒 Requiere Header Authorization

Obtener todas las publicaciones
Devuelve la lista de comidas registradas.

Método: GET

URL: https://vidasaludbackend.onrender.com/posts

Respuesta Exitosa (200 OK):

JSON

[
  {
    "id": 1,
    "title": "Ensalada César",
    "description": "300 calorías, con pollo",
    "imageUrl": "https://vidasaludbackend.onrender.com/uploads/foto_uuid.jpg",
    "createdAt": "2024-03-20T10:00:00",
    "user": { ... }
  }
]
Crear una publicación (Subir Foto)
Sube una foto y guarda los datos de la comida.

Método: POST

URL: https://vidasaludbackend.onrender.com/posts

Tipo de contenido: multipart/form-data

Parámetros (Form-Data):

title (Text): Título de la comida.

description (Text): Descripción o calorías.

username (Text): El nombre de usuario que publica (ej: "usuario123").

image (File): El archivo de imagen (jpg/png).

3. Imágenes Estáticas (/uploads)
Acceso directo a las imágenes subidas por los usuarios.

Método: GET

URL Base: https://vidasaludbackend.onrender.com/uploads/

Ejemplo: https://vidasaludbackend.onrender.com/uploads/b149b0-imagen.jpg

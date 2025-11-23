# Instrucciones para Crear la Base de Datos

## Paso 1: Abrir phpMyAdmin

1. Asegúrate de que **WAMP Server** esté corriendo (ícono verde)
2. Abre tu navegador y ve a: `http://localhost/phpmyadmin`

## Paso 2: Crear la Base de Datos

1. En la interfaz de phpMyAdmin, haz clic en la pestaña **"Bases de datos"** (arriba)
2. En el campo **"Crear base de datos"**, escribe: `restaurant_db`
3. En **"Cotejamiento"**, selecciona: `utf8mb4_unicode_ci`
4. Haz clic en el botón **"Crear"**

## Paso 3: Verificar Credenciales

Verifica que las credenciales en `application.properties` sean correctas:
- **Usuario**: `root` (por defecto en WAMP)
- **Contraseña**: (vacía por defecto, si la cambiaste, actualiza el archivo)

## Paso 4: Ejecutar la Aplicación

Una vez creada la base de datos, ejecuta la aplicación nuevamente. Las tablas se crearán automáticamente gracias a la configuración `spring.jpa.hibernate.ddl-auto=update`.

## Alternativa: Crear BD desde SQL

Si prefieres usar SQL directamente, ejecuta este comando en phpMyAdmin (pestaña SQL):

```sql
CREATE DATABASE restaurant_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

¡Listo! Después de crear la base de datos, la aplicación debería iniciar correctamente.




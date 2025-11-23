# Sistema de Gestión de Restaurante "Sabor Gourmet"

Sistema empresarial desarrollado con **Spring Boot 3.5.8** (Backend) y **Astro + Tailwind CSS** (Frontend) para la gestión integral de un restaurante, implementando AOP para auditoría y Spring Security para control de acceso basado en roles.

## 🚀 Características Principales

- ✅ **CRUD Completo** para todos los módulos del sistema
- ✅ **Spring Security** con roles y rutas protegidas (ADMIN, MOZO, COCINERO, CAJERO)
- ✅ **AOP (Aspect-Oriented Programming)** para auditoría automática
- ✅ **JPA/Hibernate** para persistencia de datos
- ✅ **Frontend Moderno** con Astro + Tailwind CSS + Flowbite
- ✅ **MySQL** como base de datos
- ✅ **REST API** para comunicación entre frontend y backend

## 🛠️ Tecnologías Utilizadas

### Backend
- **Spring Boot 3.5.8**
- **Spring Security** (Autenticación y Autorización)
- **Spring Data JPA** (Persistencia)
- **Spring AOP** (Aspectos Transversales)
- **MySQL** (Base de datos)
- **Maven** (Gestión de dependencias)

### Frontend
- **Astro** (Framework web moderno)
- **Tailwind CSS** (Framework CSS utility-first)
- **Flowbite** (Componentes UI)
- **TypeScript** (Tipado estático)

## 📋 Requisitos Previos

- Java 17 o superior
- Maven 3.6+
- Node.js 18+ y npm
- MySQL 8.0+ (o WAMP Server con MySQL)
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🗄️ Configuración de la Base de Datos

1. **Crear la base de datos en MySQL:**
   ```sql
   CREATE DATABASE restaurant_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

2. **Configurar `application.properties`:**
   - El archivo ya está configurado para conectarse a `localhost:3306`
   - Usuario: `root`
   - Contraseña: (vacía por defecto, ajustar si es necesario)
   - Base de datos: `restaurant_db`

3. **Las tablas se crearán automáticamente** al ejecutar la aplicación (gracias a `spring.jpa.hibernate.ddl-auto=update`)

## 🚀 Instalación y Ejecución

### Backend (Spring Boot)

1. **Compilar el proyecto:**
   ```bash
   mvn clean install
   ```

2. **Ejecutar la aplicación:**
   ```bash
   mvn spring-boot:run
   ```
   O desde el IDE ejecutar la clase `RestaurantManagementSystemApplication`

3. **El backend estará disponible en:** `http://localhost:8081`

### Frontend (Astro)

1. **Navegar a la carpeta del frontend:**
   ```bash
   cd frontend-flowbite
   ```

2. **Instalar dependencias:**
   ```bash
   npm install
   ```

3. **Iniciar el servidor de desarrollo:**
   ```bash
   npm run dev
   ```

4. **El frontend estará disponible en:** `http://localhost:4321`

## 👥 Usuarios por Defecto

El sistema crea automáticamente los siguientes usuarios al iniciar:

| Usuario    | Contraseña  | Rol       | Acceso                          |
|------------|-------------|-----------|---------------------------------|
| admin      | admin123    | ADMIN     | Acceso completo                 |
| mozo       | mozo123     | MOZO      | Crear y gestionar pedidos       |
| cocinero   | cocinero123 | COCINERO  | Ver y confirmar pedidos         |
| cajero     | cajero123   | CAJERO    | Gestionar ventas y facturas     |

## 🔐 Estructura de Roles y Permisos

### ADMIN
- Acceso completo al sistema
- Gestión de: Clientes, Mesas, Platos, Pedidos, Ventas, Inventario, Usuarios

### MOZO
- Crear y gestionar pedidos
- Ver pedidos
- No puede acceder a: Clientes, Mesas, Platos, Ventas, Inventario

### COCINERO
- Ver pedidos
- Confirmar pedidos (cambiar estado a "en preparación")
- Cambiar estado de pedidos (pendiente → en preparación → servido)
- No puede crear pedidos nuevos

### CAJERO
- Gestionar ventas y facturación
- Generar facturas
- Registrar pagos
- No puede acceder a: Clientes, Mesas, Platos, Pedidos, Inventario

## 📦 Módulos del Sistema

### 1. Clientes (`/crud/clientes`)
- CRUD de clientes
- Gestión de información personal (DNI, nombres, apellidos, teléfono, correo)

### 2. Mesas (`/crud/mesas`)
- CRUD de mesas
- Estados: disponible, ocupada, reservada, mantenimiento

### 3. Platos (`/crud/platos`)
- Catálogo de platos (entrada, fondo, postre, bebida)
- Gestión de precios y disponibilidad

### 4. Pedidos (`/crud/pedidos`)
- Creación y seguimiento de pedidos
- Estados: pendiente, en preparación, servido, cerrado
- **MOZO**: Puede crear pedidos
- **COCINERO**: Puede confirmar y cambiar estado de pedidos

### 5. Ventas (`/crud/ventas`)
- Generación de facturas
- Métodos de pago: efectivo, tarjeta, yape
- Control de pagos

### 6. Inventario (`/crud/inventario`)
- Gestión de insumos
- Alertas de stock bajo
- Control de unidades y precios

## 📊 Auditoría con AOP

El sistema implementa **Aspect-Oriented Programming (AOP)** para registrar automáticamente todas las acciones en la tabla `Bitacora`:

- **Aspecto de Auditoría**: `AuditoriaAspect`
- Registra automáticamente: CREAR, ACTUALIZAR, ELIMINAR
- Información registrada:
  - Usuario que realizó la acción
  - Método y clase ejecutada
  - Fecha y hora
  - Tipo de acción

## 📁 Estructura del Proyecto

```
restaurant-management-system/
├── src/main/java/com/tecsup/restaurantmanagementsystem/
│   ├── model/              # Entidades JPA
│   ├── repository/         # Repositorios (JpaRepository)
│   ├── service/            # Lógica de negocio
│   ├── controller/         # Controladores MVC y REST API
│   ├── config/             # Configuración (Security, CORS, DataInitializer)
│   ├── aspect/             # Aspectos AOP (Auditoría)
│   └── dto/                # Data Transfer Objects
├── frontend-flowbite/      # Frontend con Astro
│   ├── src/
│   │   ├── pages/          # Páginas de Astro
│   │   ├── components/     # Componentes reutilizables
│   │   ├── app/            # Layouts y componentes principales
│   │   └── lib/            # Utilidades y API client
│   └── package.json
├── src/main/resources/
│   ├── application.properties
│   └── templates/          # Templates Thymeleaf (legacy)
└── pom.xml
```

## 🗄️ Base de Datos

### Tablas Principales:
- `cliente` - Información de clientes
- `mesa` - Mesas del restaurante
- `plato` - Catálogo de platos
- `insumo` - Insumos e inventario
- `pedido` - Pedidos de clientes
- `detalle_pedido` - Detalles de cada pedido
- `factura` - Facturas generadas
- `detalle_factura` - Detalles de facturación
- `proveedor` - Proveedores
- `compra` - Compras de insumos
- `detalle_compra` - Detalles de compras
- `usuario` - Usuarios del sistema
- `bitacora` - Registro de auditoría

## 🔧 Desarrollo

### Compilar Backend:
```bash
mvn clean compile
```

### Ejecutar Tests:
```bash
mvn test
```

### Generar JAR:
```bash
mvn clean package
```

### Ejecutar JAR:
```bash
java -jar target/restaurant-management-system-0.0.1-SNAPSHOT.jar
```

## 📝 Notas Importantes

1. **Primera ejecución**: El sistema creará automáticamente los usuarios por defecto
2. **Base de datos**: Las tablas se crean automáticamente al iniciar
3. **Auditoría**: Todas las acciones CRUD se registran automáticamente en la bitácora
4. **Seguridad**: Las contraseñas se almacenan cifradas con BCrypt
5. **Validación**: Se utilizan anotaciones de validación JPA en todas las entidades
6. **CORS**: Configurado para permitir comunicación entre frontend (puerto 4321) y backend (puerto 8081)

## 🐛 Solución de Problemas

### Error de conexión a MySQL:
- Verificar que MySQL esté corriendo
- Verificar credenciales en `application.properties`
- Verificar que la base de datos `restaurant_db` exista

### Error de compilación:
- Verificar que Java 17 esté instalado
- Ejecutar `mvn clean install` para descargar dependencias

### Error de permisos:
- Verificar que el usuario tenga el rol correcto
- Revisar la configuración en `SecurityConfig.java`

### Frontend no se conecta al backend:
- Verificar que el backend esté corriendo en `http://localhost:8081`
- Verificar la configuración de CORS en `CorsConfig.java`
- Verificar que las cookies de sesión se estén enviando correctamente

## 👨‍💻 Autor

Desarrollado para el curso de Desarrollo de Aplicaciones Web Avanzado

## 📄 Licencia

Este proyecto es de uso educativo.

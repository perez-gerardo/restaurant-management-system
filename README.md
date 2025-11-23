# 🍽️ Sistema de Gestión de Restaurante "Sabor Gourmet"

Sistema empresarial completo para la gestión integral de un restaurante, desarrollado con tecnologías modernas y arquitectura separada entre frontend y backend.

## 📖 Contexto del Proyecto

**Sabor Gourmet** es un sistema de gestión diseñado para restaurantes que necesitan administrar de manera eficiente todas las operaciones del negocio. El sistema permite gestionar clientes, mesas, menú, pedidos, ventas, inventario y usuarios del sistema, todo con un control de acceso basado en roles que garantiza que cada empleado solo acceda a las funcionalidades correspondientes a su puesto.

### ¿Qué hace el sistema?

El sistema automatiza y centraliza las siguientes operaciones de un restaurante:

- **Gestión de Clientes**: Registro y administración de información de clientes (DNI, nombres, contacto)
- **Gestión de Mesas**: Control de disponibilidad y estado de las mesas del restaurante
- **Catálogo de Platos**: Administración del menú con categorías (entrada, fondo, postre, bebida) y precios
- **Gestión de Pedidos**: Sistema completo de pedidos con flujo de trabajo:
  - **MOZO**: Crea pedidos nuevos
  - **COCINERO**: Ve pedidos y confirma su preparación (cambia estados)
  - Estados: Pendiente → En Preparación → Servido → Cerrado
- **Ventas y Facturación**: Generación de facturas, registro de pagos (efectivo, tarjeta, yape)
- **Inventario**: Control de insumos, alertas de stock bajo, gestión de proveedores y compras
- **Auditoría Automática**: Registro automático de todas las acciones CRUD en bitácora
- **Control de Acceso**: Sistema de roles con permisos específicos por tipo de usuario

## 🛠️ Tecnologías Utilizadas

### Backend
- **Spring Boot 3.5.8** - Framework principal para desarrollo de aplicaciones Java empresariales
- **Spring Security** - Autenticación y autorización basada en roles (ADMIN, MOZO, COCINERO, CAJERO)
- **Spring Data JPA** - Abstracción para acceso a datos y persistencia
- **Spring AOP** - Programación orientada a aspectos para auditoría automática
- **Hibernate** - ORM (Object-Relational Mapping) para mapeo objeto-relacional
- **MySQL** - Base de datos relacional
- **Maven** - Gestión de dependencias y construcción del proyecto
- **BCrypt** - Cifrado de contraseñas

### Frontend
- **Astro 4.x** - Framework web moderno con renderizado del lado del servidor
- **Tailwind CSS 3.x** - Framework CSS utility-first para diseño rápido y responsive
- **Flowbite** - Biblioteca de componentes UI basada en Tailwind CSS
- **TypeScript** - Superset de JavaScript con tipado estático
- **REST API** - Comunicación asíncrona entre frontend y backend

### Arquitectura
- **Arquitectura Separada**: Frontend (puerto 4321) y Backend (puerto 8081) independientes
- **REST API**: Backend expone endpoints REST para todas las operaciones
- **Autenticación por Sesión**: Spring Security con cookies de sesión
- **CORS Configurado**: Permite comunicación entre dominios diferentes

## 🚀 Características Principales

- ✅ **CRUD Completo** para todos los módulos del sistema
- ✅ **Control de Acceso Basado en Roles (RBAC)** con 4 roles diferentes
- ✅ **Auditoría Automática** con AOP - todas las acciones se registran
- ✅ **Interfaz Moderna y Responsive** con diseño elegante y profesional
- ✅ **REST API** completa para integración con otros sistemas
- ✅ **Gestión de Estados** para pedidos y mesas
- ✅ **Alertas de Stock Bajo** en inventario
- ✅ **Dashboard con Estadísticas** en tiempo real

## 📋 Requisitos Previos

- **Java 17** o superior
- **Maven 3.6+**
- **Node.js 18+** y npm
- **MySQL 8.0+** (o WAMP Server con MySQL)
- **IDE** (IntelliJ IDEA, Eclipse, VS Code recomendado)

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

5. **Acceder al sistema:**
   - Ve a `http://localhost:4321/login` o `http://localhost:8081/login` (redirige automáticamente)
   - Usa las credenciales de los usuarios por defecto (ver sección siguiente)

## 👥 Usuarios por Defecto

El sistema crea automáticamente los siguientes usuarios al iniciar:

| Usuario    | Contraseña  | Rol       | Funcionalidades                          |
|------------|-------------|-----------|-----------------------------------------|
| admin      | admin123    | ADMIN     | Acceso completo a todos los módulos     |
| mozo       | mozo123     | MOZO      | Crear y gestionar pedidos               |
| cocinero   | cocinero123 | COCINERO  | Ver y confirmar pedidos                 |
| cajero     | cajero123   | CAJERO    | Gestionar ventas y facturas             |

## 🔐 Estructura de Roles y Permisos

### 👨‍💼 ADMIN
- **Acceso completo** al sistema
- Gestión de: Clientes, Mesas, Platos, Pedidos, Ventas, Inventario, Usuarios
- Configuración del sistema

### 🍽️ MOZO
- **Crear pedidos** nuevos
- Ver y editar pedidos existentes
- Asignar mesas y clientes a pedidos
- ❌ No puede acceder a: Clientes, Mesas, Platos, Ventas, Inventario

### 👨‍🍳 COCINERO
- **Ver todos los pedidos**
- **Confirmar pedidos** (cambiar estado a "en preparación")
- Cambiar estado de pedidos (pendiente → en preparación → servido)
- ❌ No puede crear pedidos nuevos
- ❌ No puede acceder a: Clientes, Mesas, Platos, Ventas, Inventario

### 💰 CAJERO
- **Gestionar ventas** y facturación
- Generar facturas
- Registrar pagos (efectivo, tarjeta, yape)
- ❌ No puede acceder a: Clientes, Mesas, Platos, Pedidos, Inventario

## 📦 Módulos del Sistema

### 1. 👥 Clientes (`/crud/clientes`)
- CRUD completo de clientes
- Gestión de información personal (DNI, nombres, apellidos, teléfono, correo)
- Estados: activo/inactivo
- **Solo ADMIN**

### 2. 🪑 Mesas (`/crud/mesas`)
- CRUD de mesas del restaurante
- Estados: disponible, ocupada, reservada, mantenimiento
- **Solo ADMIN**

### 3. 🍕 Platos (`/crud/platos`)
- Catálogo completo de platos
- Categorías: entrada, fondo, postre, bebida
- Gestión de precios y disponibilidad
- **Solo ADMIN**

### 4. 📋 Pedidos (`/crud/pedidos`)
- Creación y seguimiento de pedidos
- Estados: pendiente, en preparación, servido, cerrado
- Asignación de mesas y clientes
- **MOZO**: Puede crear pedidos
- **COCINERO**: Puede confirmar y cambiar estado de pedidos
- **ADMIN**: Acceso completo

### 5. 💵 Ventas (`/crud/ventas`)
- Generación de facturas
- Métodos de pago: efectivo, tarjeta, yape
- Control de pagos y estados de facturación
- **CAJERO** y **ADMIN**

### 6. 📦 Inventario (`/crud/inventario`)
- Gestión de insumos
- Alertas de stock bajo
- Control de unidades y precios
- Gestión de proveedores y compras
- **Solo ADMIN**

## 📊 Auditoría con AOP

El sistema implementa **Aspect-Oriented Programming (AOP)** para registrar automáticamente todas las acciones en la tabla `Bitacora`:

- **Aspecto de Auditoría**: `AuditoriaAspect`
- Registra automáticamente: **CREAR**, **ACTUALIZAR**, **ELIMINAR**
- Información registrada:
  - Usuario que realizó la acción
  - Método y clase ejecutada
  - Fecha y hora
  - Tipo de acción (CREAR/ACTUALIZAR/ELIMINAR)

### Ejemplo de Registro en Bitácora:
```
Usuario: admin
Acción: CREAR
Clase: ClienteService.save
Fecha: 2025-11-23 10:30:45
```

## 📁 Estructura del Proyecto

```
restaurant-management-system/
├── src/main/java/com/tecsup/restaurantmanagementsystem/
│   ├── model/              # Entidades JPA (Cliente, Mesa, Plato, Pedido, etc.)
│   ├── repository/         # Repositorios (JpaRepository)
│   ├── service/            # Lógica de negocio
│   ├── controller/         # Controladores MVC y REST API
│   │   ├── api/           # REST Controllers para frontend
│   │   └── *.java         # MVC Controllers (legacy)
│   ├── config/             # Configuración
│   │   ├── SecurityConfig.java      # Configuración de seguridad
│   │   ├── CorsConfig.java          # Configuración CORS
│   │   └── DataInitializer.java    # Inicialización de datos
│   ├── aspect/            # Aspectos AOP
│   │   └── AuditoriaAspect.java    # Auditoría automática
│   └── dto/                # Data Transfer Objects
├── frontend-flowbite/      # Frontend con Astro
│   ├── src/
│   │   ├── pages/          # Páginas de Astro
│   │   │   ├── crud/       # Páginas CRUD
│   │   │   └── login.astro # Página de login
│   │   ├── components/     # Componentes reutilizables
│   │   ├── app/            # Layouts y componentes principales
│   │   │   ├── SideBarRestaurante.astro
│   │   │   └── NavBarSidebar.astro
│   │   └── lib/            # Utilidades
│   │       ├── api.ts      # Cliente API REST
│   │       └── auth.ts     # Utilidades de autenticación
│   └── package.json
├── src/main/resources/
│   ├── application.properties    # Configuración de Spring Boot
│   └── templates/                # Templates Thymeleaf (legacy)
└── pom.xml                       # Configuración Maven
```

## 🗄️ Base de Datos

### Tablas Principales:
- `cliente` - Información de clientes
- `mesa` - Mesas del restaurante
- `plato` - Catálogo de platos
- `insumo` - Insumos e inventario
- `pedido` - Pedidos de clientes
- `detalle_pedido` - Detalles de cada pedido (platos y cantidades)
- `factura` - Facturas generadas
- `detalle_factura` - Detalles de facturación
- `proveedor` - Proveedores de insumos
- `compra` - Compras de insumos
- `detalle_compra` - Detalles de compras
- `usuario` - Usuarios del sistema
- `bitacora` - Registro de auditoría automática

## 🔧 Comandos de Desarrollo

### Backend

```bash
# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Generar JAR
mvn clean package

# Ejecutar JAR
java -jar target/restaurant-management-system-0.0.1-SNAPSHOT.jar
```

### Frontend

```bash
# Instalar dependencias
npm install

# Desarrollo
npm run dev

# Build para producción
npm run build

# Preview del build
npm run preview
```

## 📝 Notas Importantes

1. **Primera ejecución**: El sistema creará automáticamente los usuarios por defecto
2. **Base de datos**: Las tablas se crean automáticamente al iniciar (Hibernate DDL Auto)
3. **Auditoría**: Todas las acciones CRUD se registran automáticamente en la bitácora
4. **Seguridad**: Las contraseñas se almacenan cifradas con BCrypt
5. **Validación**: Se utilizan anotaciones de validación JPA en todas las entidades
6. **CORS**: Configurado para permitir comunicación entre frontend (puerto 4321) y backend (puerto 8081)
7. **Sesiones**: El sistema usa cookies de sesión para mantener la autenticación

## 🐛 Solución de Problemas

### Error de conexión a MySQL:
- Verificar que MySQL esté corriendo
- Verificar credenciales en `application.properties`
- Verificar que la base de datos `restaurant_db` exista

### Error de compilación:
- Verificar que Java 17 esté instalado: `java -version`
- Ejecutar `mvn clean install` para descargar dependencias
- Verificar que Maven esté instalado: `mvn -version`

### Error de permisos:
- Verificar que el usuario tenga el rol correcto
- Revisar la configuración en `SecurityConfig.java`
- Limpiar `localStorage` del navegador si hay problemas de autenticación

### Frontend no se conecta al backend:
- Verificar que el backend esté corriendo en `http://localhost:8081`
- Verificar la configuración de CORS en `CorsConfig.java`
- Verificar que las cookies de sesión se estén enviando correctamente
- Revisar la consola del navegador para errores de CORS

## 📚 Documentación Adicional

- **Credenciales de Usuarios**: Ver `CREDENCIALES_USUARIOS.md`
- **Instrucciones de Base de Datos**: Ver `INSTRUCCIONES_BD.md`

## 👨‍💻 Autor

Desarrollado para el curso de **Desarrollo de Aplicaciones Web Avanzado**

## 📄 Licencia

Este proyecto es de uso educativo.

---

**Repositorio**: [https://github.com/perez-gerardo/restaurant-management-system](https://github.com/perez-gerardo/restaurant-management-system)

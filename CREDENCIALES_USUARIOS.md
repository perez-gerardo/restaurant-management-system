# 🔐 CREDENCIALES DE ACCESO - SABOR GOURMET

## 👥 USUARIOS DEL SISTEMA

El sistema crea automáticamente estos usuarios al iniciar por primera vez:

---

### 👨‍💼 **ADMINISTRADOR**
- **Usuario:** `admin`
- **Contraseña:** `admin123`
- **Rol:** ADMIN
- **Acceso Completo:**
  - ✅ Gestión de Clientes
  - ✅ Gestión de Mesas
  - ✅ Gestión de Platos
  - ✅ Gestión de Pedidos
  - ✅ Gestión de Ventas
  - ✅ Gestión de Inventario
  - ✅ Gestión de Usuarios

---

### 🍽️ **MOZO**
- **Usuario:** `mozo`
- **Contraseña:** `mozo123`
- **Rol:** MOZO
- **Acceso:**
  - ✅ Gestión de Pedidos
  - ✅ Ver Dashboard
  - ❌ No puede acceder a: Clientes, Mesas, Platos, Ventas, Inventario, Usuarios

---

### 👨‍🍳 **COCINERO**
- **Usuario:** `cocinero`
- **Contraseña:** `cocinero123`
- **Rol:** COCINERO
- **Acceso:**
  - ✅ Gestión de Pedidos
  - ✅ Ver pedidos en cocina
  - ✅ Cambiar estado de pedidos
  - ❌ No puede acceder a: Clientes, Mesas, Platos, Ventas, Inventario, Usuarios

---

### 💰 **CAJERO**
- **Usuario:** `cajero`
- **Contraseña:** `cajero123`
- **Rol:** CAJERO
- **Acceso:**
  - ✅ Gestión de Ventas
  - ✅ Generar Facturas
  - ✅ Registrar Pagos
  - ❌ No puede acceder a: Clientes, Mesas, Platos, Pedidos, Inventario, Usuarios

---

## 📋 RESUMEN RÁPIDO

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| `admin` | `admin123` | ADMIN |
| `mozo` | `mozo123` | MOZO |
| `cocinero` | `cocinero123` | COCINERO |
| `cajero` | `cajero123` | CAJERO |

---

## 🔒 RUTAS PROTEGIDAS POR ROL

| Ruta | ADMIN | MOZO | COCINERO | CAJERO |
|------|-------|------|----------|--------|
| `/admin/**` | ✅ | ❌ | ❌ | ❌ |
| `/pedidos/**` | ✅ | ✅ | ✅ | ❌ |
| `/ventas/**` | ✅ | ❌ | ❌ | ✅ |
| `/inventario/**` | ✅ | ❌ | ❌ | ❌ |
| `/dashboard` | ✅ | ✅ | ✅ | ✅ |

---

## 📝 NOTAS IMPORTANTES

1. **Primera Ejecución:** Los usuarios se crean automáticamente al iniciar la aplicación por primera vez
2. **Contraseñas:** Todas las contraseñas están cifradas con BCrypt
3. **Seguridad:** Por ahora, las APIs están abiertas (sin autenticación JWT). Se implementará JWT próximamente.
4. **Auditoría:** Todas las acciones se registran automáticamente en la bitácora

---

## 🚀 CÓMO ACCEDER

### Para el Frontend (Astro):
1. Abre: `http://localhost:4321`
2. Por ahora no hay login en el frontend (se implementará próximamente)

### Para el Backend (Spring Boot):
1. Abre: `http://localhost:8081/login`
2. Ingresa las credenciales del usuario correspondiente
3. Serás redirigido al dashboard según tu rol

---

## ⚠️ SI OLVIDASTE LAS CREDENCIALES

Si necesitas restablecer las contraseñas:
1. Accede como ADMIN al backend
2. Ve a `/admin/usuarios`
3. Edita el usuario y cambia la contraseña

---

**Sistema desarrollado para el curso de Desarrollo de Aplicaciones Web Avanzado**


# Frontend - Sabor Gourmet

Frontend del Sistema de Gestión de Restaurante desarrollado con **Astro**, **Tailwind CSS** y **Flowbite**.

## 🚀 Inicio Rápido

1. **Instalar dependencias:**
   ```bash
   npm install
   ```

2. **Iniciar servidor de desarrollo:**
   ```bash
   npm run dev
   ```

3. **El frontend estará disponible en:** `http://localhost:4321`

## 📦 Tecnologías

- **Astro** - Framework web moderno
- **Tailwind CSS** - Framework CSS utility-first
- **Flowbite** - Componentes UI
- **TypeScript** - Tipado estático

## 🔗 Conexión con Backend

El frontend se conecta al backend Spring Boot en:
- **URL:** `http://localhost:8081`
- **API Base:** `http://localhost:8081/api`

Asegúrate de que el backend esté corriendo antes de iniciar el frontend.

## 📁 Estructura

```
frontend-flowbite/
├── src/
│   ├── pages/          # Páginas de Astro
│   ├── components/     # Componentes reutilizables
│   ├── app/            # Layouts y componentes principales
│   └── lib/            # Utilidades y API client
└── package.json
```

## 🛠️ Scripts Disponibles

- `npm run dev` - Inicia el servidor de desarrollo
- `npm run build` - Construye para producción
- `npm run preview` - Previsualiza el build de producción

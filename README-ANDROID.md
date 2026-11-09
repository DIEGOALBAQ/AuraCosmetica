# Aura Cosmética — Prototipo Android (Kotlin + Jetpack Compose)

App de compras de cosméticos con diseño de lujo editorial, generada a partir del
kit de diseño Aura (22 pantallas Figma/HTML). Este proyecto es la **interfaz
completa y navegable**: 10 pantallas de comprador, 6 de vendedor y 6 de
administrador, con datos de demostración en español (Colombia) y precios en COP.

---

## 1. Requisitos

| Herramienta        | Versión mínima                          |
|--------------------|------------------------------------------|
| Android Studio     | Ladybug (2024.2) o superior recomendado  |
| JDK                | 17 (incluido en Android Studio)          |
| Android SDK       | Compile SDK 35 (se descarga al sincronizar) |
| Dispositivo/emulador | Android 8.0 (API 26) o superior        |

## 2. Cómo abrirlo

1. Descomprime el ZIP.
2. En Android Studio: **File → Open…** y selecciona la carpeta `AuraCosmetica`.
3. Espera la **sincronización de Gradle** (la primera descarga puede tardar unos
   minutos; el wrapper 8.9 y las dependencias se descargan solos).
4. Pulsa **Run ▶** en un emulador o dispositivo.

> Si Android Studio pregunta por el SDK o el JDK 17, acepta las opciones
> sugeridas (Settings → Languages & Frameworks → Android SDK).

## 3. Estructura del proyecto

```
AuraCosmetica/
├── app/src/main/java/com/aura/cosmetica/
│   ├── MainActivity.kt              → punto de entrada (setContent)
│   ├── data/
│   │   ├── Models.kt                → Producto, Pedido, estados, usuarios
│   │   └── Repo.kt                  → catálogo (8 productos COP), carrito y pedidos
│   ├── nav/AuraNav.kt               → 22 rutas + navegación por rol (tab helpers)
│   └── ui/
│       ├── theme/                   → Color.kt · Type.kt · Theme.kt (tokens Aura)
│       ├── components/              → Componentes.kt + Tarjetas.kt (reutilizables)
│       └── screens/                 → las 22 pantallas (agrupadas por rol)
└── app/src/main/res/
    ├── font/                        → Cinzel · Cormorant Garamond · Inter (TTF)
    ├── drawable-nodpi/              → 8 fotos de producto + logo (PNG)
    └── mipmap-anydpi-v26/           → icono adaptativo con el logo A
```

## 4. Correspondencia pantallas ↔ maquetas del kit

| #  | Maqueta (PNG del kit)      | Composable (ui/screens/)        |
|----|-----------------------------|---------------------------------|
| 01 | 01-inicio                   | PantallaInicio                  |
| 02 | 02-catalogo                 | PantallaCatalogo                |
| 03 | 03-detalle                  | PantallaDetalle                 |
| 04 | 04-carrito                  | PantallaCarrito                 |
| 05 | 05-pago                     | PantallaPago                    |
| 06 | 06-confirmacion             | PantallaConfirmacion            |
| 07 | 07-registro                 | PantallaRegistro                |
| 08 | 08-direccion                | PantallaDireccion               |
| 09 | 09-pedidos                  | PantallaPedidos                 |
| 10 | 10-perfil                   | PantallaPerfil                  |
| 11 | 11-vendedor-panel           | PantallaVendedorPanel           |
| 12 | 12-vendedor-productos       | PantallaVendedorProductos       |
| 13 | 13-vendedor-crear-producto  | PantallaVendedorCrear           |
| 14 | 14-vendedor-camara          | PantallaVendedorCamara          |
| 15 | 15-vendedor-pedidos         | PantallaVendedorPedidos         |
| 16 | 16-vendedor-perfil          | PantallaVendedorPerfil          |
| 17 | 17-admin-panel              | PantallaAdminPanel              |
| 18 | 18-admin-usuarios           | PantallaAdminUsuarios           |
| 19 | 19-admin-productos          | PantallaAdminProductos          |
| 20 | 20-admin-compras            | PantallaAdminCompras            |
| 21 | 21-admin-reportes           | PantallaAdminReportes           |
| 22 | 22-admin-perfil             | PantallaAdminPerfil             |

## 5. Sistema de diseño aplicado

- **Colores** (`ui/theme/Color.kt`): marfil `#F7F1EA`, ciruela `#6E2F50`,
  champagne `#C9A96A`, blush `#F1DFD8` — los mismos tokens del kit.
- **Tipografía** (`ui/theme/Type.kt`): Cinzel (wordmark y títulos de header),
  Cormorant Garamond (titulares), Inter (UI). Fuentes locales en `res/font/`,
  no dependen de internet.
- **Formas**: botones píldora (999), tarjetas 16–24 dp, chips redondeadas.
- **Precios**: formateados con `Locale("es","CO")` → `$128.000`.

## 6. Qué incluye la navegación

- Comprador: Inicio → Catálogo → Detalle → Carrito → Pago → Confirmación →
  Pedidos, más Registro, Dirección y Perfil (barra inferior de 4 pestañas).
- Vendedor: Panel con KPIs, productos, crear producto (con cámara simulada),
  pedidos con avance de estado y perfil.
- Administrador: panel resumen, usuarios con roles, productos, compras,
  reportes con gráfico de barras y perfil.
- El carrito y los pedidos **reaccionan de verdad**: agregar productos, cambiar
  cantidades, vaciar, pagar y ver el pedido nuevo en "Mis pedidos".

## 7. Personalización rápida

- **Cambiar colores**: edita `ui/theme/Color.kt`.
- **Cambiar textos/productos**: edita `data/Repo.kt`.
- **Cambiar fotos**: reemplaza los PNG en `res/drawable-nodpi/` (mismo nombre).
- **Cambiar logo**: reemplaza `drawable-nodpi/logo_aura.png` y
  `drawable/ic_launcher_foreground.png`.

## 8. Limitaciones (prototipo de interfaz)

- Sin backend: los datos viven en `Repo.kt` (memoria). El pago es simulado.
- La cámara (pantalla 14) es una simulación visual del visor.
- Los íconos sociales del login no abren flujos reales.
- Pensado como base de UI: la lógica de negocio (Firebase, API REST, pagos)
  se puede conectar sobre esta misma estructura.

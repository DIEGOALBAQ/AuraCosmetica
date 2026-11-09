# Aura Cosmética

Aplicación Android de una tienda virtual de cosmética, desarrollada en Kotlin con Jetpack Compose como proyecto de la actividad de Profundización. 
He decidido crear esta tienda de cosmeticos porque llevo como 3 meses pensando en montar algun negocio que me genere otra fuente de ingreso y asi no depender 100% de mi empleo. Estaba pensando que hacer hasta que en esta asiognatura se me currio crear una tienda de consmeticos, tengo en mente llevarla a ser una tienda real, llevarla a produccion, he tratado de cuidar detaller en la elegancia y laa facilidad de uso. 
Nota: es para mi un reto todo esto de crear App ya que mi experciencia es en la Web, pero esto me ha retado, gracias por las clases. 


## Descripción

La app tiene tres roles de usuario (comprador, vendedor y administrador). Cada rol tiene su propia navegación inferior y sus pantallas, 26 en total.

## Funcionalidades

- Splash screen con el logo de la marca
- Ingreso por huella digital (BiometricPrompt)
- Recuperación de contraseña por correo
- Navegación inferior con pestañas según el rol
- **Comprador:** catálogo, detalle del producto, carrito, pago, historial de pedidos y perfil
- **Vendedor:** crear, ver, editar y eliminar productos, gestionar pedidos y perfil
- **Administrador:** crear, editar y desactivar usuarios, gestionar productos y ver reportes

## Tecnologías usadas

- Kotlin 2.0
- Jetpack Compose + Material 3
- Navigation Compose (navegación entre pantallas)
- AndroidX Biometric (huella digital)
- Gradle 8.9
- Recursos en res/values (strings, colors, themes), res/font y drawables

## Cómo ejecutar el proyecto

1. Clonar o descargar el proyecto
2. Abrirlo en Android Studio
3. Si el IDE pide el Gradle JVM, elegir "Use JVM 21"
4. Esperar la sincronización de Gradle y ejecutar en un emulador con Android 8.0 o superior
5. Para probar la huella: enrolar una en el emulador (Settings → Security → Fingerprint)

## Autor

- Diego Armando Alba Quintero
- UCompensar
- Docente: Favio Alexander Navarro Carmona
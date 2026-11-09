package com.aura.cosmetica.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aura.cosmetica.ui.components.NavItem
import com.aura.cosmetica.ui.screens.PantallaAdminCompras
import com.aura.cosmetica.ui.screens.PantallaAdminPanel
import com.aura.cosmetica.ui.screens.PantallaAdminPerfil
import com.aura.cosmetica.ui.screens.PantallaAdminProductos
import com.aura.cosmetica.ui.screens.PantallaAdminReportes
import com.aura.cosmetica.ui.screens.PantallaAdminUsuarios
import com.aura.cosmetica.ui.screens.PantallaAdminCrearUsuario
import com.aura.cosmetica.ui.screens.PantallaAdminEditarUsuario
import com.aura.cosmetica.ui.screens.PantallaCarrito
import com.aura.cosmetica.ui.screens.PantallaCatalogo
import com.aura.cosmetica.ui.screens.PantallaConfirmacion
import com.aura.cosmetica.ui.screens.PantallaDetalle
import com.aura.cosmetica.ui.screens.PantallaDireccion
import com.aura.cosmetica.ui.screens.PantallaInicio
import com.aura.cosmetica.ui.screens.PantallaPago
import com.aura.cosmetica.ui.screens.PantallaPedidos
import com.aura.cosmetica.ui.screens.PantallaPerfil
import com.aura.cosmetica.ui.screens.PantallaRecuperar
import com.aura.cosmetica.ui.screens.PantallaRegistro
import com.aura.cosmetica.ui.screens.PantallaSplash
import com.aura.cosmetica.ui.screens.PantallaVendedorCamara
import com.aura.cosmetica.ui.screens.PantallaVendedorCrear
import com.aura.cosmetica.ui.screens.PantallaVendedorEditar
import com.aura.cosmetica.ui.screens.PantallaVendedorPanel
import com.aura.cosmetica.ui.screens.PantallaVendedorPedidos
import com.aura.cosmetica.ui.screens.PantallaVendedorPerfil
import com.aura.cosmetica.ui.screens.PantallaVendedorProductos
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Storefront

object Rutas {
    // Transversal
    const val SPLASH = "splash"
    const val RECUPERAR = "recuperar"

    // Comprador
    const val INICIO = "inicio"
    const val CATALOGO = "catalogo"
    const val DETALLE = "detalle/{pid}"
    const val CARRITO = "carrito"
    const val PAGO = "pago"
    const val CONFIRMACION = "confirmacion"
    const val REGISTRO = "registro"
    const val DIRECCION = "direccion"
    const val PEDIDOS = "pedidos"
    const val PERFIL = "perfil"

    // Vendedor
    const val V_PANEL = "vendedor-panel"
    const val V_PRODUCTOS = "vendedor-productos"
    const val V_CREAR = "vendedor-crear"
    const val V_EDITAR = "vendedor-producto-editar/{pid}"
    const val V_CAMARA = "vendedor-camara"
    const val V_PEDIDOS = "vendedor-pedidos"
    const val V_PERFIL = "vendedor-perfil"

    // Administrador
    const val A_PANEL = "admin-panel"
    const val A_USUARIOS = "admin-usuarios"
    const val A_USUARIO_CREAR = "admin-usuario-crear"
    const val A_USUARIO_EDITAR = "admin-usuario-editar/{indice}"
    const val A_PRODUCTOS = "admin-productos"
    const val A_COMPRAS = "admin-compras"
    const val A_REPORTES = "admin-reportes"
    const val A_PERFIL = "admin-perfil"

    fun detalle(pid: Int) = "detalle/$pid"
    fun productoEditar(pid: Int) = "vendedor-producto-editar/$pid"
    fun usuarioEditar(indice: Int) = "admin-usuario-editar/$indice"
}

val NavCliente = listOf(
    NavItem(Icons.Filled.Home, "Inicio"),
    NavItem(Icons.Filled.Storefront, "Productos"),
    NavItem(Icons.Filled.ReceiptLong, "Pedidos"),
    NavItem(Icons.Filled.Person, "Perfil")
)

val NavVendedor = listOf(
    NavItem(Icons.Filled.Home, "Inicio"),
    NavItem(Icons.Filled.Inventory2, "Productos"),
    NavItem(Icons.Filled.ReceiptLong, "Pedidos"),
    NavItem(Icons.Filled.Person, "Perfil")
)

val NavAdmin = listOf(
    NavItem(Icons.Filled.Home, "Inicio"),
    NavItem(Icons.Filled.Groups, "Usuarios"),
    NavItem(Icons.Filled.Inventory2, "Productos"),
    NavItem(Icons.Filled.ReceiptLong, "Compras")
)

fun irTabCliente(nav: NavHostController, ruta: String) {
    nav.navigate(ruta) {
        popUpTo(Rutas.INICIO) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun irTabVendedor(nav: NavHostController, ruta: String) {
    nav.navigate(ruta) {
        popUpTo(Rutas.V_PANEL) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun irTabAdmin(nav: NavHostController, ruta: String) {
    nav.navigate(ruta) {
        popUpTo(Rutas.A_PANEL) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

/** Navega limpiando toda la pila (login / logout / volver al inicio). */
fun irLimpio(nav: NavHostController, ruta: String) {
    nav.navigate(ruta) {
        popUpTo(nav.graph.findStartDestination().id) { inclusive = true }
        launchSingleTop = true
    }
}

@Composable
fun AuraApp() {
    val nav = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        NavHost(
            navController = nav,
            startDestination = Rutas.SPLASH,
            modifier = Modifier.padding(padding)
        ) {
            // ----- Transversal (todos los roles) -----
            composable(Rutas.SPLASH) { PantallaSplash(nav) }
            composable(Rutas.RECUPERAR) { PantallaRecuperar(nav) }

            // ----- Comprador -----
            composable(Rutas.INICIO) { PantallaInicio(nav) }
            composable(Rutas.CATALOGO) { PantallaCatalogo(nav) }
            composable(
                Rutas.DETALLE,
                arguments = listOf(navArgument("pid") { type = NavType.IntType })
            ) { entrada ->
                PantallaDetalle(nav, entrada.arguments?.getInt("pid") ?: 1)
            }
            composable(Rutas.CARRITO) { PantallaCarrito(nav) }
            composable(Rutas.PAGO) { PantallaPago(nav) }
            composable(Rutas.CONFIRMACION) { PantallaConfirmacion(nav) }
            composable(Rutas.REGISTRO) { PantallaRegistro(nav) }
            composable(Rutas.DIRECCION) { PantallaDireccion(nav) }
            composable(Rutas.PEDIDOS) { PantallaPedidos(nav) }
            composable(Rutas.PERFIL) { PantallaPerfil(nav) }

            // ----- Vendedor -----
            composable(Rutas.V_PANEL) { PantallaVendedorPanel(nav) }
            composable(Rutas.V_PRODUCTOS) { PantallaVendedorProductos(nav) }
            composable(Rutas.V_CREAR) { PantallaVendedorCrear(nav) }
            composable(
                Rutas.V_EDITAR,
                arguments = listOf(navArgument("pid") { type = NavType.IntType })
            ) { entrada ->
                PantallaVendedorEditar(nav, entrada.arguments?.getInt("pid") ?: 0)
            }
            composable(Rutas.V_CAMARA) { PantallaVendedorCamara(nav) }
            composable(Rutas.V_PEDIDOS) { PantallaVendedorPedidos(nav) }
            composable(Rutas.V_PERFIL) { PantallaVendedorPerfil(nav) }

            // ----- Administrador -----
            composable(Rutas.A_PANEL) { PantallaAdminPanel(nav) }
            composable(Rutas.A_USUARIOS) { PantallaAdminUsuarios(nav) }
            composable(Rutas.A_USUARIO_CREAR) { PantallaAdminCrearUsuario(nav) }
            composable(
                Rutas.A_USUARIO_EDITAR,
                arguments = listOf(navArgument("indice") { type = NavType.IntType })
            ) { entrada ->
                PantallaAdminEditarUsuario(nav, entrada.arguments?.getInt("indice") ?: 0)
            }
            composable(Rutas.A_PRODUCTOS) { PantallaAdminProductos(nav) }
            composable(Rutas.A_COMPRAS) { PantallaAdminCompras(nav) }
            composable(Rutas.A_REPORTES) { PantallaAdminReportes(nav) }
            composable(Rutas.A_PERFIL) { PantallaAdminPerfil(nav) }
        }
    }
}

package com.aura.cosmetica.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.data.ProductoAdmin
import com.aura.cosmetica.data.Repo
import com.aura.cosmetica.data.UsuarioAdmin
import com.aura.cosmetica.data.cop
import com.aura.cosmetica.nav.NavAdmin
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irTabAdmin
import com.aura.cosmetica.ui.components.AvatarIniciales
import com.aura.cosmetica.ui.components.BarraBusqueda
import com.aura.cosmetica.ui.components.BarraInferior
import com.aura.cosmetica.ui.components.BotonMini
import com.aura.cosmetica.ui.components.BotonPrincipal
import com.aura.cosmetica.ui.components.BotonSecundario
import com.aura.cosmetica.ui.components.CabeceraLogo
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.CampoTexto
import com.aura.cosmetica.ui.components.ChipAccion
import com.aura.cosmetica.ui.components.ChipCategoria
import com.aura.cosmetica.ui.components.FilaOpcion
import com.aura.cosmetica.ui.components.FilaProducto
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.components.SeccionLabel
import com.aura.cosmetica.ui.components.TarjetaKpi
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Blush
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.ChampagneSuave
import com.aura.cosmetica.ui.theme.ChampagneOscuro
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.Dorado
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Tinta

private fun rutaDeAdmin(etiqueta: String): String = when (etiqueta) {
    "Usuarios" -> Rutas.A_USUARIOS
    "Productos" -> Rutas.A_PRODUCTOS
    "Compras" -> Rutas.A_COMPRAS
    else -> Rutas.A_PANEL
}

// ===========================================================================
// 17 · PANEL ADMINISTRADOR
// ===========================================================================

@Composable
fun PantallaAdminPanel(nav: NavHostController) {
    PantallaAura(
        barraInferior = {
            BarraInferior(NavAdmin, "Inicio") { etiqueta ->
                irTabAdmin(nav, rutaDeAdmin(etiqueta))
            }
        },
        cabecera = { CabeceraLogo(chip = "Rol · Administrador") }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                "PANEL DE ADMINISTRACIÓN",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.5.sp,
                color = Champagne
            )
            Spacer(Modifier.height(8.dp))
            Text(
                buildAnnotatedString {
                    append("Resumen ")
                    withStyle(SpanStyle(color = Ciruela, fontStyle = FontStyle.Italic)) {
                        append("general")
                    }
                    append(".")
                },
                fontFamily = Cormorant,
                fontWeight = FontWeight.SemiBold,
                fontSize = 29.sp,
                color = Tinta
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Gestión de usuarios, productos y compras.",
                fontFamily = Inter,
                fontSize = 13.sp,
                color = GrisCalido
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TarjetaKpi("120", "Usuarios", "3 roles activos", modificador = Modifier.weight(1f))
            TarjetaKpi("250", "Productos", "18 categorías", modificador = Modifier.weight(1f))
        }
        Spacer(Modifier.height(10.dp))
        Row(
            Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TarjetaKpi("430", "Compras", "+38 esta semana", Dorado, Modifier.weight(1f))
            TarjetaKpi("Oct", "Reportes", "$5.200.000 facturado", Dorado, Modifier.weight(1f))
        }

        Spacer(Modifier.height(20.dp))
        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                "GESTIÓN RÁPIDA",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.sp,
                color = GrisCalido
            )
            Spacer(Modifier.height(10.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FilaOpcion(Icons.Filled.Groups, "Gestión de usuarios") {
                    nav.navigate(Rutas.A_USUARIOS) { launchSingleTop = true }
                }
                FilaOpcion(Icons.Filled.Inventory2, "Gestión de productos") {
                    nav.navigate(Rutas.A_PRODUCTOS) { launchSingleTop = true }
                }
                FilaOpcion(Icons.Filled.ReceiptLong, "Gestión de compras") {
                    nav.navigate(Rutas.A_COMPRAS) { launchSingleTop = true }
                }
                FilaOpcion(Icons.Filled.BarChart, "Ver reportes de octubre") {
                    nav.navigate(Rutas.A_REPORTES) { launchSingleTop = true }
                }
            }
        }
    }
}

// ===========================================================================
// 18 · USUARIOS (ADMIN)
// ===========================================================================

private fun chipRolColores(rol: String): Pair<Color, Color> = when (rol) {
    "Vendedor" -> ChampagneSuave to ChampagneOscuro
    "Admin" -> Ciruela to Blanco
    else -> Blush to CiruelaOscuro
}

@Composable
fun PantallaAdminUsuarios(nav: NavHostController) {
    var busqueda by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("Todos") }

    fun cumpleFiltro(u: UsuarioAdmin): Boolean {
        val okFiltro = when (filtro) {
            "Compradoras" -> u.rol == "Compradora"
            "Vendedores" -> u.rol == "Vendedor"
            "Admins" -> u.rol == "Admin"
            else -> true
        }
        return okFiltro && (busqueda.isBlank() ||
                u.nombre.contains(busqueda, ignoreCase = true) ||
                u.correo.contains(busqueda, ignoreCase = true))
    }

    PantallaAura(
        barraInferior = {
            BarraInferior(NavAdmin, "Usuarios") { etiqueta ->
                irTabAdmin(nav, rutaDeAdmin(etiqueta))
            }
        },
        cabecera = {
            CabeceraTitulo("Usuarios", onVolver = { nav.popBackStack() }) {
                ChipAccion("+Nuevo") { nav.navigate(Rutas.A_USUARIO_CREAR) }
            }
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            BarraBusqueda(busqueda, { busqueda = it }, "Buscar usuario…")
        }

        Spacer(Modifier.height(12.dp))

        Row(
            Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Todos", "Compradoras", "Vendedores", "Admins").forEach { f ->
                ChipCategoria(f, filtro == f) { filtro = f }
            }
        }

        Spacer(Modifier.height(14.dp))

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Repo.usuariosAdmin.forEachIndexed { indice, u ->
                if (!cumpleFiltro(u)) return@forEachIndexed
                val (fondoChip, textoChip) = chipRolColores(u.rol)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Blanco)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AvatarIniciales(u.nombre, 42.dp)
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                u.nombre,
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = Tinta
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                u.rol,
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 9.sp,
                                color = textoChip,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(fondoChip)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                        Text(
                            "${u.correo} · ${u.detalle}",
                            fontFamily = Inter,
                            fontSize = 11.sp,
                            color = GrisCalido,
                            maxLines = 1,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                    IconoAdminMini(Icons.Filled.Edit, "Editar") {
                        nav.navigate(Rutas.usuarioEditar(indice))
                    }
                    if (!u.desactivado) {
                        Spacer(Modifier.width(4.dp))
                        IconoAdminMini(Icons.Filled.Close, "Desactivar") {
                            Repo.alternarUsuario(indice)
                        }
                    } else {
                        Spacer(Modifier.width(4.dp))
                        BotonMini("Reactivar", { Repo.alternarUsuario(indice) }, primario = false)
                    }
                }
            }
        }
    }
}

@Composable
private fun IconoAdminMini(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    descripcion: String,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(com.aura.cosmetica.ui.theme.MarfilOscuro)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icono, descripcion, tint = Ciruela, modifier = Modifier.size(15.dp))
    }
}

// ===========================================================================
// 19 · PRODUCTOS (ADMIN)
// ===========================================================================

@Composable
fun PantallaAdminProductos(nav: NavHostController) {
    var busqueda by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("Todos") }

    fun cumpleFiltroProducto(p: ProductoAdmin): Boolean {
        val okFiltro = when (filtro) {
            "Activos" -> !p.desactivado
            "Desactivados" -> p.desactivado
            else -> true
        }
        return okFiltro && (busqueda.isBlank() || p.nombre.contains(busqueda, ignoreCase = true))
    }

    PantallaAura(
        barraInferior = {
            BarraInferior(NavAdmin, "Productos") { etiqueta ->
                irTabAdmin(nav, rutaDeAdmin(etiqueta))
            }
        },
        cabecera = {
            CabeceraTitulo("Productos", onVolver = { nav.popBackStack() }) {
                ChipAccion("+Nuevo") { }
            }
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            BarraBusqueda(busqueda, { busqueda = it }, "Buscar producto…")
        }

        Spacer(Modifier.height(12.dp))

        Row(
            Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Todos", "Activos", "Desactivados").forEach { f ->
                ChipCategoria(f, filtro == f) { filtro = f }
            }
        }

        Spacer(Modifier.height(14.dp))

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Repo.productosAdmin.forEachIndexed { indice, p ->
                if (!cumpleFiltroProducto(p)) return@forEachIndexed
                FilaProducto(
                    imagen = p.imagen,
                    titulo = p.nombre,
                    subtitulo = "${p.categoria} · ${p.vendedor}",
                    precio = cop(p.precioCop),
                    badgeDorado = if (p.desactivado) "Desactivado" else null,
                    trailing = {
                        IconoAdminMini(
                            if (p.desactivado) Icons.Filled.Refresh else Icons.Filled.Close,
                            if (p.desactivado) "Reactivar" else "Desactivar"
                        ) { Repo.alternarProductoAdmin(indice) }
                    }
                )
            }
        }
    }
}

// ===========================================================================
// CRUD DE USUARIO (ADMIN) · RF-05 y RF-07
// Formulario compartido por "Crear usuario" y "Editar usuario".
// ===========================================================================

private val RolesUsuario = listOf("Compradora", "Vendedor", "Admin")

@Composable
private fun FormularioUsuario(
    nav: NavHostController,
    titulo: String,
    textoBoton: String,
    nombreInicial: String = "",
    correoInicial: String = "",
    telefonoInicial: String = "",
    rolInicial: String = "Compradora",
    accionSecundaria: Pair<String, () -> Unit>? = null,
    onGuardar: (nombre: String, correo: String, telefono: String, rol: String) -> Unit
) {
    var nombre by remember { mutableStateOf(nombreInicial) }
    var correo by remember { mutableStateOf(correoInicial) }
    var telefono by remember { mutableStateOf(telefonoInicial) }
    var rol by remember { mutableStateOf(rolInicial) }
    var confirmado by remember { mutableStateOf(false) }

    PantallaAura(
        barraInferior = {
            Column {
                BotonPrincipal(
                    texto = if (confirmado) "Volver a Usuarios" else textoBoton,
                    habilitado = confirmado || (nombre.isNotBlank() && correo.contains("@")),
                    modificador = Modifier.padding(horizontal = 20.dp),
                    onClick = {
                        if (confirmado) {
                            nav.popBackStack()
                        } else {
                            onGuardar(nombre.trim(), correo.trim(), telefono.trim(), rol)
                            confirmado = true
                        }
                    }
                )
                if (!confirmado && accionSecundaria != null) {
                    Spacer(Modifier.height(8.dp))
                    BotonSecundario(
                        accionSecundaria.first,
                        Modifier.padding(horizontal = 20.dp)
                    ) { accionSecundaria.second() }
                }
                Spacer(Modifier.height(10.dp))
            }
        },
        cabecera = { CabeceraTitulo(titulo, onVolver = { nav.popBackStack() }) }
    ) {
        if (confirmado) {
            Column(Modifier.padding(horizontal = 20.dp)) {
                Spacer(Modifier.height(20.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Blanco)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.CheckCircle, null,
                        tint = com.aura.cosmetica.ui.theme.VerdeExito,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            "Usuario guardado",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Tinta
                        )
                        Text(
                            "Los cambios se reflejan en el listado de usuarios.",
                            fontFamily = Inter,
                            fontSize = 12.sp,
                            color = GrisCalido
                        )
                    }
                }
            }
        } else {
            Column(Modifier.padding(horizontal = 20.dp)) {
                SeccionLabel("DATOS DE LA CUENTA")
                Spacer(Modifier.height(10.dp))
                CampoTexto(
                    nombre, { nombre = it }, "Nombre completo",
                    icono = Icons.Filled.Person
                )
                Spacer(Modifier.height(10.dp))
                CampoTexto(
                    correo, { correo = it }, "correo@ejemplo.com",
                    icono = Icons.Filled.MailOutline,
                    teclado = androidx.compose.ui.text.input.KeyboardType.Email
                )
                Spacer(Modifier.height(10.dp))
                CampoTexto(
                    telefono, { telefono = it }, "Teléfono · 300 000 0000",
                    icono = Icons.Filled.Phone,
                    teclado = androidx.compose.ui.text.input.KeyboardType.Phone
                )

                Spacer(Modifier.height(18.dp))
                SeccionLabel("ROL ASIGNADO")
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    RolesUsuario.forEach { r ->
                        ChipCategoria(r, rol == r) { rol = r }
                    }
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    "El rol define la interfaz y los permisos visibles · RF-03",
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    color = GrisCalido
                )
            }
        }
    }
}

@Composable
fun PantallaAdminCrearUsuario(nav: NavHostController) {
    FormularioUsuario(nav, "Nuevo usuario", "Crear usuario") { nombre, correo, telefono, rol ->
        Repo.crearUsuario(nombre, correo, telefono, rol)
    }
}

@Composable
fun PantallaAdminEditarUsuario(nav: NavHostController, indice: Int) {
    val usuario = Repo.usuariosAdmin.getOrNull(indice)
    if (usuario == null) {
        // Índice inválido (ej. lista modificada): regresar al listado.
        LaunchedEffect(Unit) { nav.popBackStack() }
        return
    }
    FormularioUsuario(
        nav = nav,
        titulo = "Editar usuario",
        textoBoton = "Guardar cambios",
        nombreInicial = usuario.nombre,
        correoInicial = usuario.correo,
        telefonoInicial = usuario.telefono,
        rolInicial = usuario.rol,
        accionSecundaria = if (usuario.desactivado) {
            "Reactivar cuenta" to { Repo.alternarUsuario(indice) }
        } else {
            "Desactivar cuenta" to { Repo.alternarUsuario(indice) }
        }
    ) { nombre, correo, telefono, rol ->
        Repo.actualizarUsuario(indice, nombre, correo, telefono, rol)
    }
}

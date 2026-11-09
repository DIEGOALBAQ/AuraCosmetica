package com.aura.cosmetica.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.data.EstadoPedido
import com.aura.cosmetica.data.Pedido
import com.aura.cosmetica.data.Repo
import com.aura.cosmetica.data.cop
import com.aura.cosmetica.nav.NavCliente
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irLimpio
import com.aura.cosmetica.nav.irTabCliente
import com.aura.cosmetica.ui.components.AvatarIniciales
import com.aura.cosmetica.ui.components.BarraBusqueda
import com.aura.cosmetica.ui.components.BarraInferior
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.ChipCategoria
import com.aura.cosmetica.ui.components.EtiquetaEstado
import com.aura.cosmetica.ui.components.FilaOpcion
import com.aura.cosmetica.ui.components.IconoBoton
import com.aura.cosmetica.ui.components.LinkTexto
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Borde
import com.aura.cosmetica.ui.theme.Blush
import com.aura.cosmetica.ui.theme.ChampagneSuave
import com.aura.cosmetica.ui.theme.ChampagneOscuro
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.MarfilOscuro
import com.aura.cosmetica.ui.theme.Tinta

// ===========================================================================
// 09 · MIS PEDIDOS
// ===========================================================================

@Composable
fun PantallaPedidos(nav: NavHostController) {
    var tab by remember { mutableStateOf("Todos") }
    var mostrarBusqueda by remember { mutableStateOf(false) }
    var busqueda by remember { mutableStateOf("") }

    val tabs = listOf("Todos", "En preparación", "Enviados", "Entregados")
    val lista = Repo.pedidosCliente.filter { p ->
        val okTab = tab == "Todos" || p.estado.etiqueta == tab
        val okBusqueda = busqueda.isBlank() ||
                p.codigo.contains(busqueda, ignoreCase = true) ||
                p.resumen.contains(busqueda, ignoreCase = true)
        okTab && okBusqueda
    }

    PantallaAura(
        barraInferior = {
            BarraInferior(NavCliente, "Pedidos") { etiqueta ->
                irTabCliente(
                    nav,
                    when (etiqueta) {
                        "Inicio" -> Rutas.INICIO
                        "Productos" -> Rutas.CATALOGO
                        "Perfil" -> Rutas.PERFIL
                        else -> Rutas.PEDIDOS
                    }
                )
            }
        },
        cabecera = {
            CabeceraTitulo("Mis pedidos") {
                IconoBoton(Icons.Filled.Search, "Buscar") { mostrarBusqueda = !mostrarBusqueda }
            }
        }
    ) {
        if (mostrarBusqueda) {
            Box(Modifier.padding(horizontal = 20.dp)) {
                BarraBusqueda(busqueda, { busqueda = it }, "Buscar por código o producto…")
            }
            Spacer(Modifier.height(12.dp))
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp)
        ) {
            tabs.forEach { t ->
                item(key = t) {
                    ChipCategoria(t, tab == t) { tab = t }
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            lista.forEach { pedido ->
                TarjetaPedidoCliente(pedido) {
                    if (pedido.estado == EstadoPedido.ENTREGADO) {
                        nav.navigate(Rutas.CATALOGO) { launchSingleTop = true }
                    }
                }
            }
            if (lista.isEmpty()) {
                Text(
                    "No hay pedidos que coincidan con tu búsqueda.",
                    fontFamily = Inter,
                    fontSize = 13.sp,
                    color = GrisCalido,
                    modifier = Modifier.padding(vertical = 30.dp)
                )
            }
        }
    }
}

@Composable
private fun TarjetaPedidoCliente(pedido: Pedido, onAccion: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Blanco)
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    pedido.codigo,
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Tinta
                )
                Text(
                    "${pedido.fecha} · ${pedido.items} ${if (pedido.items == 1) "producto" else "productos"}",
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    color = GrisCalido
                )
            }
            EtiquetaEstado(pedido.estado)
        }

        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            pedido.thumbs.take(2).forEachIndexed { i, img ->
                if (i > 0) Spacer(Modifier.width(6.dp))
                Image(
                    painter = painterResource(img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            if (pedido.items > 2) {
                Spacer(Modifier.width(6.dp))
                Box(
                    Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MarfilOscuro),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "+${pedido.items - 2}",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        color = CiruelaOscuro
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Text(
                cop(pedido.totalCop),
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Ciruela
            )
        }

        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.weight(1f))
            LinkTexto(
                if (pedido.estado == EstadoPedido.ENTREGADO) "Volver a comprar" else "Rastrear pedido"
            ) { onAccion() }
        }
    }
}

// ===========================================================================
// 10 · PERFIL DEL COMPRADOR
// ===========================================================================

@Composable
fun PantallaPerfil(nav: NavHostController) {
    PantallaAura(
        barraInferior = {
            BarraInferior(NavCliente, "Perfil") { etiqueta ->
                irTabCliente(
                    nav,
                    when (etiqueta) {
                        "Inicio" -> Rutas.INICIO
                        "Productos" -> Rutas.CATALOGO
                        "Pedidos" -> Rutas.PEDIDOS
                        else -> Rutas.PERFIL
                    }
                )
            }
        },
        cabecera = {
            CabeceraTitulo("Perfil") {
                IconoBoton(Icons.Filled.Settings, "Ajustes") { }
            }
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {

            // ---- Tarjeta de usuario ----
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Blanco)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AvatarIniciales("María López", 56.dp)
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(
                        "María López",
                        fontFamily = Cormorant,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        color = Tinta
                    )
                    Text(
                        "maria.lopez@email.com · Miembro desde 2024",
                        fontFamily = Inter,
                        fontSize = 12.sp,
                        color = GrisCalido
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // ---- Banner Aura Rewards ----
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(ChampagneSuave)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.WorkspacePremium, null, tint = ChampagneOscuro, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        "Aura Rewards · 240 puntos",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = ChampagneOscuro
                    )
                    Text(
                        "Te faltan 60 puntos para tu próxima recompensa",
                        fontFamily = Inter,
                        fontSize = 11.sp,
                        color = ChampagneOscuro.copy(alpha = 0.85f)
                    )
                }
                Text(
                    "Oro",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp,
                    color = Blanco,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(ChampagneOscuro)
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }

            Spacer(Modifier.height(18.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FilaOpcion(Icons.Filled.Person, "Mis datos personales")
                FilaOpcion(Icons.Filled.LocationOn, "Mis direcciones") {
                    nav.navigate(Rutas.DIRECCION) { launchSingleTop = true }
                }
                FilaOpcion(Icons.Filled.CreditCard, "Métodos de pago") {
                    nav.navigate(Rutas.PAGO) { launchSingleTop = true }
                }
                FilaOpcion(Icons.Filled.FavoriteBorder, "Mis favoritos", trailing = {
                    Text(
                        "12",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        color = CiruelaOscuro,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Blush)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                })
                FilaOpcion(Icons.Filled.HelpOutline, "Ayuda y soporte")
            }

            Spacer(Modifier.height(14.dp))
            FilaOpcion(
                Icons.Filled.Logout,
                "Cerrar sesión",
                tinte = Ciruela
            ) {
                irLimpio(nav, Rutas.REGISTRO)
            }
        }
    }
}

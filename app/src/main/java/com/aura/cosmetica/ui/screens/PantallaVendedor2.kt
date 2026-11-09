package com.aura.cosmetica.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlipCameraAndroid
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.R
import com.aura.cosmetica.data.EstadoPedido
import com.aura.cosmetica.data.Repo
import com.aura.cosmetica.nav.NavVendedor
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irLimpio
import com.aura.cosmetica.nav.irTabVendedor
import com.aura.cosmetica.ui.components.AvatarIniciales
import com.aura.cosmetica.ui.components.BarraInferior
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.ChipCategoria
import com.aura.cosmetica.ui.components.FilaOpcion
import com.aura.cosmetica.ui.components.FilaPedidoTienda
import com.aura.cosmetica.ui.components.IconoBoton
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Blush
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Tinta

// ===========================================================================
// 14 · CÁMARA DE PRODUCTO (pantalla oscura a pantalla completa)
// ===========================================================================

@Composable
fun PantallaVendedorCamara(nav: NavHostController) {
    Scaffold(containerColor = Color(0xFF141013)) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconoBoton(Icons.Filled.Close, "Cerrar", tinte = Blanco) { nav.popBackStack() }
                Spacer(Modifier.weight(1f))
                Text(
                    "TOMAR FOTO",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    letterSpacing = 3.sp,
                    color = Blanco
                )
                Spacer(Modifier.weight(1f))
                IconoBoton(Icons.Filled.FlipCameraAndroid, "Invertir", tinte = Blanco) { }
            }

            Spacer(Modifier.weight(1f))

            // ---- Visor ----
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.52f)
                    .clip(RoundedCornerShape(24.dp))
                    .border(2.dp, Champagne, RoundedCornerShape(24.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.img_shampoo),
                    contentDescription = "Visor de cámara",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    "MODO PRODUCTO",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.sp,
                    letterSpacing = 2.sp,
                    color = Color(0xFF141013),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 14.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Champagne)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            Spacer(Modifier.height(16.dp))
            Text(
                "Centra el producto sobre un fondo limpio y con buena luz.",
                fontFamily = Inter,
                fontSize = 12.sp,
                color = Blanco.copy(alpha = 0.65f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))

            // ---- Botonera ----
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_aceite),
                    contentDescription = "Última foto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, Blanco.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                )
                Spacer(Modifier.weight(1f))
                Box(
                    Modifier
                        .size(68.dp)
                        .clip(CircleShape)
                        .border(4.dp, Blanco, CircleShape)
                        .padding(6.dp)
                        .clip(CircleShape)
                        .background(Blanco)
                        .clickable { nav.popBackStack() }
                )
                Spacer(Modifier.weight(1f))
                IconoBoton(Icons.Filled.FlipCameraAndroid, "Invertir cámara", tinte = Blanco) { }
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}

// ===========================================================================
// 15 · PEDIDOS RECIBIDOS (VENDEDOR)
// ===========================================================================

@Composable
fun PantallaVendedorPedidos(nav: NavHostController) {
    var tab by remember { mutableStateOf("Pendientes") }

    val estadoDelTab = when (tab) {
        "En preparación" -> EstadoPedido.EN_PREPARACION
        "Enviados" -> EstadoPedido.ENVIADO
        else -> EstadoPedido.PENDIENTE
    }
    val pendientes = Repo.pedidosTienda.count { it.estado == EstadoPedido.PENDIENTE }

    PantallaAura(
        barraInferior = {
            BarraInferior(NavVendedor, "Pedidos") { etiqueta ->
                irTabVendedor(nav, rutaVendedorDe(etiqueta))
            }
        },
        cabecera = {
            CabeceraTitulo("Pedidos recibidos", onVolver = { nav.popBackStack() })
        }
    ) {
        Row(
            Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChipCategoria("Pendientes · $pendientes", tab == "Pendientes") { tab = "Pendientes" }
            ChipCategoria("En preparación", tab == "En preparación") { tab = "En preparación" }
            ChipCategoria("Enviados", tab == "Enviados") { tab = "Enviados" }
        }

        Spacer(Modifier.height(14.dp))

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Repo.pedidosTienda.forEachIndexed { indice, pedido ->
                if (pedido.estado == estadoDelTab) {
                    FilaPedidoTienda(
                        p = pedido,
                        onAccion = { Repo.avanzarPedido(indice) }
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                "Estado del pedido #1001",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = Tinta
            )
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                PasoEstado("Recibido", activo = true)
                LineaEstado(Champagne)
                PasoEstado("Preparación", activo = false)
                LineaEstado(com.aura.cosmetica.ui.theme.MarfilOscuro)
                PasoEstado("Enviado", activo = false)
            }
        }
    }
}

private fun rutaVendedorDe(etiqueta: String): String = when (etiqueta) {
    "Productos" -> Rutas.V_PRODUCTOS
    "Pedidos" -> Rutas.V_PEDIDOS
    "Perfil" -> Rutas.V_PERFIL
    else -> Rutas.V_PANEL
}

@Composable
private fun PasoEstado(texto: String, activo: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(if (activo) Ciruela else com.aura.cosmetica.ui.theme.MarfilOscuro)
        )
        Spacer(Modifier.height(5.dp))
        Text(
            texto,
            fontFamily = Inter,
            fontWeight = if (activo) FontWeight.SemiBold else FontWeight.Medium,
            fontSize = 10.sp,
            color = if (activo) Ciruela else GrisCalido
        )
    }
}

@Composable
private fun LineaEstado(color: Color) {
    Box(
        Modifier
            .width(46.dp)
            .height(2.dp)
            .background(color)
    )
}

// ===========================================================================
// 16 · PERFIL VENDEDOR
// ===========================================================================

@Composable
fun PantallaVendedorPerfil(nav: NavHostController) {
    PantallaAura(
        barraInferior = {
            BarraInferior(NavVendedor, "Perfil") { etiqueta ->
                irTabVendedor(nav, rutaVendedorDe(etiqueta))
            }
        },
        cabecera = {
            CabeceraTitulo("Perfil", onVolver = { nav.popBackStack() })
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Blanco)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AvatarIniciales("Juan Pérez", 56.dp)
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(
                        "Juan Pérez",
                        fontFamily = Cormorant,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        color = Tinta
                    )
                    Text(
                        "vendedor@gmail.com",
                        fontFamily = Inter,
                        fontSize = 12.sp,
                        color = GrisCalido
                    )
                    Spacer(Modifier.height(6.dp))
                    com.aura.cosmetica.ui.components.ChipRolAura("Rol · Vendedor")
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Blush)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Storefront, null, tint = Ciruela, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        "Aura · Punto de venta Chapinero",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = CiruelaOscuro
                    )
                    Text(
                        "Tienda verificada desde marzo 2025",
                        fontFamily = Inter,
                        fontSize = 11.sp,
                        color = CiruelaOscuro.copy(alpha = 0.75f)
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FilaOpcion(Icons.Filled.Person, "Mis datos")
                FilaOpcion(Icons.Filled.Storefront, "Mi tienda")
                FilaOpcion(Icons.Filled.Lock, "Cambiar contraseña")
                FilaOpcion(Icons.Filled.Paid, "Ventas y comisiones")
            }

            Spacer(Modifier.height(14.dp))
            FilaOpcion(Icons.Filled.Logout, "Cerrar sesión") {
                irLimpio(nav, Rutas.REGISTRO)
            }
        }
    }
}

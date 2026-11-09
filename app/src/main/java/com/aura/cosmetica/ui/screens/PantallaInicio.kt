package com.aura.cosmetica.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.R
import com.aura.cosmetica.data.Repo
import com.aura.cosmetica.nav.NavCliente
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irTabCliente
import com.aura.cosmetica.ui.components.BarraBusqueda
import com.aura.cosmetica.ui.components.BarraInferior
import com.aura.cosmetica.ui.components.CabeceraLogo
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.ChipCategoria
import com.aura.cosmetica.ui.components.IconoCarrito
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.components.SeccionTitulo
import com.aura.cosmetica.ui.components.TarjetaProducto
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Marfil
import com.aura.cosmetica.ui.theme.Tinta

// ===========================================================================
// 01 · INICIO
// ===========================================================================

@Composable
fun PantallaInicio(nav: NavHostController) {
    PantallaAura(
        barraInferior = {
            BarraInferior(NavCliente, "Inicio") { etiqueta ->
                irTabCliente(nav, rutaDeCliente(etiqueta))
            }
        },
        cabecera = {
            CabeceraLogo(
                unidades = Repo.unidadesCarrito,
                onBuscar = { nav.navigate(Rutas.CATALOGO) },
                onCarrito = { nav.navigate(Rutas.CARRITO) }
            )
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                "AURA COSMÉTICA",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.5.sp,
                color = Champagne
            )
            Spacer(Modifier.height(8.dp))
            Text(
                buildAnnotatedString {
                    append("Belleza que ")
                    withStyle(SpanStyle(color = Ciruela, fontStyle = FontStyle.Italic)) {
                        append("inspira")
                    }
                    append(", elegancia que perdura.")
                },
                fontFamily = Cormorant,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                lineHeight = 35.sp,
                color = Tinta
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Tu ritual de belleza, en un solo lugar.",
                fontFamily = Inter,
                fontSize = 13.sp,
                color = GrisCalido
            )
            Spacer(Modifier.height(14.dp))
            BarraBusqueda(
                valor = "",
                onValor = { },
                pista = "Buscar productos, marcas y más…",
                soloLectura = true,
                onClick = { nav.navigate(Rutas.CATALOGO) }
            )
        }

        Spacer(Modifier.height(18.dp))

        // ---- Hero editorial ----
        Box(
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.img_hero),
                contentDescription = "Nueva colección",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, CiruelaOscuro.copy(alpha = 0.82f))
                        )
                    )
            )
            Column(
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(18.dp)
            ) {
                Text(
                    "NUEVA COLECCIÓN",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.sp,
                    letterSpacing = 2.5.sp,
                    color = Champagne
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Ritual Radiance",
                    fontFamily = Cormorant,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 27.sp,
                    color = Blanco
                )
                Text(
                    "Iluminación dorada para tu piel, cada día.",
                    fontFamily = Inter,
                    fontSize = 12.sp,
                    color = Blanco.copy(alpha = 0.85f)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "Descubrir →",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = CiruelaOscuro,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Blanco)
                        .clickable { nav.navigate(Rutas.CATALOGO) }
                        .padding(horizontal = 16.dp, vertical = 9.dp)
                )
            }
        }

        SeccionTitulo("Destacados", "Ver todo") { nav.navigate(Rutas.CATALOGO) }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp)
        ) {
            Repo.productos.take(3).forEach { p ->
                item(key = p.id) {
                    TarjetaProducto(
                        p = p,
                        onClick = { nav.navigate(Rutas.detalle(p.id)) },
                        onAgregar = { Repo.agregar(p.id) },
                        modificador = Modifier.width(185.dp)
                    )
                }
            }
        }

        SeccionTitulo("Completa tu ritual")

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Repo.productos.drop(3).take(4).chunked(2).forEach { fila ->
                Row {
                    fila.forEachIndexed { i, p ->
                        if (i > 0) Spacer(Modifier.width(12.dp))
                        TarjetaProducto(
                            p = p,
                            onClick = { nav.navigate(Rutas.detalle(p.id)) },
                            onAgregar = { Repo.agregar(p.id) },
                            modificador = Modifier.weight(1f)
                        )
                    }
                    if (fila.size == 1) Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

private fun rutaDeCliente(etiqueta: String): String = when (etiqueta) {
    "Productos" -> Rutas.CATALOGO
    "Pedidos" -> Rutas.PEDIDOS
    "Perfil" -> Rutas.PERFIL
    else -> Rutas.INICIO
}

// ===========================================================================
// 02 · CATÁLOGO
// ===========================================================================

@Composable
fun PantallaCatalogo(nav: NavHostController) {
    var filtro by remember { mutableStateOf("Todo") }
    var busqueda by remember { mutableStateOf("") }
    var ordenar by remember { mutableStateOf(false) }

    val filtrados = Repo.productos
        .filter { (filtro == "Todo" || it.categoria == filtro) &&
                (busqueda.isBlank() || it.nombre.contains(busqueda, ignoreCase = true)) }
        .let { if (ordenar) it.sortedBy { p -> p.precioCop } else it }

    PantallaAura(
        barraInferior = {
            BarraInferior(NavCliente, "Productos") { etiqueta ->
                irTabCliente(nav, rutaDeCliente(etiqueta))
            }
        },
        cabecera = {
            CabeceraTitulo("Catálogo") {
                IconoCarrito(Repo.unidadesCarrito) { nav.navigate(Rutas.CARRITO) }
            }
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            BarraBusqueda(busqueda, { busqueda = it }, "Buscar productos…")
        }

        Spacer(Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp)
        ) {
            Repo.categorias.forEach { c ->
                item(key = c) {
                    ChipCategoria(c, c == filtro) { filtro = c }
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        Row(
            Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (filtro == "Todo" && busqueda.isBlank()) "248 productos"
                else "${filtrados.size} productos",
                fontFamily = Inter,
                fontSize = 12.sp,
                color = GrisCalido,
                modifier = Modifier.weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Ciruela)
                    .padding(horizontal = 12.dp, vertical = 7.dp)
            ) {
                Text(
                    "Filtrar",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Blanco
                )
                Spacer(Modifier.width(6.dp))
                Box(
                    Modifier
                        .size(18.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Champagne),
                    contentAlignment = Alignment.Center
                ) {
                    Text("2", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = CiruelaOscuro)
                }
            }
            Spacer(Modifier.width(12.dp))
            Text(
                if (ordenar) "Precio ↑" else "Ordenar",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Ciruela,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Blanco)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }

        Spacer(Modifier.height(14.dp))

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            filtrados.chunked(2).forEach { fila ->
                Row {
                    fila.forEachIndexed { i, p ->
                        if (i > 0) Spacer(Modifier.width(12.dp))
                        TarjetaProducto(
                            p = p,
                            onClick = { nav.navigate(Rutas.detalle(p.id)) },
                            onAgregar = { Repo.agregar(p.id) },
                            mostrarCorazon = true,
                            modificador = Modifier.weight(1f)
                        )
                    }
                    if (fila.size == 1) Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

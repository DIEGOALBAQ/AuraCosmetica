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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.data.ItemCarrito
import com.aura.cosmetica.data.Repo
import com.aura.cosmetica.data.cop
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.ui.components.BotonPrincipal
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.ChipCategoria
import com.aura.cosmetica.ui.components.IconoBoton
import com.aura.cosmetica.ui.components.LinkTexto
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.components.SeccionLabel
import com.aura.cosmetica.ui.components.Stepper
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Borde
import com.aura.cosmetica.ui.theme.Blush
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.Dorado
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Marfil
import com.aura.cosmetica.ui.theme.MarfilOscuro
import com.aura.cosmetica.ui.theme.Tinta
import com.aura.cosmetica.ui.theme.VerdeExito

// ===========================================================================
// 03 · DETALLE DE PRODUCTO
// ===========================================================================

@Composable
fun PantallaDetalle(nav: NavHostController, pid: Int) {
    val p = Repo.productos.firstOrNull { it.id == pid } ?: Repo.productos[0]
    var favorito by remember { mutableStateOf(pid == 2) }
    var tamanoExtra by remember { mutableStateOf(0) }
    val precioTotal = p.precioCop + tamanoExtra

    Scaffold(containerColor = Marfil, bottomBar = {
        BotonPrincipal(
            texto = "Agregar al carrito · ${cop(precioTotal)}",
            icono = Icons.Filled.ShoppingBag,
            modificador = Modifier.padding(horizontal = 20.dp).navigationBarsPadding(),
            onClick = {
                Repo.agregar(p.id)
                nav.navigate(Rutas.CARRITO) { launchSingleTop = true }
            }
        )
    }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconoBoton(Icons.Filled.ArrowBackIosNew, "Volver") { nav.popBackStack() }
                Spacer(Modifier.weight(1f))
                IconoBoton(
                    if (favorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    "Favorito"
                ) { favorito = !favorito }
            }

            Box(
                Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(310.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Blanco)
            ) {
                Image(
                    painter = painterResource(p.imagen),
                    contentDescription = p.nombre,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    Modifier
                        .width(22.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Ciruela)
                )
                Spacer(Modifier.width(6.dp))
                repeat(2) {
                    Box(
                        Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(MarfilOscuro)
                    )
                    Spacer(Modifier.width(6.dp))
                }
            }

            Column(Modifier.padding(horizontal = 20.dp)) {
                Spacer(Modifier.height(18.dp))
                Text(
                    p.detalle.uppercase(),
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    letterSpacing = 2.sp,
                    color = Champagne
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    p.nombre,
                    fontFamily = Cormorant,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 31.sp,
                    color = Tinta
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    p.subtitulo,
                    fontFamily = Inter,
                    fontSize = 13.sp,
                    color = GrisCalido
                )
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(Icons.Filled.Star, null, tint = Dorado, modifier = Modifier.size(14.dp))
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "${p.valoracion} (${p.resenas} reseñas)",
                        fontFamily = Inter,
                        fontSize = 12.sp,
                        color = GrisCalido
                    )
                }
                Spacer(Modifier.height(14.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        cop(precioTotal),
                        fontFamily = Cormorant,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 33.sp,
                        color = CiruelaOscuro
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Impuestos incluidos",
                        fontFamily = Inter,
                        fontSize = 11.sp,
                        color = GrisCalido,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                if (p.id == 1) {
                    Spacer(Modifier.height(16.dp))
                    SeccionLabel("TAMAÑO")
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ChipCategoria("30 ml", tamanoExtra == 0) { tamanoExtra = 0 }
                        ChipCategoria("50 ml · +${cop(35000)}", tamanoExtra == 35000) {
                            tamanoExtra = 35000
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))
                Text(
                    p.descripcion,
                    fontFamily = Inter,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = Tinta.copy(alpha = 0.85f)
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

// ===========================================================================
// 04 · CARRITO
// ===========================================================================

@Composable
fun PantallaCarrito(nav: NavHostController) {
    PantallaAura(
        barraInferior = {
            if (Repo.carrito.isNotEmpty()) {
                BotonPrincipal(
                    texto = "Proceder al pago",
                    modificador = Modifier.padding(horizontal = 20.dp),
                    onClick = { nav.navigate(Rutas.PAGO) { launchSingleTop = true } }
                )
            }
        },
        cabecera = {
            CabeceraTitulo("Carrito · ${Repo.unidadesCarrito} productos", onVolver = { nav.popBackStack() }) {
                LinkTexto("Vaciar") { Repo.vaciar() }
            }
        }
    ) {
        if (Repo.carrito.isEmpty()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Filled.ShoppingBag, null,
                    tint = MarfilOscuro, modifier = Modifier.size(46.dp)
                )
                Spacer(Modifier.height(14.dp))
                Text(
                    "Tu carrito está vacío",
                    fontFamily = Cormorant,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Tinta,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Explora el catálogo y encuentra tu ritual perfecto.",
                    fontFamily = Inter,
                    fontSize = 13.sp,
                    color = GrisCalido,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(20.dp))
                BotonPrincipal("Explorar catálogo", modificador = Modifier) {
                    nav.navigate(Rutas.CATALOGO) { launchSingleTop = true }
                }
            }
        } else {
            Column(Modifier.padding(horizontal = 20.dp)) {

                // ---- Banner envío gratis ----
                val progreso = (Repo.subtotalCarrito.toFloat() / Repo.UMBRAL_ENVIO)
                    .coerceIn(0f, 1f)
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Blush)
                        .padding(14.dp)
                ) {
                    Column {
                        Text(
                            if (progreso >= 1f) "¡Tienes envío gratis en este pedido!"
                            else buildStringFaltante(Repo.UMBRAL_ENVIO - Repo.subtotalCarrito),
                            fontFamily = Inter,
                            fontSize = 12.sp,
                            color = CiruelaOscuro
                        )
                        Spacer(Modifier.height(8.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Blanco)
                        ) {
                            Box(
                                Modifier
                                    .fillMaxWidth(progreso)
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(Champagne)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(14.dp))

                Repo.carrito.forEachIndexed { indice, item ->
                    FilaCarrito(
                        item = item,
                        onMenos = { Repo.cambiarCantidad(indice, -1) },
                        onMas = { Repo.cambiarCantidad(indice, 1) },
                        onEliminar = { Repo.eliminar(indice) }
                    )
                    Spacer(Modifier.height(10.dp))
                }

                Spacer(Modifier.height(6.dp))

                // ---- Cupón ----
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Blush)
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Código de descuento",
                        fontFamily = Inter,
                        fontSize = 13.sp,
                        color = GrisCalido,
                        modifier = Modifier.weight(1f)
                    )
                    LinkTexto("Aplicar") { }
                }

                Spacer(Modifier.height(14.dp))

                // ---- Resumen ----
                Column(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Blanco)
                        .padding(18.dp)
                ) {
                    FilaResumen("Subtotal", cop(Repo.subtotalCarrito), Tinta)
                    Spacer(Modifier.height(8.dp))
                    FilaResumen(
                        "Envío",
                        if (Repo.costoEnvio == 0) "Gratis" else cop(Repo.costoEnvio),
                        if (Repo.costoEnvio == 0) VerdeExito else Tinta
                    )
                    Spacer(Modifier.height(12.dp))
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Borde)
                    )
                    Spacer(Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Total",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Tinta,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            cop(Repo.totalCarrito),
                            fontFamily = Cormorant,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = CiruelaOscuro
                        )
                    }
                }

                Spacer(Modifier.height(12.dp))
                Text(
                    "Pago protegido · Entrega estimada 2–4 días hábiles",
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    color = GrisCalido,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

private fun buildStringFaltante(faltan: Int): String =
    "Te faltan ${cop(faltan)} para obtener envío gratis"

@Composable
private fun FilaResumen(etiqueta: String, valor: String, colorValor: androidx.compose.ui.graphics.Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            etiqueta,
            fontFamily = Inter,
            fontSize = 13.sp,
            color = GrisCalido,
            modifier = Modifier.weight(1f)
        )
        Text(
            valor,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = colorValor
        )
    }
}

@Composable
private fun FilaCarrito(
    item: ItemCarrito,
    onMenos: () -> Unit,
    onMas: () -> Unit,
    onEliminar: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Blanco)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(item.producto.imagen),
            contentDescription = item.producto.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                item.producto.nombre,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Tinta
            )
            Text(
                item.producto.detalle.uppercase(),
                fontFamily = Inter,
                fontSize = 9.5.sp,
                letterSpacing = 1.sp,
                color = GrisCalido
            )
            Spacer(Modifier.height(8.dp))
            Stepper(item.cantidad, onMenos, onMas)
        }
        Spacer(Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.End) {
            Text(
                cop(item.total),
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Ciruela
            )
            Spacer(Modifier.height(8.dp))
            Icon(
                Icons.Filled.Delete, "Eliminar",
                tint = GrisCalido,
                modifier = Modifier
                    .size(18.dp)
                    .clickable(onClick = onEliminar)
            )
        }
    }
}

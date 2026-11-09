package com.aura.cosmetica.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aura.cosmetica.data.EstadoPedido
import com.aura.cosmetica.data.PedidoTienda
import com.aura.cosmetica.data.Producto
import com.aura.cosmetica.data.accionPara
import com.aura.cosmetica.data.cop
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Borde
import com.aura.cosmetica.ui.theme.Blush
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.ChampagneSuave
import com.aura.cosmetica.ui.theme.ChampagneOscuro
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Dorado
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.MarfilOscuro
import com.aura.cosmetica.ui.theme.Tinta

// ---------------------------------------------------------------------------
// Barra de navegación inferior
// ---------------------------------------------------------------------------

data class NavItem(val icono: androidx.compose.ui.graphics.vector.ImageVector, val etiqueta: String)

@Composable
fun BarraInferior(items: List<NavItem>, actual: String, onNavegar: (String) -> Unit) {
    NavigationBar(containerColor = Blanco, tonalElevation = 0.dp) {
        items.forEach { item ->
            val seleccionado = item.etiqueta == actual
            NavigationBarItem(
                selected = seleccionado,
                onClick = { onNavegar(item.etiqueta) },
                icon = {
                    Icon(item.icono, contentDescription = item.etiqueta, modifier = Modifier.size(23.dp))
                },
                label = {
                    Text(
                        item.etiqueta,
                        fontFamily = Inter,
                        fontSize = 10.sp,
                        fontWeight = if (seleccionado) FontWeight.SemiBold else FontWeight.Medium
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Ciruela,
                    selectedTextColor = Ciruela,
                    unselectedIconColor = GrisCalido,
                    unselectedTextColor = GrisCalido,
                    indicatorColor = Blush
                )
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Estados de pedido
// ---------------------------------------------------------------------------

@Composable
fun EtiquetaEstado(estado: EstadoPedido) {
    val (fondo, texto) = when (estado) {
        EstadoPedido.PENDIENTE -> Blush to Ciruela
        EstadoPedido.EN_PREPARACION -> ChampagneSuave to ChampagneOscuro
        EstadoPedido.ENVIADO -> com.aura.cosmetica.ui.theme.AzulSuave to com.aura.cosmetica.ui.theme.AzulOscuro
        EstadoPedido.ENTREGADO -> com.aura.cosmetica.ui.theme.VerdeFondo to com.aura.cosmetica.ui.theme.VerdeExito
    }
    Text(
        estado.etiqueta,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        color = texto,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(fondo)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    )
}

// ---------------------------------------------------------------------------
// Tarjeta de producto (vertical, para grids y carruseles)
// ---------------------------------------------------------------------------

@Composable
fun TarjetaProducto(
    p: Producto,
    onClick: () -> Unit,
    onAgregar: () -> Unit,
    mostrarCorazon: Boolean = false,
    modificador: Modifier = Modifier
) {
    Column(
        modifier = modificador
            .clip(RoundedCornerShape(20.dp))
            .background(Blanco)
            .clickable(onClick = onClick)
    ) {
        Box {
            Image(
                painter = painterResource(p.imagen),
                contentDescription = p.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            if (p.etiqueta != null) {
                Text(
                    p.etiqueta.uppercase(),
                    fontFamily = Inter,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.8.sp,
                    color = CiruelaOscuro,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(50))
                        .background(ChampagneSuave)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            if (mostrarCorazon) {
                Icon(
                    if (p.id == 2) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    null,
                    tint = Ciruela,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(18.dp)
                )
            }
        }
        Column(Modifier.padding(14.dp)) {
            Text(
                p.nombre,
                fontFamily = com.aura.cosmetica.ui.theme.Cormorant,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                lineHeight = 20.sp,
                color = Tinta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(
                p.detalle,
                fontFamily = Inter,
                fontSize = 11.sp,
                color = GrisCalido,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(
                        cop(p.precioCop),
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Ciruela
                    )
                    Spacer(Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Star, null, tint = Dorado, modifier = Modifier.size(12.dp))
                        Spacer(Modifier.width(3.dp))
                        Text(
                            "${p.valoracion} (${p.resenas})",
                            fontFamily = Inter,
                            fontSize = 10.sp,
                            color = GrisCalido
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Ciruela)
                        .clickable(onClick = onAgregar),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Add, "Agregar", tint = Blanco, modifier = Modifier.size(17.dp))
                }
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Fila de producto (listas horizontales compactas)
// ---------------------------------------------------------------------------

@Composable
fun FilaProducto(
    imagen: Int,
    titulo: String,
    subtitulo: String,
    precio: String,
    onClick: (() -> Unit)? = null,
    badgeDorado: String? = null,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Blanco)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(imagen),
            contentDescription = titulo,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                titulo,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Tinta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(
                subtitulo,
                fontFamily = Inter,
                fontSize = 11.sp,
                color = GrisCalido,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (badgeDorado != null) {
                Spacer(Modifier.height(4.dp))
                Text(
                    badgeDorado,
                    fontFamily = Inter,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ChampagneOscuro
                )
            }
        }
        if (trailing != null) trailing() else {
            Text(
                precio,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Ciruela
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Stepper de cantidad
// ---------------------------------------------------------------------------

@Composable
fun Stepper(cantidad: Int, onMenos: () -> Unit, onMas: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(MarfilOscuro)
    ) {
        PuntoStepper(Icons.Filled.Remove, onMenos)
        Text(
            "$cantidad",
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Tinta,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        PuntoStepper(Icons.Filled.Add, onMas)
    }
}

@Composable
private fun PuntoStepper(icono: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Box(
        Modifier
            .size(32.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icono, null, tint = Ciruela, modifier = Modifier.size(16.dp))
    }
}

// ---------------------------------------------------------------------------
// Pedidos de la tienda (vendedor)
// ---------------------------------------------------------------------------

@Composable
fun FilaPedidoTienda(
    p: PedidoTienda,
    onAccion: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Blanco)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "${p.codigo} · ${p.cliente}",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = Tinta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            EtiquetaEstado(p.estado)
        }
        Spacer(Modifier.height(3.dp))
        Text(p.meta, fontFamily = Inter, fontSize = 11.sp, color = GrisCalido)
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                cop(p.totalCop),
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Ciruela,
                modifier = Modifier.weight(1f)
            )
            if (onAccion != null && p.estado != EstadoPedido.ENTREGADO) {
                BotonMini(
                    texto = accionPara(p.estado),
                    onClick = onAccion,
                    primario = p.estado != EstadoPedido.ENVIADO
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// KPI y gráfico de barras
// ---------------------------------------------------------------------------

@Composable
fun TarjetaKpi(
    valor: String,
    etiqueta: String,
    detalle: String? = null,
    detalleColor: Color = GrisCalido,
    modificador: Modifier = Modifier
) {
    Column(
        modificador
            .clip(RoundedCornerShape(18.dp))
            .background(Blanco)
            .padding(horizontal = 14.dp, vertical = 13.dp)
    ) {
        Text(
            valor,
            fontFamily = com.aura.cosmetica.ui.theme.Cinzel,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Ciruela,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(4.dp))
        Text(
            etiqueta,
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            color = Tinta,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (detalle != null) {
            Spacer(Modifier.height(2.dp))
            Text(
                detalle,
                fontFamily = Inter,
                fontSize = 10.sp,
                color = detalleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun GraficoBarras(
    valores: List<Float>,
    etiquetas: List<String>,
    colores: List<Color>? = null,
    altura: Dp = 130.dp,
    modificador: Modifier = Modifier
) {
    val maximo = (valores.maxOrNull() ?: 1f).coerceAtLeast(0.001f)
    Row(
        modificador
            .fillMaxWidth()
            .height(altura),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        valores.forEachIndexed { i, v ->
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(((altura - 20.dp) * (v / maximo)).coerceAtLeast(6.dp))
                        .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                        .background(colores?.getOrElse(i) { Ciruela } ?: Ciruela)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    etiquetas.getOrElse(i) { "" },
                    fontFamily = Inter,
                    fontSize = 9.sp,
                    color = GrisCalido,
                    maxLines = 1
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Fila de opciones (perfiles y menús)
// ---------------------------------------------------------------------------

@Composable
fun FilaOpcion(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    titulo: String,
    detalle: String? = null,
    tinte: Color = Ciruela,
    trailing: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Blanco)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icono, null, tint = tinte, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                titulo,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Tinta
            )
            if (detalle != null) {
                Spacer(Modifier.height(1.dp))
                Text(detalle, fontFamily = Inter, fontSize = 11.sp, color = GrisCalido)
            }
        }
        trailing?.invoke() ?: Icon(
            Icons.Filled.ChevronRight,
            null,
            tint = GrisCalido,
            modifier = Modifier.size(18.dp)
        )
    }
}

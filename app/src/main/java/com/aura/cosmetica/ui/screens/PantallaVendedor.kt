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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.ReceiptLong
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.data.Repo
import com.aura.cosmetica.data.cop
import com.aura.cosmetica.nav.NavVendedor
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irTabVendedor
import com.aura.cosmetica.ui.components.BarraBusqueda
import com.aura.cosmetica.ui.components.BarraInferior
import com.aura.cosmetica.ui.components.BotonPrincipal
import com.aura.cosmetica.ui.components.CabeceraLogo
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.CampoLargo
import com.aura.cosmetica.ui.components.CampoTexto
import com.aura.cosmetica.ui.components.ChipAccion
import com.aura.cosmetica.ui.components.ChipCategoria
import com.aura.cosmetica.ui.components.FilaProducto
import com.aura.cosmetica.ui.components.FilaPedidoTienda
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.components.SeccionTitulo
import com.aura.cosmetica.ui.components.TarjetaKpi
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Borde
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.Dorado
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.Tinta

// ===========================================================================
// 11 · PANEL VENDEDOR
// ===========================================================================

@Composable
fun PantallaVendedorPanel(nav: NavHostController) {
    PantallaAura(
        barraInferior = {
            BarraInferior(NavVendedor, "Inicio") { etiqueta ->
                irTabVendedor(nav, rutaDeVendedor(etiqueta))
            }
        },
        cabecera = { CabeceraLogo(chip = "Rol · Vendedor") }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                "PANEL VENDEDOR",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.5.sp,
                color = Champagne
            )
            Spacer(Modifier.height(8.dp))
            Text(
                buildAnnotatedString {
                    append("Buenos días, ")
                    withStyle(SpanStyle(color = Ciruela, fontStyle = FontStyle.Italic)) {
                        append("Juan")
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
                "Tu tienda está lista. Esto es lo que pasa hoy.",
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
            TarjetaKpi("12", "Mis productos", "2 nuevos este mes", modificador = Modifier.weight(1f))
            TarjetaKpi("8", "Pedidos activos", "3 pendientes hoy", modificador = Modifier.weight(1f))
        }
        Spacer(Modifier.height(10.dp))
        Row(
            Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TarjetaKpi(
                "1.250.000", "Ventas del mes · COP",
                "+12% vs. septiembre", Dorado, Modifier.weight(1f)
            )
            TarjetaKpi("25", "Clientes", "6 compraron 2+ veces", modificador = Modifier.weight(1f))
        }

        Spacer(Modifier.height(18.dp))
        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                "ACCIONES RÁPIDAS",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.sp,
                color = GrisCalido
            )
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(
                    Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Ciruela)
                        .clickable { nav.navigate(Rutas.V_CREAR) }
                        .padding(14.dp)
                ) {
                    Column {
                        Icon(Icons.Filled.Add, null, tint = Blanco, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Nuevo producto",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Blanco
                        )
                    }
                }
                Box(
                    Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Blanco)
                        .clickable { nav.navigate(Rutas.V_PEDIDOS) }
                        .padding(14.dp)
                ) {
                    Column {
                        Icon(Icons.Filled.ReceiptLong, null, tint = Ciruela, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Ver pedidos",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Ciruela
                        )
                    }
                }
            }
        }

        SeccionTitulo("Últimos pedidos", "Ver todos") { nav.navigate(Rutas.V_PEDIDOS) }

        Column(Modifier.padding(horizontal = 20.dp)) {
            Repo.pedidosTienda.take(2).forEach { pedido ->
                FilaPedidoTienda(pedido, onClick = { nav.navigate(Rutas.V_PEDIDOS) })
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

private fun rutaDeVendedor(etiqueta: String): String = when (etiqueta) {
    "Productos" -> Rutas.V_PRODUCTOS
    "Pedidos" -> Rutas.V_PEDIDOS
    "Perfil" -> Rutas.V_PERFIL
    else -> Rutas.V_PANEL
}

// ===========================================================================
// 12 · MIS PRODUCTOS (VENDEDOR)
// ===========================================================================

@Composable
fun PantallaVendedorProductos(nav: NavHostController) {
    var busqueda by remember { mutableStateOf("") }
    val mios = listOf(3, 2, 5, 6).mapNotNull { id -> Repo.productos.find { it.id == id } }
        .filter { busqueda.isBlank() || it.nombre.contains(busqueda, ignoreCase = true) }

    PantallaAura(
        barraInferior = {
            BarraInferior(NavVendedor, "Productos") { etiqueta ->
                irTabVendedor(nav, rutaDeVendedor(etiqueta))
            }
        },
        cabecera = {
            CabeceraTitulo("Mis productos", onVolver = { nav.popBackStack() }) {
                ChipAccion("+Nuevo") { nav.navigate(Rutas.V_CREAR) }
            }
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            BarraBusqueda(busqueda, { busqueda = it }, "Buscar en mi tienda…")
        }

        Spacer(Modifier.height(14.dp))

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            mios.forEach { p ->
                FilaProducto(
                    imagen = p.imagen,
                    titulo = p.nombre,
                    subtitulo = "${p.detalle} · ${p.stock} uds",
                    precio = cop(p.precioCop),
                    badgeDorado = if (p.stock < 10) "Stock bajo" else null,
                    trailing = {
                        Row {
                            IconoBotonPequeno(Icons.Filled.Edit, "Editar") {
                                nav.navigate(Rutas.productoEditar(p.id))
                            }
                            Spacer(Modifier.width(2.dp))
                            IconoBotonPequeno(Icons.Filled.Delete, "Eliminar") {
                                Repo.eliminarProducto(p.id)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun IconoBotonPequeno(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    descripcion: String,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .size(34.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(com.aura.cosmetica.ui.theme.MarfilOscuro)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(icono, descripcion, tint = Ciruela, modifier = Modifier.size(16.dp))
    }
}

// ===========================================================================
// 13 · CREAR PRODUCTO (VENDEDOR)
// ===========================================================================

@Composable
fun PantallaVendedorCrear(nav: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Skincare") }

    PantallaAura(
        barraInferior = {
            Column {
                BotonPrincipal(
                    texto = "Guardar producto",
                    modificador = Modifier.padding(horizontal = 20.dp),
                    onClick = { nav.popBackStack() }
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "La foto se valida con la cámara · RF-11 a RF-13",
                    fontFamily = Inter,
                    fontSize = 10.sp,
                    color = GrisCalido,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        },
        cabecera = {
            CabeceraTitulo("Nuevo producto", onVolver = { nav.popBackStack() })
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {

            // ---- Zona de imagen ----
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Blanco)
                    .border(1.5.dp, Champagne, RoundedCornerShape(18.dp))
                    .clickable { nav.navigate(Rutas.V_CAMARA) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Filled.PhotoCamera, null, tint = Champagne, modifier = Modifier.size(26.dp))
                Spacer(Modifier.height(8.dp))
                Text(
                    "Agregar imagen del producto",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Tinta
                )
                Text(
                    "Toma una foto con la cámara o sube desde galería",
                    fontFamily = Inter,
                    fontSize = 11.sp,
                    color = GrisCalido
                )
            }

            Spacer(Modifier.height(16.dp))
            CampoTexto(nombre, { nombre = it }, "Nombre del producto · Ej. Crema Facial 24K")
            Spacer(Modifier.height(10.dp))
            CampoLargo(descripcion, { descripcion = it }, "Describe textura, beneficios y tipo de piel…")
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(Modifier.weight(1f)) {
                    CampoTexto(
                        precio, { precio = it }, "Precio · COP",
                        teclado = androidx.compose.ui.text.input.KeyboardType.Number
                    )
                }
                Box(Modifier.weight(1f)) {
                    CampoTexto(
                        stock, { stock = it }, "Stock inicial · 24 uds",
                        teclado = androidx.compose.ui.text.input.KeyboardType.Number
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(
                "Categoría",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Tinta
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Skincare", "Maquillaje", "Capilar", "Personal").forEach { c ->
                    ChipCategoria(c, categoria == c) { categoria = c }
                }
            }
        }
    }
}

// ===========================================================================
// EDITAR PRODUCTO (VENDEDOR) · CRUD completo: Update
// Prellena la ficha del producto y guarda sobre el catálogo en Repo.
// ===========================================================================

@Composable
fun PantallaVendedorEditar(nav: NavHostController, pid: Int) {
    val producto = Repo.productos.find { it.id == pid }
    if (producto == null) {
        // El producto ya no existe (eliminado): regresar al listado.
        LaunchedEffect(Unit) { nav.popBackStack() }
        return
    }

    var nombre by remember { mutableStateOf(producto.nombre) }
    var descripcion by remember { mutableStateOf(producto.descripcion) }
    var precio by remember { mutableStateOf(producto.precioCop.toString()) }
    var stock by remember { mutableStateOf(producto.stock.toString()) }
    var categoria by remember { mutableStateOf(producto.categoria) }
    var guardado by remember { mutableStateOf(false) }

    PantallaAura(
        barraInferior = {
            Column {
                BotonPrincipal(
                    texto = if (guardado) "Volver a mis productos" else "Guardar cambios",
                    habilitado = guardado || (nombre.isNotBlank() &&
                            precio.toIntOrNull() != null && stock.toIntOrNull() != null),
                    modificador = Modifier.padding(horizontal = 20.dp),
                    onClick = {
                        if (guardado) {
                            nav.popBackStack()
                        } else {
                            Repo.actualizarProducto(
                                id = pid,
                                nombre = nombre.trim(),
                                descripcion = descripcion.trim(),
                                precioCop = precio.toIntOrNull() ?: producto.precioCop,
                                stock = stock.toIntOrNull() ?: producto.stock,
                                categoria = categoria
                            )
                            guardado = true
                        }
                    }
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "El precio y el stock se validan antes de guardar · RF-12",
                    fontFamily = Inter,
                    fontSize = 10.sp,
                    color = GrisCalido,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        },
        cabecera = { CabeceraTitulo("Editar producto", onVolver = { nav.popBackStack() }) }
    ) {
        if (guardado) {
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
                            "Producto actualizado",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Tinta
                        )
                        Text(
                            "El catálogo y las fichas reflejan los nuevos datos.",
                            fontFamily = Inter,
                            fontSize = 12.sp,
                            color = GrisCalido
                        )
                    }
                }
            }
        } else {
            Column(Modifier.padding(horizontal = 20.dp)) {

                // ---- Imagen actual del producto ----
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Blanco)
                        .border(1.5.dp, Champagne, RoundedCornerShape(18.dp))
                ) {
                    Image(
                        painter = painterResource(producto.imagen),
                        contentDescription = producto.nombre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(150.dp)
                    )
                }

                Spacer(Modifier.height(16.dp))
                CampoTexto(nombre, { nombre = it }, "Nombre del producto")
                Spacer(Modifier.height(10.dp))
                CampoLargo(descripcion, { descripcion = it }, "Describe textura, beneficios y tipo de piel…")
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(Modifier.weight(1f)) {
                        CampoTexto(
                            precio, { precio = it }, "Precio · COP",
                            teclado = androidx.compose.ui.text.input.KeyboardType.Number
                        )
                    }
                    Box(Modifier.weight(1f)) {
                        CampoTexto(
                            stock, { stock = it }, "Stock · uds",
                            teclado = androidx.compose.ui.text.input.KeyboardType.Number
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Text(
                    "Categoría",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Tinta
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Skincare", "Maquillaje", "Capilar", "Personal").forEach { c ->
                        ChipCategoria(c, categoria == c) { categoria = c }
                    }
                }
            }
        }
    }
}

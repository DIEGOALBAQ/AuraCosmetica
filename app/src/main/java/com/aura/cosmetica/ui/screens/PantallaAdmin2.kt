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
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aura.cosmetica.data.Repo
import com.aura.cosmetica.data.cop
import com.aura.cosmetica.nav.NavAdmin
import com.aura.cosmetica.nav.Rutas
import com.aura.cosmetica.nav.irLimpio
import com.aura.cosmetica.nav.irTabAdmin
import com.aura.cosmetica.ui.components.AvatarIniciales
import com.aura.cosmetica.ui.components.BarraBusqueda
import com.aura.cosmetica.ui.components.BarraInferior
import com.aura.cosmetica.ui.components.CabeceraTitulo
import com.aura.cosmetica.ui.components.ChipRolAura
import com.aura.cosmetica.ui.components.EtiquetaEstado
import com.aura.cosmetica.ui.components.FilaOpcion
import com.aura.cosmetica.ui.components.GraficoBarras
import com.aura.cosmetica.ui.components.PantallaAura
import com.aura.cosmetica.ui.components.SeccionTitulo
import com.aura.cosmetica.ui.theme.Blanco
import com.aura.cosmetica.ui.theme.Blush
import com.aura.cosmetica.ui.theme.Champagne
import com.aura.cosmetica.ui.theme.Ciruela
import com.aura.cosmetica.ui.theme.CiruelaOscuro
import com.aura.cosmetica.ui.theme.Cormorant
import com.aura.cosmetica.ui.theme.Dorado
import com.aura.cosmetica.ui.theme.GrisCalido
import com.aura.cosmetica.ui.theme.Inter
import com.aura.cosmetica.ui.theme.MarfilOscuro
import com.aura.cosmetica.ui.theme.Tinta

// ===========================================================================
// 20 · COMPRAS (ADMIN)
// ===========================================================================

@Composable
fun PantallaAdminCompras(nav: NavHostController) {
    var busqueda by remember { mutableStateOf("") }

    val lista = Repo.comprasAdmin.filter { c ->
        busqueda.isBlank() ||
                c.cliente.contains(busqueda, ignoreCase = true) ||
                c.codigo.contains(busqueda, ignoreCase = true)
    }

    PantallaAura(
        barraInferior = {
            BarraInferior(NavAdmin, "Compras") { etiqueta ->
                irTabAdmin(
                    nav,
                    when (etiqueta) {
                        "Usuarios" -> Rutas.A_USUARIOS
                        "Productos" -> Rutas.A_PRODUCTOS
                        "Compras" -> Rutas.A_COMPRAS
                        else -> Rutas.A_PANEL
                    }
                )
            }
        },
        cabecera = {
            CabeceraTitulo("Compras", onVolver = { nav.popBackStack() })
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            BarraBusqueda(busqueda, { busqueda = it }, "Buscar compra…")
        }

        Spacer(Modifier.height(14.dp))

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            lista.forEach { c ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Blanco)
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "${c.codigo} · ${c.cliente}",
                                fontFamily = Inter,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = Tinta,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(Modifier.width(8.dp))
                            EtiquetaEstado(c.estado)
                        }
                        Spacer(Modifier.height(3.dp))
                        Text(
                            "${c.fecha} · ${c.medioPago}",
                            fontFamily = Inter,
                            fontSize = 11.sp,
                            color = GrisCalido
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        cop(c.totalCop),
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Ciruela
                    )
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                "RESUMEN DEL DÍA",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                letterSpacing = 2.sp,
                color = GrisCalido
            )
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChipResumenDia("Ventas hoy", "$303.000", Modifier.weight(1f), destacado = false)
                ChipResumenDia("Pedidos", "4", Modifier.weight(1f), destacado = false)
                ChipResumenDia("Pendientes", "1", Modifier.weight(1f), destacado = true)
            }
        }
    }
}

@Composable
private fun ChipResumenDia(
    etiqueta: String,
    valor: String,
    modificador: Modifier,
    destacado: Boolean
) {
    Column(
        modificador
            .clip(RoundedCornerShape(14.dp))
            .background(if (destacado) Blush else Blanco)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            etiqueta,
            fontFamily = Inter,
            fontSize = 10.sp,
            color = GrisCalido
        )
        Spacer(Modifier.height(3.dp))
        Text(
            valor,
            fontFamily = Inter,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = if (destacado) CiruelaOscuro else Tinta
        )
    }
}

// ===========================================================================
// 21 · REPORTES (ADMIN)
// ===========================================================================

private val periodos = listOf("Agosto 2025", "Septiembre 2025", "Octubre 2025")

@Composable
fun PantallaAdminReportes(nav: NavHostController) {
    var periodo by remember { mutableStateOf(2) }

    val valores = listOf(8.2f, 6.5f, 7.1f, 5.8f, 8.6f, 7.4f, 9.3f, 7.8f)
    val etiquetas = listOf("S1", "S1·", "S2", "S2·", "S3", "S3·", "S4", "S4·")
    val colores = listOf(
        Ciruela, Champagne, Ciruela, Champagne,
        Ciruela, Champagne, Ciruela, Champagne
    )

    PantallaAura(
        cabecera = {
            CabeceraTitulo("Reportes", onVolver = { nav.popBackStack() })
        }
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {

            // ---- Selector de periodo ----
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Blanco)
                    .clickable { periodo = (periodo + 1) % periodos.size }
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    periodos[periodo],
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Tinta
                )
                Icon(Icons.Filled.ExpandMore, null, tint = Ciruela, modifier = Modifier.size(18.dp))
            }

            Spacer(Modifier.height(18.dp))
            Text(
                "Ventas por semana",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Tinta
            )
            Text(
                "Comparado con septiembre · cifra en millones COP",
                fontFamily = Inter,
                fontSize = 11.sp,
                color = GrisCalido
            )
            Spacer(Modifier.height(12.dp))
            GraficoBarras(valores, etiquetas, colores, altura = 140.dp)

            Spacer(Modifier.height(20.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Column(
                    Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Blanco)
                        .padding(14.dp)
                ) {
                    Text("Total ventas", fontFamily = Inter, fontSize = 10.sp, color = GrisCalido)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "$5.200.000",
                        fontFamily = com.aura.cosmetica.ui.theme.Cinzel,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                        color = Ciruela
                    )
                    Text("Todo octubre", fontFamily = Inter, fontSize = 10.sp, color = GrisCalido)
                }
                Column(
                    Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Blanco)
                        .padding(14.dp)
                ) {
                    Text("Total pedidos", fontFamily = Inter, fontSize = 10.sp, color = GrisCalido)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "320",
                        fontFamily = com.aura.cosmetica.ui.theme.Cinzel,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                        color = Ciruela
                    )
                    Text("≈ 10.6 diarios", fontFamily = Inter, fontSize = 10.sp, color = GrisCalido)
                }
            }
        }

        SeccionTitulo("Productos más vendidos")

        Column(
            Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TopProducto(1, "Sérum Radiance", "48 unidades · $6.144.000")
            TopProducto(2, "Labial Noir", "36 unidades · $1.296.000")
        }
    }
}

@Composable
private fun TopProducto(puesto: Int, nombre: String, detalle: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Blanco)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Blush),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "$puesto",
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Ciruela
            )
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                nombre,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Tinta
            )
            Text(detalle, fontFamily = Inter, fontSize = 11.sp, color = GrisCalido)
        }
        Text(
            "Top $puesto",
            fontFamily = Inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            color = CiruelaOscuro,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(com.aura.cosmetica.ui.theme.ChampagneSuave)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

// ===========================================================================
// 22 · PERFIL ADMINISTRADOR
// ===========================================================================

@Composable
fun PantallaAdminPerfil(nav: NavHostController) {
    PantallaAura(
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
                AvatarIniciales("Carlos Ramírez", 56.dp)
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(
                        "Carlos Ramírez",
                        fontFamily = Cormorant,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        color = Tinta
                    )
                    Text(
                        "admin@gmail.com",
                        fontFamily = Inter,
                        fontSize = 12.sp,
                        color = GrisCalido
                    )
                    Spacer(Modifier.height(6.dp))
                    ChipRolAura("Rol · Administrador")
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Ciruela)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Security, null, tint = Blanco, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        "Acceso total · Auditoría activa",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = Blanco
                    )
                    Text(
                        "Último acceso hoy · 8:15 a.m.",
                        fontFamily = Inter,
                        fontSize = 11.sp,
                        color = Blanco.copy(alpha = 0.72f)
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FilaOpcion(Icons.Filled.Settings, "Configuración del sistema")
                FilaOpcion(Icons.Filled.ManageAccounts, "Gestión de roles")
                FilaOpcion(Icons.Filled.Backup, "Respaldos de datos")
                FilaOpcion(Icons.Filled.History, "Registro de auditoría")
            }

            Spacer(Modifier.height(14.dp))
            FilaOpcion(Icons.Filled.Logout, "Cerrar sesión") {
                irLimpio(nav, Rutas.REGISTRO)
            }

            Spacer(Modifier.height(16.dp))
            Text(
                "RF-28 a RF-30 · APIs, panel y auditoría",
                fontFamily = Inter,
                fontSize = 10.sp,
                color = GrisCalido,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

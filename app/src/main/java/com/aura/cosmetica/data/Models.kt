package com.aura.cosmetica.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class Producto(
    val id: Int,
    val nombre: String,
    val detalle: String,
    val subtitulo: String,
    val categoria: String,
    val precioCop: Int,
    @DrawableRes val imagen: Int,
    val descripcion: String,
    val etiqueta: String? = null,
    val valoracion: Double = 4.8,
    val resenas: Int = 64,
    val stock: Int = 24
)

@Immutable
data class ItemCarrito(val producto: Producto, val cantidad: Int = 1) {
    val total: Int get() = producto.precioCop * cantidad
}

enum class EstadoPedido(val etiqueta: String) {
    PENDIENTE("Pendiente"),
    EN_PREPARACION("En preparación"),
    ENVIADO("Enviado"),
    ENTREGADO("Entregado")
}

fun accionPara(e: EstadoPedido): String = when (e) {
    EstadoPedido.PENDIENTE -> "Comenzar a preparar"
    EstadoPedido.EN_PREPARACION -> "Marcar enviado"
    else -> "Ver detalle"
}

fun siguienteEstado(e: EstadoPedido): EstadoPedido = when (e) {
    EstadoPedido.PENDIENTE -> EstadoPedido.EN_PREPARACION
    EstadoPedido.EN_PREPARACION -> EstadoPedido.ENVIADO
    else -> e
}

@Immutable
data class Pedido(
    val codigo: String,
    val fecha: String,
    val resumen: String,
    val items: Int,
    val totalCop: Int,
    val estado: EstadoPedido,
    val thumbs: List<Int> = emptyList()
)

@Immutable
data class PedidoTienda(
    val codigo: String,
    val cliente: String,
    val meta: String,
    val totalCop: Int,
    val estado: EstadoPedido
)

@Immutable
data class CompraAdmin(
    val codigo: String,
    val cliente: String,
    val estado: EstadoPedido,
    val medioPago: String,
    val fecha: String,
    val totalCop: Int
)

@Immutable
data class UsuarioAdmin(
    val nombre: String,
    val correo: String,
    val rol: String,
    val detalle: String,
    val telefono: String = "",
    val desactivado: Boolean = false
)

@Immutable
data class ProductoAdmin(
    val nombre: String,
    val categoria: String,
    val vendedor: String,
    val precioCop: Int,
    @DrawableRes val imagen: Int,
    val desactivado: Boolean = false
)

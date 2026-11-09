package com.aura.cosmetica.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.aura.cosmetica.R
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val formatoCop: NumberFormat = NumberFormat.getIntegerInstance(Locale("es", "CO"))

/** Formato de precio colombiano: $128.000 */
fun cop(valor: Int): String = "$" + formatoCop.format(valor)

private fun fechaHoy(): String =
    LocalDate.now().format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale("es", "CO")))

fun entregaEstimada(): String {
    val formato = DateTimeFormatter.ofPattern("EEE d MMM", Locale("es", "CO"))
    return LocalDate.now().plusDays(3).format(formato).replaceFirstChar { it.uppercase() }
}

object Repo {

    val productos: SnapshotStateList<Producto> = mutableStateListOf(
        Producto(
            id = 1, nombre = "Sérum Radiance",
            detalle = "Skincare · 30 ml",
            subtitulo = "Vitamina C · Ácido hialurónico · 30 ml",
            categoria = "Skincare", precioCop = 128000, imagen = R.drawable.img_serum,
            descripcion = "Fórmula ligera con vitamina C estabilizada que unifica el tono y devuelve el brillo natural. Absorción inmediata, apto para todo tipo de piel.",
            etiqueta = "Nuevo", valoracion = 4.9, resenas = 128, stock = 32
        ),
        Producto(
            id = 2, nombre = "Labial Noir",
            detalle = "Maquillaje · Tono ciruela",
            subtitulo = "Acabado mate · Larga duración · 3.8 g",
            categoria = "Maquillaje", precioCop = 36000, imagen = R.drawable.img_labial,
            descripcion = "Acabado mate satinado con pigmento de larga duración. Un rojo ciruela profundo con cobertura en una sola pasada.",
            valoracion = 4.7, resenas = 86, stock = 13
        ),
        Producto(
            id = 3, nombre = "Crema Hydra Plus",
            detalle = "Skincare · 50 ml",
            subtitulo = "Ácido hialurónico · Agua de rosas · 50 ml",
            categoria = "Skincare", precioCop = 42000, imagen = R.drawable.img_crema,
            descripcion = "Hidratación profunda 24 horas con ácido hialurónico y agua de rosas. Piel suave, elástica y luminosa desde la primera aplicación.",
            valoracion = 4.8, resenas = 94, stock = 24
        ),
        Producto(
            id = 4, nombre = "Paleta Rosé",
            detalle = "Maquillaje · 6 tonos",
            subtitulo = "6 tonos rosados · Acabado aterciopelado",
            categoria = "Maquillaje", precioCop = 58000, imagen = R.drawable.img_paleta,
            descripcion = "Seis tonos rosados de pigmento sedoso para looks de día y de noche. Acabado aterciopelado con espejo incluido.",
            valoracion = 4.6, resenas = 71, stock = 18
        ),
        Producto(
            id = 5, nombre = "Shampoo Hidratante",
            detalle = "Capilar · 400 ml",
            subtitulo = "Keratina vegetal · Aceite de argán · 400 ml",
            categoria = "Capilar", precioCop = 30000, imagen = R.drawable.img_shampoo,
            descripcion = "Limpieza suave con keratina vegetal y aceite de argán. Reduce el quiebre y devuelve el brillo natural al cabello.",
            valoracion = 4.7, resenas = 58, stock = 31
        ),
        Producto(
            id = 6, nombre = "Aceite Nutritivo",
            detalle = "Skincare · 100 ml",
            subtitulo = "Argán · Jojoba · Vitamina E · 100 ml",
            categoria = "Skincare", precioCop = 54500, imagen = R.drawable.img_aceite,
            descripcion = "Mezcla de argán, jojoba y vitamina E que nutre piel y cabello. Textura sedosa, absorción rápida y aroma a vainilla.",
            valoracion = 4.8, resenas = 47, stock = 8
        ),
        Producto(
            id = 7, nombre = "Perfume Ritual",
            detalle = "Personal · 100 ml",
            subtitulo = "Bergamota · Rosa damascena · Ámbar · 100 ml",
            categoria = "Personal", precioCop = 95000, imagen = R.drawable.img_perfume,
            descripcion = "Eau de parfum con salida de bergamota, corazón de rosa damascena y fondo de ámbar y sándalo. Fijación de 8 horas.",
            valoracion = 4.9, resenas = 63, stock = 12
        ),
        Producto(
            id = 8, nombre = "Kit Ritual Radiance",
            detalle = "Skincare · Colección",
            subtitulo = "Sérum + Crema + Aceite · Estuche editorial",
            categoria = "Skincare", precioCop = 219000, imagen = R.drawable.img_hero,
            descripcion = "La colección completa: sérum iluminador, crema hidratante y aceite nutritivo en un estuche editorial de edición limitada.",
            etiqueta = "Edición limitada", valoracion = 5.0, resenas = 21, stock = 6
        )
    )

    val categorias = listOf("Todo", "Skincare", "Maquillaje", "Capilar", "Personal")

    // ---------- Carrito (inicia con los 2 productos de la maqueta 04) ----------

    val carrito: SnapshotStateList<ItemCarrito> = mutableStateListOf(
        ItemCarrito(productos[1], 1),
        ItemCarrito(productos[2], 1)
    )

    const val UMBRAL_ENVIO = 150000
    const val COSTO_ENVIO = 5980

    val subtotalCarrito: Int get() = carrito.sumOf { it.total }
    val costoEnvio: Int get() = if (carrito.isEmpty() || subtotalCarrito >= UMBRAL_ENVIO) 0 else COSTO_ENVIO
    val totalCarrito: Int get() = subtotalCarrito + costoEnvio
    val unidadesCarrito: Int get() = carrito.sumOf { it.cantidad }

    fun agregar(idProducto: Int) {
        val i = carrito.indexOfFirst { it.producto.id == idProducto }
        if (i >= 0) carrito[i] = carrito[i].copy(cantidad = carrito[i].cantidad + 1)
        else productos.find { it.id == idProducto }?.let { carrito.add(ItemCarrito(it, 1)) }
    }

    fun cambiarCantidad(indice: Int, delta: Int) {
        val item = carrito.getOrNull(indice) ?: return
        val nueva = item.cantidad + delta
        if (nueva <= 0) carrito.removeAt(indice)
        else carrito[indice] = item.copy(cantidad = nueva)
    }

    fun eliminar(indice: Int) {
        if (indice in carrito.indices) carrito.removeAt(indice)
    }

    fun vaciar() = carrito.clear()

    fun actualizarProducto(
        id: Int,
        nombre: String,
        descripcion: String,
        precioCop: Int,
        stock: Int,
        categoria: String
    ) {
        val i = productos.indexOfFirst { it.id == id }
        if (i < 0) return
        val actual = productos[i]
        val tamano = actual.detalle.substringAfter(" · ", "")
        productos[i] = actual.copy(
            nombre = nombre,
            descripcion = descripcion,
            precioCop = precioCop,
            stock = stock,
            categoria = categoria,
            detalle = if (tamano.isBlank()) categoria else "$categoria · $tamano"
        )
    }

    /** Elimina un producto del catálogo (CRUD de vendedor). */
    fun eliminarProducto(id: Int) {
        productos.removeAll { it.id == id }
    }

    // ---------- Pedidos del cliente ----------

    var pedidoConfirmado: Pedido? = null
        private set

    private var siguienteCodigo = 1024

    val pedidosCliente: SnapshotStateList<Pedido> = mutableStateListOf(
        Pedido("A-0981", "2 oct 2025", "Sérum Radiance + 2 más", 3, 156500,
            EstadoPedido.ENVIADO, listOf(R.drawable.img_serum, R.drawable.img_aceite)),
        Pedido("A-0912", "21 sep 2025", "Paleta Rosé + 1 más", 2, 88000,
            EstadoPedido.ENTREGADO, listOf(R.drawable.img_paleta, R.drawable.img_shampoo))
    )

    fun confirmarPedido(): Pedido {
        val codigo = "A-$siguienteCodigo"
        siguienteCodigo += 1
        val pedido = Pedido(
            codigo = codigo,
            fecha = fechaHoy(),
            resumen = if (carrito.size == 1) carrito[0].producto.nombre
                      else "${carrito[0].producto.nombre} + ${carrito.size - 1} más",
            items = unidadesCarrito,
            totalCop = totalCarrito,
            estado = EstadoPedido.EN_PREPARACION,
            thumbs = carrito.take(2).map { it.producto.imagen }
        )
        pedidoConfirmado = pedido
        pedidosCliente.add(0, pedido)
        vaciar()
        return pedido
    }

    // ---------- Pedidos de la tienda (vendedor, pantalla 15) ----------

    val pedidosTienda: SnapshotStateList<PedidoTienda> = mutableStateListOf(
        PedidoTienda("#1001", "María López", "2 productos · 18/10/2025 · Cra 13 # 93-40",
            85000, EstadoPedido.PENDIENTE),
        PedidoTienda("#1002", "Carlos Ramírez", "1 producto · 18/10/2025 · Cl 45 # 21-15",
            56000, EstadoPedido.EN_PREPARACION),
        PedidoTienda("#1003", "Ana Torres", "3 productos · 17/10/2025 · Av 68 # 40-10",
            120000, EstadoPedido.ENVIADO)
    )

    fun avanzarPedido(indice: Int) {
        val p = pedidosTienda.getOrNull(indice) ?: return
        pedidosTienda[indice] = p.copy(estado = siguienteEstado(p.estado))
    }

    // ---------- Usuarios (admin, pantalla 18) ----------

    val usuariosAdmin: SnapshotStateList<UsuarioAdmin> = mutableStateListOf(
        UsuarioAdmin("María López", "marta@gmail.com", "Compradora", "14 compras", "300 111 2233"),
        UsuarioAdmin("Juan Pérez", "juan@gmail.com", "Vendedor", "12 productos", "301 222 3344"),
        UsuarioAdmin("Carlos Ramírez", "carlos@gmail.com", "Admin", "Acceso total", "302 333 4455"),
        UsuarioAdmin("Ana Torres", "ana@gmail.com", "Compradora", "6 compras", "310 444 5566"),
        UsuarioAdmin("Luis Gómez", "luis@gmail.com", "Vendedor", "Desactivado desde 01/10", "311 555 6677", desactivado = true)
    )

    // ---- CRUD de usuario (admin · RF-05 a RF-08) ----

    fun crearUsuario(nombre: String, correo: String, telefono: String, rol: String) {
        usuariosAdmin.add(
            0,
            UsuarioAdmin(nombre, correo, rol, "Nuevo registro · ${fechaHoy()}", telefono)
        )
    }

    fun actualizarUsuario(indice: Int, nombre: String, correo: String, telefono: String, rol: String) {
        val u = usuariosAdmin.getOrNull(indice) ?: return
        usuariosAdmin[indice] = u.copy(nombre = nombre, correo = correo, telefono = telefono, rol = rol)
    }

    /** Borrado lógico: desactiva o reactiva sin eliminar el historial (RF-08). */
    fun alternarUsuario(indice: Int) {
        val u = usuariosAdmin.getOrNull(indice) ?: return
        usuariosAdmin[indice] = if (u.desactivado) {
            u.copy(desactivado = false, detalle = "Reactivado · ${fechaHoy()}")
        } else {
            u.copy(desactivado = true, detalle = "Desactivado desde ${fechaHoy()}")
        }
    }

    // ---------- Productos por gestionar (admin, pantalla 19) ----------

    val productosAdmin: SnapshotStateList<ProductoAdmin> = mutableStateListOf(
        ProductoAdmin("Crema Facial 24K", "Skincare", "Juan Pérez", 42000, R.drawable.img_crema),
        ProductoAdmin("Labial Matte", "Maquillaje", "Juan Pérez", 36000, R.drawable.img_labial),
        ProductoAdmin("Shampoo Hidratante", "Capilar", "Luis Gómez", 30000, R.drawable.img_shampoo),
        ProductoAdmin("Perfume Ritual", "Personal", "Juan Pérez", 95000, R.drawable.img_perfume),
        ProductoAdmin("Paleta Rosé", "Maquillaje", "Luis Gómez", 58000, R.drawable.img_paleta, desactivado = true)
    )

    /** Borrado lógico del producto en el panel admin (RF-13). */
    fun alternarProductoAdmin(indice: Int) {
        val p = productosAdmin.getOrNull(indice) ?: return
        productosAdmin[indice] = p.copy(desactivado = !p.desactivado)
    }

    // ---------- Compras (admin, pantalla 20) ----------

    val comprasAdmin = listOf(
        CompraAdmin("#1001", "Marta López", EstadoPedido.PENDIENTE, "Visa ·•• 4021", "12/03/2025", 85000),
        CompraAdmin("#1002", "Carlos Ramírez", EstadoPedido.ENVIADO, "PSE · Bancolombia", "11/03/2025", 56000),
        CompraAdmin("#1003", "Ana Torres", EstadoPedido.ENTREGADO, "Nequi", "10/03/2025", 120000),
        CompraAdmin("#1004", "María López", EstadoPedido.ENTREGADO, "Visa ·•• 8834", "09/03/2025", 42000)
    )
}

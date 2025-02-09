package com.alura.comex.maths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import com.alura.comex.model.Categoria;
import com.alura.comex.model.Pedido;
import com.alura.comex.model.Producto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class CalculosInformeTest {

    @Test
    void deberiaGenerarListaPorCategoria() {
        //ARRANGE
        CalculosInforme calculosInforme = new CalculosInforme();
        //ACT
        List<InformeVentasPorCategoria> lista = calculosInforme.listaPorCategoria();
        //ASSERT
        assertNotNull(lista, "La lista no debería ser nula");
        assertEquals(5, lista.size(), "El tamaño de la lista no coincide con el esperado");
    }

    @Test
    void deberiaGenerarListaPorCategoriaVacia() {
        //ARRANGE
        CalculosInforme calculosInforme = Mockito.mock(CalculosInforme.class);
        when(calculosInforme.listaPorCategoria()).thenReturn(List.of());
        //ACT
        List<InformeVentasPorCategoria> lista = calculosInforme.listaPorCategoria();
        //ASSERT
        assertEquals(0, lista.size(), "El tamaño de la lista no coincide con el esperado");
    }

    @Test
    void deberiaGenerarInformeProductosMasVendidos() {
        //ARRANGE
        CalculosInforme calculosInforme = new CalculosInforme();
        //ACT
        List<Pedido> lista = calculosInforme.listaPorProducto();
        //ASSERT
        assertNotNull(lista, "La lista no debería estar vacía");
    }

    @Test
    void deberiaGenerarInformeProductosMasVendidosConUnSoloProducto() {
        //ARRANGE
        CalculosInforme calculosInforme = Mockito.mock(CalculosInforme.class);
        when(calculosInforme.listaPorProducto()).thenReturn(List.of(new Pedido(new Producto("Prueba", 120.00, Categoria.INFORMÁTICA), 3)));
        //ACT
        List<Pedido> lista = calculosInforme.listaPorProducto();
        //ASSERT
        assertNotNull(lista, "La lista no debería estar vacía");
        assertEquals("Prueba", lista.get(0).getProducto().getNombre(), "El nombre no coincide con el esperado");
        assertEquals(3, lista.get(0).getCantidad(), "La cantidad no coincide con el esperado");
        assertEquals(120.00, lista.get(0).getProducto().getPrecio(), "El precio no coincide con el esperado");
        assertEquals(1, lista.size());
    }

    @Test
    void deberiaGenerarUnInformeDeLosProductosMasCaros() {
        //ARRANGE
        CalculosInforme calculosInforme = new CalculosInforme();
        //ACT
        List<Producto> lista = calculosInforme.listaProductoMasCaroPorCategoria();
        //ASSERT
        assertNotNull(lista);
        assertEquals(5, lista.size(), "El tamaño de la lista no coincide con el esperado");
    }

    @Test
    void deberiaGenerarUnInformeDeLosProductosMasCarosConUnSoloProducto() {
        //ARRANGE
        CalculosInforme calculosInforme = Mockito.mock(CalculosInforme.class);
        when(calculosInforme.listaProductoMasCaroPorCategoria()).thenReturn(List.of(new Producto("Un Objeto", 35.000, Categoria.AUTOMOTOR)));
        //ACT
        List<Producto> lista = calculosInforme.listaProductoMasCaroPorCategoria();
        //ASSERT
        assertNotNull(lista);
        assertEquals(1, lista.size(), "El tamaño de la lista no coincide con el esperado");
        assertEquals("Un Objeto", lista.get(0).getNombre(), "El nombre no coincide con el esperado");
        assertEquals(35.000, lista.get(0).getPrecio(), "El precio no coincide con el esperado");
        assertEquals(Categoria.AUTOMOTOR, lista.get(0).getCategoria());
    }

    @Test
    void deberiaGenerarUnInformeDeLosProductosMasCarosVacia() {
        //ARRANGE
        CalculosInforme calculosInforme = Mockito.mock(CalculosInforme.class);
        when(calculosInforme.listaProductoMasCaroPorCategoria()).thenReturn(List.of(new Producto()));
        //ACT
        var lista = calculosInforme.generarInforme();
        //ASSERT
        assertNull(lista, "La lista debería estar vacía");
    }
}
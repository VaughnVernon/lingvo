// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

class SentenciaTest {

    @Test
    void si() {
        AtomicLong counter = new AtomicLong();

        Sentencia.si(true, counter::incrementAndGet);

        assertEquals(1L, counter.get());
    }

    @Test
    void coincide() {
        AtomicLong counter = new AtomicLong();

        Sentencia.coincide(counter.get())
                .con(0L,  counter::incrementAndGet)
                .nones(Assertions::fail);

        assertEquals(1L, counter.get());
    }

    @Test
    void bucle() {
        AtomicLong i = new AtomicLong();
        AtomicLong counter = new AtomicLong();

        Sentencia.bucle(() -> i.set(0), () -> i.get() < 10, () -> i.incrementAndGet(), () -> {
            counter.incrementAndGet();
        });

        assertEquals(10L, counter.get());
    }

    @Test
    void bucleContador() {
        int inicio = 0;
        int fin = 10;
        StringBuilder concatenador = new StringBuilder();

        Sentencia.bucle(inicio, fin, i -> concatenador.append(i.toString()));

        assertEquals("0123456789", concatenador.toString());
    }

    @Test
    void bucleContadorNegativo() {
        int inicio = 5;
        int fin = 2;
        StringBuilder concatenador = new StringBuilder();

        Sentencia.bucle(inicio, fin, i -> concatenador.append(i.toString()));

        assertEquals("", concatenador.toString());
    }

    @Test
    void mientras() {
        AtomicLong counter = new AtomicLong();

        Sentencia.mientras(() -> counter.get() < 10, () -> {
            counter.incrementAndGet();
        });

        assertEquals(10L, counter.get());
    }

    @Test
    void hazMientrasQue() {
        AtomicLong counter = new AtomicLong();

        Sentencia.haz(counter::incrementAndGet)
                .mientrasQue(() -> counter.get() < 10);

        assertEquals(10L, counter.get());
    }

    @Test
    void porCada() {
        List<Long> valores = List.of(1L, 2L, 3L);
        AtomicLong counter = new AtomicLong();

        Sentencia.porCada(valores, counter::addAndGet);

        assertEquals(6L, counter.get());
    }

    @Test
    void intenta() {
        AtomicLong counter = new AtomicLong();

        Sentencia.intenta(counter::incrementAndGet)
                .finalmente(counter::incrementAndGet);

        assertEquals(2L, counter.get());
    }

    @Test
    void nueva() {
        //Given
        String mensaje = "La descripcion de la excepcion";

        //When
        IllegalArgumentException actual = Sentencia.nueva(IllegalArgumentException.class, mensaje);

        //Then
        assertEquals(mensaje, actual.getMessage());
    }

    @Test
    void nuevaExcepcionSinContructorParaDescripcion() {
        //Given
        String mensaje = "La descripcion de la excepcion";

        //When
        IllegalStateException actualEx = assertThrows(IllegalStateException.class, () -> Sentencia.nueva(ExcepcionSinConstructorParaMensaje.class, mensaje));

        assertEquals("No se ha podido instanciar la excepcion de tipo " +
                "tools.dddesign.lingvo.es.java.ExcepcionSinConstructorParaMensaje. " +
                "Es probable que haga falta un constructor que reciba el mensaje", actualEx.getMessage());
    }

    @Test
    void nuevaExcepcionConMensajeYCausa() {
        //Given
        String mensaje = "La descripcion de la excepcion";
        IllegalArgumentException cause = new IllegalArgumentException();

        //When
        RuntimeException actualEx = Sentencia.nueva(RuntimeException.class, mensaje, cause);

        assertEquals(mensaje, actualEx.getMessage());
        assertEquals(cause, actualEx.getCause());
    }

    @Test
    void nuevaExcepcionSinConstructorParaMensajeYCausa() {
        //Given
        String mensaje = "La descripcion de la excepcion";

        //When
        IllegalStateException actualEx = assertThrows(IllegalStateException.class, () -> Sentencia.nueva(ExcepcionSinConstructorParaMensaje.class, mensaje, new IllegalArgumentException()));

        assertEquals("No se ha podido instanciar la excepcion de tipo " +
                "tools.dddesign.lingvo.es.java.ExcepcionSinConstructorParaMensaje. " +
                "Es probable que haga falta un constructor que reciba el mensaje y la causa", actualEx.getMessage());
    }

    @Test
    void siEstaPresente() {
        //Given
        Optional<String> optional = Optional.of("");

        //When
        boolean actual = Sentencia.estaPresente(optional);

        //Then
        assertTrue(actual);
    }

    @Test
    void noEstaPresente() {
        Optional<String> optional = Optional.empty();

        boolean actual = Sentencia.estaPresente(optional);

        assertFalse(actual);
    }

    @Test
    void siEstaVacio() {
        Optional<String> optional = Optional.empty();

        boolean actual = Sentencia.estaVacio(optional);

        assertTrue(actual);
    }

    @Test
    void noEstaVacio() {
        Optional<String> optional = Optional.of("");

        boolean actual = Sentencia.estaVacio(optional);

        assertFalse(actual);
    }

    @Test
    void coleccionVacia() {
        List<Long> valores = List.of();

        boolean actual = Sentencia.estaVacio(valores);

        assertTrue(actual);
    }

    @Test
    void colleccionNoVacia() {
        List<Long> valores = List.of(1L, 2L, 3L);

        boolean actual = Sentencia.estaVacio(valores);

        assertFalse(actual);
    }

    @Test
    void obtener() {
        Optional<String> optional = Optional.of("");

        String actual = Sentencia.obtener(optional);

        assertEquals("", actual);
    }

    @Test
    void obtenerDeUnOpcionalVacioArrojaExcepcion() {
        Optional<String> optional = Optional.empty();

        IllegalArgumentException actualEx = assertThrows(IllegalArgumentException.class, () -> Sentencia.obtener(optional));

        assertEquals("No existe el elemento",  actualEx.getMessage());
    }

    @Test
    void nuevaInstancia() {
        AtomicLong actualInstance = Sentencia.nuevaInstancia(AtomicLong.class);

        assertNotNull(actualInstance);
        assertEquals(0L, actualInstance.get());
    }

    @Test
    void nuevaInstanciaConParametros() {
        URI actualUri = Sentencia.nuevaInstancia(URI.class, "lingvo.ddd.com");

        assertEquals(URI.class, actualUri.getClass());
        assertEquals("lingvo.ddd.com", actualUri.toString());
    }

    @Test
    void nuevaInstanciaConParametrosPrimitivosArrojaExcepcion() {
        IllegalStateException actualEx = assertThrows(IllegalStateException.class, () -> Sentencia.nuevaInstancia(AtomicLong.class, 1L));

        assertEquals("No se ha podido instanciar el tipo java.util.concurrent.atomic.AtomicLong. " +
                "Es probable que haga falta un constructor que reciba los parámetros indicados. " +
                "De momento no esta soportado unboxing. Es decir si el constructor recibe long (primitivo) " +
                "se le enviará un Long (Object), por lo que no existe ese constructor.", actualEx.getMessage());
    }

}
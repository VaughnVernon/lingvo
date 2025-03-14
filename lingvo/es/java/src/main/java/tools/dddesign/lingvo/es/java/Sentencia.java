// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import jakarta.annotation.Nonnull;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * Statements façade
 */
public final class Sentencia {

    private Sentencia() { }

    /**
     * si - if
     * @param condicion
     * @param ejecutable
     * @return
     */
    public static SentenciaSi si(final boolean condicion, @Nonnull final Runnable ejecutable) {
        return SentenciaSi.si(condicion, ejecutable);
    }

    /**
     * switch = coincide
     * @param valor
     * @return
     * @param <T>
     */
    public static <T> SentenciaCoincide<T> coincide(@Nonnull final T valor) {
        return SentenciaCoincide.coincide(valor);
    }

    /**
     * for - bucle
     * @param inicializacion
     * @param condicionDeFin
     * @param incremento
     * @param ejecutable
     */
    public static void bucle(@Nonnull final Runnable inicializacion,
                             @Nonnull final BooleanSupplier condicionDeFin,
                             @Nonnull final Runnable incremento,
                             @Nonnull final Runnable ejecutable) {
        for (inicializacion.run(); condicionDeFin.getAsBoolean(); incremento.run()) {
            ejecutable.run();
        }
    }

    /**
     * while - mientras
     * @param condicion
     * @param ejecutable
     */
    public static void mientras(final BooleanSupplier condicion, @Nonnull final Runnable ejecutable) {
        while (condicion.getAsBoolean()) {
            ejecutable.run();
        }
    }

    /**
     * do - haz
     * @param ejecutable
     * @return
     */
    public static SentenciaHazMientras haz(@Nonnull final Runnable ejecutable) {
        return SentenciaHazMientras.haz(ejecutable);
    }

    /**
     * for each - por cada
     * @param collection
     * @param operacion
     * @param <T>
     */
    public static <T> void porCada(@Nonnull final Collection<T> collection, @Nonnull final Consumer<T> operacion) {
        collection.forEach(operacion);
    }

    /**
     * try - intenta
     * @param accion
     * @return
     */
    public static SentenciaIntenta intenta(@Nonnull Runnable accion) {
        return SentenciaIntenta.intenta(accion);
    }

    public static void arroja(final RuntimeException excepcion) {
        throw excepcion;
    }

    public static <T extends RuntimeException> T nueva(final Class<T> tipoDeExcepcion, final String mensaje) {
        try {
            return tipoDeExcepcion.getDeclaredConstructor(String.class).newInstance(mensaje);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            String message = "No se ha podido instanciar la excepcion de tipo " + tipoDeExcepcion.getName();
            message += ". Es probable que haga falta un constructor que reciba el mensaje";
            throw new IllegalStateException(message, e);
        }
    }

    public static <T extends RuntimeException> T nueva(final Class<T> tipoDeExcepcion, final String mensaje, final Throwable cause) {
        try {
            return tipoDeExcepcion.getDeclaredConstructor(String.class, Throwable.class).newInstance(mensaje, cause);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            String message = "No se ha podido instanciar la excepcion de tipo " + tipoDeExcepcion.getName();
            message += ". Es probable que haga falta un constructor que reciba el mensaje y la causa";
            throw new IllegalStateException(message, e);
        }
    }

    public static <T> boolean estaPresente(final Optional<T> optional) {
        return optional.isPresent();
    }

    public static <T> boolean estaVacio(final Optional<T> optional) {
        return optional.isEmpty();
    }

    public static <T> boolean estaVacio(final Collection<T> collection) {
        return collection.isEmpty();
    }
}

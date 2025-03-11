// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SentenciaIntenta {

    private final Runnable ejecutableATratar;
    private final List<Map.Entry<Class<? extends Exception>, Consumer<? extends Exception>>> manejadoresDeExcepcion;

    private SentenciaIntenta(final Runnable ejecutableATratar) {
        this.ejecutableATratar = ejecutableATratar;
        this.manejadoresDeExcepcion = new ArrayList<>();
    }

    public static SentenciaIntenta intenta(@Nonnull Runnable ejecutableATratar) {
        return new SentenciaIntenta(ejecutableATratar);
    }

    public <T extends Exception> SentenciaIntenta atrapa(@Nonnull Class<T> tipoDeExcepcion,
                                                         @Nonnull Consumer<T> manejadorDeExcepcion) {
        manejadoresDeExcepcion.add(Map.entry(tipoDeExcepcion, manejadorDeExcepcion));
        return this;
    }

    public void finalmente(@Nonnull final Runnable ejecutableAlFinal) {
        try {
            ejecutableATratar.run();
        } catch (Exception e) {
            for (Map.Entry<Class<? extends Exception>, Consumer<? extends Exception>> entry : manejadoresDeExcepcion) {
                Class<? extends Exception> type = entry.getKey();
                if (type.isInstance(e)) {
                    Consumer consumer = entry.getValue();
                    consumer.accept(e);
                    return;
                }
            }
            throw e;
        } finally {
            ejecutableAlFinal.run();
        }
    }

    public void ahora() {
        if (manejadoresDeExcepcion.isEmpty()) {
            throw new IllegalArgumentException(
                    "Es necesario atrapar una excepción o proporcionar un ejecutable al final");
        }
        finalmente(() -> {});
    }
}

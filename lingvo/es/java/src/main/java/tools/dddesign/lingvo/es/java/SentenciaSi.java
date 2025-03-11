// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import jakarta.annotation.Nonnull;

/*
 * If - si
 * else if - o si
 * else - si no
 */
public class SentenciaSi {

    private final boolean ejecutado;

    private SentenciaSi(final boolean ejecutado) {
        this.ejecutado = ejecutado;
    }

    private SentenciaSi(boolean condicion, @Nonnull Runnable ejecutable) {
        if (condicion) {
            ejecutable.run();
            ejecutado = true;
        } else {
            ejecutado = false;
        }
    }

    public static SentenciaSi si(final boolean condicion, @Nonnull final Runnable ejecutable) {
        return new SentenciaSi(condicion, ejecutable);
    }

    public SentenciaSi oSi(final boolean condicion, @Nonnull final Runnable ejecutable) {
        if (ejecutado) {
            return this;
        }

        if (condicion) {
            ejecutable.run();
            return new SentenciaSi(true);
        }
        return this;
    }

    public void siNo(@Nonnull final Runnable ejecutable) {
        if (!ejecutado) {
            ejecutable.run();
        }
    }
}

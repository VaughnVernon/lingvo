// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java.acceptance.domain.model;

import static tools.dddesign.lingvo.es.java.Sentencia.nuevaInstancia;

public class RepresentanteLegal {

    private final RFC rfc;
    private final NombreCompleto nombreCompleto;

    private RepresentanteLegal(final RFC rfc, final NombreCompleto nombreCompleto) {
        this.rfc = rfc;
        this.nombreCompleto = nombreCompleto;
    }

    public static RepresentanteLegal con(final RFC rfc, final NombreCompleto nombreCompleto) {
        return nuevaInstancia(RepresentanteLegal.class, rfc, nombreCompleto);
    }

    public RFC rfc() {
        return rfc;
    }

    public NombreCompleto nombreCompleto() {
        return nombreCompleto;
    }
}

// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java.example.domain.model;

import static tools.dddesign.lingvo.es.java.Sentencia.arroja;
import static tools.dddesign.lingvo.es.java.Sentencia.coincide;
import static tools.dddesign.lingvo.es.java.Sentencia.nueva;

/**
 * Equivalent to Tax Payer
 */
public class Contribuyente {

    /** Tax payer id. */
    private RFC rfc;
    /** Legal representative. */
    private RepresentanteLegal representanteLegal;

    protected Contribuyente() { }

    private Contribuyente(final RFC rfc) {
        this.rfc = rfc;
    }

    public static Contribuyente con(final RFC rfc) {
        return new Contribuyente(rfc);
    }

    public void designarRepresentanteLegal(final RFC rfc, final NombreCompleto representanteLegal) {
        coincide(rfc.tipoDePersona())
                .con(TipoDePersona.FISICA, () ->
                        arroja(nueva(ExcepcionOperacionNoPermitida.class,
                                "No es posible asignar un representante legal para una persona física.")))
                .con(TipoDePersona.MORAL, () ->
                        this.representanteLegal = RepresentanteLegal.con(rfc, representanteLegal))
                .nones(() ->
                        arroja(nueva(ExcepcionTipoPersonaInvalido.class,
                                "No es posible asignar un representante para el tipo de persona " + rfc.tipoDePersona() + ".")));
    }

    public RFC rfc() {
        return rfc;
    }
}

// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java.acceptance.application;

import tools.dddesign.lingvo.es.java.acceptance.application.data.DatosContribuyente;
import tools.dddesign.lingvo.es.java.acceptance.domain.model.Contribuyente;
import tools.dddesign.lingvo.es.java.acceptance.domain.model.NombreCompleto;
import tools.dddesign.lingvo.es.java.acceptance.domain.model.RFC;
import tools.dddesign.lingvo.es.java.acceptance.domain.repository.RepositorioDeContribuyente;

import java.util.Optional;

import static tools.dddesign.lingvo.es.java.Sentencia.arroja;
import static tools.dddesign.lingvo.es.java.Sentencia.estaPresente;
import static tools.dddesign.lingvo.es.java.Sentencia.estaVacio;
import static tools.dddesign.lingvo.es.java.Sentencia.nueva;
import static tools.dddesign.lingvo.es.java.Sentencia.obtener;
import static tools.dddesign.lingvo.es.java.Sentencia.si;

/**
 * Tax Payer Service
 */
public class ServicioDeContribuyente {

    /** Tax payer repository. */
    private final RepositorioDeContribuyente repositorio;

    public ServicioDeContribuyente(final RepositorioDeContribuyente repositorio) {
        this.repositorio = repositorio;
    }

    public DatosContribuyente registrarContribuyente(final String rfcRecibido) {
        RFC rfc = RFC.aPartirDe(rfcRecibido);
        Optional<Contribuyente> contribuyenteOpcional = repositorio.encontrarPorRfc(rfc);
        si (estaPresente(contribuyenteOpcional), () ->
            arroja(nueva(ExcepcionContribuyenteYaRegistrado.class,
                    "El contribuyente con RFC " + rfcRecibido + " ya se encuentra registrado."))
        );

        Contribuyente contribuyente = Contribuyente.con(rfc);
        repositorio.almacenar(contribuyente);

        return DatosContribuyente.de(contribuyente);
    }

    public void designarRepresentanteLegal(final String rfcDelContribuyente,
                                           final String rfcDelRepresentante,
                                           final String nombreDelRepresentante,
                                           final String segundoNombreDelRepresentante,
                                           final String apellidoDelRepresentante,
                                           final String segundoApellidoDelRepresentante) {
        RFC rfc = RFC.aPartirDe(rfcDelContribuyente);
        Optional<Contribuyente> contribuyenteOpcional = repositorio.encontrarPorRfc(rfc);
        si (estaVacio(contribuyenteOpcional), () ->
                arroja(nueva(ExcepcionContribuyenteNoEncontrado.class,
                        "No existe el contribuyente con RFC " + rfcDelContribuyente))
        );
        Contribuyente contribuyente = obtener(contribuyenteOpcional);

        RFC rfcRepresentante = RFC.aPartirDe(rfcDelRepresentante);
        Optional<Contribuyente> representanteLegal = repositorio.encontrarPorRfc(rfcRepresentante);
        si (estaVacio(representanteLegal), () ->
                arroja(nueva(ExcepcionContribuyenteNoEncontrado.class,
                        "No existe el representante legal con RFC " + rfcDelRepresentante))
        );

        NombreCompleto nombre = NombreCompleto.de(nombreDelRepresentante, apellidoDelRepresentante)
                .conSegundoNombre(segundoNombreDelRepresentante)
                .conApellidoMaterno(segundoApellidoDelRepresentante);

        contribuyente.designarRepresentanteLegal(rfcRepresentante, nombre);
    }
}

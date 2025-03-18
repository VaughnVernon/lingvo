// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java.acceptance.domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static tools.dddesign.lingvo.es.java.Sentencia.arroja;
import static tools.dddesign.lingvo.es.java.Sentencia.intenta;
import static tools.dddesign.lingvo.es.java.Sentencia.nueva;
import static tools.dddesign.lingvo.es.java.Sentencia.si;

/**
 * Equivalent to Tax Payer ID
 */
public class RFC {

    public static final int TAMANIO_PERSONA_MORAL = 12;
    public static final int TAMANIO_PERSONA_FISICA = 13;
    private static final int TAMANIO_DIGITOS_PARA_FECHA = 2;
    private static final int TAMANIO_INICIALES_PERSONA_MORAL = 3;
    private static final int TAMANIO_INICIALES_PERSONA_FISICA = 4;

    /** Birthday or date of incorporation */
    private LocalDate fechaDeNacimiento;
    private String homoclave;
    /** Tax payer type */
    private TipoDePersona tipoDePersona;
    private String iniciales;
    /** plain value */
    private String valorPlano;

    private RFC(final String rfc) {
        si(rfc.length() != TAMANIO_PERSONA_MORAL && rfc.length() != TAMANIO_PERSONA_FISICA, () ->
            arroja(nueva(ExcepcionDeRfcInvalido.class,
                    "El RFC es incorrecto. Debe ser de 12 caracteres para persona moral o 13 para persona física."))
        );

        AtomicInteger indiceFinIniciales = new AtomicInteger(TAMANIO_INICIALES_PERSONA_MORAL);
        si(rfc.length() == TAMANIO_PERSONA_FISICA, () ->
            indiceFinIniciales.set(TAMANIO_INICIALES_PERSONA_FISICA)
        );

        valorPlano = rfc;
        asignaIniciales(rfc, indiceFinIniciales.get());
        asignaFechaDeNacimiento(rfc, indiceFinIniciales.get());
        asignaHomoclave(rfc);
        asignaTipoDePersona(rfc);
    }

    private void asignaHomoclave(final String rfc) {
        homoclave = rfc.substring(rfc.length() - 3);
    }

    private void asignaTipoDePersona(final String rfc) {
        si(rfc.length() == TAMANIO_PERSONA_MORAL, () ->
            tipoDePersona = TipoDePersona.MORAL
        ).siNo(() ->
            tipoDePersona = TipoDePersona.FISICA
        );
    }

    private void asignaIniciales(final String rfc, final int indiceFinDeIniciales) {
        iniciales = rfc.substring(0, indiceFinDeIniciales);
    }

    public static RFC aPartirDe(final String rfc) {
        return new RFC(rfc);
    }

    public void asignaFechaDeNacimiento(final String rfc, final int indiceInicioFechaDeNacimiento) {
        int indice = indiceInicioFechaDeNacimiento;
        int anio = extraeLosSiguientesDosDigitos(rfc, indice);
        indice += TAMANIO_DIGITOS_PARA_FECHA;
        int mes = extraeLosSiguientesDosDigitos(rfc, indice);
        indice += TAMANIO_DIGITOS_PARA_FECHA;
        int dia = extraeLosSiguientesDosDigitos(rfc, indice);

        intenta(() ->
            this.fechaDeNacimiento = LocalDate.of(anio, mes, dia))
        .atrapa(Exception.class, e -> {
            String fechaDeNacimientoFormateada = String.format("%02d%02d%02d", anio, mes, dia);
            throw new IllegalArgumentException("La fecha " + fechaDeNacimientoFormateada + " no es válida.");
        }).ahora();
    }

    private int extraeLosSiguientesDosDigitos(final String rfc, final int index) {
        String substring = rfc.substring(index, index + TAMANIO_DIGITOS_PARA_FECHA);
        si(noEsNumero(substring), () ->
            arroja(nueva(IllegalArgumentException.class, "El valor " + substring + " no es un número."))
        );
        return Integer.parseInt(substring);
    }

    private boolean noEsNumero(final String numberAsString) {
        for (int i = 0; i < numberAsString.length(); i++) {
            if (!Character.isDigit(numberAsString.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public LocalDate fechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public String homoclave() {
        return homoclave;
    }

    public TipoDePersona tipoDePersona() {
        return tipoDePersona;
    }

    public String iniciales() {
        return iniciales;
    }

    public String valorPlano() {
        return valorPlano;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RFC rfc = (RFC) o;
        return Objects.equals(valorPlano, rfc.valorPlano);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(valorPlano);
    }
}

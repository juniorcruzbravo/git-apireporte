$(document).ready(function () {
    var urlMenu = window.location.protocol+'//'+window.location.host+'/authoriza/menu/opciones';
    $.get(urlMenu, function (listaOpciones) {
        console.log("Informacion", listaOpciones);
        var listPadrePrincipales = getPadrePrincipales(listaOpciones);
        for (var opcion of listPadrePrincipales) {
            esPadre(opcion, listaOpciones)
        }
    });

    function getPadrePrincipales(listaOpciones) {
        var listPadrePrincipales = new Array();
        for (var opcion of listaOpciones) {
            if (opcion.numNivel == 0) {
                listPadrePrincipales.push(opcion);
            }
        }
        console.log(listPadrePrincipales);
        return listPadrePrincipales;
    }

    function esPadre(opcion, listaOpciones) {
        console.log("Sera padre", opcion);
        if (opcion.txtUrl == '#') {
            console.log("Si es padre", opcion);
            crearPadre(opcion);
            buscarHijos(opcion, listaOpciones);
        } else {
            console.log("Si es hijo", opcion);
            crearHijo(opcion);
        }
    }

    function buscarHijos(opcion, listaOpciones) {
        console.log("Buscando Hijos");
        for (var hijo of listaOpciones) {
            if (hijo.idOpcionPadre == opcion.idOpciones) {
                esPadre(hijo, listaOpciones);
            }
        }
    }

    function crearPadre(opcion) {
        var idDominio = "#Opcion" + opcion.idOpcionPadre;
        var idLista = "Opcion" + opcion.idOpciones;
        console.log("Padre", opcion);
        console.log("idDominio", idDominio);
        console.log("idLista", idLista);
        var paddingleft = 20 + 5 * opcion.numNivel;
        var insertHtml = "<li style='padding: 10px 20px;padding-left:" + paddingleft + "px;'><a href='" + "#" + idLista + "' data-toggle='collapse' aria-expanded='false' class='dropdown-toggle'>" + opcion.txtDescripcion + "</a> " +
            "<ul class='collapse list-unstyled' id='" + idLista + "'></ul></li>";
        $(idDominio).append(insertHtml);

    }

    function crearHijo(opcion) {
        var idDominio = "#Opcion" + opcion.idOpcionPadre;
        var idHijo = "Opcion" + opcion.idOpciones;
        var attrlink = opcion.txtUrl;
        var paddingleft = 20 + 5 * opcion.numNivel;
        var insertHtml = "<li style='padding: 10px 20px;padding-left:" + paddingleft + "px;'><a id='" + idHijo + "'>" + opcion.txtDescripcion + "</a></li>";
        $(idDominio).append(insertHtml);
        $("#" + idHijo).attr('href', attrlink);
    }
});
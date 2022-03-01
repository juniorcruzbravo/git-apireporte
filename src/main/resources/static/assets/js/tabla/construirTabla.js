(function($) {
    var HttpCodes = {
        success  : 200,
        notFound : 404,
        created  : 201
    };
    $.fn.tablaSas=function(json){
        this.each(function() {
            var elem = $( this );
            var urlDatos=json.url;
            limpiarTabla(this);
            iniciarlizarTabla(this);
            pintarCabeceraTitulo(this,json.columns);
            traerInformacion(this,urlDatos,json.columns);
        });
    };
    function limpiarTabla(etiqueta){
        $(etiqueta).html("");
    }

    function iniciarlizarTabla(etiqueta){
        $(etiqueta).append("<table><thead></thead><tbody></tbody></table>");
    }

    function pintarCabeceraTitulo(etiqueta,columna){
        $(etiqueta).find("table thead").append("<tr>");
        for(var column of columna){
            crearCabecera(etiqueta,column.title);
        }
        $(etiqueta).find("table thead").append("</tr>");
    }
    function crearCabecera(etiqueta,header){
        $(etiqueta).find("table thead tr").append("<th>"+header+"</th>");
    }
    function pintarCuerpo(etiqueta,data,columnas){
        var i=0;
        for(var datajson of data){
            nombre='row'+i;
            $(etiqueta).find("table tbody").append("<tr class='"+nombre+"'>");
            for(var column of columnas){
                crearCuerpo(etiqueta,datajson[column.field,nombre]);
            }
            $(etiqueta).find("table tbody").append("</tr>");
            i++;
        }
    }
    function crearCuerpo(etiqueta,cuerpo,nombre){
        if(cuerpo==null)cuerpo="";
        $(etiqueta).find("table tbody ."+nombre).append("<th style='color:red'>"+cuerpo+"</th>");
    }
    function traerInformacion(etiqueta,url,columnas){
        var token = $('#_csrf').attr('content');
        var header = $('#_csrf_header').attr('content');
        $.ajax({
            type:"GET",
            contentType : "application/json",
            accept: 'text/plain',
            url : url,
            data : null,
            dataType: 'text',
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success:function(result,textStatus,xhr){
                if(xhr.status==HttpCodes.success){
                    var entrada=JSON.parse(result);
                    pintarCuerpo(etiqueta,entrada,columnas);
                }
            }
        });
    }
}(jQuery));
$(document).ready( function() {
    var urlActual=window.location;
    $("#grid").tablaSas({
        url:urlActual+"list",
        columns:[
            {title:"Codigo",field:"codPerfil"},
            {title:"Descripcion",field:"txtDescripcion"},
            {title:"Fecha de Inicio",field:"fecInicio"},
            {title:"Fecha de Fin",field:"fecFin"}
        ]
    });
});



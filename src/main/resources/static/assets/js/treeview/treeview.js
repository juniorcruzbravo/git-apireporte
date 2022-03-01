$(function() {
    $.fn.treeview=function(data){
        this.each(function(){
            $(this).empty();
            crearTreeView(this,data);
            $(this).off();
            $(this).on('change',' input[type="checkbox"]', checkboxChanged);
            $(this).on('click','.toggle', ocultarRama);
        });
    };

    $.fn.treedata=function(){
        var lista = new Array();
        this.each(function() {
            $(this).find('input[type="checkbox"]:checked').each(function (data) {
                var id = $(this).attr("id").substring(5, $(this).attr("id").length);
                lista.push(id)
            });
        });
        return lista;
    };

    function crearTreeView(etiqueta,data){
        var token = $('#_csrf').attr('content');
        var header = $('#_csrf_header').attr('content');
        $.ajax({
            type:"GET",
            contentType : "application/json",
            accept: 'text/plain',
            url : data.url,
            data : null,
            dataType: 'text',
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success:function(result,textStatus,xhr){
                if(xhr.status==HttpCodes.success){
                    var listaOpciones=JSON.parse(result);
                    var idOpcion='Lista0';
                    $(etiqueta).append("<ul id='"+idOpcion+"' class='treeview'></ul>");
                    buscarHijos(idOpcion,listaOpciones);
                }
            }
        });
    }

    function buscarHijos(etiqueta,data){
        for(var nodo of data){
            esPadre(etiqueta,nodo);
        }
    }
    function esPadre(etiqueta,nodo){
        if(!nodo.havechildren){
            crearHijo(etiqueta,nodo);
        }else{
            crearPadre(etiqueta,nodo);
            var idetiqueta="Lista"+nodo.id;
            buscarHijos(idetiqueta,nodo.children);
        }
    }


    function crearPadre(etiqueta,opcion){
        var idOpcion="Lista"+opcion.id;
        var idCheck="Check"+opcion.id;
        //console.log("etiqueta padre",etiqueta);
        var checked="";
        if(opcion.seleccionado){
            checked ="checked";
        }
        $("#"+etiqueta).append("<li><span attr-data='"+opcion.id+"' style='margin-left: -18px;color:#5990e0;margin-right:5px;cursor: pointer;' class='toggle' attr-opcion='plus'><span class='icon"+opcion.id+"'><i class=\"far fa-plus-square\"></i></span></span><input type='checkbox' "+checked+" class='form-check-input' id='"+idCheck+"'><label for='"+idCheck+"' class='form-check-label'><span style='color:#5990e0;margin-right:5px; '><i class='fas fa-folder'></i></span>"+opcion.text+"</label><ul id='"+idOpcion+"'></ul></li>");
    }
    function crearHijo(etiqueta,opcion){
        //console.log("etiqueta hijo",etiqueta);
        var idCheck="Check"+opcion.id;
        var checked="";
        if(opcion.seleccionado){
            checked ="checked";
        }
        $("#"+etiqueta).append("<li><input type='checkbox' "+checked+" class='form-check-input' id='"+idCheck+"'><label for='"+idCheck+"' class='form-check-label'><span style='color:#5990e0;margin-right:5px; '><i class=\"far fa-file-alt\"></i></span>"+opcion.text+"</label></li>");
    }
    var i=0;
    function checkboxChanged() {
        var $this = $(this),
            checked = $this.prop("checked"),
            container = $this.parent(),
            siblings = container.siblings();

        container.find('input[type="checkbox"]')
            .prop({
                checked: checked
            })
            .siblings('label');

        checkSiblings(container, checked);
    }

    function checkSiblings($el, checked) {
        var parent = $el.parent().parent(),
            all = true,
            indeterminate = false;

        $el.siblings().each(function() {
            return all = ($(this).children('input[type="checkbox"]').prop("checked") === checked);
        });

        if (all && checked) {
            parent.children('input[type="checkbox"]')
                .prop({
                    checked: checked
                })
                .siblings('label');

            checkSiblings(parent, checked);
        }
        else if (all && !checked) {
            parent.children('input[type="checkbox"]')
                .prop("checked", checked)
                .siblings('label');
            console.log()
            checkSiblings(parent, checked);
        }
        else {
            $el.parents("li").children('input[type="checkbox"]')
                .prop({
                    checked: true
                })
                .siblings('label');
        }
    }

    function ocultarRama(){
        var $this=$(this);
        console.log("entro al boton ")
        var id=$this.attr("attr-data");
        var opcion=$this.attr("attr-opcion");
        if(opcion=='plus'){
            $this.attr("attr-opcion","rest");
            $(".icon"+id).html("<i class=\"fas fa-minus\"></i>");
        }else{
            $(".icon"+id).html("<i class='far fa-plus-square'></i>");
            $this.attr("attr-opcion","plus");
        }
        $("#Lista"+id).toggle("slow");
    }
});
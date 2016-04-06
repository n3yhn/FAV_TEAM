<%-- 
    Document   : note
    Created on : 30/10/2012, 8:05:07 AM
    Author     : dungnt78
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<form id="noteForm" style="display: none;">
    <sd:TextBox cssStyle="width:100%"
                id="noteForm.noteId"
                key=""
                name="noteForm.noteId">
    </sd:TextBox>
    <sd:TextBox cssStyle="width:100%"
                id="noteForm.height"
                key=""
                name="noteForm.height">
    </sd:TextBox>
    <sd:TextBox cssStyle="width:100%"
                id="noteForm.width"
                key=""
                name="noteForm.width">
    </sd:TextBox>
    <sd:TextBox cssStyle="width:100%"
                id="noteForm.text"
                key=""
                name="noteForm.text">
    </sd:TextBox>
    <sd:TextBox cssStyle="width:100%"
                id="noteForm.posX"
                key=""
                name="noteForm.posX">
    </sd:TextBox>
    <sd:TextBox cssStyle="width:100%"
                id="noteForm.posY"
                key=""
                name="noteForm.posY">
    </sd:TextBox>
</form>
<script type="text/javascript">
    var edited = function(note) {
        sd._("noteForm.noteId").setValue(note.id);
        sd._("noteForm.height").setValue(note.height);
        sd._("noteForm.width").setValue(note.width);
        sd._("noteForm.posX").setValue(note.pos_x);
        sd._("noteForm.posY").setValue(note.pos_y);
        sd._("noteForm.text").setValue(note.text);
        sd.connector.post("noteAction!update.do", null, "noteForm", null, null);

    }
    var created = function(note) { 
        note = {
            'id' : jQuery.fn.stickyNotes.notes.length + 1, 
            'height' : 162, 
            'width' : 162, 
            'pos_x' : 10, 
            'pos_y' :10 ,
            'text' : ' '
        };
        sd._("noteForm.noteId").setValue(note.id);
        sd._("noteForm.height").setValue(note.height);
        sd._("noteForm.width").setValue(note.width);
        sd._("noteForm.posX").setValue(note.pos_x);
        sd._("noteForm.posY").setValue(note.pos_y);
        sd._("noteForm.text").setValue(note.text);
        sd.connector.post("noteAction!insert.do", null, "noteForm", null, function(data) {
            var obj = dojo.fromJson(data);
            var result = obj.customInfo;
            sd._("noteForm.noteId").setValue(result);
            var pos_x = note.pos_x;
            var pos_y = note.pos_y;
            note.id = result;
            if(jQuery.fn.stickyNotes.notes == null) jQuery.fn.stickyNotes.notes = [];
            jQuery.fn.stickyNotes.notes.push(note);
            
            var note_id = note.id;   
            var _note_content = jQuery(document.createElement('textarea'));
            var _div_note = jQuery(document.createElement('div')).addClass('jStickyNote');
            var _div_background = jQuery.fn.stickyNotes.createNoteBackground();
            _div_note.append(_note_content);

            var _div_delete = $(document.createElement('div'))
            .addClass('jSticky-delete')
            .click(function(){
                jQuery.fn.stickyNotes.deleteNote(this);
            });
            var _div_wrap = $(document.createElement('div'))
            .css({
                'position':'absolute',
                'top':pos_x,
                'left':pos_y, 
                'float' : 'left'
            })
            .attr("id", "note-" + note_id)
            .append(_div_background)
            .append(_div_note)
            .append(_div_delete);	
            _div_wrap.addClass('jSticky-medium');		
            if (jQuery.fn.stickyNotes.options.resizable) {
                _div_wrap.resizable({
                    stop: function(event, ui) {
                        jQuery.fn.stickyNotes.resizedNote(note_id)
                    }
                });
            }
            _div_wrap.draggable({
                containment: '#sticky-container', 
                scroll: false, 
                stop: function(event, ui){
                    jQuery.fn.stickyNotes.movedNote(note_id)
                }
            }); 

            $('#sticky-container').append(_div_wrap);
            jQuery.fn.stickyNotes.setCurrentlyEditedNote(note_id);
            jQuery("#note-" + note_id).click(function() {
                return false;
            })
            jQuery("#note-" + note_id).find("textarea").focus();
            jQuery(_note_content).css({
                'width': jQuery("#note-" + note_id).width() - 44, 
                'height': jQuery("#note-" + note_id).height() - 15
            });
        });
    }
			
    var deleted = function(note) {
        sd._("noteForm.noteId").setValue(note.id);
        sd._("noteForm.height").setValue(note.height);
        sd._("noteForm.width").setValue(note.width);
        sd._("noteForm.posX").setValue(note.pos_x);
        sd._("noteForm.posY").setValue(note.pos_y);
        sd._("noteForm.text").setValue(note.text);
        sd.connector.post("noteAction!delete.do", null, "noteForm", null, null);
    }
			
    var moved = function(note) {
        sd._("noteForm.noteId").setValue(note.id);
        sd._("noteForm.height").setValue(note.height);
        sd._("noteForm.width").setValue(note.width);
        sd._("noteForm.posX").setValue(note.pos_x);
        sd._("noteForm.posY").setValue(note.pos_y);
        sd._("noteForm.text").setValue(note.text);
        sd.connector.post("noteAction!update.do", null, "noteForm", null, null);
    }	
			
    var resized = function(note) {
        sd._("noteForm.noteId").setValue(note.id);
        sd._("noteForm.height").setValue(note.height);
        sd._("noteForm.width").setValue(note.width);
        sd._("noteForm.posX").setValue(note.pos_x);
        sd._("noteForm.posY").setValue(note.pos_y);
        sd._("noteForm.text").setValue(note.text);
        sd.connector.post("noteAction!update.do", null, "noteForm", null, null);
    }					
        
</script>
<div style="display: none;">
    <sd:DataGrid id="noteGrid"
                 getDataUrl=""
                 rowSelector="0%"
                 style="width:auto;height:auto"
                 rowsPerPage="10"
                 serverPaging="true"
                 clientSort="false"
                 >
        <sd:ColumnDataGrid  key="note.height" field="height"
                            width="50%"  headerStyles="text-align:center;" />
        <sd:ColumnDataGrid  key="note.width" field="width"
                            width="50%"  headerStyles="text-align:center;" />
        <sd:ColumnDataGrid  key="note.text" field="text"
                            width="50%"  headerStyles="text-align:center;" />
        <sd:ColumnDataGrid  key="note.posX" field="posX"
                            width="50%"  headerStyles="text-align:center;" />
        <sd:ColumnDataGrid  key="note.posY" field="posY"
                            width="50%"  headerStyles="text-align:center;" />
    </sd:DataGrid>
</div>
<script type="text/javascript">
    callbackAfterLoad = function(data){
        var grid = dijit.byId("noteGrid");
        var notes = []; 
        for ( var i=0; i<grid._by_idx.length;i++){
            var processNote = grid.getItem(i);
            notes.push(
            {
                "id": processNote.noteId[0],
                "text": processNote.text[0],
                "pos_x": processNote.posX[0],
                "pos_y": processNote.posY[0],	
                "width": processNote.width[0],							
                "height": processNote.height[0]
            })
        };
        var options = {
            notes: notes      
            ,resizable: true
            ,controls: false 
            ,editCallback: edited
            ,createCallback: created
            ,deleteCallback: deleted
            ,moveCallback: moved					
            ,resizeCallback: resized					

        };
        jQuery(".notes").stickyNotes(options);
        jQuery("#add_note").click(function() {
            jQuery.fn.stickyNotes.createNote();
            return false;
        });
        dijit.byId("noteGrid").vtReload("noteAction!getListNote.do", null,null, callbackAfterLoad);
  

</script>
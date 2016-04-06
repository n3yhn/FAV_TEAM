<%-- 
    Document   : newtag_file
    Created on : Feb 29, 2012, 3:48:13 PM
    Author     : thanhhh1
--%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="action" description="Action thực hiện upload file" required="true"%>
<%@attribute name="extension" type="java.lang.String" required="true"
             description="Các đuôi file cho phép. Ví dụ: *.jpg,*.JPG,*.png,*.PNG,*.gif,*.GIF,*.bmp,*.BMP"%>
<%@attribute name="maxSize" required="true"%>
<%@attribute name="id" required="true"%>
<%@attribute name="auto" description="Tự động upload hoặc không"%>
<%@attribute name="created"%>
<%@attribute name="disabled"%>
<%request.setAttribute("contextPath", request.getContextPath());%>
<script type="text/javascript" src="">
    var dis = false;
    <c:if test="${not empty disabled}">
        dis = ${disabled};        
    </c:if>    
        if(dis == true)
            document.getElementById('browse${fn:escapeXml(id)}').style.display='none';
        setVisible = function(id, visible){
            var id = 'browse' + id;
            document.getElementById(id).style.display=visible;
        }
        function upclickSh(d){        
            var g={
                element:null,
                action:"about:blank",
                action_params:{},
                maxsize:0,
                onstart:null,
                oncomplete:null,
                dataname:"Filedata",
                target:null,
                zindex:"auto"
            };
        
            for(var h in g)d[h]=d[h]?d[h]:g[h];var k=d.element;
            if(typeof k=="string")k=document.getElementById(k);
            var e=k.ownerDocument,b,c=e.createElement("div"),n="frame"+(new Date).getTime().toString().substr(8);
            c.innerHTML='<iframe name="'+n+'" src="about:blank" onload="this.onload_callback()"></iframe>';
            var i=c.childNodes[0];
            i.onload_callback=
                function(){
                var a=e.createElement("form");
                c.appendChild(a);
                a.method="post";
                a.enctype="multipart/form-data";
                a.encoding="multipart/form-data";
                if(d.target){
                    a.target=d.target;
                    a.setAttribute("target",d.target)
                }else{
                    a.target=n;
                    a.setAttribute("target",n)
                }
                a.action=d.action;
                a.setAttribute("action",d.action);
                a.style.margin=0;
                a.style.padding=0;
                a.style.height="80px";
                a.style.width="40px";
                a.runat="server";
                var j=d.action_params;
                for(var p in j){
                    var m=e.createElement("input");
                    m.type="hidden";
                    m.name=p;
                    m.value=String(j[p]);
                    a.appendChild(m)
                }
                if(d.maxsize){
                    j=e.createElement("input");
                    j.type="hidden";
                    j.name="MAX_FILE_SIZE";
                    j.value=String(d.maxsize);
                    a.appendChild(j)
                }

                b=e.createElement("input");
                b.name=d.dataname;
                b.id=d.id;
                b.type="file";
                b.size="1";
                b.runat="server";
                a.appendChild(b);
                b.style.position="absolute";
                b.style.display="block";
                b.style.top=0;
                b.style.left=0;
                b.style.height=a.style.height;
                b.style.width="80px";
                b.style.opacity=0;
                b.style.filter="alpha(opacity=0)";
                b.style.fontSize=8;
                b.style.zIndex=1;
                b.style.visiblity="hidden";
                b.style.marginLeft=
                    "-40px";
                var o=function(){                        
                    if(b.value){
                        var f=d.onstart;
                        if(f&&f(b.value)) a.submit();
                        //                    var isValid=page.onSelectFile;
                        //                    if(isValid) a.submit()
                    }
                };
                var s=function(){
                    if(b.value){
                        var f=d.onselectfile;
                        f&&f(b.value);
                        if(isValid) a.submit()
                    }
                }
                if(b.addEventListener)b.addEventListener("change",o,false);            
                else if(b.attachEvent)b.attachEvent("onpropertychange",function(f){
                    if(!f)f=window.event;
                    f.propertyName=="value"&&o()
                });else b.onpropertychange=o;

                i.onload_callback=function(){
                    var f=null;
                    if(i.contentWindow)f=i.contentWindow;
                    else if(i.contentDocument)f=i.contentDocument.defaultView;
                    f=f.document.body.innerHTML;
                    var q=d.oncomplete;
                    q&&q(f);
                    a.reset()
                }
            };

            i.style.display=
                "none";
            i.width=0;
            i.height=0;
            i.marginHeight=0;
            i.marginWidth=0;
            e.body.insertBefore(c,e.body.firstChild);
            c.style.position="absolute";
            c.style.overflow="hidden";
            c.style.padding=0;
            c.style.margin=0;
            c.style.visiblity="hidden";
            c.style.width="0px";
            c.style.height="0px";
            if(d.zindex=="auto"){
                g=0;
                var l;
                for(h=k;h.tagName!="BODY";){
                    l=h.currentStyle?h.currentStyle:getComputedStyle(h,null);
                    l=parseInt(l.zIndex);
                    l=isNaN(l)?0:l;
                    g+=l+1;
                    h=h.parentNode
                }
                c.style.zIndex=g
            }else c.style.zIndex=d.zindex;
            g=function(a){
                if(!a)a=
                    window.event;
                c.style.width="0px";
                c.style.height="0px";
                if(e.elementFromPoint(a.clientX,a.clientY)===k){
                    c.style.width="40px";
                    c.style.height="80px"
                }
            };

            if(c.addEventListener)c.addEventListener("mousemove",g,false);else c.attachEvent&&c.attachEvent("onmousemove",g);
            g=function(a){
                if(!a)a=window.event;
                var j=y=0;
                if(a.pageX)j=a.pageX;
                else if(a.clientX)j=a.clientX+(e.documentElement.scrollLeft?e.documentElement.scrollLeft:e.body.scrollLeft);
                if(a.pageY)y=a.pageY;
                else if(a.clientY)y=a.clientY+(e.documentElement.scrollTop?
                    e.documentElement.scrollTop:e.body.scrollTop);
                c.style.left=j-20+"px";
                c.style.top=y-40+"px";
                c.style.width="40px";
                c.style.height="80px"
            };

            if(k.addEventListener)k.addEventListener("mousemove",g,false);else k.attachEvent&&k.attachEvent("onmousemove",g)
        
        };

</script>
<table id='browse${fn:escapeXml(id)}tb'>
</table>
<img src="${fn:escapeXml(contextPath)}/share/images/icons/browse2.png" id='browse${fn:escapeXml(id)}'/>
<img src="${fn:escapeXml(contextPath)}/share/images/loading/loading2.gif" width='29px' height='29px' id='imgLoading${fn:escapeXml(id)}' style="display: none;"/>
<script type="text/javascript">
    
    openFile = function(id){
        var url = "uploadiframe!openFile.do?attachId=" + id;
        var target = "divOpenFile";        
        sd.connector.post(url, target,null,null,null);
    }
    
    removeThis = function(trId, tbId){          
        var table = document.getElementById(tbId);
        var tr = document.getElementById(trId);
        var attachId = tr.getAttribute("id");
        var url = "uploadiframe!removeFile.do?attachId=" + attachId;
        sd.connector.post(url, null,null,null,null);
        table.removeChild(tr);
    }
    uploadNewRowSh =function(name, attachId, id){
        alert(attachId);
        //        clearAttFile(id);
        var tbId = 'browse' + id + 'tb';
        var table = document.getElementById(tbId);
        var tds = table.getElementsByTagName("tr");
        var tr = document.createElement("tr");
        var ranDomId = attachId;
        tr.setAttribute("id", ranDomId);
        var tdName = document.createElement("td");
        var source = "<a href='uploadiframe!openFile.do?attachId=" + attachId+"' >"+name+"</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<span id='CheckSh'><span href='#' id='nameSh' onclick='onSaveDigitizingSh("+attachId+");'>Số hóa</span></span>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;";
        if(dis != true)
            source += "<img src='${contextPath}/share/images/delete.png' width='12' height='12' onclick='removeThis(\""+ranDomId+"\"" + "," + "\"" + tbId + "\")'/>" ;
        tdName.innerHTML = source;
        tr.appendChild(tdName);
        table.appendChild(tr);
    }
    onSaveDigitizingSh = function(attachId){
        sd.connector.post("voAttach!getFileName.do?attachId="+attachId,null,null,null, function(data){
            var obj = dojo.fromJson(data);
            var fileName = obj.customInfo;
            if(page.isValidExtForSH(fileName)){
                var url = "digitizing!onSaveDigitizingSh.do?attachId="+attachId+"&" + token.getTokenParamString();
                sd.connector.post(url,null,null,null,callBackDigitizingProfileSh);
            }
        });
    }
    
    callBackDigitizingProfileSh = function(data){
        data = dojo.fromJson(data);
        if(data.customInfo[0] != 0 && data.customInfo[0] !=''){
            if(data.customInfo[0] =='undefined'){
                alert('Lỗi không cập nhật số hóa được');
            }else{
                document.getElementById("CheckSh").innerHTML = "<span id='nameSh' onclick='statusDigitizingSh("+data.customInfo[0]+");'>Cập nhật số hóa</span>";
            }
        }
    }
    statusDigitizingSh = function(id){
        var url = "digitizing!checkStatus.do?attachId="+id;
        sd.connector.post(url,null,null,null,callBackStatusSh);
    }
    callBackStatusSh = function(data){
        data = dojo.fromJson(data);
        if(data.customInfo[0]=="Waiting"){
            alert('Chờ nhận dạng');
            //            document.getElementById("CheckSh").innerHTML = "<a href='digitizing!downloadXL.do?fileId="+data.customInfo[0]+"' style='cursor:hand;cursor:pointer;'>"+ "Download" +"</a>";
        }else if(data.customInfo[0]=="Proccessing"){
            alert('Đang nhận dạng');
        }else if(data.customInfo[0]=="Complete"){
            document.getElementById("CheckSh").innerHTML = "<a href='digitizing!downloadXL.do?fileId="+data.customInfo[0]+"' style='cursor:hand;cursor:pointer;'>"+ "Download" +"</a>";
        }else{
            alert('Lỗi không nhận dạng được');
        }
    }
    uploadNewRowReadonly =function(name, attachId, id){
        var tbId = 'browse' + id + 'tb';
        var table = document.getElementById(tbId);
        var tds = table.getElementsByTagName("tr");
        var tr = document.createElement("tr");
        var ranDomId = attachId;
        tr.setAttribute("id", ranDomId);
        var tdName = document.createElement("td");
        var source = "<a href='uploadiframe!openFile.do?attachId=" + attachId+"' >"+name+"</a>";
        tdName.innerHTML = source;
        tr.appendChild(tdName);
        table.appendChild(tr);
    }

    getAttacthFileSh = function(objectId, objectType, id){
        sd.connector.post("uploadiframe!getAttachFile.do?objectId=" + objectId + "&objectType=" + objectType + "&id=" + id,null,null,null,callBackGetAttachSh);
    }
    
    callBackGetAttachSh = function(data){
        data = dojo.fromJson(data);
        var id = data.customInfo;        
        var result = data.items;
        var ids = result[0];
        var names = result[1];
        var idSHs = result[2];
        for(var i = 0; i < ids.length; i++){
            uploadNewRowSh(names[i], ids[i], id,true);
            if(idSHs[i] != null && idSHs[i] != ""){
                var url = "digitizing!checkStatus.do?attachId="+idSHs[i];
                sd.connector.post(url,null,null,null,callViewStatusSh);
            }
        }
    }
    
    callViewStatusSh = function(data){
        data = dojo.fromJson(data);
        if(data.customInfo[0]=="Waiting"){
            document.getElementById("CheckSh").innerHTML = "<span id='nameSh' onclick='statusDigitizingSh("+data.customInfo[1]+");'>Cập nhật số hóa</span>";
        }else if(data.customInfo[0]=="Proccessing"){
            document.getElementById("CheckSh").innerHTML = "<span id='nameSh' onclick='statusDigitizingSh("+data.customInfo[1]+");'>Cập nhật số hóa</span>";
        }else if(data.customInfo[0]=="Complete"){
            document.getElementById("CheckSh").innerHTML = "<a href='digitizing!downloadXL.do?fileId="+data.customInfo[1]+"' style='cursor:hand;cursor:pointer;'>"+ "Download" +"</a>";
        }else{
            alert('Lỗi không nhận dạng được');
        }
    }
    
    getAttacthFileReadonly = function(objectId, objectType, id){
        sd.connector.post("uploadiframe!getAttachFile.do?objectId=" + objectId + "&objectType=" + objectType + "&id=" + id,null,null,null,callBackGetAttachReadonly);
    }
    
    callBackGetAttachReadonly = function(data){
        data = dojo.fromJson(data);
        var id = data.customInfo;        
        var result = data.items;
        var ids = result[0];
        var names = result[1];
        for(var i = 0; i < ids.length; i++){
            uploadNewRowReadonly(names[i], ids[i], id);
        }
    }
    
    getListAttachId = function(id){
        var tbId = "browse" + id + "tb";
        var table = document.getElementById(tbId);
        var rows = table.rows;
        var arrId = "";
        for(var i = 0; i < rows.length; i++){
            arrId += rows[i].id + ";";
        }
        return arrId;
    }
    
    clearAttFile = function(id){
        var tbId = "browse" + id + "tb";
        var table = document.getElementById(tbId);
        for(var i = table.rows.length - 1; i >= 0; i--){
            table.deleteRow(i);
        }
    }
    
    var uploader = document.getElementById('browse${fn:escapeXml(id)}');
    upclickSh(
    {
        element: uploader,
        action: '${fn:escapeXml(action)}',
        maxsize:${fn:escapeXml(maxSize)},
        id:'${fn:escapeXml(id)}',
        onstart:function(filename)
        {
            var isValid = false;
            var file = document.getElementById('${fn:escapeXml(id)}');
            var files = file.files;
            if(!files){//for IE
                if(file.size > 0){                
                    var fileName = file.value;
                    if(!page.isValidExtSh(fileName)) return false;
                }
            }else{
                if(document.getElementById('${fn:escapeXml(id)}').files.length>0){
                    var fileName=document.getElementById('${fn:escapeXml(id)}').files[0].name;
                    var size=document.getElementById('${fn:escapeXml(id)}').files[0].size;
                    if(!page.isValidExtSh(fileName)) return false;
                    if(!page.isValidSize(size)) return false;
                }
            }
            document.getElementById('imgLoading${fn:escapeXml(id)}').style.display='';
            return true;
        },
        oncomplete:function(result){
            if(page.displayFile){
                page.displayFile('${fn:escapeXml(id)}')
            }
            document.getElementById('imgLoading${fn:escapeXml(id)}').style.display='none';
        }
    });
</script>
<script>
    page.onSelectFile=function(){
        var isValid = false;        
        if(document.getElementById('${fn:escapeXml(id)}').files.length>0){
            var fileName=document.getElementById('${fn:escapeXml(id)}').files[0].name;
            var size=document.getElementById('${fn:escapeXml(id)}').files[0].size;
            if(!page.isValidExtSh(fileName)) return false;            
            if(!page.isValidSize(size)) return false;
        }
        return true;
    }
    page.getFileExtension=function(fileName)
    {
        var ext = /^.+\.([^.]+)$/.exec(fileName);
        return ext == null ? "" : ext[1];
    }
    page.isValidExtSh=function(fileName) {
        //        var ext = page.getFileExtension(fileName);
        //        var allExt = "${fn:escapeXml(extension)}";        
        //        if(allExt.toLowerCase().indexOf(ext.toLowerCase()) != -1) return true;
        //        msg.alert("File đã chọn không đúng định dạng cho phép ("+allExt+")", "<sd:Property>confirm.title</sd:Property>");
        //        return false;
        return true;
    }
    
    page.isValidExtForSH = function(fileName) {
        var ext = page.getFileExtension(fileName);
        var allExt = "${fn:escapeXml(extension)}";        
        if(allExt.toLowerCase().indexOf(ext.toLowerCase()) != -1) return true;
        msg.alert("Chỉ cho phép Số hóa với các định dạng file ("+allExt+")", "<sd:Property>confirm.title</sd:Property>");
        return false;
    }
        
    page.isValidSize=function(size) {
        if (size/1024/1024>${fn:escapeXml(maxSize)}){
            msg.alert("File đã chọn dung lượng không được vượt quá ${fn:escapeXml(maxSize)}M", "<sd:Property>confirm.title</sd:Property>");
            return false;
        }
        return true;
    }

    page.choseFile=function(){
        //document.getElementById('uploadIframe.file').click();
    }
</script>

<%-- 
    Document   : view
    Created on : 09/01/2013, 11:32:13 AM
    Author     : dungnt78
--%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <style type="text/css">
            /* Style Definitions */
            p.MsoNormal, li.MsoNormal, div.MsoNormal{
                margin:0in;
                margin-bottom:.0001pt;
                font-size:12.0pt;
                font-family:"Times New Roman","serif";}

            p{
                margin-right:0in;
                margin-left:0in;
                font-size:12.0pt;
                font-family:"Times New Roman","serif";}

            .MsoChpDefault{
                font-family:"Calibri","sans-serif";}

            .MsoPapDefault{
                margin-bottom:10.0pt;
                line-height:115%;}

            @page WordSection1{
                size:8.5in 11.0in;
                margin:28.35pt 56.7pt 42.55pt 62.35pt;
            }

            div.WordSection1{
                page:WordSection1;
            }
        </style>       

        <style type="text/css">
            /*mini reset*/
            html, body, div, form, fieldset, legend, label { margin: 0; padding: 0; }
            table { border-collapse: collapse; border-spacing: 0; }
            th, td { text-align: left; vertical-align: top; }
            h1, h2, h3, h4, h5, h6, th, td, caption { font-weight:normal; }
            img { border: 0; }

            /*basics*/
            html, body { margin:0; padding:0; height:100%; width:100%; overflow:hidden; font-size:12px; font-family:Arial; }          

            /*page borders*/
            .docviewer .doc .page {
                outline:none;
                box-shadow:1px 1px 3px #AAA;
                border-style: solid; border-width: 1px; border-color: #DDD #BBB #BBB #DDD; top:-1px; left:-1px;
            }
        </style>
        <style type="text/css">
            /*Layout*/
            .toolbar { position:absolute; top:0; left:0; right:0; border-color:#fff #fff #bbb; }
            .docviewer { position:absolute; top:36px; bottom:0; left:0; right:0; }
        </style>

        <style type="text/css">
            .docviewer { padding:0; } 
            .docviewer .doc { margin:0; padding:0; position:absolute; height:100%; width:100%; overflow:auto; overflow-y:scroll; } 
            .docviewer .doc { font-size:10px; }             
            .docviewer .page-outer { margin:15px auto; padding:5px 18px; position:relative; } 
            .docviewer .page { background:white; position:relative; overflow:hidden; }             
            .docviewer .doc { background:#eee; } 
            .docviewer .page { outline:1px solid #BBB; }
        </style>
    </head>
    <body>
        <!--Toolbar-->
        <style type="text/css">
            .toolbar {
                cursor:default; border-width:1px 0; border-style:solid none; height:34px; font-size:0; white-space:nowrap;
                background:#f7f7f7;                
            }

            /*layout*/
            .toolbar .zoom-btns,
            .toolbar .toolbar-btns { display:inline-block; float:left; vertical-align:top; margin-top:3px; }
            .toolbar .page-nav { position:absolute; top:0; left:0; width:100%; height:0; overflow:visible; text-align:center; margin-top:3px; }
            .toolbar .zoom-btns { margin-left:2px; }
            .toolbar .zoom-btns .btn { margin-left:4px; }
            .toolbar .toolbar-btns .btn { margin-left:8px; }

            /*logo*/
            .toolbar .logo {
                position:absolute; width:90px; height:28px; right:12px; top:5px;
                background:url("sprites.png") no-repeat -9px -112px; }
            .toolbar .logo .text { position:absolute; right:3px; top:0; font-size:9px; color:#999; text-shadow:0 1px #FFF; }

            /*button style*/
            .toolbar .btn { cursor:pointer; color:#222; border:1px solid; border-radius:2px; display:inline-block; vertical-align:top; }
            .toolbar .btn .icon { width:30px; height:25px; display:inline-block; }
            .toolbar .btn .text { height:14px; line-height:14px; font-size:14px; padding:6px 7px 5px 28px; display:inline-block; }

            .toolbar .btn,
            .toolbar .btn.disabled:hover {
                border-color:#d0d0d0;
                background-image: -webkit-linear-gradient(top, #fdfdfd, #ececec);
                background-image: -moz-linear-gradient(top, #fdfdfd, #ececec);
                -webkit-box-shadow: 0 0 0 1px #FFF; -moz-box-shadow: 0 0 0 1px #FFF; box-shadow: 0 0 0 1px #FFF; }

            .toolbar .btn:hover {
                border-color:#b6b6b6;
                background-image: -webkit-linear-gradient(top, #f8f8f8, #e1e1e1);
                background-image: -moz-linear-gradient(top, #f8f8f8, #e1e1e1); }

            .toolbar .btn.selected,
            .toolbar .btn.active {
                border-color:#b6b6b6;
                background-color:#eee;
                background-image: -webkit-linear-gradient(top, #e1e1e1, #f8f8f8);
                background-image: -moz-linear-gradient(top, #e1e1e1, #f8f8f8); }

            .toolbar .btn.disabled { cursor:default; opacity:.3; filter:alpha(opacity=30); -ms-filter:"alpha(opacity=30)"; }

            /*button images*/
            .toolbar .zoom-out .icon { background:url("sprites.png") no-repeat -6px -6px; }
            .toolbar .zoom-in .icon { background:url("sprites.png") no-repeat -6px -31px; }
            .toolbar .prev .icon { background:url("sprites.png") no-repeat -5px -52px; }
            .toolbar .next .icon { background:url("sprites.png") no-repeat -5px -74px; }
            .toolbar .annotate .text { background:url("sprites.png") no-repeat -4px -188px; }
            .toolbar .download .icon { background:url("download.png") no-repeat 7px 4px; }
        </style>
        <div class="toolbar">
            zoom buttons
            <div class="zoom-btns">
                <span class="btn zoom-out"><span class="icon"></span></span>
                <span class="btn zoom-in"><span class="icon"></span></span>
            </div>

            toolbar buttons
            <div class="toolbar-btns">
            </div>

            logo
            <div class="logo">
                <div class="text">Powered by Viettel</div>
            </div>
        </div>


        <!--DocViewer-->
        <div class="docviewer">
            <div style="font-size: 6.64171px;" class="doc subpx">
                <div id="pageourter" class="page-outer" style="width:120em;">
                    <div class="page">  
                        <div class="layer text">

                            <div id="paddingDiv" style="padding: 8em 8em 8em 12em">
                                <div id="replace">  
                                    ${fn:escapeXml(docContent)}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" charset="utf-8">
            var fileType = '${fn:escapeXml(fileType)}';
            if (fileType=="pdf"){
                //deal with pdf  
                document.getElementById("paddingDiv").style.padding="";
                var width = '${width}';   
                document.getElementById("pageourter").style.width = width+"px";
            } else if (fileType=="docx"){
                //deal with docx   
            }else if (fileType=="xlsx"||fileType=="xls"){
                document.getElementById("pageourter").style.width = ""
                document.getElementById("paddingDiv").style.padding="";
                document.getElementById("pageourter").style.maxWidth = "150em";
                console.log( document.getElementById("pageourter").style);
                //deal with excel
            } else if (fileType =="image"){
                document.getElementById("paddingDiv").style.padding="";
                var width = '${width}';   
                document.getElementById("pageourter").style.width = width+"px";
            } 
            else if (fileType =="ppt"||fileType =="pptx"){
                document.getElementById("paddingDiv").style.padding="";
                var width = '${width}';
                document.getElementById("pageourter").style.width = width+"px";
            }else{
                alert("Not support to open this type of file");
                window.close();
            }
        </script>
    </body>
</html>

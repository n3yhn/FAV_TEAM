<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : keyboard
    Created on : Oct 21, 2013, 3:13:12 PM
    Author     : havm2
--%>
<script type="text/javascript">
    onSelectKey = function(key) {
        var item = document.getElementById(localStorage.focusedItem);
        item.value = item.value + key;
    }

    makeKeyboard = function(strings) {
        var table = document.getElementById("tblOfCharacter");
        var row = document.createElement("tr");
        table.appendChild(row);
        var i = 0;
        for (i = 0; i < strings.length; i++) {
            if(i>0 && i % 10 == 0){
                row = document.createElement("tr");
                table.appendChild(row);
            }
            var td = document.createElement("td");
            td.innerHTML = strings[i];
            td.setAttribute("onclick", "onSelectKey(\"" + strings[i] + "\");");
            row.appendChild(td);
        }

    }
</script>
<style type="text/css">
    #tblOfCharacter td{
        margin: 2px;
        width : 20px;
        height: 20px;
        border-radius: 3px;
        border: 1px solid #00688b;
        text-align: center;
        vertical-align: middle;
    }
    #tblOfCharacter td:hover{
        background: #00688b
    }
</style>

<div id="keyboardInput" style="background: wheat;border-radius: 4;position: absolute; top: 70px; left: 800px; border:1px solid #00688b; display:none">
    <table>
        <tr>
            <th style="text-align: center">
                Bảng các ký tự đặc biệt
            </th>
        </tr>
        <tr>
            <td>
                <table id="tblOfCharacter">
                </table>
            </td>
        </tr>
    </table>
</div>
<script>
    makeKeyboard("©®™€£¥±≠≤≥÷×∞µαβπΩ∑");
</script>
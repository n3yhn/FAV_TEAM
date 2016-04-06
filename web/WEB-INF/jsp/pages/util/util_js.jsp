<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script>
    page.convertDateTime =function(date) { 
        var createDateStr = "";
        if (date != null ){
            var createDate=date.toString();
            if (createDate != ""){                   
                var hour = createDate.substring(11,19);                    
                var dd = createDate.toString().substring(8,10);                    
                var mm = createDate.substring(5,7);
                var yyyy = createDate.substring(0,4);
                createDateStr = dd + "/"+mm+"/"+yyyy+" "+hour;                     
            }
        }
        return createDateStr;
    }
    page.checkDate = function(controlID)
    {
        var tdate= dijit.byId(controlID).value;        
        var check=new RegExp("/","gi");
        var check2 = new RegExp("^[0-9]+$");
        if(check.exec(tdate)!=null)
        {
            if(tdate!="")
            {
                if(tdate.length==10)
                {
                    var dd=tdate.slice(0,2);                    
                    if(check2.exec(dd) != null)
                        dd=new Number(dd);
                    else{
                        //                        msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                        return false;
                    }
                    var mm=tdate.slice(3,5);
                    if(check2.exec(mm) != null)
                        dd=new Number(mm);
                    else{
                        //                        msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                        return false;
                    }
                    var yyyy=tdate.slice(6,10);
                    if(check2.exec(yyyy) != null)
                        dd=new Number(yyyy);
                    else{
                        //                        msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                        return false;
                    }
                    var today=new Date();
                    var thisy=today.getYear();
                    var thism=today.getMonth();
                    var thisd=today.getDate();
                    if(dd!=null&&mm!=null&&yyyy!=null)
                    {
                        if (dd < 0 || mm < 0 || mm > 12 || yyyy < 1900 || yyyy  > thisy + 1900)
                        {
                            //                            msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                            return false;
                        }
                    }
                    else{
                        //                        msg.alert("Ngày/tháng/năm không hợp lệ..."+dd+" "+mm+" "+ yyyy + dd.constructor);
                        return false;
                    }
                }else if(tdate.length == 8){
                    var dd=tdate.slice(0,1);
                    if(check2.exec(dd) != null)
                        dd=new Number(dd);
                    else{
                        //                        msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                        return false;
                    }
                    var mm=tdate.slice(2,3);
                    if(check2.exec(mm) != null)
                        dd=new Number(mm);
                    else{
                        //                        msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                        return false;
                    }
                    var yyyy=tdate.slice(4,8);
                    if(check2.exec(yyyy) != null)
                        dd=new Number(yyyy);
                    else{
                        //                        msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                        return false;
                    }
                    var today=new Date();
                    var thisy=today.getYear();
                    var thism=today.getMonth();
                    var thisd=today.getDate();
                    if(dd!=null&&mm!=null&&yyyy!=null)
                    {
                        if (dd < 0 || mm < 0 || mm > 12 || yyyy < 1900 || yyyy  > thisy + 1900)
                        {
                            //                            msg.alert("Ngày/tháng/năm không hợp lệ");
                            return false;
                        }
                    }
                    else{
                        //                        msg.alert("Ngày/tháng/năm không hợp lệ..."+dd+" "+mm+" "+yyyy+dd.constructor);
                        return false;
                    }
                }else{
                    //                    msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                    return false;
                }
            }
            else{
                //                msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                return false;
            }
        }
        else if(check2.exec(tdate) != null)
        {            
            var y = new Number(tdate);            
            var today=new Date();
            if (y < 1900 || y > today.getYear() + 1900)
            {
                //                msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
                return false;
            }
        }
        else{               
            //            msg.alert("Ngày/tháng/năm không hợp lệ. Bạn phải nhập theo định dạng dd/mm/yyy hoặc d/m/yyyy hoặc yyyy");
            return false;
        }
        return true;
    }
    page.check = function(){
        page.checkDate("declaration.dadDob")
    }
    page.alert = function(txtTitle,txtContent,type){
        alert(txtContent);
    }

    page.getLengthText = function(value) {
        return dojo.trim(value.toString()).length;
    }
    page.setDefautCombo = function(obj,value){        
        if(!obj.isValid()){
            obj.attr("value",value);
        }
    }
    page.isValidCombo=function(id, name) {
        if(!id.isValid()){
            id.focus();
            msg.alert(name+" nhập không hợp lệ!", "<sd:Property>confirm.title</sd:Property>");
            return false;
        }
        return true;
    }
    page.isValidComboRequire=function(id, name) {
        if(!id.isValid()){
            id.focus();
            msg.alert(name+" nhập không hợp lệ!", "<sd:Property>confirm.title</sd:Property>");
            return false;
        }
        if(id.attr("value") == "" || id.attr("value") == "-1")
        {
            id.focus();
            msg.alert("Bạn hãy chọn " + name, "<sd:Property>confirm.title</sd:Property>");
            return false;
        }
        return true;
    }

    page.isDate=function(id,isNull,name) {
        if(!id.isValid()){
            id.focus();
            return false;
        }
        if (isNull==true){
            if(id.getValue()==null || dojo.trim(id.getValue().toString())==""){
                id.focus();
                msg.alert("Bạn phải nhập " +name, "<sd:Property>confirm.title</sd:Property>");
                return false;
            }
        }
        return true;
    }
    
    page.compareDate=function(date1,date2) { //dd/mm/yyyy
        if(date1.getValue()==null || dojo.trim(date1.getValue().toString())==""){
            return true;
        }
        if(date2.getValue()==null || dojo.trim(date2.getValue().toString())==""){
            return true;
        }
        
        var dValue1 = date1.getValue();
        var dValue2 = date2.getValue();
        if(dValue2 < dValue1) return false;
        
        return true;
    }
    
    page.compareDateXday =function(date1,date2, x) { //dd/mm/yyyy
        if(date1.getValue()==null || dojo.trim(date1.getValue().toString())==""){
            return true;
        }
        if(date2.getValue()==null || dojo.trim(date2.getValue().toString())==""){
            return true;
        }
        
        var dValue1 = date1.getValue();
        //        var dateCompare = new Date(dValue1.getFullYear(), dValue1.getMonth() + 1, dValue1.getDate()).getMonth() + 1)
        var dValue2 = date2.getValue();
        
        var offset = dValue2.getTime() - dValue1.getTime(); // lấy độ lệch của 2 mốc thời gian, đơn vị tính là millisecond
        var totalDays = Math.round(offset/1000/60/60/24);
        if(totalDays > x) return false;
        
        return true;
    }
        
    page.compareToday=function(date, name) { //dd/mm/yyyy
        if(date.getValue()==null || dojo.trim(date.getValue().toString())==""){
            return true;
        }   
                
        var today = new Date();
        var dValue = date.getValue();
        if(today < dValue) {
            date.focus();
            msg.alert(name + " phải nhỏ hơn hoặc bằng ngày hiện tại", "<sd:Property>confirm.title</sd:Property>"); 
            return false;
        }
        return true;
    }

    page.convertDateToString = function(dgDate){
        try{
            var temp = dgDate.split("-");
            return temp[2].split("T")[0]+"/"+temp[1]+"/"+temp[0];
        }catch(e){
            return "";
        }
    }
    page.convertGridDateToDate = function(dgDate){
        try{
            
            if(dgDate != null)
            {
                var temp = dgDate.toString().split("-");
                var year = temp[0];
                var month = temp[1] - 1;
                var day = temp[2].split("T")[0];            
                            
                return new Date(year, month, day);
            }
            
            return null;
        }catch(e){
            return null;
        }
    }
    
    page.checkBlankDate = function(value){
        return value == null || dojo.trim(value.toString()) == "";
    }
    
    page.checkBlankTextBox = function(id, name){
        if(id.getValue() == null || dojo.trim(id.getValue()) == "")
        {
            id.focus();
            msg.alert("Bạn hãy nhập " + name);            
            return false;
        }
        return true;
    }
    
    page.disableCheckboxApprove = function(parentStatus,childStatus,minisIdRecord,minisIdLogin){
        if (minisIdRecord==minisIdLogin){
            if (childStatus=='0'){
                if ((parentStatus=='2')||(parentStatus=='3')||(parentStatus=='5')||((parentStatus=='6'))){
                    return false;
                }else return true;
            }else return true;
        }else {
            if (childStatus=='6'){
                if (parentStatus=='5'||(parentStatus=='6')){
                    return false
                }else return true;
            }else  return true;
        }
    }

    page.validateFreeTextDate = function(date, name){        
        if (date == null) return null;
        var trimDate = dojo.trim(date);
        if (trimDate == "") return null;
        var partern1 = /^\d{2}\/\d{2}\/\d{4}$/;
        var partern2 = /^\d{2}\/\d{4}$/;
        var partern3 = /^\d{4}$/;
        
        var iDay = null;
        var iMonth = null;
        var iYear = null;
        if(partern3.test(trimDate)) {            
            iYear = trimDate;
        } else if(partern2.test(trimDate)) {            
            var split = trimDate.split("/");
            iMonth = split[0];
            iYear = split[1];
        } else if(partern1.test(trimDate)) {            
            var split = trimDate.split("/");
            iDay = split[0];
            iMonth = split[1];
            iYear = split[2];
        } else {
            return name + " phải thuộc một trong các định dạng(dd/mm/yyyy, mm/yyyy, yyyy)";
        }
        
        if(iDay != null) {
            iDay = parseInt(iDay, 10);
            iMonth = parseInt(iMonth, 10);
            iYear = parseInt(iYear, 10);
            var testDate = new Date(iYear, iMonth-1, iDay);
            if (testDate.getDate() == iDay) {
                if (testDate.getMonth() + 1 == iMonth) {
                    if (testDate.getFullYear() == iYear) {
                        
                    } else {
                        return name + " không tồn tại";
                    }
                } else {
                    return name + " không tồn tại";
                }
            } else {
                return name + " không tồn tại";
            }            
        }
        
        if(iMonth != null) {
            if(iMonth>12) return name + " không tồn tại";
        }
        
        return null;
    }
    page.compareFreeTextDate = function(IdDate1, IdDate2, dateName1, dateName2)
    {
        if(IdDate1 == null || IdDate2 == null)
        {
            return true;
        }
        
        var strDate1 = dojo.trim(IdDate1.getValue());
        var strDate2 = dojo.trim(IdDate2.getValue());
        var objDate1;
        var objDate2;
         
        var partern1 = /^\d{2}\/\d{2}\/\d{4}$/;
        var partern2 = /^\d{2}\/\d{4}$/;
        var partern3 = /^\d{4}$/;
        
        var iDay1 = "1";
        var iMonth1 = "1";
        var iYear1 = "1";
        if(partern3.test(strDate1)) {            
            iYear1 = strDate1;
        } else if(partern2.test(strDate1)) {            
            var split = strDate1.split("/");
            iMonth1 = split[0];
            iYear1 = split[1];
        } else if(partern1.test(strDate1)) {            
            var split = strDate1.split("/");
            iDay1 = split[0];
            iMonth1 = split[1];
            iYear1 = split[2];
        } else {
            return true;
        }
        
        var iDay2 = null;
        var iMonth2 = null;
        var iYear2 = null;
        if(partern3.test(strDate2)) {            
            iYear2 = strDate2;
        } else if(partern2.test(strDate2)) {            
            var split = strDate2.split("/");
            iMonth2 = split[0];
            iYear2 = split[1];
        } else if(partern1.test(strDate2)) {            
            var split = strDate2.split("/");
            iDay2 = split[0];
            iMonth2 = split[1];
            iYear2 = split[2];
        } else {
            return true;
        }
        
        objDate1 = new Date(iYear1,iMonth1,iDay1);
        objDate2 = new Date(iYear2,iMonth2,iDay2);
        
        // kiem tra
        if(objDate1 > objDate2)
        {
            IdDate1.focus();
            msg.alert(dateName1 + " phải nhỏ hơn hoặc bằng " + dateName2);            
            return false;
        }
        
        return true;
    }
    page.changeLable = function(textBoxID, labelID, str){    
        var value = document.getElementById(textBoxID).value;
        var check2 = new RegExp("^[0-9]+$");
        if(check2.exec(value) == null){
            dijit.byId(textBoxID).focus();
            return false;
        }
        if(value.length == 6){
            var yy = value.slice(0,2);
            var mm = value.slice(2,4);
            var dd = value.slice(4,6);
            var y = new Number(yy);
            var m = new Number(mm);
            var d = new Number(dd);
            var text = " ";
            if(y != 0){
                text += y + " năm ";
            }
            if(m != 0){
                text += m + " tháng ";
            }
            if(d != 0){
                text += d + " ngày";
            }
            document.getElementById(labelID).innerHTML = str + text;
        }else{
            document.getElementById(labelID).innerHTML = "(YYMMDD)";
            return false;
        }
        return true;
    }
    
    page.changePenaltyMainId = function(value){
        if(value.length == 6){
            var yy = value.slice(0,2);
            var mm = value.slice(2,4);
            var dd = value.slice(4,6);
            var y = new Number(yy);
            var m = new Number(mm);
            var d = new Number(dd);
            var text = " ";
            if(y != 0){
                text += y + " năm ";
            }
            if(m != 0){
                text += m + " tháng ";
            }
            if(d != 0){
                text += d + " ngày";
            }
            return text;
        }else{
            return "";
        }
    }
    
    page.validateTextbox = function(textBoxID, labelID, str){
        var value = dijit.byId(textBoxID).value;
        var check2 = new RegExp("^[0-9]+$");
        if(check2.exec(value) == null){
            dijit.byId(textBoxID).focus();
            return false;
        }
        if(value.toString().length == 6){
            var yy = value.slice(0,2);
            var mm = value.slice(2,4);
            var dd = value.slice(4,6);
            var y = new Number(yy);
            var m = new Number(mm);
            var d = new Number(dd);
            var text = " ";
            if(y != 0){
                text += y + " năm ";
            }
            if(m != 0){
                text += m + " tháng ";
            }
            if(d != 0){
                text += d + " ngày";
            }
            document.getElementById(labelID).innerHTML = str + text;
        }
        else{
            dijit.byId(textBoxID).focus();
            return false;
        }
        return true;
    }
    
    page.refineFullName = function(myfield) {
        var lowerChar = new Array(
        "à", "á", "ạ", "ả", "ã", "â", "ầ", "ấ", "ẩ", "ậ", "ẫ", "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ", 
        "đ", "è", "é", "ẹ", "ẻ", "ẽ", "ê", "ề", "ế", "ệ", "ể", "ễ", "ì", "í", "ị", "ỉ", "ĩ",
        "ò", "ó", "ọ", "ỏ", "õ", "ô", "ồ", "ố", "ộ", "ổ", "ỗ", "ù", "ú", "ụ", "ủ", "ũ", "ư", "ừ", "ứ", "ự", "ử", "ữ",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    );
        var upperChar = new Array(
        "À", "Á", "Ạ", "Ả", "Ã", "Â", "Ầ", "Ấ", "Ẩ", "Ậ", "Ẫ", "Ă", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ", 
        "Đ", "È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ề", "Ế", "Ệ", "Ể", "Ễ", "Ì", "Í", "Ị", "Ỉ", "Ĩ",
        "Ò", "Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ù", "Ú", "Ụ", "Ủ", "Ũ", "Ư", "Ừ", "Ứ", "Ự", "Ử", "Ữ",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );
        var fullName = myfield.value;
        var newName = "";
        var last = "";
        var current;
        for(var i=0; i<fullName.length; i++) {
            current = fullName.charAt(i).toString();
            //            alert(current);
            if(last == "") {            
                var check = true;
                for (var j = 0; j < lowerChar.length; j++) {
                    if (lowerChar[j] == current) {
                        newName += upperChar[j].toString();
                        check = false;
                        break;
                    }
                }
                if(check)  newName += current;
            }
            else if(last == " ") {                
                if(current != " ") {
                    var check = true;
                    for (var j = 0; j < lowerChar.length; j++) {
                        if (lowerChar[j] == current) {
                            newName += upperChar[j].toString();
                            check = false;
                            break;
                        }
                    }
                    if(check)  newName += current;
                }                
            } else {
                var check = true;
                for (var j = 0; j < lowerChar.length; j++) {
                    if (upperChar[j] == current) {
                        newName += lowerChar[j].toString();
                        check = false;
                        break;
                    }
                }
                if(check)  newName += current;
            }
            last = current;
        }
        
        dijit.byId(myfield.id).attr("value", newName);
    }
    
    page.refineFullNameDeclaration = function(myfield) {
        var lowerChar = new Array(
        "à", "á", "ạ", "ả", "ã", "â", "ầ", "ấ", "ẩ", "ậ", "ẫ", "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ", 
        "đ", "è", "é", "ẹ", "ẻ", "ẽ", "ê", "ề", "ế", "ệ", "ể", "ễ", "ì", "í", "ị", "ỉ", "ĩ",
        "ò", "ó", "ọ", "ỏ", "õ", "ô", "ồ", "ố", "ộ", "ổ", "ỗ", "ù", "ú", "ụ", "ủ", "ũ", "ư", "ừ", "ứ", "ự", "ử", "ữ",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    );
        var upperChar = new Array(
        "À", "Á", "Ạ", "Ả", "Ã", "Â", "Ầ", "Ấ", "Ẩ", "Ậ", "Ẫ", "Ă", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ", 
        "Đ", "È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ề", "Ế", "Ệ", "Ể", "Ễ", "Ì", "Í", "Ị", "Ỉ", "Ĩ",
        "Ò", "Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ù", "Ú", "Ụ", "Ủ", "Ũ", "Ư", "Ừ", "Ứ", "Ự", "Ử", "Ữ",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );
        var fullName = myfield.value;
        var newName = "";        
        var current;        
        for(var i=0; i<fullName.length; i++) {
            current = fullName.charAt(i).toString();
            if(current != " "){
                for (var j = 0; j < lowerChar.length; j++) {
                    //neu la chu thuong thi doi thanh chu hoa
                    if (lowerChar[j] == current) {
                        newName += upperChar[j].toString();
                        //                        newName += fullName.substring(i + 1, fullName.length);                        
                        break;
                    }else if(upperChar[j] == current){//neu la chu hoa thi tra ve luon
                        newName += current;
                        //                        newName += fullName.substring(i + 1, fullName.length);                        
                        break;
                    }
                }
            }else{
                newName += current;
            }            
        }
        //cat nhung khoang trang thua
        var last="";
        var strReturn="";
        for(var i=0; i<newName.length; i++){
            current = newName.charAt(i).toString();
            if(last == " "){
                if(current != " ")
                    strReturn += current;   
            }else{
                strReturn += current;
            }
            last = current;
        }
        dijit.byId(myfield.id).attr("value", strReturn);
    }
    
    page.refineString = function(myfield) {
        var lowerChar = new Array(
        "à", "á", "ạ", "ả", "ã", "â", "ầ", "ấ", "ẩ", "ậ", "ẫ", "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ", 
        "đ", "è", "é", "ẹ", "ẻ", "ẽ", "ê", "ề", "ế", "ệ", "ể", "ễ", "ì", "í", "ị", "ỉ", "ĩ",
        "ò", "ó", "ọ", "ỏ", "õ", "ô", "ồ", "ố", "ộ", "ổ", "ỗ", "ù", "ú", "ụ", "ủ", "ũ", "ư", "ừ", "ứ", "ự", "ử", "ữ",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    );
        var upperChar = new Array(
        "À", "Á", "Ạ", "Ả", "Ã", "Â", "Ầ", "Ấ", "Ẩ", "Ậ", "Ẫ", "Ă", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ", 
        "Đ", "È", "É", "Ẹ", "Ẻ", "Ẽ", "Ê", "Ề", "Ế", "Ệ", "Ể", "Ễ", "Ì", "Í", "Ị", "Ỉ", "Ĩ",
        "Ò", "Ó", "Ọ", "Ỏ", "Õ", "Ô", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ", "Ù", "Ú", "Ụ", "Ủ", "Ũ", "Ư", "Ừ", "Ứ", "Ự", "Ử", "Ữ",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );
        var fullName = myfield.value;
        var newName = "";        
        var current;
        var check = false;
        for(var i=0; i<fullName.length; i++) {
            current = fullName.charAt(i).toString();
            if(current != " "){
                for (var j = 0; j < lowerChar.length; j++) {
                    //neu la chu thuong thi doi thanh chu hoa
                    if (lowerChar[j] == current) {
                        newName += upperChar[j].toString();
                        newName += fullName.substring(i + 1, fullName.length);
                        check = true;
                        break;
                    }else if(upperChar[j] == current){//neu la chu hoa thi tra ve luon
                        newName += current;
                        newName += fullName.substring(i + 1, fullName.length);
                        check = true;
                        break;
                    }
                }
            }
            if(check)
                break;
        }
        //cat nhung khoang trang thua
        var last="";
        var strReturn="";
        for(var i=0; i<newName.length; i++){
            current = newName.charAt(i).toString();
            if(last == " "){
                if(current != " ")
                    strReturn += current;   
            }else{
                strReturn += current;
            }
            last = current;
        }
        dijit.byId(myfield.id).attr("value", strReturn);
    }
    
    page.validateFreeTextDate2 = function(iDay, iMonth, iYear){
        if(iDay != null && iDay != ""){
            if(iDay > 31 || iDay <= 0){
                return "iDayError";
            }
        }
        
        if((iDay != null && iDay != "") && (iMonth == null || iMonth == "")){
            return "iMonthError";
        }
        
        if(iMonth != null && iMonth != ""){
            if(iMonth > 12 || iMonth <= 0){
                return "iMonthError";
            }
        }
        
        if(iYear != null && iYear != ""){
            if(iYear.toString().length < 4){
                return "iYearError";
            }
        }
        
        if(iDay != null && iDay != "") {
            iDay = parseInt(iDay, 10);
            iMonth = parseInt(iMonth, 10);
            iYear = parseInt(iYear, 10);
            var testDate = new Date(iYear, iMonth-1, iDay);
            if (testDate.getDate() == iDay) {
                if (testDate.getMonth() + 1 == iMonth) {
                    if (testDate.getFullYear() == iYear) {
                        
                    } else {
                        return "iDateError";
                    }
                } else {
                    return "iDateError";
                }
            } else {
                return "iDateError";
            }            
        }
        return null;
    }
    page.showDialogS = function() {
        
        var applet = document.signApplet;
        if (applet.isActive) {
            try{
                applet.showSignal();

            }catch(err){
                alert(err.toString());
            }
        }
        return false;
    }

    page.setDisableCheckbox = function (exchangeStatus, mj_record, mj_session){        
        
        if (mj_record == mj_session){
            if(exchangeStatus == null || exchangeStatus == "0"){
                return false;
            }
        }
        return true;
    }
    page.formatApproveStatus = function(inDatum){
        var statusReturn = "";
        if(inDatum!=null){            
            if(inDatum=="0" || inDatum=="6"){
                statusReturn = "Chờ phê duyệt ";
            }
            else if(inDatum=="2" || inDatum=="5" ){
                statusReturn = "Đã phê duyệt ";
            }
        }
        return statusReturn; 
    }
    page.statusSendConfirm = function(inDatum){    
        var status = "";
        if(inDatum!=null){
            if (inDatum == '2'){
                status = "Chờ gửi";
            }else if (inDatum == '3'){
                status = "Đã gửi/ chờ xác nhận ";
            }else if (inDatum == '6'){
                status = "Đơn vị khác đã xác nhận ";
            }else if (inDatum == '4'){
                status = "Bị từ chối ";
            }        
        }
        return status;
    }
    page.openFile = function(id){
        var url = "uploadiframe!openFile.do?attachId=" + id;
        var target = "divOpenFile";        
        sd.connector.post(url, target,null,null,null);
    }
</script>
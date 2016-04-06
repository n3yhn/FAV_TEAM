/**
 * Author: LongH
 * Description: Cung cấp các API util
 * Date: 10/07/2011
 * FWVersion: 3.3
 **/

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

trimZero = function(str) {
    if (str != null) {
        var i = 0;
        for (; i < str.length; i++) {
            if (str.charAt(i) != '0') break;
        }
        if (i > 0) str = str.substring(i);
    }
    return str;
}
onTrimZero=function(obj){
    if(obj.value == null ||sd.validator.trim(obj.value).length == 0){
        obj.value="0";
        //    obj.focus();
        return false;
    }
    var number=     parseFloat(obj.value);
    dijit.byId(obj.id).setValue(number);
    obj.value=number;
    return true;
}

page.formatNull = function(data) {
    if (data == null || data == undefined) {
        return "";
    } else {
        return data;
    }
}
    
page.getRow = function(inRowIndex){
    return inRowIndex;
}
    
page.getIndex = function(inRowIndex){
    return inRowIndex + 1;
}
    
//ham nay convert date lay tu datePicker thanh dang "dd/MM/yyyy" de co the compare
page.convertDate = function(date){
    if (date != null) {
        var year = date. getFullYear();
        var month = date. getMonth() + 1;
        var date = date.getDate();
        if(month < 10){
            strM = "0"+month;
        }else{
            strM=""+month;
        }
        if(date < 10){
            strD = "0"+date;
        }else{
            strD=""+date;

        }
        var strDate = strD+"/"+strM+"/"+year;
        return strDate;
    }
}
page.convertStringToDate = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dgDate){
    try{
        var dateStr = page.getString( dgDate );
        var temp = dateStr.split("-");
        return new Date(temp[1]+"/"+temp[2].split("T")[0]+"/"+temp[0]);
    }catch(e){
        //page.alert("Thông báo","function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss", "warning");
        return undefined;
    }
}
page.getString = function( obj ) {
    var ret = "";
    if( obj.constructor == String ) {
        ret = obj;
    }
    else if( obj.constructor == Array ) {
        ret = obj.join( "" );
    }

    return sd.validator.trim( ret );
}
page.convertDateToString = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dateStr){
    if (dateStr != null && dateStr != "") {
        try{
            var dgDate  = page.getString( dateStr );
            var temp = dgDate.split("-");
            return temp[2].split("T")[0]+"/"+temp[1]+"/"+temp[0];
        }catch(e){
            //alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
            return undefined;
        }
    } else {
        return "";
    }
}
page.convertDateToStringForDetail = function(/*String date from DataGrid: yyyy-MM-ddThh:mm:ss*/dateStr){
    //return dd/mm/yyyy
    try{

        if(dateStr!=null){
            if(dateStr.toString().length>0)
            {
                console.log("dateStr.toString() "+dateStr.toString());
                var dgDate  =dateStr.toString().substring(0,10);
                console.log("dgDate "+dgDate);
                var arrDate=dgDate.split("-");
                console.log("arrDate "+arrDate);
                return arrDate[2]+"/"+arrDate[1]+"/"+arrDate[0];
            }
            else return "";
        }
        else return "";
    }catch(e){
        //alert("function convertStringToDate need parameter format: yyyy-MM-ddThh:mm:ss");
        return undefined;
    }
}
page.isValidDate = function(strValue){
    return sd.validator.isDate(strValue);
}


page.formatGender = function(inDatum){
    return (inDatum == '0')? "Nam": "Nữ";
}
page.clearGridData=function(gridIdStr){
    try{
        var grid = dijit.byId(gridIdStr);
        var rowCount = grid._getRowCountAttr();
        if(rowCount>0){
            for(var i = rowCount - 1; i >= 0;i-- ){
                var item=grid.getItem(i);
                grid.store.deleteItem(item);
            }
            grid.store.save();
        }
    }catch(e){
        console.log(e.message);
    }
}

page.deleteGridData = function(gridIdStr){
    var grid = dijit.byId(gridIdStr);
    if( grid._getRowCountAttr()>0)
        grid.edit.apply();
    if (!grid.vtIsChecked()){
        alert("Bạn chưa chọn bản ghi để xóa");
        return false;
    }
    else{
        var items = grid.vtGetCheckedItems();
        var length = items.length;
        try{
            for(var i = length - 1 ;i >= 0;i--){
                var item = items[i];
                grid.store.deleteItem(item);
            }
            grid.store.save();
            grid.vtUnCheckAll();
        }catch(e){
            console.log(e.message);
        }
    }
}

page.validStringLength=function(element,size,isNull, name){
    if (isNull==true){
        if(dojo.trim(element.value)==""){
            msg.alert("Bạn phải nhập " +name);
            element.focus();
            return false;
        }
    }
    if(dojo.trim(element.value).length > size){
        msg.alert(name + " không được vượt quá "+size +" ký tự");
        element.focus();
        return false;
    }
    return true;
}

page.validateComboValue=function(element, name){
    if(!dijit.byId(element).getValue()||dijit.byId(element).getValue()==-1){
        page.alert("Thông báo","Bạn chưa chọn " + name , "warning");
        dijit.byId(element).focus();
        return false;
    }
    return true;
}
page.validateTextBoxValue=function(element, name){
    if(!dijit.byId(element).getValue()){
        page.alert("Thông báo","Bạn chưa nhập " + name , "warning");
        dijit.byId(element).focus();
        return false;
    }
    return true;
}
page.validateEditableString=function(element,size,isNull, name,rowNo,gridName,index,gridNameVN){
    if (isNull==true){
        if(element==null|| element.toString().length == 0){
            alert(gridNameVN+ " - " + "Dòng "+rowNo+": Bạn phải nhập " +name);
            dijit.byId(gridName).focus.setFocusIndex(rowNo-1,index);
            return false;
        }
    }
    if(dojo.trim(element.toString()).length > size){
        alert("Dòng "+rowNo+":" + name + " không được vượt quá "+size +" ký tự");
        dijit.byId(gridName).focus.setFocusIndex(rowNo-1,index);
        return false;
    }
    return true;
}
page.validateSelectableItem=function(element, name,rowNo,gridName,index,gridNameVN){
    //    alert(element);
    if(element == null || element.toString().length == 0 || element.toString() == '-1'){
        alert(gridNameVN+ " - " + "Dòng "+rowNo+": Bạn phải chọn "+ name);
        dijit.byId(gridName).focus.setFocusIndex(rowNo-1,index);
        return false;
    }
    return true;
}

page.validateDateFormat=function(value){
    //    var date=value.substr(0,10);
    var date=value;
    // Regular expression used to check if date is in correct format
    var pattern = new RegExp(/^\d{1,2}(\-|\/|\.)\d{1,2}\1\d{4}$/);

    // kiem tra date
    if(date.match(pattern)) return true;
    else return false;
}
page.validDate=function(element,isNull, name){
    if (isNull==true && dojo.trim(element.value)==""){
        alert("Bạn phải nhập " +name);
        element.focus();
        return false;
    }
    else if(!dojo.trim(element.value)==""){
        if(! page.validateDateFormat(dojo.trim(element.value)))
        {
            alert(name+" không đúng định dạng dd/MM/yyyy!");
            element.focus();
            return false;
        }
        if(!sd.validator.isDate(dojo.trim(element.value)))
        {
            alert(name+" đã nhập không tồn tại giá trị ngày tháng !");
            element.focus();
            return false;
        }
    }
    return true;
}
page.compareToCurrentDate=function(element, name, isEqual){
    var dateStr = element.value;
    var str = dojo.date.locale.format(new Date(), {
        datePattern : 'dd/MM/yyyy',
        selector : 'date'
    });
    var result = false;
    result = sd.validator.compareDates(dateStr,str);
    if (isEqual) {
        result = result || (dateStr == str);
        if(!result){
            page.alert("Thông báo",name+ " phải nhỏ hơn hoặc bằng ngày hiện tại", "warning");
            element.focus();
            return false;
        }
    } else {
        if(!result){
            page.alert("Thông báo",name+ " phải nhỏ hơn ngày hiện tại", "warning");
            element.focus();
            return false;
        }
    }
    return true;
}
formEnter = function(formId, functionStr, isClear) {
    if (isClear) {
        $('#' + formId).unbind("keypress");
    }
    var func = eval(functionStr);
    var dk = dojo.keys;
    $('#' + formId).keypress(function(e) {
        var keynum
        if(window.event) { //IE
            keynum = e.keyCode
        }
        else if(e.which) {// Netscape/Firefox/Opera
            keynum = e.which
        }
        switch(keynum){
            case dk.ENTER:
                func();
                break;
        }
    });
}

setFirstFocus = function(formId) {
    var aForm = document.getElementById(formId);
    if( aForm.elements[0]!=null) {
        var i;
        var max = aForm.length;
        for( i = 0; i < max; i++ ) {
            if( aForm.elements[ i ].type != "hidden" &&
                !aForm.elements[ i ].disabled &&
                !aForm.elements[ i ].readOnly ) {
                aForm.elements[ i ].focus();
                break;
            }
        }
    }
}

clearForm = function(formId) {
    var aForm = document.getElementById(formId);
    var inputs = aForm.getElementsByTagName("input");
    if(inputs != null && inputs.length > 0) {
        for( var i = 0; i < inputs.length; i++ ) {
            inputs[ i ].value = "";
        }
    }
}

//DiuLTT sửa forcus => onFocus
onFocus = function(elementId) {
    var element = document.getElementById(elementId);
    if (element != null) {
        element.focus();
    }
}

checkFile_ = function(uploadId, fileObj, extentions, fileSize) {
    if (extentions != null && extentions != "") {
        var stringArray = fileObj.name.split('.');
        var ext = stringArray[stringArray.length - 1];
        var exts = extentions.split(":");
        var isValid = false;
        var validExtString = "";
        var upload = "#" + uploadId;
        for (var i = 0; i <exts.length; i++) {
            var validExt = exts[i];
            if (validExt != "") {
                validExtString = validExtString + validExt + ", ";
                if (validExt == ext) {
                    isValid = true;
                    break;
                }
            }
        }
        if (validExtString.length > 2) {
            validExtString = validExtString.substring(0, validExtString.length - 2);
        }
        if (!isValid) {
            msg.alert("Định dạng file không cho phép. Hãy chọn file " + validExtString, "<sd:Property>confirm.title</sd:Property>");
            jQuery(upload).uploadifyClearQueue();
            jQuery(upload).uploadifyCancel();
        }
        return isValid;
    }
    if (fileSize != null && fileSize < fileObj.size) {
        var sizeMB = fileSize / (1024*1024);
        msg.alert("Dung lượng file không vượt quá" + sizeMB + "MB", "<sd:Property>confirm.title</sd:Property>");
        jQuery(upload).uploadifyClearQueue();
        jQuery(upload).uploadifyCancel();
        return false;
    }
}
    
shortenFilename = function(filename, maxLength) {
    if (!(filename instanceof String)) {
        filename = filename.toString();
    }
    if (filename != null && dojo.trim(filename) != "") {
        var pos = filename.lastIndexOf(".");
        var ext = "";
        if (pos >= 0 && pos < filename.length) {
            ext = filename.substring(pos + 1);
            filename = filename.substring(0, pos);
        }
        if (filename.length > maxLength) {
            filename = filename.substring(0, maxLength - 1);
            filename += "~";
        }
        return filename + "." + ext;
    }
    return "";
}
    
shorten_ = function(content, maxLength) {
    var hideDiv = document.getElementById("hideDiv");
    var html = content.toString();
    hideDiv.innerHTML = content;
    content = escapeHtml_(hideDiv.textContent);
    if (content.length > maxLength) {
        content = content.substring(0, maxLength - 3);
        content += "...";
    }
    if (content == null || dojo.trim(content) == "") {
        if (html.indexOf("<img") >= 0) {
            content = "Hình minh họa...";
        } else if (html.indexOf("<table") >= 0) {
            content = "Bảng...";
        } else {
            content = "...";
        }
    }
    return content;
}
    
isFloat_ = function (str) {
    var value = str.toString();
    value = dojo.trim(value);
    return (value == parseFloat(value));
}
    
isInteger_ = function (str) {
    var value = str.toString();
    value = dojo.trim(value);
    return (value == parseInt(value));
}
    
escapeHtml_ = function (str) {
    var result = "";
    if (str != null && str != "") {
        result = str.toString().replace(/&/g, '&amp;');
        result = result.replace(/</g, '&lt;');
        result = result.replace(/>/g, '&gt;');
        result = result.replace(/'/g, '&39;');
        result = result.replace(/"/g, '&quot;');
    }
    return result;
}
    
deEscapeHtml_ = function (str) {
    var result = "";
    if (str != null && str != "") {
        result = str.toString().replace(/&amp;/g, '&');
        result = result.replace(/&lt;/g, '<');
        result = result.replace(/&gt;/g, '>');
        result = result.replace(/&39;/g, ',');
        result = result.replace(/&quot;/g, '\"');
    }
    return result;
}
    
/**
     * For trim text in fck editor
     */
htmlTrim_ = function(str) {
    var result = "";
    if (str != null && str != "") {
        result = str.toString().replace(/&nbsp;/g, ' ');
        result = dojo.trim(result);
        if (result.lastIndexOf("<br />") == result.length - 6) {
            result = result.substring(0, result.length - 6);
        }
    }
    return result;
}
    
changeGridSize = function(gridId) {
    var divParent = document.getElementById(gridId);
    var divHeader = divParent.firstChild.nextSibling;
    var divHeader = divHeader.firstChild.nextSibling;
    divParent.style.width = divHeader.style.width;
}
    
toggleSizeDialog = function(dialogID, func) {
    var divTitleDialog = document.getElementById(dialogID).firstChild.nextSibling;
    divTitleDialog.ondblclick = func;
    var buttonResize = divTitleDialog.firstChild.nextSibling.nextSibling.nextSibling.nextSibling;
    buttonResize.onclick = func;
}
    
click_ = function(elementId) {
    alert(elementId);
    var fireOnThis = document.getElementById(elementId);
    if (document.createEvent) {
        var evObj = document.createEvent('MouseEvents');
        evObj.initEvent('click', true, false);
        fireOnThis.dispatchEvent(evObj);
    } else if (document.createEventObject) {
        fireOnThis.fireEvent('onclick');
    }
    alert(elementId);
}

lowerFirstCharacter = function(str) {
    var content = str;
    if (str != null && str.length > 0) {
        content = str.substring(0, 1).toLowerCase() + str.substring(1);
    }
    return content;
}
    
getLinkAttach = function(prefix, attachId, filename, maxLength) {
    var link = "";
    if (attachId != null && attachId != undefined && filename != null && filename != undefined) {
        if (maxLength != null && maxLength != undefined) {
            filename = shortenFilename(filename, maxLength);
        }
        if (prefix != null && prefix != "") {
            prefix = prefix + "_";
        } else {
            prefix = "";
        }
        link = "<a id='" + prefix + "attach_" + attachId + "' href='/HVCT/attachAction%21download.do?attachId=" + attachId + "' target='downloadAttach_'>"
        + filename + "</a>";
    }
    return link;
}
Date.fromUKFormat = function(sUK) {
    var A = sUK.split(/[\\\/]/);
    A = [A[1],A[0],A[2]];
    return new Date(Date.parse(A.join('/')));
}

page.attachError_ = function() {
    alert("Lỗi: Dữ liệu không tồn tại!");
}
    
function getFileSize(element) {
    var fileSize = null;
    if (/MSIE (\d+\.\d+);/.test(navigator.userAgent)) {
        // IE
        var myFSO = new ActiveXObject("Scripting.FileSystemObject");
        var filepath = element.value;
        var file = myFSO.getFile(filepath);
        fileSize = file.size;
    } else if (/Firefox[\/\s](\d+\.\d+)/.test(navigator.userAgent)) {
        // Firefox
        if(element.files) {
            fileSize = element.files.item(0).fileSize;
        }
    }
    return fileSize;
}
    
isEmpty_ = function(str) {
    if (str != null && str != "") {
        return false;
    }
    else return true;
}
    
isEmptySelect_ = function(str) {
    if (str != null && str != "" && str != "-1") {
        return false;
    }
    else return true;
}
    
validateURL =function(url){
    var bReturn;
    url = url.toLowerCase();
    console.log("begin : "+url);
    var idx =0;
    if(url.indexOf("fpt://")==0){
        idx = url.indexOf("fpt://");
        url = url.substring(6);
    } else if(url.indexOf("http://")==0){
        idx = url.indexOf("fpt://");
        url = url.substring(7);
    } else if(url.indexOf("https://")==0){
        idx = url.indexOf("fpt://");
        url = url.substring(8);
    }
    console.log(url);
        
    if(url.indexOf("www.")==0){
        idx = url.indexOf("www.");
        url = url.substring(4);
    }
    idx= url.indexOf(".");
    if(idx==-1){
        return false;
    }
        
    //
    // check site name
    //
    do{
        idx= url.indexOf(".");
        if(idx==-1){
        } else {
            var siteName = url.substring(0, idx);
            var url = url.substring(idx+1,url.length);
            console.log("siteName:"+siteName);
            console.log("param:"+url);
            var formFormat = new RegExp("^[a-z,A-Z,0-9,_]*$");
            bReturn = formFormat.exec(siteName);
            if(!bReturn){
                return bReturn;
            }
        }
    } while (idx != -1);
    //
    // check param
    //
    var paramFormat = new RegExp("^[a-z,A-Z,0-9,_,?,=,:,/]*$");
    bReturn = paramFormat.exec(url);
    return bReturn;
}
    
validatePhone = function(phoneNumber){
    var bReturn = true;
    if(phoneNumber == null){
        bReturn = false;
    } else {
        phoneNumber = phoneNumber.trim();
        if(phoneNumber.length >14){
            bReturn = false
        } else {
            try{
                bReturn = !isNaN(parseInt(phoneNumber));
            } catch (en){
                bReturn = false;
            }
        }
    }
    return bReturn;
}   
    
function validateEmail(email) { 
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
} 
    
getRowLengthOfTable = function(tableId){
    var table = document.getElementById(tableId);
    var size = table.getElementsByTagName("tr").length;
    return size;
}

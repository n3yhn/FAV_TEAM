dojo.provide("vt.dataGrid.util");

(function(){
    trimContent:function(trimmed,content,ruler,cellWidth){
        try{
            if(content.length>1){
                var len = content.length;
                var mid = len%2==0?len/2:Math.round(len/2)-1;
                var left = content.substr(0,mid);
                //console.debug(left);
                var right = content.substr(mid,len-mid);
                //console.debug(right);
                ruler.innerHTML = trimmed + left;
                if(ruler.offsetWidth > cellWidth){
                    this.trimContent(trimmed,left,ruler,cellWidth);
                }
                else{
                    trimmed +=left;
                    this.trimContent(trimmed,right,ruler,cellWidth);
                }
            }
            return trimmed;
        }catch(e){
            alert("DungDV: " + e.message);
        }
    }
    
})();

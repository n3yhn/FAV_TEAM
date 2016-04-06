<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<script type="text/javascript">
     page.convertStatusToStr=function(inDatum){
         switch(inDatum){
            case 1:
                return 'Hoạt động';
            case 0:
                return 'Bị khóa';
        }
    }
    page.convertGenderToStr=function(inDatum){
         switch(inDatum){
            case -1:
                return 'Nam';
            case 1:
                return 'Nữ';
        }
    }
</script>
<!-- [ Query section -->
<jsp:include page="usrOfAd-QueryForm.jsp"/>
<!-- ] Query section -->


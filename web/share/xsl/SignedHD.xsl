<?xml version="1.0"?><!-- DWXMLSource="thongtinhoadon.xml" -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:s="http://www.w3.org/2000/09/xmldsig#" version="1.0"> 
   <xsl:template match="HDDT"> 
      <HTML> 
         <HEAD> 
            <TITLE>HDDT</TITLE>
           <style type="text/css">
			 h1 { line-height: 0.1;
			 font-size: 24px;
             }
			 h2 { line-height: 0.1;
			 font-size: 14px;
             }
             strong {
             font-size: 16px;			 
             }
			 .logo
			 {
			 width:200px;
			 }
             .tong
             {
             font-weight:bold;
             }
             .tongcuoi
             {
             font-weight:bold;
             color:#860000;
             }
             .tieude
             {
             margin: 10px 0px 2px 0px;
             font-weight:bold;
             }
             .tieude_bang
             {
             background-color:#dad7d7;
             color:#000000;
             }
             .tieude_phu_bang
             {
             background-color:#ececec;
             color:#000000;
             }
             .thongtinky
             {
             font-weight:bold;
             font-size:13px;
             margin:0px;
             }
			 .bangsolieu
			 {
			 background-color:#F9FDFE;
			 padding-bottom:10px;
			 }
			 .bangsolieu tr
			 {
			 padding:0;
			 }
			 .font1
			 {
			 color:#2f4e9b
			 }
			 .font2
			 {
			 color:#000000;
			 }
			 .font3
			 {
			 font-size:14px;
			 color:#000000;
			 }
			 .dongke
			 {
			 border-color:#2f4e9b
			 }
           </style> 
		   <!--<script>
		   function phien()
             {
             document.getElementById("phien").innerHTML= (document.getElementById("so").innerHTML).substring(0,3) + document.getElementById("phien").innerHTML ;
			 ky(parseInt(document.getElementById("thang").innerHTML),parseInt(document.getElementById("nam").innerHTML));		
			 dot(parseInt(document.getElementById("gtKy").innerHTML));				  
             }
			 function dot(k)
			 {
				 if(k == 1)
				 {
				 document.getElementById("kyhd").innerHTML ="";
				 document.getElementById("gtKy").innerHTML ="";
				 }
			 }
			 function ky(a,b)
			 {
			 	if(a ==12)
				{
					document.getElementById("thang").innerHTML ="1";
					b=b+1;
					document.getElementById("nam").innerHTML =b;
				}
				else
				{
					a= a+1;
					document.getElementById("thang").innerHTML =a;
				}
			 }
		   </script>    -->     
         </HEAD> 
         <BODY> 
		 <font face="Arial, Helvetica, sans-serif">		 </font>
		 <table border ="0" align ="center" width ="800"  style="background-color:#F9FDFE">
           <tr>&#160;</tr>
           <tr >
             <td valign="top" width ="100"><font face="Arial, Helvetica, sans-serif"><img src="http://192.168.140.98:8088/invoice/share/xsl/logo.png" class="logo" /> </font></td>
             <td width ="400" align ="center" class="font1"><font face="Arial, Helvetica, sans-serif" size="+2" style="font:bold"> 
               HÓA ĐƠN GTGT (TIỀN ĐIỆN) <br />
               <!-- HÓA ĐƠN GIÁ TRỊ GIA TĂNG(TIỀN ĐIỆN)-->
               <!--<xsl:choose>
               <xsl:when test="HOADON/@LOAI_HDON ='VC'">HÓA ĐƠN GIÁ TRỊ GIA TĂNG(TIỀN ĐIỆN)</xsl:when>
               <xsl:when test="HOADON/@LOAI_HDON ='TD'">HÓA ĐƠN GIÁ TRỊ GIA TĂNG(TIỀN ĐIỆN)</xsl:when>
               <xsl:otherwise>HÓA ĐƠN GIÁ TRỊ GIA TĂNG(TIỀN ĐIỆN)</xsl:otherwise>
             </xsl:choose>-->
               </font>
                 <font face="Arial, Helvetica, sans-serif"><strong>(Bản thể hiện của hóa đơn điện tử)</strong><br />
                 <!--<xsl:if test="//HOADON/@KY !='1'">-->
                 <a id="kyhd">Đợt: </a></font><a id="kyhd"><a class="font3" id="gtKy"> <font face="Arial, Helvetica, sans-serif"><xsl:value-of select="//HOADON/@KY"/> </font></a></a> <font face="Arial, Helvetica, sans-serif">
                   <!--</xsl:if>-->
                   &#160;&#160; &#160;&#160; Tháng: <a  class="font3"><xsl:value-of select="//HOADON/@THANG"/>/<xsl:value-of select="//HOADON/@NAM"/></a> <br />
                   Kỳ: </font><a class="font3"><a id="thang" class="font3">
                     <xsl:if test="//HOADON/@THANG !='12'">
                       <font face="Arial, Helvetica, sans-serif"><xsl:value-of select="number(//HOADON/@THANG) + 1"/>/ </font><a id="nam" class="font3"> <font face="Arial, Helvetica, sans-serif"><xsl:value-of select="//HOADON/@NAM"/> </font></a>
                     </xsl:if>
                     <xsl:if test="//HOADON/@THANG ='12'">
                       <font face="Arial, Helvetica, sans-serif">1/ </font><a id="nam" class="font3"> <font face="Arial, Helvetica, sans-serif"><xsl:value-of select="number(//HOADON/@NAM) + 1"/> </font></a>
                     </xsl:if>
                     </a> </a> <font face="Arial, Helvetica, sans-serif">&#160;&#160; Từ ngày: <a class="font3"> <xsl:value-of select="//HOADON/@NGAY_DKY"/> </a> &#160;&#160; Đến ngày: <a class="font3"> <xsl:value-of select="//HOADON/@NGAY_CKY"/> </a>
                       
                     </font></td>
             <td rowspan ="2" class="font1"><font face="Arial, Helvetica, sans-serif"> Mẫu số: <xsl:value-of select="HOADON/@MAU_SO" /> <br />
               Ký hiệu: <b class="font2"><xsl:value-of select="//HOADON/@KIHIEU_SERY" /></b> <br />
               Số: <b class="font2"><xsl:value-of select="//HOADON/@SO_SERY" /></b> <br />
               ID HĐ: <a class="font2"><xsl:value-of select="//HOADON/@ID_HDON"/></a> </font></td>
           </tr>
		   <tr>
		   <td colspan="3" align="center" class="font2"><font face="Arial, Helvetica, sans-serif">  &#160;&#160; &#160;&#160; &#160;&#160;
		   			<xsl:if test="//HOADON/@ID_HDON_DC !=''">
                         <!--<br />-->
						 	<xsl:choose>
							   <xsl:when test="HOADON/@LOAI_DCHINH ='LL'"><i class="font2">(Là hóa đơn lập lại của hóa đơn có sery <b><xsl:value-of select="//HOADON/@SERY_DCHINH" /></b> ký hiệu <b><xsl:value-of select="//HOADON/@KYHIEU_DCHINH" /></b> )</i></xsl:when>
							   <xsl:when test="HOADON/@LOAI_DCHINH ='TT'"><i class="font2">(Là hóa đơn truy thu của hóa đơn có sery <b><xsl:value-of select="//HOADON/@SERY_DCHINH" /></b> ký hiệu <b><xsl:value-of select="//HOADON/@KYHIEU_DCHINH" /></b> )</i></xsl:when>
							   <xsl:otherwise><i class="font2">(Là hóa đơn thoái hoàn của hóa đơn có sery <b><xsl:value-of select="//HOADON/@SERY_DCHINH" /></b> ký hiệu <b><xsl:value-of select="//HOADON/@KYHIEU_DCHINH" /></b> )</i></xsl:otherwise>
             				</xsl:choose>
                         
                       </xsl:if></font>
		   </td>
		   </tr>
         </table>
		 <font face="Arial, Helvetica, sans-serif">
         <table border ="0" align ="center" width ="800" VALIGN="top" Cellpadding="0"  class="bangsolieu; font1" style="background-color:#F9FDFE">
             <!--Bảng thông tin về điện lực và khách hàng-->
             <tr>
               <td colspan ="8">
                 <p class="tieude">
                   <xsl:value-of select="//HOADON/@TEN_DVIQLY"/>                 </p>               </td> 
             </tr>
             <tr>
               <td colspan ="8">
               Địa chỉ: <a class="font2"><xsl:value-of select="//HOADON/@DIA_CHI"/></a>               </td>
               </tr>
             <tr>
                 <td width="80">
                 Điện thoại:               </td>
                 <td width="150" class="font2">
                   19001122                 </td>
                 <td width="80">
                   MST:                 </td>
                 <td width="150" class="font2">
                   <xsl:value-of select="//HOADON/@MASO_THUEDL"/>                 </td>
                 <td width="100">
                   ĐT sửa chữa:                 </td>
                 <td class="font2">
                   19001122                 </td>
               </tr>
             <tr>
                 <td colspan ="8">
                   <p class="tieude">
                     Tên khách hàng: <a class="font2"> <xsl:value-of select="//HOADON/@TEN_KHANG" /></a>                   </p>                 </td>                 
               </tr>
             <tr>
                 <td colspan ="8">
                   Địa chỉ khách hàng: <a class="font2"> <xsl:choose>
                <xsl:when test="//HOADON/@DIA_CHITT != ''"><xsl:value-of select="//HOADON/@DIA_CHITT"/></xsl:when>
			   <xsl:when test="//HOADON/@DCHI_KHANGTT != ''"><xsl:value-of select="//HOADON/@DCHI_KHANGTT"/></xsl:when>
			   </xsl:choose></a>               </td>
               </tr>
             <tr>
                 <td>
                   Điện thoại:                 </td>
                 <td class="font2">                 </td>
                 <td>
                   MST:                 </td>
                 <td class="font2">
                   <xsl:value-of select="//HOADON/@MASO_THUEKH"/>                 </td>
                 <td>
                   Số công tơ:                 </td>
                 <td class="font2">
                   <xsl:value-of select="//HOADON/@SO_CTO"/>                 </td>
                 <td width="80">
                   Số hộ :                 </td>
                 <td class="font2">
                   <xsl:value-of select="//HOADON/@SO_HO"/>                 </td>
               </tr>
             <tr>
                 <td>
                   Mã KH:                 </td>
                 <td class="font2">
                   <xsl:value-of select="//HOADON/@MA_KHANG"/>                 </td>
                 <td>
                   Mã T.Toán:                 </td>
                 <td class="font2">
                   <xsl:value-of select="//HOADON/@MA_KHANGTT"/>                 </td>
                 <td>
                   Mã NN:                 </td>
                 <td class="font2">
                   <xsl:value-of select="HOADON/@MA_NN"/>                 </td>
                 <td>
                   Mã tổ:                 </td>
                 <td class="font2">
                   <xsl:value-of select="//HOADON/@MA_TO"/>                 </td>
               </tr>
             <tr>
                 <td>
                   Mã trạm:                 </td>
                 <td class="font2">
				 <xsl:choose>
               <xsl:when test="count(HOADON/@DINH_DANH)=0"><xsl:value-of select="HOADON/@MA_TRAM"/></xsl:when>
               <xsl:when test="count(HOADON/@DINH_DANH)=1"><xsl:value-of select="HOADON/@DINH_DANH"/></xsl:when>
			   </xsl:choose>
				<!-- <xsl:if test="count(HOADON/@MA_TRAM)=1">
                   <xsl:value-of select="HOADON/@MA_TRAM"/>
				   </xsl:if>-->                 </td>
                 <td>
                   P GCS:                 </td>
                 <td class="font2" id ="phien">
                   <xsl:value-of select="substring(HOADON/@MA_SOGCS,1,3)"/><xsl:value-of select="//HOADON/@PHIEN_GCS"/>                 </td>
                 <td>
                   Sổ GCS:                 </td>
                 <td class="font2" id="so">
                   <xsl:value-of select="//HOADON/@MA_SOGCS"/>                 </td>
                 <td>
                   Cấp ĐA:                 </td>
                 <td class="font2">
                   <xsl:value-of select="HOADON/@MA_CAPDA"/>                 </td>
               </tr>
             <tr>
                 <td>
                   Mã giá:                 </td>
                 <td class="font2">
                   <xsl:value-of select="HOADON/@MA_GIA"/>                 </td>
               </tr>   
           </table>
           <!--<xsl:if test="//HOADON/@LOAI_HDON ='TD' or //HOADON/@LOAI_HDON ='TC'" >-->
             <!--Trường hợp là hóa đơn tiền điện-->
            <TABLE Cellpadding="6" Border="2" align ="center" width ="800" style="background-color:#F9FDFE;padding:5px;border:1px; border-collapse: collapse;" class="dongke">
              <TR class="font1;dongke" style="background-color:#dad7d7">
                <Th align ="center" width ="50" class="dongke">
                  Bộ chỉ số                </Th>
                <Th align ="center" width ="70" class="dongke">
                  Chỉ số mới                </Th>
                <Th align ="center" width ="70" class="dongke">
                  Chỉ số cũ                </Th>
                <Th align ="center" Border="1" width ="60" class="dongke">
                  Hệ số nhân                </Th>
                <Th align ="center" width ="60" class="dongke">
                  ĐN tiêu thụ                </Th>
				<Th align ="center" width ="60" class="dongke">
                  ĐN trực tiếp                </Th>
				<Th align ="center" width ="60" class="dongke">
                  ĐN trừ phụ                </Th>
				<Th align ="center" width ="60" class="dongke">
                  ĐN tính giá                </Th>
                <Th align ="center" width ="50" class="dongke">
                  Đơn giá                </Th>
                <Th align ="center" class="dongke">
                  Thành tiền                </Th>              
            </TR>              
              <xsl:for-each select="//HOADON/DIEMDO">
                <!--Trường hợp điểm đo bằng 1-->
                <xsl:if test="count(../../../HDDT/HOADON/DIEMDO)=1">
				  <xsl:if test="../../../HDDT/HOADON/@COSFI !='0'">
                  <TR class="tieude_phu_bang;dongke">                  
                  <td colspan="10" border="5px" class="font2;dongke">
                  <a class="font1">Mã Điểm đo:</a> <xsl:value-of select="@MA_DDO"/> <a class="font1"> - SLVC/SLHC</a> (<xsl:value-of select="@VO_CONG"/>/<xsl:value-of select="@HUU_CONG"/>) <a class="font1">- Hệ số Cosfi </a><xsl:value-of select="../../../HDDT/HOADON/@COSFI"/> <a class="font1">- Hệ số K % </a> <xsl:value-of select="../../../HDDT/HOADON/@TYLE_PHAT"/>                  </td>    
				  <!--bắt đầu vào bảng lặp-->              
                </TR>     
				</xsl:if>  
				<xsl:if test="count(CS/CHISO[@BCS='KT'])&#62;0">      
                 <tr class="dongke">
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
                         <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>                         
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
					<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
						</xsl:if>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke"> 
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
					<xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
					<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='KT']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='KT']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='KT']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr> 
				</xsl:if>
				<xsl:if test="count(CS/CHISO[@BCS='BT'])&#62;0">   
				<tr class="dongke">
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
                       <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>                         
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
				<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				 <xsl:for-each select="CS/CHISO[@BCS='BT']" >
				 <xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
				<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='BT']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='BT']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='BT']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr>
				</xsl:if>
				<xsl:if test="count(CS/CHISO[@BCS='CD'])&#62;0">   
				<tr>
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>					</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
                         <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
				<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				 <xsl:for-each select="CS/CHISO[@BCS='CD']" >
				 <xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
				<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
					<xsl:for-each select="CT/CHITIET[@BCS='CD']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='CD']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='CD']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr> 
				</xsl:if>
				<xsl:if test="count(CS/CHISO[@BCS='TD'])&#62;0">   
				<tr>
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
                         <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
				<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				 <xsl:for-each select="CS/CHISO[@BCS='TD']" >
				 <xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
				<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='TD']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='TD']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='TD']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr> 
				</xsl:if>
				<xsl:if test="count(CS/CHISO[@BCS='VC'])&#62;0">   
				<tr>
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
                         <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke"> 
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
				<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				 <xsl:for-each select="CS/CHISO[@BCS='VC']" >
				 <xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke"> 
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
				<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='VC']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='VC']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='VC']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr>
				</xsl:if>
                </xsl:if>
                <!--Trường hợp nhiều điểm đo-->
                <xsl:if test="@MA_DDO !=''">
                  <xsl:if test="count(../../../HDDT/HOADON/DIEMDO)&#62;1">
                  <TR class="tieude_phu_bang;dongke">                  
                  <td colspan="10" border="5px" class="dongke">
                  <a class="font1">Mã Điểm đo:</a> <xsl:value-of select="@MA_DDO"/> <a class="font1"> - SLVC/SLHC</a> (<xsl:value-of select="@VO_CONG"/>/<xsl:value-of select="@HUU_CONG"/>) <a class="font1">- Hệ số Cosfi </a><xsl:value-of select="@COSFI"/> <a class="font1">- Hệ số K %  </a><xsl:value-of select="@TYLE_PHAT"/>                  </td>                  
                </TR>
				<xsl:if test="count(CS/CHISO[@BCS='KT'])&#62;0">      
                  <tr class="dongke">
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
                         <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>                         
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
					<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
						</xsl:if>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke"> 
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
					<xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='KT']" >
					<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='KT']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='KT']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='KT']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr> 
				</xsl:if>
				<xsl:if test="count(CS/CHISO[@BCS='BT'])&#62;0">   
				<tr class="dongke">
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
                       <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>                         
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
				<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				 <xsl:for-each select="CS/CHISO[@BCS='BT']" >
				 <xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='BT']" >
				<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='BT']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='BT']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='BT']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr>
				</xsl:if>
				<xsl:if test="count(CS/CHISO[@BCS='CD'])&#62;0">   
				<tr>
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>					</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
                         <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
				<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				 <xsl:for-each select="CS/CHISO[@BCS='CD']" >
				 <xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='CD']" >
				<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
					<xsl:for-each select="CT/CHITIET[@BCS='CD']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='CD']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='CD']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr> 
				</xsl:if>
				<xsl:if test="count(CS/CHISO[@BCS='TD'])&#62;0">   
				<tr>
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
                         <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
				<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				 <xsl:for-each select="CS/CHISO[@BCS='TD']" >
				 <xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='TD']" >
				<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='TD']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='TD']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='TD']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr> 
				</xsl:if>
				<xsl:if test="count(CS/CHISO[@BCS='VC'])&#62;0">   
				<tr>
				<td align="center" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
                         <xsl:value-of select="@BCS"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
                         <xsl:value-of select="translate(format-number(@CHISO_MOI,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>				</td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
                         <xsl:if test="@CHISO_CU = 0">
                           0
                         </xsl:if>
                         <xsl:if test="@CHISO_CU != 0">
                           <xsl:value-of select="translate(format-number(@CHISO_CU,'##,###'),',','.')"/>
                         </xsl:if>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
                         <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                         <br></br>
                       </xsl:for-each>					   </td>
				<td align="right" valign="top" class="dongke"> 
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
				<xsl:if test="@SAN_LUONG = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SAN_LUONG != 0">
                        <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				 <xsl:for-each select="CS/CHISO[@BCS='VC']" >
				 <xsl:if test="@SLUONG_TTIEP = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TTIEP != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TTIEP,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke"> 
				<xsl:for-each select="CS/CHISO[@BCS='VC']" >
				<xsl:if test="@SLUONG_TRPHU = 0">
                           <br></br>
                         </xsl:if>
                         <xsl:if test="@SLUONG_TRPHU != 0">
                        <xsl:value-of select="translate(format-number(@SLUONG_TRPHU,'##,###'),',','.')"/>
                        <br></br>
						</xsl:if>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='VC']">
                        <p style="text-align:right;margin:0;">
                          <xsl:value-of select="translate(format-number(@DIEN_TTHU,'##,###'),',','.')"/>                        </p>                        
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='VC']">
                        <xsl:value-of select="translate(format-number(@DON_GIA,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				<td align="right" valign="top" class="dongke">
				<xsl:for-each select="CT/CHITIET[@BCS='VC']">
                        <xsl:value-of select="translate(format-number(@SO_TIEN,'##,###'),',','.')"/>
                        <br></br>
                      </xsl:for-each>					  </td>
				</tr>
				</xsl:if>     
                </xsl:if>
               </xsl:if>
              </xsl:for-each>              
              <TR class="dongke">
                <TD colspan ="7" class="dongke">
                  <span class="tong; font1">Cộng</span>
                    <!--<xsl:for-each select="//s:Signature">
                  <xsl:value-of select="s:SignatureValue"/>
                  <br></br>
                </xsl:for-each>-->                </TD>
                <TD align="right" valign="top" class="dongke">
                  <!--<xsl:value-of select="@DIEN_TTHU"/>-->
                  <b class="dongke">
				  <xsl:if test="//HOADON/@DIEN_TTHU != ''">
                    <xsl:value-of select="translate(format-number(//HOADON/@DIEN_TTHU,'##,###'),',','.')"/>
					</xsl:if>
                  </b>                </TD>
                <TD class="dongke">                </TD>
                <TD align="right" valign="top" class="dongke">
                  <!--<xsl:value-of select="@SO_TIEN"/>-->
                  <span class="tong">
				   <xsl:if test="//HOADON/@SO_TIEN != ''">
                    <xsl:value-of select="translate(format-number(//HOADON/@SO_TIEN,'##,###'),',','.')"/>
					</xsl:if>
                  </span>                </TD>               
              </TR>
              <TR>
                <TD colspan="9" class="dongke">
                  <span class="tong; font1">
                    Thuế suất GTGT: <a class="font2"> <xsl:value-of select="HOADON/@TYLE_THUE" />% </a>  Thuế GTGT                  </span>                 </TD>
                <TD align="right" valign="top" class="dongke">
                  <!--<xsl:value-of select="@TIEN_GTGT"/>-->
                  <span class="tong">
				  <xsl:if test="//HOADON/@TIEN_GTGT != ''">
                    <xsl:value-of select="translate(format-number(//HOADON/@TIEN_GTGT,'##,###'),',','.')"/>
					</xsl:if>
                  </span>                </TD>                
              </TR>
              <TR>
                <TD colspan="9" class="dongke">
                  <span class="tong; font1">Tổng tiền thanh toán</span>                </TD>
                <TD align="right" valign="top" class="dongke">
                  <!--<xsl:value-of select="@TONG_TIEN"/>-->
                  <span class="tong">
				  <xsl:if test="//HOADON/@TONG_TIEN != ''">
                    <xsl:value-of select="translate(format-number(//HOADON/@TONG_TIEN,'##,###'),',','.')"/>
					</xsl:if>
                  </span>                </TD>                
              </TR>
              <TR>
                <td colspan ="10" class="dongke">
                     <i class="font1">Số tiền bằng chữ:&#160;</i> <i><xsl:value-of select="HOADON/@SO_TIEN_BANG_CHU"/>.</i>                </td>
              </TR>
			  <xsl:if test="//s:SignatureValue !=''">
              <tr>
                <td colspan ="10" align ="right" class="dongke">
				
					<p class="thongtinky">
                    <a class="font1">Ngày:&#160;</a> <xsl:value-of select="//HOADON/@NGAY_KY"/>                  </p>
                  <p class="thongtinky">
                    <a class="font1">Người ký:&#160;</a> <xsl:value-of select="//HOADON/@TEN_NGUOI_KY"/>                  </p>                </td>
              </tr>
			  </xsl:if>
             </TABLE>
           <!--</xsl:if>-->
          <!-- <xsl:if test="//HOADON/@LOAI_HDON ='VC'">
             Trường hợp là hóa đơn vô công
            <TABLE Cellpadding="6" Border="1" align ="center" width ="800"  style="background-color:#fdfaee">
               <TR>
                 <Th width ="90">
                   Số công tơ
                 </Th>
                 <Th width ="80">
                   Chỉ số mới
                 </Th>
                 <Th width ="80">
                   Chỉ số cũ
                 </Th>
                 <Th width ="70">
                   HS nhân
                 </Th>
                 <Th width ="160">
                   Điện năng phản kháng
                 </Th>
               </TR>
               <xsl:for-each select="//HOADON/DIEMDO/CS/CHISO">
                 <tr>
                   <td>
                     <xsl:value-of select="@SO_CTO"/>
                   </td>
                   <td>
                     <xsl:value-of select="@CHISO_MOI"/>
                   </td>
                   <td>
                     <xsl:value-of select="@CHISO_CU"/>
                   </td>
                   <td>
                     <xsl:value-of select="translate(format-number(@HS_NHAN,'##,###'),',','.')"/>
                   </td>
                   <td>
                     <xsl:value-of select="translate(format-number(@SAN_LUONG,'##,###'),',','.')"/>
                   </td>
                 </tr>
               </xsl:for-each>
               <tr>
                 <td colspan="4">Cộng</td>
                 <td>
                   <xsl:value-of select="//HOADON/@DIEN_TTHU"/>
                 </td>
               </tr>
               <xsl:for-each select="//HOADON/DIEMDO">
                 <tr>
                   <td colspan="4">Tổng ĐN hữu công</td>
                   <td>
                     <xsl:value-of select="@HUU_CONG"/>                  
                   </td>
               </tr>
                 <tr>
                 <td colspan="3">Cos &#966;</td>
                 <td colspan="2">Hệ số k:</td>
               </tr>
                 <tr>
                 <td colspan="4">Tiền Đn hữu công (Tp)</td>
                 <td>
                   <xsl:value-of select="@TIEN_HUUCONG"/>
                 </td>
               </tr>
                 <tr>
                 <td colspan="4">Tiền CSPK (Tp x k)</td>
                 <td>
                   <xsl:value-of select="@TIEN_VOCONG"/></td>
               </tr>
                 <tr>
                 <td colspan="4">Thuế suất GTGT: 10% Thuế GTGT:</td>
                 <td>giá trị</td>
               </tr>
                 <tr>
                 <td colspan="4">Tổng tiền thanh toán</td>
                 <td>
                  
                 </td>
               </tr>
               </xsl:for-each>
               <tr>
                 <td colspan="5">
                   <em id ="desc"  align ="left">
                     <xsl:value-of select="//HOADON/@TONG_TIEN"/>
                   </em>
                 </td>
               </tr>               
               <tr></tr>
             </TABLE>
             </xsl:if>-->
           <!--<button onclick="DocTienBangChu(127423)"></button>-->
         </font>
		 </BODY> 
      </HTML> 
   </xsl:template>  
</xsl:stylesheet> 
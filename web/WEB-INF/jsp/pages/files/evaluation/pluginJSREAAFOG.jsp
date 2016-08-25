<%-- 
    Document   : pluginJS
    Created on : Oct 12, 2015, 5:57:46 PM
    Author     : hieptq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    var VtPlugin_REAAFOG = {
        initPlugin_REAAFOG: function ()
        {
            return document.getElementById('plugin_REAAFOG').getVersion == null ? false : true;
        },
        getVersion_REAAFOG: function ()
        {
            return document.getElementById('plugin_REAAFOG').getVersion();
        },
        getFileName: function ()
        {
            return document.getElementById('plugin_REAAFOG').getFileName();
        },
        getCert: function ()
        {
            return document.getElementById('plugin_REAAFOG').getCert();
        },
        getCertChain: function ()
        {
            return document.getElementById('plugin_REAAFOG').getCertChain();
        },
        signHash: function (base64Hash, certSerial)
        {
            return document.getElementById('plugin_REAAFOG').signHash(base64Hash, certSerial);
        },
        signPdf: function (filePath, reason, location)
        {
            return document.getElementById('plugin_REAAFOG').signPdf(filePath, reason, location);
        },
        signPdf : function (filePath, reason, location, fileDest)
        {
            return document.getElementById('plugin_REAAFOG').signPdf(filePath, reason, location, fileDest);
        },
                signOOXml: function (filePath)
                {
                    return document.getElementById('plugin_REAAFOG').signOOXml(filePath);
                },
        signOOXml : function (filePath, fileDest)
        {
            return document.getElementById('plugin_REAAFOG').signOOXml(filePath, fileDest);
        },
                signXml: function (filePath)
                {
                    return document.getElementById('plugin_REAAFOG').signOOXml(filePath);
                },
        signXml : function (filePath, fileDest)
        {
            return document.getElementById('plugin_REAAFOG').signXml(filePath, fileDest);
        }

    };


    function uploadCertOfFile_REAAFOG(fileId) {
        try {
            if (!initPlugin_REAAFOG()) {
                alert("Ký số không thành công !");
                return false;
            }
            var certChain = new String(VtPlugin_REAAFOG.getCertChain());
            if (certChain !== '')
            {
                certChain = certChain.replace(/\n|\r/g, "");
                var data = {
                    certChain: certChain,
                    fileId: fileId
                };
                return data;
            } else
            {
                alert("Ký số không thành công !");
                return false;
            }
        } catch (e) {
            alert("Ký số không thành công !");
            return false;
        }

    }

    function initPlugin_REAAFOG() {
        if (!(VtPlugin_REAAFOG.initPlugin_REAAFOG()) || VtPlugin_REAAFOG.getVersion_REAAFOG() !== '1.1.0.0') {
            alert('Ban can cai dat plugin ViettelCASigner');
            var pathname = window.location.pathname;
            pathname = pathname.replace("index.zul", "");
            var url = pathname + "Share/js/ca/ViettelCASigner.msi"
            window.open(url);
            return false;
        }
        return true;
    }

    function signAndSubmit_REAAFOG() {
        try {
            var base64Hash = dijit.byId("txtBase64Hash_REAAFOG").getValue();
            var certSerial = dijit.byId("txtCertSerial_REAAFOG").getValue();
            if (base64Hash !== '' && certSerial !== '') {
                var base64Signature = new String(VtPlugin_REAAFOG.signHash(base64Hash, certSerial));
                console.log('base64: ' + base64Hash);
                console.log('certSerial: ' + certSerial);
                base64Signature = base64Signature.replace(/\n|\r/g, "");
                return base64Signature;
            } else {
                alert("Ký số không thành công !");
                return false;
            }
        } catch (e) {
            alert("Ký số không thành công !");
            return false;
        }
    }

    function signAndSubmitOriginalFile() {
        try {
            var base64Hash = dijit.byId("txtBase64Hash_REAAFOG0").getValue();
            var certSerial = dijit.byId("txtCertSerial_REAAFOG").getValue();
            if (base64Hash !== '' && certSerial !== '') {
                var base64Signature = new String(VtPlugin_REAAFOG.signHash(base64Hash, certSerial));
                console.log('base64: ' + base64Hash);
                console.log('certSerial: ' + certSerial);
                base64Signature = base64Signature.replace(/\n|\r/g, "");
                return base64Signature;
            } else {
                alert("Ký số không thành công !");
                return false;
            }
        } catch (e) {
            alert("Ký số không thành công !");
            return false;
        }
    }
    function signAndSubmitOriginalFileAA() {
        try {
            var base64Hash = dijit.byId("txtBase64Hash_REAAFOG").getValue();
            var certSerial = dijit.byId("txtCertSerial_REAAFOG").getValue();
            alert(base64Hash);
            alert(certSerial);
            if (base64Hash !== '' && certSerial !== '') {
                var base64Signature = new String(VtPlugin_REAAFOG.signHash(base64Hash, certSerial));
                console.log('base64: ' + base64Hash);
                console.log('certSerial: ' + certSerial);
                base64Signature = base64Signature.replace(/\n|\r/g, "");
                return base64Signature;
            } else {
                alert("Ký số không thành công !");
                return false;
            }
        } catch (e) {
            alert("Ký số không thành công !");
            return false;
        }
    }
</script>
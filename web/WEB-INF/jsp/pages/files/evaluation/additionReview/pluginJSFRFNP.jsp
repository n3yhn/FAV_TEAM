<%-- 
    Document   : pluginJS
    Created on : Oct 12, 2015, 5:57:46 PM
    Author     : hieptq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    var VtPlugin = {
        initPlugin: function()
        {
            return document.getElementById('plugin0').getVersion == null ? false : true;
        },
        getVersion: function()
        {
            return document.getElementById('plugin0').getVersion();
        },
        getFileName: function()
        {
            return document.getElementById('plugin0').getFileName();
        },
        getCert: function()
        {
            return document.getElementById('plugin0').getCert();
        },
        getCertChain: function()
        {
            return document.getElementById('plugin0').getCertChain();
        },
        signHash: function(base64Hash, certSerial)
        {
            return document.getElementById('plugin0').signHash(base64Hash, certSerial);
        },
        signPdf: function(filePath, reason, location)
        {
            return document.getElementById('plugin0').signPdf(filePath, reason, location);
        },
        signPdf : function(filePath, reason, location, fileDest)
        {
            return document.getElementById('plugin0').signPdf(filePath, reason, location, fileDest);
        },
                signOOXml: function(filePath)
                {
                    return document.getElementById('plugin0').signOOXml(filePath);
                },
        signOOXml : function(filePath, fileDest)
        {
            return document.getElementById('plugin0').signOOXml(filePath, fileDest);
        },
                signXml: function(filePath)
                {
                    return document.getElementById('plugin0').signOOXml(filePath);
                },
        signXml : function(filePath, fileDest)
        {
            return document.getElementById('plugin0').signXml(filePath, fileDest);
        }

    };


    function uploadCertOfFile(fileId) {
        try {
            //var wgt = zk.Widget.$('$businessWindow');
            if (!initPlugin()) {
                alert("Ký số không thành công !");
                return false;
            }
            // var base64Cert = new String(VtPlugin.getCert());
            var certChain = new String(VtPlugin.getCertChain());
            if (certChain !== '')
            {
                certChain = certChain.replace(/\n|\r/g, "");
                var data = {
                    certChain: certChain,
                    fileId: fileId
                };
                //zAu.send(new zk.Event(wgt, 'onUploadCert', data));
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

    function initPlugin() {
        if (!(VtPlugin.initPlugin()) || VtPlugin.getVersion() !== '1.1.0.0') {
            alert('Ban can cai dat plugin ViettelCASigner');
            var pathname = window.location.pathname;
            pathname = pathname.replace("index.zul", "");
            var url = pathname + "Share/js/ca/ViettelCASigner.msi"
            window.open(url);
            return false;
        }
        return true;
    }

    function signAndSubmit() {
        try {
            var base64Hash = dijit.byId("txtBase64HashFRFNP").getValue();
            var certSerial = dijit.byId("txtCertSerialFRFNP").getValue();
            if (base64Hash !== '' && certSerial !== '') {
                var base64Signature = new String(VtPlugin.signHash(base64Hash, certSerial));
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
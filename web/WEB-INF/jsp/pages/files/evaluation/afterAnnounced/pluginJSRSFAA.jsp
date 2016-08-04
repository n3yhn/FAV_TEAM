<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    var VtPluginRSFAA = {
        initPluginRSFAA: function()
        {
            return document.getElementById('plugin1').getVersion == null ? false : true;
        },
        getVersion: function()
        {
            return document.getElementById('plugin1').getVersion();
        },
        getFileName: function()
        {
            return document.getElementById('plugin1').getFileName();
        },
        getCert: function()
        {
            return document.getElementById('plugin1').getCert();
        },
        getCertChain: function()
        {
            return document.getElementById('plugin1').getCertChain();
        },
        signHash: function(base64Hash, certSerial)
        {
            return document.getElementById('plugin1').signHash(base64Hash, certSerial);
        },
        signPdf: function(filePath, reason, location)
        {
            return document.getElementById('plugin1').signPdf(filePath, reason, location);
        },
        signPdf : function(filePath, reason, location, fileDest)
        {
            return document.getElementById('plugin1').signPdf(filePath, reason, location, fileDest);
        },
                signOOXml: function(filePath)
                {
                    return document.getElementById('plugin1').signOOXml(filePath);
                },
        signOOXml : function(filePath, fileDest)
        {
            return document.getElementById('plugin1').signOOXml(filePath, fileDest);
        },
                signXml: function(filePath)
                {
                    return document.getElementById('plugin1').signOOXml(filePath);
                },
        signXml : function(filePath, fileDest)
        {
            return document.getElementById('plugin1').signXml(filePath, fileDest);
        }

    };


    function uploadCertOfFileRSFAA(fileId) {
        try {
            if (!initPluginRSFAA()) {
                alert("Ký số không thành công !");
                return false;
            }
            var certChain = new String(VtPluginRSFAA.getCertChain());
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

    function initPluginRSFAA() {
        if (!(VtPluginRSFAA.initPluginRSFAA()) || VtPluginRSFAA.getVersion() !== '1.1.0.0') {
            alert('Ban can cai dat plugin ViettelCASigner');
            var pathname = window.location.pathname;
            pathname = pathname.replace("index.zul", "");
            var url = pathname + "Share/js/ca/ViettelCASigner.msi"
            window.open(url);
            return false;
        }
        return true;
    }

    function signAndSubmitRSFAA() {
        try {
            var base64Hash = dijit.byId("txtBase64HashFRFNP").getValue();
            var certSerial = dijit.byId("txtCertSerialFRFNP").getValue();
            if (base64Hash !== '' && certSerial !== '') {
                var base64Signature = new String(VtPluginRSFAA.signHash(base64Hash, certSerial));
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
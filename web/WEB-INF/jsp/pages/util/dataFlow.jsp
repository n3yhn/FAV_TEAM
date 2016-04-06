<%-- 
    Document   : dataFlow
    Created on : Jun 20, 2012, 2:34:20 PM
    Author     : KienDV4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

    function drawFlow(id) {
        color_processing = "rgb(113,153,236)";
        color_finish = "rgb(230,193,119)";
        color_border = "rgb(200,163,89)";
        color_anchor = "rgb(200,163,89)";
        color_active = "rgb(255,100,100)";
        fixedWidth = 0;
        fixedHeight = 0;

        nodeList = new Array();
        nodeLength = 0;
        id = id;
        nodeHeight = 40;
        nodeWidth = 100;
        activeNode = -1;
        xpoint = 0;
        ypoint = 0;
        mouseFlags = 0;


        this.addNode = function(nodeId, nodeName, nodeObj, x, y, color, previousId) {
            var node = new Object();
            node.nodeName = nodeName;
            node.nodeObj = nodeObj;
            node.nodeId = nodeId;
            node.previousId = previousId;

            node.x = x;
            node.y = y;
            node.color = color;
            nodeList[nodeLength] = node;

            nodeLength = nodeLength + 1;
            this.drawGraph();
        };

        this.addGraph = function(levelW, levelH, customInfo) {

            this.addNode(nodeLength,
                    customInfo.nodeName,
                    levelW * (nodeWidth + 20),
                    levelH * (nodeHeight + 20),
                    color_processing,
                    (nodeLength == 0 ? null : (nodeLength - 1)));

            var tmp = customInfo.childs.length;
            for (var i = 0; i < tmp; i++) {
                this.addGraph(levelW + 1, levelH + i, customInfo.childs[i]);
            }
        };

        this.addNextNode = function(nodeId, nodeName) {
            var node = new Object();
            node.nodeName = nodeName;
            node.nodeId = nodeId;
            node.previousId = nodeLength - 1;
            if (nodeLength == 0) {
                node.x = 10;
                node.y = 10;

            } else {
                node.x = nodeList[nodeLength - 1].x + nodeWidth + 50;
                node.y = nodeList[nodeLength - 1].y;
            }
            node.color = color_processing;
            nodeList[nodeLength] = node;
            nodeLength = nodeLength + 1;
            this.drawGraph();
        };

        this.checkIEVersion = function() {
            var bReturn = false;

            var BrowserDetect = {
                init: function() {
                    this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
                    this.version = this.searchVersion(navigator.userAgent)
                            || this.searchVersion(navigator.appVersion)
                            || "an unknown version";
                    this.OS = this.searchString(this.dataOS) || "an unknown OS";
                },
                searchString: function(data) {
                    for (var i = 0; i < data.length; i++) {
                        var dataString = data[i].string;
                        var dataProp = data[i].prop;
                        this.versionSearchString = data[i].versionSearch || data[i].identity;
                        if (dataString) {
                            if (dataString.indexOf(data[i].subString) != -1)
                                return data[i].identity;
                        }
                        else if (dataProp)
                            return data[i].identity;
                    }
                },
                searchVersion: function(dataString) {
                    var index = dataString.indexOf(this.versionSearchString);
                    if (index == -1)
                        return;
                    return parseFloat(dataString.substring(index + this.versionSearchString.length + 1));
                },
                dataBrowser: [
                    {
                        string: navigator.userAgent,
                        subString: "Chrome",
                        identity: "Chrome"
                    },
                    {string: navigator.userAgent,
                        subString: "OmniWeb",
                        versionSearch: "OmniWeb/",
                        identity: "OmniWeb"
                    },
                    {
                        string: navigator.vendor,
                        subString: "Apple",
                        identity: "Safari",
                        versionSearch: "Version"
                    },
                    {
                        prop: window.opera,
                        identity: "Opera",
                        versionSearch: "Version"
                    },
                    {
                        string: navigator.vendor,
                        subString: "iCab",
                        identity: "iCab"
                    },
                    {
                        string: navigator.vendor,
                        subString: "KDE",
                        identity: "Konqueror"
                    },
                    {
                        string: navigator.userAgent,
                        subString: "Firefox",
                        identity: "Firefox"
                    },
                    {
                        string: navigator.vendor,
                        subString: "Camino",
                        identity: "Camino"
                    },
                    {// for newer Netscapes (6+)
                        string: navigator.userAgent,
                        subString: "Netscape",
                        identity: "Netscape"
                    },
                    {
                        string: navigator.userAgent,
                        subString: "MSIE",
                        identity: "Explorer",
                        versionSearch: "MSIE"
                    },
                    {
                        string: navigator.userAgent,
                        subString: "Gecko",
                        identity: "Mozilla",
                        versionSearch: "rv"
                    },
                    {// for older Netscapes (4-)
                        string: navigator.userAgent,
                        subString: "Mozilla",
                        identity: "Netscape",
                        versionSearch: "Mozilla"
                    }
                ],
                dataOS: [
                    {
                        string: navigator.platform,
                        subString: "Win",
                        identity: "Windows"
                    },
                    {
                        string: navigator.platform,
                        subString: "Mac",
                        identity: "Mac"
                    },
                    {
                        string: navigator.userAgent,
                        subString: "iPhone",
                        identity: "iPhone/iPod"
                    },
                    {
                        string: navigator.platform,
                        subString: "Linux",
                        identity: "Linux"
                    }
                ]

            };

            BrowserDetect.init();
            if (BrowserDetect.browser == "Firefox") {
                if (BrowserDetect.version > 9) {
                    bReturn = true;
                }
            } else if (BrowserDetect.browser == "Chrome") {
                if (BrowserDetect.version > 9) {
                    bReturn = true;
                }
            } else if (BrowserDetect.browser == "Explorer") {
                if (BrowserDetect.version > 9) {
                    bReturn = true;
                }
            }
//            alert(BrowserDetect.browser +"-"+BrowserDetect.version);

            return bReturn;
        };

        drawRectangle = function(context, text, x, y, height, width, color) {
            context.beginPath();
            context.fillStyle = color;
            context.moveTo(x, y);
            context.lineTo(x + width, y);
            context.lineTo(x + width, y + height);
            context.lineTo(x, y + height);
            context.lineTo(x, y);
            context.fill();
            context.stroke();
            context.closePath();
            context.fillStyle = "#FFF";
            context.fillText(text, x + 10, y + height / 2, width, height);

        };

        drawRect = function(context, node) {
            var radius = 4;
            context.moveTo(node.x + radius, node.y);

            context.lineTo(node.x + nodeWidth - radius, node.y);
            //context.moveTo(node.x+nodeWidth-3,node.y+3);
            context.arc(node.x + nodeWidth - radius, node.y + radius, radius, 3 * Math.PI / 2, 2 * Math.PI);

            context.lineTo(node.x + nodeWidth, node.y + nodeHeight - 3);
            //context.moveTo(node.x+nodeWidth-3,node.y+nodeHeight-3);
            context.arc(node.x + nodeWidth - radius, node.y + nodeHeight - radius, radius, 0, Math.PI / 2);

            context.lineTo(node.x + radius, node.y + nodeHeight);
            //context.moveTo(node.x+3,node.y+nodeHeight-3);
            context.arc(node.x + radius, node.y + nodeHeight - radius, radius, Math.PI / 2, Math.PI);

            context.lineTo(node.x, node.y + radius);
            //context.moveTo(node.x+3,node.y+3);
            context.arc(node.x + radius, node.y + radius, radius, Math.PI, 3 * Math.PI / 2);

        }

        drawNode = function(context, node) {
            context.beginPath();
            context.fillStyle = node.color;
            drawRect(context, node);
            context.fill();
            context.stroke();
            context.closePath();
            context.fillStyle = "#000";
            context.font = "12px verdana";

            var text = node.nodeName.toString();
            if (text.length > 10) {
                text = text.substring(0, 10) + "..";
            }

            context.fillText(text, node.x + 10, node.y + nodeHeight / 2, nodeWidth);
        }

        drawActiveNode = function(context, node) {
            context.beginPath();
            context.fillStyle = node.color;
            drawRect(context, node);
            context.fill();
            context.stroke();
            context.closePath();
            context.fillStyle = "#000";
            context.font = "12px verdana";

            var text = node.nodeName.toString();
            if (text.length > 10) {
                text = text.substring(0, 10) + "..";
            }

            context.fillText(text, node.x + 10, node.y + nodeHeight / 2, nodeWidth);
        }

        drawTooltip = function(context, node) {
            if (node == null || node == undefined) {
                return;
            }

            var remFont = context.font;
            context.font = "13px verdana";
            var text1 = "ĐƠN VỊ : " + node.nodeObj.receiveGroup;
            var text2 = "NGƯỜI NHẬN : " + ((node.nodeObj.receiveUser == null || node.nodeObj.receiveUser.toString() == "") ? "..." : node.nodeObj.receiveUser);
            if (node.previousId == undefined && node.previousId == null) {
                text2 = "NGƯỜI GỬI :" + ((node.nodeObj.sendUser == null || node.nodeObj.sendUser.toString() == "") ? "..." : node.nodeObj.sendUser);
            }

            var tm1 = context.measureText(text1);
            var tm2 = context.measureText(text2);

            var textWidth = Math.max(tm1.width, tm2.width) + 10;
            context.beginPath();
            context.fillStyle = "rgba(255,100,100,0.3)";

            context.moveTo(node.x + nodeWidth / 2, node.y + nodeHeight);
            context.lineTo(node.x + nodeWidth / 2 - 2, node.y + nodeHeight + 6);
            context.lineTo(node.x, node.y + nodeHeight + 6);
            context.lineTo(node.x, node.y + nodeHeight + 6 + 37);
            context.lineTo(node.x + textWidth, node.y + nodeHeight + 6 + 37);
            context.lineTo(node.x + textWidth, node.y + nodeHeight + 6);
            context.lineTo(node.x + nodeWidth / 2 + 2, node.y + nodeHeight + 6);
            context.lineTo(node.x + nodeWidth / 2, node.y + nodeHeight);
            context.fill();

            var remember = context.strokeStyle;
            context.strokeStyle = "rgba(255,100,100,0.3)";
            context.stroke();
            context.strokeStyle = remember;
            context.closePath();

            context.fillStyle = "#000";

            context.fillText(text1, node.x + 5, node.y + nodeHeight + 20);
            context.fillText(text2, node.x + 5, node.y + nodeHeight + 35);
            context.font = remFont;
        }

        drawAnchor = function(context, fromX, fromY, toX, toY) {
            var arg = Math.atan((fromY - toY) / (toX - fromX));

            var arg1 = arg + Math.PI / 6;
            var arg2 = arg - Math.PI / 6;
            var anchorLenght = 10;

            var arrowX1;
            var arrowY1;
            var arrowX2;
            var arrowY2;
            if (toX >= fromX) {
                arrowX1 = toX - Math.cos(arg1) * anchorLenght;
                arrowY1 = toY + Math.sin(arg1) * anchorLenght;
                arrowX2 = toX - Math.cos(arg2) * anchorLenght;
                arrowY2 = toY + Math.sin(arg2) * anchorLenght;
            } else {
                arrowX1 = toX + Math.cos(arg1) * anchorLenght;
                arrowY1 = toY - Math.sin(arg1) * anchorLenght;
                arrowX2 = toX + Math.cos(arg2) * anchorLenght;
                arrowY2 = toY - Math.sin(arg2) * anchorLenght;
            }

            context.beginPath();
            context.moveTo(fromX, fromY);
            context.lineTo(toX, toY);
            context.lineTo(arrowX1, arrowY1);
            context.lineTo(toX, toY);
            context.lineTo(arrowX2, arrowY2);
            context.stroke();
            context.closePath();
        }

        drawNodeToNode = function(context, node1, node2) {
            var x1 = node1.x + nodeWidth;
            var y1 = node1.y + nodeHeight / 2;
            var x2 = node2.x;
            var y2 = node2.y + nodeHeight / 2;

            drawAnchor(context, x1, y1, x2, y2);
        }

        adjustNodePosition = function() {
            var x = 10;
            var y = 10;
        }

        this.resize = function(width, height) {
            fixedWidth = width;
            fixedHeight = height;
            this.drawGraph();
        }

        this.drawGraph = function() {
            try {
                var canvas = document.getElementById(id);
                canvas.width = fixedWidth;
                canvas.height = fixedHeight;

                //canvas.setAttribute("width", 800);
                //context.globalAlpha = 0.8;
                var context = canvas.getContext("2d");
                context.font = "bold 12pt Times New Roman";

                if (nodeLength > 0) {
                    for (var i = 0; i < nodeLength; i++) {
                        if (i == activeNode) {
                            drawActiveNode(context, nodeList[i]);
                        } else {
                            drawNode(context, nodeList[i]);
                        }

                        if (nodeList[i].previousId != undefined && nodeList[i].previousId != null && nodeList[i].previousId >= 0) {
                            drawNodeToNode(context, nodeList[nodeList[i].previousId], nodeList[i]);
                        }
                    }
                    drawTooltip(context, nodeList[activeNode]);
                }

            } catch (e) {
                alert(e);
            }
        }

        isInNode = function(x, y, node) {

            if (x >= node.x && x <= node.x + nodeWidth && y >= node.y && y <= node.y + nodeHeight) {
                return true;
            } else {
                return false;
            }
        }

        this.whereIsActiveNode = function(x, y) {
            var i = 0;
            if (mouseFlags == 1) {
                if (activeNode >= 0) {
                    nodeList[activeNode].x = x;
                    nodeList[activeNode].y = y;
                    this.drawGraph();
                }
            } else {
                for (i = 0; i < nodeLength; i++) {
                    if (isInNode(x, y, nodeList[i])) {
                        if (activeNode != i) {
                            activeNode = i;
                            this.drawGraph();
                            return;
                        } else {
                            return;
                        }
                    }
                }
                if (activeNode != -1) {
                    activeNode = -1;
                    this.drawGraph();
                }
            }


        }

        this.clickInNode = function() {
            if (activeNode >= 0) {
                mouseFlags = 1;
            }
        }

        this.mouseUp = function() {
            mouseFlags = 0;
        }
    }

    function findPos(obj) {
        var curleft = 0, curtop = 0;
        if (obj.offsetParent) {
            do {
                curleft += obj.offsetLeft;
                curtop += obj.offsetTop;
            } while (obj = obj.offsetParent);
            return {x: curleft, y: curtop};
        }
        return undefined;
    }

    function drawProcessFromGridData(data, canvasId) {
        /*
         * Khoi tao cac gia tri
         */
        var graph = new drawFlow(canvasId);
        if (!graph.checkIEVersion())
            return;

        var blueColor = "rgb(146,201,231)";
        var grayColor = "rgb(192,192,192)";
        var redColor = "rgb(248,173,173)";
        var greenColor = "rgb(173,248,198)";

        dojo.connect(dojo.byId(canvasId), "onmousedown", function(e) {
            graph.clickInNode();
        });

        dojo.connect(dojo.byId(canvasId), "onmouseup", function(e) {
            graph.mouseUp();
        });

        dojo.connect(dojo.byId(canvasId), "onmousemove", function(e) {
            var dataFlow = document.getElementById(canvasId);
            var pos = findPos(dataFlow);
            canvas_x = e.pageX - pos.x;
            canvas_y = e.pageY - pos.y;

            graph.whereIsActiveNode(canvas_x, canvas_y);
        });


        /*
         * Xu ly du lieu ve luong
         */

        var maxWidth = 1000, maxHeight = 0;
        var items = data.items;
        var i = 0;
        var j = 0;

        if (items.length > 0) {
            var a = new Array(items.length * items.length * 4);
            var b = new Array(items.length * 2);
            var c = new Array(items.length * 2);
            for (i = 0; i < b.length; i++) {
                b[i] = 0;
                c[i] = 0;
            }

            for (i = 0; i < a.length; i++) {
                a[i] = 0;
            }

            var obj = new Object();
            obj.id = items[0].sendGroupId;
            obj.subId = null;
            obj.name = items[0].sendGroup;
            obj.nodeObj = items[0];
            obj.color = redColor;
            if (items[0].status.toString() == "0" || items[0].status.toString() == "1" || items[0].status.toString() == "18")
                obj.color = grayColor;
            if (items[0].status.toString() == "7" || items[0].status.toString() == "8" || items[0].status.toString() == "9" || items[0].status.toString() == "16" || items[0].status.toString() == "21")
                obj.color = greenColor;
            if (items[0].status.toString() == "6" || items[0].status.toString() == "15" || items[0].status.toString() == "20")
                obj.color = blueColor;

            c[0] = obj;

            b[0] = 1;
            graph.addNode(0,
                    c[0].name.toString(),
                    c[0].nodeObj,
                    20,
                    20,
                    c[0].color,
                    null
                    );

            var idx = 1;
            var length = items.length;
            var doubleLength = 2 * items.length;
            for (i = 0; i < length; i++) {
                j = 0;
                var ok = false;
                var tpm_j = 0;
                var aItem = items[i];
                for (j = 0; j < doubleLength; j++) {
                    //                    alert(i+"-"+j);
                    if (c[j] != 0) {
                        if (aItem.sendGroupId.toString() == c[j].id.toString()) {
                            ok = true;
                            tpm_j = j;
                            var obj = new Object();
                            obj.id = aItem.receiveGroupId;
                            obj.name = aItem.receiveGroup;
                            if (aItem.sendGroupId.toString() == aItem.receiveGroupId.toString()) {
                                obj.subId = aItem.receiveUserId;
                            }
                            else {
                                obj.subId = null;
                            }
                            obj.nodeObj = aItem;
                            obj.color = redColor;                            
                            if (items[0].status.toString() == "0" || items[0].status.toString() == "1" || items[0].status.toString() == "18")
                                obj.color = grayColor;
                            if (items[0].status.toString() == "7" || items[0].status.toString() == "8" || items[0].status.toString() == "9" || items[0].status.toString() == "16" || items[0].status.toString() == "21")                                
                            obj.color = greenColor;
                            if (items[0].status.toString() == "6" || items[0].status.toString() == "15" || items[0].status.toString() == "20")
                                obj.color = blueColor;
                            if ((c[j].subId == null) || (c[j].subId != null && c[j].subId.toString() == aItem.sendUserId.toString())) {
                                c[j].subId = aItem.sendUserId;
                                break;
                            }

                        }
                    }
                }
                if (ok) {
                    c[idx] = obj;
                    a[tpm_j * items.length * 2 + idx] = 1;
                    idx++;
                }
            }

            var vt = 1;
            while (true) {
                var ok = true;
                for (i = 0; i < b.length; i++) {
                    if (b[i] == vt) {
                        for (j = 0; j < b.length; j++) {
                            if (b[j] == 0 && a[i * items.length * 2 + j] == 1) {
                                b[j] = vt + 1;
                                ok = false;

                                var x = 0;
                                for (var k = 0; k < b.length; k++) {
                                    if (b[k] == vt + 1 && k != j) {
                                        x++;
                                    }
                                }
                                if (x * (50 + 50) + 70 > maxHeight)
                                    maxHeight = x * (50 + 50) + 70;
                                if ((b[j] - 1) * (100 + 50) + 120 > maxWidth)
                                    maxWidth = (b[j] - 1) * (100 + 50) + 120;

                                graph.addNode(j,
                                        c[j].name.toString(),
                                        c[j].nodeObj,
                                        (b[j] - 1) * (100 + 50) + 20,
                                        (x) * (50 + 50) + 20,
                                        c[j].color,
                                        i
                                        );

                            }
                        }
                    }
                }
                if (ok)
                    break;
                vt = vt + 1;
            }
        }
        graph.resize(maxWidth + 200, maxHeight + 50);//mo rong de hien thi tooltip
    }
</script>

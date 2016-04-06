<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="struts-dojo-tags" %>
<%@taglib prefix="sx" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    request.setAttribute("contextPath", request.getContextPath());
%>

<script type="text/javascript">
    function drawFlow(id) {
        color_processing = "rgb(113,153,236)";
        color_finish = "rgb(230,193,119)";
        color_border = "rgb(200,163,89)";
        color_anchor = "rgb(200,163,89)";
        color_active = "rgb(255,100,100)";
        fixedWidth = 0;
        fixedHeight = 0;
        firstSelectNode = null;
        endSelectNode = null;
        select = null;

        nodeList = new Array();
        nodeToNodeList = new Array();
        nodeLength = 0;
        nodeToNodeLength = 0;
        id = id;
        nodeHeight = 40;
        nodeWidth = 100;
        activeNode = -1;
        xpoint = 0;
        ypoint = 0;
        mouseFlags = 0;
        maxId = 0;

        this.getNodeList = function() {
            return nodeList;
        }


        this.setSelect = function(selectStatus) {
            select = selectStatus;
        }

        getNextIdOfNode = function() {
            maxId = maxId + 1;
            return maxId;
        }

        this.addRelation = function(previousId, nextId, action) {
            var previousIndex = -1;
            var nextIndex = -1;
            for (var i = 0; i < nodeList.length; i++) {
                if (nodeList[i].nodeId == previousId) {
                    previousIndex = i;
                }
                if (nodeList[i].nodeId == nextId) {
                    nextIndex = i;
                }
            }
            var add = true;
            for (var i = 0; i < nodeToNodeList.length; i++) {
                if (nodeToNodeList[i].previousNodeId == previousIndex && nodeToNodeList[i].nextNodeId == nextIndex) {
                    add = false;
                    break;
                }
            }
            if (add) {
                var nodeToNode = new Object();
                nodeToNode.previousNodeId = previousIndex;
                nodeToNode.nextNodeId = nextIndex;
                nodeToNode.previousId = previousId;
                nodeToNode.nextId = nextId;
                nodeToNode.action = action;
                nodeToNodeList[nodeToNodeLength] = nodeToNode;
                nodeToNodeLength++;
            }
            this.drawGraph();
        }

        this.addNode = function(nodeId, nodeName, x, y, status, color) {
            var node = new Object();
            node.id = getNextIdOfNode();
            node.nodeName = nodeName;
            node.status = status;
            node.nodeId = nodeId;

            node.x = x;
            node.y = y;
            if (color != null) {
                node.color = color;
            } else {
                node.color = color_processing;
            }
            nodeList[nodeLength] = node;
            nodeLength = nodeLength + 1;
            this.drawGraph();
        }

        this.addNewNode = function(nodeName) {
            var node = new Object();
            node.id = getNextIdOfNode();
            node.nodeId = null;
            if (nodeName != null && nodeName != "")
                node.nodeName = nodeName;
            else
                node.nodeName = "Node " + node.id;
            node.status = status;
            node.color = color_processing;
            //node.previousId = nodeLength-1;
            if (nodeLength == 0) {
                node.x = 10;
                node.y = 10;
                nodeList[nodeLength] = node;

            } else {
                node.x = nodeList[nodeLength - 1].x + nodeWidth + 50;
                node.y = nodeList[nodeLength - 1].y;

                if (node.x > fixedWidth) {
                    if (node.y < fixedHeight) {
                        node.x = fixedWidth - nodeWidth;
                        node.y = node.y + 80;
                    } else {
                        node.x = fixedWidth - nodeWidth + Math.random() * 20;

                    }
                }
                if (node.y > fixedHeight) {
                    node.y = fixedHeight - nodeHeight + Math.random() * 20;
                }

                nodeList[nodeLength] = node;

                var nodeToNode = new Object();

                nodeToNode.previousId = nodeList[nodeLength - 1].nodeId;
                nodeToNode.nextId = nodeList[nodeLength].nodeId;

                nodeToNode.previousNodeId = nodeLength - 1;
                nodeToNode.nextNodeId = nodeLength;
                nodeToNodeList[nodeToNodeLength] = nodeToNode;
                nodeToNodeLength += 1;

            }

            nodeLength += 1;

            this.drawGraph();
        }

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
        }

        this.addNextNode = function(nodeId, nodeName) {
            var node = new Object();
            node.id = getNextIdOfNode();
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
        }

        this.clearNode = function() {
            nodeList = new Array();
            nodeLength = 0;
            this.drawGraph();
        }


        this.deleteNode = function(id) {
            var index = -1;
            for (var i = 0; i < nodeLength; i++) {
                if (nodeList[i].id == id) {
                    nodeList.splice(i, 1);
                    nodeLength--;
                    index = i;
                    break;
                }
            }
            for (var i = nodeToNodeLength - 1; i >= 0; i--) {
                if (nodeToNodeList[i].nextNodeId == index || nodeToNodeList[i].previousNodeId == index) {
                    nodeToNodeList.splice(i, 1);
                    nodeToNodeLength--;
                }
            }
            for (var i = nodeToNodeLength - 1; i >= 0; i--) {
                if (nodeToNodeList[i].nextNodeId > index) {
                    nodeToNodeList[i].nextNodeId = nodeToNodeList[i].nextNodeId - 1;
                }
                ;
                if (nodeToNodeList[i].previousNodeId > index) {
                    nodeToNodeList[i].previousNodeId = nodeToNodeList[i].previousNodeId - 1;
                }
                ;
            }
            this.drawGraph();
        }

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
        }

        this.nodeToJSON = function() {
            var outContent = {};
            var flowId = document.getElementById("viewForm.flowId").value;
            for (var i = 0; i < nodeList.length; i++) {
                if (!outContent[ "nodeList[" + i + "].id"]) {
                    outContent[ "nodeList[" + i + "].id"] = [];
                }
                if (!outContent[ "nodeList[" + i + "].flowId"]) {
                    outContent[ "nodeList[" + i + "].flowId"] = [];
                }
                if (!outContent[ "nodeList[" + i + "].nodeId"]) {
                    outContent[ "nodeList[" + i + "].nodeId"] = [];
                }
                if (!outContent[ "nodeList[" + i + "].nodeName"]) {
                    outContent[ "nodeList[" + i + "].nodeName"] = [];
                }
                if (!outContent[ "nodeList[" + i + "].x"]) {
                    outContent[ "nodeList[" + i + "].x"] = [];
                }

                if (!outContent[ "nodeList[" + i + "].y"]) {
                    outContent[ "nodeList[" + i + "].y"] = [];
                }

                if (!outContent[ "nodeList[" + i + "].status"]) {
                    outContent[ "nodeList[" + i + "].status"] = [];
                }

                outContent["nodeList[" + i + "].id"].push(nodeList[i].id);
                if (nodeList[i].nodeId) {
                    outContent["nodeList[" + i + "].nodeId"].push(nodeList[i].nodeId);
                }

                if (flowId) {
                    outContent["nodeList[" + i + "].flowId"].push(flowId);
                }
                outContent["nodeList[" + i + "].nodeName"].push(nodeList[i].nodeName);
                if (nodeList[i].x == null) {
                    outContent["nodeList[" + i + "].x"].push(0);
                } else {
                    outContent["nodeList[" + i + "].x"].push(nodeList[i].x);

                }
                if (nodeList[i].y == null) {
                    outContent["nodeList[" + i + "].y"].push(0);
                } else {
                    outContent["nodeList[" + i + "].y"].push(nodeList[i].y);
                }

                if (nodeList[i].status == null) {
                    outContent["nodeList[" + i + "].status"].push(0);
                } else {
                    outContent["nodeList[" + i + "].status"].push(nodeList[i].status);
                }
            }

            for (var i = 0; i < nodeToNodeList.length; i++) {
                if (!outContent[ "nodeToNodeList[" + i + "].previousId"]) {
                    outContent[ "nodeToNodeList[" + i + "].previousId"] = [];
                }
                if (!outContent[ "nodeToNodeList[" + i + "].nextId"]) {
                    outContent[ "nodeToNodeList[" + i + "].nextId"] = [];
                }
                if (!outContent[ "nodeToNodeList[" + i + "].action"]) {
                    outContent[ "nodeToNodeList[" + i + "].action"] = [];
                }

                outContent["nodeToNodeList[" + i + "].previousId"].push(nodeToNodeList[i].previousNodeId);
                outContent["nodeToNodeList[" + i + "].nextId"].push(nodeToNodeList[i].nextNodeId);
                if(nodeToNodeList[i].action == null || nodeToNodeList[i].action === undefined){
                    outContent["nodeToNodeList[" + i + "].action"].push("");
                } else {
                    outContent["nodeToNodeList[" + i + "].action"].push(nodeToNodeList[i].action);
                }
            }

            //var myJSON = JSON.stringify({nodeList : nodeList});
            return outContent;
        }

        this.nodeToNodeToJson = function() {
            var myJSON = JSON.stringify({nodeToNodeList: nodeToNodeList});
            return myJSON;

        }

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

        }

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
            var text1 = "Node : " + node.nodeName;

            var tm1 = context.measureText(text1);

            var textWidth = tm1.width + 10;
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

        drawNodeToNode = function(context, node1, node2, action) {
            var x1 = node1.x + nodeWidth;
            var y1 = node1.y + nodeHeight / 2;
            var x2 = node2.x;
            var y2 = node2.y + nodeHeight / 2;

            if (node1.x + nodeWidth < node2.x) {
                x1 = node1.x + nodeWidth;
                x2 = node2.x;
            } else if (node1.x >= node2.x + nodeWidth) {
                x1 = node1.x;
                x2 = node2.x + nodeWidth;
            } else if (node1.y + nodeHeight < node2.y) {
                x1 = node1.x + nodeWidth / 2;
                y1 = node1.y + nodeHeight;
                x2 = node2.x + nodeWidth / 2;
                y2 = node2.y;
            } else if (node1.y > node2.y + nodeHeight) {
                x1 = node1.x + nodeWidth / 2;
                y1 = node1.y;
                x2 = node2.x + nodeWidth / 2;
                y2 = node2.y + nodeHeight;
            }

            drawAnchor(context, x1, y1, x2, y2);
            if (action != null && action != "") {
                if (y1 > y2) {
                    context.fillText(action, (x1 + x2 - nodeWidth) / 2, (y1 + y2) / 2 + 10, nodeWidth);
                } else {
                    context.fillText(action, (x1 + x2 - nodeWidth) / 2, (y1 + y2) / 2 - 10, nodeWidth);

                }
            }

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

                        //                        if(nodeList[i].previousId != undefined && nodeList[i].previousId != null && nodeList[i].previousId >=0 ){                            
                        //                            drawNodeToNode(context,nodeList[nodeList[i].previousId],nodeList[i]);
                        //                        }
                    }


                    for (var i = 0; i < nodeToNodeLength; i++) {
                        drawNodeToNode(context, nodeList[nodeToNodeList[i].previousNodeId], nodeList[nodeToNodeList[i].nextNodeId], nodeToNodeList[i].action);
                    }
                    drawTooltip(context, nodeList[activeNode]);
                }

            } catch (e) {
                //alert(e);
            }
        }

        isInNode = function(x, y, node) {
            if (node == null)
                return false;
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
                for (i = 0; i < nodeList.length; i++) {
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
            if (select == 1) {
                firstSelectNode = activeNode;
                select = 2;
            } else if (select == 2) {
                var addNewNodeToNode = true;
                if (firstSelectNode == activeNode) {
                    addNewNodeToNode = false;
                } else {
                    for (var i = 0; i < nodeToNodeLength; i++) {
                        if (nodeToNodeList[i].previousNodeId == firstSelectNode && nodeToNodeList[i].nextNodeId == activeNode) {
                            addNewNodeToNode = false;
                            break;
                        }
                    }
                }
                if (addNewNodeToNode) {
                    var nodeToNode = new Object();

                    nodeToNode.previousId = nodeList[firstSelectNode].nodeId;
                    nodeToNode.nextId = nodeList[activeNode].nodeId;

                    nodeToNode.previousNodeId = firstSelectNode;
                    nodeToNode.nextNodeId = activeNode;
                    nodeToNodeList[nodeToNodeLength] = nodeToNode;
                    nodeToNodeLength += 1;

                    //nodeList[activeNode].previousId= firstSelectNode;
                    graph.drawGraph();
                }
                select = 1;
            } else {
                if (activeNode >= 0) {
                    mouseFlags = 1;
                }
            }

        }

        this.updateFromForm = function() {
            var nodeName = document.getElementById("viewNodeForm.nodeName").value;
            var nodeId = document.getElementById("viewNodeForm.nodeId").value;
            var status = document.getElementById("viewNodeForm.status").value;
            var id = document.getElementById("viewNodeForm.id").value = nodeList[activeNode].id;
            //            nodeList[id].nodeName = nodeName;

            var grid = dijit.byId("nodeToNodeGrid");
            //
            // cap nhat node name
            //
            for (var i = 0; i <= nodeLength; i++) {
                if (nodeList[i].id == id) {
                    nodeList[i].nodeId = nodeId;
                    nodeList[i].nodeName = nodeName;
                    nodeList[i].status = status;
                    break;
                }
            }
            //
            // xoa  cac quan he bi xoa
            //
            for (var i = nodeToNodeLength - 1; i >= 0; i--) {
                if (nodeList[nodeToNodeList[i].previousNodeId].id == id) {
                    var have = false;
                    for (var j = 0; j < grid._by_idx.length; j++) {
                        var item = grid.getItem(j);
                        if (item.nextNodeId == nodeToNodeList[i].nextNodeId) {
                            have = true;
                            nodeToNodeList[i].action = item.action;
                            break;
                        }
                    }
                    if (!have) {
                        nodeToNodeList.splice(i, 1);
                        nodeToNodeLength--;
                    } else {
                    }
                }
            }
            this.drawGraph();
        }

        this.showNodeContent = function() {
            document.getElementById("viewNodeForm.nodeName").value = nodeList[activeNode].nodeName;
            document.getElementById("viewNodeForm.nodeId").value = nodeList[activeNode].nodeId;
            document.getElementById("viewNodeForm.id").value = nodeList[activeNode].id;
            document.getElementById("viewNodeForm.x").value = nodeList[activeNode].x;
            document.getElementById("viewNodeForm.y").value = nodeList[activeNode].y;
            document.getElementById("viewNodeForm.status").value = nodeList[activeNode].status;
            document.getElementById("viewNodeForm.flowId").value = document.getElementById("viewForm.flowId").value;
            page.showNodeContent();
            var grid = dijit.byId("nodeToNodeGrid");
            //
            // clear item cu
            //
            for (var i = grid._by_idx.length - 1; i >= 0; i--) {
                var item = grid.getItem(i);
                grid.store.deleteItem(item);
            }
            //
            // dua cac relation vao grid
            //
            for (var i = 0; i < nodeToNodeLength; i++) {
                if (nodeToNodeList[i].previousNodeId == activeNode) {
                    var item = new Object();
                    item.nextNodeId = nodeToNodeList[i].nextNodeId;
                    item.nextId = nodeToNodeList[i].nextId;
                    item.action = nodeToNodeList[i].action;
                    item.nextNodeName = nodeList[nodeToNodeList[i].nextNodeId].nodeName;
                    grid.store.newItem(item);
                }
            }
            grid.store.save();
            grid.renderNoReload();
            //
            // khoi tao tab NodeDeptUser
            //
            if (nodeList[activeNode].nodeId) {
                dijit.byId("processGridId").vtReload("flow!loadDeptUser.do?nodeId=" + nodeList[activeNode].nodeId, null, null, hideGridHeader);
            } else {
                //
                // delete all dept_user of old data
                //
                dijit.byId("processGridId").vtReload("flow!loadDeptUser.do", null, null, hideGridHeader);

                //                var processGrid = dijit.byId("processGridId");
                //                for(var i=processGrid._by_idx.length-1;i>=0;i--){
                //                    var item = processGrid.getItem(i);
                //                    processGrid.store.deleteItem(item);
                //                }            
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
    var graph = null;
    var select = 0;

    function drawProcess(canvasId) {
        /*
         * Khoi tao cac gia tri
         */
        graph = new drawFlow(canvasId);
        if (!graph.checkIEVersion())
            return;

        var blueColor = "rgb(146,201,231)";
        var grayColor = "rgb(192,192,192)";
        var redColor = "rgb(248,173,173)";
        var greenColor = "rgb(173,248,198)";
        var canvas = document.getElementById(canvasId);
        var maxWidth = canvas.clientWidth;
        var maxHeight = canvas.clientHeight;
    
        document.getElementById(canvasId).onmousedown = function(e) {
            graph.clickInNode();
            return false;
        }
        document.getElementById(canvasId).onmouseup = function(e) {
            graph.mouseUp();
        }
        document.getElementById(canvasId).ondblclick = function(e) {
            graph.showNodeContent();
            return false;
            //window.showModalDialog("node.html","","center:yes;dialogWidth:1000;dialogHeight:500;");                    
            //graph.clickInNode();
        }
        document.getElementById(canvasId).onmousemove = function(e) {
            var dataFlow = document.getElementById(canvasId);
            var pos = findPos(dataFlow);
            canvas_x = e.pageX - pos.x;
            canvas_y = e.pageY - pos.y;

            graph.whereIsActiveNode(canvas_x, canvas_y);
        };
        //            graph.addNextNode(1, "Văn thư");
        //            graph.addNextNode(2, "Ban 1");
        graph.resize(maxWidth, maxHeight);//mo rong de hien thi tooltip
        page.loadNodeOfFlow();
    }

</script>
<div class="buttonDiv">
    <sd:Button id="" key="" onclick="page.back();">
        <img src="share/images/icons/back.png" height="14" width="18" alt="Đóng">
        <span style="font-size:12px">Quay lại</span>
    </sd:Button>
    <sx:ButtonSave onclick="page.saveFlowNode();"/>
    <sd:Button key="" onclick="page.addNewNode();">
        <img src="share/images/icons/6.png" height="14" width="14" alt="Ghi lại">
        <span style="font-size:12px">Thêm node</span>
    </sd:Button>
    <sd:Button key="" onclick="page.adjustFlow();">
        <img src="share/images/icons/edit.png" height="14" width="14" alt="Ghi lại">
        <span style="font-size:12px">Điều chỉnh luồng</span>
    </sd:Button>
</div>
<sd:TitlePane key="Thông tin luồng văn bản" id="flowViewPanel">
    <table class="viewTable">
        <tr>
            <td width="30%">Tên luồng</td>
            <td width="70%">
                <input type="hidden" id="viewForm.flowId" name="viewForm.flowId"/>
                <span id="viewForm.flowName"></span>
            </td>
        </tr>
        <tr>
            <td>Thủ tục hành chính</td>
            <td>
                <span id="viewForm.flowTypeName"></span>
            </td>
        </tr>
        <tr>
            <td>Tên đơn vị</td>
            <td><span id="viewForm.deptName"></span></td>
        </tr>        
        <tr>
            <td>Ghi chú</td>
            <td><span id="viewForm.description"></span></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
            </td>
        </tr>
    </table>

</sd:TitlePane>

<sd:TitlePane key="Chi tiết luồng văn bản" id="flowPanel">
    <canvas id="flowNode" style="width:100%;height: 400px;">

    </canvas>
</sd:TitlePane>

<script type="text/javascript">

    page.closeViewDlg = function() {
        var dlg = dijit.byId("dlgViewFlow");
        dlg.hide();
    }

    //    page.saveViewFlow = function(){
    //      var obj = graph.nodeToJSON();
    //    }

    page.addNewNode = function() {
        graph.addNewNode("");
    }

    page.adjustFlow = function() {
        if (select == 0 || select == null) {
            graph.setSelect(1);
        }
        else {
            graph.setSelect(0);
        }
    }

    page.showNodeContent = function() {
        var dlg = dijit.byId("dlgViewNode");
        dlg.show();
    }

    page.afterSaveNode = function(data) {
        msg.alert("Cập nhật hoàn tât");
        //page.closeViewDlg();
    }

    page.afterLoadRelationOfFlow = function(data) {
        var returnData = dojo.fromJson(data);
        var result = returnData.items;
        for (var i = 0; i < result.length; i++) {
            var item = result[i];
            graph.addRelation(item.previousId, item.nextId, item.action);
        }
    }

    page.afterLoadNode = function(data) {
        var returnData = dojo.fromJson(data);
        var result = returnData.items;
        for (var i = 0; i < result.length; i++) {
            var item = result[i];
            graph.addNode(item.nodeId, item.nodeName, item.xpoint, item.ypoint, item.status);
        }
        page.loadRelationOfFlow();
    }

    page.loadNodeOfFlow = function() {
        var flowId = document.getElementById("viewForm.flowId").value;
        var url = "flow!getNodeOfFlow.do?flowId=" + flowId;
        sd.connector.post(url, null, null, null, page.afterLoadNode);
    }

    page.loadRelationOfFlow = function() {
        var flowId = document.getElementById("viewForm.flowId").value;
        sd.connector.post("flow!getRelationOfFlow.do?flowId=" + flowId, null, null, null, page.afterLoadRelationOfFlow);
    }

    page.saveFlowNode = function() {
        var obj = graph.nodeToJSON();
        //obj = graph.getNodeList();
        //alert(obj);
        //alert(obj.length);
        var flowId = document.getElementById("viewForm.flowId").value;
        sd.connector.post("flow!saveNode.do?flowId=" + flowId + "&" + token.getTokenParamString(), null, null, obj, page.afterSaveNode);
    }

    page.back = function() {
        document.getElementById("flowListDivId").style.display = "";
        document.getElementById("flowViewDivId").style.display = "none";
    }

    //drawProcess("flowNode");

</script>
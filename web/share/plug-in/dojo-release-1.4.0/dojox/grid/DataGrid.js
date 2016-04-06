/*
	Copyright (c) 2004-2009, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

/*
	This is a compiled version of Dojo, built for deployment and not for
	development. To get an editable version, please visit:

		http://dojotoolkit.org

	for documentation and information on getting the source.
*/

if(!dojo._hasResource["dijit._base.manager"]){
    dojo._hasResource["dijit._base.manager"]=true;dojo.provide("dijit._base.manager");dojo.declare("dijit.WidgetSet",null,{
        constructor:function(){
            this._hash={};this.length=0;
        },
        add:function(_1){
            if(this._hash[_1.id]){
                throw new Error("Tried to register widget with id=="+_1.id+" but that id is already registered");
            }this._hash[_1.id]=_1;this.length++;
        },
        remove:function(id){
            if(this._hash[id]){
                delete this._hash[id];this.length--;
            }
            },
        forEach:function(_2,_3){
            _3=_3||dojo.global;var i=0,id;for(id in this._hash){
                _2.call(_3,this._hash[id],i++,this._hash);
            }return this;
        },
        filter:function(_4,_5){
            _5=_5||dojo.global;var _6=new dijit.WidgetSet(),i=0,id;for(id in this._hash){
                var w=this._hash[id];if(_4.call(_5,w,i++,this._hash)){
                    _6.add(w);
                }
                }return _6;
        },
        byId:function(id){
            return this._hash[id];
        },
        byClass:function(_7){
            var _8=new dijit.WidgetSet(),id,_9;for(id in this._hash){
                _9=this._hash[id];if(_9.declaredClass==_7){
                    _8.add(_9);
                }
                }return _8;
        },
        toArray:function(){
            var ar=[];for(var id in this._hash){
                ar.push(this._hash[id]);
            }return ar;
        },
        map:function(_a,_b){
            return dojo.map(this.toArray(),_a,_b);
        },
        every:function(_c,_d){
            _d=_d||dojo.global;var x=0,i;for(i in this._hash){
                if(!_c.call(_d,this._hash[i],x++,this._hash)){
                    return false;
                }
                }return true;
        },
        some:function(_e,_f){
            _f=_f||dojo.global;var x=0,i;for(i in this._hash){
                if(_e.call(_f,this._hash[i],x++,this._hash)){
                    return true;
                }
                }return false;
        }
        });dijit.registry=new dijit.WidgetSet();dijit._widgetTypeCtr={};dijit.getUniqueId=function(_10){
        var id;do{
            id=_10+"_"+(_10 in dijit._widgetTypeCtr?++dijit._widgetTypeCtr[_10]:dijit._widgetTypeCtr[_10]=0);
        }while(dijit.byId(id));return id;
    };dijit.findWidgets=function(_11){
        var _12=[];function _13(_14){
            for(var _15=_14.firstChild;_15;_15=_15.nextSibling){
                if(_15.nodeType==1){
                    var _16=_15.getAttribute("widgetId");if(_16){
                        var _17=dijit.byId(_16);_12.push(_17);
                    }else{
                        _13(_15);
                    }
                    }
                }
            };_13(_11);return _12;
    };dijit._destroyAll=function(){
        dijit._curFocus=null;dijit._prevFocus=null;dijit._activeStack=[];dojo.forEach(dijit.findWidgets(dojo.body()),function(_18){
            if(!_18._destroyed){
                if(_18.destroyRecursive){
                    _18.destroyRecursive();
                }else{
                    if(_18.destroy){
                        _18.destroy();
                    }
                    }
                }
            });
    };if(dojo.isIE){
        dojo.addOnWindowUnload(function(){
            dijit._destroyAll();
        });
    }dijit.byId=function(id){
        return typeof id=="string"?dijit.registry._hash[id]:id;
    };dijit.byNode=function(_19){
        return dijit.registry.byId(_19.getAttribute("widgetId"));
    };dijit.getEnclosingWidget=function(_1a){
        while(_1a){
            var id=_1a.getAttribute&&_1a.getAttribute("widgetId");if(id){
                return dijit.byId(id);
            }_1a=_1a.parentNode;
        }return null;
    };dijit._isElementShown=function(_1b){
        var _1c=dojo.style(_1b);return (_1c.visibility!="hidden")&&(_1c.visibility!="collapsed")&&(_1c.display!="none")&&(dojo.attr(_1b,"type")!="hidden");
    };dijit.isTabNavigable=function(_1d){
        if(dojo.attr(_1d,"disabled")){
            return false;
        }else{
            if(dojo.hasAttr(_1d,"tabIndex")){
                return dojo.attr(_1d,"tabIndex")>=0;
            }else{
                switch(_1d.nodeName.toLowerCase()){
                    case "a":return dojo.hasAttr(_1d,"href");case "area":case "button":case "input":case "object":case "select":case "textarea":return true;case "iframe":if(dojo.isMoz){
                        return _1d.contentDocument.designMode=="on";
                    }else{
                        if(dojo.isWebKit){
                            var doc=_1d.contentDocument,_1e=doc&&doc.body;return _1e&&_1e.contentEditable=="true";
                        }else{
                            doc=_1d.contentWindow.document;_1e=doc&&doc.body;return _1e&&_1e.firstChild&&_1e.firstChild.contentEditable=="true";
                        }
                        }default:return _1d.contentEditable=="true";
                }
                }
            }
        };dijit._getTabNavigable=function(_1f){
        var _20,_21,_22,_23,_24,_25;var _26=function(_27){
            dojo.query("> *",_27).forEach(function(_28){
                var _29=dijit._isElementShown(_28);if(_29&&dijit.isTabNavigable(_28)){
                    var _2a=dojo.attr(_28,"tabIndex");if(!dojo.hasAttr(_28,"tabIndex")||_2a==0){
                        if(!_20){
                            _20=_28;
                        }_21=_28;
                    }else{
                        if(_2a>0){
                            if(!_22||_2a<_23){
                                _23=_2a;_22=_28;
                            }if(!_24||_2a>=_25){
                                _25=_2a;_24=_28;
                            }
                            }
                        }
                    }if(_29&&_28.nodeName.toUpperCase()!="SELECT"){
                    _26(_28);
                }
                });
        };if(dijit._isElementShown(_1f)){
            _26(_1f);
        }return {
            first:_20,
            last:_21,
            lowest:_22,
            highest:_24
        };
    };dijit.getFirstInTabbingOrder=function(_2b){
        var _2c=dijit._getTabNavigable(dojo.byId(_2b));return _2c.lowest?_2c.lowest:_2c.first;
    };dijit.getLastInTabbingOrder=function(_2d){
        var _2e=dijit._getTabNavigable(dojo.byId(_2d));return _2e.last?_2e.last:_2e.highest;
    };dijit.defaultDuration=dojo.config["defaultDuration"]||200;
}if(!dojo._hasResource["dijit._base.focus"]){
    dojo._hasResource["dijit._base.focus"]=true;dojo.provide("dijit._base.focus");dojo.mixin(dijit,{
        _curFocus:null,
        _prevFocus:null,
        isCollapsed:function(){
            return dijit.getBookmark().isCollapsed;
        },
        getBookmark:function(){
            var bm,rg,tg,sel=dojo.doc.selection,cf=dijit._curFocus;if(dojo.global.getSelection){
                sel=dojo.global.getSelection();if(sel){
                    if(sel.isCollapsed){
                        tg=cf?cf.tagName:"";if(tg){
                            tg=tg.toLowerCase();if(tg=="textarea"||(tg=="input"&&(!cf.type||cf.type.toLowerCase()=="text"))){
                                sel={
                                    start:cf.selectionStart,
                                    end:cf.selectionEnd,
                                    node:cf,
                                    pRange:true
                                };return {
                                    isCollapsed:(sel.end<=sel.start),
                                    mark:sel
                                };
                            }
                            }bm={
                            isCollapsed:true
                        };
                    }else{
                        rg=sel.getRangeAt(0);bm={
                            isCollapsed:false,
                            mark:rg.cloneRange()
                            };
                    }
                    }
                }else{
                if(sel){
                    tg=cf?cf.tagName:"";tg=tg.toLowerCase();if(cf&&tg&&(tg=="button"||tg=="textarea"||tg=="input")){
                        if(sel.type&&sel.type.toLowerCase()=="none"){
                            return {
                                isCollapsed:true,
                                mark:null
                            };
                        }else{
                            rg=sel.createRange();return {
                                isCollapsed:rg.text&&rg.text.length?false:true,
                                mark:{
                                    range:rg,
                                    pRange:true
                                }
                                };
                        }
                        }bm={};try{
                        rg=sel.createRange();bm.isCollapsed=!(sel.type=="Text"?rg.htmlText.length:rg.length);
                    }catch(e){
                        bm.isCollapsed=true;return bm;
                    }if(sel.type.toUpperCase()=="CONTROL"){
                        if(rg.length){
                            bm.mark=[];var i=0,len=rg.length;while(i<len){
                                bm.mark.push(rg.item(i++));
                            }
                            }else{
                            bm.isCollapsed=true;bm.mark=null;
                        }
                        }else{
                        bm.mark=rg.getBookmark();
                    }
                    }else{
                    console.warn("No idea how to store the current selection for this browser!");
                }
                }return bm;
        },
        moveToBookmark:function(_2f){
            var _30=dojo.doc,_31=_2f.mark;if(_31){
                if(dojo.global.getSelection){
                    var sel=dojo.global.getSelection();if(sel&&sel.removeAllRanges){
                        if(_31.pRange){
                            var r=_31;var n=r.node;n.selectionStart=r.start;n.selectionEnd=r.end;
                        }else{
                            sel.removeAllRanges();sel.addRange(_31);
                        }
                        }else{
                        console.warn("No idea how to restore selection for this browser!");
                    }
                    }else{
                    if(_30.selection&&_31){
                        var rg;if(_31.pRange){
                            rg=_31.range;
                        }else{
                            if(dojo.isArray(_31)){
                                rg=_30.body.createControlRange();dojo.forEach(_31,function(n){
                                    rg.addElement(n);
                                });
                            }else{
                                rg=_30.body.createTextRange();rg.moveToBookmark(_31);
                            }
                            }rg.select();
                    }
                    }
                }
            },
        getFocus:function(_32,_33){
            var _34=!dijit._curFocus||(_32&&dojo.isDescendant(dijit._curFocus,_32.domNode))?dijit._prevFocus:dijit._curFocus;return {
                node:_34,
                bookmark:(_34==dijit._curFocus)&&dojo.withGlobal(_33||dojo.global,dijit.getBookmark),
                openedForWindow:_33
            };
        },
        focus:function(_35){
            if(!_35){
                return;
            }var _36="node" in _35?_35.node:_35,_37=_35.bookmark,_38=_35.openedForWindow,_39=_37?_37.isCollapsed:false;if(_36){
                var _3a=(_36.tagName.toLowerCase()=="iframe")?_36.contentWindow:_36;if(_3a&&_3a.focus){
                    try{
                        _3a.focus();
                    }catch(e){}
                    }dijit._onFocusNode(_36);
            }if(_37&&dojo.withGlobal(_38||dojo.global,dijit.isCollapsed)&&!_39){
                if(_38){
                    _38.focus();
                }try{
                    dojo.withGlobal(_38||dojo.global,dijit.moveToBookmark,null,[_37]);
                }catch(e2){}
                }
            },
        _activeStack:[],
        registerIframe:function(_3b){
            return dijit.registerWin(_3b.contentWindow,_3b);
        },
        unregisterIframe:function(_3c){
            dijit.unregisterWin(_3c);
        },
        registerWin:function(_3d,_3e){
            var _3f=function(evt){
                dijit._justMouseDowned=true;setTimeout(function(){
                    dijit._justMouseDowned=false;
                },0);dijit._onTouchNode(_3e||evt.target||evt.srcElement,"mouse");
            };var doc=dojo.isIE?_3d.document.documentElement:_3d.document;if(doc){
                if(dojo.isIE){
                    doc.attachEvent("onmousedown",_3f);var _40=function(evt){
                        if(evt.srcElement.tagName.toLowerCase()!="#document"&&dijit.isTabNavigable(evt.srcElement)){
                            dijit._onFocusNode(_3e||evt.srcElement);
                        }else{
                            dijit._onTouchNode(_3e||evt.srcElement);
                        }
                        };doc.attachEvent("onactivate",_40);var _41=function(evt){
                        dijit._onBlurNode(_3e||evt.srcElement);
                    };doc.attachEvent("ondeactivate",_41);return function(){
                        doc.detachEvent("onmousedown",_3f);doc.detachEvent("onactivate",_40);doc.detachEvent("ondeactivate",_41);doc=null;
                    };
                }else{
                    doc.addEventListener("mousedown",_3f,true);var _42=function(evt){
                        dijit._onFocusNode(_3e||evt.target);
                    };doc.addEventListener("focus",_42,true);var _43=function(evt){
                        dijit._onBlurNode(_3e||evt.target);
                    };doc.addEventListener("blur",_43,true);return function(){
                        doc.removeEventListener("mousedown",_3f,true);doc.removeEventListener("focus",_42,true);doc.removeEventListener("blur",_43,true);doc=null;
                    };
                }
                }
            },
        unregisterWin:function(_44){
            _44&&_44();
        },
        _onBlurNode:function(_45){
            dijit._prevFocus=dijit._curFocus;dijit._curFocus=null;if(dijit._justMouseDowned){
                return;
            }if(dijit._clearActiveWidgetsTimer){
                clearTimeout(dijit._clearActiveWidgetsTimer);
            }dijit._clearActiveWidgetsTimer=setTimeout(function(){
                delete dijit._clearActiveWidgetsTimer;dijit._setStack([]);dijit._prevFocus=null;
            },100);
        },
        _onTouchNode:function(_46,by){
            if(dijit._clearActiveWidgetsTimer){
                clearTimeout(dijit._clearActiveWidgetsTimer);delete dijit._clearActiveWidgetsTimer;
            }var _47=[];try{
                while(_46){
                    var _48=dojo.attr(_46,"dijitPopupParent");if(_48){
                        _46=dijit.byId(_48).domNode;
                    }else{
                        if(_46.tagName&&_46.tagName.toLowerCase()=="body"){
                            if(_46===dojo.body()){
                                break;
                            }_46=dijit.getDocumentWindow(_46.ownerDocument).frameElement;
                        }else{
                            var id=_46.getAttribute&&_46.getAttribute("widgetId");if(id){
                                _47.unshift(id);
                            }_46=_46.parentNode;
                        }
                        }
                    }
                }catch(e){}dijit._setStack(_47,by);
        },
        _onFocusNode:function(_49){
            if(!_49){
                return;
            }if(_49.nodeType==9){
                return;
            }dijit._onTouchNode(_49);if(_49==dijit._curFocus){
                return;
            }if(dijit._curFocus){
                dijit._prevFocus=dijit._curFocus;
            }dijit._curFocus=_49;dojo.publish("focusNode",[_49]);
        },
        _setStack:function(_4a,by){
            var _4b=dijit._activeStack;dijit._activeStack=_4a;for(var _4c=0;_4c<Math.min(_4b.length,_4a.length);_4c++){
                if(_4b[_4c]!=_4a[_4c]){
                    break;
                }
                }var _4d;for(var i=_4b.length-1;i>=_4c;i--){
                _4d=dijit.byId(_4b[i]);if(_4d){
                    _4d._focused=false;_4d._hasBeenBlurred=true;if(_4d._onBlur){
                        _4d._onBlur(by);
                    }if(_4d._setStateClass){
                        _4d._setStateClass();
                    }dojo.publish("widgetBlur",[_4d,by]);
                }
                }for(i=_4c;i<_4a.length;i++){
                _4d=dijit.byId(_4a[i]);if(_4d){
                    _4d._focused=true;if(_4d._onFocus){
                        _4d._onFocus(by);
                    }if(_4d._setStateClass){
                        _4d._setStateClass();
                    }dojo.publish("widgetFocus",[_4d,by]);
                }
                }
            }
        });dojo.addOnLoad(function(){
        var _4e=dijit.registerWin(window);if(dojo.isIE){
            dojo.addOnWindowUnload(function(){
                dijit.unregisterWin(_4e);_4e=null;
            });
        }
        });
}if(!dojo._hasResource["dojo.AdapterRegistry"]){
    dojo._hasResource["dojo.AdapterRegistry"]=true;dojo.provide("dojo.AdapterRegistry");dojo.AdapterRegistry=function(_4f){
        this.pairs=[];this.returnWrappers=_4f||false;
    };dojo.extend(dojo.AdapterRegistry,{
        register:function(_50,_51,_52,_53,_54){
            this.pairs[((_54)?"unshift":"push")]([_50,_51,_52,_53]);
        },
        match:function(){
            for(var i=0;i<this.pairs.length;i++){
                var _55=this.pairs[i];if(_55[1].apply(this,arguments)){
                    if((_55[3])||(this.returnWrappers)){
                        return _55[2];
                    }else{
                        return _55[2].apply(this,arguments);
                    }
                    }
                }throw new Error("No match found");
        },
        unregister:function(_56){
            for(var i=0;i<this.pairs.length;i++){
                var _57=this.pairs[i];if(_57[0]==_56){
                    this.pairs.splice(i,1);return true;
                }
                }return false;
        }
        });
}if(!dojo._hasResource["dijit._base.place"]){
    dojo._hasResource["dijit._base.place"]=true;dojo.provide("dijit._base.place");dijit.getViewport=function(){
        var _58=(dojo.doc.compatMode=="BackCompat")?dojo.body():dojo.doc.documentElement;var _59=dojo._docScroll();return {
            w:_58.clientWidth,
            h:_58.clientHeight,
            l:_59.x,
            t:_59.y
            };
    };dijit.placeOnScreen=function(_5a,pos,_5b,_5c){
        var _5d=dojo.map(_5b,function(_5e){
            var c={
                corner:_5e,
                pos:{
                    x:pos.x,
                    y:pos.y
                    }
                };if(_5c){
                c.pos.x+=_5e.charAt(1)=="L"?_5c.x:-_5c.x;c.pos.y+=_5e.charAt(0)=="T"?_5c.y:-_5c.y;
            }return c;
        });return dijit._place(_5a,_5d);
    };dijit._place=function(_5f,_60,_61){
        var _62=dijit.getViewport();if(!_5f.parentNode||String(_5f.parentNode.tagName).toLowerCase()!="body"){
            dojo.body().appendChild(_5f);
        }var _63=null;dojo.some(_60,function(_64){
            var _65=_64.corner;var pos=_64.pos;if(_61){
                _61(_5f,_64.aroundCorner,_65);
            }var _66=_5f.style;var _67=_66.display;var _68=_66.visibility;_66.visibility="hidden";_66.display="";var mb=dojo.marginBox(_5f);_66.display=_67;_66.visibility=_68;var _69=Math.max(_62.l,_65.charAt(1)=="L"?pos.x:(pos.x-mb.w)),_6a=Math.max(_62.t,_65.charAt(0)=="T"?pos.y:(pos.y-mb.h)),_6b=Math.min(_62.l+_62.w,_65.charAt(1)=="L"?(_69+mb.w):pos.x),_6c=Math.min(_62.t+_62.h,_65.charAt(0)=="T"?(_6a+mb.h):pos.y),_6d=_6b-_69,_6e=_6c-_6a,_6f=(mb.w-_6d)+(mb.h-_6e);if(_63==null||_6f<_63.overflow){
                _63={
                    corner:_65,
                    aroundCorner:_64.aroundCorner,
                    x:_69,
                    y:_6a,
                    w:_6d,
                    h:_6e,
                    overflow:_6f
                };
            }return !_6f;
        });_5f.style.left=_63.x+"px";_5f.style.top=_63.y+"px";if(_63.overflow&&_61){
            _61(_5f,_63.aroundCorner,_63.corner);
        }return _63;
    };dijit.placeOnScreenAroundNode=function(_70,_71,_72,_73){
        _71=dojo.byId(_71);var _74=_71.style.display;_71.style.display="";var _75=dojo.position(_71,true);_71.style.display=_74;return dijit._placeOnScreenAroundRect(_70,_75.x,_75.y,_75.w,_75.h,_72,_73);
    };dijit.placeOnScreenAroundRectangle=function(_76,_77,_78,_79){
        return dijit._placeOnScreenAroundRect(_76,_77.x,_77.y,_77.width,_77.height,_78,_79);
    };dijit._placeOnScreenAroundRect=function(_7a,x,y,_7b,_7c,_7d,_7e){
        var _7f=[];for(var _80 in _7d){
            _7f.push({
                aroundCorner:_80,
                corner:_7d[_80],
                pos:{
                    x:x+(_80.charAt(1)=="L"?0:_7b),
                    y:y+(_80.charAt(0)=="T"?0:_7c)
                    }
                });
        }return dijit._place(_7a,_7f,_7e);
    };dijit.placementRegistry=new dojo.AdapterRegistry();dijit.placementRegistry.register("node",function(n,x){
        return typeof x=="object"&&typeof x.offsetWidth!="undefined"&&typeof x.offsetHeight!="undefined";
    },dijit.placeOnScreenAroundNode);dijit.placementRegistry.register("rect",function(n,x){
        return typeof x=="object"&&"x" in x&&"y" in x&&"width" in x&&"height" in x;
    },dijit.placeOnScreenAroundRectangle);dijit.placeOnScreenAroundElement=function(_81,_82,_83,_84){
        return dijit.placementRegistry.match.apply(dijit.placementRegistry,arguments);
    };dijit.getPopupAlignment=function(_85,_86){
        var _87={};dojo.forEach(_85,function(pos){
            switch(pos){
                case "after":_87[_86?"BR":"BL"]=_86?"BL":"BR";break;case "before":_87[_86?"BL":"BR"]=_86?"BR":"BL";break;case "below":_87[_86?"BL":"BR"]=_86?"TL":"TR";_87[_86?"BR":"BL"]=_86?"TR":"TL";break;case "above":default:_87[_86?"TL":"TR"]=_86?"BL":"BR";_87[_86?"TR":"TL"]=_86?"BR":"BL";break;
            }
            });return _87;
    };dijit.getPopupAroundAlignment=function(_88,_89){
        var _8a={};dojo.forEach(_88,function(pos){
            switch(pos){
                case "after":_8a[_89?"BR":"BL"]=_89?"BL":"BR";break;case "before":_8a[_89?"BL":"BR"]=_89?"BR":"BL";break;case "below":_8a[_89?"BL":"BR"]=_89?"TL":"TR";_8a[_89?"BR":"BL"]=_89?"TR":"TL";break;case "above":default:_8a[_89?"TL":"TR"]=_89?"BL":"BR";_8a[_89?"TR":"TL"]=_89?"BR":"BL";break;
            }
            });return _8a;
    };
}if(!dojo._hasResource["dijit._base.window"]){
    dojo._hasResource["dijit._base.window"]=true;dojo.provide("dijit._base.window");dijit.getDocumentWindow=function(doc){
        if(dojo.isIE&&window!==document.parentWindow&&!doc._parentWindow){
            doc.parentWindow.execScript("document._parentWindow = window;","Javascript");var win=doc._parentWindow;doc._parentWindow=null;return win;
        }return doc._parentWindow||doc.parentWindow||doc.defaultView;
    };
}if(!dojo._hasResource["dijit._base.popup"]){
    dojo._hasResource["dijit._base.popup"]=true;dojo.provide("dijit._base.popup");dijit.popup=new function(){
        var _8b=[],_8c=1000,_8d=1;this.moveOffScreen=function(_8e){
            var s=_8e.style;s.visibility="hidden";s.position="absolute";s.top="-9999px";if(s.display=="none"){
                s.display="";
            }dojo.body().appendChild(_8e);
        };var _8f=function(){
            for(var pi=_8b.length-1;pi>0&&_8b[pi].parent===_8b[pi-1].widget;pi--){}return _8b[pi];
        };var _90=[];this.open=function(_91){
            var _92=_91.popup,_93=_91.orient||(dojo._isBodyLtr()?{
                "BL":"TL",
                "BR":"TR",
                "TL":"BL",
                "TR":"BR"
            }:{
                "BR":"TR",
                "BL":"TL",
                "TR":"BR",
                "TL":"BL"
            }),_94=_91.around,id=(_91.around&&_91.around.id)?(_91.around.id+"_dropdown"):("popup_"+_8d++);var _95=_90.pop(),_96,_97;if(!_95){
                _96=dojo.create("div",{
                    "class":"dijitPopup"
                },dojo.body());dijit.setWaiRole(_96,"presentation");
            }else{
                _96=_95[0];_97=_95[1];
            }dojo.attr(_96,{
                id:id,
                style:{
                    zIndex:_8c+_8b.length,
                    visibility:"hidden",
                    top:"-9999px"
                },
                dijitPopupParent:_91.parent?_91.parent.id:""
                });var s=_92.domNode.style;s.display="";s.visibility="";s.position="";s.top="0px";_96.appendChild(_92.domNode);if(!_97){
                _97=new dijit.BackgroundIframe(_96);
            }else{
                _97.resize(_96);
            }var _98=_94?dijit.placeOnScreenAroundElement(_96,_94,_93,_92.orient?dojo.hitch(_92,"orient"):null):dijit.placeOnScreen(_96,_91,_93=="R"?["TR","BR","TL","BL"]:["TL","BL","TR","BR"],_91.padding);_96.style.visibility="visible";var _99=[];_99.push(dojo.connect(_96,"onkeypress",this,function(evt){
                if(evt.charOrCode==dojo.keys.ESCAPE&&_91.onCancel){
                    dojo.stopEvent(evt);_91.onCancel();
                }else{
                    if(evt.charOrCode===dojo.keys.TAB){
                        dojo.stopEvent(evt);var _9a=_8f();if(_9a&&_9a.onCancel){
                            _9a.onCancel();
                        }
                        }
                    }
                }));if(_92.onCancel){
                _99.push(dojo.connect(_92,"onCancel",_91.onCancel));
            }_99.push(dojo.connect(_92,_92.onExecute?"onExecute":"onChange",function(){
                var _9b=_8f();if(_9b&&_9b.onExecute){
                    _9b.onExecute();
                }
                }));_8b.push({
                wrapper:_96,
                iframe:_97,
                widget:_92,
                parent:_91.parent,
                onExecute:_91.onExecute,
                onCancel:_91.onCancel,
                onClose:_91.onClose,
                handlers:_99
            });if(_92.onOpen){
                _92.onOpen(_98);
            }return _98;
        };this.close=function(_9c){
            while(dojo.some(_8b,function(_9d){
                return _9d.widget==_9c;
                })){
                var top=_8b.pop(),_9e=top.wrapper,_9f=top.iframe,_a0=top.widget,_a1=top.onClose;if(_a0.onClose){
                    _a0.onClose();
                }dojo.forEach(top.handlers,dojo.disconnect);if(_a0&&_a0.domNode){
                    this.moveOffScreen(_a0.domNode);
                }_9e.style.top="-9999px";_9e.style.visibility="hidden";_90.push([_9e,_9f]);if(_a1){
                    _a1();
                }
                }
            };
    }();dijit._frames=new function(){
        var _a2=[];this.pop=function(){
            var _a3;if(_a2.length){
                _a3=_a2.pop();_a3.style.display="";
            }else{
                if(dojo.isIE){
                    var _a4=dojo.config["dojoBlankHtmlUrl"]||(dojo.moduleUrl("dojo","resources/blank.html")+"")||"javascript:\"\"";var _a5="<iframe src='"+_a4+"'"+" style='position: absolute; left: 0px; top: 0px;"+"z-index: -1; filter:Alpha(Opacity=\"0\");'>";_a3=dojo.doc.createElement(_a5);
                }else{
                    _a3=dojo.create("iframe");_a3.src="javascript:\"\"";_a3.className="dijitBackgroundIframe";dojo.style(_a3,"opacity",0.1);
                }_a3.tabIndex=-1;
            }return _a3;
        };this.push=function(_a6){
            _a6.style.display="none";_a2.push(_a6);
        };
    }();dijit.BackgroundIframe=function(_a7){
        if(!_a7.id){
            throw new Error("no id");
        }if(dojo.isIE||dojo.isMoz){
            var _a8=dijit._frames.pop();_a7.appendChild(_a8);if(dojo.isIE<7){
                this.resize(_a7);this._conn=dojo.connect(_a7,"onresize",this,function(){
                    this.resize(_a7);
                });
            }else{
                dojo.style(_a8,{
                    width:"100%",
                    height:"100%"
                });
            }this.iframe=_a8;
        }
        };dojo.extend(dijit.BackgroundIframe,{
        resize:function(_a9){
            if(this.iframe&&dojo.isIE<7){
                dojo.style(this.iframe,{
                    width:_a9.offsetWidth+"px",
                    height:_a9.offsetHeight+"px"
                    });
            }
            },
        destroy:function(){
            if(this._conn){
                dojo.disconnect(this._conn);this._conn=null;
            }if(this.iframe){
                dijit._frames.push(this.iframe);delete this.iframe;
            }
            }
        });
}if(!dojo._hasResource["dijit._base.scroll"]){
    dojo._hasResource["dijit._base.scroll"]=true;dojo.provide("dijit._base.scroll");dijit.scrollIntoView=function(_aa,pos){
        try{
            _aa=dojo.byId(_aa);var doc=_aa.ownerDocument||dojo.doc,_ab=doc.body||dojo.body(),_ac=doc.documentElement||_ab.parentNode,_ad=dojo.isIE,_ae=dojo.isWebKit;if((!(dojo.isMoz||_ad||_ae)||_aa==_ab||_aa==_ac)&&(typeof _aa.scrollIntoView!="undefined")){
                _aa.scrollIntoView(false);return;
            }var _af=doc.compatMode=="BackCompat",_b0=_af?_ab:_ac,_b1=_ae?_ab:_b0,_b2=_b0.clientWidth,_b3=_b0.clientHeight,rtl=!dojo._isBodyLtr(),_b4=pos||dojo.position(_aa),el=_aa.parentNode,_b5=function(el){
                return ((_ad<=6||(_ad&&_af))?false:(dojo.style(el,"position").toLowerCase()=="fixed"));
            };if(_b5(_aa)){
                return;
            }while(el){
                if(el==_ab){
                    el=_b1;
                }var _b6=dojo.position(el),_b7=_b5(el);with(_b6){
                    if(el==_b1){
                        w=_b2,h=_b3;if(_b1==_ac&&_ad&&rtl){
                            x+=_b1.offsetWidth-w;
                        }if(x<0||!_ad){
                            x=0;
                        }if(y<0||!_ad){
                            y=0;
                        }
                        }else{
                        var pb=dojo._getPadBorderExtents(el);w-=pb.w;h-=pb.h;x+=pb.l;y+=pb.t;
                    }with(el){
                        if(el!=_b1){
                            var _b8=clientWidth,_b9=w-_b8;if(_b8>0&&_b9>0){
                                w=_b8;if(_ad&&rtl){
                                    x+=_b9;
                                }
                                }_b8=clientHeight;_b9=h-_b8;if(_b8>0&&_b9>0){
                                h=_b8;
                            }
                            }if(_b7){
                            if(y<0){
                                h+=y,y=0;
                            }if(x<0){
                                w+=x,x=0;
                            }if(y+h>_b3){
                                h=_b3-y;
                            }if(x+w>_b2){
                                w=_b2-x;
                            }
                            }var l=_b4.x-x,t=_b4.y-Math.max(y,0),r=l+_b4.w-w,bot=t+_b4.h-h;if(r*l>0){
                            var s=Math[l<0?"max":"min"](l,r);_b4.x+=scrollLeft;scrollLeft+=(_ad>=8&&!_af&&rtl)?-s:s;_b4.x-=scrollLeft;
                        }if(bot*t>0){
                            _b4.y+=scrollTop;scrollTop+=Math[t<0?"max":"min"](t,bot);_b4.y-=scrollTop;
                        }
                        }
                        }el=(el!=_b1)&&!_b7&&el.parentNode;
            }
            }catch(error){
            console.error("scrollIntoView: "+error);_aa.scrollIntoView(false);
        }
        };
}if(!dojo._hasResource["dijit._base.sniff"]){
    dojo._hasResource["dijit._base.sniff"]=true;dojo.provide("dijit._base.sniff");(function(){
        var d=dojo,_ba=d.doc.documentElement,ie=d.isIE,_bb=d.isOpera,maj=Math.floor,ff=d.isFF,_bc=d.boxModel.replace(/-/,""),_bd={
            dj_ie:ie,
            dj_ie6:maj(ie)==6,
            dj_ie7:maj(ie)==7,
            dj_ie8:maj(ie)==8,
            dj_iequirks:ie&&d.isQuirks,
            dj_opera:_bb,
            dj_khtml:d.isKhtml,
            dj_webkit:d.isWebKit,
            dj_safari:d.isSafari,
            dj_chrome:d.isChrome,
            dj_gecko:d.isMozilla,
            dj_ff3:maj(ff)==3
            };_bd["dj_"+_bc]=true;for(var p in _bd){
            if(_bd[p]){
                if(_ba.className){
                    _ba.className+=" "+p;
                }else{
                    _ba.className=p;
                }
                }
            }dojo._loaders.unshift(function(){
            if(!dojo._isBodyLtr()){
                _ba.className+=" dijitRtl";for(var p in _bd){
                    if(_bd[p]){
                        _ba.className+=" "+p+"-rtl";
                    }
                    }
                }
            });
    })();
}if(!dojo._hasResource["dijit._base.typematic"]){
    dojo._hasResource["dijit._base.typematic"]=true;dojo.provide("dijit._base.typematic");dijit.typematic={
        _fireEventAndReload:function(){
            this._timer=null;this._callback(++this._count,this._node,this._evt);this._currentTimeout=Math.max(this._currentTimeout<0?this._initialDelay:(this._subsequentDelay>1?this._subsequentDelay:Math.round(this._currentTimeout*this._subsequentDelay)),10);this._timer=setTimeout(dojo.hitch(this,"_fireEventAndReload"),this._currentTimeout);
        },
        trigger:function(evt,_be,_bf,_c0,obj,_c1,_c2){
            if(obj!=this._obj){
                this.stop();this._initialDelay=_c2||500;this._subsequentDelay=_c1||0.9;this._obj=obj;this._evt=evt;this._node=_bf;this._currentTimeout=-1;this._count=-1;this._callback=dojo.hitch(_be,_c0);this._fireEventAndReload();
            }
            },
        stop:function(){
            if(this._timer){
                clearTimeout(this._timer);this._timer=null;
            }if(this._obj){
                this._callback(-1,this._node,this._evt);this._obj=null;
            }
            },
        addKeyListener:function(_c3,_c4,_c5,_c6,_c7,_c8){
            if(_c4.keyCode){
                _c4.charOrCode=_c4.keyCode;dojo.deprecated("keyCode attribute parameter for dijit.typematic.addKeyListener is deprecated. Use charOrCode instead.","","2.0");
            }else{
                if(_c4.charCode){
                    _c4.charOrCode=String.fromCharCode(_c4.charCode);dojo.deprecated("charCode attribute parameter for dijit.typematic.addKeyListener is deprecated. Use charOrCode instead.","","2.0");
                }
                }return [dojo.connect(_c3,"onkeypress",this,function(evt){
                if(evt.charOrCode==_c4.charOrCode&&(_c4.ctrlKey===undefined||_c4.ctrlKey==evt.ctrlKey)&&(_c4.altKey===undefined||_c4.altKey==evt.altKey)&&(_c4.metaKey===undefined||_c4.metaKey==(evt.metaKey||false))&&(_c4.shiftKey===undefined||_c4.shiftKey==evt.shiftKey)){
                    dojo.stopEvent(evt);dijit.typematic.trigger(_c4,_c5,_c3,_c6,_c4,_c7,_c8);
                }else{
                    if(dijit.typematic._obj==_c4){
                        dijit.typematic.stop();
                    }
                    }
                }),dojo.connect(_c3,"onkeyup",this,function(evt){
                if(dijit.typematic._obj==_c4){
                    dijit.typematic.stop();
                }
                })];
        },
        addMouseListener:function(_c9,_ca,_cb,_cc,_cd){
            var dc=dojo.connect;return [dc(_c9,"mousedown",this,function(evt){
                dojo.stopEvent(evt);dijit.typematic.trigger(evt,_ca,_c9,_cb,_c9,_cc,_cd);
            }),dc(_c9,"mouseup",this,function(evt){
                dojo.stopEvent(evt);dijit.typematic.stop();
            }),dc(_c9,"mouseout",this,function(evt){
                dojo.stopEvent(evt);dijit.typematic.stop();
            }),dc(_c9,"mousemove",this,function(evt){
                dojo.stopEvent(evt);
            }),dc(_c9,"dblclick",this,function(evt){
                dojo.stopEvent(evt);if(dojo.isIE){
                    dijit.typematic.trigger(evt,_ca,_c9,_cb,_c9,_cc,_cd);setTimeout(dojo.hitch(this,dijit.typematic.stop),50);
                }
                })];
        },
        addListener:function(_ce,_cf,_d0,_d1,_d2,_d3,_d4){
            return this.addKeyListener(_cf,_d0,_d1,_d2,_d3,_d4).concat(this.addMouseListener(_ce,_d1,_d2,_d3,_d4));
        }
        };
}if(!dojo._hasResource["dijit._base.wai"]){
    dojo._hasResource["dijit._base.wai"]=true;dojo.provide("dijit._base.wai");dijit.wai={
        onload:function(){
            var div=dojo.create("div",{
                id:"a11yTestNode",
                style:{
                    cssText:"border: 1px solid;"+"border-color:red green;"+"position: absolute;"+"height: 5px;"+"top: -999px;"+"background-image: url(\""+(dojo.config.blankGif||dojo.moduleUrl("dojo","resources/blank.gif"))+"\");"
                    }
                },dojo.body());var cs=dojo.getComputedStyle(div);if(cs){
                var _d5=cs.backgroundImage;var _d6=(cs.borderTopColor==cs.borderRightColor)||(_d5!=null&&(_d5=="none"||_d5=="url(invalid-url:)"));dojo[_d6?"addClass":"removeClass"](dojo.body(),"dijit_a11y");if(dojo.isIE){
                    div.outerHTML="";
                }else{
                    dojo.body().removeChild(div);
                }
                }
            }
        };if(dojo.isIE||dojo.isMoz){
        dojo._loaders.unshift(dijit.wai.onload);
    }dojo.mixin(dijit,{
        _XhtmlRoles:/banner|contentinfo|definition|main|navigation|search|note|secondary|seealso/,
        hasWaiRole:function(_d7,_d8){
            var _d9=this.getWaiRole(_d7);return _d8?(_d9.indexOf(_d8)>-1):(_d9.length>0);
        },
        getWaiRole:function(_da){
            return dojo.trim((dojo.attr(_da,"role")||"").replace(this._XhtmlRoles,"").replace("wairole:",""));
        },
        setWaiRole:function(_db,_dc){
            var _dd=dojo.attr(_db,"role")||"";if(!this._XhtmlRoles.test(_dd)){
                dojo.attr(_db,"role",_dc);
            }else{
                if((" "+_dd+" ").indexOf(" "+_dc+" ")<0){
                    var _de=dojo.trim(_dd.replace(this._XhtmlRoles,""));var _df=dojo.trim(_dd.replace(_de,""));dojo.attr(_db,"role",_df+(_df?" ":"")+_dc);
                }
                }
            },
        removeWaiRole:function(_e0,_e1){
            var _e2=dojo.attr(_e0,"role");if(!_e2){
                return;
            }if(_e1){
                var t=dojo.trim((" "+_e2+" ").replace(" "+_e1+" "," "));dojo.attr(_e0,"role",t);
            }else{
                _e0.removeAttribute("role");
            }
            },
        hasWaiState:function(_e3,_e4){
            return _e3.hasAttribute?_e3.hasAttribute("aria-"+_e4):!!_e3.getAttribute("aria-"+_e4);
        },
        getWaiState:function(_e5,_e6){
            return _e5.getAttribute("aria-"+_e6)||"";
        },
        setWaiState:function(_e7,_e8,_e9){
            _e7.setAttribute("aria-"+_e8,_e9);
        },
        removeWaiState:function(_ea,_eb){
            _ea.removeAttribute("aria-"+_eb);
        }
        });
}if(!dojo._hasResource["dijit._base"]){
    dojo._hasResource["dijit._base"]=true;dojo.provide("dijit._base");
}if(!dojo._hasResource["dojo.date.stamp"]){
    dojo._hasResource["dojo.date.stamp"]=true;dojo.provide("dojo.date.stamp");dojo.date.stamp.fromISOString=function(_ec,_ed){
        if(!dojo.date.stamp._isoRegExp){
            dojo.date.stamp._isoRegExp=/^(?:(\d{4})(?:-(\d{2})(?:-(\d{2}))?)?)?(?:T(\d{2}):(\d{2})(?::(\d{2})(.\d+)?)?((?:[+-](\d{2}):(\d{2}))|Z)?)?$/;
        }var _ee=dojo.date.stamp._isoRegExp.exec(_ec),_ef=null;if(_ee){
            _ee.shift();if(_ee[1]){
                _ee[1]--;
            }if(_ee[6]){
                _ee[6]*=1000;
            }if(_ed){
                _ed=new Date(_ed);dojo.map(["FullYear","Month","Date","Hours","Minutes","Seconds","Milliseconds"],function(_f0){
                    return _ed["get"+_f0]();
                }).forEach(function(_f1,_f2){
                    if(_ee[_f2]===undefined){
                        _ee[_f2]=_f1;
                    }
                    });
            }_ef=new Date(_ee[0]||1970,_ee[1]||0,_ee[2]||1,_ee[3]||0,_ee[4]||0,_ee[5]||0,_ee[6]||0);if(_ee[0]<100){
                _ef.setFullYear(_ee[0]||1970);
            }var _f3=0,_f4=_ee[7]&&_ee[7].charAt(0);if(_f4!="Z"){
                _f3=((_ee[8]||0)*60)+(Number(_ee[9])||0);if(_f4!="-"){
                    _f3*=-1;
                }
                }if(_f4){
                _f3-=_ef.getTimezoneOffset();
            }if(_f3){
                _ef.setTime(_ef.getTime()+_f3*60000);
            }
            }return _ef;
    };dojo.date.stamp.toISOString=function(_f5,_f6){
        var _f7=function(n){
            return (n<10)?"0"+n:n;
        };_f6=_f6||{};var _f8=[],_f9=_f6.zulu?"getUTC":"get",_fa="";if(_f6.selector!="time"){
            var _fb=_f5[_f9+"FullYear"]();_fa=["0000".substr((_fb+"").length)+_fb,_f7(_f5[_f9+"Month"]()+1),_f7(_f5[_f9+"Date"]())].join("-");
        }_f8.push(_fa);if(_f6.selector!="date"){
            var _fc=[_f7(_f5[_f9+"Hours"]()),_f7(_f5[_f9+"Minutes"]()),_f7(_f5[_f9+"Seconds"]())].join(":");var _fd=_f5[_f9+"Milliseconds"]();if(_f6.milliseconds){
                _fc+="."+(_fd<100?"0":"")+_f7(_fd);
            }if(_f6.zulu){
                _fc+="Z";
            }else{
                if(_f6.selector!="time"){
                    var _fe=_f5.getTimezoneOffset();var _ff=Math.abs(_fe);_fc+=(_fe>0?"-":"+")+_f7(Math.floor(_ff/60))+":"+_f7(_ff%60);
                }
                }_f8.push(_fc);
        }return _f8.join("T");
    };
}if(!dojo._hasResource["dojo.parser"]){
    dojo._hasResource["dojo.parser"]=true;dojo.provide("dojo.parser");dojo.parser=new function(){
        var d=dojo;this._attrName=d._scopeName+"Type";this._query="["+this._attrName+"]";function _100(_101){
            if(d.isString(_101)){
                return "string";
            }if(typeof _101=="number"){
                return "number";
            }if(typeof _101=="boolean"){
                return "boolean";
            }if(d.isFunction(_101)){
                return "function";
            }if(d.isArray(_101)){
                return "array";
            }if(_101 instanceof Date){
                return "date";
            }if(_101 instanceof d._Url){
                return "url";
            }return "object";
        };function _102(_103,type){
            switch(type){
                case "string":return _103;case "number":return _103.length?Number(_103):NaN;case "boolean":return typeof _103=="boolean"?_103:!(_103.toLowerCase()=="false");case "function":if(d.isFunction(_103)){
                    _103=_103.toString();_103=d.trim(_103.substring(_103.indexOf("{")+1,_103.length-1));
                }try{
                    if(_103.search(/[^\w\.]+/i)!=-1){
                        return new Function(_103);
                    }else{
                        return d.getObject(_103,false);
                    }
                    }catch(e){
                    return new Function();
                }case "array":return _103?_103.split(/\s*,\s*/):[];case "date":switch(_103){
                    case "":return new Date("");case "now":return new Date();default:return d.date.stamp.fromISOString(_103);
                }case "url":return d.baseUrl+_103;default:return d.fromJson(_103);
            }
            };var _104={};dojo.connect(dojo,"extend",function(){
            _104={};
        });function _105(_106){
            if(!_104[_106]){
                var cls=d.getObject(_106);if(!d.isFunction(cls)){
                    throw new Error("Could not load class '"+_106+"'. Did you spell the name correctly and use a full path, like 'dijit.form.Button'?");
                }var _107=cls.prototype;var _108={},_109={};for(var name in _107){
                    if(name.charAt(0)=="_"){
                        continue;
                    }if(name in _109){
                        continue;
                    }var _10a=_107[name];_108[name]=_100(_10a);
                }_104[_106]={
                    cls:cls,
                    params:_108
                };
            }return _104[_106];
        };this._functionFromScript=function(_10b){
            var _10c="";var _10d="";var _10e=_10b.getAttribute("args");if(_10e){
                d.forEach(_10e.split(/\s*,\s*/),function(part,idx){
                    _10c+="var "+part+" = arguments["+idx+"]; ";
                });
            }var _10f=_10b.getAttribute("with");if(_10f&&_10f.length){
                d.forEach(_10f.split(/\s*,\s*/),function(part){
                    _10c+="with("+part+"){";_10d+="}";
                });
            }return new Function(_10c+_10b.innerHTML+_10d);
        };this.instantiate=function(_110,_111,args){
            var _112=[],dp=dojo.parser;_111=_111||{};args=args||{};d.forEach(_110,function(node){
                if(!node){
                    return;
                }var type=dp._attrName in _111?_111[dp._attrName]:node.getAttribute(dp._attrName);if(!type||!type.length){
                    return;
                }var _113=_105(type),_114=_113.cls,ps=_114._noScript||_114.prototype._noScript;var _115={},_116=node.attributes;for(var name in _113.params){
                    var item=name in _111?{
                        value:_111[name],
                        specified:true
                    }:_116.getNamedItem(name);if(!item||(!item.specified&&(!dojo.isIE||name.toLowerCase()!="value"))){
                        continue;
                    }var _117=item.value;switch(name){
                        case "class":_117="className" in _111?_111.className:node.className;break;case "style":_117="style" in _111?_111.style:(node.style&&node.style.cssText);
                    }var _118=_113.params[name];if(typeof _117=="string"){
                        _115[name]=_102(_117,_118);
                    }else{
                        _115[name]=_117;
                    }
                    }if(!ps){
                    var _119=[],_11a=[];d.query("> script[type^='dojo/']",node).orphan().forEach(function(_11b){
                        var _11c=_11b.getAttribute("event"),type=_11b.getAttribute("type"),nf=d.parser._functionFromScript(_11b);if(_11c){
                            if(type=="dojo/connect"){
                                _119.push({
                                    event:_11c,
                                    func:nf
                                });
                            }else{
                                _115[_11c]=nf;
                            }
                            }else{
                            _11a.push(nf);
                        }
                        });
                }var _11d=_114.markupFactory||_114.prototype&&_114.prototype.markupFactory;var _11e=_11d?_11d(_115,node,_114):new _114(_115,node);_112.push(_11e);var _11f=node.getAttribute("jsId");if(_11f){
                    d.setObject(_11f,_11e);
                }if(!ps){
                    d.forEach(_119,function(_120){
                        d.connect(_11e,_120.event,null,_120.func);
                    });d.forEach(_11a,function(func){
                        func.call(_11e);
                    });
                }
                });if(!_111._started){
                d.forEach(_112,function(_121){
                    if(!args.noStart&&_121&&_121.startup&&!_121._started&&(!_121.getParent||!_121.getParent())){
                        _121.startup();
                    }
                    });
            }return _112;
        };this.parse=function(_122,args){
            var root;if(!args&&_122&&_122.rootNode){
                args=_122;root=args.rootNode;
            }else{
                root=_122;
            }var list=d.query(this._query,root);return this.instantiate(list,null,args);
        };
    }();(function(){
        var _123=function(){
            if(dojo.config.parseOnLoad){
                dojo.parser.parse();
            }
            };if(dojo.exists("dijit.wai.onload")&&(dijit.wai.onload===dojo._loaders[0])){
            dojo._loaders.splice(1,0,_123);
        }else{
            dojo._loaders.unshift(_123);
        }
        })();
}if(!dojo._hasResource["dijit._Widget"]){
    dojo._hasResource["dijit._Widget"]=true;dojo.provide("dijit._Widget");dojo.require("dijit._base");dojo.connect(dojo,"_connect",function(_124,_125){
        if(_124&&dojo.isFunction(_124._onConnect)){
            _124._onConnect(_125);
        }
        });dijit._connectOnUseEventHandler=function(_126){};dijit._lastKeyDownNode=null;if(dojo.isIE){
        (function(){
            var _127=function(evt){
                dijit._lastKeyDownNode=evt.srcElement;
            };dojo.doc.attachEvent("onkeydown",_127);dojo.addOnWindowUnload(function(){
                dojo.doc.detachEvent("onkeydown",_127);
            });
        })();
    }else{
        dojo.doc.addEventListener("keydown",function(evt){
            dijit._lastKeyDownNode=evt.target;
        },true);
    }(function(){
        var _128={},_129=function(_12a){
            var dc=_12a.declaredClass;if(!_128[dc]){
                var r=[],_12b,_12c=_12a.constructor.prototype;for(var _12d in _12c){
                    if(dojo.isFunction(_12c[_12d])&&(_12b=_12d.match(/^_set([a-zA-Z]*)Attr$/))&&_12b[1]){
                        r.push(_12b[1].charAt(0).toLowerCase()+_12b[1].substr(1));
                    }
                    }_128[dc]=r;
            }return _128[dc]||[];
        };dojo.declare("dijit._Widget",null,{
            id:"",
            lang:"",
            dir:"",
            "class":"",
            style:"",
            title:"",
            tooltip:"",
            srcNodeRef:null,
            domNode:null,
            containerNode:null,
            attributeMap:{
                id:"",
                dir:"",
                lang:"",
                "class":"",
                style:"",
                title:""
            },
            _deferredConnects:{
                onClick:"",
                onDblClick:"",
                onKeyDown:"",
                onKeyPress:"",
                onKeyUp:"",
                onMouseMove:"",
                onMouseDown:"",
                onMouseOut:"",
                onMouseOver:"",
                onMouseLeave:"",
                onMouseEnter:"",
                onMouseUp:""
            },
            onClick:dijit._connectOnUseEventHandler,
            onDblClick:dijit._connectOnUseEventHandler,
            onKeyDown:dijit._connectOnUseEventHandler,
            onKeyPress:dijit._connectOnUseEventHandler,
            onKeyUp:dijit._connectOnUseEventHandler,
            onMouseDown:dijit._connectOnUseEventHandler,
            onMouseMove:dijit._connectOnUseEventHandler,
            onMouseOut:dijit._connectOnUseEventHandler,
            onMouseOver:dijit._connectOnUseEventHandler,
            onMouseLeave:dijit._connectOnUseEventHandler,
            onMouseEnter:dijit._connectOnUseEventHandler,
            onMouseUp:dijit._connectOnUseEventHandler,
            _blankGif:(dojo.config.blankGif||dojo.moduleUrl("dojo","resources/blank.gif")).toString(),
            postscript:function(_12e,_12f){
                this.create(_12e,_12f);
            },
            create:function(_130,_131){
                this.srcNodeRef=dojo.byId(_131);this._connects=[];this._subscribes=[];this._deferredConnects=dojo.clone(this._deferredConnects);for(var attr in this.attributeMap){
                    delete this._deferredConnects[attr];
                }for(attr in this._deferredConnects){
                    if(this[attr]!==dijit._connectOnUseEventHandler){
                        delete this._deferredConnects[attr];
                    }
                    }if(this.srcNodeRef&&(typeof this.srcNodeRef.id=="string")){
                    this.id=this.srcNodeRef.id;
                }if(_130){
                    this.params=_130;dojo.mixin(this,_130);
                }this.postMixInProperties();if(!this.id){
                    this.id=dijit.getUniqueId(this.declaredClass.replace(/\./g,"_"));
                }dijit.registry.add(this);this.buildRendering();if(this.domNode){
                    this._applyAttributes();var _132=this.srcNodeRef;if(_132&&_132.parentNode){
                        _132.parentNode.replaceChild(this.domNode,_132);
                    }for(attr in this.params){
                        this._onConnect(attr);
                    }
                    }if(this.domNode){
                    this.domNode.setAttribute("widgetId",this.id);
                }this.postCreate();if(this.srcNodeRef&&!this.srcNodeRef.parentNode){
                    delete this.srcNodeRef;
                }this._created=true;
            },
            _applyAttributes:function(){
                var _133=function(attr,_134){
                    if((_134.params&&attr in _134.params)||_134[attr]){
                        _134.attr(attr,_134[attr]);
                    }
                    };for(var attr in this.attributeMap){
                    _133(attr,this);
                }dojo.forEach(_129(this),function(a){
                    if(!(a in this.attributeMap)){
                        _133(a,this);
                    }
                    },this);
            },
            postMixInProperties:function(){},
            buildRendering:function(){
                this.domNode=this.srcNodeRef||dojo.create("div");
            },
            postCreate:function(){},
            startup:function(){
                this._started=true;
            },
            destroyRecursive:function(_135){
                this._beingDestroyed=true;this.destroyDescendants(_135);this.destroy(_135);
            },
            destroy:function(_136){
                this._beingDestroyed=true;this.uninitialize();var d=dojo,dfe=d.forEach,dun=d.unsubscribe;dfe(this._connects,function(_137){
                    dfe(_137,d.disconnect);
                });dfe(this._subscribes,function(_138){
                    dun(_138);
                });dfe(this._supportingWidgets||[],function(w){
                    if(w.destroyRecursive){
                        w.destroyRecursive();
                    }else{
                        if(w.destroy){
                            w.destroy();
                        }
                        }
                    });this.destroyRendering(_136);dijit.registry.remove(this.id);this._destroyed=true;
            },
            destroyRendering:function(_139){
                if(this.bgIframe){
                    this.bgIframe.destroy(_139);delete this.bgIframe;
                }if(this.domNode){
                    if(_139){
                        dojo.removeAttr(this.domNode,"widgetId");
                    }else{
                        dojo.destroy(this.domNode);
                    }delete this.domNode;
                }if(this.srcNodeRef){
                    if(!_139){
                        dojo.destroy(this.srcNodeRef);
                    }delete this.srcNodeRef;
                }
                },
            destroyDescendants:function(_13a){
                dojo.forEach(this.getChildren(),function(_13b){
                    if(_13b.destroyRecursive){
                        _13b.destroyRecursive(_13a);
                    }
                    });
            },
            uninitialize:function(){
                return false;
            },
            onFocus:function(){},
            onBlur:function(){},
            _onFocus:function(e){
                this.onFocus();
            },
            _onBlur:function(){
                this.onBlur();
            },
            _onConnect:function(_13c){
                if(_13c in this._deferredConnects){
                    var _13d=this[this._deferredConnects[_13c]||"domNode"];this.connect(_13d,_13c.toLowerCase(),_13c);delete this._deferredConnects[_13c];
                }
                },
            _setClassAttr:function(_13e){
                var _13f=this[this.attributeMap["class"]||"domNode"];dojo.removeClass(_13f,this["class"]);this["class"]=_13e;dojo.addClass(_13f,_13e);
            },
            _setStyleAttr:function(_140){
                var _141=this[this.attributeMap.style||"domNode"];if(dojo.isObject(_140)){
                    dojo.style(_141,_140);
                }else{
                    if(_141.style.cssText){
                        _141.style.cssText+="; "+_140;
                    }else{
                        _141.style.cssText=_140;
                    }
                    }this.style=_140;
            },
            setAttribute:function(attr,_142){
                dojo.deprecated(this.declaredClass+"::setAttribute() is deprecated. Use attr() instead.","","2.0");this.attr(attr,_142);
            },
            _attrToDom:function(attr,_143){
                var _144=this.attributeMap[attr];dojo.forEach(dojo.isArray(_144)?_144:[_144],function(_145){
                    var _146=this[_145.node||_145||"domNode"];var type=_145.type||"attribute";switch(type){
                        case "attribute":if(dojo.isFunction(_143)){
                            _143=dojo.hitch(this,_143);
                        }var _147=_145.attribute?_145.attribute:(/^on[A-Z][a-zA-Z]*$/.test(attr)?attr.toLowerCase():attr);dojo.attr(_146,_147,_143);break;case "innerText":_146.innerHTML="";_146.appendChild(dojo.doc.createTextNode(_143));break;case "innerHTML":_146.innerHTML=_143;break;case "class":dojo.removeClass(_146,this[attr]);dojo.addClass(_146,_143);break;
                    }
                    },this);this[attr]=_143;
            },
            attr:function(name,_148){
                var args=arguments.length;if(args==1&&!dojo.isString(name)){
                    for(var x in name){
                        this.attr(x,name[x]);
                    }return this;
                }var _149=this._getAttrNames(name);if(args>=2){
                    if(this[_149.s]){
                        args=dojo._toArray(arguments,1);return this[_149.s].apply(this,args)||this;
                    }else{
                        if(name in this.attributeMap){
                            this._attrToDom(name,_148);
                        }this[name]=_148;
                    }return this;
                }else{
                    return this[_149.g]?this[_149.g]():this[name];
                }
                },
            _attrPairNames:{},
            _getAttrNames:function(name){
                var apn=this._attrPairNames;if(apn[name]){
                    return apn[name];
                }var uc=name.charAt(0).toUpperCase()+name.substr(1);return (apn[name]={
                    n:name+"Node",
                    s:"_set"+uc+"Attr",
                    g:"_get"+uc+"Attr"
                    });
            },
            toString:function(){
                return "[Widget "+this.declaredClass+", "+(this.id||"NO ID")+"]";
            },
            getDescendants:function(){
                return this.containerNode?dojo.query("[widgetId]",this.containerNode).map(dijit.byNode):[];
            },
            getChildren:function(){
                return this.containerNode?dijit.findWidgets(this.containerNode):[];
            },
            nodesWithKeyClick:["input","button"],
            connect:function(obj,_14a,_14b){
                var d=dojo,dc=d._connect,_14c=[];if(_14a=="ondijitclick"){
                    if(!this.nodesWithKeyClick[obj.tagName.toLowerCase()]){
                        var m=d.hitch(this,_14b);_14c.push(dc(obj,"onkeydown",this,function(e){
                            if((e.keyCode==d.keys.ENTER||e.keyCode==d.keys.SPACE)&&!e.ctrlKey&&!e.shiftKey&&!e.altKey&&!e.metaKey){
                                dijit._lastKeyDownNode=e.target;d.stopEvent(e);
                            }
                            }),dc(obj,"onkeyup",this,function(e){
                            if((e.keyCode==d.keys.ENTER||e.keyCode==d.keys.SPACE)&&e.target===dijit._lastKeyDownNode&&!e.ctrlKey&&!e.shiftKey&&!e.altKey&&!e.metaKey){
                                dijit._lastKeyDownNode=null;return m(e);
                            }
                            }));
                    }_14a="onclick";
                }_14c.push(dc(obj,_14a,this,_14b));this._connects.push(_14c);return _14c;
            },
            disconnect:function(_14d){
                for(var i=0;i<this._connects.length;i++){
                    if(this._connects[i]==_14d){
                        dojo.forEach(_14d,dojo.disconnect);this._connects.splice(i,1);return;
                    }
                    }
                },
            subscribe:function(_14e,_14f){
                var d=dojo,_150=d.subscribe(_14e,this,_14f);this._subscribes.push(_150);return _150;
            },
            unsubscribe:function(_151){
                for(var i=0;i<this._subscribes.length;i++){
                    if(this._subscribes[i]==_151){
                        dojo.unsubscribe(_151);this._subscribes.splice(i,1);return;
                    }
                    }
                },
            isLeftToRight:function(){
                return dojo._isBodyLtr();
            },
            isFocusable:function(){
                return this.focus&&(dojo.style(this.domNode,"display")!="none");
            },
            placeAt:function(_152,_153){
                if(_152.declaredClass&&_152.addChild){
                    _152.addChild(this,_153);
                }else{
                    dojo.place(this.domNode,_152,_153);
                }return this;
            },
            _onShow:function(){
                this.onShow();
            },
            onShow:function(){},
            onHide:function(){}
            });
    })();
}if(!dojo._hasResource["dojo.string"]){
    dojo._hasResource["dojo.string"]=true;dojo.provide("dojo.string");dojo.string.rep=function(str,num){
        if(num<=0||!str){
            return "";
        }var buf=[];for(;;){
            if(num&1){
                buf.push(str);
            }if(!(num>>=1)){
                break;
            }str+=str;
        }return buf.join("");
    };dojo.string.pad=function(text,size,ch,end){
        if(!ch){
            ch="0";
        }var out=String(text),pad=dojo.string.rep(ch,Math.ceil((size-out.length)/ch.length));return end?out+pad:pad+out;
    };dojo.string.substitute=function(_154,map,_155,_156){
        _156=_156||dojo.global;_155=_155?dojo.hitch(_156,_155):function(v){
            return v;
        };return _154.replace(/\$\{([^\s\:\}]+)(?:\:([^\s\:\}]+))?\}/g,function(_157,key,_158){
            var _159=dojo.getObject(key,false,map);if(_158){
                _159=dojo.getObject(_158,false,_156).call(_156,_159,key);
            }return _155(_159,key).toString();
        });
    };dojo.string.trim=String.prototype.trim?dojo.trim:function(str){
        str=str.replace(/^\s+/,"");for(var i=str.length-1;i>=0;i--){
            if(/\S/.test(str.charAt(i))){
                str=str.substring(0,i+1);break;
            }
            }return str;
    };
}if(!dojo._hasResource["dojo.cache"]){
    dojo._hasResource["dojo.cache"]=true;dojo.provide("dojo.cache");(function(){
        var _15a={};dojo.cache=function(_15b,url,_15c){
            if(typeof _15b=="string"){
                var _15d=dojo.moduleUrl(_15b,url);
            }else{
                _15d=_15b;_15c=url;
            }var key=_15d.toString();var val=_15c;if(_15c!==undefined&&!dojo.isString(_15c)){
                val=("value" in _15c?_15c.value:undefined);
            }var _15e=_15c&&_15c.sanitize?true:false;if(val||val===null){
                if(val==null){
                    delete _15a[key];
                }else{
                    val=_15a[key]=_15e?dojo.cache._sanitize(val):val;
                }
                }else{
                if(!(key in _15a)){
                    val=dojo._getText(key);_15a[key]=_15e?dojo.cache._sanitize(val):val;
                }val=_15a[key];
            }return val;
        };dojo.cache._sanitize=function(val){
            if(val){
                val=val.replace(/^\s*<\?xml(\s)+version=[\'\"](\d)*.(\d)*[\'\"](\s)*\?>/im,"");var _15f=val.match(/<body[^>]*>\s*([\s\S]+)\s*<\/body>/im);if(_15f){
                    val=_15f[1];
                }
                }else{
                val="";
            }return val;
        };
    })();
}if(!dojo._hasResource["dijit._Templated"]){
    dojo._hasResource["dijit._Templated"]=true;dojo.provide("dijit._Templated");dojo.declare("dijit._Templated",null,{
        templateString:null,
        templatePath:null,
        widgetsInTemplate:false,
        _skipNodeCache:false,
        _earlyTemplatedStartup:false,
        _stringRepl:function(tmpl){
            var _160=this.declaredClass,_161=this;return dojo.string.substitute(tmpl,this,function(_162,key){
                if(key.charAt(0)=="!"){
                    _162=dojo.getObject(key.substr(1),false,_161);
                }if(typeof _162=="undefined"){
                    throw new Error(_160+" template:"+key);
                }if(_162==null){
                    return "";
                }return key.charAt(0)=="!"?_162:_162.toString().replace(/"/g,"&quot;");
            },this);
        },
        buildRendering:function(){
            this._attachPoints=[];var _163=dijit._Templated.getCachedTemplate(this.templatePath,this.templateString,this._skipNodeCache);var node;if(dojo.isString(_163)){
                node=dojo._toDom(this._stringRepl(_163));if(node.nodeType!=1){
                    throw new Error("Invalid template: "+_163);
                }
                }else{
                node=_163.cloneNode(true);
            }this.domNode=node;this._attachTemplateNodes(node);if(this.widgetsInTemplate){
                var _164=dojo.parser,qry,attr;if(_164._query!="[dojoType]"){
                    qry=_164._query;attr=_164._attrName;_164._query="[dojoType]";_164._attrName="dojoType";
                }var cw=(this._startupWidgets=dojo.parser.parse(node,{
                    noStart:!this._earlyTemplatedStartup
                    }));if(qry){
                    _164._query=qry;_164._attrName=attr;
                }this._supportingWidgets=dijit.findWidgets(node);this._attachTemplateNodes(cw,function(n,p){
                    return n[p];
                });
            }this._fillContent(this.srcNodeRef);
        },
        _fillContent:function(_165){
            var dest=this.containerNode;if(_165&&dest){
                while(_165.hasChildNodes()){
                    dest.appendChild(_165.firstChild);
                }
                }
            },
        _attachTemplateNodes:function(_166,_167){
            _167=_167||function(n,p){
                return n.getAttribute(p);
            };var _168=dojo.isArray(_166)?_166:(_166.all||_166.getElementsByTagName("*"));var x=dojo.isArray(_166)?0:-1;for(;x<_168.length;x++){
                var _169=(x==-1)?_166:_168[x];if(this.widgetsInTemplate&&_167(_169,"dojoType")){
                    continue;
                }var _16a=_167(_169,"dojoAttachPoint");if(_16a){
                    var _16b,_16c=_16a.split(/\s*,\s*/);while((_16b=_16c.shift())){
                        if(dojo.isArray(this[_16b])){
                            this[_16b].push(_169);
                        }else{
                            this[_16b]=_169;
                        }this._attachPoints.push(_16b);
                    }
                    }var _16d=_167(_169,"dojoAttachEvent");if(_16d){
                    var _16e,_16f=_16d.split(/\s*,\s*/);var trim=dojo.trim;while((_16e=_16f.shift())){
                        if(_16e){
                            var _170=null;if(_16e.indexOf(":")!=-1){
                                var _171=_16e.split(":");_16e=trim(_171[0]);_170=trim(_171[1]);
                            }else{
                                _16e=trim(_16e);
                            }if(!_170){
                                _170=_16e;
                            }this.connect(_169,_16e,_170);
                        }
                        }
                    }var role=_167(_169,"waiRole");if(role){
                    dijit.setWaiRole(_169,role);
                }var _172=_167(_169,"waiState");if(_172){
                    dojo.forEach(_172.split(/\s*,\s*/),function(_173){
                        if(_173.indexOf("-")!=-1){
                            var pair=_173.split("-");dijit.setWaiState(_169,pair[0],pair[1]);
                        }
                        });
                }
                }
            },
        startup:function(){
            dojo.forEach(this._startupWidgets,function(w){
                if(w&&!w._started&&w.startup){
                    w.startup();
                }
                });this.inherited(arguments);
        },
        destroyRendering:function(){
            dojo.forEach(this._attachPoints,function(_174){
                delete this[_174];
            },this);this._attachPoints=[];this.inherited(arguments);
        }
        });dijit._Templated._templateCache={};dijit._Templated.getCachedTemplate=function(_175,_176,_177){
        var _178=dijit._Templated._templateCache;var key=_176||_175;var _179=_178[key];if(_179){
            try{
                if(!_179.ownerDocument||_179.ownerDocument==dojo.doc){
                    return _179;
                }
                }catch(e){}dojo.destroy(_179);
        }if(!_176){
            _176=dojo.cache(_175,{
                sanitize:true
            });
        }_176=dojo.string.trim(_176);if(_177||_176.match(/\$\{([^\}]+)\}/g)){
            return (_178[key]=_176);
        }else{
            var node=dojo._toDom(_176);if(node.nodeType!=1){
                throw new Error("Invalid template: "+_176);
            }return (_178[key]=node);
        }
        };if(dojo.isIE){
        dojo.addOnWindowUnload(function(){
            var _17a=dijit._Templated._templateCache;for(var key in _17a){
                var _17b=_17a[key];if(typeof _17b=="object"){
                    dojo.destroy(_17b);
                }delete _17a[key];
            }
            });
    }dojo.extend(dijit._Widget,{
        dojoAttachEvent:"",
        dojoAttachPoint:"",
        waiRole:"",
        waiState:""
    });
}if(!dojo._hasResource["dijit._Container"]){
    dojo._hasResource["dijit._Container"]=true;dojo.provide("dijit._Container");dojo.declare("dijit._Container",null,{
        isContainer:true,
        buildRendering:function(){
            this.inherited(arguments);if(!this.containerNode){
                this.containerNode=this.domNode;
            }
            },
        addChild:function(_17c,_17d){
            var _17e=this.containerNode;if(_17d&&typeof _17d=="number"){
                var _17f=this.getChildren();if(_17f&&_17f.length>=_17d){
                    _17e=_17f[_17d-1].domNode;_17d="after";
                }
                }dojo.place(_17c.domNode,_17e,_17d);if(this._started&&!_17c._started){
                _17c.startup();
            }
            },
        removeChild:function(_180){
            if(typeof _180=="number"&&_180>0){
                _180=this.getChildren()[_180];
            }if(_180&&_180.domNode){
                var node=_180.domNode;node.parentNode.removeChild(node);
            }
            },
        getChildren:function(){
            return dojo.query("> [widgetId]",this.containerNode).map(dijit.byNode);
        },
        hasChildren:function(){
            return dojo.query("> [widgetId]",this.containerNode).length>0;
        },
        destroyDescendants:function(_181){
            dojo.forEach(this.getChildren(),function(_182){
                _182.destroyRecursive(_181);
            });
        },
        _getSiblingOfChild:function(_183,dir){
            var node=_183.domNode,_184=(dir>0?"nextSibling":"previousSibling");do{
                node=node[_184];
            }while(node&&(node.nodeType!=1||!dijit.byNode(node)));return node&&dijit.byNode(node);
        },
        getIndexOfChild:function(_185){
            return dojo.indexOf(this.getChildren(),_185);
        },
        startup:function(){
            if(this._started){
                return;
            }dojo.forEach(this.getChildren(),function(_186){
                _186.startup();
            });this.inherited(arguments);
        }
        });
}if(!dojo._hasResource["dijit._Contained"]){
    dojo._hasResource["dijit._Contained"]=true;dojo.provide("dijit._Contained");dojo.declare("dijit._Contained",null,{
        getParent:function(){
            var _187=dijit.getEnclosingWidget(this.domNode.parentNode);return _187&&_187.isContainer?_187:null;
        },
        _getSibling:function(_188){
            var node=this.domNode;do{
                node=node[_188+"Sibling"];
            }while(node&&node.nodeType!=1);return node&&dijit.byNode(node);
        },
        getPreviousSibling:function(){
            return this._getSibling("previous");
        },
        getNextSibling:function(){
            return this._getSibling("next");
        },
        getIndexInParent:function(){
            var p=this.getParent();if(!p||!p.getIndexOfChild){
                return -1;
            }return p.getIndexOfChild(this);
        }
        });
}if(!dojo._hasResource["dijit.layout._LayoutWidget"]){
    dojo._hasResource["dijit.layout._LayoutWidget"]=true;dojo.provide("dijit.layout._LayoutWidget");dojo.declare("dijit.layout._LayoutWidget",[dijit._Widget,dijit._Container,dijit._Contained],{
        baseClass:"dijitLayoutContainer",
        isLayoutContainer:true,
        postCreate:function(){
            dojo.addClass(this.domNode,"dijitContainer");dojo.addClass(this.domNode,this.baseClass);this.inherited(arguments);
        },
        startup:function(){
            if(this._started){
                return;
            }this.inherited(arguments);var _189=this.getParent&&this.getParent();if(!(_189&&_189.isLayoutContainer)){
                this.resize();this.connect(dojo.isIE?this.domNode:dojo.global,"onresize",function(){
                    this.resize();
                });
            }
            },
        resize:function(_18a,_18b){
            var node=this.domNode;if(_18a){
                dojo.marginBox(node,_18a);if(_18a.t){
                    node.style.top=_18a.t+"px";
                }if(_18a.l){
                    node.style.left=_18a.l+"px";
                }
                }var mb=_18b||{};dojo.mixin(mb,_18a||{});if(!("h" in mb)||!("w" in mb)){
                mb=dojo.mixin(dojo.marginBox(node),mb);
            }var cs=dojo.getComputedStyle(node);var me=dojo._getMarginExtents(node,cs);var be=dojo._getBorderExtents(node,cs);var bb=(this._borderBox={
                w:mb.w-(me.w+be.w),
                h:mb.h-(me.h+be.h)
                });var pe=dojo._getPadExtents(node,cs);this._contentBox={
                l:dojo._toPixelValue(node,cs.paddingLeft),
                t:dojo._toPixelValue(node,cs.paddingTop),
                w:bb.w-pe.w,
                h:bb.h-pe.h
                };this.layout();
        },
        layout:function(){},
        _setupChild:function(_18c){
            dojo.addClass(_18c.domNode,this.baseClass+"-child");if(_18c.baseClass){
                dojo.addClass(_18c.domNode,this.baseClass+"-"+_18c.baseClass);
            }
            },
        addChild:function(_18d,_18e){
            this.inherited(arguments);if(this._started){
                this._setupChild(_18d);
            }
            },
        removeChild:function(_18f){
            dojo.removeClass(_18f.domNode,this.baseClass+"-child");if(_18f.baseClass){
                dojo.removeClass(_18f.domNode,this.baseClass+"-"+_18f.baseClass);
            }this.inherited(arguments);
        }
        });dijit.layout.marginBox2contentBox=function(node,mb){
        var cs=dojo.getComputedStyle(node);var me=dojo._getMarginExtents(node,cs);var pb=dojo._getPadBorderExtents(node,cs);return {
            l:dojo._toPixelValue(node,cs.paddingLeft),
            t:dojo._toPixelValue(node,cs.paddingTop),
            w:mb.w-(me.w+pb.w),
            h:mb.h-(me.h+pb.h)
            };
    };(function(){
        var _190=function(word){
            return word.substring(0,1).toUpperCase()+word.substring(1);
        };var size=function(_191,dim){
            _191.resize?_191.resize(dim):dojo.marginBox(_191.domNode,dim);dojo.mixin(_191,dojo.marginBox(_191.domNode));dojo.mixin(_191,dim);
        };dijit.layout.layoutChildren=function(_192,dim,_193){
            dim=dojo.mixin({},dim);dojo.addClass(_192,"dijitLayoutContainer");_193=dojo.filter(_193,function(item){
                return item.layoutAlign!="client";
            }).concat(dojo.filter(_193,function(item){
                return item.layoutAlign=="client";
            }));dojo.forEach(_193,function(_194){
                var elm=_194.domNode,pos=_194.layoutAlign;var _195=elm.style;_195.left=dim.l+"px";_195.top=dim.t+"px";_195.bottom=_195.right="auto";dojo.addClass(elm,"dijitAlign"+_190(pos));if(pos=="top"||pos=="bottom"){
                    size(_194,{
                        w:dim.w
                        });dim.h-=_194.h;if(pos=="top"){
                        dim.t+=_194.h;
                    }else{
                        _195.top=dim.t+dim.h+"px";
                    }
                    }else{
                    if(pos=="left"||pos=="right"){
                        size(_194,{
                            h:dim.h
                            });dim.w-=_194.w;if(pos=="left"){
                            dim.l+=_194.w;
                        }else{
                            _195.left=dim.l+dim.w+"px";
                        }
                        }else{
                        if(pos=="client"){
                            size(_194,dim);
                        }
                        }
                    }
                });
        };
    })();
}if(!dojo._hasResource["dijit.form._FormWidget"]){
    dojo._hasResource["dijit.form._FormWidget"]=true;dojo.provide("dijit.form._FormWidget");dojo.declare("dijit.form._FormWidget",[dijit._Widget,dijit._Templated],{
        baseClass:"",
        name:"",
        alt:"",
        value:"",
        type:"text",
        tabIndex:"0",
        disabled:false,
        intermediateChanges:false,
        scrollOnFocus:true,
        attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{
            value:"focusNode",
            id:"focusNode",
            tabIndex:"focusNode",
            alt:"focusNode",
            title:"focusNode"
        }),
        postMixInProperties:function(){
            this.nameAttrSetting=this.name?("name='"+this.name+"'"):"";this.inherited(arguments);
        },
        _setDisabledAttr:function(_196){
            this.disabled=_196;dojo.attr(this.focusNode,"disabled",_196);if(this.valueNode){
                dojo.attr(this.valueNode,"disabled",_196);
            }dijit.setWaiState(this.focusNode,"disabled",_196);if(_196){
                this._hovering=false;this._active=false;this.focusNode.setAttribute("tabIndex","-1");
            }else{
                this.focusNode.setAttribute("tabIndex",this.tabIndex);
            }this._setStateClass();
        },
        setDisabled:function(_197){
            dojo.deprecated("setDisabled("+_197+") is deprecated. Use attr('disabled',"+_197+") instead.","","2.0");this.attr("disabled",_197);
        },
        _onFocus:function(e){
            if(this.scrollOnFocus){
                dijit.scrollIntoView(this.domNode);
            }this.inherited(arguments);
        },
        _onMouse:function(_198){
            var _199=_198.currentTarget;if(_199&&_199.getAttribute){
                this.stateModifier=_199.getAttribute("stateModifier")||"";
            }if(!this.disabled){
                switch(_198.type){
                    case "mouseenter":case "mouseover":this._hovering=true;this._active=this._mouseDown;break;case "mouseout":case "mouseleave":this._hovering=false;this._active=false;break;case "mousedown":this._active=true;this._mouseDown=true;var _19a=this.connect(dojo.body(),"onmouseup",function(){
                        if(this._mouseDown&&this.isFocusable()){
                            this.focus();
                        }this._active=false;this._mouseDown=false;this._setStateClass();this.disconnect(_19a);
                    });break;
                }this._setStateClass();
            }
            },
        isFocusable:function(){
            return !this.disabled&&!this.readOnly&&this.focusNode&&(dojo.style(this.domNode,"display")!="none");
        },
        focus:function(){
            dijit.focus(this.focusNode);
        },
        _setStateClass:function(){
            var _19b=this.baseClass.split(" ");function _19c(_19d){
                _19b=_19b.concat(dojo.map(_19b,function(c){
                    return c+_19d;
                }),"dijit"+_19d);
            };if(this.checked){
                _19c("Checked");
            }if(this.state){
                _19c(this.state);
            }if(this.selected){
                _19c("Selected");
            }if(this.disabled){
                _19c("Disabled");
            }else{
                if(this.readOnly){
                    _19c("ReadOnly");
                }else{
                    if(this._active){
                        _19c(this.stateModifier+"Active");
                    }else{
                        if(this._focused){
                            _19c("Focused");
                        }if(this._hovering){
                            _19c(this.stateModifier+"Hover");
                        }
                        }
                    }
                }var tn=this.stateNode||this.domNode,_19e={};dojo.forEach(tn.className.split(" "),function(c){
                _19e[c]=true;
            });if("_stateClasses" in this){
                dojo.forEach(this._stateClasses,function(c){
                    delete _19e[c];
                });
            }dojo.forEach(_19b,function(c){
                _19e[c]=true;
            });var _19f=[];for(var c in _19e){
                _19f.push(c);
            }tn.className=_19f.join(" ");this._stateClasses=_19b;
        },
        compare:function(val1,val2){
            if(typeof val1=="number"&&typeof val2=="number"){
                return (isNaN(val1)&&isNaN(val2))?0:val1-val2;
            }else{
                if(val1>val2){
                    return 1;
                }else{
                    if(val1<val2){
                        return -1;
                    }else{
                        return 0;
                    }
                    }
                }
            },
        onChange:function(_1a0){},
        _onChangeActive:false,
        _handleOnChange:function(_1a1,_1a2){
            this._lastValue=_1a1;if(this._lastValueReported==undefined&&(_1a2===null||!this._onChangeActive)){
                this._resetValue=this._lastValueReported=_1a1;
            }if((this.intermediateChanges||_1a2||_1a2===undefined)&&((typeof _1a1!=typeof this._lastValueReported)||this.compare(_1a1,this._lastValueReported)!=0)){
                this._lastValueReported=_1a1;if(this._onChangeActive){
                    if(this._onChangeHandle){
                        clearTimeout(this._onChangeHandle);
                    }this._onChangeHandle=setTimeout(dojo.hitch(this,function(){
                        this._onChangeHandle=null;this.onChange(_1a1);
                    }),0);
                }
                }
            },
        create:function(){
            this.inherited(arguments);this._onChangeActive=true;this._setStateClass();
        },
        destroy:function(){
            if(this._onChangeHandle){
                clearTimeout(this._onChangeHandle);this.onChange(this._lastValueReported);
            }this.inherited(arguments);
        },
        setValue:function(_1a3){
            dojo.deprecated("dijit.form._FormWidget:setValue("+_1a3+") is deprecated.  Use attr('value',"+_1a3+") instead.","","2.0");this.attr("value",_1a3);
        },
        getValue:function(){
            dojo.deprecated(this.declaredClass+"::getValue() is deprecated. Use attr('value') instead.","","2.0");return this.attr("value");
        }
        });dojo.declare("dijit.form._FormValueWidget",dijit.form._FormWidget,{
        readOnly:false,
        attributeMap:dojo.delegate(dijit.form._FormWidget.prototype.attributeMap,{
            value:"",
            readOnly:"focusNode"
        }),
        _setReadOnlyAttr:function(_1a4){
            this.readOnly=_1a4;dojo.attr(this.focusNode,"readOnly",_1a4);dijit.setWaiState(this.focusNode,"readonly",_1a4);this._setStateClass();
        },
        postCreate:function(){
            if(dojo.isIE){
                this.connect(this.focusNode||this.domNode,"onkeydown",this._onKeyDown);
            }if(this._resetValue===undefined){
                this._resetValue=this.value;
            }
            },
        _setValueAttr:function(_1a5,_1a6){
            this.value=_1a5;this._handleOnChange(_1a5,_1a6);
        },
        _getValueAttr:function(){
            return this._lastValue;
        },
        undo:function(){
            this._setValueAttr(this._lastValueReported,false);
        },
        reset:function(){
            this._hasBeenBlurred=false;this._setValueAttr(this._resetValue,true);
        },
        _onKeyDown:function(e){
            if(e.keyCode==dojo.keys.ESCAPE&&!(e.ctrlKey||e.altKey||e.metaKey)){
                var te;if(dojo.isIE){
                    e.preventDefault();te=document.createEventObject();te.keyCode=dojo.keys.ESCAPE;te.shiftKey=e.shiftKey;e.srcElement.fireEvent("onkeypress",te);
                }
                }
            },
        _layoutHackIE7:function(){
            if(dojo.isIE==7){
                var _1a7=this.domNode;var _1a8=_1a7.parentNode;var _1a9=_1a7.firstChild||_1a7;var _1aa=_1a9.style.filter;while(_1a8&&_1a8.clientHeight==0){
                    _1a8._disconnectHandle=this.connect(_1a8,"onscroll",dojo.hitch(this,function(e){
                        this.disconnect(_1a8._disconnectHandle);_1a8.removeAttribute("_disconnectHandle");_1a9.style.filter=(new Date()).getMilliseconds();setTimeout(function(){
                            _1a9.style.filter=_1aa;
                        },0);
                    }));_1a8=_1a8.parentNode;
                }
                }
            }
        });
}if(!dojo._hasResource["dijit.dijit"]){
    dojo._hasResource["dijit.dijit"]=true;dojo.provide("dijit.dijit");
}if(!dojo._hasResource["dijit._KeyNavContainer"]){
    dojo._hasResource["dijit._KeyNavContainer"]=true;dojo.provide("dijit._KeyNavContainer");dojo.declare("dijit._KeyNavContainer",dijit._Container,{
        tabIndex:"0",
        _keyNavCodes:{},
        connectKeyNavHandlers:function(_1ab,_1ac){
            var _1ad=(this._keyNavCodes={});var prev=dojo.hitch(this,this.focusPrev);var next=dojo.hitch(this,this.focusNext);dojo.forEach(_1ab,function(code){
                _1ad[code]=prev;
            });dojo.forEach(_1ac,function(code){
                _1ad[code]=next;
            });this.connect(this.domNode,"onkeypress","_onContainerKeypress");this.connect(this.domNode,"onfocus","_onContainerFocus");
        },
        startupKeyNavChildren:function(){
            dojo.forEach(this.getChildren(),dojo.hitch(this,"_startupChild"));
        },
        addChild:function(_1ae,_1af){
            dijit._KeyNavContainer.superclass.addChild.apply(this,arguments);this._startupChild(_1ae);
        },
        focus:function(){
            this.focusFirstChild();
        },
        focusFirstChild:function(){
            var _1b0=this._getFirstFocusableChild();this.focusChild(_1b0);
        },
        focusNext:function(){
            var _1b1=this._getNextFocusableChild(this.focusedChild,1);this.focusChild(_1b1);
        },
        focusPrev:function(){
            var _1b2=this._getNextFocusableChild(this.focusedChild,-1);this.focusChild(_1b2,true);
        },
        focusChild:function(_1b3,last){
            if(this.focusedChild&&_1b3!==this.focusedChild){
                this._onChildBlur(this.focusedChild);
            }_1b3.focus(last?"end":"start");this.focusedChild=_1b3;
        },
        _startupChild:function(_1b4){
            _1b4.attr("tabIndex","-1");this.connect(_1b4,"_onFocus",function(){
                _1b4.attr("tabIndex",this.tabIndex);
            });this.connect(_1b4,"_onBlur",function(){
                _1b4.attr("tabIndex","-1");
            });
        },
        _onContainerFocus:function(evt){
            if(evt.target!==this.domNode){
                return;
            }this.focusFirstChild();dojo.attr(this.domNode,"tabIndex","-1");
        },
        _onBlur:function(evt){
            if(this.tabIndex){
                dojo.attr(this.domNode,"tabIndex",this.tabIndex);
            }this.inherited(arguments);
        },
        _onContainerKeypress:function(evt){
            if(evt.ctrlKey||evt.altKey){
                return;
            }var func=this._keyNavCodes[evt.charOrCode];if(func){
                func();dojo.stopEvent(evt);
            }
            },
        _onChildBlur:function(_1b5){},
        _getFirstFocusableChild:function(){
            return this._getNextFocusableChild(null,1);
        },
        _getNextFocusableChild:function(_1b6,dir){
            if(_1b6){
                _1b6=this._getSiblingOfChild(_1b6,dir);
            }var _1b7=this.getChildren();for(var i=0;i<_1b7.length;i++){
                if(!_1b6){
                    _1b6=_1b7[(dir>0)?0:(_1b7.length-1)];
                }if(_1b6.isFocusable()){
                    return _1b6;
                }_1b6=this._getSiblingOfChild(_1b6,dir);
            }return null;
        }
        });
}if(!dojo._hasResource["dijit.MenuItem"]){
    dojo._hasResource["dijit.MenuItem"]=true;dojo.provide("dijit.MenuItem");dojo.declare("dijit.MenuItem",[dijit._Widget,dijit._Templated,dijit._Contained],{
        templateString:dojo.cache("dijit","templates/MenuItem.html","<tr class=\"dijitReset dijitMenuItem\" dojoAttachPoint=\"focusNode\" waiRole=\"menuitem\" tabIndex=\"-1\"\n\t\tdojoAttachEvent=\"onmouseenter:_onHover,onmouseleave:_onUnhover,ondijitclick:_onClick\">\n\t<td class=\"dijitReset\" waiRole=\"presentation\">\n\t\t<img src=\"${_blankGif}\" alt=\"\" class=\"dijitMenuItemIcon\" dojoAttachPoint=\"iconNode\">\n\t</td>\n\t<td class=\"dijitReset dijitMenuItemLabel\" colspan=\"2\" dojoAttachPoint=\"containerNode\"></td>\n\t<td class=\"dijitReset dijitMenuItemAccelKey\" style=\"display: none\" dojoAttachPoint=\"accelKeyNode\"></td>\n\t<td class=\"dijitReset dijitMenuArrowCell\" waiRole=\"presentation\">\n\t\t<div dojoAttachPoint=\"arrowWrapper\" style=\"visibility: hidden\">\n\t\t\t<img src=\"${_blankGif}\" alt=\"\" class=\"dijitMenuExpand\">\n\t\t\t<span class=\"dijitMenuExpandA11y\">+</span>\n\t\t</div>\n\t</td>\n</tr>\n"),
        attributeMap:dojo.delegate(dijit._Widget.prototype.attributeMap,{
            label:{
                node:"containerNode",
                type:"innerHTML"
            },
            iconClass:{
                node:"iconNode",
                type:"class"
            }
            }),
        label:"",
        iconClass:"",
        accelKey:"",
        disabled:false,
        _fillContent:function(_1b8){
            if(_1b8&&!("label" in this.params)){
                this.attr("label",_1b8.innerHTML);
            }
            },
        postCreate:function(){
            dojo.setSelectable(this.domNode,false);var _1b9=this.id+"_text";dojo.attr(this.containerNode,"id",_1b9);if(this.accelKeyNode){
                dojo.attr(this.accelKeyNode,"id",this.id+"_accel");_1b9+=" "+this.id+"_accel";
            }dijit.setWaiState(this.domNode,"labelledby",_1b9);
        },
        _onHover:function(){
            dojo.addClass(this.domNode,"dijitMenuItemHover");this.getParent().onItemHover(this);
        },
        _onUnhover:function(){
            dojo.removeClass(this.domNode,"dijitMenuItemHover");this.getParent().onItemUnhover(this);
        },
        _onClick:function(evt){
            this.getParent().onItemClick(this,evt);dojo.stopEvent(evt);
        },
        onClick:function(evt){},
        focus:function(){
            try{
                if(dojo.isIE==8){
                    this.containerNode.focus();
                }dijit.focus(this.focusNode);
            }catch(e){}
            },
        _onFocus:function(){
            this._setSelected(true);this.getParent()._onItemFocus(this);this.inherited(arguments);
        },
        _setSelected:function(_1ba){
            dojo.toggleClass(this.domNode,"dijitMenuItemSelected",_1ba);
        },
        setLabel:function(_1bb){
            dojo.deprecated("dijit.MenuItem.setLabel() is deprecated.  Use attr('label', ...) instead.","","2.0");this.attr("label",_1bb);
        },
        setDisabled:function(_1bc){
            dojo.deprecated("dijit.Menu.setDisabled() is deprecated.  Use attr('disabled', bool) instead.","","2.0");this.attr("disabled",_1bc);
        },
        _setDisabledAttr:function(_1bd){
            this.disabled=_1bd;dojo[_1bd?"addClass":"removeClass"](this.domNode,"dijitMenuItemDisabled");dijit.setWaiState(this.focusNode,"disabled",_1bd?"true":"false");
        },
        _setAccelKeyAttr:function(_1be){
            this.accelKey=_1be;this.accelKeyNode.style.display=_1be?"":"none";this.accelKeyNode.innerHTML=_1be;dojo.attr(this.containerNode,"colSpan",_1be?"1":"2");
        }
        });
}if(!dojo._hasResource["dijit.PopupMenuItem"]){
    dojo._hasResource["dijit.PopupMenuItem"]=true;dojo.provide("dijit.PopupMenuItem");dojo.declare("dijit.PopupMenuItem",dijit.MenuItem,{
        _fillContent:function(){
            if(this.srcNodeRef){
                var _1bf=dojo.query("*",this.srcNodeRef);dijit.PopupMenuItem.superclass._fillContent.call(this,_1bf[0]);this.dropDownContainer=this.srcNodeRef;
            }
            },
        startup:function(){
            if(this._started){
                return;
            }this.inherited(arguments);if(!this.popup){
                var node=dojo.query("[widgetId]",this.dropDownContainer)[0];this.popup=dijit.byNode(node);
            }dojo.body().appendChild(this.popup.domNode);this.popup.domNode.style.display="none";if(this.arrowWrapper){
                dojo.style(this.arrowWrapper,"visibility","");
            }dijit.setWaiState(this.focusNode,"haspopup","true");
        },
        destroyDescendants:function(){
            if(this.popup){
                if(!this.popup._destroyed){
                    this.popup.destroyRecursive();
                }delete this.popup;
            }this.inherited(arguments);
        }
        });
}if(!dojo._hasResource["dijit.CheckedMenuItem"]){
    dojo._hasResource["dijit.CheckedMenuItem"]=true;dojo.provide("dijit.CheckedMenuItem");dojo.declare("dijit.CheckedMenuItem",dijit.MenuItem,{
        templateString:dojo.cache("dijit","templates/CheckedMenuItem.html","<tr class=\"dijitReset dijitMenuItem\" dojoAttachPoint=\"focusNode\" waiRole=\"menuitemcheckbox\" tabIndex=\"-1\"\n\t\tdojoAttachEvent=\"onmouseenter:_onHover,onmouseleave:_onUnhover,ondijitclick:_onClick\">\n\t<td class=\"dijitReset\" waiRole=\"presentation\">\n\t\t<img src=\"${_blankGif}\" alt=\"\" class=\"dijitMenuItemIcon dijitCheckedMenuItemIcon\" dojoAttachPoint=\"iconNode\">\n\t\t<span class=\"dijitCheckedMenuItemIconChar\">&#10003;</span>\n\t</td>\n\t<td class=\"dijitReset dijitMenuItemLabel\" colspan=\"2\" dojoAttachPoint=\"containerNode,labelNode\"></td>\n\t<td class=\"dijitReset dijitMenuItemAccelKey\" style=\"display: none\" dojoAttachPoint=\"accelKeyNode\"></td>\n\t<td class=\"dijitReset dijitMenuArrowCell\" waiRole=\"presentation\">\n\t</td>\n</tr>\n"),
        checked:false,
        _setCheckedAttr:function(_1c0){
            dojo.toggleClass(this.domNode,"dijitCheckedMenuItemChecked",_1c0);dijit.setWaiState(this.domNode,"checked",_1c0);this.checked=_1c0;
        },
        onChange:function(_1c1){},
        _onClick:function(e){
            if(!this.disabled){
                this.attr("checked",!this.checked);this.onChange(this.checked);
            }this.inherited(arguments);
        }
        });
}if(!dojo._hasResource["dijit.MenuSeparator"]){
    dojo._hasResource["dijit.MenuSeparator"]=true;dojo.provide("dijit.MenuSeparator");dojo.declare("dijit.MenuSeparator",[dijit._Widget,dijit._Templated,dijit._Contained],{
        templateString:dojo.cache("dijit","templates/MenuSeparator.html","<tr class=\"dijitMenuSeparator\">\n\t<td colspan=\"4\">\n\t\t<div class=\"dijitMenuSeparatorTop\"></div>\n\t\t<div class=\"dijitMenuSeparatorBottom\"></div>\n\t</td>\n</tr>\n"),
        postCreate:function(){
            dojo.setSelectable(this.domNode,false);
        },
        isFocusable:function(){
            return false;
        }
        });
}if(!dojo._hasResource["dijit.Menu"]){
    dojo._hasResource["dijit.Menu"]=true;dojo.provide("dijit.Menu");dojo.declare("dijit._MenuBase",[dijit._Widget,dijit._Templated,dijit._KeyNavContainer],{
        parentMenu:null,
        popupDelay:500,
        startup:function(){
            if(this._started){
                return;
            }dojo.forEach(this.getChildren(),function(_1c2){
                _1c2.startup();
            });this.startupKeyNavChildren();this.inherited(arguments);
        },
        onExecute:function(){},
        onCancel:function(_1c3){},
        _moveToPopup:function(evt){
            if(this.focusedChild&&this.focusedChild.popup&&!this.focusedChild.disabled){
                this.focusedChild._onClick(evt);
            }else{
                var _1c4=this._getTopMenu();if(_1c4&&_1c4._isMenuBar){
                    _1c4.focusNext();
                }
                }
            },
        _onPopupHover:function(evt){
            if(this.currentPopup&&this.currentPopup._pendingClose_timer){
                var _1c5=this.currentPopup.parentMenu;if(_1c5.focusedChild){
                    _1c5.focusedChild._setSelected(false);
                }_1c5.focusedChild=this.currentPopup.from_item;_1c5.focusedChild._setSelected(true);this._stopPendingCloseTimer(this.currentPopup);
            }
            },
        onItemHover:function(item){
            if(this.isActive){
                this.focusChild(item);if(this.focusedChild.popup&&!this.focusedChild.disabled&&!this.hover_timer){
                    this.hover_timer=setTimeout(dojo.hitch(this,"_openPopup"),this.popupDelay);
                }
                }if(this.focusedChild){
                this.focusChild(item);
            }this._hoveredChild=item;
        },
        _onChildBlur:function(item){
            this._stopPopupTimer();item._setSelected(false);var _1c6=item.popup;if(_1c6){
                this._stopPendingCloseTimer(_1c6);_1c6._pendingClose_timer=setTimeout(function(){
                    _1c6._pendingClose_timer=null;if(_1c6.parentMenu){
                        _1c6.parentMenu.currentPopup=null;
                    }dijit.popup.close(_1c6);
                },this.popupDelay);
            }
            },
        onItemUnhover:function(item){
            if(this.isActive){
                this._stopPopupTimer();
            }if(this._hoveredChild==item){
                this._hoveredChild=null;
            }
            },
        _stopPopupTimer:function(){
            if(this.hover_timer){
                clearTimeout(this.hover_timer);this.hover_timer=null;
            }
            },
        _stopPendingCloseTimer:function(_1c7){
            if(_1c7._pendingClose_timer){
                clearTimeout(_1c7._pendingClose_timer);_1c7._pendingClose_timer=null;
            }
            },
        _stopFocusTimer:function(){
            if(this._focus_timer){
                clearTimeout(this._focus_timer);this._focus_timer=null;
            }
            },
        _getTopMenu:function(){
            for(var top=this;top.parentMenu;top=top.parentMenu){}return top;
        },
        onItemClick:function(item,evt){
            if(item.disabled){
                return false;
            }if(typeof this.isShowingNow=="undefined"){
                this._markActive();
            }this.focusChild(item);if(item.popup){
                this._openPopup();
            }else{
                this.onExecute();item.onClick(evt);
            }
            },
        _openPopup:function(){
            this._stopPopupTimer();var _1c8=this.focusedChild;if(!_1c8){
                return;
            }var _1c9=_1c8.popup;if(_1c9.isShowingNow){
                return;
            }if(this.currentPopup){
                this._stopPendingCloseTimer(this.currentPopup);dijit.popup.close(this.currentPopup);
            }_1c9.parentMenu=this;_1c9.from_item=_1c8;var self=this;dijit.popup.open({
                parent:this,
                popup:_1c9,
                around:_1c8.domNode,
                orient:this._orient||(this.isLeftToRight()?{
                    "TR":"TL",
                    "TL":"TR",
                    "BR":"BL",
                    "BL":"BR"
                }:{
                    "TL":"TR",
                    "TR":"TL",
                    "BL":"BR",
                    "BR":"BL"
                }),
                onCancel:function(){
                    self.focusChild(_1c8);self._cleanUp();_1c8._setSelected(true);self.focusedChild=_1c8;
                },
                onExecute:dojo.hitch(this,"_cleanUp")
                });this.currentPopup=_1c9;_1c9.connect(_1c9.domNode,"onmouseenter",dojo.hitch(self,"_onPopupHover"));if(_1c9.focus){
                _1c9._focus_timer=setTimeout(dojo.hitch(_1c9,function(){
                    this._focus_timer=null;this.focus();
                }),0);
            }
            },
        _markActive:function(){
            this.isActive=true;dojo.addClass(this.domNode,"dijitMenuActive");dojo.removeClass(this.domNode,"dijitMenuPassive");
        },
        onOpen:function(e){
            this.isShowingNow=true;this._markActive();
        },
        _markInactive:function(){
            this.isActive=false;dojo.removeClass(this.domNode,"dijitMenuActive");dojo.addClass(this.domNode,"dijitMenuPassive");
        },
        onClose:function(){
            this._stopFocusTimer();this._markInactive();this.isShowingNow=false;this.parentMenu=null;
        },
        _closeChild:function(){
            this._stopPopupTimer();if(this.focusedChild){
                this.focusedChild._setSelected(false);this.focusedChild._onUnhover();this.focusedChild=null;
            }if(this.currentPopup){
                dijit.popup.close(this.currentPopup);this.currentPopup=null;
            }
            },
        _onItemFocus:function(item){
            if(this._hoveredChild&&this._hoveredChild!=item){
                this._hoveredChild._onUnhover();
            }
            },
        _onBlur:function(){
            this._cleanUp();this.inherited(arguments);
        },
        _cleanUp:function(){
            this._closeChild();if(typeof this.isShowingNow=="undefined"){
                this._markInactive();
            }
            }
        });dojo.declare("dijit.Menu",dijit._MenuBase,{
        constructor:function(){
            this._bindings=[];
        },
        templateString:dojo.cache("dijit","templates/Menu.html","<table class=\"dijit dijitMenu dijitMenuPassive dijitReset dijitMenuTable\" waiRole=\"menu\" tabIndex=\"${tabIndex}\" dojoAttachEvent=\"onkeypress:_onKeyPress\">\n\t<tbody class=\"dijitReset\" dojoAttachPoint=\"containerNode\"></tbody>\n</table>\n"),
        targetNodeIds:[],
        contextMenuForWindow:false,
        leftClickToOpen:false,
        refocus:true,
        _contextMenuWithMouse:false,
        postCreate:function(){
            if(this.contextMenuForWindow){
                this.bindDomNode(dojo.body());
            }else{
                dojo.forEach(this.targetNodeIds,this.bindDomNode,this);
            }var k=dojo.keys,l=this.isLeftToRight();this._openSubMenuKey=l?k.RIGHT_ARROW:k.LEFT_ARROW;this._closeSubMenuKey=l?k.LEFT_ARROW:k.RIGHT_ARROW;this.connectKeyNavHandlers([k.UP_ARROW],[k.DOWN_ARROW]);
        },
        _onKeyPress:function(evt){
            if(evt.ctrlKey||evt.altKey){
                return;
            }switch(evt.charOrCode){
                case this._openSubMenuKey:this._moveToPopup(evt);dojo.stopEvent(evt);break;case this._closeSubMenuKey:if(this.parentMenu){
                    if(this.parentMenu._isMenuBar){
                        this.parentMenu.focusPrev();
                    }else{
                        this.onCancel(false);
                    }
                    }else{
                    dojo.stopEvent(evt);
                }break;
            }
            },
        _iframeContentWindow:function(_1ca){
            var win=dijit.getDocumentWindow(this._iframeContentDocument(_1ca))||this._iframeContentDocument(_1ca)["__parent__"]||(_1ca.name&&dojo.doc.frames[_1ca.name])||null;return win;
        },
        _iframeContentDocument:function(_1cb){
            var doc=_1cb.contentDocument||(_1cb.contentWindow&&_1cb.contentWindow.document)||(_1cb.name&&dojo.doc.frames[_1cb.name]&&dojo.doc.frames[_1cb.name].document)||null;return doc;
        },
        bindDomNode:function(node){
            node=dojo.byId(node);var cn;if(node.tagName.toLowerCase()=="iframe"){
                var _1cc=node,win=this._iframeContentWindow(_1cc);cn=dojo.withGlobal(win,dojo.body);
            }else{
                cn=(node==dojo.body()?dojo.doc.documentElement:node);
            }var _1cd={
                node:node,
                iframe:_1cc
            };dojo.attr(node,"_dijitMenu"+this.id,this._bindings.push(_1cd));var _1ce=dojo.hitch(this,function(cn){
                return [dojo.connect(cn,(this.leftClickToOpen)?"onclick":"oncontextmenu",this,function(evt){
                    this._openMyself(evt,cn,_1cc);
                }),dojo.connect(cn,"onkeydown",this,"_contextKey"),dojo.connect(cn,"onmousedown",this,"_contextMouse")];
            });_1cd.connects=cn?_1ce(cn):[];if(_1cc){
                _1cd.onloadHandler=dojo.hitch(this,function(){
                    var win=this._iframeContentWindow(_1cc);cn=dojo.withGlobal(win,dojo.body);_1cd.connects=_1ce(cn);
                });if(_1cc.addEventListener){
                    _1cc.addEventListener("load",_1cd.onloadHandler,false);
                }else{
                    _1cc.attachEvent("onload",_1cd.onloadHandler);
                }
                }
            },
        unBindDomNode:function(_1cf){
            var node;try{
                node=dojo.byId(_1cf);
            }catch(e){
                return;
            }var _1d0="_dijitMenu"+this.id;if(node&&dojo.hasAttr(node,_1d0)){
                var bid=dojo.attr(node,_1d0)-1,b=this._bindings[bid];dojo.forEach(b.connects,dojo.disconnect);var _1d1=b.iframe;if(_1d1){
                    if(_1d1.removeEventListener){
                        _1d1.removeEventListener("load",b.onloadHandler,false);
                    }else{
                        _1d1.detachEvent("onload",b.onloadHandler);
                    }
                    }dojo.removeAttr(node,_1d0);delete this._bindings[bid];
            }
            },
        _contextKey:function(e){
            this._contextMenuWithMouse=false;if(e.keyCode==dojo.keys.F10){
                dojo.stopEvent(e);if(e.shiftKey&&e.type=="keydown"){
                    var _1d2={
                        target:e.target,
                        pageX:e.pageX,
                        pageY:e.pageY
                        };_1d2.preventDefault=_1d2.stopPropagation=function(){};window.setTimeout(dojo.hitch(this,function(){
                        this._openMyself(_1d2);
                    }),1);
                }
                }
            },
        _contextMouse:function(e){
            this._contextMenuWithMouse=true;
        },
        _openMyself:function(e,node,_1d3){
            if(this.leftClickToOpen&&e.button>0){
                return;
            }dojo.stopEvent(e);var x,y;if(dojo.isSafari||this._contextMenuWithMouse){
                x=e.pageX;y=e.pageY;if(_1d3){
                    var od=e.target.ownerDocument,ifc=dojo.position(_1d3,true),win=this._iframeContentWindow(_1d3),_1d4=dojo.withGlobal(win,"_docScroll",dojo);var cs=dojo.getComputedStyle(_1d3),tp=dojo._toPixelValue,left=(dojo.isIE&&dojo.isQuirks?0:tp(_1d3,cs.paddingLeft))+(dojo.isIE&&dojo.isQuirks?tp(_1d3,cs.borderLeftWidth):0),top=(dojo.isIE&&dojo.isQuirks?0:tp(_1d3,cs.paddingTop))+(dojo.isIE&&dojo.isQuirks?tp(_1d3,cs.borderTopWidth):0);x+=ifc.x+left-_1d4.x;y+=ifc.y+top-_1d4.y;
                }
                }else{
                var _1d5=dojo.position(e.target,true);x=_1d5.x+10;y=_1d5.y+10;
            }var self=this;var _1d6=dijit.getFocus(this);function _1d7(){
                if(self.refocus){
                    dijit.focus(_1d6);
                }dijit.popup.close(self);
            };dijit.popup.open({
                popup:this,
                x:x,
                y:y,
                onExecute:_1d7,
                onCancel:_1d7,
                orient:this.isLeftToRight()?"L":"R"
                });this.focus();this._onBlur=function(){
                this.inherited("_onBlur",arguments);dijit.popup.close(this);
            };
        },
        uninitialize:function(){
            dojo.forEach(this._bindings,function(b){
                if(b){
                    this.unBindDomNode(b.node);
                }
                },this);this.inherited(arguments);
        }
        });
}if(!dojo._hasResource["dojox.html.metrics"]){
    dojo._hasResource["dojox.html.metrics"]=true;dojo.provide("dojox.html.metrics");(function(){
        var dhm=dojox.html.metrics;dhm.getFontMeasurements=function(){
            var _1d8={
                "1em":0,
                "1ex":0,
                "100%":0,
                "12pt":0,
                "16px":0,
                "xx-small":0,
                "x-small":0,
                "small":0,
                "medium":0,
                "large":0,
                "x-large":0,
                "xx-large":0
            };if(dojo.isIE){
                dojo.doc.documentElement.style.fontSize="100%";
            }var div=dojo.doc.createElement("div");var ds=div.style;ds.position="absolute";ds.left="-100px";ds.top="0";ds.width="30px";ds.height="1000em";ds.border="0";ds.margin="0";ds.padding="0";ds.outline="0";ds.lineHeight="1";ds.overflow="hidden";dojo.body().appendChild(div);for(var p in _1d8){
                ds.fontSize=p;_1d8[p]=Math.round(div.offsetHeight*12/16)*16/12/1000;
            }dojo.body().removeChild(div);div=null;return _1d8;
        };var _1d9=null;dhm.getCachedFontMeasurements=function(_1da){
            if(_1da||!_1d9){
                _1d9=dhm.getFontMeasurements();
            }return _1d9;
        };var _1db=null,_1dc={};dhm.getTextBox=function(text,_1dd,_1de){
            var m;if(!_1db){
                m=_1db=dojo.doc.createElement("div");m.style.position="absolute";m.style.left="-10000px";m.style.top="0";dojo.body().appendChild(m);
            }else{
                m=_1db;
            }m.className="";m.style.border="0";m.style.margin="0";m.style.padding="0";m.style.outline="0";if(arguments.length>1&&_1dd){
                for(var i in _1dd){
                    if(i in _1dc){
                        continue;
                    }m.style[i]=_1dd[i];
                }
                }if(arguments.length>2&&_1de){
                m.className=_1de;
            }m.innerHTML=text;return dojo.marginBox(m);
        };var _1df={
            w:16,
            h:16
        };dhm.getScrollbar=function(){
            return {
                w:_1df.w,
                h:_1df.h
                };
        };dhm._fontResizeNode=null;dhm.initOnFontResize=function(_1e0){
            var f=dhm._fontResizeNode=dojo.doc.createElement("iframe");var fs=f.style;fs.position="absolute";fs.width="5em";fs.height="10em";fs.top="-10000px";if(dojo.isIE){
                f.onreadystatechange=function(){
                    if(f.contentWindow.document.readyState=="complete"){
                        f.onresize=f.contentWindow.parent[dojox._scopeName].html.metrics._fontresize;
                    }
                    };
            }else{
                f.onload=function(){
                    f.contentWindow.onresize=f.contentWindow.parent[dojox._scopeName].html.metrics._fontresize;
                };
            }f.setAttribute("src","javascript:'<html><head><script>if(\"loadFirebugConsole\" in window){window.loadFirebugConsole();}</script></head><body></body></html>'");dojo.body().appendChild(f);dhm.initOnFontResize=function(){};
        };dhm.onFontResize=function(){};dhm._fontresize=function(){
            dhm.onFontResize();
        };dojo.addOnUnload(function(){
            var f=dhm._fontResizeNode;if(f){
                if(dojo.isIE&&f.onresize){
                    f.onresize=null;
                }else{
                    if(f.contentWindow&&f.contentWindow.onresize){
                        f.contentWindow.onresize=null;
                    }
                    }dhm._fontResizeNode=null;
            }
            });dojo.addOnLoad(function(){
            try{
                var n=dojo.doc.createElement("div");n.style.cssText="top:0;left:0;width:100px;height:100px;overflow:scroll;position:absolute;visibility:hidden;";dojo.body().appendChild(n);_1df.w=n.offsetWidth-n.clientWidth;_1df.h=n.offsetHeight-n.clientHeight;dojo.body().removeChild(n);delete n;
            }catch(e){}if("fontSizeWatch" in dojo.config&&!!dojo.config.fontSizeWatch){
                dhm.initOnFontResize();
            }
            });
    })();
}if(!dojo._hasResource["dojox.grid.util"]){
    dojo._hasResource["dojox.grid.util"]=true;dojo.provide("dojox.grid.util");(function(){
        var dgu=dojox.grid.util;dgu.na="...";dgu.rowIndexTag="gridRowIndex";dgu.gridViewTag="gridView";dgu.fire=function(ob,ev,args){
            var fn=ob&&ev&&ob[ev];return fn&&(args?fn.apply(ob,args):ob[ev]());
        };dgu.setStyleHeightPx=function(_1e1,_1e2){
            if(_1e2>=0){
                var s=_1e1.style;var v=_1e2+"px";if(_1e1&&s["height"]!=v){
                    s["height"]=v;
                }
                }
            };dgu.mouseEvents=["mouseover","mouseout","mousedown","mouseup","click","dblclick","contextmenu"];dgu.keyEvents=["keyup","keydown","keypress"];dgu.funnelEvents=function(_1e3,_1e4,_1e5,_1e6){
            var evts=(_1e6?_1e6:dgu.mouseEvents.concat(dgu.keyEvents));for(var i=0,l=evts.length;i<l;i++){
                _1e4.connect(_1e3,"on"+evts[i],_1e5);
            }
            };dgu.removeNode=function(_1e7){
            _1e7=dojo.byId(_1e7);_1e7&&_1e7.parentNode&&_1e7.parentNode.removeChild(_1e7);return _1e7;
        };dgu.arrayCompare=function(inA,inB){
            for(var i=0,l=inA.length;i<l;i++){
                if(inA[i]!=inB[i]){
                    return false;
                }
                }return (inA.length==inB.length);
        };dgu.arrayInsert=function(_1e8,_1e9,_1ea){
            if(_1e8.length<=_1e9){
                _1e8[_1e9]=_1ea;
            }else{
                _1e8.splice(_1e9,0,_1ea);
            }
            };dgu.arrayRemove=function(_1eb,_1ec){
            _1eb.splice(_1ec,1);
        };dgu.arraySwap=function(_1ed,inI,inJ){
            var _1ee=_1ed[inI];_1ed[inI]=_1ed[inJ];_1ed[inJ]=_1ee;
        };
    })();
}if(!dojo._hasResource["dojox.grid._Scroller"]){
    dojo._hasResource["dojox.grid._Scroller"]=true;dojo.provide("dojox.grid._Scroller");(function(){
        var _1ef=function(_1f0){
            var i=0,n,p=_1f0.parentNode;while((n=p.childNodes[i++])){
                if(n==_1f0){
                    return i-1;
                }
                }return -1;
        };var _1f1=function(_1f2){
            if(!_1f2){
                return;
            }var _1f3=function(inW){
                return inW.domNode&&dojo.isDescendant(inW.domNode,_1f2,true);
            };var ws=dijit.registry.filter(_1f3);for(var i=0,w;(w=ws[i]);i++){
                w.destroy();
            }delete ws;
        };var _1f4=function(_1f5){
            var node=dojo.byId(_1f5);return (node&&node.tagName?node.tagName.toLowerCase():"");
        };var _1f6=function(_1f7,_1f8){
            var _1f9=[];var i=0,n;while((n=_1f7.childNodes[i])){
                i++;if(_1f4(n)==_1f8){
                    _1f9.push(n);
                }
                }return _1f9;
        };var _1fa=function(_1fb){
            return _1f6(_1fb,"div");
        };dojo.declare("dojox.grid._Scroller",null,{
            constructor:function(_1fc){
                this.setContentNodes(_1fc);this.pageHeights=[];this.pageNodes=[];this.stack=[];
            },
            rowCount:0,
            defaultRowHeight:32,
            keepRows:100,
            contentNode:null,
            scrollboxNode:null,
            defaultPageHeight:0,
            keepPages:10,
            pageCount:0,
            windowHeight:0,
            firstVisibleRow:0,
            lastVisibleRow:0,
            averageRowHeight:0,
            page:0,
            pageTop:0,
            init:function(_1fd,_1fe,_1ff){
                switch(arguments.length){
                    case 3:this.rowsPerPage=_1ff;case 2:this.keepRows=_1fe;case 1:this.rowCount=_1fd;default:break;
                }this.defaultPageHeight=this.defaultRowHeight*this.rowsPerPage;this.pageCount=this._getPageCount(this.rowCount,this.rowsPerPage);this.setKeepInfo(this.keepRows);this.invalidate();if(this.scrollboxNode){
                    this.scrollboxNode.scrollTop=0;this.scroll(0);this.scrollboxNode.onscroll=dojo.hitch(this,"onscroll");
                }
                },
            _getPageCount:function(_200,_201){
                return _200?(Math.ceil(_200/_201)||1):0;
            },
            destroy:function(){
                this.invalidateNodes();delete this.contentNodes;delete this.contentNode;delete this.scrollboxNode;
            },
            setKeepInfo:function(_202){
                this.keepRows=_202;this.keepPages=!this.keepRows?this.keepPages:Math.max(Math.ceil(this.keepRows/this.rowsPerPage),2);
            },
            setContentNodes:function(_203){
                this.contentNodes=_203;this.colCount=(this.contentNodes?this.contentNodes.length:0);this.pageNodes=[];for(var i=0;i<this.colCount;i++){
                    this.pageNodes[i]=[];
                }
                },
            getDefaultNodes:function(){
                return this.pageNodes[0]||[];
            },
            invalidate:function(){
                this._invalidating=true;this.invalidateNodes();this.pageHeights=[];this.height=(this.pageCount?(this.pageCount-1)*this.defaultPageHeight+this.calcLastPageHeight():0);this.resize();this._invalidating=false;
            },
            updateRowCount:function(_204){
                this.invalidateNodes();this.rowCount=_204;var _205=this.pageCount;if(_205===0){
                    this.height=1;
                }this.pageCount=this._getPageCount(this.rowCount,this.rowsPerPage);if(this.pageCount<_205){
                    for(var i=_205-1;i>=this.pageCount;i--){
                        this.height-=this.getPageHeight(i);delete this.pageHeights[i];
                    }
                    }else{
                    if(this.pageCount>_205){
                        this.height+=this.defaultPageHeight*(this.pageCount-_205-1)+this.calcLastPageHeight();
                    }
                    }this.resize();
            },
            pageExists:function(_206){
                return Boolean(this.getDefaultPageNode(_206));
            },
            measurePage:function(_207){
                if(this.grid.rowHeight){
                    var _208=this.grid.rowHeight+1;return ((_207+1)*this.rowsPerPage>this.rowCount?this.rowCount-_207*this.rowsPerPage:this.rowsPerPage)*_208;
                }var n=this.getDefaultPageNode(_207);return (n&&n.innerHTML)?n.offsetHeight:undefined;
            },
            positionPage:function(_209,_20a){
                for(var i=0;i<this.colCount;i++){
                    this.pageNodes[i][_209].style.top=_20a+"px";
                }
                },
            repositionPages:function(_20b){
                var _20c=this.getDefaultNodes();var last=0;for(var i=0;i<this.stack.length;i++){
                    last=Math.max(this.stack[i],last);
                }var n=_20c[_20b];var y=(n?this.getPageNodePosition(n)+this.getPageHeight(_20b):0);for(var p=_20b+1;p<=last;p++){
                    n=_20c[p];if(n){
                        if(this.getPageNodePosition(n)==y){
                            return;
                        }this.positionPage(p,y);
                    }y+=this.getPageHeight(p);
                }
                },
            installPage:function(_20d){
                for(var i=0;i<this.colCount;i++){
                    this.contentNodes[i].appendChild(this.pageNodes[i][_20d]);
                }
                },
            preparePage:function(_20e,_20f){
                var p=(_20f?this.popPage():null);for(var i=0;i<this.colCount;i++){
                    var _210=this.pageNodes[i];var _211=(p===null?this.createPageNode():this.invalidatePageNode(p,_210));_211.pageIndex=_20e;_210[_20e]=_211;
                }
                },
            renderPage:function(_212){
                var _213=[];var i,j;for(i=0;i<this.colCount;i++){
                    _213[i]=this.pageNodes[i][_212];
                }for(i=0,j=_212*this.rowsPerPage;(i<this.rowsPerPage)&&(j<this.rowCount);i++,j++){
                    this.renderRow(j,_213);
                }
                },
            removePage:function(_214){
                for(var i=0,j=_214*this.rowsPerPage;i<this.rowsPerPage;i++,j++){
                    this.removeRow(j);
                }
                },
            destroyPage:function(_215){
                for(var i=0;i<this.colCount;i++){
                    var n=this.invalidatePageNode(_215,this.pageNodes[i]);if(n){
                        dojo.destroy(n);
                    }
                    }
                },
            pacify:function(_216){},
            pacifying:false,
            pacifyTicks:200,
            setPacifying:function(_217){
                if(this.pacifying!=_217){
                    this.pacifying=_217;this.pacify(this.pacifying);
                }
                },
            startPacify:function(){
                this.startPacifyTicks=new Date().getTime();
            },
            doPacify:function(){
                var _218=(new Date().getTime()-this.startPacifyTicks)>this.pacifyTicks;this.setPacifying(true);this.startPacify();return _218;
            },
            endPacify:function(){
                this.setPacifying(false);
            },
            resize:function(){
                if(this.scrollboxNode){
                    this.windowHeight=this.scrollboxNode.clientHeight;
                }for(var i=0;i<this.colCount;i++){
                    dojox.grid.util.setStyleHeightPx(this.contentNodes[i],Math.max(1,this.height));
                }var _219=(!this._invalidating);if(!_219){
                    var ah=this.grid.attr("autoHeight");if(typeof ah=="number"&&ah<=Math.min(this.rowsPerPage,this.rowCount)){
                        _219=true;
                    }
                    }if(_219){
                    this.needPage(this.page,this.pageTop);
                }var _21a=(this.page<this.pageCount-1)?this.rowsPerPage:((this.rowCount%this.rowsPerPage)||this.rowsPerPage);var _21b=this.getPageHeight(this.page);this.averageRowHeight=(_21b>0&&_21a>0)?(_21b/_21a):0;
            },
            calcLastPageHeight:function(){
                if(!this.pageCount){
                    return 0;
                }var _21c=this.pageCount-1;var _21d=((this.rowCount%this.rowsPerPage)||(this.rowsPerPage))*this.defaultRowHeight;this.pageHeights[_21c]=_21d;return _21d;
            },
            updateContentHeight:function(inDh){
                this.height+=inDh;this.resize();
            },
            updatePageHeight:function(_21e,_21f){
                if(this.pageExists(_21e)){
                    var oh=this.getPageHeight(_21e);var h=(this.measurePage(_21e));if(h===undefined){
                        h=oh;
                    }this.pageHeights[_21e]=h;if(oh!=h){
                        this.updateContentHeight(h-oh);var ah=this.grid.attr("autoHeight");if((typeof ah=="number"&&ah>this.rowCount)||(ah===true&&!_21f)){
                            this.grid.sizeChange();
                        }else{
                            this.repositionPages(_21e);
                        }
                        }return h;
                }return 0;
            },
            rowHeightChanged:function(_220){
                this.updatePageHeight(Math.floor(_220/this.rowsPerPage),false);
            },
            invalidateNodes:function(){
                while(this.stack.length){
                    this.destroyPage(this.popPage());
                }
                },
            createPageNode:function(){
                var p=document.createElement("div");dojo.attr(p,"role","presentation");p.style.position="absolute";p.style[dojo._isBodyLtr()?"left":"right"]="0";return p;
            },
            getPageHeight:function(_221){
                var ph=this.pageHeights[_221];return (ph!==undefined?ph:this.defaultPageHeight);
            },
            pushPage:function(_222){
                return this.stack.push(_222);
            },
            popPage:function(){
                return this.stack.shift();
            },
            findPage:function(_223){
                var i=0,h=0;for(var ph=0;i<this.pageCount;i++,h+=ph){
                    ph=this.getPageHeight(i);if(h+ph>=_223){
                        break;
                    }
                    }this.page=i;this.pageTop=h;
            },
            buildPage:function(_224,_225,_226){
                this.preparePage(_224,_225);this.positionPage(_224,_226);this.installPage(_224);this.renderPage(_224);this.pushPage(_224);
            },
            needPage:function(_227,_228){
                var h=this.getPageHeight(_227),oh=h;if(!this.pageExists(_227)){
                    this.buildPage(_227,this.keepPages&&(this.stack.length>=this.keepPages),_228);h=this.updatePageHeight(_227,true);
                }else{
                    this.positionPage(_227,_228);
                }return h;
            },
            onscroll:function(){
                this.scroll(this.scrollboxNode.scrollTop);
            },
            scroll:function(_229){
                this.grid.scrollTop=_229;if(this.colCount){
                    this.startPacify();this.findPage(_229);var h=this.height;var b=this.getScrollBottom(_229);for(var p=this.page,y=this.pageTop;(p<this.pageCount)&&((b<0)||(y<b));p++){
                        y+=this.needPage(p,y);
                    }this.firstVisibleRow=this.getFirstVisibleRow(this.page,this.pageTop,_229);this.lastVisibleRow=this.getLastVisibleRow(p-1,y,b);if(h!=this.height){
                        this.repositionPages(p-1);
                    }this.endPacify();
                }
                },
            getScrollBottom:function(_22a){
                return (this.windowHeight>=0?_22a+this.windowHeight:-1);
            },
            processNodeEvent:function(e,_22b){
                var t=e.target;while(t&&(t!=_22b)&&t.parentNode&&(t.parentNode.parentNode!=_22b)){
                    t=t.parentNode;
                }if(!t||!t.parentNode||(t.parentNode.parentNode!=_22b)){
                    return false;
                }var page=t.parentNode;e.topRowIndex=page.pageIndex*this.rowsPerPage;e.rowIndex=e.topRowIndex+_1ef(t);e.rowTarget=t;return true;
            },
            processEvent:function(e){
                return this.processNodeEvent(e,this.contentNode);
            },
            renderRow:function(_22c,_22d){},
            removeRow:function(_22e){},
            getDefaultPageNode:function(_22f){
                return this.getDefaultNodes()[_22f];
            },
            positionPageNode:function(_230,_231){},
            getPageNodePosition:function(_232){
                return _232.offsetTop;
            },
            invalidatePageNode:function(_233,_234){
                var p=_234[_233];if(p){
                    delete _234[_233];this.removePage(_233,p);_1f1(p);p.innerHTML="";
                }return p;
            },
            getPageRow:function(_235){
                return _235*this.rowsPerPage;
            },
            getLastPageRow:function(_236){
                return Math.min(this.rowCount,this.getPageRow(_236+1))-1;
            },
            getFirstVisibleRow:function(_237,_238,_239){
                if(!this.pageExists(_237)){
                    return 0;
                }var row=this.getPageRow(_237);var _23a=this.getDefaultNodes();var rows=_1fa(_23a[_237]);for(var i=0,l=rows.length;i<l&&_238<_239;i++,row++){
                    _238+=rows[i].offsetHeight;
                }return (row?row-1:row);
            },
            getLastVisibleRow:function(_23b,_23c,_23d){
                if(!this.pageExists(_23b)){
                    return 0;
                }var _23e=this.getDefaultNodes();var row=this.getLastPageRow(_23b);var rows=_1fa(_23e[_23b]);for(var i=rows.length-1;i>=0&&_23c>_23d;i--,row--){
                    _23c-=rows[i].offsetHeight;
                }return row+1;
            },
            findTopRow:function(_23f){
                var _240=this.getDefaultNodes();var rows=_1fa(_240[this.page]);for(var i=0,l=rows.length,t=this.pageTop,h;i<l;i++){
                    h=rows[i].offsetHeight;t+=h;if(t>=_23f){
                        this.offset=h-(t-_23f);return i+this.page*this.rowsPerPage;
                    }
                    }return -1;
            },
            findScrollTop:function(_241){
                var _242=Math.floor(_241/this.rowsPerPage);var t=0;var i,l;for(i=0;i<_242;i++){
                    t+=this.getPageHeight(i);
                }this.pageTop=t;this.needPage(_242,this.pageTop);var _243=this.getDefaultNodes();var rows=_1fa(_243[_242]);var r=_241-this.rowsPerPage*_242;for(i=0,l=rows.length;i<l&&i<r;i++){
                    t+=rows[i].offsetHeight;
                }return t;
            },
            dummy:0
        });
    })();
}if(!dojo._hasResource["dojox.grid.cells._base"]){
    dojo._hasResource["dojox.grid.cells._base"]=true;dojo.provide("dojox.grid.cells._base");dojo.declare("dojox.grid._DeferredTextWidget",dijit._Widget,{
        deferred:null,
        _destroyOnRemove:true,
        postCreate:function(){
            if(this.deferred){
                this.deferred.addBoth(dojo.hitch(this,function(text){
                    if(this.domNode){
                        this.domNode.innerHTML=text;
                    }
                    }));
            }
            }
        });(function(){
        var _244=function(_245){
            try{
                dojox.grid.util.fire(_245,"focus");dojox.grid.util.fire(_245,"select");
            }catch(e){}
            };var _246=function(){
            setTimeout(dojo.hitch.apply(dojo,arguments),0);
        };var dgc=dojox.grid.cells;dojo.declare("dojox.grid.cells._Base",null,{
            styles:"",
            classes:"",
            editable:false,
            alwaysEditing:false,
            formatter:null,
            defaultValue:"...",
            value:null,
            hidden:false,
            noresize:false,
            draggable:true,
            _valueProp:"value",
            _formatPending:false,
            constructor:function(_247){
                this._props=_247||{};dojo.mixin(this,_247);if(this.draggable===undefined){
                    this.draggable=true;
                }
                },
            _defaultFormat:function(_248,_249){
                var s=this.grid.formatterScope||this;var f=this.formatter;if(f&&s&&typeof f=="string"){
                    f=this.formatter=s[f];
                }var v=(_248!=this.defaultValue&&f)?f.apply(s,_249):_248;if(typeof v=="undefined"){
                    return this.defaultValue;
                }if(v&&v.addBoth){
                    v=new dojox.grid._DeferredTextWidget({
                        deferred:v
                    },dojo.create("span",{
                        innerHTML:this.defaultValue
                        }));
                }if(v&&v.declaredClass&&v.startup){
                    return "<div class='dojoxGridStubNode' linkWidget='"+v.id+"' cellIdx='"+this.index+"'>"+this.defaultValue+"</div>";
                }
//                [duongtb: cut when cell's data is too long
           if(this.wrapping !== true && this.formatter == undefined){
                            var cellWidth = this.getHeaderNode().offsetWidth;
                            //for measuring string in pixel
                            try{
                                var ruler = dojo.byId("ruler");
                                if(ruler==null){
                                    ruler = document.createElement("span");
                                    ruler.id = "ruler";
                                    ruler.style.visibility = "hidden";
                                    ruler.className = "dojoxGridCell";
                                    try{
                                        document.body.appendChild(ruler);
                                    }catch(e){
                                        document.getElementsByTagName("body")[0].appendChild(ruler);
                                    }
                                }
                                ruler.innerHTML = v;
                                var contentWidth = ruler.offsetWidth;
                                if(contentWidth > cellWidth){
                                    var len = Math.round(cellWidth/14);//W
                                    var temp = v.substr(0,len);
                                    var vtemp = v;
                                    var i = 0;
                                    contentWidth = 0;
                                    while(contentWidth<cellWidth){
                                        v = temp;
                                        temp = vtemp.substr(0,len + i);
                                        ruler.innerHTML = temp + "...";
                                        contentWidth = ruler.offsetWidth;
                                        i+=3;
                                    }
                                    v += "...";
                                }
                            }catch(e){
                                // if ruler has not been created

                            }
                        }
//                ]
                return v;
            },
            format:function(_24a,_24b){
                var f,i=this.grid.edit.info,d=this.get?this.get(_24a,_24b):(this.value||this.defaultValue);d=(d&&d.replace&&this.grid.escapeHTMLInData)?d.replace(/&/g,"&amp;").replace(/</g,"&lt;"):d;if(this.editable&&(this.alwaysEditing||(i.rowIndex==_24a&&i.cell==this))){
                    return this.formatEditing(d,_24a);
                }else{
                    return this._defaultFormat(d,[d,_24a,this]);
                }
                },
            formatEditing:function(_24c,_24d){},
            getNode:function(_24e){
                return this.view.getCellNode(_24e,this.index);
            },
            getHeaderNode:function(){
                return this.view.getHeaderCellNode(this.index);
            },
            getEditNode:function(_24f){
                return (this.getNode(_24f)||0).firstChild||0;
            },
            canResize:function(){
                var uw=this.unitWidth;return uw&&(uw!=="auto");
            },
            isFlex:function(){
                var uw=this.unitWidth;return uw&&dojo.isString(uw)&&(uw=="auto"||uw.slice(-1)=="%");
            },
            applyEdit:function(_250,_251){
                this.grid.edit.applyCellEdit(_250,this,_251);
            },
            cancelEdit:function(_252){
                this.grid.doCancelEdit(_252);
            },
            _onEditBlur:function(_253){
                if(this.grid.edit.isEditCell(_253,this.index)){
                    this.grid.edit.apply();
                }
                },
            registerOnBlur:function(_254,_255){
                if(this.commitOnBlur){
                    dojo.connect(_254,"onblur",function(e){
                        setTimeout(dojo.hitch(this,"_onEditBlur",_255),250);
                    });
                }
                },
            needFormatNode:function(_256,_257){
                this._formatPending=true;_246(this,"_formatNode",_256,_257);
            },
            cancelFormatNode:function(){
                this._formatPending=false;
            },
            _formatNode:function(_258,_259){
                if(this._formatPending){
                    this._formatPending=false;dojo.setSelectable(this.grid.domNode,true);this.formatNode(this.getEditNode(_259),_258,_259);
                }
                },
            formatNode:function(_25a,_25b,_25c){
                if(dojo.isIE){
                    _246(this,"focus",_25c,_25a);
                }else{
                    this.focus(_25c,_25a);
                }
                },
            dispatchEvent:function(m,e){
                if(m in this){
                    return this[m](e);
                }
                },
            getValue:function(_25d){
                return this.getEditNode(_25d)[this._valueProp];
            },
            setValue:function(_25e,_25f){
                var n=this.getEditNode(_25e);if(n){
                    n[this._valueProp]=_25f;
                }
                },
            focus:function(_260,_261){
                _244(_261||this.getEditNode(_260));
            },
            save:function(_262){
                this.value=this.value||this.getValue(_262);
            },
            restore:function(_263){
                this.setValue(_263,this.value);
            },
            _finish:function(_264){
                dojo.setSelectable(this.grid.domNode,false);this.cancelFormatNode();
            },
            apply:function(_265){
                this.applyEdit(this.getValue(_265),_265);this._finish(_265);
            },
            cancel:function(_266){
                this.cancelEdit(_266);this._finish(_266);
            }
            });dgc._Base.markupFactory=function(node,_267){
            var d=dojo;var _268=d.trim(d.attr(node,"formatter")||"");if(_268){
                _267.formatter=dojo.getObject(_268)||_268;
            }var get=d.trim(d.attr(node,"get")||"");if(get){
                _267.get=dojo.getObject(get);
            }var _269=function(attr,cell,_26a){
                var _26b=d.trim(d.attr(node,attr)||"");if(_26b){
                    cell[_26a||attr]=!(_26b.toLowerCase()=="false");
                }
                };_269("sortDesc",_267);_269("editable",_267);_269("alwaysEditing",_267);_269("noresize",_267);_269("draggable",_267);var _26c=d.trim(d.attr(node,"loadingText")||d.attr(node,"defaultValue")||"");if(_26c){
                _267.defaultValue=_26c;
            }var _26d=function(attr,cell,_26e){
                var _26f=d.trim(d.attr(node,attr)||"")||undefined;if(_26f){
                    cell[_26e||attr]=_26f;
                }
                };_26d("styles",_267);_26d("headerStyles",_267);_26d("cellStyles",_267);_26d("classes",_267);_26d("headerClasses",_267);_26d("cellClasses",_267);
        };dojo.declare("dojox.grid.cells.Cell",dgc._Base,{
            constructor:function(){
                this.keyFilter=this.keyFilter;
            },
            keyFilter:null,
            formatEditing:function(_270,_271){
                this.needFormatNode(_270,_271);
//                return "<input class=\"dojoxGridInput\" type=\"text\" value=\""+_270+"\">";
//                [duongtb:
                return "<input type=\"text\" style=\"border:1px solid;border-color:blue;height:" + this.getHeaderNode().offsetHeight +
                "px;width:" + dojo.contentBox(this.getHeaderNode()).w + "px;\" value=\""+_270+"\" />";
//                ]
            },
            formatNode:function(_272,_273,_274){
                this.inherited(arguments);this.registerOnBlur(_272,_274);
            },
            doKey:function(e){
                if(this.keyFilter){
                    var key=String.fromCharCode(e.charCode);if(key.search(this.keyFilter)==-1){
                        dojo.stopEvent(e);
                    }
                    }
                },
            _finish:function(_275){
                this.inherited(arguments);var n=this.getEditNode(_275);try{
                    dojox.grid.util.fire(n,"blur");
                }catch(e){}
                }
            });dgc.Cell.markupFactory=function(node,_276){
            dgc._Base.markupFactory(node,_276);var d=dojo;var _277=d.trim(d.attr(node,"keyFilter")||"");if(_277){
                _276.keyFilter=new RegExp(_277);
            }
            };dojo.declare("dojox.grid.cells.RowIndex",dgc.Cell,{
            name:"Row",
            postscript:function(){
                this.editable=false;
            },
            get:function(_278){
                return _278+1;
            }
            });dgc.RowIndex.markupFactory=function(node,_279){
            dgc.Cell.markupFactory(node,_279);
        };dojo.declare("dojox.grid.cells.Select",dgc.Cell,{
            options:null,
            values:null,
            returnIndex:-1,
            constructor:function(_27a){
                this.values=this.values||this.options;
            },
            formatEditing:function(_27b,_27c){
                this.needFormatNode(_27b,_27c);
//                var h=["<select class=\"dojoxGridSelect\">"];
                var h=["<select style=\"border:1px solid;background-color:#E0ECF8;border-color:#8181F7;\">"];
                for(var i=0,o,v;((o=this.options[i])!==undefined)&&((v=this.values[i])!==undefined);i++){
                    h.push("<option",(_27b==v?" selected":"")," value=\""+v+"\"",">",o,"</option>");
                }h.push("</select>");return h.join("");
            },
            getValue:function(_27d){
                var n=this.getEditNode(_27d);if(n){
                    var i=n.selectedIndex,o=n.options[i];return this.returnIndex>-1?i:o.value||o.innerHTML;
                }
                },
                //[duongtb:for fixing when setting alwaysEditing
              dochange:function(e){
                   var el = this.getEditNode(e.rowIndex);
                   var val = el.options[el.selectedIndex].value;
//                  var item = this.grid.getItem(e.rowIndex);
//                  var msg = e.cell.get ? e.cell.get(e.rowIndex, item) : (e.cell.value || e.cell.defaultValue);
                   try{
                      this.grid.doApplyCellEdit(val,e.rowIndex,e.cell.field);
                   }catch(ex){
                      console.log(ex.message);
                      this.grid.edit.applyCellEdit(val, this, e.rowIndex);
                   }

               }
               //end duongtb]
            });dgc.Select.markupFactory=function(node,cell){
            dgc.Cell.markupFactory(node,cell);var d=dojo;var _27e=d.trim(d.attr(node,"options")||"");if(_27e){
                var o=_27e.split(",");if(o[0]!=_27e){
                    cell.options=o;
                }
                }var _27f=d.trim(d.attr(node,"values")||"");if(_27f){
                var v=_27f.split(",");if(v[0]!=_27f){
                    cell.values=v;
                }
                }
            };dojo.declare("dojox.grid.cells.AlwaysEdit",dgc.Cell,{
            alwaysEditing:true,
            _formatNode:function(_280,_281){
                this.formatNode(this.getEditNode(_281),_280,_281);
            },
            applyStaticValue:function(_282){
                var e=this.grid.edit;e.applyCellEdit(this.getValue(_282),this,_282);e.start(this,_282,true);
            }
            });dgc.AlwaysEdit.markupFactory=function(node,cell){
            dgc.Cell.markupFactory(node,cell);
        };
        dojo.declare("dojox.grid.cells.Bool",dgc.AlwaysEdit,{
            _valueProp:"checked",
            formatEditing:function(_283,_284){
                return "<input class=\"dojoxGridInput\" type=\"checkbox\""+(_283?" checked=\"checked\"":"")+" style=\"width: auto\" />";
            },
            doclick:function(e){
                if(e.target.tagName=="INPUT"){
                    this.applyStaticValue(e.rowIndex);
                }
                }
            });dgc.Bool.markupFactory=function(node,cell){
            dgc.AlwaysEdit.markupFactory(node,cell);
        };
    //        [duongtb: define some types of grid cell
            dojo.declare("dojox.grid.cells.Text", dgc.AlwaysEdit, {
		formatEditing: function(inDatum, inRowIndex){
			 return "<input type=\"text\" style=\"border:1px solid;border-color:blue;height:" + this.getHeaderNode().offsetHeight +
                "px;width:" + dojo.contentBox(this.getHeaderNode()).w + "px;\" value=\""+inDatum+"\" />";
		},
		doclick: function(e){
                    var ez = this.grid.edit;
                    if(!this.grid.focus.isFocusCell(e.cell, e.rowIndex) && !ez.isEditCell(e.rowIndex)){
                        ez.setEditCell(e.cell, e.rowIndex);
			ez.grid.focus.focusGrid();
                        this.grid.focus.setFocusCell(e.cell,e.rowIndex);
			if(e.target.tagName == 'INPUT'){
                                dojo.setSelectable(this.grid.domNode, true);
                                this.grid.edit._focusEditor(e.cell, e.rowIndex);
                                this.grid.focus._focusifyCellNode(true);
			}
                    }
                    else if(this.grid.focus.isFocusCell(e.cell, e.rowIndex) && !ez.isEditCell(e.rowIndex) && !ez.isEditing()){
                        ez.setEditCell(e.cell, e.rowIndex);
			ez.grid.focus.focusGrid();
                        this.grid.focus.setFocusCell(e.cell,e.rowIndex);
			if(e.target.tagName == 'INPUT'){
                                dojo.setSelectable(this.grid.domNode, true);
                                this.grid.edit._focusEditor(e.cell, e.rowIndex);
                                this.grid.focus._focusifyCellNode(true);
			}
                    }
		},
                dochange:function(e){
                    try{
                          this.applyStaticValue(e.rowIndex);
                    }catch(ex){
                        console.log(ex.message);
                    }
                    // do something
                },
                dokeypress:function(e){
                    var ez = this.grid.edit;
                    if(e.keyCode==13 && ez.isEditCell(e.rowIndex)){//enter key
                      ez.setEditCell(e.cell, e.rowIndex + 1);
                      dojo.setSelectable(this.grid.domNode, true);
                      this.grid.edit._focusEditor(e.cell, e.rowIndex + 1);
                      this.grid.focus._focusifyCellNode(true);
                    }
                    else if(e.keyCode==13 && !ez.isEditCell(e.rowIndex) && ez.isEditing()){
                        dojo.setSelectable(this.grid.domNode, true);
                        this.grid.edit._focusEditor(e.cell, e.rowIndex);
                        this.grid.focus._focusifyCellNode(true);
                    }
                }
	});
	dgc.Text.markupFactory = function(node, cell){
		dgc.AlwaysEdit.markupFactory(node, cell);
	};
         dojo.declare("dojox.grid.cells.vtBool",dgc.AlwaysEdit,{
           _valueProp:"checked",
           copyOf:function(item){
            var o1 = {};
            for(var key in item){
                if(key !== '_RI' && key != '_S' &&
                    key !== '_0' && key !== '_RPM'
                    && key !== 'checked' && key !== '_RS' && key !== '_R'){
                    o1[key] = item[key];
                }
            }
            return o1;
            },
           _existIn:function(arr, item){
               //summary:
               //       means to solve in serverPaging case,
              var value = this.copyOf(item);
               for(var j in arr){
                  var arrj = this.copyOf(arr[j]);
                  if(dojo.toJson(value) == dojo.toJson(arrj)){
                      return true;
                  }
               }

               return false;
           },
           _index:function(arr,item){
               var value = this.copyOf(item);
               for(var j = 0; j < arr.length; j++){
                    var arrj = this.copyOf(arr[j]);
                    if(dojo.toJson(value) == dojo.toJson(arrj)){
                        return j;
                    }
                }
               return -1;
           },
           _isCheckAll:function(){
                var arr = this.grid.store._arrayOfTopLevelItems;
                for(var i=0; i < arr.length; i++){
                    var item = arr[i];
                    if(item !== undefined && item !== null){
                        if(item.checked === false){
                            return false;
                        }
                    }
                }
                return true;
           },
            formatEditing:function(_283,_284){
                 var item = this.grid.getItem(_284);//when sorting that only items were saved in this._by_idx had been re-sorted
                 if(item === undefined || item === null)
                    item = this.grid.store._arrayOfTopLevelItems[_284];// mark when sorting
                 var isChecked = false;
                 var serverPaging = this.grid.serverPaging !== undefined && this.grid.serverPaging === true;
                 var disabled = null, checked = null;
                 var onclick = this.onclick?this.onclick:"";
                 var setDisabled = this.setDisabled?this.setDisabled:"";
                 var setChecked = this.setChecked?this.setChecked:"";
                 if(typeof setChecked == 'function' || typeof setChecked == 'object') {
                      try{
                         checked = setChecked.call(this,_284);
                         if(!this._existIn(this.grid._initialized, item)){
                                if(checked == true){
                                    if(serverPaging){
                                        this.grid._markup.push(item);
                                    }
                                    this.grid._initialized.push(item);
                                    if(item !== undefined){
                                        item.checked = true;
                                    }
                                }
                         }
                      }catch(ex){
                          console.debug("[duongtb<formatEditing of cells.vtBool:" + ex.message + ">]");
                      }
                  }
                  if(typeof setDisabled == 'function' || typeof setDisabled == 'object') {
                      try{
                         disabled = setDisabled.call(this,_284);
                      }catch(ex){
                           console.debug("[duongtb<formatEditing[setDisabled] of cells.vtBool:" + ex.message + ">]");
                      }
                  }
//                  ]
                  if(/\(*(,?)*\)/.test(onclick)){
                      //do nothing
                  }
                  else{
                      if(onclick != ""){
                        onclick = onclick + "(this," + _284 + ");";
                      }
                  }
                  //[
                 if(serverPaging){
                        if((item!== undefined && item.checked == true) ||this._existIn(this.grid._markup,item)){
                            isChecked = true;
                        }
                  } else {
                        if(this.grid._checkAll == true || (item!== undefined && item.checked == true)){
                            isChecked = true;
                        }
                  }
                  //[ apply a new attribute
                  if(isChecked){
                     if(item !== undefined)
                         item.checked = true;
                  } else {
                      if(item !== undefined)
                          item.checked = false;
                  }
                  //]
                   if(disabled == true || this.editable == false){
                      return "<input style=\""+ (this.cellStyles!==undefined?this.cellStyles:"") + "\" type=\"checkbox\" id = \"checkbox_" + _284 + "_" + this.gridId + "\" onclick=\"" + onclick + "\" class=\"dojoxGridInput_checkbox\""+ (isChecked == true?"  checked=\"checked\"":"") + " disabled=\"true\" />";
                   }
//                  ]
                  return "<input style=\""+ (this.cellStyles!==undefined?this.cellStyles:"") + "\" type=\"checkbox\" id = \"checkbox_" + _284 + "_" + this.gridId + "\" onclick=\"" + onclick +  "\" class=\"dojoxGridInput_checkbox\""+ (isChecked == true?"  checked=\"checked\"":"") + " />";
            },
            doclick:function(e){
                 if(e.target.tagName=="INPUT"){
                 var item = this.grid.getItem(e.rowIndex);
                 var disabled = null;
                 var serverPaging = this.grid.serverPaging !== undefined && this.grid.serverPaging === true;
                 var setDisabled = this.setDisabled?this.setDisabled:"";

                 if(typeof setDisabled== 'function' || typeof setDisabled == 'object') {
                      try{
                         disabled = setDisabled.call(this,e.rowIndex);
                      }catch(ex){
                          console.debug("[duongtb:" + ex.message + "]");
                      }
                  }
                  if(this.editable==true && disabled != true){

                        //var value = this.getEditNode(e.rowIndex)[this._valueProp];
                        var checked = this.getEditNode(e.rowIndex).checked;
                        if(checked == true){
                            if(serverPaging){
                                if(!this._existIn(this.grid._markup, item)){
                                    this.grid._markup.push(item);
                                }
                            }
                            if(item !== undefined && item !== null){
                                item.checked = true;
                            }
                        }
                        else {
                            if(serverPaging){
                                var idx = this._index(this.grid._markup, item);
                                if(idx != -1){
                                    this.grid._markup.splice(idx,1)// remove
                                }
                            }
                            if(item !== undefined && item !== null){
                                item.checked = false;
                            }
                        }
                         // header checkbox
                         //var pageIndex = this.grid._rowToPage(e.rowIndex);
                         if(this._isCheckAll()){
                            if(e.cell.headerCheckbox !== undefined && e.cell.headerCheckbox == true){
                                dojo.byId("cbx_" + this.grid.id).checked = true;
                                this.grid._checkAll = true;
                            }
                        }else{
                            if(checked == false && e.cell.headerCheckbox !== undefined && e.cell.headerCheckbox == true){
                                dojo.byId("cbx_" + this.grid.id).checked = false;
                                if(serverPaging && this.grid.store._loadFinished == false){
                                    // do nothing
                                } else {
                                    this.grid._checkAll = false;
                                }
                            }
                        }
                  }
                //return true;
              }
            }
            });
       dgc.vtBool.markupFactory=function(node,cell){
                dgc.AlwaysEdit.markupFactory(node,cell);
        };
         dojo.declare("dojox.grid.cells.Radio",dgc.AlwaysEdit,{
             _valueProp:"checked",
             copyOf:function(item){
            var o1 = {};
            for(var key in item){
                if(key !== '_RI' && key != '_S' &&
                    key !== '_0' && key !== '_RPM'
                    && key !== 'checked' && key !== '_RS' && key !== '_R'){
                    o1[key] = item[key];
                }
            }
            return o1;
            },
           _equals:function(item1, item2){
               //summary:
               //       means to solve in serverPaging case,
              var o1 = this.copyOf(item1);
              var o2 = this.copyOf(item2);
              if(dojo.toJson(o1) === dojo.toJson(o2)){
                   return true;
              }
              return false;
           },
            formatEditing:function(_283,_284){
                  var item = null;
                  item = this.grid.getItem(_284);//when sorting that only items were saved in this._by_idx had been re-sorted
                  if(item == null)
                    item = this.grid.store._arrayOfTopLevelItems[_284];// mark when sorting
                  var disabled = null,checked = null;
                  var onclick = this.onclick?this.onclick:"";
                  var setDisabled = this.setDisabled?this.setDisabled:"";
                  var setChecked = this.setChecked?this.setChecked:"";
                  if(typeof setChecked == 'function' || typeof setChecked == 'object') {
                      try{
                         checked = setChecked.call(this,_284);
                         if(this.grid._checked == null){
                             if(checked == true){
                                 this.grid._checked = item;
                             }
                         }
                      }catch(ex){
                          console.debug("[duongtb:" + ex.message + "]");
                      }
                  }
                  if(typeof setDisabled == 'function' || typeof setDisabled == 'object') {
                      try{
                         disabled = setDisabled.call(this,_284);
                      }catch(ex){
                          console.debug("[duongtb:" + ex.message + "]");
                      }
                  }
//                  ]
                  if(/\(*(,?)*\)/.test(onclick)){
                      //do nothing
                  }
                  else{
                      if(onclick != ""){
                        onclick = onclick + "(this," + _284 + ");";
                      }
                  }

                      if(disabled == true || this.editable == false){
                          return "<input  type=\"radio\" id = \"radio_" + _284 + "_" + this.gridId + "\" onclick=\"" +
                              onclick + "\" name = \"radio_cell_"+ this.gridId + "\" class=\"dojoxGridInput_radio\""+
                              (this._equals(this.grid._checked, item)?" checked":"") + " disabled=\"true\" />";
                      }
//                  }
//                  ]
                  return "<input  type=\"radio\" id = \"radio_" + _284 + "_" + this.gridId + "\" onclick=\"" +
                      onclick + "\" name = \"radio_cell_"+ this.gridId + "\" class=\"dojoxGridInput_radio\""+
                      (this._equals(this.grid._checked, item)?" checked":"") + " />";

            },
            doclick:function(e){
                if(e.target.tagName=="INPUT"){
                 //var item = this.grid.store._arrayOfTopLevelItems[e.rowIndex];// mark when sorting
                 var item = this.grid.getItem(e.rowIndex);
                 var disabled = null;
                 var setDisabled = this.setDisabled?this.setDisabled:"";
                 if(typeof setDisabled == 'function' || typeof setDisabled == 'object') {
                      try{
                         disabled = setDisabled.call(this,e.rowIndex);
                      }catch(ex){
                          console.debug("[<Radio:" + ex.message + ">]");
                      }
                  }
                if(this.editable==true && disabled != true){
                        try{
                            //var checked = this.getValue(e.rowIndex);
                            var checked = this.getEditNode(e.rowIndex).checked;
                            if(checked == true){
                                this.grid._checked = item;
                            }
                        }catch(ex){
                            console.debug(ex.message);
                        }
                }
//               dojo.byId('radio_' +(e.rowIndex + 1).toString()).checked = true;
                 }
            }
            });
            dgc.Radio.markupFactory=function(node,cell){
                dgc.AlwaysEdit.markupFactory(node,cell);
          };
    //        ]


    //        ]
    })();
}if(!dojo._hasResource["dojox.grid.cells"]){
    dojo._hasResource["dojox.grid.cells"]=true;dojo.provide("dojox.grid.cells");
}if(!dojo._hasResource["dojo.dnd.common"]){
    dojo._hasResource["dojo.dnd.common"]=true;dojo.provide("dojo.dnd.common");dojo.dnd._uniqueId=0;dojo.dnd.getUniqueId=function(){
        var id;do{
            id=dojo._scopeName+"Unique"+(++dojo.dnd._uniqueId);
        }while(dojo.byId(id));return id;
    };dojo.dnd._empty={};dojo.dnd.isFormElement=function(e){
        var t=e.target;if(t.nodeType==3){
            t=t.parentNode;
        }return " button textarea input select option ".indexOf(" "+t.tagName.toLowerCase()+" ")>=0;
    };
}if(!dojo._hasResource["dojo.dnd.autoscroll"]){
    dojo._hasResource["dojo.dnd.autoscroll"]=true;dojo.provide("dojo.dnd.autoscroll");dojo.dnd.getViewport=function(){
        var d=dojo.doc,dd=d.documentElement,w=window,b=dojo.body();if(dojo.isMozilla){
            return {
                w:dd.clientWidth,
                h:w.innerHeight
                };
        }else{
            if(!dojo.isOpera&&w.innerWidth){
                return {
                    w:w.innerWidth,
                    h:w.innerHeight
                    };
            }else{
                if(!dojo.isOpera&&dd&&dd.clientWidth){
                    return {
                        w:dd.clientWidth,
                        h:dd.clientHeight
                        };
                }else{
                    if(b.clientWidth){
                        return {
                            w:b.clientWidth,
                            h:b.clientHeight
                            };
                    }
                    }
                }
            }return null;
    };dojo.dnd.V_TRIGGER_AUTOSCROLL=32;dojo.dnd.H_TRIGGER_AUTOSCROLL=32;dojo.dnd.V_AUTOSCROLL_VALUE=16;dojo.dnd.H_AUTOSCROLL_VALUE=16;dojo.dnd.autoScroll=function(e){
        var v=dojo.dnd.getViewport(),dx=0,dy=0;if(e.clientX<dojo.dnd.H_TRIGGER_AUTOSCROLL){
            dx=-dojo.dnd.H_AUTOSCROLL_VALUE;
        }else{
            if(e.clientX>v.w-dojo.dnd.H_TRIGGER_AUTOSCROLL){
                dx=dojo.dnd.H_AUTOSCROLL_VALUE;
            }
            }if(e.clientY<dojo.dnd.V_TRIGGER_AUTOSCROLL){
            dy=-dojo.dnd.V_AUTOSCROLL_VALUE;
        }else{
            if(e.clientY>v.h-dojo.dnd.V_TRIGGER_AUTOSCROLL){
                dy=dojo.dnd.V_AUTOSCROLL_VALUE;
            }
            }window.scrollBy(dx,dy);
    };dojo.dnd._validNodes={
        "div":1,
        "p":1,
        "td":1
    };dojo.dnd._validOverflow={
        "auto":1,
        "scroll":1
    };dojo.dnd.autoScrollNodes=function(e){
        for(var n=e.target;n;){
            if(n.nodeType==1&&(n.tagName.toLowerCase() in dojo.dnd._validNodes)){
                var s=dojo.getComputedStyle(n);if(s.overflow.toLowerCase() in dojo.dnd._validOverflow){
                    var b=dojo._getContentBox(n,s),t=dojo.position(n,true);var w=Math.min(dojo.dnd.H_TRIGGER_AUTOSCROLL,b.w/2),h=Math.min(dojo.dnd.V_TRIGGER_AUTOSCROLL,b.h/2),rx=e.pageX-t.x,ry=e.pageY-t.y,dx=0,dy=0;if(dojo.isWebKit||dojo.isOpera){
                        rx+=dojo.body().scrollLeft,ry+=dojo.body().scrollTop;
                    }if(rx>0&&rx<b.w){
                        if(rx<w){
                            dx=-w;
                        }else{
                            if(rx>b.w-w){
                                dx=w;
                            }
                            }
                        }if(ry>0&&ry<b.h){
                        if(ry<h){
                            dy=-h;
                        }else{
                            if(ry>b.h-h){
                                dy=h;
                            }
                            }
                        }var _285=n.scrollLeft,_286=n.scrollTop;n.scrollLeft=n.scrollLeft+dx;n.scrollTop=n.scrollTop+dy;if(_285!=n.scrollLeft||_286!=n.scrollTop){
                        return;
                    }
                    }
                }try{
                n=n.parentNode;
            }catch(x){
                n=null;
            }
            }dojo.dnd.autoScroll(e);
    };
}if(!dojo._hasResource["dojo.dnd.Mover"]){
    dojo._hasResource["dojo.dnd.Mover"]=true;dojo.provide("dojo.dnd.Mover");dojo.declare("dojo.dnd.Mover",null,{
        constructor:function(node,e,host){
            this.node=dojo.byId(node);this.marginBox={
                l:e.pageX,
                t:e.pageY
                };this.mouseButton=e.button;var h=this.host=host,d=node.ownerDocument,_287=dojo.connect(d,"onmousemove",this,"onFirstMove");this.events=[dojo.connect(d,"onmousemove",this,"onMouseMove"),dojo.connect(d,"onmouseup",this,"onMouseUp"),dojo.connect(d,"ondragstart",dojo.stopEvent),dojo.connect(d.body,"onselectstart",dojo.stopEvent),_287];if(h&&h.onMoveStart){
                h.onMoveStart(this);
            }
            },
        onMouseMove:function(e){
            dojo.dnd.autoScroll(e);var m=this.marginBox;this.host.onMove(this,{
                l:m.l+e.pageX,
                t:m.t+e.pageY
                });dojo.stopEvent(e);
        },
        onMouseUp:function(e){
            if(dojo.isWebKit&&dojo.isMac&&this.mouseButton==2?e.button==0:this.mouseButton==e.button){
                this.destroy();
            }dojo.stopEvent(e);
        },
        onFirstMove:function(){
            var s=this.node.style,l,t,h=this.host;switch(s.position){
                case "relative":case "absolute":l=Math.round(parseFloat(s.left));t=Math.round(parseFloat(s.top));break;default:s.position="absolute";var m=dojo.marginBox(this.node);var b=dojo.doc.body;var bs=dojo.getComputedStyle(b);var bm=dojo._getMarginBox(b,bs);var bc=dojo._getContentBox(b,bs);l=m.l-(bc.l-bm.l);t=m.t-(bc.t-bm.t);break;
            }this.marginBox.l=l-this.marginBox.l;this.marginBox.t=t-this.marginBox.t;if(h&&h.onFirstMove){
                h.onFirstMove(this);
            }dojo.disconnect(this.events.pop());
        },
        destroy:function(){
            dojo.forEach(this.events,dojo.disconnect);var h=this.host;if(h&&h.onMoveStop){
                h.onMoveStop(this);
            }this.events=this.node=this.host=null;
        }
        });
}if(!dojo._hasResource["dojo.dnd.Moveable"]){
    dojo._hasResource["dojo.dnd.Moveable"]=true;dojo.provide("dojo.dnd.Moveable");dojo.declare("dojo.dnd.Moveable",null,{
        handle:"",
        delay:0,
        skip:false,
        constructor:function(node,_288){
            this.node=dojo.byId(node);if(!_288){
                _288={};
            }this.handle=_288.handle?dojo.byId(_288.handle):null;if(!this.handle){
                this.handle=this.node;
            }this.delay=_288.delay>0?_288.delay:0;this.skip=_288.skip;this.mover=_288.mover?_288.mover:dojo.dnd.Mover;this.events=[dojo.connect(this.handle,"onmousedown",this,"onMouseDown"),dojo.connect(this.handle,"ondragstart",this,"onSelectStart"),dojo.connect(this.handle,"onselectstart",this,"onSelectStart")];
        },
        markupFactory:function(_289,node){
            return new dojo.dnd.Moveable(node,_289);
        },
        destroy:function(){
            dojo.forEach(this.events,dojo.disconnect);this.events=this.node=this.handle=null;
        },
        onMouseDown:function(e){
            if(this.skip&&dojo.dnd.isFormElement(e)){
                return;
            }if(this.delay){
                this.events.push(dojo.connect(this.handle,"onmousemove",this,"onMouseMove"),dojo.connect(this.handle,"onmouseup",this,"onMouseUp"));this._lastX=e.pageX;this._lastY=e.pageY;
            }else{
                this.onDragDetected(e);
            }dojo.stopEvent(e);
        },
        onMouseMove:function(e){
            if(Math.abs(e.pageX-this._lastX)>this.delay||Math.abs(e.pageY-this._lastY)>this.delay){
                this.onMouseUp(e);this.onDragDetected(e);
            }dojo.stopEvent(e);
        },
        onMouseUp:function(e){
            for(var i=0;i<2;++i){
                dojo.disconnect(this.events.pop());
            }dojo.stopEvent(e);
        },
        onSelectStart:function(e){
            if(!this.skip||!dojo.dnd.isFormElement(e)){
                dojo.stopEvent(e);
            }
            },
        onDragDetected:function(e){
            new this.mover(this.node,e,this);
        },
        onMoveStart:function(_28a){
            dojo.publish("/dnd/move/start",[_28a]);dojo.addClass(dojo.body(),"dojoMove");dojo.addClass(this.node,"dojoMoveItem");
        },
        onMoveStop:function(_28b){
            dojo.publish("/dnd/move/stop",[_28b]);dojo.removeClass(dojo.body(),"dojoMove");dojo.removeClass(this.node,"dojoMoveItem");
        },
        onFirstMove:function(_28c){},
        onMove:function(_28d,_28e){
            this.onMoving(_28d,_28e);var s=_28d.node.style;s.left=_28e.l+"px";s.top=_28e.t+"px";this.onMoved(_28d,_28e);
        },
        onMoving:function(_28f,_290){},
        onMoved:function(_291,_292){}
        });
}if(!dojo._hasResource["dojox.grid._Builder"]){
    dojo._hasResource["dojox.grid._Builder"]=true;dojo.provide("dojox.grid._Builder");(function(){
        var dg=dojox.grid;var _293=function(td){
            return td.cellIndex>=0?td.cellIndex:dojo.indexOf(td.parentNode.cells,td);
        };var _294=function(tr){
            return tr.rowIndex>=0?tr.rowIndex:dojo.indexOf(tr.parentNode.childNodes,tr);
        };var _295=function(_296,_297){
            return _296&&((_296.rows||0)[_297]||_296.childNodes[_297]);
        };var _298=function(node){
            for(var n=node;n&&n.tagName!="TABLE";n=n.parentNode){}return n;
        };var _299=function(_29a,_29b){
            for(var n=_29a;n&&_29b(n);n=n.parentNode){}return n;
        };var _29c=function(_29d){
            var name=_29d.toUpperCase();return function(node){
                return node.tagName!=name;
            };
        };var _29e=dojox.grid.util.rowIndexTag;var _29f=dojox.grid.util.gridViewTag;dg._Builder=dojo.extend(function(view){
            if(view){
                this.view=view;this.grid=view.grid;
            }
            },{
            view:null,
            _table:"<table class=\"dojoxGridRowTable\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"",
            getTableArray:function(){
                var html=[this._table];if(this.view.viewWidth){
                    html.push([" style=\"width:",this.view.viewWidth,";\""].join(""));
                }html.push(">");return html;
            },
            generateCellMarkup:function(_2a0,_2a1,_2a2,_2a3){
                var _2a4=[],html;if(_2a3){
                    var _2a5=_2a0.index!=_2a0.grid.getSortIndex()?"":_2a0.grid.sortInfo>0?"aria-sort=\"ascending\"":"aria-sort=\"descending\"";if(!_2a0.id){
                        _2a0.id=this.grid.id+"Hdr"+_2a0.index;
                    }html=["<th tabIndex=\"-1\" aria-readonly=\"true\" role=\"columnheader\"",_2a5,"id=\"",_2a0.id,"\""];
                }else{
                    var _2a6=this.grid.editable&&!_2a0.editable?"aria-readonly=\"true\"":"";html=["<td tabIndex=\"-1\" role=\"gridcell\"",_2a6];
                }if(_2a0.colSpan){
                    html.push(" colspan=\"",_2a0.colSpan,"\"");
                }if(_2a0.rowSpan){
                    html.push(" rowspan=\"",_2a0.rowSpan,"\"");
                }html.push(" class=\"dojoxGridCell ");if(_2a0.classes){
                    html.push(_2a0.classes," ");
                }if(_2a2){
                    html.push(_2a2," ");
                }_2a4.push(html.join(""));_2a4.push("");html=["\" idx=\"",_2a0.index,"\" style=\""];if(_2a1&&_2a1[_2a1.length-1]!=";"){
                    _2a1+=";";
                }html.push(_2a0.styles,_2a1||"",_2a0.hidden?"display:none;":"");if(_2a0.unitWidth){
                    html.push("width:",_2a0.unitWidth,";");
                }_2a4.push(html.join(""));_2a4.push("");html=["\""];if(_2a0.attrs){
                    html.push(" ",_2a0.attrs);
                }html.push(">");_2a4.push(html.join(""));_2a4.push("");_2a4.push(_2a3?"</th>":"</td>");return _2a4;
            },
            isCellNode:function(_2a7){
                return Boolean(_2a7&&_2a7!=dojo.doc&&dojo.attr(_2a7,"idx"));
            },
            getCellNodeIndex:function(_2a8){
                return _2a8?Number(dojo.attr(_2a8,"idx")):-1;
            },
            getCellNode:function(_2a9,_2aa){
                for(var i=0,row;(row=_295(_2a9.firstChild,i));i++){
                    for(var j=0,cell;(cell=row.cells[j]);j++){
                        if(this.getCellNodeIndex(cell)==_2aa){
                            return cell;
                        }
                        }
                    }return null;
            },
            findCellTarget:function(_2ab,_2ac){
                var n=_2ab;while(n&&(!this.isCellNode(n)||(n.offsetParent&&_29f in n.offsetParent.parentNode&&n.offsetParent.parentNode[_29f]!=this.view.id))&&(n!=_2ac)){
                    n=n.parentNode;
                }return n!=_2ac?n:null;
            },
            baseDecorateEvent:function(e){
                e.dispatch="do"+e.type;e.grid=this.grid;e.sourceView=this.view;e.cellNode=this.findCellTarget(e.target,e.rowNode);e.cellIndex=this.getCellNodeIndex(e.cellNode);e.cell=(e.cellIndex>=0?this.grid.getCell(e.cellIndex):null);
            },
            findTarget:function(_2ad,_2ae){
                var n=_2ad;while(n&&(n!=this.domNode)&&(!(_2ae in n)||(_29f in n&&n[_29f]!=this.view.id))){
                    n=n.parentNode;
                }return (n!=this.domNode)?n:null;
            },
            findRowTarget:function(_2af){
                return this.findTarget(_2af,_29e);
            },
            isIntraNodeEvent:function(e){
                try{
                    return (e.cellNode&&e.relatedTarget&&dojo.isDescendant(e.relatedTarget,e.cellNode));
                }catch(x){
                    return false;
                }
                },
            isIntraRowEvent:function(e){
                try{
                    var row=e.relatedTarget&&this.findRowTarget(e.relatedTarget);return !row&&(e.rowIndex==-1)||row&&(e.rowIndex==row.gridRowIndex);
                }catch(x){
                    return false;
                }
                },
            dispatchEvent:function(e){
                if(e.dispatch in this){
                    return this[e.dispatch](e);
                }return false;
            },
            domouseover:function(e){
                if(e.cellNode&&(e.cellNode!=this.lastOverCellNode)){
                    this.lastOverCellNode=e.cellNode;this.grid.onMouseOver(e);
                }this.grid.onMouseOverRow(e);
            },
            domouseout:function(e){
                if(e.cellNode&&(e.cellNode==this.lastOverCellNode)&&!this.isIntraNodeEvent(e,this.lastOverCellNode)){
                    this.lastOverCellNode=null;this.grid.onMouseOut(e);if(!this.isIntraRowEvent(e)){
                        this.grid.onMouseOutRow(e);
                    }
                    }
                },
            domousedown:function(e){
                if(e.cellNode){
                    this.grid.onMouseDown(e);
                }this.grid.onMouseDownRow(e);
            }
            });dg._ContentBuilder=dojo.extend(function(view){
            dg._Builder.call(this,view);
        },dg._Builder.prototype,{
            update:function(){
                this.prepareHtml();
            },
            prepareHtml:function(){
                var _2b0=this.grid.get,_2b1=this.view.structure.cells;for(var j=0,row;(row=_2b1[j]);j++){
                    for(var i=0,cell;(cell=row[i]);i++){
                        cell.get=cell.get||(cell.value==undefined)&&_2b0;cell.markup=this.generateCellMarkup(cell,cell.cellStyles,cell.cellClasses,false);if(!this.grid.editable&&cell.editable){
                            this.grid.editable=true;
                        }
                        }
                    }
                },
            generateHtml:function(_2b2,_2b3){
                var html=this.getTableArray(),v=this.view,_2b4=v.structure.cells,item=this.grid.getItem(_2b3);dojox.grid.util.fire(this.view,"onBeforeRow",[_2b3,_2b4]);for(var j=0,row;(row=_2b4[j]);j++){
                    if(row.hidden||row.header){
                        continue;
                    }html.push(!row.invisible?"<tr>":"<tr class=\"dojoxGridInvisible\">");for(var i=0,cell,m,cc,cs;(cell=row[i]);i++){
                        m=cell.markup;cc=cell.customClasses=[];cs=cell.customStyles=[];m[5]=cell.format(_2b3,item);m[1]=cc.join(" ");m[3]=cs.join(";");html.push.apply(html,m);
                    }html.push("</tr>");
                }html.push("</table>");return html.join("");
            },
            decorateEvent:function(e){
                e.rowNode=this.findRowTarget(e.target);if(!e.rowNode){
                    return false;
                }e.rowIndex=e.rowNode[_29e];this.baseDecorateEvent(e);e.cell=this.grid.getCell(e.cellIndex);return true;
            }
            });dg._HeaderBuilder=dojo.extend(function(view){
            this.moveable=null;dg._Builder.call(this,view);
        },dg._Builder.prototype,{
            _skipBogusClicks:false,
            overResizeWidth:4,
            minColWidth:1,
            update:function(){
                if(this.tableMap){
                    this.tableMap.mapRows(this.view.structure.cells);
                }else{
                    this.tableMap=new dg._TableMap(this.view.structure.cells);
                }
                },
            generateHtml:function(_2b5,_2b6){
                var html=this.getTableArray(),_2b7=this.view.structure.cells;dojox.grid.util.fire(this.view,"onBeforeRow",[-1,_2b7]);for(var j=0,row;(row=_2b7[j]);j++){
                    if(row.hidden){
                        continue;
                    }html.push(!row.invisible?"<tr>":"<tr class=\"dojoxGridInvisible\">");for(var i=0,cell,_2b8;(cell=row[i]);i++){
                        cell.customClasses=[];cell.customStyles=[];if(this.view.simpleStructure){
                            if(cell.draggable){
                                if(cell.headerClasses){
                                    if(cell.headerClasses.indexOf("dojoDndItem")==-1){
                                        cell.headerClasses+=" dojoDndItem";
                                    }
                                }else{
                                    cell.headerClasses="dojoDndItem";
                                }
                            }if(cell.attrs){
                                if(cell.attrs.indexOf("dndType='gridColumn_")==-1){
                                    cell.attrs+=" dndType='gridColumn_"+this.grid.id+"'";
                                }
                            }else{
                                cell.attrs="dndType='gridColumn_"+this.grid.id+"'";
                            }
                        }
                        _2b8=this.generateCellMarkup(cell,cell.headerStyles,cell.headerClasses,true);
                        _2b8[5]=(_2b6!=undefined?_2b6:_2b5(cell));
                        //[duongtb: header checkbox
                        if(cell.headerCheckbox !== undefined && cell.headerCheckbox == true && cell.type == dojox.grid.cells.vtBool){
                                _2b8[5]="<input style=\""+ (cell.headerStyles!=undefined?cell.headerStyles:"") + "\" type=\"checkbox\" id=\"cbx_" + this.grid.id + "\""+(this.grid._checkAll == true?"  checked=\"checked\"":"") + " onclick='headerCheck(this);'/>";
                         }
                        //]
                        _2b8[3]=cell.customStyles.join(";");
                        _2b8[1]=cell.customClasses.join(" ");
                        html.push(_2b8.join(""));
                    }html.push("</tr>");
                }html.push("</table>");return html.join("");
            },
            getCellX:function(e){
                var n,x=e.layerX;if(dojo.isMoz){
                    n=_299(e.target,_29c("th"));x-=(n&&n.offsetLeft)||0;var t=e.sourceView.getScrollbarWidth();if(!dojo._isBodyLtr()&&e.sourceView.headerNode.scrollLeft<t){
                        x-=t;
                    }
                    }n=_299(e.target,function(){
                    if(!n||n==e.cellNode){
                        return false;
                    }x+=(n.offsetLeft<0?0:n.offsetLeft);return true;
                });return x;
            },
            decorateEvent:function(e){
                this.baseDecorateEvent(e);e.rowIndex=-1;e.cellX=this.getCellX(e);return true;
            },
            prepareResize:function(e,mod){
                do{
                    var i=_293(e.cellNode);e.cellNode=(i?e.cellNode.parentNode.cells[i+mod]:null);e.cellIndex=(e.cellNode?this.getCellNodeIndex(e.cellNode):-1);
                }while(e.cellNode&&e.cellNode.style.display=="none");return Boolean(e.cellNode);
            },
            canResize:function(e){
                if(!e.cellNode||e.cellNode.colSpan>1){
                    return false;
                }var cell=this.grid.getCell(e.cellIndex);return !cell.noresize&&cell.canResize();
            },
            overLeftResizeArea:function(e){
                if(dojo.hasClass(dojo.body(),"dojoDndMove")){
                    return false;
                }if(dojo.isIE){
                    var tN=e.target;if(dojo.hasClass(tN,"dojoxGridArrowButtonNode")||dojo.hasClass(tN,"dojoxGridArrowButtonChar")){
                        return false;
                    }
                    }if(dojo._isBodyLtr()){
                    return (e.cellIndex>0)&&(e.cellX<this.overResizeWidth)&&this.prepareResize(e,-1);
                }var t=e.cellNode&&(e.cellX<this.overResizeWidth);return t;
            },
            overRightResizeArea:function(e){
                if(dojo.hasClass(dojo.body(),"dojoDndMove")){
                    return false;
                }if(dojo.isIE){
                    var tN=e.target;if(dojo.hasClass(tN,"dojoxGridArrowButtonNode")||dojo.hasClass(tN,"dojoxGridArrowButtonChar")){
                        return false;
                    }
                    }if(dojo._isBodyLtr()){
                    return e.cellNode&&(e.cellX>=e.cellNode.offsetWidth-this.overResizeWidth);
                }return (e.cellIndex>0)&&(e.cellX>=e.cellNode.offsetWidth-this.overResizeWidth)&&this.prepareResize(e,-1);
            },
            domousemove:function(e){
                if(!this.moveable){
                    var c=(this.overRightResizeArea(e)?"dojoxGridColResize":(this.overLeftResizeArea(e)?"dojoxGridColResize":""));if(c&&!this.canResize(e)){
                        c="dojoxGridColNoResize";
                    }dojo.toggleClass(e.sourceView.headerNode,"dojoxGridColNoResize",(c=="dojoxGridColNoResize"));dojo.toggleClass(e.sourceView.headerNode,"dojoxGridColResize",(c=="dojoxGridColResize"));if(dojo.isIE){
                        var t=e.sourceView.headerNode.scrollLeft;e.sourceView.headerNode.scrollLeft=t;
                    }if(c){
                        dojo.stopEvent(e);
                    }
                    }
                },
            domousedown:function(e){
                if(!this.moveable){
                    if((this.overRightResizeArea(e)||this.overLeftResizeArea(e))&&this.canResize(e)){
                        this.beginColumnResize(e);
                    }else{
                        this.grid.onMouseDown(e);this.grid.onMouseOverRow(e);
                    }
                    }
                },
            doclick:function(e){
                if(this._skipBogusClicks){
                    dojo.stopEvent(e);return true;
                }return false;
            },
            colResizeSetup:function(e,_2b9){
                var _2ba=dojo.contentBox(e.sourceView.headerNode);if(_2b9){
                    this.lineDiv=document.createElement("div");var vw=(dojo.position||dojo._abs)(e.sourceView.headerNode,true);var _2bb=dojo.contentBox(e.sourceView.domNode);dojo.style(this.lineDiv,{
                        top:vw.y+"px",
                        left:e.clientX+"px",
                        height:(_2bb.h+_2ba.h)+"px"
                        });dojo.addClass(this.lineDiv,"dojoxGridResizeColLine");this.lineDiv._origLeft=e.clientX;dojo.body().appendChild(this.lineDiv);
                }var _2bc=[],_2bd=this.tableMap.findOverlappingNodes(e.cellNode);for(var i=0,cell;(cell=_2bd[i]);i++){
                    _2bc.push({
                        node:cell,
                        index:this.getCellNodeIndex(cell),
                        width:cell.offsetWidth
                        });
                }var view=e.sourceView;var adj=dojo._isBodyLtr()?1:-1;var _2be=e.grid.views.views;var _2bf=[];for(var j=view.idx+adj,_2c0;(_2c0=_2be[j]);j=j+adj){
                    _2bf.push({
                        node:_2c0.headerNode,
                        left:window.parseInt(_2c0.headerNode.style.left)
                        });
                }var _2c1=view.headerContentNode.firstChild;var drag={
                    scrollLeft:e.sourceView.headerNode.scrollLeft,
                    view:view,
                    node:e.cellNode,
                    index:e.cellIndex,
                    w:dojo.contentBox(e.cellNode).w,
                    vw:_2ba.w,
                    table:_2c1,
                    tw:dojo.contentBox(_2c1).w,
                    spanners:_2bc,
                    followers:_2bf
                };return drag;
            },
            beginColumnResize:function(e){
                this.moverDiv=document.createElement("div");dojo.style(this.moverDiv,{
                    position:"absolute",
                    left:0
                });dojo.body().appendChild(this.moverDiv);dojo.addClass(this.grid.domNode,"dojoxGridColumnResizing");var m=(this.moveable=new dojo.dnd.Moveable(this.moverDiv));var drag=this.colResizeSetup(e,true);m.onMove=dojo.hitch(this,"doResizeColumn",drag);dojo.connect(m,"onMoveStop",dojo.hitch(this,function(){
                    this.endResizeColumn(drag);if(drag.node.releaseCapture){
                        drag.node.releaseCapture();
                    }this.moveable.destroy();delete this.moveable;this.moveable=null;dojo.removeClass(this.grid.domNode,"dojoxGridColumnResizing");
                }));if(e.cellNode.setCapture){
                    e.cellNode.setCapture();
                }m.onMouseDown(e);
            },
            doResizeColumn:function(_2c2,_2c3,_2c4){
                var _2c5=_2c4.l;var data={
                    deltaX:_2c5,
                    w:_2c2.w+_2c5,
                    vw:_2c2.vw+_2c5,
                    tw:_2c2.tw+_2c5
                    };this.dragRecord={
                    inDrag:_2c2,
                    mover:_2c3,
                    leftTop:_2c4
                };if(data.w>=this.minColWidth){
                    if(!_2c3){
                        this.doResizeNow(_2c2,data);
                    }else{
                        dojo.style(this.lineDiv,"left",(this.lineDiv._origLeft+data.deltaX)+"px");
                    }
                    }
                },
            endResizeColumn:function(_2c6){
                if(this.dragRecord){
                    var _2c7=this.dragRecord.leftTop;var _2c8=dojo._isBodyLtr()?_2c7.l:-_2c7.l;_2c8+=Math.max(_2c6.w+_2c8,this.minColWidth)-(_2c6.w+_2c8);if(dojo.isWebKit&&_2c6.spanners.length){
                        _2c8+=dojo._getPadBorderExtents(_2c6.spanners[0].node).w;
                    }var data={
                        deltaX:_2c8,
                        w:_2c6.w+_2c8,
                        vw:_2c6.vw+_2c8,
                        tw:_2c6.tw+_2c8
                        };this.doResizeNow(_2c6,data);
                }dojo.destroy(this.lineDiv);dojo.destroy(this.moverDiv);dojo.destroy(this.moverDiv);delete this.moverDiv;this._skipBogusClicks=true;_2c6.view.update();this._skipBogusClicks=false;this.grid.onResizeColumn(_2c6.index);
            },
            doResizeNow:function(_2c9,data){
                _2c9.view.convertColPctToFixed();if(_2c9.view.flexCells&&!_2c9.view.testFlexCells()){
                    var t=_298(_2c9.node);if(t){
                        (t.style.width="");
                    }
                    }var i,s,sw,f,fl;for(i=0;(s=_2c9.spanners[i]);i++){
                    sw=s.width+data.deltaX;s.node.style.width=sw+"px";_2c9.view.setColWidth(s.index,sw);
                }for(i=0;(f=_2c9.followers[i]);i++){
                    fl=f.left+data.deltaX;f.node.style.left=fl+"px";
                }_2c9.node.style.width=data.w+"px";_2c9.view.setColWidth(_2c9.index,data.w);_2c9.view.headerNode.style.width=data.vw+"px";_2c9.view.setColumnsWidth(data.tw);if(!dojo._isBodyLtr()){
                    _2c9.view.headerNode.scrollLeft=_2c9.scrollLeft+data.deltaX;
                }
                }
            });dg._TableMap=dojo.extend(function(rows){
            this.mapRows(rows);
        },{
            map:null,
            mapRows:function(_2ca){
                var _2cb=_2ca.length;if(!_2cb){
                    return;
                }this.map=[];var row;for(var k=0;(row=_2ca[k]);k++){
                    this.map[k]=[];
                }for(var j=0;(row=_2ca[j]);j++){
                    for(var i=0,x=0,cell,_2cc,_2cd;(cell=row[i]);i++){
                        while(this.map[j][x]){
                            x++;
                        }this.map[j][x]={
                            c:i,
                            r:j
                        };_2cd=cell.rowSpan||1;_2cc=cell.colSpan||1;for(var y=0;y<_2cd;y++){
                            for(var s=0;s<_2cc;s++){
                                this.map[j+y][x+s]=this.map[j][x];
                            }
                            }x+=_2cc;
                    }
                    }
                },
            dumpMap:function(){
                for(var j=0,row,h="";(row=this.map[j]);j++,h=""){
                    for(var i=0,cell;(cell=row[i]);i++){
                        h+=cell.r+","+cell.c+"   ";
                    }
                    }
                },
            getMapCoords:function(_2ce,_2cf){
                for(var j=0,row;(row=this.map[j]);j++){
                    for(var i=0,cell;(cell=row[i]);i++){
                        if(cell.c==_2cf&&cell.r==_2ce){
                            return {
                                j:j,
                                i:i
                            };
                        }
                        }
                    }return {
                    j:-1,
                    i:-1
                };
            },
            getNode:function(_2d0,_2d1,_2d2){
                var row=_2d0&&_2d0.rows[_2d1];return row&&row.cells[_2d2];
            },
            _findOverlappingNodes:function(_2d3,_2d4,_2d5){
                var _2d6=[];var m=this.getMapCoords(_2d4,_2d5);for(var j=0,row;(row=this.map[j]);j++){
                    if(j==m.j){
                        continue;
                    }var rw=row[m.i];var n=(rw?this.getNode(_2d3,rw.r,rw.c):null);if(n){
                        _2d6.push(n);
                    }
                    }return _2d6;
            },
            findOverlappingNodes:function(_2d7){
                return this._findOverlappingNodes(_298(_2d7),_294(_2d7.parentNode),_293(_2d7));
            }
            });
    })();
}if(!dojo._hasResource["dojo.dnd.Container"]){
    dojo._hasResource["dojo.dnd.Container"]=true;dojo.provide("dojo.dnd.Container");dojo.declare("dojo.dnd.Container",null,{
        skipForm:false,
        constructor:function(node,_2d8){
            this.node=dojo.byId(node);if(!_2d8){
                _2d8={};
            }this.creator=_2d8.creator||null;this.skipForm=_2d8.skipForm;this.parent=_2d8.dropParent&&dojo.byId(_2d8.dropParent);this.map={};this.current=null;this.containerState="";dojo.addClass(this.node,"dojoDndContainer");if(!(_2d8&&_2d8._skipStartup)){
                this.startup();
            }this.events=[dojo.connect(this.node,"onmouseover",this,"onMouseOver"),dojo.connect(this.node,"onmouseout",this,"onMouseOut"),dojo.connect(this.node,"ondragstart",this,"onSelectStart"),dojo.connect(this.node,"onselectstart",this,"onSelectStart")];
        },
        creator:function(){},
        getItem:function(key){
            return this.map[key];
        },
        setItem:function(key,data){
            this.map[key]=data;
        },
        delItem:function(key){
            delete this.map[key];
        },
        forInItems:function(f,o){
            o=o||dojo.global;var m=this.map,e=dojo.dnd._empty;for(var i in m){
                if(i in e){
                    continue;
                }f.call(o,m[i],i,this);
            }return o;
        },
        clearItems:function(){
            this.map={};
        },
        getAllNodes:function(){
            return dojo.query("> .dojoDndItem",this.parent);
        },
        sync:function(){
            var map={};this.getAllNodes().forEach(function(node){
                if(node.id){
                    var item=this.getItem(node.id);if(item){
                        map[node.id]=item;return;
                    }
                    }else{
                    node.id=dojo.dnd.getUniqueId();
                }var type=node.getAttribute("dndType"),data=node.getAttribute("dndData");map[node.id]={
                    data:data||node.innerHTML,
                    type:type?type.split(/\s*,\s*/):["text"]
                    };
            },this);this.map=map;return this;
        },
        insertNodes:function(data,_2d9,_2da){
            if(!this.parent.firstChild){
                _2da=null;
            }else{
                if(_2d9){
                    if(!_2da){
                        _2da=this.parent.firstChild;
                    }
                    }else{
                    if(_2da){
                        _2da=_2da.nextSibling;
                    }
                    }
                }if(_2da){
                for(var i=0;i<data.length;++i){
                    var t=this._normalizedCreator(data[i]);this.setItem(t.node.id,{
                        data:t.data,
                        type:t.type
                        });this.parent.insertBefore(t.node,_2da);
                }
                }else{
                for(var i=0;i<data.length;++i){
                    var t=this._normalizedCreator(data[i]);this.setItem(t.node.id,{
                        data:t.data,
                        type:t.type
                        });this.parent.appendChild(t.node);
                }
                }return this;
        },
        destroy:function(){
            dojo.forEach(this.events,dojo.disconnect);this.clearItems();this.node=this.parent=this.current=null;
        },
        markupFactory:function(_2db,node){
            _2db._skipStartup=true;return new dojo.dnd.Container(node,_2db);
        },
        startup:function(){
            if(!this.parent){
                this.parent=this.node;if(this.parent.tagName.toLowerCase()=="table"){
                    var c=this.parent.getElementsByTagName("tbody");if(c&&c.length){
                        this.parent=c[0];
                    }
                    }
                }this.defaultCreator=dojo.dnd._defaultCreator(this.parent);this.sync();
        },
        onMouseOver:function(e){
            var n=e.relatedTarget;while(n){
                if(n==this.node){
                    break;
                }try{
                    n=n.parentNode;
                }catch(x){
                    n=null;
                }
                }if(!n){
                this._changeState("Container","Over");this.onOverEvent();
            }n=this._getChildByEvent(e);if(this.current==n){
                return;
            }if(this.current){
                this._removeItemClass(this.current,"Over");
            }if(n){
                this._addItemClass(n,"Over");
            }this.current=n;
        },
        onMouseOut:function(e){
            for(var n=e.relatedTarget;n;){
                if(n==this.node){
                    return;
                }try{
                    n=n.parentNode;
                }catch(x){
                    n=null;
                }
                }if(this.current){
                this._removeItemClass(this.current,"Over");this.current=null;
            }this._changeState("Container","");this.onOutEvent();
        },
        onSelectStart:function(e){
            if(!this.skipForm||!dojo.dnd.isFormElement(e)){
                dojo.stopEvent(e);
            }
            },
        onOverEvent:function(){},
        onOutEvent:function(){},
        _changeState:function(type,_2dc){
            var _2dd="dojoDnd"+type;var _2de=type.toLowerCase()+"State";dojo.removeClass(this.node,_2dd+this[_2de]);dojo.addClass(this.node,_2dd+_2dc);this[_2de]=_2dc;
        },
        _addItemClass:function(node,type){
            dojo.addClass(node,"dojoDndItem"+type);
        },
        _removeItemClass:function(node,type){
            dojo.removeClass(node,"dojoDndItem"+type);
        },
        _getChildByEvent:function(e){
            var node=e.target;if(node){
                for(var _2df=node.parentNode;_2df;node=_2df,_2df=node.parentNode){
                    if(_2df==this.parent&&dojo.hasClass(node,"dojoDndItem")){
                        return node;
                    }
                    }
                }return null;
        },
        _normalizedCreator:function(item,hint){
            var t=(this.creator||this.defaultCreator).call(this,item,hint);if(!dojo.isArray(t.type)){
                t.type=["text"];
            }if(!t.node.id){
                t.node.id=dojo.dnd.getUniqueId();
            }dojo.addClass(t.node,"dojoDndItem");return t;
        }
        });dojo.dnd._createNode=function(tag){
        if(!tag){
            return dojo.dnd._createSpan;
        }return function(text){
            return dojo.create(tag,{
                innerHTML:text
            });
        };
    };dojo.dnd._createTrTd=function(text){
        var tr=dojo.create("tr");dojo.create("td",{
            innerHTML:text
        },tr);return tr;
    };dojo.dnd._createSpan=function(text){
        return dojo.create("span",{
            innerHTML:text
        });
    };dojo.dnd._defaultCreatorNodes={
        ul:"li",
        ol:"li",
        div:"div",
        p:"div"
    };dojo.dnd._defaultCreator=function(node){
        var tag=node.tagName.toLowerCase();var c=tag=="tbody"||tag=="thead"?dojo.dnd._createTrTd:dojo.dnd._createNode(dojo.dnd._defaultCreatorNodes[tag]);return function(item,hint){
            var _2e0=item&&dojo.isObject(item),data,type,n;if(_2e0&&item.tagName&&item.nodeType&&item.getAttribute){
                data=item.getAttribute("dndData")||item.innerHTML;type=item.getAttribute("dndType");type=type?type.split(/\s*,\s*/):["text"];n=item;
            }else{
                data=(_2e0&&item.data)?item.data:item;type=(_2e0&&item.type)?item.type:["text"];n=(hint=="avatar"?dojo.dnd._createSpan:c)(String(data));
            }n.id=dojo.dnd.getUniqueId();return {
                node:n,
                data:data,
                type:type
            };
        };
    };
}if(!dojo._hasResource["dojo.dnd.Selector"]){
    dojo._hasResource["dojo.dnd.Selector"]=true;dojo.provide("dojo.dnd.Selector");dojo.declare("dojo.dnd.Selector",dojo.dnd.Container,{
        constructor:function(node,_2e1){
            if(!_2e1){
                _2e1={};
            }this.singular=_2e1.singular;this.autoSync=_2e1.autoSync;this.selection={};this.anchor=null;this.simpleSelection=false;this.events.push(dojo.connect(this.node,"onmousedown",this,"onMouseDown"),dojo.connect(this.node,"onmouseup",this,"onMouseUp"));
        },
        singular:false,
        getSelectedNodes:function(){
            var t=new dojo.NodeList();var e=dojo.dnd._empty;for(var i in this.selection){
                if(i in e){
                    continue;
                }t.push(dojo.byId(i));
            }return t;
        },
        selectNone:function(){
            return this._removeSelection()._removeAnchor();
        },
        selectAll:function(){
            this.forInItems(function(data,id){
                this._addItemClass(dojo.byId(id),"Selected");this.selection[id]=1;
            },this);return this._removeAnchor();
        },
        deleteSelectedNodes:function(){
            var e=dojo.dnd._empty;for(var i in this.selection){
                if(i in e){
                    continue;
                }var n=dojo.byId(i);this.delItem(i);dojo.destroy(n);
            }this.anchor=null;this.selection={};return this;
        },
        forInSelectedItems:function(f,o){
            o=o||dojo.global;var s=this.selection,e=dojo.dnd._empty;for(var i in s){
                if(i in e){
                    continue;
                }f.call(o,this.getItem(i),i,this);
            }
            },
        sync:function(){
            dojo.dnd.Selector.superclass.sync.call(this);if(this.anchor){
                if(!this.getItem(this.anchor.id)){
                    this.anchor=null;
                }
                }var t=[],e=dojo.dnd._empty;for(var i in this.selection){
                if(i in e){
                    continue;
                }if(!this.getItem(i)){
                    t.push(i);
                }
                }dojo.forEach(t,function(i){
                delete this.selection[i];
            },this);return this;
        },
        insertNodes:function(_2e2,data,_2e3,_2e4){
            var _2e5=this._normalizedCreator;this._normalizedCreator=function(item,hint){
                var t=_2e5.call(this,item,hint);if(_2e2){
                    if(!this.anchor){
                        this.anchor=t.node;this._removeItemClass(t.node,"Selected");this._addItemClass(this.anchor,"Anchor");
                    }else{
                        if(this.anchor!=t.node){
                            this._removeItemClass(t.node,"Anchor");this._addItemClass(t.node,"Selected");
                        }
                        }this.selection[t.node.id]=1;
                }else{
                    this._removeItemClass(t.node,"Selected");this._removeItemClass(t.node,"Anchor");
                }return t;
            };dojo.dnd.Selector.superclass.insertNodes.call(this,data,_2e3,_2e4);this._normalizedCreator=_2e5;return this;
        },
        destroy:function(){
            dojo.dnd.Selector.superclass.destroy.call(this);this.selection=this.anchor=null;
        },
        markupFactory:function(_2e6,node){
            _2e6._skipStartup=true;return new dojo.dnd.Selector(node,_2e6);
        },
        onMouseDown:function(e){
            if(this.autoSync){
                this.sync();
            }if(!this.current){
                return;
            }if(!this.singular&&!dojo.isCopyKey(e)&&!e.shiftKey&&(this.current.id in this.selection)){
                this.simpleSelection=true;if(e.button===dojo.mouseButtons.LEFT){
                    dojo.stopEvent(e);
                }return;
            }if(!this.singular&&e.shiftKey){
                if(!dojo.isCopyKey(e)){
                    this._removeSelection();
                }var c=this.getAllNodes();if(c.length){
                    if(!this.anchor){
                        this.anchor=c[0];this._addItemClass(this.anchor,"Anchor");
                    }this.selection[this.anchor.id]=1;if(this.anchor!=this.current){
                        var i=0;for(;i<c.length;++i){
                            var node=c[i];if(node==this.anchor||node==this.current){
                                break;
                            }
                            }for(++i;i<c.length;++i){
                            var node=c[i];if(node==this.anchor||node==this.current){
                                break;
                            }this._addItemClass(node,"Selected");this.selection[node.id]=1;
                        }this._addItemClass(this.current,"Selected");this.selection[this.current.id]=1;
                    }
                    }
                }else{
                if(this.singular){
                    if(this.anchor==this.current){
                        if(dojo.isCopyKey(e)){
                            this.selectNone();
                        }
                        }else{
                        this.selectNone();this.anchor=this.current;this._addItemClass(this.anchor,"Anchor");this.selection[this.current.id]=1;
                    }
                    }else{
                    if(dojo.isCopyKey(e)){
                        if(this.anchor==this.current){
                            delete this.selection[this.anchor.id];this._removeAnchor();
                        }else{
                            if(this.current.id in this.selection){
                                this._removeItemClass(this.current,"Selected");delete this.selection[this.current.id];
                            }else{
                                if(this.anchor){
                                    this._removeItemClass(this.anchor,"Anchor");this._addItemClass(this.anchor,"Selected");
                                }this.anchor=this.current;this._addItemClass(this.current,"Anchor");this.selection[this.current.id]=1;
                            }
                            }
                        }else{
                        if(!(this.current.id in this.selection)){
                            this.selectNone();this.anchor=this.current;this._addItemClass(this.current,"Anchor");this.selection[this.current.id]=1;
                        }
                        }
                    }
                }dojo.stopEvent(e);
        },
        onMouseUp:function(e){
            if(!this.simpleSelection){
                return;
            }this.simpleSelection=false;this.selectNone();if(this.current){
                this.anchor=this.current;this._addItemClass(this.anchor,"Anchor");this.selection[this.current.id]=1;
            }
            },
        onMouseMove:function(e){
            this.simpleSelection=false;
        },
        onOverEvent:function(){
            this.onmousemoveEvent=dojo.connect(this.node,"onmousemove",this,"onMouseMove");
        },
        onOutEvent:function(){
            dojo.disconnect(this.onmousemoveEvent);delete this.onmousemoveEvent;
        },
        _removeSelection:function(){
            var e=dojo.dnd._empty;for(var i in this.selection){
                if(i in e){
                    continue;
                }var node=dojo.byId(i);if(node){
                    this._removeItemClass(node,"Selected");
                }
                }this.selection={};return this;
        },
        _removeAnchor:function(){
            if(this.anchor){
                this._removeItemClass(this.anchor,"Anchor");this.anchor=null;
            }return this;
        }
        });
}if(!dojo._hasResource["dojo.dnd.Avatar"]){
    dojo._hasResource["dojo.dnd.Avatar"]=true;dojo.provide("dojo.dnd.Avatar");dojo.declare("dojo.dnd.Avatar",null,{
        constructor:function(_2e7){
            this.manager=_2e7;this.construct();
        },
        construct:function(){
            this.isA11y=dojo.hasClass(dojo.body(),"dijit_a11y");var a=dojo.create("table",{
                "class":"dojoDndAvatar",
                style:{
                    position:"absolute",
                    zIndex:"1999",
                    margin:"0px"
                }
                }),_2e8=this.manager.source,node,b=dojo.create("tbody",null,a),tr=dojo.create("tr",null,b),td=dojo.create("td",null,tr),icon=this.isA11y?dojo.create("span",{
                id:"a11yIcon",
                innerHTML:this.manager.copy?"+":"<"
                },td):null,span=dojo.create("span",{
                innerHTML:_2e8.generateText?this._generateText():""
                },td),k=Math.min(5,this.manager.nodes.length),i=0;dojo.attr(tr,{
                "class":"dojoDndAvatarHeader",
                style:{
                    opacity:0.9
                }
                });for(;i<k;++i){
                if(_2e8.creator){
                    node=_2e8._normalizedCreator(_2e8.getItem(this.manager.nodes[i].id).data,"avatar").node;
                }else{
                    node=this.manager.nodes[i].cloneNode(true);if(node.tagName.toLowerCase()=="tr"){
                        var _2e9=dojo.create("table"),_2ea=dojo.create("tbody",null,_2e9);_2ea.appendChild(node);node=_2e9;
                    }
                    }node.id="";tr=dojo.create("tr",null,b);td=dojo.create("td",null,tr);td.appendChild(node);dojo.attr(tr,{
                    "class":"dojoDndAvatarItem",
                    style:{
                        opacity:(9-i)/10
                        }
                    });
            }this.node=a;
        },
        destroy:function(){
            dojo.destroy(this.node);this.node=false;
        },
        update:function(){
            dojo[(this.manager.canDropFlag?"add":"remove")+"Class"](this.node,"dojoDndAvatarCanDrop");if(this.isA11y){
                var icon=dojo.byId("a11yIcon");var text="+";if(this.manager.canDropFlag&&!this.manager.copy){
                    text="< ";
                }else{
                    if(!this.manager.canDropFlag&&!this.manager.copy){
                        text="o";
                    }else{
                        if(!this.manager.canDropFlag){
                            text="x";
                        }
                        }
                    }icon.innerHTML=text;
            }dojo.query(("tr.dojoDndAvatarHeader td span"+(this.isA11y?" span":"")),this.node).forEach(function(node){
                node.innerHTML=this._generateText();
            },this);
        },
        _generateText:function(){
            return this.manager.nodes.length.toString();
        }
        });
}if(!dojo._hasResource["dojo.dnd.Manager"]){
    dojo._hasResource["dojo.dnd.Manager"]=true;dojo.provide("dojo.dnd.Manager");dojo.declare("dojo.dnd.Manager",null,{
        constructor:function(){
            this.avatar=null;this.source=null;this.nodes=[];this.copy=true;this.target=null;this.canDropFlag=false;this.events=[];
        },
        OFFSET_X:16,
        OFFSET_Y:16,
        overSource:function(_2eb){
            if(this.avatar){
                this.target=(_2eb&&_2eb.targetState!="Disabled")?_2eb:null;this.canDropFlag=Boolean(this.target);this.avatar.update();
            }dojo.publish("/dnd/source/over",[_2eb]);
        },
        outSource:function(_2ec){
            if(this.avatar){
                if(this.target==_2ec){
                    this.target=null;this.canDropFlag=false;this.avatar.update();dojo.publish("/dnd/source/over",[null]);
                }
                }else{
                dojo.publish("/dnd/source/over",[null]);
            }
            },
        startDrag:function(_2ed,_2ee,copy){
            this.source=_2ed;this.nodes=_2ee;this.copy=Boolean(copy);this.avatar=this.makeAvatar();dojo.body().appendChild(this.avatar.node);dojo.publish("/dnd/start",[_2ed,_2ee,this.copy]);this.events=[dojo.connect(dojo.doc,"onmousemove",this,"onMouseMove"),dojo.connect(dojo.doc,"onmouseup",this,"onMouseUp"),dojo.connect(dojo.doc,"onkeydown",this,"onKeyDown"),dojo.connect(dojo.doc,"onkeyup",this,"onKeyUp"),dojo.connect(dojo.doc,"ondragstart",dojo.stopEvent),dojo.connect(dojo.body(),"onselectstart",dojo.stopEvent)];var c="dojoDnd"+(copy?"Copy":"Move");dojo.addClass(dojo.body(),c);
        },
        canDrop:function(flag){
            var _2ef=Boolean(this.target&&flag);if(this.canDropFlag!=_2ef){
                this.canDropFlag=_2ef;this.avatar.update();
            }
            },
        stopDrag:function(){
            dojo.removeClass(dojo.body(),"dojoDndCopy");dojo.removeClass(dojo.body(),"dojoDndMove");dojo.forEach(this.events,dojo.disconnect);this.events=[];this.avatar.destroy();this.avatar=null;this.source=this.target=null;this.nodes=[];
        },
        makeAvatar:function(){
            return new dojo.dnd.Avatar(this);
        },
        updateAvatar:function(){
            this.avatar.update();
        },
        onMouseMove:function(e){
            var a=this.avatar;if(a){
                dojo.dnd.autoScrollNodes(e);var s=a.node.style;s.left=(e.pageX+this.OFFSET_X)+"px";s.top=(e.pageY+this.OFFSET_Y)+"px";var copy=Boolean(this.source.copyState(dojo.isCopyKey(e)));if(this.copy!=copy){
                    this._setCopyStatus(copy);
                }
                }
            },
        onMouseUp:function(e){
            if(this.avatar){
                if(this.target&&this.canDropFlag){
                    var copy=Boolean(this.source.copyState(dojo.isCopyKey(e))),_2f0=[this.source,this.nodes,copy,this.target];dojo.publish("/dnd/drop/before",_2f0);dojo.publish("/dnd/drop",_2f0);
                }else{
                    dojo.publish("/dnd/cancel");
                }this.stopDrag();
            }
            },
        onKeyDown:function(e){
            if(this.avatar){
                switch(e.keyCode){
                    case dojo.keys.CTRL:var copy=Boolean(this.source.copyState(true));if(this.copy!=copy){
                        this._setCopyStatus(copy);
                    }break;case dojo.keys.ESCAPE:dojo.publish("/dnd/cancel");this.stopDrag();break;
                }
                }
            },
        onKeyUp:function(e){
            if(this.avatar&&e.keyCode==dojo.keys.CTRL){
                var copy=Boolean(this.source.copyState(false));if(this.copy!=copy){
                    this._setCopyStatus(copy);
                }
                }
            },
        _setCopyStatus:function(copy){
            this.copy=copy;this.source._markDndStatus(this.copy);this.updateAvatar();dojo.removeClass(dojo.body(),"dojoDnd"+(this.copy?"Move":"Copy"));dojo.addClass(dojo.body(),"dojoDnd"+(this.copy?"Copy":"Move"));
        }
        });dojo.dnd._manager=null;dojo.dnd.manager=function(){
        if(!dojo.dnd._manager){
            dojo.dnd._manager=new dojo.dnd.Manager();
        }return dojo.dnd._manager;
    };
}if(!dojo._hasResource["dojo.dnd.Source"]){
    dojo._hasResource["dojo.dnd.Source"]=true;dojo.provide("dojo.dnd.Source");dojo.declare("dojo.dnd.Source",dojo.dnd.Selector,{
        isSource:true,
        horizontal:false,
        copyOnly:false,
        selfCopy:false,
        selfAccept:true,
        skipForm:false,
        withHandles:false,
        autoSync:false,
        delay:0,
        accept:["text"],
        generateText:true,
        constructor:function(node,_2f1){
            dojo.mixin(this,dojo.mixin({},_2f1));var type=this.accept;if(type.length){
                this.accept={};for(var i=0;i<type.length;++i){
                    this.accept[type[i]]=1;
                }
                }this.isDragging=false;this.mouseDown=false;this.targetAnchor=null;this.targetBox=null;this.before=true;this._lastX=0;this._lastY=0;this.sourceState="";if(this.isSource){
                dojo.addClass(this.node,"dojoDndSource");
            }this.targetState="";if(this.accept){
                dojo.addClass(this.node,"dojoDndTarget");
            }if(this.horizontal){
                dojo.addClass(this.node,"dojoDndHorizontal");
            }this.topics=[dojo.subscribe("/dnd/source/over",this,"onDndSourceOver"),dojo.subscribe("/dnd/start",this,"onDndStart"),dojo.subscribe("/dnd/drop",this,"onDndDrop"),dojo.subscribe("/dnd/cancel",this,"onDndCancel")];
        },
        checkAcceptance:function(_2f2,_2f3){
            if(this==_2f2){
                return !this.copyOnly||this.selfAccept;
            }for(var i=0;i<_2f3.length;++i){
                var type=_2f2.getItem(_2f3[i].id).type;var flag=false;for(var j=0;j<type.length;++j){
                    if(type[j] in this.accept){
                        flag=true;break;
                    }
                    }if(!flag){
                    return false;
                }
                }return true;
        },
        copyState:function(_2f4,self){
            if(_2f4){
                return true;
            }if(arguments.length<2){
                self=this==dojo.dnd.manager().target;
            }if(self){
                if(this.copyOnly){
                    return this.selfCopy;
                }
                }else{
                return this.copyOnly;
            }return false;
        },
        destroy:function(){
            dojo.dnd.Source.superclass.destroy.call(this);dojo.forEach(this.topics,dojo.unsubscribe);this.targetAnchor=null;
        },
        markupFactory:function(_2f5,node){
            _2f5._skipStartup=true;return new dojo.dnd.Source(node,_2f5);
        },
        onMouseMove:function(e){
            if(this.isDragging&&this.targetState=="Disabled"){
                return;
            }dojo.dnd.Source.superclass.onMouseMove.call(this,e);var m=dojo.dnd.manager();if(this.isDragging){
                var _2f6=false;if(this.current){
                    if(!this.targetBox||this.targetAnchor!=this.current){
                        this.targetBox=dojo.position(this.current,true);
                    }if(this.horizontal){
                        _2f6=(e.pageX-this.targetBox.x)<(this.targetBox.w/2);
                    }else{
                        _2f6=(e.pageY-this.targetBox.y)<(this.targetBox.h/2);
                    }
                    }if(this.current!=this.targetAnchor||_2f6!=this.before){
                    this._markTargetAnchor(_2f6);m.canDrop(!this.current||m.source!=this||!(this.current.id in this.selection));
                }
                }else{
                if(this.mouseDown&&this.isSource&&(Math.abs(e.pageX-this._lastX)>this.delay||Math.abs(e.pageY-this._lastY)>this.delay)){
                    var _2f7=this.getSelectedNodes();if(_2f7.length){
                        m.startDrag(this,_2f7,this.copyState(dojo.isCopyKey(e),true));
                    }
                    }
                }
            },
        onMouseDown:function(e){
            if(!this.mouseDown&&this._legalMouseDown(e)&&(!this.skipForm||!dojo.dnd.isFormElement(e))){
                this.mouseDown=true;this._lastX=e.pageX;this._lastY=e.pageY;dojo.dnd.Source.superclass.onMouseDown.call(this,e);
            }
            },
        onMouseUp:function(e){
            if(this.mouseDown){
                this.mouseDown=false;dojo.dnd.Source.superclass.onMouseUp.call(this,e);
            }
            },
        onDndSourceOver:function(_2f8){
            if(this!=_2f8){
                this.mouseDown=false;if(this.targetAnchor){
                    this._unmarkTargetAnchor();
                }
                }else{
                if(this.isDragging){
                    var m=dojo.dnd.manager();m.canDrop(this.targetState!="Disabled"&&(!this.current||m.source!=this||!(this.current.id in this.selection)));
                }
                }
            },
        onDndStart:function(_2f9,_2fa,copy){
            if(this.autoSync){
                this.sync();
            }if(this.isSource){
                this._changeState("Source",this==_2f9?(copy?"Copied":"Moved"):"");
            }var _2fb=this.accept&&this.checkAcceptance(_2f9,_2fa);this._changeState("Target",_2fb?"":"Disabled");if(this==_2f9){
                dojo.dnd.manager().overSource(this);
            }this.isDragging=true;
        },
        onDndDrop:function(_2fc,_2fd,copy,_2fe){
            if(this==_2fe){
                this.onDrop(_2fc,_2fd,copy);
            }this.onDndCancel();
        },
        onDndCancel:function(){
            if(this.targetAnchor){
                this._unmarkTargetAnchor();this.targetAnchor=null;
            }this.before=true;this.isDragging=false;this.mouseDown=false;this._changeState("Source","");this._changeState("Target","");
        },
        onDrop:function(_2ff,_300,copy){
            if(this!=_2ff){
                this.onDropExternal(_2ff,_300,copy);
            }else{
                this.onDropInternal(_300,copy);
            }
            },
        onDropExternal:function(_301,_302,copy){
            var _303=this._normalizedCreator;if(this.creator){
                this._normalizedCreator=function(node,hint){
                    return _303.call(this,_301.getItem(node.id).data,hint);
                };
            }else{
                if(copy){
                    this._normalizedCreator=function(node,hint){
                        var t=_301.getItem(node.id);var n=node.cloneNode(true);n.id=dojo.dnd.getUniqueId();return {
                            node:n,
                            data:t.data,
                            type:t.type
                            };
                    };
                }else{
                    this._normalizedCreator=function(node,hint){
                        var t=_301.getItem(node.id);_301.delItem(node.id);return {
                            node:node,
                            data:t.data,
                            type:t.type
                            };
                    };
                }
                }this.selectNone();if(!copy&&!this.creator){
                _301.selectNone();
            }this.insertNodes(true,_302,this.before,this.current);if(!copy&&this.creator){
                _301.deleteSelectedNodes();
            }this._normalizedCreator=_303;
        },
        onDropInternal:function(_304,copy){
            var _305=this._normalizedCreator;if(this.current&&this.current.id in this.selection){
                return;
            }if(copy){
                if(this.creator){
                    this._normalizedCreator=function(node,hint){
                        return _305.call(this,this.getItem(node.id).data,hint);
                    };
                }else{
                    this._normalizedCreator=function(node,hint){
                        var t=this.getItem(node.id);var n=node.cloneNode(true);n.id=dojo.dnd.getUniqueId();return {
                            node:n,
                            data:t.data,
                            type:t.type
                            };
                    };
                }
                }else{
                if(!this.current){
                    return;
                }this._normalizedCreator=function(node,hint){
                    var t=this.getItem(node.id);return {
                        node:node,
                        data:t.data,
                        type:t.type
                        };
                };
            }this._removeSelection();this.insertNodes(true,_304,this.before,this.current);this._normalizedCreator=_305;
        },
        onDraggingOver:function(){},
        onDraggingOut:function(){},
        onOverEvent:function(){
            dojo.dnd.Source.superclass.onOverEvent.call(this);dojo.dnd.manager().overSource(this);if(this.isDragging&&this.targetState!="Disabled"){
                this.onDraggingOver();
            }
            },
        onOutEvent:function(){
            dojo.dnd.Source.superclass.onOutEvent.call(this);dojo.dnd.manager().outSource(this);if(this.isDragging&&this.targetState!="Disabled"){
                this.onDraggingOut();
            }
            },
        _markTargetAnchor:function(_306){
            if(this.current==this.targetAnchor&&this.before==_306){
                return;
            }if(this.targetAnchor){
                this._removeItemClass(this.targetAnchor,this.before?"Before":"After");
            }this.targetAnchor=this.current;this.targetBox=null;this.before=_306;if(this.targetAnchor){
                this._addItemClass(this.targetAnchor,this.before?"Before":"After");
            }
            },
        _unmarkTargetAnchor:function(){
            if(!this.targetAnchor){
                return;
            }this._removeItemClass(this.targetAnchor,this.before?"Before":"After");this.targetAnchor=null;this.targetBox=null;this.before=true;
        },
        _markDndStatus:function(copy){
            this._changeState("Source",copy?"Copied":"Moved");
        },
        _legalMouseDown:function(e){
            if(!dojo.mouseButtons.isLeft(e)){
                return false;
            }if(!this.withHandles){
                return true;
            }for(var node=e.target;node&&node!==this.node;node=node.parentNode){
                if(dojo.hasClass(node,"dojoDndHandle")){
                    return true;
                }if(dojo.hasClass(node,"dojoDndItem")||dojo.hasClass(node,"dojoDndIgnore")){
                    break;
                }
                }return false;
        }
        });dojo.declare("dojo.dnd.Target",dojo.dnd.Source,{
        constructor:function(node,_307){
            this.isSource=false;dojo.removeClass(this.node,"dojoDndSource");
        },
        markupFactory:function(_308,node){
            _308._skipStartup=true;return new dojo.dnd.Target(node,_308);
        }
        });dojo.declare("dojo.dnd.AutoSource",dojo.dnd.Source,{
        constructor:function(node,_309){
            this.autoSync=true;
        },
        markupFactory:function(_30a,node){
            _30a._skipStartup=true;return new dojo.dnd.AutoSource(node,_30a);
        }
        });
}if(!dojo._hasResource["dojox.grid._View"]){
    dojo._hasResource["dojox.grid._View"]=true;dojo.provide("dojox.grid._View");(function(){
        var _30b=function(_30c,_30d){
            return _30c.style.cssText==undefined?_30c.getAttribute("style"):_30c.style.cssText;
        };dojo.declare("dojox.grid._View",[dijit._Widget,dijit._Templated],{
            defaultWidth:"18em",
            viewWidth:"",
            templateString:"<div class=\"dojoxGridView\" wairole=\"presentation\">\n\t<div class=\"dojoxGridHeader\" dojoAttachPoint=\"headerNode\" wairole=\"presentation\">\n\t\t<div dojoAttachPoint=\"headerNodeContainer\" style=\"width:9000em\" wairole=\"presentation\">\n\t\t\t<div dojoAttachPoint=\"headerContentNode\" wairole=\"row\"></div>\n\t\t</div>\n\t</div>\n\t<input type=\"checkbox\" class=\"dojoxGridHiddenFocus\" dojoAttachPoint=\"hiddenFocusNode\" wairole=\"presentation\" />\n\t<input type=\"checkbox\" class=\"dojoxGridHiddenFocus\" wairole=\"presentation\" />\n\t<div class=\"dojoxGridScrollbox\" dojoAttachPoint=\"scrollboxNode\" wairole=\"presentation\">\n\t\t<div class=\"dojoxGridContent\" dojoAttachPoint=\"contentNode\" hidefocus=\"hidefocus\" wairole=\"presentation\"></div>\n\t</div>\n</div>\n",
            themeable:false,
            classTag:"dojoxGrid",
            marginBottom:0,
            rowPad:2,
            _togglingColumn:-1,
            _headerBuilderClass:dojox.grid._HeaderBuilder,
            _contentBuilderClass:dojox.grid._ContentBuilder,
            postMixInProperties:function(){
                this.rowNodes={};
            },
            postCreate:function(){
                //[duongtb:onchange,onkeypress event listener
                this.connect(this.scrollboxNode,"onscroll","doscroll");dojox.grid.util.funnelEvents(this.contentNode,this,"doContentEvent",["mouseover","mouseout","click","dblclick","contextmenu","mousedown","change","keypress"]);dojox.grid.util.funnelEvents(this.headerNode,this,"doHeaderEvent",["dblclick","mouseover","mouseout","mousemove","mousedown","click","contextmenu","change","keypress"]);this.content=new this._contentBuilderClass(this);this.header=new this._headerBuilderClass(this);if(!dojo._isBodyLtr()){
                    this.headerNodeContainer.style.width="";
                }
                //]
                },
            destroy:function(){
                dojo.destroy(this.headerNode);delete this.headerNode;for(var i in this.rowNodes){
                    dojo.destroy(this.rowNodes[i]);
                }this.rowNodes={};if(this.source){
                    this.source.destroy();
                }this.inherited(arguments);
            },
            focus:function(){
                if(dojo.isIE||dojo.isWebKit||dojo.isOpera){
                    this.hiddenFocusNode.focus();
                }else{
                    this.scrollboxNode.focus();
                }
                },
            setStructure:function(_30e){
                var vs=(this.structure=_30e);if(vs.width&&!isNaN(vs.width)){
                    this.viewWidth=vs.width+"em";
                }else{
                    this.viewWidth=vs.width||(vs.noscroll?"auto":this.viewWidth);
                }this._onBeforeRow=vs.onBeforeRow||function(){};this._onAfterRow=vs.onAfterRow||function(){};this.noscroll=vs.noscroll;if(this.noscroll){
                    this.scrollboxNode.style.overflow="hidden";
                }this.simpleStructure=Boolean(vs.cells.length==1);this.testFlexCells();this.updateStructure();
            },
            _cleanupRowWidgets:function(_30f){
                if(_30f){
                    dojo.forEach(dojo.query("[widgetId]",_30f).map(dijit.byNode),function(w){
                        if(w._destroyOnRemove){
                            w.destroy();delete w;
                        }else{
                            if(w.domNode&&w.domNode.parentNode){
                                w.domNode.parentNode.removeChild(w.domNode);
                            }
                            }
                        });
                }
                },
            onBeforeRow:function(_310,_311){
                this._onBeforeRow(_310,_311);if(_310>=0){
                    this._cleanupRowWidgets(this.getRowNode(_310));
                }
                },
            onAfterRow:function(_312,_313,_314){
                this._onAfterRow(_312,_313,_314);var g=this.grid;dojo.forEach(dojo.query(".dojoxGridStubNode",_314),function(n){
                    if(n&&n.parentNode){
                        var lw=n.getAttribute("linkWidget");var _315=window.parseInt(dojo.attr(n,"cellIdx"),10);var _316=g.getCell(_315);var w=dijit.byId(lw);if(w){
                            n.parentNode.replaceChild(w.domNode,n);if(!w._started){
                                w.startup();
                            }
                            }else{
                            n.innerHTML="";
                        }
                        }
                    },this);
            },
            testFlexCells:function(){
                this.flexCells=false;for(var j=0,row;(row=this.structure.cells[j]);j++){
                    for(var i=0,cell;(cell=row[i]);i++){
                        cell.view=this;this.flexCells=this.flexCells||cell.isFlex();
                    }
                    }return this.flexCells;
            },
            updateStructure:function(){
                this.header.update();this.content.update();
            },
            getScrollbarWidth:function(){
                var _317=this.hasVScrollbar();var _318=dojo.style(this.scrollboxNode,"overflow");if(this.noscroll||!_318||_318=="hidden"){
                    _317=false;
                }else{
                    if(_318=="scroll"){
                        _317=true;
                    }
                    }return (_317?dojox.html.metrics.getScrollbar().w:0);
            },
            getColumnsWidth:function(){
                var h=this.headerContentNode;return h&&h.firstChild?h.firstChild.offsetWidth:0;
            },
            setColumnsWidth:function(_319){
                this.headerContentNode.firstChild.style.width=_319+"px";if(this.viewWidth){
                    this.viewWidth=_319+"px";
                }
                },
            getWidth:function(){
                return this.viewWidth||(this.getColumnsWidth()+this.getScrollbarWidth())+"px";
            },
            getContentWidth:function(){
                return Math.max(0,dojo._getContentBox(this.domNode).w-this.getScrollbarWidth())+"px";
            },
            render:function(){
                this.scrollboxNode.style.height="";this.renderHeader();if(this._togglingColumn>=0){
                    this.setColumnsWidth(this.getColumnsWidth()-this._togglingColumn);this._togglingColumn=-1;
                }var _31a=this.grid.layout.cells;var _31b=dojo.hitch(this,function(node,_31c){
                    var inc=_31c?-1:1;var idx=this.header.getCellNodeIndex(node)+inc;var cell=_31a[idx];while(cell&&cell.getHeaderNode()&&cell.getHeaderNode().style.display=="none"){
                        idx+=inc;cell=_31a[idx];
                    }if(cell){
                        return cell.getHeaderNode();
                    }return null;
                });if(this.grid.columnReordering&&this.simpleStructure){
                    if(this.source){
                        this.source.destroy();
                    }var _31d="dojoxGrid_bottomMarker";var _31e="dojoxGrid_topMarker";if(this.bottomMarker){
                        dojo.destroy(this.bottomMarker);
                    }this.bottomMarker=dojo.byId(_31d);if(this.topMarker){
                        dojo.destroy(this.topMarker);
                    }this.topMarker=dojo.byId(_31e);if(!this.bottomMarker){
                        this.bottomMarker=dojo.create("div",{
                            "id":_31d,
                            "class":"dojoxGridColPlaceBottom"
                        },dojo.body());this._hide(this.bottomMarker);this.topMarker=dojo.create("div",{
                            "id":_31e,
                            "class":"dojoxGridColPlaceTop"
                        },dojo.body());this._hide(this.topMarker);
                    }this.arrowDim=dojo.contentBox(this.bottomMarker);var _31f=dojo.contentBox(this.headerContentNode.firstChild.rows[0]).h;this.source=new dojo.dnd.Source(this.headerContentNode.firstChild.rows[0],{
                        horizontal:true,
                        accept:["gridColumn_"+this.grid.id],
                        viewIndex:this.index,
                        generateText:false,
                        onMouseDown:dojo.hitch(this,function(e){
                            this.header.decorateEvent(e);if((this.header.overRightResizeArea(e)||this.header.overLeftResizeArea(e))&&this.header.canResize(e)&&!this.header.moveable){
                                this.header.beginColumnResize(e);
                            }else{
                                if(this.grid.headerMenu){
                                    this.grid.headerMenu.onCancel(true);
                                }if(e.button===(dojo.isIE?1:0)){
                                    dojo.dnd.Source.prototype.onMouseDown.call(this.source,e);
                                }
                                }
                            }),
                        onMouseOver:dojo.hitch(this,function(e){
                            var src=this.source;if(src._getChildByEvent(e)){
                                dojo.dnd.Source.prototype.onMouseOver.apply(src,arguments);
                            }
                            }),
                        _markTargetAnchor:dojo.hitch(this,function(_320){
                            var src=this.source;if(src.current==src.targetAnchor&&src.before==_320){
                                return;
                            }if(src.targetAnchor&&_31b(src.targetAnchor,src.before)){
                                src._removeItemClass(_31b(src.targetAnchor,src.before),src.before?"After":"Before");
                            }dojo.dnd.Source.prototype._markTargetAnchor.call(src,_320);var _321=_320?src.targetAnchor:_31b(src.targetAnchor,src.before);var _322=0;if(!_321){
                                _321=src.targetAnchor;_322=dojo.contentBox(_321).w+this.arrowDim.w/2+2;
                            }var pos=(dojo.position||dojo._abs)(_321,true);var left=Math.floor(pos.x-this.arrowDim.w/2+_322);dojo.style(this.bottomMarker,"visibility","visible");dojo.style(this.topMarker,"visibility","visible");dojo.style(this.bottomMarker,{
                                "left":left+"px",
                                "top":(_31f+pos.y)+"px"
                                });dojo.style(this.topMarker,{
                                "left":left+"px",
                                "top":(pos.y-this.arrowDim.h)+"px"
                                });if(src.targetAnchor&&_31b(src.targetAnchor,src.before)){
                                src._addItemClass(_31b(src.targetAnchor,src.before),src.before?"After":"Before");
                            }
                            }),
                        _unmarkTargetAnchor:dojo.hitch(this,function(){
                            var src=this.source;if(!src.targetAnchor){
                                return;
                            }if(src.targetAnchor&&_31b(src.targetAnchor,src.before)){
                                src._removeItemClass(_31b(src.targetAnchor,src.before),src.before?"After":"Before");
                            }this._hide(this.bottomMarker);this._hide(this.topMarker);dojo.dnd.Source.prototype._unmarkTargetAnchor.call(src);
                        }),
                        destroy:dojo.hitch(this,function(){
                            dojo.disconnect(this._source_conn);dojo.unsubscribe(this._source_sub);dojo.dnd.Source.prototype.destroy.call(this.source);if(this.bottomMarker){
                                dojo.destroy(this.bottomMarker);delete this.bottomMarker;
                            }if(this.topMarker){
                                dojo.destroy(this.topMarker);delete this.topMarker;
                            }
                            }),
                        onDndCancel:dojo.hitch(this,function(){
                            dojo.dnd.Source.prototype.onDndCancel.call(this.source);this._hide(this.bottomMarker);this._hide(this.topMarker);
                        })
                        });this._source_conn=dojo.connect(this.source,"onDndDrop",this,"_onDndDrop");this._source_sub=dojo.subscribe("/dnd/drop/before",this,"_onDndDropBefore");this.source.startup();
                }
                },
            _hide:function(node){
                dojo.style(node,{
                    left:"-10000px",
                    top:"-10000px",
                    "visibility":"hidden"
                });
            },
            _onDndDropBefore:function(_323,_324,copy){
                if(dojo.dnd.manager().target!==this.source){
                    return;
                }this.source._targetNode=this.source.targetAnchor;this.source._beforeTarget=this.source.before;var _325=this.grid.views.views;var _326=_325[_323.viewIndex];var _327=_325[this.index];if(_327!=_326){
                    _326.convertColPctToFixed();_327.convertColPctToFixed();
                }
                },
            _onDndDrop:function(_328,_329,copy){
                if(dojo.dnd.manager().target!==this.source){
                    if(dojo.dnd.manager().source===this.source){
                        this._removingColumn=true;
                    }return;
                }this._hide(this.bottomMarker);this._hide(this.topMarker);var _32a=function(n){
                    return n?dojo.attr(n,"idx"):null;
                };var w=dojo.marginBox(_329[0]).w;if(_328.viewIndex!==this.index){
                    var _32b=this.grid.views.views;var _32c=_32b[_328.viewIndex];var _32d=_32b[this.index];if(_32c.viewWidth&&_32c.viewWidth!="auto"){
                        _32c.setColumnsWidth(_32c.getColumnsWidth()-w);
                    }if(_32d.viewWidth&&_32d.viewWidth!="auto"){
                        _32d.setColumnsWidth(_32d.getColumnsWidth());
                    }
                    }var stn=this.source._targetNode;var stb=this.source._beforeTarget;var _32e=this.grid.layout;var idx=this.index;delete this.source._targetNode;delete this.source._beforeTarget;_32e.moveColumn(_328.viewIndex,idx,_32a(_329[0]),_32a(stn),stb);
            },
            renderHeader:function(){
                this.headerContentNode.innerHTML=this.header.generateHtml(this._getHeaderContent);if(this.flexCells){
                    this.contentWidth=this.getContentWidth();this.headerContentNode.firstChild.style.width=this.contentWidth;
                }dojox.grid.util.fire(this,"onAfterRow",[-1,this.structure.cells,this.headerContentNode]);
            },
            _getHeaderContent:function(_32f){
                var n=_32f.name||_32f.grid.getCellName(_32f);var ret=["<div class=\"dojoxGridSortNode"];if(_32f.index!=_32f.grid.getSortIndex()){
                    ret.push("\">");
                }else{
                    ret=ret.concat([" ",_32f.grid.sortInfo>0?"dojoxGridSortUp":"dojoxGridSortDown","\"><div class=\"dojoxGridArrowButtonChar\">",_32f.grid.sortInfo>0?"&#9650;":"&#9660;","</div><div class=\"dojoxGridArrowButtonNode\" role=\"presentation\"></div>"]);
                }ret=ret.concat([n,"</div>"]);return ret.join("");
            },
            resize:function(){
                this.adaptHeight();this.adaptWidth();
            },
            hasHScrollbar:function(_330){
                var _331=this._hasHScroll||false;if(this._hasHScroll==undefined||_330){
                    if(this.noscroll){
                        this._hasHScroll=false;
                    }else{
                        var _332=dojo.style(this.scrollboxNode,"overflow");if(_332=="hidden"){
                            this._hasHScroll=false;
                        }else{
                            if(_332=="scroll"){
                                this._hasHScroll=true;
                            }else{
                                this._hasHScroll=(this.scrollboxNode.offsetWidth-this.getScrollbarWidth()<this.contentNode.offsetWidth);
                            }
                            }
                        }
                    }if(_331!==this._hasHScroll){
                    this.grid.update();
                }return this._hasHScroll;
            },
            hasVScrollbar:function(_333){
                var _334=this._hasVScroll||false;if(this._hasVScroll==undefined||_333){
                    if(this.noscroll){
                        this._hasVScroll=false;
                    }else{
                        var _335=dojo.style(this.scrollboxNode,"overflow");if(_335=="hidden"){
                            this._hasVScroll=false;
                        }else{
                            if(_335=="scroll"){
                                this._hasVScroll=true;
                            }else{
                                this._hasVScroll=(this.scrollboxNode.scrollHeight>this.scrollboxNode.clientHeight);
                            }
                            }
                        }
                    }if(_334!==this._hasVScroll){
                    this.grid.update();
                }return this._hasVScroll;
            },
            convertColPctToFixed:function(){
                var _336=false;this.grid.initialWidth="";var _337=dojo.query("th",this.headerContentNode);var _338=dojo.map(_337,function(c,vIdx){
                    var w=c.style.width;dojo.attr(c,"vIdx",vIdx);if(w&&w.slice(-1)=="%"){
                        _336=true;
                    }else{
                        if(w&&w.slice(-2)=="px"){
                            return window.parseInt(w,10);
                        }
                        }return dojo.contentBox(c).w;
                });if(_336){
                    dojo.forEach(this.grid.layout.cells,function(cell,idx){
                        if(cell.view==this){
                            var _339=cell.view.getHeaderCellNode(cell.index);if(_339&&dojo.hasAttr(_339,"vIdx")){
                                var vIdx=window.parseInt(dojo.attr(_339,"vIdx"));this.setColWidth(idx,_338[vIdx]);dojo.removeAttr(_339,"vIdx");
                            }
                            }
                        },this);return true;
                }return false;
            },
            adaptHeight:function(_33a){
                if(!this.grid._autoHeight){
                    var h=(this.domNode.style.height&&parseInt(this.domNode.style.height.replace(/px/,""),10))||this.domNode.clientHeight;var self=this;var _33b=function(){
                        var v;for(var i in self.grid.views.views){
                            v=self.grid.views.views[i];if(v!==self&&v.hasHScrollbar()){
                                return true;
                            }
                            }return false;
                    };if(_33a||(this.noscroll&&_33b())){
                        h-=dojox.html.metrics.getScrollbar().h;
                    }dojox.grid.util.setStyleHeightPx(this.scrollboxNode,h);
                }this.hasVScrollbar(true);
            },
            adaptWidth:function(){
                if(this.flexCells){
                    this.contentWidth=this.getContentWidth();this.headerContentNode.firstChild.style.width=this.contentWidth;
                }var w=this.scrollboxNode.offsetWidth-this.getScrollbarWidth();if(!this._removingColumn){
                    w=Math.max(w,this.getColumnsWidth())+"px";
                }else{
                    w=Math.min(w,this.getColumnsWidth())+"px";this._removingColumn=false;
                }var cn=this.contentNode;cn.style.width=w;this.hasHScrollbar(true);
            },
            setSize:function(w,h){
                var ds=this.domNode.style;var hs=this.headerNode.style;if(w){
                    ds.width=w;hs.width=w;
                }ds.height=(h>=0?h+"px":"");
            },
            renderRow:function(_33c){
                var _33d=this.createRowNode(_33c);this.buildRow(_33c,_33d);this.grid.edit.restore(this,_33c);return _33d;
            },
            createRowNode:function(_33e){
                var node=document.createElement("div");node.className=this.classTag+"Row";if(this instanceof dojox.grid._RowSelector){
                    dojo.attr(node,"role","presentation");
                }else{
                    dojo.attr(node,"role","row");if(this.grid.selectionMode!="none"){
                        dojo.attr(node,"aria-selected","false");
                    }
                    }node[dojox.grid.util.gridViewTag]=this.id;node[dojox.grid.util.rowIndexTag]=_33e;this.rowNodes[_33e]=node;return node;
            },
            buildRow:function(_33f,_340){
                this.buildRowContent(_33f,_340);this.styleRow(_33f,_340);
            },
            buildRowContent:function(_341,_342){
                _342.innerHTML=this.content.generateHtml(_341,_341);if(this.flexCells&&this.contentWidth){
                    _342.firstChild.style.width=this.contentWidth;
                }dojox.grid.util.fire(this,"onAfterRow",[_341,this.structure.cells,_342]);
            },
            rowRemoved:function(_343){
                if(_343>=0){
                    this._cleanupRowWidgets(this.getRowNode(_343));
                }this.grid.edit.save(this,_343);delete this.rowNodes[_343];
            },
            getRowNode:function(_344){
                return this.rowNodes[_344];
            },
            getCellNode:function(_345,_346){
                var row=this.getRowNode(_345);if(row){
                    return this.content.getCellNode(row,_346);
                }
                },
            getHeaderCellNode:function(_347){
                if(this.headerContentNode){
                    return this.header.getCellNode(this.headerContentNode,_347);
                }
                },
            styleRow:function(_348,_349){
                _349._style=_30b(_349);this.styleRowNode(_348,_349);
            },
            styleRowNode:function(_34a,_34b){
                if(_34b){
                    this.doStyleRowNode(_34a,_34b);
                }
                },
            doStyleRowNode:function(_34c,_34d){
                this.grid.styleRowNode(_34c,_34d);
            },
            updateRow:function(_34e){
                var _34f=this.getRowNode(_34e);if(_34f){
                    _34f.style.height="";this.buildRow(_34e,_34f);
                }return _34f;
            },
            updateRowStyles:function(_350){
                this.styleRowNode(_350,this.getRowNode(_350));
            },
            lastTop:0,
            firstScroll:0,
            doscroll:function(_351){
                var _352=dojo._isBodyLtr();if(this.firstScroll<2){
                    if((!_352&&this.firstScroll==1)||(_352&&this.firstScroll===0)){
                        var s=dojo.marginBox(this.headerNodeContainer);if(dojo.isIE){
                            this.headerNodeContainer.style.width=s.w+this.getScrollbarWidth()+"px";
                        }else{
                            if(dojo.isMoz){
                                this.headerNodeContainer.style.width=s.w-this.getScrollbarWidth()+"px";this.scrollboxNode.scrollLeft=_352?this.scrollboxNode.clientWidth-this.scrollboxNode.scrollWidth:this.scrollboxNode.scrollWidth-this.scrollboxNode.clientWidth;
                            }
                            }
                        }this.firstScroll++;
                }this.headerNode.scrollLeft=this.scrollboxNode.scrollLeft;var top=this.scrollboxNode.scrollTop;if(top!==this.lastTop){
                    this.grid.scrollTo(top);
                }
                },
            setScrollTop:function(_353){
                this.lastTop=_353;this.scrollboxNode.scrollTop=_353;return this.scrollboxNode.scrollTop;
            },
            doContentEvent:function(e){
                if(this.content.decorateEvent(e)){
                    this.grid.onContentEvent(e);
                }
                },
            doHeaderEvent:function(e){
                if(this.header.decorateEvent(e)){
                    this.grid.onHeaderEvent(e);
                }
                },
            dispatchContentEvent:function(e){
                return this.content.dispatchEvent(e);
            },
            dispatchHeaderEvent:function(e){
                return this.header.dispatchEvent(e);
            },
            setColWidth:function(_354,_355){
                this.grid.setCellWidth(_354,_355+"px");
            },
            update:function(){
                if(!this.domNode){
                    return;
                }this.content.update();this.grid.update();var left=this.scrollboxNode.scrollLeft;this.scrollboxNode.scrollLeft=left;this.headerNode.scrollLeft=left;
            }
            });dojo.declare("dojox.grid._GridAvatar",dojo.dnd.Avatar,{
            construct:function(){
                var dd=dojo.doc;var a=dd.createElement("table");a.cellPadding=a.cellSpacing="0";a.className="dojoxGridDndAvatar";a.style.position="absolute";a.style.zIndex=1999;a.style.margin="0px";var b=dd.createElement("tbody");var tr=dd.createElement("tr");var td=dd.createElement("td");var img=dd.createElement("td");tr.className="dojoxGridDndAvatarItem";img.className="dojoxGridDndAvatarItemImage";img.style.width="16px";var _356=this.manager.source,node;if(_356.creator){
                    node=_356._normalizedCreator(_356.getItem(this.manager.nodes[0].id).data,"avatar").node;
                }else{
                    node=this.manager.nodes[0].cloneNode(true);var _357,_358;if(node.tagName.toLowerCase()=="tr"){
                        _357=dd.createElement("table");_358=dd.createElement("tbody");_358.appendChild(node);_357.appendChild(_358);node=_357;
                    }else{
                        if(node.tagName.toLowerCase()=="th"){
                            _357=dd.createElement("table");_358=dd.createElement("tbody");var r=dd.createElement("tr");_357.cellPadding=_357.cellSpacing="0";r.appendChild(node);_358.appendChild(r);_357.appendChild(_358);node=_357;
                        }
                        }
                    }node.id="";td.appendChild(node);tr.appendChild(img);tr.appendChild(td);dojo.style(tr,"opacity",0.9);b.appendChild(tr);a.appendChild(b);this.node=a;var m=dojo.dnd.manager();this.oldOffsetY=m.OFFSET_Y;m.OFFSET_Y=1;
            },
            destroy:function(){
                dojo.dnd.manager().OFFSET_Y=this.oldOffsetY;this.inherited(arguments);
            }
            });var _359=dojo.dnd.manager().makeAvatar;dojo.dnd.manager().makeAvatar=function(){
            var src=this.source;if(src.viewIndex!==undefined&&!dojo.hasClass(dojo.body(),"dijit_a11y")){
                return new dojox.grid._GridAvatar(this);
            }return _359.call(dojo.dnd.manager());
        };
    })();
}if(!dojo._hasResource["dojox.grid._RowSelector"]){
    dojo._hasResource["dojox.grid._RowSelector"]=true;dojo.provide("dojox.grid._RowSelector");dojo.declare("dojox.grid._RowSelector",dojox.grid._View,{
        defaultWidth:"2em",
        noscroll:true,
        padBorderWidth:2,
        buildRendering:function(){
            this.inherited("buildRendering",arguments);this.scrollboxNode.style.overflow="hidden";this.headerNode.style.visibility="hidden";
        },
        getWidth:function(){
            return this.viewWidth||this.defaultWidth;
        },
        buildRowContent:function(_35a,_35b){
            var w=this.contentWidth||0;_35b.innerHTML="<table class=\"dojoxGridRowbarTable\" style=\"width:"+w+"px;height:1px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"><tr><td class=\"dojoxGridRowbarInner\">&nbsp;</td></tr></table>";
        },
        renderHeader:function(){},
        updateRow:function(){},
        resize:function(){
            this.adaptHeight();
        },
        adaptWidth:function(){
            if(!("contentWidth" in this)&&this.contentNode){
                this.contentWidth=this.contentNode.offsetWidth-this.padBorderWidth;
            }
            },
        doStyleRowNode:function(_35c,_35d){
            var n=["dojoxGridRowbar dojoxGridNonNormalizedCell"];if(this.grid.rows.isOver(_35c)){
                n.push("dojoxGridRowbarOver");
            }if(this.grid.selection.isSelected(_35c)){
                n.push("dojoxGridRowbarSelected");
            }_35d.className=n.join(" ");
        },
        domouseover:function(e){
            this.grid.onMouseOverRow(e);
        },
        domouseout:function(e){
            if(!this.isIntraRowEvent(e)){
                this.grid.onMouseOutRow(e);
            }
            }
        });
}if(!dojo._hasResource["dojox.grid._Layout"]){
    dojo._hasResource["dojox.grid._Layout"]=true;dojo.provide("dojox.grid._Layout");dojo.declare("dojox.grid._Layout",null,{
        constructor:function(_35e){
            this.grid=_35e;
        },
        cells:[],
        structure:null,
        defaultWidth:"6em",
        moveColumn:function(_35f,_360,_361,_362,_363){
            var _364=this.structure[_35f].cells[0];var _365=this.structure[_360].cells[0];var cell=null;var _366=0;var _367=0;for(var i=0,c;c=_364[i];i++){
                if(c.index==_361){
                    _366=i;break;
                }
                }cell=_364.splice(_366,1)[0];cell.view=this.grid.views.views[_360];for(i=0,c=null;c=_365[i];i++){
                if(c.index==_362){
                    _367=i;break;
                }
                }if(!_363){
                _367+=1;
            }_365.splice(_367,0,cell);var _368=this.grid.getCell(this.grid.getSortIndex());if(_368){
                _368._currentlySorted=this.grid.getSortAsc();
            }this.cells=[];_361=0;var v;for(i=0;v=this.structure[i];i++){
                for(var j=0,cs;cs=v.cells[j];j++){
                    for(var k=0;c=cs[k];k++){
                        c.index=_361;this.cells.push(c);if("_currentlySorted" in c){
                            var si=_361+1;si*=c._currentlySorted?1:-1;this.grid.sortInfo=si;delete c._currentlySorted;
                        }_361++;
                    }
                    }
                }this.grid.setupHeaderMenu();
        },
        setColumnVisibility:function(_369,_36a){
            var cell=this.cells[_369];if(cell.hidden==_36a){
                cell.hidden=!_36a;var v=cell.view,w=v.viewWidth;if(w&&w!="auto"){
                    v._togglingColumn=dojo.marginBox(cell.getHeaderNode()).w||0;
                }v.update();return true;
            }else{
                return false;
            }
            },
        addCellDef:function(_36b,_36c,_36d){
            var self=this;var _36e=function(_36f){
                var w=0;if(_36f.colSpan>1){
                    w=0;
                }else{
                    w=_36f.width||self._defaultCellProps.width||self.defaultWidth;if(!isNaN(w)){
                        w=w+"em";
                    }
                    }return w;
            };var _370={
                grid:this.grid,
                subrow:_36b,
                layoutIndex:_36c,
                index:this.cells.length
                };if(_36d&&_36d instanceof dojox.grid.cells._Base){
                var _371=dojo.clone(_36d);_370.unitWidth=_36e(_371._props);_371=dojo.mixin(_371,this._defaultCellProps,_36d._props,_370);return _371;
            }var _372=_36d.type||this._defaultCellProps.type||dojox.grid.cells.Cell;
//            [duongtb: for fixing default cell when setting alwaysEditing
              if(_372 == dojox.grid.cells.Cell && _36d.alwaysEditing==true){
                    _372 = dojox.grid.cells.Text;
                }
//            ]
            _370.unitWidth=_36e(_36d);return new _372(dojo.mixin({},
            this._defaultCellProps,_36d,_370));
        },
        addRowDef:function(_373,_374){
            var _375=[];var _376=0,_377=0,_378=true;for(var i=0,def,cell;(def=_374[i]);i++){
                cell=this.addCellDef(_373,i,def);_375.push(cell);this.cells.push(cell);if(_378&&cell.relWidth){
                    _376+=cell.relWidth;
                }else{
                    if(cell.width){
                        var w=cell.width;if(typeof w=="string"&&w.slice(-1)=="%"){
                            _377+=window.parseInt(w,10);
                        }else{
                            if(w=="auto"){
                                _378=false;
                            }
                            }
                        }
                    }
                }if(_376&&_378){
                dojo.forEach(_375,function(cell){
                    if(cell.relWidth){
                        cell.width=cell.unitWidth=((cell.relWidth/_376)*(100-_377))+"%";
                    }
                    });
            }return _375;
        },
        addRowsDef:function(_379){
            var _37a=[];if(dojo.isArray(_379)){
                if(dojo.isArray(_379[0])){
                    for(var i=0,row;_379&&(row=_379[i]);i++){
                        _37a.push(this.addRowDef(i,row));
                    }
                    }else{
                    _37a.push(this.addRowDef(0,_379));
                }
                }return _37a;
        },
        addViewDef:function(_37b){
            this._defaultCellProps=_37b.defaultCell||{};if(_37b.width&&_37b.width=="auto"){
                delete _37b.width;
            }return dojo.mixin({},_37b,{
                cells:this.addRowsDef(_37b.rows||_37b.cells)
                });
        },
        setStructure:function(_37c){
            this.fieldIndex=0;this.cells=[];var s=this.structure=[];if(this.grid.rowSelector){
                var sel={
                    type:dojox._scopeName+".grid._RowSelector"
                    };if(dojo.isString(this.grid.rowSelector)){
                    var _37d=this.grid.rowSelector;if(_37d=="false"){
                        sel=null;
                    }else{
                        if(_37d!="true"){
                            sel["width"]=_37d;
                        }
                        }
                    }else{
                    if(!this.grid.rowSelector){
                        sel=null;
                    }
                    }if(sel){
                    s.push(this.addViewDef(sel));
                }
                }var _37e=function(def){
                return ("name" in def||"field" in def||"get" in def);
            };var _37f=function(def){
                if(dojo.isArray(def)){
                    if(dojo.isArray(def[0])||_37e(def[0])){
                        return true;
                    }
                    }return false;
            };var _380=function(def){
                return (def!==null&&dojo.isObject(def)&&("cells" in def||"rows" in def||("type" in def&&!_37e(def))));
            };if(dojo.isArray(_37c)){
                var _381=false;for(var i=0,st;(st=_37c[i]);i++){
                    if(_380(st)){
                        _381=true;break;
                    }
                    }if(!_381){
                    s.push(this.addViewDef({
                        cells:_37c
                    }));
                }else{
                    for(i=0;(st=_37c[i]);i++){
                        if(_37f(st)){
                            s.push(this.addViewDef({
                                cells:st
                            }));
                        }else{
                            if(_380(st)){
                                s.push(this.addViewDef(st));
                            }
                            }
                        }
                    }
                }else{
                if(_380(_37c)){
                    s.push(this.addViewDef(_37c));
                }
                }this.cellCount=this.cells.length;this.grid.setupHeaderMenu();
        }
        });
}if(!dojo._hasResource["dojox.grid._ViewManager"]){
    dojo._hasResource["dojox.grid._ViewManager"]=true;dojo.provide("dojox.grid._ViewManager");dojo.declare("dojox.grid._ViewManager",null,{
        constructor:function(_382){
            this.grid=_382;
        },
        defaultWidth:200,
        views:[],
        resize:function(){
            this.onEach("resize");
        },
        render:function(){
            this.onEach("render");
        },
        addView:function(_383){
            _383.idx=this.views.length;this.views.push(_383);
        },
        destroyViews:function(){
            for(var i=0,v;v=this.views[i];i++){
                v.destroy();
            }this.views=[];
        },
        getContentNodes:function(){
            var _384=[];for(var i=0,v;v=this.views[i];i++){
                _384.push(v.contentNode);
            }return _384;
        },
        forEach:function(_385){
            for(var i=0,v;v=this.views[i];i++){
                _385(v,i);
            }
            },
        onEach:function(_386,_387){
            _387=_387||[];for(var i=0,v;v=this.views[i];i++){
                if(_386 in v){
                    v[_386].apply(v,_387);
                }
                }
            },
        normalizeHeaderNodeHeight:function(){
            var _388=[];for(var i=0,v;(v=this.views[i]);i++){
                if(v.headerContentNode.firstChild){
                    _388.push(v.headerContentNode);
                }
                }this.normalizeRowNodeHeights(_388);
        },
        normalizeRowNodeHeights:function(_389){
            var h=0;var _38a=[];if(this.grid.rowHeight){
                h=this.grid.rowHeight;
            }else{
                if(_389.length<=1){
                    return;
                }for(var i=0,n;(n=_389[i]);i++){
                    if(!dojo.hasClass(n,"dojoxGridNonNormalizedCell")){
                        _38a[i]=n.firstChild.offsetHeight;h=Math.max(h,_38a[i]);
                    }
                    }h=(h>=0?h:0);if(dojo.isMoz&&h){
                    h++;
                }
                }for(i=0;(n=_389[i]);i++){
                if(_38a[i]!=h){
                    n.firstChild.style.height=h+"px";
                }
                }
            },
        resetHeaderNodeHeight:function(){
            for(var i=0,v,n;(v=this.views[i]);i++){
                n=v.headerContentNode.firstChild;if(n){
                    n.style.height="";
                }
                }
            },
        renormalizeRow:function(_38b){
            var _38c=[];for(var i=0,v,n;(v=this.views[i])&&(n=v.getRowNode(_38b));i++){
                n.firstChild.style.height="";_38c.push(n);
            }this.normalizeRowNodeHeights(_38c);
        },
        getViewWidth:function(_38d){
            return this.views[_38d].getWidth()||this.defaultWidth;
        },
        measureHeader:function(){
            this.resetHeaderNodeHeight();this.forEach(function(_38e){
                _38e.headerContentNode.style.height="";
            });var h=0;this.forEach(function(_38f){
                h=Math.max(_38f.headerNode.offsetHeight,h);
            });return h;
        },
        measureContent:function(){
            var h=0;this.forEach(function(_390){
                h=Math.max(_390.domNode.offsetHeight,h);
            });return h;
        },
        findClient:function(_391){
            var c=this.grid.elasticView||-1;if(c<0){
                for(var i=1,v;(v=this.views[i]);i++){
                    if(v.viewWidth){
                        for(i=1;(v=this.views[i]);i++){
                            if(!v.viewWidth){
                                c=i;break;
                            }
                            }break;
                    }
                    }
                }if(c<0){
                c=Math.floor(this.views.length/2);
            }return c;
        },
        arrange:function(l,w){
            var i,v,vw,len=this.views.length;var c=(w<=0?len:this.findClient());var _392=function(v,l){
                var ds=v.domNode.style;var hs=v.headerNode.style;if(!dojo._isBodyLtr()){
                    ds.right=l+"px";if(dojo.isMoz){
                        hs.right=l+v.getScrollbarWidth()+"px";hs.width=parseInt(hs.width,10)-v.getScrollbarWidth()+"px";
                    }else{
                        hs.right=l+"px";
                    }
                    }else{
                    ds.left=l+"px";hs.left=l+"px";
                }ds.top=0+"px";hs.top=0;
            };for(i=0;(v=this.views[i])&&(i<c);i++){
                vw=this.getViewWidth(i);v.setSize(vw,0);_392(v,l);if(v.headerContentNode&&v.headerContentNode.firstChild){
                    vw=v.getColumnsWidth()+v.getScrollbarWidth();
                }else{
                    vw=v.domNode.offsetWidth;
                }l+=vw;
            }i++;var r=w;for(var j=len-1;(v=this.views[j])&&(i<=j);j--){
                vw=this.getViewWidth(j);v.setSize(vw,0);vw=v.domNode.offsetWidth;r-=vw;_392(v,r);
            }if(c<len){
                v=this.views[c];vw=Math.max(1,r-l);v.setSize(vw+"px",0);_392(v,l);
            }return l;
        },
        renderRow:function(_393,_394,_395){
            var _396=[];for(var i=0,v,n,_397;(v=this.views[i])&&(n=_394[i]);i++){
                _397=v.renderRow(_393);n.appendChild(_397);_396.push(_397);
            }if(!_395){
                this.normalizeRowNodeHeights(_396);
            }
            },
        rowRemoved:function(_398){
            this.onEach("rowRemoved",[_398]);
        },
        updateRow:function(_399,_39a){
            for(var i=0,v;v=this.views[i];i++){
                v.updateRow(_399);
            }if(!_39a){
                this.renormalizeRow(_399);
            }
            },
        updateRowStyles:function(_39b){
            this.onEach("updateRowStyles",[_39b]);
        },
        setScrollTop:function(_39c){
            var top=_39c;for(var i=0,v;v=this.views[i];i++){
                top=v.setScrollTop(_39c);if(dojo.isIE&&v.headerNode&&v.scrollboxNode){
                    v.headerNode.scrollLeft=v.scrollboxNode.scrollLeft;
                }
                }return top;
        },
        getFirstScrollingView:function(){
            for(var i=0,v;(v=this.views[i]);i++){
                if(v.hasHScrollbar()||v.hasVScrollbar()){
                    return v;
                }
                }return null;
        }
        });
}if(!dojo._hasResource["dojox.grid._RowManager"]){
    dojo._hasResource["dojox.grid._RowManager"]=true;dojo.provide("dojox.grid._RowManager");(function(){
        var _39d=function(_39e,_39f){
            if(_39e.style.cssText==undefined){
                _39e.setAttribute("style",_39f);
            }else{
                _39e.style.cssText=_39f;
            }
            };dojo.declare("dojox.grid._RowManager",null,{
            constructor:function(_3a0){
                this.grid=_3a0;
            },
            linesToEms:2,
            overRow:-2,
            prepareStylingRow:function(_3a1,_3a2){
                return {
                    index:_3a1,
                    node:_3a2,
                    odd:Boolean(_3a1&1),
                    selected:!!this.grid.selection.isSelected(_3a1),
                    over:this.isOver(_3a1),
                    customStyles:"",
                    customClasses:"dojoxGridRow"
                };
            },
            styleRowNode:function(_3a3,_3a4){
                var row=this.prepareStylingRow(_3a3,_3a4);this.grid.onStyleRow(row);this.applyStyles(row);
            },
            applyStyles:function(_3a5){
                var i=_3a5;i.node.className=i.customClasses;var h=i.node.style.height;_39d(i.node,i.customStyles+";"+(i.node._style||""));i.node.style.height=h;
            },
            updateStyles:function(_3a6){
                this.grid.updateRowStyles(_3a6);
            },
            setOverRow:function(_3a7){
                var last=this.overRow;this.overRow=_3a7;if((last!=this.overRow)&&(dojo.isString(last)||last>=0)){
                    this.updateStyles(last);
                }this.updateStyles(this.overRow);
            },
            isOver:function(_3a8){
                return (this.overRow==_3a8&&!dojo.hasClass(this.grid.domNode,"dojoxGridColumnResizing"));
            }
            });
    })();
}if(!dojo._hasResource["dojox.grid._FocusManager"]){
    dojo._hasResource["dojox.grid._FocusManager"]=true;dojo.provide("dojox.grid._FocusManager");dojo.declare("dojox.grid._FocusManager",null,{
        constructor:function(_3a9){
            this.grid=_3a9;this.cell=null;this.rowIndex=-1;this._connects=[];this._connects.push(dojo.connect(this.grid.domNode,"onfocus",this,"doFocus"));this._connects.push(dojo.connect(this.grid.domNode,"onblur",this,"doBlur"));this._connects.push(dojo.connect(this.grid.lastFocusNode,"onfocus",this,"doLastNodeFocus"));this._connects.push(dojo.connect(this.grid.lastFocusNode,"onblur",this,"doLastNodeBlur"));this._connects.push(dojo.connect(this.grid,"_onFetchComplete",this,"_delayedCellFocus"));this._connects.push(dojo.connect(this.grid,"postrender",this,"_delayedHeaderFocus"));
        },
        destroy:function(){
            dojo.forEach(this._connects,dojo.disconnect);delete this.grid;delete this.cell;
        },
        _colHeadNode:null,
        _colHeadFocusIdx:null,
        tabbingOut:false,
        focusClass:"dojoxGridCellFocus",
        focusView:null,
        initFocusView:function(){
            this.focusView=this.grid.views.getFirstScrollingView()||this.focusView;this._initColumnHeaders();
        },
        isFocusCell:function(_3aa,_3ab){
            return (this.cell==_3aa)&&(this.rowIndex==_3ab);
        },
        isLastFocusCell:function(){
            if(this.cell){
                return (this.rowIndex==this.grid.rowCount-1)&&(this.cell.index==this.grid.layout.cellCount-1);
            }return false;
        },
        isFirstFocusCell:function(){
            if(this.cell){
                return (this.rowIndex===0)&&(this.cell.index===0);
            }return false;
        },
        isNoFocusCell:function(){
            return (this.rowIndex<0)||!this.cell;
        },
        isNavHeader:function(){
            return (!!this._colHeadNode);
        },
        getHeaderIndex:function(){
            if(this._colHeadNode){
                return dojo.indexOf(this._findHeaderCells(),this._colHeadNode);
            }else{
                return -1;
            }
            },
        _focusifyCellNode:function(_3ac){
            var n=this.cell&&this.cell.getNode(this.rowIndex);if(n){
                dojo.toggleClass(n,this.focusClass,_3ac);if(_3ac){
                    var sl=this.scrollIntoView();try{
                        if(!this.grid.edit.isEditing()){
                            dojox.grid.util.fire(n,"focus");if(sl){
                                this.cell.view.scrollboxNode.scrollLeft=sl;
                            }
                            }
                        }catch(e){}
                    }
                }
            },
        _delayedCellFocus:function(){
            if(this.isNavHeader()||!this.grid._focused){
                return;
            }var n=this.cell&&this.cell.getNode(this.rowIndex);if(n){
                try{
                    if(!this.grid.edit.isEditing()){
                        dojo.toggleClass(n,this.focusClass,true);dojo.removeAttr(this.grid.domNode,"aria-activedescendant");dojox.grid.util.fire(n,"focus");
                    }
                    }catch(e){}
                }
            },
        _delayedHeaderFocus:function(){
            if(this.isNavHeader()){
                this.focusHeader();this.grid.domNode.focus();
            }
            },
        _initColumnHeaders:function(){
            var _3ad=this._findHeaderCells();for(var i=0;i<_3ad.length;i++){
                this._connects.push(dojo.connect(_3ad[i],"onfocus",this,"doColHeaderFocus"));this._connects.push(dojo.connect(_3ad[i],"onblur",this,"doColHeaderBlur"));
            }
            },
        _findHeaderCells:function(){
            var _3ae=dojo.query("th",this.grid.viewsHeaderNode);var _3af=[];for(var i=0;i<_3ae.length;i++){
                var _3b0=_3ae[i];var _3b1=dojo.hasAttr(_3b0,"tabIndex");var _3b2=dojo.attr(_3b0,"tabIndex");if(_3b1&&_3b2<0){
                    _3af.push(_3b0);
                }
                }return _3af;
        },
        _setActiveColHeader:function(_3b3,_3b4,_3b5){
            dojo.attr(this.grid.domNode,"aria-activedescendant",_3b3.id);if(_3b5!=null&&_3b5>=0&&_3b5!=_3b4){
                dojo.toggleClass(this._findHeaderCells()[_3b5],this.focusClass,false);
            }dojo.toggleClass(_3b3,this.focusClass,true);this._colHeadNode=_3b3;this._colHeadFocusIdx=_3b4;this._scrollHeader(this._colHeadFocusIdx);
        },
        scrollIntoView:function(){
            var info=(this.cell?this._scrollInfo(this.cell):null);if(!info||!info.s){
                return null;
            }var rt=this.grid.scroller.findScrollTop(this.rowIndex);if(info.n&&info.sr){
                if(info.n.offsetLeft+info.n.offsetWidth>info.sr.l+info.sr.w){
                    info.s.scrollLeft=info.n.offsetLeft+info.n.offsetWidth-info.sr.w;
                }else{
                    if(info.n.offsetLeft<info.sr.l){
                        info.s.scrollLeft=info.n.offsetLeft;
                    }
                    }
                }if(info.r&&info.sr){
                if(rt+info.r.offsetHeight>info.sr.t+info.sr.h){
                    this.grid.setScrollTop(rt+info.r.offsetHeight-info.sr.h);
                }else{
                    if(rt<info.sr.t){
                        this.grid.setScrollTop(rt);
                    }
                    }
                }return info.s.scrollLeft;
        },
        _scrollInfo:function(cell,_3b6){
            if(cell){
                var cl=cell,sbn=cl.view.scrollboxNode,sbnr={
                    w:sbn.clientWidth,
                    l:sbn.scrollLeft,
                    t:sbn.scrollTop,
                    h:sbn.clientHeight
                    },rn=cl.view.getRowNode(this.rowIndex);return {
                    c:cl,
                    s:sbn,
                    sr:sbnr,
                    n:(_3b6?_3b6:cell.getNode(this.rowIndex)),
                    r:rn
                };
            }return null;
        },
        _scrollHeader:function(_3b7){
            var info=null;if(this._colHeadNode){
                var cell=this.grid.getCell(_3b7);info=this._scrollInfo(cell,cell.getNode(0));
            }if(info&&info.s&&info.sr&&info.n){
                var _3b8=info.sr.l+info.sr.w;if(info.n.offsetLeft+info.n.offsetWidth>_3b8){
                    info.s.scrollLeft=info.n.offsetLeft+info.n.offsetWidth-info.sr.w;
                }else{
                    if(info.n.offsetLeft<info.sr.l){
                        info.s.scrollLeft=info.n.offsetLeft;
                    }else{
                        if(dojo.isIE<=7&&cell&&cell.view.headerNode){
                            cell.view.headerNode.scrollLeft=info.s.scrollLeft;
                        }
                        }
                    }
                }
            },
        _isHeaderHidden:function(){
            var _3b9=this.focusView;if(!_3b9){
                for(var i=0,_3ba;(_3ba=this.grid.views.views[i]);i++){
                    if(_3ba.headerNode){
                        _3b9=_3ba;break;
                    }
                    }
                }return (_3b9&&dojo.getComputedStyle(_3b9.headerNode).display=="none");
        },
        colSizeAdjust:function(e,_3bb,_3bc){
            var _3bd=this._findHeaderCells();var view=this.focusView;if(!view){
                for(var i=0,_3be;(_3be=this.grid.views.views[i]);i++){
                    if(_3be.header.tableMap.map){
                        view=_3be;break;
                    }
                    }
                }var _3bf=_3bd[_3bb];if(!view||(_3bb==_3bd.length-1&&_3bb===0)){
                return;
            }view.content.baseDecorateEvent(e);e.cellNode=_3bf;e.cellIndex=view.content.getCellNodeIndex(e.cellNode);e.cell=(e.cellIndex>=0?this.grid.getCell(e.cellIndex):null);if(view.header.canResize(e)){
                var _3c0={
                    l:_3bc
                };var drag=view.header.colResizeSetup(e,false);view.header.doResizeColumn(drag,null,_3c0);view.update();
            }
            },
        styleRow:function(_3c1){
            return;
        },
        setFocusIndex:function(_3c2,_3c3){
            this.setFocusCell(this.grid.getCell(_3c3),_3c2);
        },
        setFocusCell:function(_3c4,_3c5){
            if(_3c4&&!this.isFocusCell(_3c4,_3c5)){
                this.tabbingOut=false;if(this._colHeadNode){
                    dojo.toggleClass(this._colHeadNode,this.focusClass,false);dojo.removeAttr(this.grid.domNode,"aria-activedescendant");
                }this._colHeadNode=this._colHeadFocusIdx=null;this.focusGridView();this._focusifyCellNode(false);this.cell=_3c4;this.rowIndex=_3c5;this._focusifyCellNode(true);
            }if(dojo.isOpera){
                setTimeout(dojo.hitch(this.grid,"onCellFocus",this.cell,this.rowIndex),1);
            }else{
                this.grid.onCellFocus(this.cell,this.rowIndex);
            }
            },
        next:function(){
            if(this.cell){
                var row=this.rowIndex,col=this.cell.index+1,cc=this.grid.layout.cellCount-1,rc=this.grid.rowCount-1;if(col>cc){
                    col=0;row++;
                }if(row>rc){
                    col=cc;row=rc;
                }if(this.grid.edit.isEditing()){
                    var _3c6=this.grid.getCell(col);if(!this.isLastFocusCell()&&!_3c6.editable){
                        this.cell=_3c6;this.rowIndex=row;this.next();return;
                    }
                    }this.setFocusIndex(row,col);
            }
            },
        previous:function(){
            if(this.cell){
                var row=(this.rowIndex||0),col=(this.cell.index||0)-1;if(col<0){
                    col=this.grid.layout.cellCount-1;row--;
                }if(row<0){
                    row=0;col=0;
                }if(this.grid.edit.isEditing()){
                    var _3c7=this.grid.getCell(col);if(!this.isFirstFocusCell()&&!_3c7.editable){
                        this.cell=_3c7;this.rowIndex=row;this.previous();return;
                    }
                    }this.setFocusIndex(row,col);
            }
            },
        move:function(_3c8,_3c9){
            var _3ca=_3c9<0?-1:1;if(this.isNavHeader()){
                var _3cb=this._findHeaderCells();var _3cc=currentIdx=dojo.indexOf(_3cb,this._colHeadNode);currentIdx+=_3c9;while(currentIdx>=0&&currentIdx<_3cb.length&&_3cb[currentIdx].style.display=="none"){
                    currentIdx+=_3ca;
                }if((currentIdx>=0)&&(currentIdx<_3cb.length)){
                    this._setActiveColHeader(_3cb[currentIdx],currentIdx,_3cc);
                }
                }else{
                if(this.cell){
                    var sc=this.grid.scroller,r=this.rowIndex,rc=this.grid.rowCount-1,row=Math.min(rc,Math.max(0,r+_3c8));if(_3c8){
                        if(_3c8>0){
                            if(row>sc.getLastPageRow(sc.page)){
                                this.grid.setScrollTop(this.grid.scrollTop+sc.findScrollTop(row)-sc.findScrollTop(r));
                            }
                            }else{
                            if(_3c8<0){
                                if(row<=sc.getPageRow(sc.page)){
                                    this.grid.setScrollTop(this.grid.scrollTop-sc.findScrollTop(r)-sc.findScrollTop(row));
                                }
                                }
                            }
                        }var cc=this.grid.layout.cellCount-1,i=this.cell.index,col=Math.min(cc,Math.max(0,i+_3c9));var cell=this.grid.getCell(col);while(col>=0&&col<cc&&cell&&cell.hidden===true){
                        col+=_3ca;cell=this.grid.getCell(col);
                    }if(!cell||cell.hidden===true){
                        col=i;
                    }this.setFocusIndex(row,col);if(_3c8){
                        this.grid.updateRow(r);
                    }
                    }
                }
            },
        previousKey:function(e){
            if(this.grid.edit.isEditing()){
                dojo.stopEvent(e);this.previous();
            }else{
                if(!this.isNavHeader()&&!this._isHeaderHidden()){
                    this.grid.domNode.focus();dojo.stopEvent(e);
                }else{
                    this.tabOut(this.grid.domNode);if(this._colHeadFocusIdx!=null){
                        dojo.toggleClass(this._findHeaderCells()[this._colHeadFocusIdx],this.focusClass,false);this._colHeadFocusIdx=null;
                    }this._focusifyCellNode(false);
                }
                }
            },
        nextKey:function(e){
            var _3cd=(this.grid.rowCount===0);if(e.target===this.grid.domNode&&this._colHeadFocusIdx==null){
                this.focusHeader();dojo.stopEvent(e);
            }else{
                if(this.isNavHeader()){
                    dojo.toggleClass(this._colHeadNode,this.focusClass,false);if(!this.findAndFocusGridCell()){
                        this.tabOut(this.grid.lastFocusNode);
                    }this._colHeadNode=this._colHeadFocusIdx=null;
                }else{
                    if(this.grid.edit.isEditing()){
                        dojo.stopEvent(e);this.next();
                    }else{
                        this.tabOut(this.grid.lastFocusNode);
                    }
                    }
                }
            },
        tabOut:function(_3ce){
            this.tabbingOut=true;_3ce.focus();
        },
        focusGridView:function(){
            dojox.grid.util.fire(this.focusView,"focus");
        },
        focusGrid:function(_3cf){
            this.focusGridView();this._focusifyCellNode(true);
        },
        findAndFocusGridCell:function(){
            var _3d0=true;var _3d1=(this.grid.rowCount===0);if(this.isNoFocusCell()&&!_3d1){
                var _3d2=0;var cell=this.grid.getCell(_3d2);if(cell.hidden){
                    _3d2=this.isNavHeader()?this._colHeadFocusIdx:0;
                }this.setFocusIndex(0,_3d2);
            }else{
                if(this.cell&&!_3d1){
                    if(this.focusView&&!this.focusView.rowNodes[this.rowIndex]){
                        this.grid.scrollToRow(this.rowIndex);
                    }this.focusGrid();
                }else{
                    _3d0=false;
                }
                }this._colHeadNode=this._colHeadFocusIdx=null;return _3d0;
        },
        focusHeader:function(){
            var _3d3=this._findHeaderCells();var _3d4=this._colHeadFocusIdx;if(this._isHeaderHidden()){
                this.findAndFocusGridCell();
            }else{
                if(!this._colHeadFocusIdx){
                    if(this.isNoFocusCell()){
                        this._colHeadFocusIdx=0;
                    }else{
                        this._colHeadFocusIdx=this.cell.index;
                    }
                    }
                }this._colHeadNode=_3d3[this._colHeadFocusIdx];while(this._colHeadNode&&this._colHeadFocusIdx>=0&&this._colHeadFocusIdx<_3d3.length&&this._colHeadNode.style.display=="none"){
                this._colHeadFocusIdx++;this._colHeadNode=_3d3[this._colHeadFocusIdx];
            }if(this._colHeadNode&&this._colHeadNode.style.display!="none"){
                this._setActiveColHeader(this._colHeadNode,this._colHeadFocusIdx,_3d4);this._scrollHeader(this._colHeadFocusIdx);this._focusifyCellNode(false);
            }else{
                this.findAndFocusGridCell();
            }
            },
        doFocus:function(e){
            if(e&&e.target!=e.currentTarget){
                dojo.stopEvent(e);return;
            }if(!this.tabbingOut){
                this.focusHeader();
            }this.tabbingOut=false;dojo.stopEvent(e);
        },
        doBlur:function(e){
            dojo.stopEvent(e);
        },
        doLastNodeFocus:function(e){
            if(this.tabbingOut){
                this._focusifyCellNode(false);
            }else{
                if(this.grid.rowCount>0){
                    if(this.isNoFocusCell()){
                        this.setFocusIndex(0,0);
                    }this._focusifyCellNode(true);
                }else{
                    this.focusHeader();
                }
                }this.tabbingOut=false;dojo.stopEvent(e);
        },
        doLastNodeBlur:function(e){
            dojo.stopEvent(e);
        },
        doColHeaderFocus:function(e){
            this._setActiveColHeader(e.target,dojo.attr(e.target,"idx"),this._colHeadFocusIdx);this._scrollHeader(this.getHeaderIndex());dojo.stopEvent(e);
        },
        doColHeaderBlur:function(e){
            dojo.toggleClass(e.target,this.focusClass,false);
        }
        });
}if(!dojo._hasResource["dojox.grid._EditManager"]){
    dojo._hasResource["dojox.grid._EditManager"]=true;dojo.provide("dojox.grid._EditManager");dojo.declare("dojox.grid._EditManager",null,{
        constructor:function(_3d5){
            this.grid=_3d5;this.connections=[];if(dojo.isIE){
                this.connections.push(dojo.connect(document.body,"onfocus",dojo.hitch(this,"_boomerangFocus")));
            }
            },
        info:{},
        destroy:function(){
            dojo.forEach(this.connections,dojo.disconnect);
        },
        cellFocus:function(_3d6,_3d7){
            if(this.grid.singleClickEdit||this.isEditRow(_3d7)){
                this.setEditCell(_3d6,_3d7);
            }else{
                this.apply();
            }if(this.isEditing()||(_3d6&&_3d6.editable&&_3d6.alwaysEditing)){
                this._focusEditor(_3d6,_3d7);
            }
            },
        rowClick:function(e){
            if(this.isEditing()&&!this.isEditRow(e.rowIndex)){
                this.apply();
            }
            },
        styleRow:function(_3d8){
            if(_3d8.index==this.info.rowIndex){
                _3d8.customClasses+=" dojoxGridRowEditing";
            }
            },
        dispatchEvent:function(e){
            var c=e.cell,ed=(c&&c["editable"])?c:0;return ed&&ed.dispatchEvent(e.dispatch,e);
        },
        isEditing:function(){
            return this.info.rowIndex!==undefined;
        },
        isEditCell:function(_3d9,_3da){
            return (this.info.rowIndex===_3d9)&&(this.info.cell.index==_3da);
        },
        isEditRow:function(_3db){
            return this.info.rowIndex===_3db;
        },
        setEditCell:function(_3dc,_3dd){
            if(!this.isEditCell(_3dd,_3dc.index)&&this.grid.canEdit&&this.grid.canEdit(_3dc,_3dd)){
                this.start(_3dc,_3dd,this.isEditRow(_3dd)||_3dc.editable);
            }
            },
        _focusEditor:function(_3de,_3df){
            dojox.grid.util.fire(_3de,"focus",[_3df]);
        },
        focusEditor:function(){
            if(this.isEditing()){
                this._focusEditor(this.info.cell,this.info.rowIndex);
            }
            },
        _boomerangWindow:500,
        _shouldCatchBoomerang:function(){
            return this._catchBoomerang>new Date().getTime();
        },
        _boomerangFocus:function(){
            if(this._shouldCatchBoomerang()){
                this.grid.focus.focusGrid();this.focusEditor();this._catchBoomerang=0;
            }
            },
        _doCatchBoomerang:function(){
            if(dojo.isIE){
                this._catchBoomerang=new Date().getTime()+this._boomerangWindow;
            }
            },
        start:function(_3e0,_3e1,_3e2){
            this.grid.beginUpdate();this.editorApply();if(this.isEditing()&&!this.isEditRow(_3e1)){
                this.applyRowEdit();this.grid.updateRow(_3e1);
            }if(_3e2){
                this.info={
                    cell:_3e0,
                    rowIndex:_3e1
                };this.grid.doStartEdit(_3e0,_3e1);this.grid.updateRow(_3e1);
            }else{
                this.info={};
            }this.grid.endUpdate();this.grid.focus.focusGrid();this._focusEditor(_3e0,_3e1);this._doCatchBoomerang();
        },
        _editorDo:function(_3e3){
            var c=this.info.cell;if(c&&c.editable){
                c[_3e3](this.info.rowIndex);
            }
            },
        editorApply:function(){
            this._editorDo("apply");
        },
        editorCancel:function(){
            this._editorDo("cancel");
        },
        applyCellEdit:function(_3e4,_3e5,_3e6){
            if(this.grid.canEdit(_3e5,_3e6)){
                this.grid.doApplyCellEdit(_3e4,_3e6,_3e5.field);
            }
            },
        applyRowEdit:function(){
            this.grid.doApplyEdit(this.info.rowIndex,this.info.cell.field);
        },
        apply:function(){
            if(this.isEditing()){
                this.grid.beginUpdate();this.editorApply();this.applyRowEdit();this.info={};this.grid.endUpdate();this.grid.focus.focusGrid();this._doCatchBoomerang();
            }
            },
        cancel:function(){
            if(this.isEditing()){
                this.grid.beginUpdate();this.editorCancel();this.info={};this.grid.endUpdate();this.grid.focus.focusGrid();this._doCatchBoomerang();
            }
            },
        save:function(_3e7,_3e8){
            var c=this.info.cell;if(this.isEditRow(_3e7)&&(!_3e8||c.view==_3e8)&&c.editable){
                c.save(c,this.info.rowIndex);
            }
            },
        restore:function(_3e9,_3ea){
            var c=this.info.cell;if(this.isEditRow(_3ea)&&c.view==_3e9&&c.editable){
                c.restore(c,this.info.rowIndex);
            }
            }
        });
}if(!dojo._hasResource["dojox.grid.Selection"]){
    dojo._hasResource["dojox.grid.Selection"]=true;dojo.provide("dojox.grid.Selection");dojo.declare("dojox.grid.Selection",null,{
        constructor:function(_3eb){
            this.grid=_3eb;this.selected=[];this.setMode(_3eb.selectionMode);
        },
        mode:"extended",
        selected:null,
        updating:0,
        selectedIndex:-1,
        setMode:function(mode){
            if(this.selected.length){
                this.deselectAll();
            }if(mode!="extended"&&mode!="multiple"&&mode!="single"&&mode!="none"){
                this.mode="extended";
            }else{
                this.mode=mode;
            }
            },
        onCanSelect:function(_3ec){
            return this.grid.onCanSelect(_3ec);
        },
        onCanDeselect:function(_3ed){
            return this.grid.onCanDeselect(_3ed);
        },
        onSelected:function(_3ee){},
        onDeselected:function(_3ef){},
        onChanging:function(){},
        onChanged:function(){},
        isSelected:function(_3f0){
            if(this.mode=="none"){
                return false;
            }return this.selected[_3f0];
        },
        getFirstSelected:function(){
            if(!this.selected.length||this.mode=="none"){
                return -1;
            }for(var i=0,l=this.selected.length;i<l;i++){
                if(this.selected[i]){
                    return i;
                }
                }return -1;
        },
        getNextSelected:function(_3f1){
            if(this.mode=="none"){
                return -1;
            }for(var i=_3f1+1,l=this.selected.length;i<l;i++){
                if(this.selected[i]){
                    return i;
                }
                }return -1;
        },
        getSelected:function(){
            var _3f2=[];for(var i=0,l=this.selected.length;i<l;i++){
                if(this.selected[i]){
                    _3f2.push(i);
                }
                }return _3f2;
        },
        getSelectedCount:function(){
            var c=0;for(var i=0;i<this.selected.length;i++){
                if(this.selected[i]){
                    c++;
                }
                }return c;
        },
        _beginUpdate:function(){
            if(this.updating===0){
                this.onChanging();
            }this.updating++;
        },
        _endUpdate:function(){
            this.updating--;if(this.updating===0){
                this.onChanged();
            }
            },
        select:function(_3f3){
            if(this.mode=="none"){
                return;
            }if(this.mode!="multiple"){
                this.deselectAll(_3f3);this.addToSelection(_3f3);
            }else{
                this.toggleSelect(_3f3);
            }
            },
        addToSelection:function(_3f4){
            if(this.mode=="none"){
                return;
            }if(dojo.isArray(_3f4)){
                dojo.forEach(_3f4,this.addToSelection,this);return;
            }_3f4=Number(_3f4);if(this.selected[_3f4]){
                this.selectedIndex=_3f4;
            }else{
                if(this.onCanSelect(_3f4)!==false){
                    this.selectedIndex=_3f4;var _3f5=this.grid.getRowNode(_3f4);if(_3f5){
                        dojo.attr(_3f5,"aria-selected","true");
                    }this._beginUpdate();this.selected[_3f4]=true;this.onSelected(_3f4);this._endUpdate();
                }
                }
            },
        deselect:function(_3f6){
            if(this.mode=="none"){
                return;
            }if(dojo.isArray(_3f6)){
                dojo.forEach(_3f6,this.deselect,this);return;
            }_3f6=Number(_3f6);if(this.selectedIndex==_3f6){
                this.selectedIndex=-1;
            }if(this.selected[_3f6]){
                if(this.onCanDeselect(_3f6)===false){
                    return;
                }var _3f7=this.grid.getRowNode(_3f6);if(_3f7){
                    dojo.attr(_3f7,"aria-selected","false");
                }this._beginUpdate();delete this.selected[_3f6];this.onDeselected(_3f6);this._endUpdate();
            }
            },
        setSelected:function(_3f8,_3f9){
            this[(_3f9?"addToSelection":"deselect")](_3f8);
        },
        toggleSelect:function(_3fa){
            if(dojo.isArray(_3fa)){
                dojo.forEach(_3fa,this.toggleSelect,this);return;
            }this.setSelected(_3fa,!this.selected[_3fa]);
        },
        _range:function(_3fb,inTo,func){
            var s=(_3fb>=0?_3fb:inTo),e=inTo;if(s>e){
                e=s;s=inTo;
            }for(var i=s;i<=e;i++){
                func(i);
            }
            },
        selectRange:function(_3fc,inTo){
            this._range(_3fc,inTo,dojo.hitch(this,"addToSelection"));
        },
        deselectRange:function(_3fd,inTo){
            this._range(_3fd,inTo,dojo.hitch(this,"deselect"));
        },
        insert:function(_3fe){
            this.selected.splice(_3fe,0,false);if(this.selectedIndex>=_3fe){
                this.selectedIndex++;
            }
            },
        remove:function(_3ff){
            this.selected.splice(_3ff,1);if(this.selectedIndex>=_3ff){
                this.selectedIndex--;
            }
            },
        deselectAll:function(_400){
            for(var i in this.selected){
                if((i!=_400)&&(this.selected[i]===true)){
                    this.deselect(i);
                }
                }
            },
        clickSelect:function(_401,_402,_403){
            if(this.mode=="none"){
                return;
            }this._beginUpdate();if(this.mode!="extended"){
                this.select(_401);
            }else{
                var _404=this.selectedIndex;if(!_402){
                    this.deselectAll(_401);
                }if(_403){
                    this.selectRange(_404,_401);
                }else{
                    if(_402){
                        this.toggleSelect(_401);
                    }else{
                        this.addToSelection(_401);
                    }
                    }
                }this._endUpdate();
        },
        clickSelectEvent:function(e){
            this.clickSelect(e.rowIndex,dojo.isCopyKey(e),e.shiftKey);
        },
        clear:function(){
            this._beginUpdate();this.deselectAll();this._endUpdate();
        }
        });
}if(!dojo._hasResource["dojox.grid._Events"]){
    dojo._hasResource["dojox.grid._Events"]=true;dojo.provide("dojox.grid._Events");dojo.declare("dojox.grid._Events",null,{
        cellOverClass:"dojoxGridCellOver",
        onKeyEvent:function(e){
            this.dispatchKeyEvent(e);
        },
        onContentEvent:function(e){
            this.dispatchContentEvent(e);
        },
        onHeaderEvent:function(e){
            this.dispatchHeaderEvent(e);
        },
        onStyleRow:function(_405){
            var i=_405;i.customClasses+=(i.odd?" dojoxGridRowOdd":"")+(i.selected?" dojoxGridRowSelected":"")+(i.over?" dojoxGridRowOver":"");this.focus.styleRow(_405);this.edit.styleRow(_405);
        },
        onKeyDown:function(e){
            if(e.altKey||e.metaKey){
                return;
            }var dk=dojo.keys;var _406;switch(e.keyCode){
                case dk.ESCAPE:this.edit.cancel();break;case dk.ENTER:if(!this.edit.isEditing()){
                    _406=this.focus.getHeaderIndex();if(_406>=0){
                        this.setSortIndex(_406);break;
                    }else{
                        this.selection.clickSelect(this.focus.rowIndex,dojo.isCopyKey(e),e.shiftKey);
                    }dojo.stopEvent(e);
                }if(!e.shiftKey){
                    var _407=this.edit.isEditing();this.edit.apply();if(!_407){
                        this.edit.setEditCell(this.focus.cell,this.focus.rowIndex);
                    }
                    }if(!this.edit.isEditing()){
                    var _408=this.focus.focusView||this.views.views[0];_408.content.decorateEvent(e);this.onRowClick(e);
                }break;case dk.SPACE:if(!this.edit.isEditing()){
                    _406=this.focus.getHeaderIndex();if(_406>=0){
                        this.setSortIndex(_406);break;
                    }else{
                        this.selection.clickSelect(this.focus.rowIndex,dojo.isCopyKey(e),e.shiftKey);
                    }dojo.stopEvent(e);
                }break;case dk.TAB:this.focus[e.shiftKey?"previousKey":"nextKey"](e);break;case dk.LEFT_ARROW:case dk.RIGHT_ARROW:if(!this.edit.isEditing()){
                    var _409=e.keyCode;dojo.stopEvent(e);_406=this.focus.getHeaderIndex();if(_406>=0&&(e.shiftKey&&e.ctrlKey)){
                        this.focus.colSizeAdjust(e,_406,(_409==dk.LEFT_ARROW?-1:1)*5);
                    }else{
                        var _40a=(_409==dk.LEFT_ARROW)?1:-1;if(dojo._isBodyLtr()){
                            _40a*=-1;
                        }this.focus.move(0,_40a);
                    }
                    }break;case dk.UP_ARROW:if(!this.edit.isEditing()&&this.focus.rowIndex!==0){
                    dojo.stopEvent(e);this.focus.move(-1,0);
                }break;case dk.DOWN_ARROW:if(!this.edit.isEditing()&&this.focus.rowIndex+1!=this.rowCount){
                    dojo.stopEvent(e);this.focus.move(1,0);
                }break;case dk.PAGE_UP:if(!this.edit.isEditing()&&this.focus.rowIndex!==0){
                    dojo.stopEvent(e);if(this.focus.rowIndex!=this.scroller.firstVisibleRow+1){
                        this.focus.move(this.scroller.firstVisibleRow-this.focus.rowIndex,0);
                    }else{
                        this.setScrollTop(this.scroller.findScrollTop(this.focus.rowIndex-1));this.focus.move(this.scroller.firstVisibleRow-this.scroller.lastVisibleRow+1,0);
                    }
                    }break;case dk.PAGE_DOWN:if(!this.edit.isEditing()&&this.focus.rowIndex+1!=this.rowCount){
                    dojo.stopEvent(e);if(this.focus.rowIndex!=this.scroller.lastVisibleRow-1){
                        this.focus.move(this.scroller.lastVisibleRow-this.focus.rowIndex-1,0);
                    }else{
                        this.setScrollTop(this.scroller.findScrollTop(this.focus.rowIndex+1));this.focus.move(this.scroller.lastVisibleRow-this.scroller.firstVisibleRow-1,0);
                    }
                    }break;default:break;
            }
            },
        onMouseOver:function(e){
            e.rowIndex==-1?this.onHeaderCellMouseOver(e):this.onCellMouseOver(e);
        },
        onMouseOut:function(e){
            e.rowIndex==-1?this.onHeaderCellMouseOut(e):this.onCellMouseOut(e);
        },
        onMouseDown:function(e){
            e.rowIndex==-1?this.onHeaderCellMouseDown(e):this.onCellMouseDown(e);
        },
        onMouseOverRow:function(e){
            if(!this.rows.isOver(e.rowIndex)){
                this.rows.setOverRow(e.rowIndex);e.rowIndex==-1?this.onHeaderMouseOver(e):this.onRowMouseOver(e);
            }
            },
        onMouseOutRow:function(e){
            if(this.rows.isOver(-1)){
                this.onHeaderMouseOut(e);
            }else{
                if(!this.rows.isOver(-2)){
                    this.rows.setOverRow(-2);this.onRowMouseOut(e);
                }
                }
            },
        onMouseDownRow:function(e){
            if(e.rowIndex!=-1){
                this.onRowMouseDown(e);
            }
            },
        onCellMouseOver:function(e){
            if(e.cellNode){
                dojo.addClass(e.cellNode,this.cellOverClass);
            }
            },
        onCellMouseOut:function(e){
            if(e.cellNode){
                dojo.removeClass(e.cellNode,this.cellOverClass);
            }
            },
        onCellMouseDown:function(e){},
        onCellClick:function(e){
            this._click[0]=this._click[1];this._click[1]=e;if(!this.edit.isEditCell(e.rowIndex,e.cellIndex)){
                this.focus.setFocusCell(e.cell,e.rowIndex);
            }this.onRowClick(e);
        },
        onCellDblClick:function(e){
            if(this._click.length>1&&dojo.isIE){
                this.edit.setEditCell(this._click[1].cell,this._click[1].rowIndex);
            }else{
                if(this._click.length>1&&this._click[0].rowIndex!=this._click[1].rowIndex){
                    this.edit.setEditCell(this._click[0].cell,this._click[0].rowIndex);
                }else{
                    this.edit.setEditCell(e.cell,e.rowIndex);
                }
                }this.onRowDblClick(e);
        },
        onCellContextMenu:function(e){
            this.onRowContextMenu(e);
        },
        onCellFocus:function(_40b,_40c){
            this.edit.cellFocus(_40b,_40c);
        },
        onRowClick:function(e){
            this.edit.rowClick(e);this.selection.clickSelectEvent(e);
        },
        onRowDblClick:function(e){},
        onRowMouseOver:function(e){},
        onRowMouseOut:function(e){},
        onRowMouseDown:function(e){},
        onRowContextMenu:function(e){
            dojo.stopEvent(e);
        },
        onHeaderMouseOver:function(e){},
        onHeaderMouseOut:function(e){},
        onHeaderCellMouseOver:function(e){
            if(e.cellNode){
                dojo.addClass(e.cellNode,this.cellOverClass);
            }
            },
        onHeaderCellMouseOut:function(e){
            if(e.cellNode){
                dojo.removeClass(e.cellNode,this.cellOverClass);
            }
            },
        onHeaderCellMouseDown:function(e){},
        onHeaderClick:function(e){},
        onHeaderCellClick:function(e){
            this.setSortIndex(e.cell.index);this.onHeaderClick(e);
        },
        onHeaderDblClick:function(e){},
        onHeaderCellDblClick:function(e){
            this.onHeaderDblClick(e);
        },
        onHeaderCellContextMenu:function(e){
            this.onHeaderContextMenu(e);
        },
        onHeaderContextMenu:function(e){
            if(!this.headerMenu){
                dojo.stopEvent(e);
            }
            },
        onStartEdit:function(_40d,_40e){},
        onApplyCellEdit:function(_40f,_410,_411){},
        onCancelEdit:function(_412){},
        onApplyEdit:function(_413){},
        onCanSelect:function(_414){
            return true;
        },
        onCanDeselect:function(_415){
            return true;
        },
        onSelected:function(_416){
            this.updateRowStyles(_416);
        },
        onDeselected:function(_417){
            this.updateRowStyles(_417);
        },
        onSelectionChanged:function(){}
        });
}if(!dojo._hasResource["dojo.i18n"]){
    dojo._hasResource["dojo.i18n"]=true;dojo.provide("dojo.i18n");dojo.i18n.getLocalization=function(_418,_419,_41a){
        _41a=dojo.i18n.normalizeLocale(_41a);var _41b=_41a.split("-");var _41c=[_418,"nls",_419].join(".");var _41d=dojo._loadedModules[_41c];if(_41d){
            var _41e;for(var i=_41b.length;i>0;i--){
                var loc=_41b.slice(0,i).join("_");if(_41d[loc]){
                    _41e=_41d[loc];break;
                }
                }if(!_41e){
                _41e=_41d.ROOT;
            }if(_41e){
                var _41f=function(){};_41f.prototype=_41e;return new _41f();
            }
            }throw new Error("Bundle not found: "+_419+" in "+_418+" , locale="+_41a);
    };dojo.i18n.normalizeLocale=function(_420){
        var _421=_420?_420.toLowerCase():dojo.locale;if(_421=="root"){
            _421="ROOT";
        }return _421;
    };dojo.i18n._requireLocalization=function(_422,_423,_424,_425){
        var _426=dojo.i18n.normalizeLocale(_424);var _427=[_422,"nls",_423].join(".");var _428="";if(_425){
            var _429=_425.split(",");for(var i=0;i<_429.length;i++){
                if(_426["indexOf"](_429[i])==0){
                    if(_429[i].length>_428.length){
                        _428=_429[i];
                    }
                    }
                }if(!_428){
                _428="ROOT";
            }
            }var _42a=_425?_428:_426;var _42b=dojo._loadedModules[_427];var _42c=null;if(_42b){
            if(dojo.config.localizationComplete&&_42b._built){
                return;
            }var _42d=_42a.replace(/-/g,"_");var _42e=_427+"."+_42d;_42c=dojo._loadedModules[_42e];
        }if(!_42c){
            _42b=dojo["provide"](_427);var syms=dojo._getModuleSymbols(_422);var _42f=syms.concat("nls").join("/");var _430;dojo.i18n._searchLocalePath(_42a,_425,function(loc){
                var _431=loc.replace(/-/g,"_");var _432=_427+"."+_431;var _433=false;if(!dojo._loadedModules[_432]){
                    dojo["provide"](_432);var _434=[_42f];if(loc!="ROOT"){
                        _434.push(loc);
                    }_434.push(_423);var _435=_434.join("/")+".js";_433=dojo._loadPath(_435,null,function(hash){
                        var _436=function(){};_436.prototype=_430;_42b[_431]=new _436();for(var j in hash){
                            _42b[_431][j]=hash[j];
                        }
                        });
                }else{
                    _433=true;
                }if(_433&&_42b[_431]){
                    _430=_42b[_431];
                }else{
                    _42b[_431]=_430;
                }if(_425){
                    return true;
                }
                });
        }if(_425&&_426!=_428){
            _42b[_426.replace(/-/g,"_")]=_42b[_428.replace(/-/g,"_")];
        }
        };(function(){
        var _437=dojo.config.extraLocale;if(_437){
            if(!_437 instanceof Array){
                _437=[_437];
            }var req=dojo.i18n._requireLocalization;dojo.i18n._requireLocalization=function(m,b,_438,_439){
                req(m,b,_438,_439);if(_438){
                    return;
                }for(var i=0;i<_437.length;i++){
                    req(m,b,_437[i],_439);
                }
                };
        }
        })();dojo.i18n._searchLocalePath=function(_43a,down,_43b){
        _43a=dojo.i18n.normalizeLocale(_43a);var _43c=_43a.split("-");var _43d=[];for(var i=_43c.length;i>0;i--){
            _43d.push(_43c.slice(0,i).join("-"));
        }_43d.push(false);if(down){
            _43d.reverse();
        }for(var j=_43d.length-1;j>=0;j--){
            var loc=_43d[j]||"ROOT";var stop=_43b(loc);if(stop){
                break;
            }
            }
        };dojo.i18n._preloadLocalizations=function(_43e,_43f){
        function _440(_441){
            _441=dojo.i18n.normalizeLocale(_441);dojo.i18n._searchLocalePath(_441,true,function(loc){
                for(var i=0;i<_43f.length;i++){
                    if(_43f[i]==loc){
                        dojo["require"](_43e+"_"+loc);return true;
                    }
                    }return false;
            });
        };_440();var _442=dojo.config.extraLocale||[];for(var i=0;i<_442.length;i++){
            _440(_442[i]);
        }
        };
}if(!dojo._hasResource["dojox.grid._Grid"]){
    dojo._hasResource["dojox.grid._Grid"]=true;dojo.provide("dojox.grid._Grid");(function(){
        if(!dojo.isCopyKey){
            dojo.isCopyKey=dojo.dnd.getCopyKeyState;
        }dojo.declare("dojox.grid._Grid",[dijit._Widget,dijit._Templated,dojox.grid._Events],{
            templateString:"<div class=\"dojoxGrid\" hidefocus=\"hidefocus\" wairole=\"grid\" dojoAttachEvent=\"onmouseout:_mouseOut\">\n\t<div class=\"dojoxGridMasterHeader\" dojoAttachPoint=\"viewsHeaderNode\" wairole=\"presentation\"></div>\n\t<div class=\"dojoxGridMasterView\" dojoAttachPoint=\"viewsNode\" wairole=\"presentation\"></div>\n\t<div class=\"dojoxGridMasterMessages\" style=\"display: none;\" dojoAttachPoint=\"messagesNode\"></div>\n\t<span dojoAttachPoint=\"lastFocusNode\" tabindex=\"0\"></span>\n</div>\n",
            classTag:"dojoxGrid",
            get:function(_443){},
            rowCount:5,
            keepRows:75,
            rowsPerPage:25,
            autoWidth:false,
            initialWidth:"",
            autoHeight:"",
            rowHeight:0,
            autoRender:true,
            defaultHeight:"15em",
            height:"",
            structure:null,
            elasticView:-1,
            singleClickEdit:false,
            selectionMode:"extended",
            rowSelector:"",
            columnReordering:false,
            headerMenu:null,
            placeholderLabel:"GridColumns",
            selectable:false,
            _click:null,
            loadingMessage:"<span class='dojoxGridLoading'>${loadingState}</span>",
            errorMessage:"<span class='dojoxGridError'>${errorState}</span>",
            noDataMessage:"",
            escapeHTMLInData:true,
            formatterScope:null,
            editable:false,
            sortInfo:0,
            themeable:true,
            _placeholders:null,
            _layoutClass:dojox.grid._Layout,
            buildRendering:function(){
                this.inherited(arguments);if(this.get==dojox.grid._Grid.prototype.get){
                    this.get=null;
                }if(!this.domNode.getAttribute("tabIndex")){
                    this.domNode.tabIndex="0";
                }this.createScroller();this.createLayout();this.createViews();this.createManagers();this.createSelection();this.connect(this.selection,"onSelected","onSelected");this.connect(this.selection,"onDeselected","onDeselected");this.connect(this.selection,"onChanged","onSelectionChanged");dojox.html.metrics.initOnFontResize();this.connect(dojox.html.metrics,"onFontResize","textSizeChanged");dojox.grid.util.funnelEvents(this.domNode,this,"doKeyEvent",dojox.grid.util.keyEvents);if(this.selectionMode!="none"){
                    dojo.attr(this.domNode,"aria-multiselectable",this.selectionMode=="single"?"false":"true");
                }
                },
            postMixInProperties:function(){
                this.inherited(arguments);var _444=dojo.i18n.getLocalization("dijit","loading",this.lang);this.loadingMessage=dojo.string.substitute(this.loadingMessage,_444);this.errorMessage=dojo.string.substitute(this.errorMessage,_444);if(this.srcNodeRef&&this.srcNodeRef.style.height){
                    this.height=this.srcNodeRef.style.height;
                }this._setAutoHeightAttr(this.autoHeight,true);this.lastScrollTop=this.scrollTop=0;
            },
            postCreate:function(){
                this._placeholders=[];this._setHeaderMenuAttr(this.headerMenu);this._setStructureAttr(this.structure);this._click=[];this.inherited(arguments);if(this.domNode&&this.autoWidth&&this.initialWidth){
                    this.domNode.style.width=this.initialWidth;
                }if(this.domNode&&!this.editable){
                    dojo.attr(this.domNode,"aria-readonly","true");
                }
                },
            destroy:function(){
                this.domNode.onReveal=null;this.domNode.onSizeChange=null;delete this._click;this.edit.destroy();delete this.edit;this.views.destroyViews();if(this.scroller){
                    this.scroller.destroy();delete this.scroller;
                }if(this.focus){
                    this.focus.destroy();delete this.focus;
                }if(this.headerMenu&&this._placeholders.length){
                    dojo.forEach(this._placeholders,function(p){
                        p.unReplace(true);
                    });this.headerMenu.unBindDomNode(this.viewsHeaderNode);
                }this.inherited(arguments);
            },
            _setAutoHeightAttr:function(ah,_445){
                if(typeof ah=="string"){
                    if(!ah||ah=="false"){
                        ah=false;
                    }else{
                        if(ah=="true"){
                            ah=true;
                        }else{
                            ah=window.parseInt(ah,10);
                        }
                        }
                    }if(typeof ah=="number"){
                    if(isNaN(ah)){
                        ah=false;
                    }if(ah<0){
                        ah=true;
                    }else{
                        if(ah===0){
                            ah=false;
                        }
                        }
                    }this.autoHeight=ah;if(typeof ah=="boolean"){
                    this._autoHeight=ah;
                }else{
                    if(typeof ah=="number"){
                        this._autoHeight=(ah>=this.attr("rowCount"));
                    }else{
                        this._autoHeight=false;
                    }
                    }if(this._started&&!_445){
                    this.render();
                }
                },
            _getRowCountAttr:function(){
                return this.updating&&this.invalidated&&this.invalidated.rowCount!=undefined?this.invalidated.rowCount:this.rowCount;
            },
            textSizeChanged:function(){
                this.render();
            },
            sizeChange:function(){
                this.update();
            },
            createManagers:function(){
                this.rows=new dojox.grid._RowManager(this);this.focus=new dojox.grid._FocusManager(this);this.edit=new dojox.grid._EditManager(this);
            },
            createSelection:function(){
                this.selection=new dojox.grid.Selection(this);
            },
            createScroller:function(){
                this.scroller=new dojox.grid._Scroller();this.scroller.grid=this;this.scroller.renderRow=dojo.hitch(this,"renderRow");this.scroller.removeRow=dojo.hitch(this,"rowRemoved");
            },
            createLayout:function(){
                this.layout=new this._layoutClass(this);this.connect(this.layout,"moveColumn","onMoveColumn");
            },
            onMoveColumn:function(){
                this.render();
            },
            onResizeColumn:function(_446){},
            createViews:function(){
                this.views=new dojox.grid._ViewManager(this);this.views.createView=dojo.hitch(this,"createView");
            },
            createView:function(_447,idx){
                var c=dojo.getObject(_447);var view=new c({
                    grid:this,
                    index:idx
                });this.viewsNode.appendChild(view.domNode);this.viewsHeaderNode.appendChild(view.headerNode);this.views.addView(view);return view;
            },
            buildViews:function(){
                for(var i=0,vs;(vs=this.layout.structure[i]);i++){
                    this.createView(vs.type||dojox._scopeName+".grid._View",i).setStructure(vs);
                }this.scroller.setContentNodes(this.views.getContentNodes());
            },
            _setStructureAttr:function(_448){
                var s=_448;if(s&&dojo.isString(s)){
                    dojo.deprecated("dojox.grid._Grid.attr('structure', 'objVar')","use dojox.grid._Grid.attr('structure', objVar) instead","2.0");s=dojo.getObject(s);
                }this.structure=s;if(!s){
                    if(this.layout.structure){
                        s=this.layout.structure;
                    }else{
                        return;
                    }
                    }this.views.destroyViews();if(s!==this.layout.structure){
                    this.layout.setStructure(s);
                }this._structureChanged();
            },
            setStructure:function(_449){
                dojo.deprecated("dojox.grid._Grid.setStructure(obj)","use dojox.grid._Grid.attr('structure', obj) instead.","2.0");this._setStructureAttr(_449);
            },
            getColumnTogglingItems:function(){
                return dojo.map(this.layout.cells,function(cell){
                    if(!cell.menuItems){
                        cell.menuItems=[];
                    }var self=this;var item=new dijit.CheckedMenuItem({
                        label:cell.name,
                        checked:!cell.hidden,
                        _gridCell:cell,
                        onChange:function(_44a){
                            if(self.layout.setColumnVisibility(this._gridCell.index,_44a)){
                                var _44b=this._gridCell.menuItems;if(_44b.length>1){
                                    dojo.forEach(_44b,function(item){
                                        if(item!==this){
                                            item.setAttribute("checked",_44a);
                                        }
                                        },this);
                                }_44a=dojo.filter(self.layout.cells,function(c){
                                    if(c.menuItems.length>1){
                                        dojo.forEach(c.menuItems,"item.attr('disabled', false);");
                                    }else{
                                        c.menuItems[0].attr("disabled",false);
                                    }return !c.hidden;
                                });if(_44a.length==1){
                                    dojo.forEach(_44a[0].menuItems,"item.attr('disabled', true);");
                                }
                                }
                            },
                        destroy:function(){
                            var _44c=dojo.indexOf(this._gridCell.menuItems,this);this._gridCell.menuItems.splice(_44c,1);delete this._gridCell;dijit.CheckedMenuItem.prototype.destroy.apply(this,arguments);
                        }
                        });cell.menuItems.push(item);return item;
                },this);
            },
            _setHeaderMenuAttr:function(menu){
                if(this._placeholders&&this._placeholders.length){
                    dojo.forEach(this._placeholders,function(p){
                        p.unReplace(true);
                    });this._placeholders=[];
                }if(this.headerMenu){
                    this.headerMenu.unBindDomNode(this.viewsHeaderNode);
                }this.headerMenu=menu;if(!menu){
                    return;
                }this.headerMenu.bindDomNode(this.viewsHeaderNode);if(this.headerMenu.getPlaceholders){
                    this._placeholders=this.headerMenu.getPlaceholders(this.placeholderLabel);
                }
                },
            setHeaderMenu:function(menu){
                dojo.deprecated("dojox.grid._Grid.setHeaderMenu(obj)","use dojox.grid._Grid.attr('headerMenu', obj) instead.","2.0");this._setHeaderMenuAttr(menu);
            },
            setupHeaderMenu:function(){
                if(this._placeholders&&this._placeholders.length){
                    dojo.forEach(this._placeholders,function(p){
                        if(p._replaced){
                            p.unReplace(true);
                        }p.replace(this.getColumnTogglingItems());
                    },this);
                }
                },
            _fetch:function(_44d){
                this.setScrollTop(0);
            },
            getItem:function(_44e){
                return null;
            },
            showMessage:function(_44f){
                if(_44f){
                    this.messagesNode.innerHTML=_44f;this.messagesNode.style.display="";
                }else{
                    this.messagesNode.innerHTML="";this.messagesNode.style.display="none";
                }
                },
            _structureChanged:function(){
                this.buildViews();if(this.autoRender&&this._started){
                    this.render();
                }
                },
            hasLayout:function(){
                return this.layout.cells.length;
            },
            resize:function(_450,_451){
                this._pendingChangeSize=_450;this._pendingResultSize=_451;this.sizeChange();
            },
            _getPadBorder:function(){
                this._padBorder=this._padBorder||dojo._getPadBorderExtents(this.domNode);return this._padBorder;
            },
            _getHeaderHeight:function(){
                var vns=this.viewsHeaderNode.style,t=vns.display=="none"?0:this.views.measureHeader();vns.height=t+"px";this.views.normalizeHeaderNodeHeight();return t;
            },
            _resize:function(_452,_453){
                _452=_452||this._pendingChangeSize;_453=_453||this._pendingResultSize;delete this._pendingChangeSize;delete this._pendingResultSize;if(!this.domNode){
                    return;
                }var pn=this.domNode.parentNode;if(!pn||pn.nodeType!=1||!this.hasLayout()||pn.style.visibility=="hidden"||pn.style.display=="none"){
                    return;
                }var _454=this._getPadBorder();var hh=undefined;var h;if(this._autoHeight){
                    this.domNode.style.height="auto";this.viewsNode.style.height="";
                }else{
                    if(typeof this.autoHeight=="number"){
                        h=hh=this._getHeaderHeight();h+=(this.scroller.averageRowHeight*this.autoHeight);this.domNode.style.height=h+"px";
                    }else{
                        if(this.domNode.clientHeight<=_454.h){
                            if(pn==document.body){
                                this.domNode.style.height=this.defaultHeight;
                            }else{
                                if(this.height){
                                    this.domNode.style.height=this.height;
                                }else{
                                    this.fitTo="parent";
                                }
                                }
                            }
                        }
                    }if(_453){
                    _452=_453;
                }if(_452){
                    dojo.marginBox(this.domNode,_452);this.height=this.domNode.style.height;delete this.fitTo;
                }else{
                    if(this.fitTo=="parent"){
                        h=this._parentContentBoxHeight=this._parentContentBoxHeight||dojo._getContentBox(pn).h;this.domNode.style.height=Math.max(0,h)+"px";
                    }
                    }var _455=dojo.some(this.views.views,function(v){
                    return v.flexCells;
                });if(!this._autoHeight&&(h||dojo._getContentBox(this.domNode).h)===0){
                    this.viewsHeaderNode.style.display="none";
                }else{
                    this.viewsHeaderNode.style.display="block";if(!_455&&hh===undefined){
                        hh=this._getHeaderHeight();
                    }
                    }if(_455){
                    hh=undefined;
                }this.adaptWidth();this.adaptHeight(hh);this.postresize();
            },
            adaptWidth:function(){
                var _456=(!this.initialWidth&&this.autoWidth);var w=_456?0:this.domNode.clientWidth||(this.domNode.offsetWidth-this._getPadBorder().w),vw=this.views.arrange(1,w);this.views.onEach("adaptWidth");if(_456){
                    this.domNode.style.width=vw+"px";
                }
                },
            adaptHeight:function(_457){
                var t=_457===undefined?this._getHeaderHeight():_457;var h=(this._autoHeight?-1:Math.max(this.domNode.clientHeight-t,0)||0);this.views.onEach("setSize",[0,h]);this.views.onEach("adaptHeight");if(!this._autoHeight){
                    var _458=0,_459=0;var _45a=dojo.filter(this.views.views,function(v){
                        var has=v.hasHScrollbar();if(has){
                            _458++;
                        }else{
                            _459++;
                        }return (!has);
                    });if(_458>0&&_459>0){
                        dojo.forEach(_45a,function(v){
                            v.adaptHeight(true);
                        });
                    }
                    }if(this.autoHeight===true||h!=-1||(typeof this.autoHeight=="number"&&this.autoHeight>=this.attr("rowCount"))){
                    this.scroller.windowHeight=h;
                }else{
                    this.scroller.windowHeight=Math.max(this.domNode.clientHeight-t,0);
                }
                },
            startup:function(){
                if(this._started){
                    return;
                }this.inherited(arguments);if(this.autoRender){
                    this.render();
                }
                },
            render:function(){
                if(!this.domNode){
                    return;
                }if(!this._started){
                    return;
                }if(!this.hasLayout()){
                    this.scroller.init(0,this.keepRows,this.rowsPerPage);return;
                }this.update=this.defaultUpdate;this._render();
            },
            _render:function(){
                this.scroller.init(this.attr("rowCount"),this.keepRows,this.rowsPerPage);this.prerender();this.setScrollTop(0);this.postrender();
            },
            prerender:function(){
                this.keepRows=this._autoHeight?0:this.keepRows;this.scroller.setKeepInfo(this.keepRows);this.views.render();this._resize();
            },
            postrender:function(){
                this.postresize();this.focus.initFocusView();dojo.setSelectable(this.domNode,this.selectable);
            },
            postresize:function(){
                if(this._autoHeight){
                    var size=Math.max(this.views.measureContent())+"px";this.viewsNode.style.height=size;
                }
                },
            renderRow:function(_45b,_45c){
                this.views.renderRow(_45b,_45c,this._skipRowRenormalize);
            },
            rowRemoved:function(_45d){
                this.views.rowRemoved(_45d);
            },
            invalidated:null,
            updating:false,
            beginUpdate:function(){
                this.invalidated=[];this.updating=true;
            },
            endUpdate:function(){
                this.updating=false;var i=this.invalidated,r;if(i.all){
                    this.update();
                }else{
                    if(i.rowCount!=undefined){
                        this.updateRowCount(i.rowCount);
                    }else{
                        for(r in i){
                            this.updateRow(Number(r));
                        }
                        }
                    }this.invalidated=[];
            },
            defaultUpdate:function(){
                if(!this.domNode){
                    return;
                }if(this.updating){
                    this.invalidated.all=true;return;
                }this.lastScrollTop=this.scrollTop;this.prerender();this.scroller.invalidateNodes();this.setScrollTop(this.lastScrollTop);this.postrender();
            },
            update:function(){
                this.render();
            },
            updateRow:function(_45e){
                _45e=Number(_45e);if(this.updating){
                    this.invalidated[_45e]=true;
                }else{
                    this.views.updateRow(_45e);this.scroller.rowHeightChanged(_45e);
                }
                },
            updateRows:function(_45f,_460){
                _45f=Number(_45f);_460=Number(_460);var i;if(this.updating){
                    for(i=0;i<_460;i++){
                        this.invalidated[i+_45f]=true;
                    }
                    }else{
                    for(i=0;i<_460;i++){
                        this.views.updateRow(i+_45f,this._skipRowRenormalize);
                    }this.scroller.rowHeightChanged(_45f);
                }
                },
            updateRowCount:function(_461){
                if(this.updating){
                    this.invalidated.rowCount=_461;
                }else{
                    this.rowCount=_461;this._setAutoHeightAttr(this.autoHeight,true);if(this.layout.cells.length){
                        this.scroller.updateRowCount(_461);
                    }this._resize();if(this.layout.cells.length){
                        this.setScrollTop(this.scrollTop);
                    }
                    }
                },
            updateRowStyles:function(_462){
                this.views.updateRowStyles(_462);
            },
            getRowNode:function(_463){
                if(this.focus.focusView&&!(this.focus.focusView instanceof dojox.grid._RowSelector)){
                    return this.focus.focusView.rowNodes[_463];
                }else{
                    for(var i=0,_464;(_464=this.views.views[i]);i++){
                        if(!(_464 instanceof dojox.grid._RowSelector)){
                            return _464.rowNodes[_463];
                        }
                        }
                    }return null;
            },
            rowHeightChanged:function(_465){
                this.views.renormalizeRow(_465);this.scroller.rowHeightChanged(_465);
            },
            fastScroll:true,
            delayScroll:false,
            scrollRedrawThreshold:(dojo.isIE?100:50),
            scrollTo:function(_466){
                if(!this.fastScroll){
                    this.setScrollTop(_466);return;
                }var _467=Math.abs(this.lastScrollTop-_466);this.lastScrollTop=_466;if(_467>this.scrollRedrawThreshold||this.delayScroll){
                    this.delayScroll=true;this.scrollTop=_466;this.views.setScrollTop(_466);if(this._pendingScroll){
                        window.clearTimeout(this._pendingScroll);
                    }var _468=this;this._pendingScroll=window.setTimeout(function(){
                        delete _468._pendingScroll;_468.finishScrollJob();
                    },200);
                }else{
                    this.setScrollTop(_466);
                }
                },
            finishScrollJob:function(){
                this.delayScroll=false;this.setScrollTop(this.scrollTop);
            },
            setScrollTop:function(_469){
                this.scroller.scroll(this.views.setScrollTop(_469));
            },
            scrollToRow:function(_46a){
                this.setScrollTop(this.scroller.findScrollTop(_46a)+1);
            },
            styleRowNode:function(_46b,_46c){
                if(_46c){
                    this.rows.styleRowNode(_46b,_46c);
                }
                },
            _mouseOut:function(e){
                this.rows.setOverRow(-2);
            },
            getCell:function(_46d){
                return this.layout.cells[_46d];
            },
            setCellWidth:function(_46e,_46f){
                this.getCell(_46e).unitWidth=_46f;
            },
            getCellName:function(_470){
                return "Cell "+_470.index;
            },
            canSort:function(_471){},
            sort:function(){},
            getSortAsc:function(_472){
                _472=_472==undefined?this.sortInfo:_472;return Boolean(_472>0);
            },
            getSortIndex:function(_473){
                _473=_473==undefined?this.sortInfo:_473;return Math.abs(_473)-1;
            },
            setSortIndex:function(_474,_475){
                var si=_474+1;if(_475!=undefined){
                    si*=(_475?1:-1);
                }else{
                    if(this.getSortIndex()==_474){
                        si=-this.sortInfo;
                    }
                    }this.setSortInfo(si);
            },
            setSortInfo:function(_476){
                if(this.canSort(_476)){
                    this.sortInfo=_476;this.sort();this.update();
                }
                },
            doKeyEvent:function(e){
                e.dispatch="do"+e.type;this.onKeyEvent(e);
            },
            _dispatch:function(m,e){
                if(m in this){
                    return this[m](e);
                }return false;
            },
            dispatchKeyEvent:function(e){
                this._dispatch(e.dispatch,e);
            },
            dispatchContentEvent:function(e){
                this.edit.dispatchEvent(e)||e.sourceView.dispatchContentEvent(e)||this._dispatch(e.dispatch,e);
            },
            dispatchHeaderEvent:function(e){
                e.sourceView.dispatchHeaderEvent(e)||this._dispatch("doheader"+e.type,e);
            },
            dokeydown:function(e){
                this.onKeyDown(e);
            },
            //[duongtb:added code for driving onchange, onkeypress events
            dochange:function(e){
                // do something
            },
            dokeypress:function(e){
              // do something
            }
            //]
            ,
            doclick:function(e){
                if(e.cellNode){
                    this.onCellClick(e);
                }else{
                    this.onRowClick(e);
                }
//                   [duongtb
                if(e.cell!=undefined){
                    //if(e.cell.type==undefined && e.cell.formatter==undefined){
                    if(e.cell.wrapping !== true){
                        try{
                            dijit.hideTooltip(e.cellNode);
                            dijit._masterTT._onDeck=null;
                        }catch(e){
                            console.log(e.message);
                        }
                    }
                }
//                ]
                },
            dodblclick:function(e){
                if(e.cellNode){
                    this.onCellDblClick(e);
                }else{
                    this.onRowDblClick(e);
                }
                },
            docontextmenu:function(e){
                if(e.cellNode){
                    this.onCellContextMenu(e);
                }else{
                    this.onRowContextMenu(e);
                }
                },
            doheaderclick:function(e){
                if(e.cell !== undefined && e.cell.type !== dojox.grid.cells.vtBool && e.cell.type !== dojox.grid.cells.Bool){ // header checkbox
                    if(e.cellNode){
                        this.onHeaderCellClick(e);
                    }else{
                        this.onHeaderClick(e);
                    }
                }
                },
            doheaderdblclick:function(e){
                if(e.cellNode){
                    this.onHeaderCellDblClick(e);
                }else{
                    this.onHeaderDblClick(e);
                }
                },
            doheadercontextmenu:function(e){
                if(e.cellNode){
                    this.onHeaderCellContextMenu(e);
                }else{
                    this.onHeaderContextMenu(e);
                }
                },
            doStartEdit:function(_477,_478){
                this.onStartEdit(_477,_478);
            },
            doApplyCellEdit:function(_479,_47a,_47b){
                this.onApplyCellEdit(_479,_47a,_47b);
            },
            doCancelEdit:function(_47c){
                this.onCancelEdit(_47c);
            },
            doApplyEdit:function(_47d){
                this.onApplyEdit(_47d);
            },
            addRow:function(){
                this.updateRowCount(this.attr("rowCount")+1);
            },
            removeSelectedRows:function(){
                if(this.allItemsSelected){
                    this.updateRowCount(0);
                }else{
                    this.updateRowCount(Math.max(0,this.attr("rowCount")-this.selection.getSelected().length));
                }this.selection.clear();
            }
            });dojox.grid._Grid.markupFactory=function(_47e,node,ctor,_47f){
            var d=dojo;var _480=function(n){
                var w=d.attr(n,"width")||"auto";if((w!="auto")&&(w.slice(-2)!="em")&&(w.slice(-1)!="%")){
                    w=parseInt(w,10)+"px";
                }return w;
            };if(!_47e.structure&&node.nodeName.toLowerCase()=="table"){
                _47e.structure=d.query("> colgroup",node).map(function(cg){
                    var sv=d.attr(cg,"span");var v={
                        noscroll:(d.attr(cg,"noscroll")=="true")?true:false,
                        __span:(!!sv?parseInt(sv,10):1),
                        cells:[]
                    };if(d.hasAttr(cg,"width")){
                        v.width=_480(cg);
                    }return v;
                });if(!_47e.structure.length){
                    _47e.structure.push({
                        __span:Infinity,
                        cells:[]
                    });
                }d.query("thead > tr",node).forEach(function(tr,_481){
                    var _482=0;var _483=0;var _484;var _485=null;d.query("> th",tr).map(function(th){
                        if(!_485){
                            _484=0;_485=_47e.structure[0];
                        }else{
                            if(_482>=(_484+_485.__span)){
                                _483++;_484+=_485.__span;var _486=_485;_485=_47e.structure[_483];
                            }
                            }var cell={
                            name:d.trim(d.attr(th,"name")||th.innerHTML),
                            colSpan:parseInt(d.attr(th,"colspan")||1,10),
                            type:d.trim(d.attr(th,"cellType")||""),
                            id:d.trim(d.attr(th,"id")||"")
                            };_482+=cell.colSpan;var _487=d.attr(th,"rowspan");if(_487){
                            cell.rowSpan=_487;
                        }if(d.hasAttr(th,"width")){
                            cell.width=_480(th);
                        }if(d.hasAttr(th,"relWidth")){
                            cell.relWidth=window.parseInt(dojo.attr(th,"relWidth"),10);
                        }if(d.hasAttr(th,"hidden")){
                            cell.hidden=d.attr(th,"hidden")=="true";
                        }if(_47f){
                            _47f(th,cell);
                        }cell.type=cell.type?dojo.getObject(cell.type):dojox.grid.cells.Cell;if(cell.type&&cell.type.markupFactory){
                            cell.type.markupFactory(th,cell);
                        }if(!_485.cells[_481]){
                            _485.cells[_481]=[];
                        }_485.cells[_481].push(cell);
                    });
                });
            }return new ctor(_47e,node);
        };
    })();
}if(!dojo._hasResource["dojox.grid.DataSelection"]){
    dojo._hasResource["dojox.grid.DataSelection"]=true;dojo.provide("dojox.grid.DataSelection");dojo.declare("dojox.grid.DataSelection",dojox.grid.Selection,{
        getFirstSelected:function(){
            var idx=dojox.grid.Selection.prototype.getFirstSelected.call(this);if(idx==-1){
                return null;
            }return this.grid.getItem(idx);
        },
        getNextSelected:function(_488){
            var _489=this.grid.getItemIndex(_488);var idx=dojox.grid.Selection.prototype.getNextSelected.call(this,_489);if(idx==-1){
                return null;
            }return this.grid.getItem(idx);
        },
        getSelected:function(){
            var _48a=[];for(var i=0,l=this.selected.length;i<l;i++){
                if(this.selected[i]){
                    _48a.push(this.grid.getItem(i));
                }
                }return _48a;
        },
        addToSelection:function(_48b){
            if(this.mode=="none"){
                return;
            }var idx=null;if(typeof _48b=="number"||typeof _48b=="string"){
                idx=_48b;
            }else{
                idx=this.grid.getItemIndex(_48b);
            }dojox.grid.Selection.prototype.addToSelection.call(this,idx);
        },
        deselect:function(_48c){
            if(this.mode=="none"){
                return;
            }var idx=null;if(typeof _48c=="number"||typeof _48c=="string"){
                idx=_48c;
            }else{
                idx=this.grid.getItemIndex(_48c);
            }dojox.grid.Selection.prototype.deselect.call(this,idx);
        },
        deselectAll:function(_48d){
            var idx=null;if(_48d||typeof _48d=="number"){
                if(typeof _48d=="number"||typeof _48d=="string"){
                    idx=_48d;
                }else{
                    idx=this.grid.getItemIndex(_48d);
                }dojox.grid.Selection.prototype.deselectAll.call(this,idx);
            }else{
                this.inherited(arguments);
            }
            }
        });
}if(!dojo._hasResource["dojox.grid.DataGrid"]){
    dojo._hasResource["dojox.grid.DataGrid"]=true;dojo.provide("dojox.grid.DataGrid");dojo.declare("dojox.grid.DataGrid",dojox.grid._Grid,{
        store:null,
        query:null,
        queryOptions:null,
        fetchText:"...",
        sortFields:null,
        updateDelay:1,
        items:null,
        _store_connects:null,
        _by_idty:null,
        _by_idx:null,
        _cache:null,
        _pages:null,
        _pending_requests:null,
        _bop:-1,
        _eop:-1,
        _requests:0,
        rowCount:0,
        _isLoaded:false,
        _isLoading:false,
//        [duongtb
        _markup:[],// markup all checked checkbox
        _initialized:[],// markup when initting checked status
        _checked:null, // mark checked radio
        _checkAll:false,
//        ]
        postCreate:function(){
            this._pages=[];this._store_connects=[];this._by_idty={};this._by_idx=[];this._cache=[];this._pending_requests={};this._setStore(this.store);this.inherited(arguments);
        },
        createSelection:function(){
            this.selection=new dojox.grid.DataSelection(this);
        },
        get:function(_48e,_48f){
            if(_48f&&this.field=="_item"&&!this.fields){
                return _48f;
            }else{
                if(_48f&&this.fields){
                    var ret=[];var s=this.grid.store;dojo.forEach(this.fields,function(f){
                        ret=ret.concat(s.getValues(_48f,f));
                    });return ret;
                }
                }return (!_48f?this.defaultValue:(!this.field?this.value:(this.field=="_item"?_48f:this.grid.store.getValue(_48f,this.field))));
        },
        _checkUpdateStatus:function(){
            if(this.updateDelay>0){
                var _490=false;if(this._endUpdateDelay){
                    clearTimeout(this._endUpdateDelay);delete this._endUpdateDelay;_490=true;
                }if(!this.updating){
                    this.beginUpdate();_490=true;
                }if(_490){
                    var _491=this;this._endUpdateDelay=setTimeout(function(){
                        delete _491._endUpdateDelay;_491.endUpdate();
                    },this.updateDelay);
                }
                }
            },
        _onSet:function(item,_492,_493,_494){
            this._checkUpdateStatus();var idx=this.getItemIndex(item);if(idx>-1){
                this.updateRow(idx);
            }
            },
        _createItem:function(item,_495){
            var idty=this._hasIdentity?this.store.getIdentity(item):dojo.toJson(this.query)+":idx:"+_495+":sort:"+dojo.toJson(this.getSortProps());var o=this._by_idty[idty]={
                idty:idty,
                item:item
            };return o;
        },
        _addItem:function(item,_496,_497){
            this._by_idx[_496]=this._createItem(item,_496);if(!_497){
                this.updateRow(_496);
            }
            },
        _onNew:function(item,_498){
            this._checkUpdateStatus();var _499=this.attr("rowCount");this._addingItem=true;this.updateRowCount(_499+1);this._addingItem=false;this._addItem(item,_499);this.showMessage();
        },
        _onDelete:function(item){
            this._checkUpdateStatus();var idx=this._getItemIndex(item,true);if(idx>=0){
                this._pages=[];this._bop=-1;this._eop=-1;var o=this._by_idx[idx];this._by_idx.splice(idx,1);delete this._by_idty[o.idty];this.updateRowCount(this.attr("rowCount")-1);if(this.attr("rowCount")===0){
                    this.showMessage(this.noDataMessage);
                }
                }
            },
        _onRevert:function(){
            this._refresh();
        },
        setStore:function(_49a,_49b,_49c){
            this._setQuery(_49b,_49c);this._setStore(_49a);this._refresh(true);
        },
        setQuery:function(_49d,_49e){
            this._setQuery(_49d,_49e);this._refresh(true);
        },
        setItems:function(_49f){
            this.items=_49f;this._setStore(this.store);this._refresh(true);
        },
        _setQuery:function(_4a0,_4a1){
            this.query=_4a0;this.queryOptions=_4a1||this.queryOptions;
        },
        _setStore:function(_4a2){
            if(this.store&&this._store_connects){
                dojo.forEach(this._store_connects,function(arr){
                    dojo.forEach(arr,dojo.disconnect);
                });
            }this.store=_4a2;if(this.store){
                var f=this.store.getFeatures();var h=[];this._canEdit=!!f["dojo.data.api.Write"]&&!!f["dojo.data.api.Identity"];this._hasIdentity=!!f["dojo.data.api.Identity"];if(!!f["dojo.data.api.Notification"]&&!this.items){
                    h.push(this.connect(this.store,"onSet","_onSet"));h.push(this.connect(this.store,"onNew","_onNew"));h.push(this.connect(this.store,"onDelete","_onDelete"));
                }if(this._canEdit){
                    h.push(this.connect(this.store,"revert","_onRevert"));
                }this._store_connects=h;
            }
            },
        _onFetchBegin:function(size,req){
            if(!this.scroller){
                return;
            }if(this.rowCount!=size){
                if(req.isRender){
                    this.scroller.init(size,this.keepRows,this.rowsPerPage);this.rowCount=size;this._setAutoHeightAttr(this.autoHeight,true);this._skipRowRenormalize=true;this.prerender();this._skipRowRenormalize=false;
                }else{
                    this.updateRowCount(size);
                }
                }if(!size){
                this.views.render();this._resize();this.showMessage(this.noDataMessage);this.focus.initFocusView();
            }else{
                this.showMessage();
            }
            },
        _onFetchComplete:function(_4a3,req){
            if(!this.scroller){
                return;
            }if(_4a3&&_4a3.length>0){
                dojo.forEach(_4a3,function(item,idx){
                    this._addItem(item,req.start+idx,true);
                },this);if(this._autoHeight){
                    this._skipRowRenormalize=true;
                }this.updateRows(req.start,_4a3.length);if(this._autoHeight){
                    this._skipRowRenormalize=false;
                }if(req.isRender){
                    this.setScrollTop(0);this.postrender();
                }else{
                    if(this._lastScrollTop){
                        this.setScrollTop(this._lastScrollTop);
                    }
                    }
                }delete this._lastScrollTop;if(!this._isLoaded){
                this._isLoading=false;this._isLoaded=true;
            }this._pending_requests[req.start]=false;
        },
        _onFetchError:function(err,req){
            delete this._lastScrollTop;if(!this._isLoaded){
                this._isLoading=false;this._isLoaded=true;this.showMessage(this.errorMessage);
            }this._pending_requests[req.start]=false;this.onFetchError(err,req);
        },
        onFetchError:function(err,req){},
        _fetch:function(_4a4,_4a5){
            _4a4=_4a4||0;if(this.store&&!this._pending_requests[_4a4]){
                if(!this._isLoaded&&!this._isLoading){
                    this._isLoading=true;this.showMessage(this.loadingMessage);
                }this._pending_requests[_4a4]=true;try{
                    if(this.items){
                        var _4a6=this.items;var _4a7=this.store;this.rowsPerPage=_4a6.length;var req={
                            start:_4a4,
                            count:this.rowsPerPage,
                            isRender:_4a5
                        };this._onFetchBegin(_4a6.length,req);var _4a8=0;dojo.forEach(_4a6,function(i){
                            if(!_4a7.isItemLoaded(i)){
                                _4a8++;
                            }
                            });if(_4a8===0){
                            this._onFetchComplete(_4a6,req);
                        }else{
                            var _4a9=function(item){
                                _4a8--;if(_4a8===0){
                                    this._onFetchComplete(_4a6,req);
                                }
                                };dojo.forEach(_4a6,function(i){
                                if(!_4a7.isItemLoaded(i)){
                                    _4a7.loadItem({
                                        item:i,
                                        onItem:_4a9,
                                        scope:this
                                    });
                                }
                                },this);
                        }
                        }else{
                        this.store.fetch({
                            start:_4a4,
                            count:this.rowsPerPage,
                            query:this.query,
                            sort:this.getSortProps(),
                            queryOptions:this.queryOptions,
                            isRender:_4a5,
                            onBegin:dojo.hitch(this,"_onFetchBegin"),
                            onComplete:dojo.hitch(this,"_onFetchComplete"),
                            onError:dojo.hitch(this,"_onFetchError")
                            });
                    }
                    }catch(e){
                    this._onFetchError(e,{
                        start:_4a4,
                        count:this.rowsPerPage
                        });
                }
                }
            },
        _clearData:function(){
            this.updateRowCount(0);this._by_idty={};this._by_idx=[];this._pages=[];this._bop=this._eop=-1;this._isLoaded=false;this._isLoading=false;
        },
        getItem:function(idx){
            var data=this._by_idx[idx];if(!data||(data&&!data.item)){
                this._preparePage(idx);return null;
            }return data.item;
        },
        getItemIndex:function(item){
            return this._getItemIndex(item,false);
        },
        _getItemIndex:function(item,_4aa){
            if(!_4aa&&!this.store.isItem(item)){
                return -1;
            }var idty=this._hasIdentity?this.store.getIdentity(item):null;for(var i=0,l=this._by_idx.length;i<l;i++){
                var d=this._by_idx[i];if(d&&((idty&&d.idty==idty)||(d.item===item))){
                    return i;
                }
                }return -1;
        },
        filter:function(_4ab,_4ac){
            this.query=_4ab;if(_4ac){
                this._clearData();
            }this._fetch();
        },
        _getItemAttr:function(idx,attr){
            var item=this.getItem(idx);return (!item?this.fetchText:this.store.getValue(item,attr));
        },
        _render:function(){
            if(this.domNode.parentNode){
                this.scroller.init(this.attr("rowCount"),this.keepRows,this.rowsPerPage);this.prerender();this._fetch(0,true);
            }
            },
        _requestsPending:function(_4ad){
            return this._pending_requests[_4ad];
        },
        _rowToPage:function(_4ae){
            return (this.rowsPerPage?Math.floor(_4ae/this.rowsPerPage):_4ae);
        },
        _pageToRow:function(_4af){
            return (this.rowsPerPage?this.rowsPerPage*_4af:_4af);
        },
        _preparePage:function(_4b0){
            if((_4b0<this._bop||_4b0>=this._eop)&&!this._addingItem){
                var _4b1=this._rowToPage(_4b0);this._needPage(_4b1);this._bop=_4b1*this.rowsPerPage;this._eop=this._bop+(this.rowsPerPage||this.attr("rowCount"));
            }
            },
        _needPage:function(_4b2){
            if(!this._pages[_4b2]){
                this._pages[_4b2]=true;this._requestPage(_4b2);
            }
            },
        _requestPage:function(_4b3){
            var row=this._pageToRow(_4b3);var _4b4=Math.min(this.rowsPerPage,this.attr("rowCount")-row);if(_4b4>0){
                this._requests++;if(!this._requestsPending(row)){
                    setTimeout(dojo.hitch(this,"_fetch",row,false),1);
                }
                }
            },
        getCellName:function(_4b5){
            return _4b5.field;
        },
        _refresh:function(_4b6){
            this._clearData();
            this._fetch(0,_4b6);
        },
        sort:function(){
            this._lastScrollTop=this.scrollTop;this._refresh();
        },
        canSort:function(){
            return (!this._isLoading);
        },
        getSortProps:function(){
            var c=this.getCell(this.getSortIndex());if(!c){
                if(this.sortFields){
                    return this.sortFields;
                }return null;
            }else{
                var desc=c["sortDesc"];var si=!(this.sortInfo>0);if(typeof desc=="undefined"){
                    desc=si;
                }else{
                    desc=si?!desc:desc;
                }return [{
                    attribute:c.field,
                    descending:desc
                }];
            }
            },
        styleRowState:function(_4b7){
            if(this.store&&this.store.getState){
                var _4b8=this.store.getState(_4b7.index),c="";for(var i=0,ss=["inflight","error","inserting"],s;s=ss[i];i++){
                    if(_4b8[s]){
                        c=" dojoxGridRow-"+s;break;
                    }
                    }_4b7.customClasses+=c;
            }
            },
        onStyleRow:function(_4b9){
            this.styleRowState(_4b9);this.inherited(arguments);
        },
        canEdit:function(_4ba,_4bb){
            return this._canEdit;
        },
        _copyAttr:function(idx,attr){
            var row={};var _4bc={};var src=this.getItem(idx);return this.store.getValue(src,attr);
        },
        doStartEdit:function(_4bd,_4be){
            if(!this._cache[_4be]){
                this._cache[_4be]=this._copyAttr(_4be,_4bd.field);
            }this.onStartEdit(_4bd,_4be);
        },
        doApplyCellEdit:function(_4bf,_4c0,_4c1){
            this.store.fetchItemByIdentity({
                identity:this._by_idx[_4c0].idty,
                onItem:dojo.hitch(this,function(item){
                    var _4c2=this.store.getValue(item,_4c1);if(typeof _4c2=="number"){
                        _4bf=isNaN(_4bf)?_4bf:parseFloat(_4bf);
                    }else{
                        if(typeof _4c2=="boolean"){
                            _4bf=_4bf=="true"?true:_4bf=="false"?false:_4bf;
                        }else{
                            if(_4c2 instanceof Date){
                                var _4c3=new Date(_4bf);_4bf=isNaN(_4c3.getTime())?_4bf:_4c3;
                            }
                            }
                        }this.store.setValue(item,_4c1,_4bf);this.onApplyCellEdit(_4bf,_4c0,_4c1);
                })
                });
        },
        doCancelEdit:function(_4c4){
            var _4c5=this._cache[_4c4];if(_4c5){
                this.updateRow(_4c4);delete this._cache[_4c4];
            }this.onCancelEdit.apply(this,arguments);
        },
        doApplyEdit:function(_4c6,_4c7){
            var _4c8=this._cache[_4c6];this.onApplyEdit(_4c6);
        },
        removeSelectedRows:function(){
            if(this._canEdit){
                this.edit.apply();var fx=dojo.hitch(this,function(_4c9){
                    if(_4c9.length){
                        dojo.forEach(_4c9,this.store.deleteItem,this.store);this.selection.clear();
                    }
                    });if(this.allItemsSelected){
                    this.store.fetch({
                        query:this.query,
                        queryOptions:this.queryOptions,
                        onComplete:fx
                    });
                }else{
                    fx(this.selection.getSelected());
                }
                }
            }
        });dojox.grid.DataGrid.cell_markupFactory=function(_4ca,node,_4cb){
        var _4cc=dojo.trim(dojo.attr(node,"field")||"");if(_4cc){
            _4cb.field=_4cc;
        }_4cb.field=_4cb.field||_4cb.name;var _4cd=dojo.trim(dojo.attr(node,"fields")||"");if(_4cd){
            _4cb.fields=_4cd.split(",");
        }if(_4ca){
            _4ca(node,_4cb);
        }
        };dojox.grid.DataGrid.markupFactory=function(_4ce,node,ctor,_4cf){
        return dojox.grid._Grid.markupFactory(_4ce,node,ctor,dojo.partial(dojox.grid.DataGrid.cell_markupFactory,_4cf));
    };
}dojo.i18n._preloadLocalizations("dojox.grid.nls.DataGrid",["ROOT","ar","ca","cs","da","de","de-de","el","en","en-gb","en-us","es","es-es","fi","fi-fi","fr","fr-fr","he","he-il","hu","it","it-it","ja","ja-jp","ko","ko-kr","nl","nl-nl","no","pl","pt","pt-br","pt-pt","ru","sk","sl","sv","th","tr","xx","zh","zh-cn","zh-tw"]);
// [stupid code
headerCheck = function(cbx){
    var gridId = cbx.id.substr(4,cbx.id.length);
    if(cbx.checked == true){
        dijit.byId(gridId).vtCheckAll();
        this._checkAll = true;
    } else {
        dijit.byId(gridId).vtUnCheckAll();
        this._checkAll = false;
    }
}
//]
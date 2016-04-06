dojo.provide("vt.tree.treeTable.TreeTable");
dojo.require("dojo.dnd.Source");

/**
 * dojo TreeTable
 */

dojo.declare( "vt.tree.treeTable.TreeTable",[dijit._Widget, dijit._Templated],
{
    templatePath: dojo.moduleUrl("vt", "templates/treeTable.html"),
    getDataUrl: "",
    width: "",
    ggap: "",
    columns: null,

    postCreate: function() {
        // get container from template
        this.container = this.gridDiv.id;
        // create dataStore
        this.dataStore=null;
        if (this.getDataUrl == ""){
            this.dataStore = new dojo.data.ItemFileWriteStore({
                data:{
                    identifier: '',
                    label: '',
                    items: []
                }
            });
        }else{
            this.dataStore = new dojo.data.ItemFileWriteStore({
                url:this.getDataUrl,
                urlPreventCache:true
            });
        }
        this.nodes = {};
        this._nodes = {};
    },
    startup:function(){
        // get data from server via fetch and onComplete of dojo.data.ItemFileWriteStore
        var _treeTable = this;
        _treeTable.dataStore.fetch( {
            onComplete: function(data) {
                if (_treeTable.dataStore._arrayOfAllItems) {
                    for (var i in _treeTable.dataStore._arrayOfAllItems) {
                        _treeTable.addNode(_treeTable.dataStore._arrayOfAllItems[i]);
                    }
                }
                _treeTable.render();
                _treeTable.node(1).expand();
                _treeTable.colorize();
            }
        });
    },
    tableEl: function() {
        return this.tableEl;
    },
    bodyEl: function() {
        return this.tbodyEl;
    },

    addNode: function(item) {
        try {
            if (typeof this.nodes[item.pid] == 'undefined') {
                this.nodes[item.pid] = [];
            }
            // if dataObject was received
            if (!(item instanceof vt.tree.treeTable.TreeNode)) {
                item = new vt.tree.treeTable.TreeNode(item, this);
            }
            this.nodes[item.pid].push(item);
            this._nodes[item.id] = item;
        }
        catch(e){
            alert("JSException in TreeTable.js-addNode: \n"+e.message);
        }
        
    },
    createSibling: function() {
        dojo.query('#' + this.id + ' .node-sibling').orphan();
        var el = dojo.doc.createElement("tr");
        el.className = 'node-hidden node-sibling';
        el.innerHTML = '<td></td><td></td><td></td>';
        return el;
    },

    siblingFix:  function() {
        var el = this.createSibling();
        this.bodyEl().appendChild(el);
    },
    render:  function() {
        try {
            var to = dojo.byId(this.container);

            var tableEl = dojo.doc.createElement('table');
            tableEl.id = this.id+"Table";
            tableEl.className = 'dojo-treetable';
            if (this.width)
                tableEl.style.width = this.width
            this.tableEl=tableEl;
            var theadEl = dojo.doc.createElement('thead');
            tableEl.appendChild(theadEl);

            var trEl = dojo.doc.createElement('tr');
            theadEl.appendChild(trEl);
            for (var i in this.columns) {
                if(!dojo.isFunction(this.columns[i])){
                    var thEl = dojo.doc.createElement('th');
                    thEl.innerHTML = this.columns[i].title;
                    if (this.columns[i].width) {
                        thEl.style.width = this.columns[i].width;
                    }
                    trEl.appendChild(thEl);
                }
               
            }
            var tbodyEl = dojo.doc.createElement('tbody');
            tbodyEl.id = this.id + '_tbody';
            tableEl.appendChild(tbodyEl);
            this.tbodyEl = tbodyEl;

            if (!this.nodes[0])
                return;
            for (var i in this.nodes[0]) {
                var node = this.nodes[0][i];
                if (!dojo.isFunction(node)){
                    node.render();
                }
            }
            to.appendChild(tableEl);
            // fix for nextSibling
            this.siblingFix();
        }
        catch(e) {
            console.debug("render:"+e.message);
        }
       
    },
    node: function(id) {
        return this._nodes[id];
    },
    colorize: function() {
        dojo.query('#' + this.id + '_tbody .dojoxGridRowOdd').removeClass('dojoxGridRowOdd');

        var nodes = dojo.query('#' + this.id + '_tbody tr.node-visible');
        for (var i = 0, n = nodes.length; i < n; i++) {
            if (i % 2) {
                dojo.addClass(nodes[i], 'dojoxGridRowOdd');
            }
        }
    },
    // event handler
    onChecked: function(node) {
    // callback
    //    alert(node.config.id);
    },
    onUnchecked: function(node) {
    // callback
    //    alert(node.config.id);
    },
    onNodeClick: function(node) {
    // callback
    //    alert(node.config.id);
    },
    // reload treeTable
    vtReload:function(url,formId,param,callback){
        var _treeTable=this;
        // remove all data and display tag in jsp
        _treeTable.remove();
        // call url to get data and process bussiness at server
        var getArgs = {
            url: url,
            form:formId,
            handleAs:"json-comment-optional",
            content:param
        };
        try{
            var getHandler = dojo.xhrPost(getArgs);
            getHandler.addCallback(function(data){
                for (var i =0; i < data.items.length; i ++){
                    _treeTable.addNode(data.items[i]);
                }
                _treeTable.render();
                _treeTable.node(1).expand();
                _treeTable.colorize();
            });
        }
        catch(e){
            console.debug("vtReload, error: " + e.message );
        }
    },
    remove:function()
    {
        try {
            var _treeTable=this;
            var to = dojo.byId(_treeTable.container);
            to.removeChild(_treeTable.tableEl);
            _treeTable.nodes = {};
            _treeTable._nodes = {};
            _treeTable.dataStore=null;
        }
        catch(e){
            console.debug("remove, error: "+ e.message );
        }
      
    },
    // manage data
    getAllNodeForPost: function(form,state,arrProps){
        var outContent = {};
        try {
            for (var i in this.nodes) {
                for(var j in this.nodes[i]) {
                    if(!dojo.isFunction(this.nodes[i][j])){
                        var node = this.nodes[i][j];
                        if (typeof node.checkbox() != 'undefined') {
                            var checked= node.checkbox().childNodes[0].checked;
                            if(checked==state) {
                                this._getAllNodeForPost(node.config,outContent,form,arrProps);
                            }
                        }
                    }
                    
                    
                }
            }
        }
        catch(e)
        {
            alert("getAllNodeForPost: "+e.message);
        }
       
        return outContent;
    },
    _getAllNodeForPost: function(item,outContent,form,arrProps){
        if (arrProps){
            for (var i = 0;i < arrProps.length; i++ ){
                var prop = arrProps[i];
                if (item[prop]){
                    var propForPost = form +  "." + prop;
                    if (!dojo.isObject(item[prop][0])||item[prop][0] == null){
                        if (!outContent[ propForPost]){
                            outContent[ propForPost] = [];
                        }
                        outContent[ propForPost].push(item[prop][0]);
                    }
                    else{
                        var compositItem = item[prop][0];
                        this._getAllNodeForPost(compositItem,outContent,propForPost,arrProps);
                    }
                }
            }
        }
        else{
            for (var prop in item){
                if ((prop != "_0") && (prop != "_R") && (prop!="_RS")
                    && (prop!="_RI") && (prop!="_S") && (prop!="_RRM")){
                    var propForPost = form +  "." + prop;
                    if (!dojo.isObject(item[prop][0])||item[prop][0] == null){
                        if (!outContent[ propForPost]){
                            outContent[ propForPost] = [];
                        }
                        outContent[ propForPost].push(item[prop][0]);
                    }
                    else{
                        var compositItem = item[prop][0];
                        this._getAllNodeForPost(compositItem,outContent,propForPost,arrProps);
                    }
                }
            }
        }

    }
});


/**
 * TreeNode
 */
dojo.declare( "vt.tree.treeTable.TreeNode",null,
{
    constructor: function(/*Config data*/config,/*treeTable*/ tree) {
        this.config = config;
        this.id = config.id;
        this.elId = 'node_' + this.id;
        this.pid = config.pid;
        this.tree = tree;
        this.expanded = false;
        this._visibleChilds = [];
    },

    el: function() {
        alert("el");
        return this.nodeEl;
    },

    hasChilds: function() {
        return (typeof this.tree.nodes[this.id]) != 'undefined' && this.tree.nodes[this.id].length > 0;
    },

    lastRenderedChild: function() {
        if (this.hasChilds()) {
            var nodes = this.tree.nodes[this.id];
            var last = nodes[nodes.length - 1];
            var all = [last].concat(last.childsAll());
            var last;
            for (var i in all) {
                if (all[i].rendered)
                    last = all[i];
            }
            return last;
        } else
            return false;
    },

    childs: function() {
        return this.hasChilds() ? this.tree.nodes[this.id] : [];
    },

    childsAll: function() {
        var nodes = [];

        var _nodes = this.childs();
        for (var i in _nodes) {
            var node = _nodes[i];
            nodes = nodes.concat(node, node.childsAll());
        }
        return nodes;
    },
    hasNode: function(node) {
        var nodes = this.childsAll();
        for (var i in nodes) {
            if (nodes[i].id == node.id)
                return true;
        }
        return false;
    },

    icon: function() {
        return this.iconEl;
    },
    checkbox: function(){
        return this.cbEl;
    },
    titleEl: function() {
        return dojo.query('#' + this.elId + ' .node-title')[0];
    },

    visibleChilds: function() {
        var nodes = [];
        if (!this.hasChilds())
            return nodes;

        var _nodes = this.childs();
        for (var i in _nodes) {
            var node = _nodes[i];
            if (node.nodeEl && dojo.hasClass(node.nodeEl, 'node-visible')) {
                nodes.push(node);
            } else
                continue;

            nodes = nodes.concat(node.visibleChilds());
        }
        return nodes;
    },

    lvl: function() {
        if (this.pid == 0) {
            return 0;
        }

        return 1 + this.tree._nodes[this.pid].lvl();
    },

    updateLvl: function() {
        if (this.rendered)
            this.dndSourceEl.style.paddingLeft =  (this.lvl() * this.tree.ggap) + 'px';
    },

    show: function() {
        dojo.removeClass(this.elId, 'node-hidden');
        dojo.addClass(this.elId, 'node-visible');
    },

    hide: function() {
        dojo.removeClass(this.elId, 'node-visible');
        dojo.addClass(this.elId, 'node-hidden');
    },

    iconUpdate: function() {
        var icon = this.icon();

        dojo.removeClass(icon, 'folder-expanded');
        dojo.removeClass(icon, 'folder-collapsed');
        dojo.removeClass(icon, 'doc');

        if (this.hasChilds()) {
            if (this.expanded)
                dojo.addClass(icon, 'folder-expanded');
            else
                dojo.addClass(icon, 'folder-collapsed');
        } else {
            dojo.addClass(icon, 'doc');
            this.expanded = false;
        }
    },

    expand: function() {
        if (!this.hasChilds()) {
            return;
        }
        var _nodes = this.tree.nodes[this.id].concat().reverse();
        for (var i in _nodes) {
            if (!_nodes[i].rendered) {
                if(!dojo.isFunction(_nodes[i])){
                    _nodes[i].render();
                    _nodes[i].show();
                }
               
            }
            
        }
        this.expanded = true;

        this.iconUpdate();

        for (var i in this._visibleChilds) {
            if(!dojo.isFunction(this._visibleChilds[i])){
                this._visibleChilds[i].show();
            }
           
        }
        this.tree.colorize();
    },

    collapse: function() {
        if (!this.hasChilds()) {
            return;
        }

        this._visibleChilds = this.visibleChilds();

        for (var i in this._visibleChilds) {
            if(!dojo.isFunction( this._visibleChilds[i])){
                this._visibleChilds[i].hide();
            }
           
        }
        this.expanded = false;

        var icon = this.icon();
        dojo.addClass(icon, 'folder-collapsed');
        dojo.removeClass(icon, 'folder-expanded');

        this.tree.colorize();
    },

    toggle: function() {
        if (this.expanded)
            this.collapse();
        else
            this.expand();
    },

    onCheckboxClick: function() {
        var state=this.checkbox().childNodes[0].checked;
        if(state) {
            this.tree.onChecked(this);
        }
        else {
            this.tree.onUnchecked(this);
        }
    },
    onNodeClick: function() {
        this.tree.onNodeClick(this);
    },
    onDrop: function(src, nodes, copy) {
        var toNode = this.ssnode;
        var srcNode = src.ssnode;
        srcNode.move(toNode);
        toNode.tree.colorize();
    },

    render: function() {
        var el = dojo.doc.createElement("tr");
        this.nodeEl = el;

        if (this.pid != 0) {
            dojo.addClass(el, 'node-hidden');
        } else {
            dojo.addClass(el, 'node-visible');
        }

        el.id = this.elId;

        // checkbox
        var cbEl = dojo.doc.createElement('td');
        cbEl.innerHTML ='<input type="checkbox">';
        this.cbEl=cbEl;
        el.appendChild(cbEl);
        // title
        this.type = this.hasChilds() ? 'folder' : 'doc';
        var cls = this.hasChilds() ? 'folder-collapsed' : 'doc';
        var titleEl = dojo.doc.createElement('td');
        titleEl.style.paddingLeft = (this.lvl() * this.tree.ggap) + 'px';
        titleEl.style.paddingRight = '30px';
        titleEl.id = 'dnd_source_' + this.id;

        var aEl = dojo.doc.createElement('a');
        dojo.addClass(aEl, 'node-icon');
        dojo.addClass(aEl, cls);
        titleEl.appendChild(aEl);
        this.iconEl = aEl;

        var spanEl = dojo.doc.createElement('span');
        spanEl.innerHTML = this.config.title;
        spanEl.className = 'node-title';
        titleEl.appendChild(spanEl);
        this.spanEl=spanEl;
        el.appendChild(titleEl);
        // title end

        for (var i = 2, n = this.tree.columns.length; i < n; i++) {
            var columns = this.tree.columns[i];
            var oEl = dojo.doc.createElement('td');
            if (columns.renderer) {
                oEl.innerHTML = columns.renderer(this);
            } else {
                oEl.innerHTML = this.config[columns.field];
            }
            el.appendChild(oEl);
        }

        // insert element
        if (this.pid == 0)
            this.tree.tbodyEl.appendChild(el);
        else {
            var node = this.tree._nodes[this.pid].nodeEl;
            node.parentNode.insertBefore(el, node.nextSibling);
        }
        // add events
        dojo.connect(this.iconEl, 'onclick', this, 'toggle');
        dojo.connect(this.cbEl,'onclick', this,'onCheckboxClick');
        dojo.connect(this.spanEl,'onclick',this,'onNodeClick');
        // render all childs nodes
        /*if (this.hasChilds()) {
        var _nodes = this.tree.nodes[this.id].concat().reverse();
        for (var i in _nodes) {
            _nodes[i].render();
        }
    }*/
        // d'n'd
        this.dndSourceEl = titleEl;
        this.dndSource = new dojo.dnd.Source(this.dndSourceEl);
        this.dndSource.ssnode = this;
        this.dndSource.insertNodes(false, [spanEl]);
        this.dndSource.onDrop = this.onDrop;

        this.rendered = true;
    },

    move: function(toNode) {
        // move this become the last child of toNode
        // update connection in the tree
        if (this.hasNode(toNode) || toNode.id == this.id) {
            return false;
        }
        if (!toNode.expanded) {
            toNode.expand();
        }
        // get parents of this: fromNode
        var fromNode = this.tree._nodes[this.pid];
        /* Search afterEl */
        // afterNode: con cuoi cung da render hoac chinh toNode neu chua co node nao render
        var afterEl, afterNode, lastNode;
        if (lastNode = toNode.lastRenderedChild()) {
            afterNode = lastNode;
        } else {
            afterNode = toNode;
        }
        // afterEl: the <tr> cua afterNode
        // qua trinh render se tao cho moi node con mot the <tr>
        if (toNode.hasNode(afterNode)) {
            afterEl = afterNode.el().nextSibling;
        } else
            afterEl = afterNode.el();

        /*
         * last element fix
         * insert sibling node & using it as afterEl
         * it will be removed at the end
         */
        // sibling: <tr><td></td><td></td><td></td></tr>
        // afterEl.parentNode: <tbody>
        var sibling = this.tree.createSibling();
        var afterEl = afterNode.el();
        afterEl.parentNode.insertBefore(sibling, afterEl.nextSibling);
        afterEl = sibling;

        /* move all */
        var nodes = [this].concat(this.childsAll()).reverse();
        for (var i in nodes) {
            if (!nodes[i].rendered) continue;
            afterEl.parentNode.insertBefore(nodes[i].el(), afterEl.nextSibling);
            nodes[i].updateLvl();
        }
        this.tree.siblingFix();

        // update tree joins
        var pid = this.pid;
        this.pid = toNode.id;
        for (var i in this.tree.nodes[pid]) {
            if (this.tree.nodes[pid][i].id == this.id) {
                this.tree.nodes[pid].splice(i, 1);
                break;
            }
        }
        this.tree.addNode(this);

        for (var i in nodes) {
            nodes[i].updateLvl();
        }

        // fix for empty nodes
        toNode.expanded = true;

        toNode.iconUpdate();
        if (fromNode)
            fromNode.iconUpdate();
    }
});

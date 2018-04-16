var width = Ext.getBody().getWidth();

Ext.onReady(function() {
	var name=document.getElementById("name").value;
	var viewport = new Ext.Viewport( {
        layout: {
            type: 'border',
            padding: '5'
        },
        items: [{
            region: 'north',
            height: 50,
            border: false,
            margin: '0,0,0,0',
           html: "<div  style='text-align:right'>欢迎你："+name+"，<a href='#'>退出</a></div>"
        }, {
            region: 'west',
            title: '功能导航',
            width: 250,
            stateId: 'statePanelExample',
            stateful: true,
            split: true,
            collapsible: true,
            padding:'0',
            layout: 'accordion',
            items: [
                {
                    title: '功能管理',
                    layout: 'fit',
                    items: [{
                        xtype: 'treepanel',
                        border: 0,
                        rootVisible: false,
                        root: {
                            expanded: true,
                            children: [
                            	{ id: '1', text: '定时任务管理', leaf: true },
                                { id: '2', text: '字典管理', leaf: true},
                                { id: '3', text: '推荐历史', leaf: true}
                            ]
                        },listeners:{
                        	"click":function(node,event){
                        		addTab(node);
                        	}
                        }
                    }
                    
                    ]
                }
            ]
        }, {
            region: 'center',
            xtype: 'tabpanel',
            id: 'MainTabPanel',
            activeTab: 0,
            items: {
                title: '首页',
/*                html: '<h1>欢迎使用推荐1.0系统</h1>'
*/              //autoLoad:'article.html',
				closable: false,  
                autoScroll: false

            }
        }, {
            region: 'south',
            collapsible: false,
            html: '<div class="footer" style="text-align:center">© 新开普电子股份有限公司 2018</div>',
            split: false,
            height: 22
        }]
    });
});


/**
 * 增加tab
 */
    function addTab(node){
    	var nowTab=Ext.getCmp("tab_"+node.id);
    	if(null!=nowTab && undefined!=nowTab){
    		Ext.getCmp("MainTabPanel").setActiveTab(nowTab);
    		return;
    	}
    	var url;
    	if("1"==node.id){
    		url="dictionary/main"
    	}else if("2"==node.id){
    		url=""
    	}
    	var tab=Ext.getCmp("MainTabPanel").add({
    		id:"tab_"+node.id,
    		title:node.text,
    		layout: 'fit',
    		closable : true,
    	  	html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src='+node.url+' id=_tabcontent_'+node.id+'></iframe>' 
    	}).show();
    }


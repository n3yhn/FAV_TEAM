
var myThemePanelBase=g_contextPath + '/share/plug-in/jscookmenu-2.0.4/ThemePanel/';var cmThemePanelBase='/JSCookMenu/ThemePanel/';try
{
    if(myThemePanelBase)

    {
        cmThemePanelBase=myThemePanelBase;
    }
    }
catch(e)
{}
var cmThemePanel={
    prefix:'ThemePanel',
    mainFolderLeft:'<img alt="" src="'+cmThemePanelBase+'blank.gif">',
    mainFolderRight:'<img alt="" src="'+cmThemePanelBase+'blank.gif">',
    mainItemLeft:'<img alt="" src="'+cmThemePanelBase+'blank.gif">',
    mainItemRight:'<img alt="" src="'+cmThemePanelBase+'blank.gif">',
    folderLeft:'<img alt="" src="'+cmThemePanelBase+'blank.gif">',
    folderRight:'<span style="border: 0; width: 24px; padding-top:8px;padding-left:2px;"><img alt="" src="'+cmThemePanelBase+'arrow.gif"></span>',
    itemLeft:'<img alt="" src="'+cmThemePanelBase+'blank.gif">',
    itemRight:'<img alt="" src="'+cmThemePanelBase+'blank.gif">',
    mainSpacing:0,
    subSpacing:0,
    subMenuHeader:'<div class="ThemePanelSubMenuShadow"></div><div class="ThemePanelSubMenuBorder">',
    subMenuFooter:'</div>',
    offsetVMainAdjust:[0,-2],
    offsetSubAdjust:[0,-2]
    };var cmThemePanelHSplit=[_cmNoClick,'<td colspan="3" class="ThemePanelMenuSplit"><div class="ThemePanelMenuSplit"></div></td>'];var cmThemePanelMainHSplit=[_cmNoClick,'<td colspan="3" class="ThemePanelMenuSplit"><div class="ThemePanelMenuSplit"></div></td>'];var cmThemePanelMainVSplit=[_cmNoClick,'|'];
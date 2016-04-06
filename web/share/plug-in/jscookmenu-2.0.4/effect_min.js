
function CMSpecialEffectInstance(effect,menu)
{effect.show=true;effect.menu=menu;menu.cmEffect=effect;this.effect=effect;}
CMSpecialEffectInstance.prototype.canShow=function(changed)
{if(changed)
{if(this.effect.show)
return false;this.effect.show=true;}
else if(!this.effect.show)
return false;return true;}
CMSpecialEffectInstance.prototype.canHide=function(changed)
{var effect=this.effect;if(changed)
{if(!effect.show)
return false;effect.show=false;}
else if(effect.show)
return false;return true;}
CMSpecialEffectInstance.prototype.startShowing=function()
{var menu=this.effect.menu;menu.style.visibility='visible';}
CMSpecialEffectInstance.prototype.finishShowing=function()
{}
CMSpecialEffectInstance.prototype.finishHiding=function()
{var menu=this.effect.menu;menu.style.visibility='hidden';menu.style.top='0px';menu.style.left='0px';menu.cmEffect=null;menu.cmOrient=null;this.effect.menu=null;}
function CMSlidingEffectInstance(menu,orient,speed)
{this.base=new CMSpecialEffectInstance(this,menu);menu.style.overflow='hidden';this.x=menu.offsetLeft;this.y=menu.offsetTop;if(orient.charAt(0)=='h')
{this.slideOrient='h';this.slideDir=orient.charAt(1);}
else
{this.slideOrient='v';this.slideDir=orient.charAt(2);}
this.speed=speed;this.fullWidth=menu.offsetWidth;this.fullHeight=menu.offsetHeight;this.percent=0;}
CMSlidingEffectInstance.prototype.showEffect=function(changed)
{if(!this.base.canShow(changed))
return;var percent=this.percent;if(this.slideOrient=='h')
this.slideMenuV();else
this.slideMenuH();if(percent==0)
{this.base.startShowing();}
if(percent<100)
{this.percent+=this.speed;cmTimeEffect(this.menu.id,this.show,10);}
else if(this.show)
{this.base.finishShowing();}}
CMSlidingEffectInstance.prototype.hideEffect=function(changed)
{if(!this.base.canHide(changed))
return;var percent=this.percent;if(this.slideOrient=='h')
this.slideMenuV();else
this.slideMenuH();if(percent>0)
{this.percent-=this.speed;cmTimeEffect(this.menu.id,this.show,10);}
else if(!this.show)
{this.menu.style.clip='auto';this.base.finishHiding();}}
CMSlidingEffectInstance.prototype.slideMenuH=function()
{var percent=this.percent;if(percent<0)
percent=0;if(percent>100)
percent=100;var fullWidth=this.fullWidth;var fullHeight=this.fullHeight;var x=this.x;var space=percent*fullWidth/100;var menu=this.menu;if(this.slideDir=='l')
{menu.style.left=(x+fullWidth-space)+'px';menu.style.clip='rect(0px '+space+'px '+fullHeight+'px 0px)';}
else
{menu.style.left=(x-fullWidth+space)+'px';menu.style.clip='rect(0px '+fullWidth+'px '+fullHeight+'px '+(fullWidth-space)+'px)';}}
CMSlidingEffectInstance.prototype.slideMenuV=function()
{var percent=this.percent;if(percent<0)
percent=0;if(percent>100)
percent=100;var fullWidth=this.fullWidth;var fullHeight=this.fullHeight;var y=this.y;var space=percent*fullHeight/100;var menu=this.menu;if(this.slideDir=='b')
{menu.style.top=(y-fullHeight+space)+'px';menu.style.clip='rect('+(fullHeight-space)+'px '+fullWidth+'px '+fullHeight+'px 0px)';}
else
{menu.style.top=(y+fullHeight-space)+'px';menu.style.clip='rect(0px '+fullWidth+'px '+space+'px 0px)';}}
function CMSlidingEffect(speed)
{if(!speed)
speed=10;else if(speed<=0)
speed=10;else if(speed>=100)
speed=100;this.speed=speed;}
CMSlidingEffect.prototype.getInstance=function(menu,orient)
{return new CMSlidingEffectInstance(menu,orient,this.speed);}
function CMFadingEffectInstance(menu,showSpeed,hideSpeed)
{this.base=new CMSpecialEffectInstance(this,menu);menu.style.overflow='hidden';this.showSpeed=showSpeed;this.hideSpeed=hideSpeed;this.opacity=0;}
CMFadingEffectInstance.prototype.showEffect=function(changed)
{if(!this.base.canShow(changed))
return;var menu=this.menu;var opacity=this.opacity;this.setOpacity();if(opacity==0)
{this.base.startShowing();}
if(opacity<100)
{this.opacity+=10;cmTimeEffect(menu.id,this.show,this.showSpeed);}
else if(this.show)
{this.base.finishShowing();}}
CMFadingEffectInstance.prototype.hideEffect=function(changed)
{if(!this.base.canHide(changed))
return;var menu=this.menu;var opacity=this.opacity;this.setOpacity();if(this.opacity>0)
{this.opacity-=10;cmTimeEffect(menu.id,this.show,this.hideSpeed);}
else if(!this.show)
{this.base.finishHiding();}}
CMFadingEffectInstance.prototype.setOpacity=function()
{this.menu.style.opacity=this.opacity/100;}
function CMFadingEffect(showSpeed,hideSpeed)
{this.showSpeed=showSpeed;this.hideSpeed=hideSpeed;}
CMFadingEffect.prototype.getInstance=function(menu,orient)
{return new CMFadingEffectInstance(menu,this.showSpeed,this.hideSpeed);}
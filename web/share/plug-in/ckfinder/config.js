/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckfinder.com/license
*/

CKFinder.customConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.skin = 'v1';
        //[ LongH@6Sep11
        var sd = opener.sd;
        var locale = sd.util.isValidS(sd.operator.getLocale()) ? sd.operator.getLocale() : "en";
	config.language = locale;
        config.defaultLanguage = locale;
        //] LongH@6Sep11
};

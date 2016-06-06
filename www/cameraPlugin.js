var cameraplugin = {
    openCamera: function(successCallback, errorCallback) {
		count = 1;
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Cameraplugin', // mapped to our native Java class called "MediaSizeCut"
            'openCamera', // with this action name
            [count]
        ); 
    }
};	
module.exports = cameraplugin;
var cameraplugin = {
    openCamera: function(count,successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Cameraplugin', // mapped to our native Java class called "MediaSizeCut"
            'openCamera', // with this action name
            [1]
        ); 
    }
};	
module.exports = cameraplugin;
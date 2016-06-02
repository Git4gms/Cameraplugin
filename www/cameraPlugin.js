var cameraplugin = {
    createEvent: function(count,successCallback, errorCallback) {
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
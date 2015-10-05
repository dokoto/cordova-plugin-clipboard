var clipBoard = {

    addEvent: function(url, successCallback, errorCallback) {
        cordova.exec(successCallback,
                    errorCallback,
                    'clipBoard', // Class name in Native side
                    'addEvent', // action parameter in Native side public boolean execute(String action,...)
                    [url]
        );
    }
    
};
module.exports = clipBoard;

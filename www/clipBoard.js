var clipBoard = {

    setCopyListener: function(successCallback, errorCallback) {
        cordova.exec(successCallback,
                    errorCallback,
                    'clipBoard', // Class name in Native side
                    'setCopyListener', // action parameter in Native side public boolean execute(String action,...)
                    []
        );
    }
    
};
module.exports = clipBoard;

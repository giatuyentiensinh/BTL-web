app.controller('contactCtrl', ['$scope', 'PhoneService',
                	function($scope,  PhoneService) {

	$scope.check = true;
	$scope.checkError = true;

    $scope.submit = function() {
    	var package = {
    		username: $scope.username,
    		email: $scope.email,
    		subject: $scope.subject,
    		content: $scope.content
    	};

    	PhoneService.comment(package)
    		.success(function(data, status, headers, config) {
    			if(data === 'OK') {
    				$scope.check = false;
    			}
    			console.log("success");
    			console.log(data);
    			// console.log(status);
    			// console.log(headers);
    			// console.log(config);
    		})
    		.error(function(data, status, headers, config) {
    			$scope.checkError = false;
    			console.log("error");
    			// console.log(data);
    			// console.log(status);
    			// console.log(headers);
    			// console.log(config);
    		});
    }

    $scope.closeAlert = function() {
    	$scope.check = true;
    	$scope.checkError = true;
    	$scope.username = $scope.email = $scope.subject = $scope.content = '';
    }

}]);
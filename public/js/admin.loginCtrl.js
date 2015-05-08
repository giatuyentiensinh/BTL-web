app.controller('LoginCtrl', ['$scope', '$http', function($scope, $http) {

	$scope.login = function() {
		$scope.messageServer = '';
		$http.post('/login', {username: $scope.username, password: $scope.password})
			.success(function(data, status, headers, config) {
				// console.log(headers);
				// console.log('Ngon');
				console.log(data);
			})
			.error(function(data, status, headers, config) {
				console.log(data);
				$scope.messageServer = data;
				console.log('error form server');
			});
	}
}]);